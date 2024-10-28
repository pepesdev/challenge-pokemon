package com.request.log.request_log.controller;

import com.request.log.request_log.model.RequestLog;
import com.request.log.request_log.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/logs")
public class RequestController {

    @Autowired
    private RequestLogRepository requestLogRepository;

    @GetMapping
    public List<RequestLog> getAllRequests(){
        return requestLogRepository.findAll();
    }

    @GetMapping("/{id}")
    public RequestLog getRequestById(@PathVariable Long id){
        return requestLogRepository.findById(id).get();
    }

}
