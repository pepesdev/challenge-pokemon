package com.api_pokemon_soap.service;

import com.api_pokemon_soap.dto.PokemonDTO;
import com.api_pokemon_soap.dto.VersionDetailData;
import com.api_pokemon_soap.pokemon.gen.*;
import com.api_pokemon_soap.repository.PokemonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {
    
    @Autowired
    private PokemonRepository pokemonRepository;

    public Pokemon getPokemonByName(String name){
        return mapToPokemon(pokemonRepository.findByName(name));
    }

    public List<AbilityData> getPokemonAbilities(String name){
        return mapToAbilities(pokemonRepository.findByName(name));
    }

    public List<HeldItem> getPokemonHeldItems(String name){
        return mapToHeldITems(pokemonRepository.findByName(name));
    }

    public Pokemon mapToPokemon(PokemonDTO inPoKemonDTO){
        Pokemon pokemon = new Pokemon();
        if(inPoKemonDTO!=null){
            pokemon.setName(inPoKemonDTO.getName());
            pokemon.setId(Integer.parseInt(inPoKemonDTO.getId()));
            pokemon.setLocationAreaEncounters(inPoKemonDTO.getLocation_area_encounters());
            pokemon.setBaseExperience(inPoKemonDTO.getBase_experience());
        }

        return pokemon;
    }



    public List<AbilityData> mapToAbilities(PokemonDTO pokemonFull){
        List<AbilityData> lstAbilities = new ArrayList<>();
        AbilityData abilityData1;
        Ability ability;

        if(pokemonFull==null){
            return lstAbilities;
        }
        if(!pokemonFull.getAbilities().isEmpty()){
            for(com.api_pokemon_soap.dto.AbilityData abilityData:pokemonFull.getAbilities()){
                abilityData1 = new AbilityData();
                ability = new Ability();
                abilityData1.setSlot(abilityData.getSlot());
                abilityData1.setIsHidden(abilityData.getIs_hidden());

                ability.setName(abilityData.getAbility().getName());
                ability.setUrl(abilityData.getAbility().getUrl());

                abilityData1.setAbility(ability);

                lstAbilities.add(abilityData1);
            }
        }

        return lstAbilities;


    }

    public List<HeldItem> mapToHeldITems(PokemonDTO pokemon){
        List<HeldItem> lstHeldItems = new ArrayList<>();
        HeldItem heldItemOut;
        Item item;
        Version version;
        List<VersionDetail> lstVersionDetail = new ArrayList<>();
        VersionDetail versionDetail;

        if(pokemon == null){
            return lstHeldItems;
        }

        if(!pokemon.getHeld_items().isEmpty()) {
            for(com.api_pokemon_soap.dto.HeldItemData heldItem: pokemon.getHeld_items()){
                heldItemOut = new HeldItem();
                item = new Item();
                item.setName(heldItem.getItem().getName());
                item.setUrl(heldItem.getItem().getUrl());
                if(!heldItem.getVersion_details().isEmpty()){
                    for(VersionDetailData versionDetailData: heldItem.getVersion_details()){
                        versionDetail = new VersionDetail();
                        version = new Version();
                        version.setName(versionDetailData.getVersion().getName());
                        version.setUrl(versionDetailData.getVersion().getUrl());
                        versionDetail.setVersion(version);
                        versionDetail.setRarity(versionDetailData.getRarity());
                        lstVersionDetail.add(versionDetail);
                    }
                }
                heldItemOut.getVersionDetails().addAll(lstVersionDetail);
                heldItemOut.setItem(item);
                lstHeldItems.add(heldItemOut);
            }


        }
        return lstHeldItems;

    }

}
