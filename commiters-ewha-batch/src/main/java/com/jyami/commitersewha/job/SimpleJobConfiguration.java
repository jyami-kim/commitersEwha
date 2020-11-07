package com.jyami.commitersewha.job;

import com.jyami.commitersewha.config.payload.RepositoryResponse;
import com.jyami.commitersewha.config.restTemplate.GithubRestTemplate;
import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.job.reader.GitRESTReaderConfig;
import com.jyami.commitersewha.job.reader.GitRESTRepositoryReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jyami on 2020/11/03
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class SimpleJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private final ItemReader<List<RepositoryResponse>> restRepositoryReader;

    private static final int chunkSize = 10;

    @Bean
    @JobScope
    public Job simpleJob() {
        return jobBuilderFactory.get("newUserInfoSaveJob")
                .start(getUserTokenStep())
                .next(simpleStep2())
                .build();
    }

    @Bean
    @StepScope
    public Step getUserTokenStep() {
        return stepBuilderFactory.get("jdbcGetUserTokenStep")
                .<List<RepositoryResponse>, RepositoryResponse>chunk(chunkSize)
                .reader(restRepositoryReader)
                .writer(repositoryBatchItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public Step simpleStep2() {
        return stepBuilderFactory.get("simpleStep2")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is Step2");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean // beanMapped()을 사용할때는 필수
    public JdbcBatchItemWriter<RepositoryResponse> repositoryBatchItemWriter() {
        return new JdbcBatchItemWriterBuilder<RepositoryResponse>()
                .dataSource(dataSource)
                .sql("insert into pay2(amount, tx_name, tx_date_time) values (:amount, :txName, :txDateTime)")
                .beanMapped()
                .build();
    }

//    @Bean
//    public JdbcPagingItemReader<User> jdbcPagingItemReader() throws Exception {
//        Map<String, Object> parameterValues = new HashMap<>();
//        parameterValues.put("amount", 2000);
//
//        return new JdbcPagingItemReaderBuilder<User>()
//                .pageSize(chunkSize)
//                .fetchSize(chunkSize)
//                .dataSource(dataSource)
//                .rowMapper(new BeanPropertyRowMapper<>(Pay.class))
//                .queryProvider(createQueryProvider())
//                .parameterValues(parameterValues)
//                .name("jdbcPagingItemReader")
//                .build();
//    }

}
