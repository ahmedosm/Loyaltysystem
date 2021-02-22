package com.osm.Loyaltysystem.enums;

import lombok.Getter;

@Getter
public enum UserType {
    EMPLOYEE( "EMPLOYEE", 30 ), AFFILIATE( "AFFILIATE", 10 ), CUSTOMER( "CUSTOMER", 5 );
    private double discount;
    private String type;

    UserType(String type, double discount) {

        this.discount = discount;
        this.type = type;
    }

    public static UserType getType(String type) {
        for( UserType userType : UserType.values() ) {
            if( userType.getType().equalsIgnoreCase( type ) ) {
                return userType;
            }
        }
        return null;
    }
}
