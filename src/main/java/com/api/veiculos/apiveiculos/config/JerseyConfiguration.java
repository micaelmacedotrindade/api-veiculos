package com.api.veiculos.apiveiculos.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.api.veiculos.apiveiculos.resources.VeiculosResource;

import jakarta.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/boot-jersey")
public class JerseyConfiguration extends ResourceConfig {
 public JerseyConfiguration() {
  register(VeiculosResource.class);
 }
}
