package com.hetero.exception;

public class BannerNotFoundException extends RuntimeException {

    public BannerNotFoundException() {
        super();
    }

    public BannerNotFoundException(String message) {
        super(message);
    }
}

