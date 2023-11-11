package com.example.miniaibackend.models;

import com.example.miniaibackend.domain.Order;
import com.example.miniaibackend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithOrder {
    public User user;
    public Order order;

}
