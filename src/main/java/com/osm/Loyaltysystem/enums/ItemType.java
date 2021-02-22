package com.osm.Loyaltysystem.enums;


import lombok.Getter;

@Getter
public enum ItemType {
    GROCERIES( "GROCERIES" )
    ;


    ItemType(String type) {
        this.type = type;
    }

    private String type;

    public static ItemType getType(String code) {
        for( ItemType itemType : ItemType.values() ) {
            if( itemType.type.equalsIgnoreCase( code ) ) {
                return itemType;
            }
        }
        return null;
    }

}
