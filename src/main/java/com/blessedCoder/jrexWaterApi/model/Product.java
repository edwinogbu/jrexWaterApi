package com.blessedCoder.jrexWaterApi.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private String category;
    private String imagePath; // Image path saved in the database

    // Getters and setters
}
