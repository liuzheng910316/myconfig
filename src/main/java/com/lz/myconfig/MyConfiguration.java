package com.lz.myconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MyConfigProperties.class)
@ConditionalOnClass(MyConfigService.class)
@ConditionalOnProperty(prefix = "myconfig",value="enabled",matchIfMissing=true)
public class MyConfiguration {
	@Autowired
	MyConfigProperties myConfigProperties;
	
	@Bean
	@ConditionalOnMissingBean(MyConfigService.class)
	public MyConfigService myConfigService(){
		MyConfigService myConfigService = new MyConfigService();
		myConfigService.setDetail(myConfigProperties.getDetail());
		return myConfigService;
	}
}
