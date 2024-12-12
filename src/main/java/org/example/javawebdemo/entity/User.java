package org.example.javawebdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    int id;
    String username;
    String password;
    String nickname;
}
