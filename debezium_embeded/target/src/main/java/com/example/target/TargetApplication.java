package com.example.target;

import cdc.autoconfigure.CdcAutoConfiguration;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication(scanBasePackages = {"cdc"})
@AutoConfigureBefore(CdcAutoConfiguration.class)
public class TargetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TargetApplication.class, args);
	}

//	@Bean
//	public Consumer<SourceRecord> mySourceRecordConsumer() {
//		return sourceRecord -> {
//			System.out.println(" My handler: " + sourceRecord.toString());
//		};
//	}

}
