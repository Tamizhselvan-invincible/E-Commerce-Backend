package com.hetero.repository;

import com.hetero.models.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ImageDao extends JpaRepository<Images, Long> {
    List<Images> findByMediaCategoryOrderByCreatedAtDesc(String mediaCategory, int limit);
    List<Images> findByMediaCategoryAndCreatedAtBeforeOrderByCreatedAtDesc(String mediaCategory, LocalDateTime createdAt, int limit);
}
