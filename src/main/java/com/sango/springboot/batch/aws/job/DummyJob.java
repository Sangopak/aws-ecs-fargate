package com.sango.springboot.batch.aws.job;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.sango.springboot.batch.aws.service.S3Service;

@Slf4j
@Component
public class DummyJob implements IDummyJob {

	@Autowired
	private S3Service s3Service;

	@Value("${aws.bucket-name}")
	private String bucketName;

	private String fileName = "SOMETEXTFILE.txt";

	@Override
	public void start() {
		int returnCode = run();
		log.info("Dummyjob ran with return code : {}", returnCode);
	}

	private int run() {
		int returnCode = 8;
		log.info("Dummy Job started");
		try {
			List<Bucket> bucketList = s3Service.getAllS3BucketNames();
			Optional<Bucket> optionalBucket = bucketList.stream()
					.filter(bucket -> bucket.getName().equalsIgnoreCase(bucketName)).findFirst();
			if (optionalBucket.isPresent()) {
				ObjectListing objectsFromS3Bucket = s3Service.getObjectsFromS3Bucket(bucketName);

				if (objectsFromS3Bucket.getObjectSummaries().isEmpty()) {
					String keyName = fileName;
					File file = Paths.get(getClass().getClassLoader().getResource(fileName).toURI()).toFile();
					log.info("Bucket is empty uploading file is S3 with file name: {} and key name: {} ", fileName, keyName);
					s3Service.putObjectToS3Bucket(bucketName, keyName, file);
					log.info("File uploaded in S3");
				} else {
					String keyName = System.currentTimeMillis() + "." + fileName;
					File file = Paths.get(getClass().getClassLoader().getResource(fileName).toURI()).toFile();
					log.info("Trying to upload file is S3 with file name: {} and key name: {} ", fileName, keyName);
					s3Service.putObjectToS3Bucket(bucketName, keyName, file);
					log.info("File uploaded in S3");
				}

				objectsFromS3Bucket.getObjectSummaries()
						.forEach(object -> log.info("Object from S3: {} ", object.getKey()));

				returnCode = 0;
			} else {
				log.error("S3 Bucket is not present with bucket name: {} ", bucketName);
				returnCode = 1;
			}
		} catch (Exception e) {
			returnCode = 10;
			log.error("Exception has occured with the below error message : {} ", e.getMessage());
			e.printStackTrace();
		}
		log.info("Dummy Job ended");
		return returnCode;
	}
}
