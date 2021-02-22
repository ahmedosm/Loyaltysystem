package com.osm.Loyaltysystem.exception;

public class InvalidDataException extends GenralException {

    public InvalidDataException(String code, String message, Object... args) {
        super( code, message, args );
    }

    public InvalidDataException(String code, String message, boolean isBackend) {
        super( code, message, isBackend );
    }
}
