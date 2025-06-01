package com.jmortegaf.bms.repositories;

import com.jmortegaf.bms.models.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
}
