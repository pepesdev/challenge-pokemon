package com.api_pokemon_soap.dto;

public class AbilityData {
    private Ability ability;
    private Boolean is_hidden;

    private Integer slot;

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public Boolean getIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(Boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbilityData{");
        sb.append("ability=").append(ability);
        sb.append(", is_hidden=").append(is_hidden);
        sb.append(", slot=").append(slot);
        sb.append('}');
        return sb.toString();
    }
}
