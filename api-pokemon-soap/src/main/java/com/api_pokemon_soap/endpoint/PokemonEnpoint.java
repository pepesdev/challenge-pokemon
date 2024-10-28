package com.api_pokemon_soap.endpoint;


import com.api_pokemon_soap.exception.PokemonNotFoundException;
import com.api_pokemon_soap.pokemon.gen.*;
import com.api_pokemon_soap.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import java.util.List;


@Endpoint
public class PokemonEnpoint {

    private static final String NAMESPACE_URI = "http://www.api-pokemon-soap.com/pokemon/gen";

    @Autowired
    private PokemonService pokemonService;


    @PayloadRoot(namespace = NAMESPACE_URI ,localPart = "getPokemonRequestByName")
    @ResponsePayload
    public GetPokemonResponseByName getPokemonByName(@RequestPayload GetPokemonRequestByName request){
        GetPokemonResponseByName response = new GetPokemonResponseByName();
        Pokemon pokemon = pokemonService.getPokemonByName(request.getName());
        if(pokemon.getName()==null){
            throw new PokemonNotFoundException("Pokemon no encontrado..");
        }
        response.setPokemon(pokemonService.getPokemonByName(request.getName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "getPokemonRequestAbilities")
    @ResponsePayload
    public GetPokemonResponseAbilities getPokemonAbilities(@RequestPayload GetPokemonRequestAbilities request){
        GetPokemonResponseAbilities response = new GetPokemonResponseAbilities();
        List<AbilityData> abilities= pokemonService.getPokemonAbilities(request.getName());
        if(abilities.isEmpty()){
            throw new PokemonNotFoundException("Pokemon no encontrado..");
        }
        response.getAbilities().addAll(abilities);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart ="getHeldItemsRequest")
    @ResponsePayload
    public GetHeldItemsResponse getHelItemsPokemon(@RequestPayload GetHeldItemsRequest request){
        GetHeldItemsResponse response = new GetHeldItemsResponse();
        List<HeldItem> heldItems = pokemonService.getPokemonHeldItems(request.getName());
        if(heldItems.isEmpty()){
            throw new PokemonNotFoundException("Pokemon no encontrado..");
        }
        response.getHeldItems().addAll(heldItems);
        return response;
    }





}
