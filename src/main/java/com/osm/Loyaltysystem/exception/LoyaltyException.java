package com.osm.Loyaltysystem.exception;

import lombok.Getter;

@Getter
public class LoyaltyException extends GenralException {

    public LoyaltyException(String code, String message, Object... args) {
        super( code, message, args );
    }

    public LoyaltyException(String code, String message) {
        super( code, message );
    }
}

