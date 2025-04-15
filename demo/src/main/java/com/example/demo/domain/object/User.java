package com.example.demo.domain.object;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class User {
    private int id;
    private String name;
    private LocalDate birthday;
}
