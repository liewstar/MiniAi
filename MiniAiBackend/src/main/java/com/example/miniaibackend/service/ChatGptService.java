package com.example.miniaibackend.service;

import com.example.miniaibackend.models.AcceptDTO;

public interface ChatGptService {
    String Chat(AcceptDTO acceptDTO);

}
