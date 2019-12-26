package com.sango.springboot.batch.aws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfigurer {

	private String accessKey = "AKIA3ND4EXRPNMWI5UIL";
	private String secretKey = "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";

	@Bean
	public AmazonS3 createS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
		return AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).build();
	}

}
