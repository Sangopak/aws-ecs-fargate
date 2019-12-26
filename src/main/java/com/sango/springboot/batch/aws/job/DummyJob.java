package com.sango.springboot.batch.aws.job;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sango.springboot.batch.aws.service.S3Service;

@Slf4j
@Component
public class DummyJob implements IDummyJob {
	
	@Autowired
	private S3Service s3Service;

    @Override
    public void start() {
     run();
    }

    private int run() {
        log.info("Dummy Job started");
        s3Service.getAllS3BucketNames();
        log.info("Dummy Job ended");
        return 0;
    }
}
