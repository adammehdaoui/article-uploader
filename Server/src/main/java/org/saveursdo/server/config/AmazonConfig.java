package org.saveursdo.server.config;

import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

@Configuration
public class AmazonConfig {

    @Bean
    public S3Client s3() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("rootkey.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String accessKeyId = (String) properties.get("key_ID");
        String secretAccessKey = (String) properties.get("secret_access_key");

        AwsCredentials awsCredentials = AwsBasicCredentials.create(
                accessKeyId,
                secretAccessKey
        );

        return S3Client.builder()
                .region(Region.EU_WEST_3)
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }

}
