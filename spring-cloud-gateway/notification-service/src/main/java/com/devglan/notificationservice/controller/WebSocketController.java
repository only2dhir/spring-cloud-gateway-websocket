package com.devglan.notificationservice.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Map;

@Controller
public class WebSocketController {

	@MessageMapping("/message")
	@SendTo("/topic/reply")
	public String processMessageFromClient(@Payload String message){
		String name = new Gson().fromJson(message, Map.class).get("name").toString();
		return "Hello " + name;
	}
	
	@MessageExceptionHandler
    @SendToUser("/topic/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}
