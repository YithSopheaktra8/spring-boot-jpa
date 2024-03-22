package org.example.itespringwebapi.dto;

import jakarta.validation.constraints.*;

public record ProductEditRequest(

        @NotNull
        @NotEmpty
        @Size(max = 40)
        String name,
        @Positive
        @NotNull
        Double price,
        @Positive
        @NotNull
        @Min(1)
        @Max(100)
        Integer qty) {
}
