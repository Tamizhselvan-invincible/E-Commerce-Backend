package com.hetero.models;



import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

import lombok.*;

@Data
@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @JsonProperty("url")
    private String url;

    @JsonProperty("folder")
    private String folder;

    @JsonProperty("sizeBytes")
    private Long sizeBytes;

    @JsonProperty("mediaCategory")
    private String mediaCategory;

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("fullPath")
    private String fullPath;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("contentType")
    private String contentType;
}