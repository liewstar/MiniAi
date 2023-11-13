package com.example.miniaibackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderArg {
    public HashMap<String,Object> map;
    public int pageNum;
    public int pageSize;
}
