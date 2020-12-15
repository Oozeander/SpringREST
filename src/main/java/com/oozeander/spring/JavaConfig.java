package com.oozeander.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ PersistenceConfig.class, RestConfig.class })
public class JavaConfig {
}