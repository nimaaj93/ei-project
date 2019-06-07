package com.ashkan.ie.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

/**
 * Created by K550 VX on 6/6/2019.
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(packages("com.ashkan.ie"));
    }

}
