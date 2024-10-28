package com.request.log.request_log.controller;

import com.request.log.request_log.model.RequestLog;
import com.request.log.request_log.repository.RequestLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RequestController.class)
public class RequestControllerTest {

    @MockBean
    private RequestLogRepository requestLogRepository;

    @InjectMocks
    private RequestController requestController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(requestController).build();
    }

    @Test
     void testGetAllRequests() throws Exception {

        List<RequestLog> requests = getListRequest();

        when(requestLogRepository.findAll()).thenReturn(requests);

        mockMvc.perform(get("/api/logs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].ip_origin", is("172.19.0.1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].ip_origin", is("172.19.0.1")));

        verify(requestLogRepository, times(1)).findAll();
    }

    @Test
     void testGetRequestById() throws Exception {
        RequestLog request1 = getOneRequestLog();
        when(requestLogRepository.findById(1L)).thenReturn(Optional.of(request1));

        mockMvc.perform(get("/api/logs/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.ip_origin", is("172.19.0.1")));

        verify(requestLogRepository, times(1)).findById(1L);
    }



    public RequestLog getOneRequestLog(){
        RequestLog requestLog = new RequestLog();
        requestLog.setId(1);
        requestLog.setDate_request(new Date());
        requestLog.setIp_origin("172.19.0.1");
        requestLog.setElapsedTime("7ms");
        requestLog.setResponse("http://www.api-pokemon-soap.com/pokemon/gen/getAbilities");
        requestLog.setRequest("<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><gen:getPokemonRequestAbilities xmlns:gen=\\\"http://www.api-pokemon-soap.com/pokemon/gen\\\">\\n         <gen:name>di</gen:name>\\n      </gen:getPokemonRequestAbilities>");
        requestLog.setMethod("\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><SOAP-ENV:Fault xmlns:SOAP-ENV=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\"><faultcode>SOAP-ENV:Server</faultcode><faultstring xml:lang=\\\"en\\\">Pokemon no encontrado..</faultstring></SOAP-ENV:Fault>\"");
        return requestLog;

    }

    public List<RequestLog> getListRequest(){

        List<RequestLog> rstList = new ArrayList<>();
        RequestLog requestLog = new RequestLog();

        requestLog.setId(1);
        requestLog.setDate_request(new Date());
        requestLog.setIp_origin("172.19.0.1");
        requestLog.setElapsedTime("7ms");
        requestLog.setResponse("http://www.api-pokemon-soap.com/pokemon/gen/getAbilities");
        requestLog.setRequest("<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><gen:getPokemonRequestAbilities xmlns:gen=\\\"http://www.api-pokemon-soap.com/pokemon/gen\\\">\\n         <gen:name>di</gen:name>\\n      </gen:getPokemonRequestAbilities>");
        requestLog.setMethod("\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><SOAP-ENV:Fault xmlns:SOAP-ENV=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\"><faultcode>SOAP-ENV:Server</faultcode><faultstring xml:lang=\\\"en\\\">Pokemon no encontrado..</faultstring></SOAP-ENV:Fault>\"");

        RequestLog requestLog2 = new RequestLog();
        requestLog2.setId(2);
        requestLog2.setDate_request(new Date());
        requestLog2.setIp_origin("172.19.0.1");
        requestLog2.setElapsedTime("6ms");
        requestLog2.setResponse("http://www.api-pokemon-soap.com/pokemon/gen/getAbilities");
        requestLog2.setRequest("<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><gen:getPokemonRequestAbilities xmlns:gen=\\\"http://www.api-pokemon-soap.com/pokemon/gen\\\">\\n         <gen:name>di</gen:name>\\n      </gen:getPokemonRequestAbilities>");
        requestLog2.setMethod("\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><SOAP-ENV:Fault xmlns:SOAP-ENV=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\"><faultcode>SOAP-ENV:Server</faultcode><faultstring xml:lang=\\\"en\\\">Pokemon no encontrado..</faultstring></SOAP-ENV:Fault>\"");

        RequestLog requestLog3 = new RequestLog();
        requestLog3.setId(3);
        requestLog3.setDate_request(new Date());
        requestLog3.setElapsedTime("5ms");
        requestLog3.setIp_origin("172.19.0.1");
        requestLog3.setResponse("http://www.api-pokemon-soap.com/pokemon/gen/getAbilities");
        requestLog3.setRequest("<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><gen:getPokemonRequestAbilities xmlns:gen=\\\"http://www.api-pokemon-soap.com/pokemon/gen\\\">\\n         <gen:name>di</gen:name>\\n      </gen:getPokemonRequestAbilities>");
        requestLog3.setMethod("\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><SOAP-ENV:Fault xmlns:SOAP-ENV=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\"><faultcode>SOAP-ENV:Server</faultcode><faultstring xml:lang=\\\"en\\\">Pokemon no encontrado..</faultstring></SOAP-ENV:Fault>\"");


        rstList.add(requestLog);
        rstList.add(requestLog2);
        rstList.add(requestLog3);

        return rstList;
    }
}
