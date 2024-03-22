package org.example.itespringwebapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true,nullable = false,length = 40)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
}
