package com.hetero.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "banner")
public class Banner {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String imageUrl;


    private Boolean active;

    @NotNull
    private String targetScreen;

}
