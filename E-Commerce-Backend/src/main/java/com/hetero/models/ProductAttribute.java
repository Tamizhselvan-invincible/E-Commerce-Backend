package com.hetero.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "product_attributes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @JsonProperty("Name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "attribute_values", joinColumns = @JoinColumn(name = "attribute_id"))
    @Column(name = "value")
    @JsonProperty("Values")
    private List<String> values;


}

