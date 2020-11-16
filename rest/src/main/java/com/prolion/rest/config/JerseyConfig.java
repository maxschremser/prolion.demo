package com.prolion.rest.config;

import com.prolion.rest.api.FileResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(FileResource.class);
    }
}
