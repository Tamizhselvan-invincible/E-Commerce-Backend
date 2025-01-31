package com.hetero.repository;

import com.hetero.models.Images;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;


@Repository
public interface ImageDao extends JpaRepository<Images, String> {
    Page<Images> findByMediaCategoryOrderByCreatedAtDesc(String mediaCategory, Pageable pageable);
    Page<Images> findByMediaCategoryAndCreatedAtLessThanOrderByCreatedAtDesc(
            String mediaCategory, LocalDateTime lastFetchedDate, Pageable pageable);
}

