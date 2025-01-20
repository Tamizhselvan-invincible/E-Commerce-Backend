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
public class Brand {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("Id")
    private Integer brandId;

    @NotNull
    @JsonProperty("Name")
    private String brandName;

    @JsonProperty("Image")
    private String brandImage;

    @JsonProperty("IsFeatured")
    private boolean isFeatured;

    @PastOrPresent
    @JsonProperty("CreatedAt")
    private Date dateCreated;

    @PastOrPresent
    @JsonProperty("UpdatedAt")
    private Date dateUpdated;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories;

    @PrePersist
    protected void onCreate() {
        dateCreated = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        dateUpdated = new Date();
    }
}
