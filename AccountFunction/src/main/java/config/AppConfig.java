package config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages = {
        "app",
        "entities",
        "model",
        "repository",
        "service"
})
@EnableMongoRepositories(basePackages = "repository")
public class AppConfig extends AbstractMongoClientConfiguration {

    @Bean
    public MongoClient mongoClient() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://account-credit:teste@cluster0-uhz0z.mongodb.net/test?retryWrites=true&w=majority");
        return mongoClient;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "account_credit");
    }

    @Override
    protected String getDatabaseName() {
        return "account_credit";
    }
}