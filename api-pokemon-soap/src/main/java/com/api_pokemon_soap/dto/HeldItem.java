package com.api_pokemon_soap.dto;

import java.util.List;

public class HeldItem {
    private List<HeldItemData> heldItemData;

    public List<HeldItemData> getHeldItemData() {
        return heldItemData;
    }

    public void setHeldItemData(List<HeldItemData> heldItemData) {
        this.heldItemData = heldItemData;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HeldItem{");
        sb.append("heldItemData=").append(heldItemData);
        sb.append('}');
        return sb.toString();
    }
}