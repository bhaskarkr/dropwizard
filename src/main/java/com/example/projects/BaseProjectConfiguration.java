package com.example.projects;

import com.example.projects.model.BillingConfig;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.dropwizard.Configuration;
import io.dropwizard.sharding.config.ShardedHibernateFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;



@Data
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseProjectConfiguration extends Configuration {

    private ShardedHibernateFactory shards;
    private SwaggerBundleConfiguration swagger;
    private BillingConfig billingConfig;
}
