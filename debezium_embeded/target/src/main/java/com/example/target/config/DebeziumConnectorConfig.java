package com.example.target.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * This class provides the configurations required to set up a Debezium connector for the Student Table.
 *
 * @author shiqbal
 */
@Configuration
public class DebeziumConnectorConfig {

    private String STUDENT_TABLE_NAME = "cdc_schema_c.TBL_STUDENT";

    /**
     * Student database connector.
     *
     * @return Configuration.
     */
    @Bean
    public io.debezium.config.Configuration studentConnector() {
        return io.debezium.config.Configuration.create()
                .with("name", "engine")
                .with("connector.class", "io.debezium.connector.sqlserver.SqlServerConnector")
                .with("database.hostname", "localhost")
                .with("database.port", "1433")
                .with("database.user", "cdc_user_c")
                .with("database.password", "cdc@123")
                .with("database.dbname", "CDC_POC3")
                .with("database.names", "CDC_POC3")
                .with("database.include.list", "CDC_POC3")
                .with("include.schema.changes", "false")
                .with("database.server.name", "localhost-CDC_POC3")
                .with("table.whitelist", STUDENT_TABLE_NAME)
                .with("database.server.id", "9")
                .with("topic.prefix", "my-app-connector")
                .with("schema.history.internal", "io.debezium.storage.file.history.FileSchemaHistory")
                .with("schema.history.internal.file.filename","C://project//changedatacapture/student-history.dat")
                .with("database.encrypt","false")
                .with("offset.storage",  "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", "C://project//changedatacapture/student-offset.dat")
                .with("offset.flush.interval.ms", 60000)
               .build();
    }

}
