package com.config.appconfigurationservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import com.azure.data.appconfiguration.models.ConfigurationSetting; 
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class ConfigController {
    @Autowired  
    private com.config.appconfigurationservice.AppConfigService appConfigService; 

    public ConfigController() {
        
    }

    @GetMapping("/config")
    public Flux<ConfigurationSetting> getMessage(String keyFilter) {
        String label = "MyAppEnv"; 
        return appConfigService.getSettingsByKeyFilter(keyFilter, label);
    }
}