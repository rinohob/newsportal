package com.newsportal.core.configs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="updates on weather",description="complete details on the Weather Updates")
public @interface WeatherUpdatesConfig {
    @AttributeDefinition(name = "morning Update",description = "details based on the morning weather")
    String morningUpdate() default "https://jsonplaceholder.typicode.com/todos";



}
