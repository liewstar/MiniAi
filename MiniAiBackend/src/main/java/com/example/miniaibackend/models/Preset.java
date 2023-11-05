package com.example.miniaibackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Preset {
    private String role;
    private String content;
}
