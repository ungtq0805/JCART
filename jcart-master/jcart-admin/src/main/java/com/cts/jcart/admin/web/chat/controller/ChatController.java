package com.cts.jcart.admin.web.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.cts.jcart.admin.web.chat.model.ChatMessage;
import com.cts.jcart.admin.web.controllers.JCartAdminBaseController;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Controller
public class ChatController extends JCartAdminBaseController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
