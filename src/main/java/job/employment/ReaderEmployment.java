package job.employment;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import dto.EmploymentDTO;

@Configuration
@EnableBatchProcessing
public class ReaderEmployment {
    
    @Bean
    public FlatFileItemReader<EmploymentDTO> reader() {
        return new FlatFileItemReaderBuilder<EmploymentDTO>()
                .name("employmentReader")
                .resource(new ClassPathResource("data.csv"))
                .delimited()
                .delimiter(";")
                .names("sex", "typeOfJob", "ageGroups", "period", "total")
                .linesToSkip(1)
                .targetType(EmploymentDTO.class)
                .build();
    }
}
