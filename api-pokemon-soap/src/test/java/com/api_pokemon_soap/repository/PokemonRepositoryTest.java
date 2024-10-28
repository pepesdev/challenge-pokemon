package com.api_pokemon_soap.repository;

import com.api_pokemon_soap.dto.PokemonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
public class PokemonRepositoryTest {

    @InjectMocks
    private PokemonRepository pokemonRepository;

    private final String baseUrl = "http://pokemon-api.com/";
    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pokemonRepository.path = baseUrl;
    }

    @Test
    void testFindByNameSuccessful() {
        String name = "pikachu";
        PokemonDTO mockPokemon = new PokemonDTO();
        mockPokemon.setName(name);
        when(restTemplate.getForObject(baseUrl + name, PokemonDTO.class)).thenReturn(mockPokemon);
        PokemonDTO result = pokemonRepository.findByName(name);
        assertEquals(mockPokemon, result);
        verify(restTemplate, times(1)).getForObject(baseUrl + name, PokemonDTO.class);
    }

    @Test
    void testFindByNameClientErrorException() {
        String name = "nonexistent";
        when(restTemplate.getForObject(baseUrl + name, PokemonDTO.class)).thenThrow(HttpClientErrorException.class);
        PokemonDTO result = pokemonRepository.findByName(name);
        assertNull(result);
        verify(restTemplate, times(1)).getForObject(baseUrl + name, PokemonDTO.class);
    }

    @Test
    void testFindByNameServerErrorException() {
        String name = "serverError";
        when(restTemplate.getForObject(baseUrl + name, PokemonDTO.class)).thenThrow(HttpServerErrorException.class);
        PokemonDTO result = pokemonRepository.findByName(name);
        assertNull(result);
        verify(restTemplate, times(1)).getForObject(baseUrl + name, PokemonDTO.class);
    }

    @Test
    void testFindByNameOtherException() {
        String name = "unexpectedError";
        when(restTemplate.getForObject(baseUrl + name, PokemonDTO.class)).thenThrow(RuntimeException.class);
        PokemonDTO result = pokemonRepository.findByName(name);
        assertNull(result);
        verify(restTemplate, times(1)).getForObject(baseUrl + name, PokemonDTO.class);
    }

}
