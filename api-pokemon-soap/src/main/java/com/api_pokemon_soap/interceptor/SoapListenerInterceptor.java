package com.api_pokemon_soap.interceptor;

import com.api_pokemon_soap.dto.RequestLog;
import com.api_pokemon_soap.repository.RequestLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.SoapMessage;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Date;

public class SoapListenerInterceptor implements EndpointInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoapListenerInterceptor.class);

    @Autowired
    private RequestLogRepository requestLogRepository;

    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private String requestLogTopic;

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) {
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) {
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) {
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) {
        try {
            RequestLog requestLog = new RequestLog();
            requestLog.setDate_request(new Date());
            requestLog.setElapsedTime("N/A");
            requestLog.setIp_origin(resolveClientIp());
            requestLog.setMethod(resolveSoapAction((SoapMessage) messageContext.getRequest()));
            requestLog.setRequest(logSoapMessage((SoapMessage) messageContext.getRequest()));
            requestLog.setResponse(logSoapMessage((SoapMessage) messageContext.getResponse()));

            requestLogRepository.save(requestLog);

            if (kafkaTemplate != null) {
                kafkaTemplate.send(requestLogTopic, requestLog.toString());
            }
        } catch (Exception loggingError) {
            LOGGER.warn("No se pudo registrar la petición SOAP sin afectar la respuesta del cliente: {}", loggingError.getMessage());
        }

        if (ex != null) {
            LOGGER.info("SOAP request completed with error: {}", ex.getMessage());
        } else {
            LOGGER.info("SOAP request completed successfully.");
        }
    }

    private String resolveClientIp() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return "unknown";
        }
        HttpServletRequest request = attributes.getRequest();
        return request != null ? request.getRemoteAddr() : "unknown";
    }

    private String resolveSoapAction(SoapMessage soapMessage) {
        if (soapMessage == null) {
            return "unknown";
        }
        String action = soapMessage.getSoapAction();
        return (action == null || action.isBlank()) ? "unknown" : action;
    }

    String logSoapMessage(SoapMessage soapMessage) throws TransformerException {
        if (soapMessage == null || soapMessage.getPayloadSource() == null) {
            return "El mensaje SOAP o su contenido está vacío.";
        }
        StringWriter stringWriter = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(soapMessage.getPayloadSource(), new StreamResult(stringWriter));
        return stringWriter.toString();
    }
}
