package com.api_pokemon_soap.interceptor;

import com.api_pokemon_soap.dto.RequestLog;
import com.api_pokemon_soap.repository.RequestLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Objects;


public class SoapListenerInterceptor implements EndpointInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoapListenerInterceptor.class);
    @Autowired
    private RequestLogRepository requestLogRepository;
     long startTime;
    String requestOut;
     String responseOut;
     String clientIp;
     String method;

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        responseOut = null;
        startTime = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        clientIp = request.getRemoteAddr();
        SoapMessage soapRequest = (SoapMessage) messageContext.getRequest();
        requestOut = logSoapMessage(soapRequest);
        method = soapRequest.getSoapAction();
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
        SoapMessage soapResponse = (SoapMessage) messageContext.getResponse();
        responseOut = logSoapMessage(soapResponse);
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
        SoapMessage soapFault = (SoapMessage) messageContext.getResponse();
        if(responseOut==null){
            responseOut = logSoapMessage(soapFault);
        }
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        if (ex != null) {
            LOGGER.info("SOAP request completed with error. ");
            LOGGER.info(ex.getMessage());
        } else {
            LOGGER.info("SOAP request completed successfully. ");
        }

        RequestLog requestLog = new RequestLog();
        requestLog.setRequest(requestOut);
        requestLog.setResponse(responseOut);
        requestLog.setElapsedTime(duration + "ms");
        requestLog.setDate_request(new Date());
        requestLog.setIp_origin(clientIp);
        requestLog.setMethod(method);
        requestLogRepository.save(requestLog);
    }


    String  logSoapMessage(SoapMessage soapMessage) throws TransformerException {
        if (soapMessage == null || soapMessage.getPayloadSource() == null) {
            return "El mensaje SOAP o su contenido está vacío.";
        }
        StringWriter stringWriter = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(soapMessage.getPayloadSource(), new StreamResult(stringWriter));
        return stringWriter.toString();
    }


}
