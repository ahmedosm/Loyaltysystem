package com.osm.Loyaltysystem.exception;

import java.text.MessageFormat;

public class GenralException extends RuntimeException {
    final String code;
    final transient boolean isBackend;
    final transient Object[] args;

    public GenralException(String code, String message, Object... args) {
        super(message);
        this.code = code;
        this.args = args;
        this.isBackend = false;
    }

    public GenralException(String code, String message, boolean isBackend) {
        super(message);
        this.code = code;
        this.isBackend = isBackend;
        this.args = null;
    }

    public GenralException(String code, String message, Throwable e, Object... args) {
        super(message, e);
        this.code = code;
        this.args = args;
        this.isBackend = false;
    }

    public String getMessage() {
        return MessageFormat.format(super.getMessage(), this.args);
    }

    public String getCode() {
        return this.code;
    }

    public boolean isBackend() {
        return this.isBackend;
    }

    public Object[] getArgs() {
        return this.args;
    }
}





