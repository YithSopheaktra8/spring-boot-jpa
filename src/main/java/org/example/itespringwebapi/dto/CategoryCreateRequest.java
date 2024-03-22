package org.example.itespringwebapi.dto;

import jakarta.validation.constraints.*;

public record CategoryCreateRequest(
        @NotBlank
        @Size(max = 40)
        String name,
        String description) {
}
