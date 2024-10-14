package com.config.appconfigurationservice;

import com.azure.data.appconfiguration.ConfigurationAsyncClient;  
import com.azure.data.appconfiguration.ConfigurationClientBuilder;  
import com.azure.data.appconfiguration.models.ConfigurationSetting;  
import com.azure.data.appconfiguration.models.SettingSelector;
import org.springframework.beans.factory.annotation.Value;  
import org.springframework.stereotype.Service;  
import reactor.core.publisher.Flux;  

@Service  
public class AppConfigService {  
  
    private final ConfigurationAsyncClient client;  
  
    public AppConfigService(@Value("${spring.cloud.azure.appconfiguration.stores[0].connection-string}") String connectionString) {  
        this.client = new ConfigurationClientBuilder()  
                .connectionString(connectionString)  
                .buildAsyncClient();  
    }  
  
    public Flux<ConfigurationSetting> getSettingsByKeyFilter(String keyFilter, String label) {  
        SettingSelector selector = new SettingSelector().setKeyFilter(keyFilter).setLabelFilter(label);
        return client.listConfigurationSettings(selector);  
    }  
} 