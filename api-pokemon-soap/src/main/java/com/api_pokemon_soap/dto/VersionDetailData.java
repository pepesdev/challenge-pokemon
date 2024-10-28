package com.api_pokemon_soap.dto;

public class VersionDetailData {

    private Integer rarity;
    private Version version;

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VersionDetailData{");
        sb.append("rarity=").append(rarity);
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }
}
