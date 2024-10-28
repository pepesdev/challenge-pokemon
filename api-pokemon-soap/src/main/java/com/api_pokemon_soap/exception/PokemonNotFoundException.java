package com.api_pokemon_soap.exception;

public class PokemonNotFoundException extends RuntimeException{
    public PokemonNotFoundException(String message){
        super(message);
    }

}
