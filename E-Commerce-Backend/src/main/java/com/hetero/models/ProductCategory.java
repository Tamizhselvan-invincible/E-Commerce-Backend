package com.hetero.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "product_categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductCategory{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name = "product_id", nullable = false)
    @JsonProperty("productId")
    private int productId;


    @Column(name = "category_id", nullable = false)
    @JsonProperty("categoryId")
    private int categoryId;
}
