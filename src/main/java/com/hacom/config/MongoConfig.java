package com.hacom.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.convert.NoOpDbRefResolver;

import java.util.Arrays;

@Configuration
public class MongoConfig {

    private final AppConfig appConfig;

    public MongoConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Bean
    public MongoClient mongoClient() {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromCodecs(new OffsetDateTimeCodec()),
                MongoClientSettings.getDefaultCodecRegistry()
        );

        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(codecRegistry)
                .build();

        return MongoClients.create(settings);
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(MongoClient mongoClient) {
        return new ReactiveMongoTemplate(
                new SimpleReactiveMongoDatabaseFactory(mongoClient, appConfig.getMongodbDatabase())
        );
    }

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new OffsetDateTimeReadConverter(),
                new OffsetDateTimeWriteConverter()
        ));
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoCustomConversions conversions) {
        MappingMongoConverter converter = new MappingMongoConverter(
                NoOpDbRefResolver.INSTANCE, new MongoMappingContext());
        converter.setCustomConversions(conversions);
        converter.afterPropertiesSet();
        return converter;
    }
}
