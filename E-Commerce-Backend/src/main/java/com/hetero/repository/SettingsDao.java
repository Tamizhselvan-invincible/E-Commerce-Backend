package com.hetero.repository;


import com.hetero.models.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsDao extends JpaRepository<Settings, Integer> {
    Settings findFirstByOrderByIdAsc();
}
