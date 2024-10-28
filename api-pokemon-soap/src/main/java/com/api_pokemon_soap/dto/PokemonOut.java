package com.api_pokemon_soap.dto;

public class PokemonOut {

    private String id;

    private String name;

    private String location_area_encounters;

    private Integer base_experience;

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

    public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    public void setLocation_area_encounters(String location_area_encounters) {
        this.location_area_encounters = location_area_encounters;
    }

    public Integer getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(Integer base_experience) {
        this.base_experience = base_experience;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PokemonOut{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", location_area_encounters='").append(location_area_encounters).append('\'');
        sb.append(", base_experience=").append(base_experience);
        sb.append('}');
        return sb.toString();
    }
}
