package study.springbatch.study2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.file.transform.RegexLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class StudyTwo {

    @Bean
    public Job studyTwoJob(
            JobRepository jobRepository,
            Step studyTwoStep
    ) {
        return new JobBuilder("studyTwoJob", jobRepository)
                .start(studyTwoStep)
                .build();
    }

    @Bean
    public Step studyTwoStep(
            JobRepository jobRepository,
            PlatformTransactionManager platformTransactionManager,
            FlatFileItemReader<Entity> studyTwoReader,
            ItemWriter<Entity> studyTwoItemWriter
    ) {
        return new StepBuilder("studyTwoStep", jobRepository)
                .<Entity, Entity>chunk(10, platformTransactionManager)
                .reader(studyTwoReader)
                .writer(studyTwoItemWriter)
                .build();
    }

    @Bean
    public FlatFileItemReader<Entity> studyTwoReader() {

        return new FlatFileItemReaderBuilder<Entity>()
                .name("studyTwoReader")
                .resource(new ClassPathResource("test.csv"))
                .encoding("UTF-8") // 한글 인코딩 지정
                .linesToSkip(1) // 첫 줄 헤더 건너뛰기
                // 👇 쉼표로 구분된 파일임을 선언
                .delimited()
                // 👇 CSV 헤더와 DTO 필드명을 순서대로 매핑
                .names("accountType", "institutionCode", "institutionName", "branchName", "postalCode", "treasuryAgency", "isUsed")
                .targetType(Entity.class)
                .build();
    }

    @Bean
    public ItemWriter<Entity> studyTwoItemWriter() {
        // 람다식으로 ItemWriter 구현
        return items -> {
            System.out.println("----------- Writing Chunk -----------");
            for (Entity item : items) {
                System.out.println("Read item: " + item);
            }
            System.out.println("-------------------------------------\n");
        };
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Entity {
        private String accountType; // 국가결산회계

        private String institutionCode; // 금융기관코드

        private String institutionName; // 금융기관명

        private String branchName; // 지점명

        private String postalCode; // 우편번호

        private String treasuryAgency; // 의미를 알 수 없는 플래그

        private String isUsed; // 국고수납대리 사용여부 (Y/N)
    }
}
