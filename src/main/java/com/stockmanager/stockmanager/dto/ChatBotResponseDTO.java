package com.stockmanager.stockmanager.dto;

public class ChatBotResponseDTO {

    private String response;

    public ChatBotResponseDTO(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
