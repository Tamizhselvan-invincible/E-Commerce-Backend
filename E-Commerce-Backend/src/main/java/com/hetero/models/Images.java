package com.hetero.models;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "images")
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("url")
    @Column(nullable = false)
    private String url;

    @JsonProperty("folder")
    private String folder;

    @JsonProperty("sizeBytes")
    private Long sizeBytes;

    @JsonProperty("filename")
    @Column(nullable = false)
    private String filename;

    @JsonProperty("fullPath")
    private String fullPath;

    @JsonProperty("createdAt")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("contentType")
    private String contentType;

    @JsonProperty("mediaCategory")
    private String mediaCategory;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
