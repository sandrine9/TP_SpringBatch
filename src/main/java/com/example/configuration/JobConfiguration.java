package com.example.configuration;

import com.example.dto.ProductInDto;
import com.example.dto.ProductOutDto;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    Tasklet tasklet;
    StepBuilderFactory stepBuilderFactory;
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    ItemProcessor<ProductInDto,ProductOutDto> processor;
    @Autowired
    ItemReader<ProductInDto> reader;
    @Autowired
    ItemWriter<ProductOutDto> writer;

    public JobConfiguration(Tasklet tasklet, StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory) {
        this.tasklet = tasklet;
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobBuilderFactory = jobBuilderFactory;
    }
    //Injection de dépendance par constructeur comme ci-dessus
    //ou par attribut comme ci-dessous
    //@Autowired
    //Tasklet tasklet;
    //@Autowired
    //StepBuilderFactory stepBuilderFactory;



    public Step stepExecuteTasklet(){
        return stepBuilderFactory
                .get("execute tasket")  //nom du step
                .tasklet(tasklet)
                .build();
    }
    //public Step stepExecuteTasklet(StepBuilderFactory stepBuilderFactory){....
    //StepBuilderFactory en paramètre instancié par Spring (au démarrage)
    //mais comme on s'en sert à plusieurs endroits, plus logique de faire l'InjDépendance par attibut ou constructeur

    public Step stepExecuteFileStep(){
        return stepBuilderFactory
                .get("fileStep")
                .<ProductInDto, ProductOutDto>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
    // ce step traite les IN et OUT par bloc de 10 :
    //     lit 10
    //     applique le processor, transcode
    //     écrit la liste de 10 en out

    @Bean
    public Job JobCompteur(){
        return jobBuilderFactory
                .get("jobCompteur")
                .start(stepExecuteTasklet())
                .next(stepExecuteFileStep())
                .build();
    }

}
