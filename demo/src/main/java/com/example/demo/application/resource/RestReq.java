package com.example.demo.application.resource;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestReq {
    @Min(0)
    @Max(1)
    private int id;

    @Size(min = 1, max = 5)
    @NotBlank   // The annotated element must not be null and must contain at least one non-whitespace character.
    private String name;
}
