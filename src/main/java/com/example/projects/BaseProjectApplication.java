package com.example.projects;

import com.example.projects.core.BaseExceptionMapper;
import com.google.inject.Injector;
import com.google.inject.Stage;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class BaseProjectApplication extends Application<BaseProjectConfiguration> {

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
        bootstrap.addBundle(new SwaggerBundle<BaseProjectConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(BaseProjectConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
        this.guiceBundle = GuiceBundle.<BaseProjectConfiguration>builder()
                .enableAutoConfig(getClass().getPackage().getName())
                .modules(new BaseModule())
                .build(Stage.PRODUCTION);
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(final BaseProjectConfiguration configuration,
                    final Environment environment) {
        Injector injector = guiceBundle.getInjector();
        environment.jersey().register(injector.getInstance(BaseExceptionMapper.class));
    }
}
