package com.stockmanager.stockmanager.controller;

import com.stockmanager.stockmanager.service.ChatbotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chatbot")
public class ChatBotController {
    private final ChatbotService chatbotService;

    public ChatBotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/ask")
    public ResponseEntity<String> chat(@RequestBody String message) {
        String response = chatbotService.handleMessage(message);
        return ResponseEntity.ok(response);
    }
}
