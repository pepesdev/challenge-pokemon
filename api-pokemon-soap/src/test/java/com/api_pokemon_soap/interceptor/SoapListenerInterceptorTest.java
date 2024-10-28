package com.api_pokemon_soap.interceptor;
import com.api_pokemon_soap.dto.RequestLog;
import com.api_pokemon_soap.repository.RequestLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.*;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class SoapListenerInterceptorTest {

    @Mock
    private RequestLogRepository requestLogRepository;

    @Mock
    private MessageContext messageContext;

    @Mock
    private SoapMessage soapRequestMessage;


    @Mock
    private SoapMessage soapResponseMessage;

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private SoapListenerInterceptor soapListenerInterceptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        RequestAttributes requestAttributes = new ServletRequestAttributes(httpServletRequest);
        RequestContextHolder.setRequestAttributes(requestAttributes);
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setRemoteAddr("127.0.0.1");
        ServletRequestAttributes attributes = new ServletRequestAttributes(mockHttpServletRequest);
        RequestContextHolder.setRequestAttributes(attributes);
    }

    @Test
    void afterCompletion_ShouldSaveLogToRepository() throws Exception {
        soapListenerInterceptor.startTime = System.currentTimeMillis() - 1000;
        soapListenerInterceptor.requestOut = "<Request>Sample Request</Request>";
        soapListenerInterceptor.responseOut = "<Response>Sample Response</Response>";
        soapListenerInterceptor.clientIp = "127.0.0.1";
        soapListenerInterceptor.method = "testMethod";
        soapListenerInterceptor.afterCompletion(messageContext, null, null);
        verify(requestLogRepository).save(any(RequestLog.class));
    }

    @Test
    void testHandleResponse() throws Exception {
        when(messageContext.getResponse()).thenReturn(soapResponseMessage);
        assertTrue(soapListenerInterceptor.handleResponse(messageContext, new Object()));
        verify(messageContext, times(1)).getResponse();
    }

    @Test
    void testLogSoapMessage() throws TransformerException {
        when(soapRequestMessage.getPayloadSource()).thenReturn(null);
        String loggedMessage = soapListenerInterceptor.logSoapMessage(soapRequestMessage);
        assertNotNull(loggedMessage);
    }


    @Test
    void testHandleFault() throws Exception {
        when(messageContext.getResponse()).thenReturn(soapResponseMessage);

        assertTrue(soapListenerInterceptor.handleFault(messageContext, new Object()));
        verify(messageContext, times(1)).getResponse();
    }

    @Test
    void testHandleRequest() throws Exception {
        when(messageContext.getRequest()).thenReturn(soapRequestMessage);
        when(soapRequestMessage.getSoapAction()).thenReturn("testSoapAction");
        assertTrue(soapListenerInterceptor.handleRequest(messageContext, new Object()));
    }

    @Test
    void testLogSoapMessageWithValidPayload() throws TransformerException {
        SoapMessage validMessage = Mockito.mock(SoapMessage.class);
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><test>Contenido</test>";
        Mockito.when(validMessage.getPayloadSource())
                .thenReturn(new StreamSource(new StringReader(xmlContent)));

        String result = soapListenerInterceptor.logSoapMessage(validMessage);
        assertEquals(xmlContent, result);
    }


}
