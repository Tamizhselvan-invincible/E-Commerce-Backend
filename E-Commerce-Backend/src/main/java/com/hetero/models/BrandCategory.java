package com.hetero.models;


import com.fasterxml.jackson.annotation.JsonProperty;
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
public class BrandCategory {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonProperty("brandId")
    private Brand brand;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonProperty("categoryId")
    private Category category;
}
