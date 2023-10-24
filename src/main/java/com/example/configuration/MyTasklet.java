package com.example.configuration;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class MyTasklet implements Tasklet {

    int compteur =5;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        if(compteur==0){
            return RepeatStatus.FINISHED;
        }
        compteur--;
        System.out.println("tasklet "+compteur);
        return RepeatStatus.CONTINUABLE;
    }
}
