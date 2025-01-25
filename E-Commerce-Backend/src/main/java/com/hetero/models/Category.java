package com.hetero.models;

import com.google.firebase.database.annotations.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.time.LocalDateTime;
import javax.validation.constraints.PastOrPresent;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Integer categoryId;

    @NotNull
    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @URL
    @Column(name = "category_image")
    private String categoryImage;

    @Column(name = "parent_category_id")
    private Integer parentCategoryId;

    @Column(name = "is_featured", nullable = false, columnDefinition = "boolean default false")
    private Boolean isFeatured;

    @PastOrPresent
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @PastOrPresent
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime dateUpdated;

    @PrePersist
    protected void onCreate() {
        dateCreated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateUpdated = LocalDateTime.now();
    }
}
