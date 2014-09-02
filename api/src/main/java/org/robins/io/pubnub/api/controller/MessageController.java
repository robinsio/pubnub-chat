package org.robins.io.pubnub.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.robins.io.pubnub.api.domain.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * ************************************************************************************
 * Author: Jonathon Robins
 * Created: 02/09/2014 10:38.
 * *************************************************************************************
 */
@Controller
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.robins.io.pubnub.api.web"})
public class MessageController {

    private final static String PUBLISH_KEY = "pub-c-726b627c-a21f-4518-8d2c-133172d01ebd";
    private final static String SUBSCRIBE_KEY = "sub-c-d53f9f88-3282-11e4-a7b3-02ee2ddab7fe";

    private Pubnub pubnub = new Pubnub(PUBLISH_KEY, SUBSCRIBE_KEY);
    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(method = RequestMethod.POST, value = "/message")
    @ResponseBody
    Message post(@RequestBody Message message) throws Exception {

        String json = mapper.writeValueAsString(message);
        JSONObject jsonObj = new JSONObject(json);

        Callback callback = new Callback() {
            public void successCallback(String channel, Object response) {
                System.out.println("Success: " + response.toString());
            }
            public void errorCallback(String channel, Object error) {
                System.out.println("Error: :" + error.toString());
            }
        };

        pubnub.publish("my_channel", jsonObj , callback);

        return message;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MessageController.class, args);
    }
}
