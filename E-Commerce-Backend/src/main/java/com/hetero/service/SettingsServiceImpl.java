package com.hetero.service;

import com.hetero.models.Settings;
import com.hetero.repository.SettingsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.Field;
import java.util.Map;

@Service
public class SettingsServiceImpl implements SettingsService {


    @Autowired
    private SettingsDao settingsDao;

    @Transactional
    @Override
    public Settings registerSettings (Settings setting) {
        settingsDao.deleteAll();
        return settingsDao.save(setting);
    }

    @Transactional(readOnly = true)
    @Override
    public Settings getSettings () {

        Settings settings = settingsDao.findFirstByOrderByIdAsc();
        if (settings == null) {
            settings = new Settings();
            settings.setAppLogo("");
            settings.setAppName("");
            settings.setTaxRate(0.0);
            settings.setShippingCost(0.0);
            settingsDao.save(settings);
        }
        return settings;
    }

    @Override
    @Transactional
    public Settings updateSettingDetails (Settings updatedSetting) {
        Settings existingSettings = settingsDao.findFirstByOrderByIdAsc();

           if(existingSettings == null) {
               return settingsDao.save(updatedSetting);
           }

        existingSettings.setTaxRate(updatedSetting.getTaxRate());
        existingSettings.setShippingCost(updatedSetting.getShippingCost());
        existingSettings.setFreeShippingThreshold(updatedSetting.getFreeShippingThreshold());
        existingSettings.setAppName(updatedSetting.getAppName());
        existingSettings.setAppLogo(updatedSetting.getAppLogo());

        return settingsDao.save(existingSettings);
    }

    @Override
    @Transactional
    public Settings updateSingleField (Map<String, Object> updates) {
        Settings existingSettings = settingsDao.findFirstByOrderByIdAsc();
        if(existingSettings == null) {
           existingSettings = new Settings();
            existingSettings.setAppLogo("");
            existingSettings.setAppName("");
            existingSettings.setTaxRate(0.0);
            existingSettings.setShippingCost(0.0);
        }

        Settings finalExistingSettings = existingSettings;
        updates.forEach((key, value) -> {
            try {
                Field field = Settings.class.getDeclaredField(key);
                field.setAccessible(true);
                field.set(finalExistingSettings, value);
            } catch (Exception e) {
                throw new RuntimeException("Invalid field: " + key);
            }
        });

        return settingsDao.save(existingSettings);
    }
}
