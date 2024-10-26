package com.hnhs.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    private String tel;
    private String username;
    private String password;
    private String email;
    private LocalDateTime registTime;
}
