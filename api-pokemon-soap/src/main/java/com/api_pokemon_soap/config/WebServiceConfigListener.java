package com.api_pokemon_soap.config;

import com.api_pokemon_soap.interceptor.SoapListenerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;

import java.util.List;

@Configuration
public class WebServiceConfigListener  extends WsConfigurerAdapter {
    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(soapListenerInterceptor());
    }
    @Bean
    public SoapListenerInterceptor soapListenerInterceptor() {
        return new SoapListenerInterceptor();
    }

}
