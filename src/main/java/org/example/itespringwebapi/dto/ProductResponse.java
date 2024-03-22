package org.example.itespringwebapi.dto;

public record ProductResponse(String uuid,
                              String name,
                              Double price,
                              Integer qty) {
}
