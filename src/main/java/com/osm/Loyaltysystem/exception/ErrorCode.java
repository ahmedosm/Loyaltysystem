package com.osm.Loyaltysystem.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults( level = AccessLevel.PRIVATE )
public enum ErrorCode {
    ERROR_EMPTY_REQUEST( "134455", "mandatory data is missing" );
    String code;
    String message;
    }
