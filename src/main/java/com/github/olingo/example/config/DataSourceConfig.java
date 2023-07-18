package com.github.olingo.example.config;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile(value = {"cloud"})
public class DataSourceConfig {

    static final String JDBC_DRIVER = "com.sap.db.jdbc.Driver";
    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);


    @Bean
    public DataSource cfDataSource() {
        try {
            JSONObject vcap_services = new JSONObject(System.getenv("VCAP_SERVICES"));

            Class.forName(JDBC_DRIVER);
            DataSource ds = DataSourceBuilder.create()
                    .url("url")
                    .driverClassName(JDBC_DRIVER)
                    .password("password")
                    .username("user")
                    .build();
            log.info("DataSourceConfig : {}", ds.toString());
            return ds;
        } catch (Exception e) {
            log.info("DataSourceConfig Bean Failed");
            log.error("Failed", e);
        }
        return null;
    }
}
