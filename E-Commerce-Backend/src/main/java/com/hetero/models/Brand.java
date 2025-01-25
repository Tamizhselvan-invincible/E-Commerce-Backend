package com.hetero.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Id")
    private Integer id;

    @NotNull
    @JsonProperty("Name")
    private String brandName;

    @JsonProperty("Image")
    private String brandImage;

    @JsonProperty("IsFeatured")
    private boolean isFeatured;

    @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent
    @JsonProperty("CreatedAt")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent
    @JsonProperty("UpdatedAt")
    private Date dateUpdated;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BrandCategory> brandCategories = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        dateCreated = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        dateUpdated = new Date();
    }
}
