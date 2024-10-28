package com.api_pokemon_soap.dto;

import java.util.List;

public class HeldItemData {

    private ItemDTO item;

    private List<VersionDetailData> version_details;


    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public List<VersionDetailData> getVersion_details() {
        return version_details;
    }

    public void setVersion_details(List<VersionDetailData> version_details) {
        this.version_details = version_details;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HeldItemData{");
        sb.append("item=").append(item);
        sb.append(", version_details=").append(version_details);
        sb.append('}');
        return sb.toString();
    }
}
