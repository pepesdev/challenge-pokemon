package com.api_pokemon_soap.enpoint;

import com.api_pokemon_soap.endpoint.PokemonEnpoint;
import com.api_pokemon_soap.exception.PokemonNotFoundException;
import com.api_pokemon_soap.pokemon.gen.*;
import com.api_pokemon_soap.service.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EndpointTest {
    @Mock
    private PokemonService pokemonService;

    @InjectMocks
    private PokemonEnpoint pokemonEndpoint;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPokemonByName() {
        GetPokemonRequestByName request = new GetPokemonRequestByName();
        request.setName("Pikachu");
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Pikachu");
        when(pokemonService.getPokemonByName("Pikachu")).thenReturn(pokemon);
        GetPokemonResponseByName response = pokemonEndpoint.getPokemonByName(request);
        assertEquals("Pikachu", response.getPokemon().getName());
        verify(pokemonService, times(2)).getPokemonByName("Pikachu");
    }

    @Test
     void testGetPokemonByName_NotFound() {
        GetPokemonRequestByName request = new GetPokemonRequestByName();
        request.setName("MissingPokemon");

        when(pokemonService.getPokemonByName("MissingPokemon")).thenReturn(new Pokemon());

        assertThrows(PokemonNotFoundException.class, () -> pokemonEndpoint.getPokemonByName(request));
        verify(pokemonService, times(1)).getPokemonByName("MissingPokemon");
    }

    @Test
     void testGetPokemonAbilities() {
        GetPokemonRequestAbilities request = new GetPokemonRequestAbilities();
        request.setName("pikachu");

        AbilityData ability = new AbilityData();
        ability.setSlot(23);
        List<AbilityData> abilities = List.of(ability);

        when(pokemonService.getPokemonAbilities("pikachu")).thenReturn(abilities);

        GetPokemonResponseAbilities response = pokemonEndpoint.getPokemonAbilities(request);

        assertEquals(1, response.getAbilities().size());
        assertEquals(23, response.getAbilities().get(0).getSlot());
        verify(pokemonService, times(1)).getPokemonAbilities("pikachu");
    }

    @Test
     void testGetPokemonAbilities_NotFound() {
        GetPokemonRequestAbilities request = new GetPokemonRequestAbilities();
        request.setName("UnknownPokemon");

        when(pokemonService.getPokemonAbilities("UnknownPokemon")).thenReturn(Collections.emptyList());

        assertThrows(PokemonNotFoundException.class, () -> pokemonEndpoint.getPokemonAbilities(request));
        verify(pokemonService, times(1)).getPokemonAbilities("UnknownPokemon");
    }

    @Test
     void testGetHeldItemsPokemon() {
        GetHeldItemsRequest request = new GetHeldItemsRequest();
        request.setName("Pikachu");

        HeldItem item = new HeldItem();
        Item item2 = new Item();
        item2.setName("Light Ball");

        item.setItem(item2);
        List<HeldItem> heldItems = Arrays.asList(item);

        when(pokemonService.getPokemonHeldItems("Pikachu")).thenReturn(heldItems);

        GetHeldItemsResponse response = pokemonEndpoint.getHelItemsPokemon(request);

        assertEquals(1, response.getHeldItems().size());
        assertEquals("Light Ball", response.getHeldItems().get(0).getItem().getName());
        verify(pokemonService, times(1)).getPokemonHeldItems("Pikachu");
    }

    @Test
     void testGetHeldItemsPokemon_NotFound() {
        GetHeldItemsRequest request = new GetHeldItemsRequest();
        request.setName("UnknownPokemon");

        when(pokemonService.getPokemonHeldItems("UnknownPokemon")).thenReturn(Collections.emptyList());

        assertThrows(PokemonNotFoundException.class, () -> pokemonEndpoint.getHelItemsPokemon(request));
        verify(pokemonService, times(1)).getPokemonHeldItems("UnknownPokemon");
    }



}
