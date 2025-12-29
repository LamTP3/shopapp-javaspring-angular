package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 350)
    private String name;

    private float price;

    @Column(name = "thumbnail", length = 300)
    private String thumbnail;

    @Column(name = "description", length = 300)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
