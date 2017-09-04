package com.nisie.udacitybakingapp.main.network;

/**
 * @author by nisie on 6/27/2017.
 */

public class ErrorNetworkException extends RuntimeException {
    public ErrorNetworkException(String errorMessage) {
        super(errorMessage);
    }
}
