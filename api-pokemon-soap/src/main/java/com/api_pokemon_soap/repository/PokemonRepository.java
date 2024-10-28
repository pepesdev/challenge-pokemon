package com.api_pokemon_soap.repository;


import com.api_pokemon_soap.dto.PokemonDTO;
import com.api_pokemon_soap.dto.PokemonOut;
import com.api_pokemon_soap.pokemon.gen.Pokemon;
import com.api_pokemon_soap.service.PokemonService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.cert.PolicyNode;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Repository
public class PokemonRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonRepository.class);

    @Value("${spring.external.rest.url}")
    String path;

    @Autowired
    private RestTemplate restTemplate;

    public PokemonDTO findByName(String name){
        String url = path+name;
        try{
            return restTemplate.getForObject(url,PokemonDTO.class);
        }catch(HttpClientErrorException | HttpServerErrorException e){
            LOGGER.info(e.getMessage());
            return null;
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            return null;
        }

    }


}

