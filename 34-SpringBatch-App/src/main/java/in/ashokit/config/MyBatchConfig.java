package in.ashokit.config;

import javax.sql.DataSource;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import in.ashokit.beans.PersonItemProcessor;
import in.ashokit.record.Person;

@Configuration
public class MyBatchConfig {
	
	@Bean
	public FlatFileItemReader<Person> reader(){
		
	 return	new FlatFileItemReaderBuilder<Person>()
		            .name("personItemReader")
		            .resource(new ClassPathResource("data.csv"))
		            .delimited()
		            .names("firstName", "lastName")
		            .targetType(Person.class)
		            .build();
		
	}
	
	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}
	
	@Bean
	public JdbcBatchItemWriter<Person> writer(DataSource ds){
		
		return new JdbcBatchItemWriterBuilder<Person>()
			  .sql("insert into people (first_name, last_name) values (:firstName, :lastName)")
			  .dataSource(ds)
			  .beanMapped()
			  .build();
	}

	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
	          FlatFileItemReader<Person> reader, PersonItemProcessor processor, JdbcBatchItemWriter<Person> writer) {
	  return new StepBuilder(jobRepository)
	    .<Person, Person>chunk(3)
	    .transactionManager(transactionManager)
	    .reader(reader)
	    .processor(processor) 
	    .writer(writer)
	    .build();
	}
	
	
	@Bean
	public Job job(JobRepository jobRepository, Step step1) {
		
		return new JobBuilder("personsJob", jobRepository)
					.start(step1)
					.build();
	}
	
}












