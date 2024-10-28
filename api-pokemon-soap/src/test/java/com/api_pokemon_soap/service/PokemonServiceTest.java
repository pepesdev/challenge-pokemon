package com.api_pokemon_soap.service;
import com.api_pokemon_soap.dto.*;
import com.api_pokemon_soap.dto.Ability;
import com.api_pokemon_soap.dto.AbilityData;
import com.api_pokemon_soap.dto.Version;
import com.api_pokemon_soap.pokemon.gen.*;
import com.api_pokemon_soap.pokemon.gen.HeldItem;
import com.api_pokemon_soap.repository.PokemonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class PokemonServiceTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonService pokemonService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testGetPokemonByName() {
        PokemonDTO mockPokemonDTO = new PokemonDTO();
        mockPokemonDTO.setName("Pikachu");
        mockPokemonDTO.setId("1");
        mockPokemonDTO.setBase_experience(112);
        mockPokemonDTO.setLocation_area_encounters("forest");

        when(pokemonRepository.findByName("Pikachu")).thenReturn(mockPokemonDTO);

        Pokemon result = pokemonService.getPokemonByName("Pikachu");

        assertNotNull(result);
        assertEquals("Pikachu", result.getName());
        assertEquals(1, result.getId());
        assertEquals(112, result.getBaseExperience());
        assertEquals("forest", result.getLocationAreaEncounters());
        verify(pokemonRepository, times(1)).findByName("Pikachu");
    }

     @Test
      void testGetPokemonByName_NotFound() {
         when(pokemonRepository.findByName("Unknown")).thenReturn(null);

         Pokemon result = pokemonService.getPokemonByName("Unknown");

         assertNotNull(result); // Result should still be non-null due to how mapToPokemon handles null
         assertNull(result.getName());
         verify(pokemonRepository, times(1)).findByName("Unknown");
     }

     @Test
      void testGetPokemonAbilities() {
         PokemonDTO mockPokemonDTO = new PokemonDTO();
         AbilityData abilityData = new AbilityData();
         abilityData.setSlot(1);
         abilityData.setIs_hidden(true);
         Ability ability = new Ability();
         ability.setName("Static");
         ability.setUrl("https://pokeapi.co/api/v2/ability/9/");
         abilityData.setAbility(ability);
         mockPokemonDTO.setAbilities(Collections.singletonList(abilityData));
         when(pokemonRepository.findByName("Pikachu")).thenReturn(mockPokemonDTO);
         List<com.api_pokemon_soap.pokemon.gen.AbilityData> abilities = pokemonService.getPokemonAbilities("Pikachu");
         assertNotNull(abilities);
         assertEquals(1, abilities.size());
         assertEquals("Static", abilities.get(0).getAbility().getName());
         verify(pokemonRepository, times(1)).findByName("Pikachu");
     }

     @Test
     void testGetPokemonAbilities_NoAbilities() {
         PokemonDTO mockPokemonDTO = new PokemonDTO();
         mockPokemonDTO.setAbilities(new ArrayList<>());

         when(pokemonRepository.findByName("Pikachu")).thenReturn(mockPokemonDTO);

         List<com.api_pokemon_soap.pokemon.gen.AbilityData> abilities = pokemonService.getPokemonAbilities("Pikachu");

         assertNotNull(abilities);
         assertTrue(abilities.isEmpty());
         verify(pokemonRepository, times(1)).findByName("Pikachu");
     }

     @Test
     void testGetPokemonHeldItems_NoHeldItems() {
         PokemonDTO mockPokemonDTO = new PokemonDTO();
         mockPokemonDTO.setHeld_items(new ArrayList<>());
         when(pokemonRepository.findByName("Pikachu")).thenReturn(mockPokemonDTO);
         List<HeldItem> heldItems = pokemonService.getPokemonHeldItems("Pikachu");
         assertNotNull(heldItems);
         assertTrue(heldItems.isEmpty());
         verify(pokemonRepository, times(1)).findByName("Pikachu");
     }

     @Test
      void testGetPokemonHeldItems() {
         PokemonDTO mockPokemonDTO = new PokemonDTO();
         HeldItemData heldItemData = getHeldItems();
         mockPokemonDTO.setHeld_items(Collections.singletonList(heldItemData));

         when(pokemonRepository.findByName("Pikachu")).thenReturn(mockPokemonDTO);

         List<HeldItem> heldItems = pokemonService.getPokemonHeldItems("Pikachu");

         assertNotNull(heldItems);
         assertEquals(1, heldItems.size());
         assertEquals("Light Ball", heldItems.get(0).getItem().getName());
         assertEquals("red", heldItems.get(0).getVersionDetails().get(0).getVersion().getName());
         assertEquals(5, heldItems.get(0).getVersionDetails().get(0).getRarity());
         verify(pokemonRepository, times(1)).findByName("Pikachu");
     }


     public HeldItemData getHeldItems(){

        HeldItemData heldItemData = new HeldItemData();
        ItemDTO item = new ItemDTO();
        item.setName("Light Ball");
        item.setUrl("https://pokeapi.co/api/v2/item/1/");
        heldItemData.setItem(item);
        VersionDetailData versionDetailData = new VersionDetailData();
        versionDetailData.setRarity(5);
         Version version = new Version();
         version.setName("red");
         version.setUrl("https://pokeapi.co/api/v2/version/1/");
         versionDetailData.setVersion(version);
         List<VersionDetailData> lst = new ArrayList<>();
         lst.add(versionDetailData);
        heldItemData.setVersion_details(lst);
        return heldItemData;
     }






}
