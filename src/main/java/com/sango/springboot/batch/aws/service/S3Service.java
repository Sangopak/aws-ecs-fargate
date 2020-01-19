package com.sango.springboot.batch.aws.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class S3Service {

	@Autowired
	private AmazonS3 amazonS3;

	public List<Bucket> getAllS3BucketNames() {
		List<Bucket> bucketList = amazonS3.listBuckets();
		bucketList.forEach(bucket -> log.info("Bucket Name: {} and Owner Name : {} from S3 ", bucket.getName(),
				bucket.getOwner()));
		return bucketList;
	}

	public ObjectListing getObjectsFromS3Bucket(String bucketName) {
		ObjectListing listObjects = amazonS3.listObjects(bucketName);
		return listObjects;
	}
	
	public void putObjectToS3Bucket(String bucketName, String keyName, File file) {
		amazonS3.putObject(new PutObjectRequest(bucketName, keyName, file));
	}
	
	public void putObjectToS3Bucket(String bucketName, String keyName, String content) {
		amazonS3.putObject(bucketName, keyName, content);
	}

	public S3Object getFileFromS3Bucket(String bucketName, String keyName) {
		return amazonS3.getObject(bucketName, keyName);
	}

}
