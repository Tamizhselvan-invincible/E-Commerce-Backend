package com.hetero.repository;

import com.hetero.models.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerDao extends JpaRepository<Banner, Integer> {

}
