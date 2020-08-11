package com.example.projects;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.example.projects.repository.BaseRepository;
import com.example.projects.repository.BookingRepository;
import com.example.projects.repository.Impl.BaseRepositoryImpl;
import com.example.projects.repository.Impl.ParkingLotRepositoryImpl;
import com.example.projects.repository.ParkingLotRepository;
import com.example.projects.service.BaseService;
import com.example.projects.service.BookingService;
import com.example.projects.service.Impl.BaseServiceImpl;
import com.example.projects.service.Impl.BookingServiceImpl;
import com.example.projects.service.Impl.ParkingServiceImpl;
import com.example.projects.service.ParkingService;
import com.example.projects.storage.StoredBase;
import com.example.projects.storage.StoredBooking;
import com.example.projects.storage.StoredParkingLot;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jersey.DropwizardResourceConfig;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.lifecycle.setup.LifecycleEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.sharding.DBShardingBundle;
import io.dropwizard.sharding.config.ShardedHibernateFactory;
import io.dropwizard.sharding.dao.RelationalDao;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.Map;

import static org.mockito.Mockito.*;

public abstract class BaseTest {

    protected static class DBConfig extends Configuration {
        protected ShardedHibernateFactory shards = new ShardedHibernateFactory();
    }

    private static final DBConfig dbConfig = new DBConfig();
    private static final JerseyEnvironment jerseyEnvironment = mock(JerseyEnvironment.class);
    private static final Environment environment = mock(Environment.class);
    private static final HealthCheckRegistry healthChecks = mock(HealthCheckRegistry.class);
    private static final LifecycleEnvironment lifecycleEnvironment = mock(LifecycleEnvironment.class);
    private static final Bootstrap<?> bootstrap = mock(Bootstrap.class);
    //SERVICES
    protected static BaseService baseService = mock(BaseServiceImpl.class);
    protected static ParkingService parkingService = mock(ParkingServiceImpl.class);
    protected static BookingService bookingService = mock(BookingServiceImpl.class);
    //REPOSITORIES
    protected static BaseRepository baseRepository;
    protected static ParkingLotRepository parkingLotRepository;
    protected static BookingRepository bookingRepository;
    protected static DBShardingBundle<DBConfig> shardingBundle = new DBShardingBundle<DBConfig>(
            StoredBase.class,
            StoredParkingLot.class,
            StoredBooking.class
    ) {
        @Override
        protected ShardedHibernateFactory getConfig(DBConfig config) {
            return dbConfig.shards;
        }
    };

    protected static DataSourceFactory createConfig(String dbName) {
        Map<String, String> properties = Maps.newHashMap();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");

        DataSourceFactory datasource = new DataSourceFactory();
        datasource.setDriverClass("org.h2.Driver");
        datasource.setUrl("jdbc:h2:mem:" + dbName);
        datasource.setValidationQuery("select 1");
        datasource.setProperties(properties);
        return datasource;
    }

    @BeforeClass
    public static void setup(){
        dbConfig.shards.setShards(ImmutableList.of(createConfig("base"), createConfig("base")));
        when(jerseyEnvironment.getResourceConfig()).thenReturn(new DropwizardResourceConfig());
        when(environment.jersey()).thenReturn(jerseyEnvironment);
        when(environment.lifecycle()).thenReturn(lifecycleEnvironment);
        when(environment.healthChecks()).thenReturn(healthChecks);
        shardingBundle.initialize(bootstrap);
        shardingBundle.initBundles(bootstrap);
        shardingBundle.runBundles(dbConfig, environment);
        shardingBundle.run(dbConfig, environment);
        //DAOs
        RelationalDao<StoredBase> storedBaseRelationalDao = DBShardingBundle.createRelatedObjectDao(shardingBundle, StoredBase.class);
        RelationalDao<StoredParkingLot> storedParkingLotRelationalDao = DBShardingBundle.createRelatedObjectDao(shardingBundle, StoredParkingLot.class);
        RelationalDao<StoredBooking> storedBookingRelationalDao = DBShardingBundle.createRelatedObjectDao(shardingBundle, StoredBooking.class);
        //ALL REPOSITORIES
        baseRepository = new BaseRepositoryImpl(storedBaseRelationalDao);
        parkingLotRepository = new ParkingLotRepositoryImpl(storedParkingLotRelationalDao);

    }

    @AfterClass
    public static void tearDown() {
        shardingBundle.getSessionFactories().forEach(SessionFactory::close);
    }

}
