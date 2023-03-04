package com.increff.ehr.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan("com.increff.ehr")
@PropertySources({ //
		@PropertySource(value = "file:./ehr.properties", ignoreResourceNotFound = true) //
})
public class SpringConfig {


}
