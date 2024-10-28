package com.api_pokemon_soap.dto;

import java.util.List;

public class PokemonDTO {
    private String id;

    private String name;

    private Integer base_experience;

    private List<AbilityData> abilities;

    private List<HeldItemData> held_items;

    private String location_area_encounters;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(Integer base_experience) {
        this.base_experience = base_experience;
    }

    public List<AbilityData> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilityData> abilities) {
        this.abilities = abilities;
    }

    public List<HeldItemData> getHeld_items() {
        return held_items;
    }

    public void setHeld_items(List<HeldItemData> held_items) {
        this.held_items = held_items;
    }

    public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    public void setLocation_area_encounters(String location_area_encounters) {
        this.location_area_encounters = location_area_encounters;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PokemonDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", base_experience=").append(base_experience);
        sb.append(", abilities=").append(abilities);
        sb.append(", held_items=").append(held_items);
        sb.append(", location_area_encounters='").append(location_area_encounters).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
