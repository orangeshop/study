package study.springbatch.study1;


import lombok.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class StudyOne {
    @Bean
    public Job studyOneJob(
            JobRepository jobRepository,
            Step studyOneStep
    ) {
        return new JobBuilder("studyOneJob", jobRepository)
                .start(studyOneStep)
                .build();
    }

    @Bean
    public Step studyOneStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            FlatFileItemReader<TestEntry> reader,
            ItemWriter<TestEntry> studyOneItemWriter
    ) {
        return new StepBuilder("studyOneStep", jobRepository)
                .<TestEntry, TestEntry>chunk(10, transactionManager)
                .reader(reader)
                .writer(studyOneItemWriter)
                .build();
    }

    @Bean
    public FlatFileItemReader<TestEntry> reader() {
        return new FlatFileItemReaderBuilder<TestEntry>()
                .name("personItemReader")
                .resource(new ClassPathResource("sample-data.csv")) // resources 폴더의 csv 파일
                .linesToSkip(1)
                .delimited()
                .delimiter(",")
                .names("name", "age") // CSV의 헤더
                .targetType(TestEntry.class)
//                .strict(true)
                .build();
    }

    @Bean
    public ItemWriter<TestEntry> studyOneItemWriter() {
        // 람다식으로 ItemWriter 구현
        return items -> {
            System.out.println("----------- Writing Chunk -----------");
            for (TestEntry item : items) {
                System.out.println("Read item: " + item);
            }
            System.out.println("-------------------------------------\n");
        };
    }

    @AllArgsConstructor
    @ToString
    @NoArgsConstructor
    @Data
    public static class TestEntry {
        private String name;
        private int age;
    }
}
