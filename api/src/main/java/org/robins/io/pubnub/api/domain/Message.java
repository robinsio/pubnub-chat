package org.robins.io.pubnub.api.domain;

/**
 * ************************************************************************************
 * Author: Jonathon Robins
 * Created: 02/09/2014 11:31.
 * *************************************************************************************
 */
public class Message {

    private String name;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
