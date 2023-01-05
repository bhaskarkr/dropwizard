package com.example.projects;

import com.example.projects.core.BaseExceptionMapper;
import com.example.projects.storage.StoredBase;
import com.google.inject.Stage;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.appform.dropwizard.sharding.DBShardingBundle;
import io.appform.dropwizard.sharding.config.ShardedHibernateFactory;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.val;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class BaseProjectApplication extends Application<BaseProjectConfiguration> {

    private DBShardingBundle<BaseProjectConfiguration> dbShardingBundle;
    private GuiceBundle<BaseProjectConfiguration> guiceBundle;

    public static void main(final String[] args) throws Exception {
        new BaseProjectApplication().run(args);
    }

    @Override
    public String getName() {
        return "Base Project";
    }

    @Override
    public void initialize(final Bootstrap<BaseProjectConfiguration> bootstrap) {
        this.dbShardingBundle = new DBShardingBundle<BaseProjectConfiguration>(StoredBase.class) {
            @Override
            protected ShardedHibernateFactory getConfig(BaseProjectConfiguration baseProjectConfiguration) {
                return baseProjectConfiguration.getShards();
            }
        };
        bootstrap.addBundle(dbShardingBundle);
        bootstrap.addBundle(new SwaggerBundle<BaseProjectConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(BaseProjectConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
        this.guiceBundle = GuiceBundle.<BaseProjectConfiguration>builder()
                .enableAutoConfig(getClass().getPackage().getName())
                .modules(new BaseModule(this.dbShardingBundle))
                .build(Stage.PRODUCTION);
        bootstrap.addBundle(guiceBundle);

    }

    @Override
    public void run(final BaseProjectConfiguration configuration,
                    final Environment environment) {
        val injector = guiceBundle.getInjector();
        environment.jersey().register(injector.getInstance(BaseExceptionMapper.class));
    }

}
