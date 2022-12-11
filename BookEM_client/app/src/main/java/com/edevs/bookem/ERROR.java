package com.edevs.bookem;

@FunctionalInterface
public interface ERROR {

    // An interface to conduit errors through the Linking

    void DEBUG(String api, Exception e);
}
