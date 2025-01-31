package com.hetero.controller;


import com.hetero.models.Settings;
import com.hetero.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @PostMapping
    public ResponseEntity<Settings> registerSettings(@RequestBody Settings setting) {
        return ResponseEntity.ok(settingsService.registerSettings(setting));
    }

    @GetMapping
    public ResponseEntity<Settings> getSettings() {
        return ResponseEntity.ok(settingsService.getSettings());
    }

    @PutMapping
    public ResponseEntity<Settings> updateSettings(@RequestBody Settings updatedSetting) {
        return ResponseEntity.ok(settingsService.updateSettingDetails(updatedSetting));
    }

    @PatchMapping
    public ResponseEntity<Settings> updateSingleField(@RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(settingsService.updateSingleField(updates));
    }
}
