package com.example.demo.domain.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Post {
    private int id;
    private String title;
    private String body;
    private int userId;
}
