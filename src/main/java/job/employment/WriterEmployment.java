package job.employment;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import model.EmploymentDataEntity;

@RequiredArgsConstructor
public class WriterEmployment {

    final private EntityManager entityManager;

    @Bean
    public ItemWriter<EmploymentDataEntity> employmentWriter() {
        return items -> {
            items.forEach(item -> {
                entityManager.persist(item);
            });
            entityManager.flush();
            entityManager.clear();
        };
    }
}
