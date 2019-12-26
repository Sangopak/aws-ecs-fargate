package com.sango.springboot.batch.aws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class S3Service {
	@Autowired
	private AmazonS3 amazonS3;

	public void getAllS3BucketNames() {
		amazonS3.listBuckets().forEach(bucket -> log.info("Bucket Name: {} and Owner Name : {} from S3 ",
				bucket.getName(), bucket.getOwner()));
	}

}
