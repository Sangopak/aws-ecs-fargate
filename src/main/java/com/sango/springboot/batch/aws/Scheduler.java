package com.sango.springboot.batch.aws;

import com.sango.springboot.batch.aws.job.IDummyJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    private IDummyJob dummyJob;

    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void run(){
        dummyJob.start();
    }
}
