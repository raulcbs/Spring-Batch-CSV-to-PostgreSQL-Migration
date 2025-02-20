package job.employment;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import model.*;
import dto.EmploymentDTO;

@Configuration
public class ProcessorEmployment {
    
    @Autowired
    private EntityManager entityManager;
    
    @Bean
    public ItemProcessor<EmploymentDTO, EmploymentDataEntity> employmentProcessor() {
        return dto -> {
            GenderEntity gender = findOrCreateGender(dto.getSex());            
            EmploymentTypeEntity employmentType = findOrCreateEmploymentType(dto.getTypeOfJob());            
            AgeGroupEntity ageGroup = findOrCreateAgeGroup(dto.getAgeGroups());            
            PeriodEntity period = findOrCreatePeriod(dto.getPeriod());
            
            EmploymentDataEntity employmentData = new EmploymentDataEntity();
            employmentData.setGender(gender);
            employmentData.setEmploymentType(employmentType);
            employmentData.setAgeGroup(ageGroup);
            employmentData.setPeriod(period);
            employmentData.setTotal(dto.getTotal());
            
            return employmentData;
        };
    }
    
    private GenderEntity findOrCreateGender(String description) {
        GenderEntity gender = entityManager
            .createQuery("SELECT g FROM GenderEntity g WHERE g.description = :description", GenderEntity.class)
            .setParameter("desc", description)
            .getResultStream()
            .findFirst()
            .orElse(null);
            
        if (gender == null) {
            gender = new GenderEntity();
            gender.setDescription(description);
            entityManager.persist(gender);
        }
        
        return gender;
    }
    
    private EmploymentTypeEntity findOrCreateEmploymentType(String description) {
        EmploymentTypeEntity type = entityManager
            .createQuery("SELECT t FROM EmploymentTypeEntity t WHERE t.description = :description", EmploymentTypeEntity.class)
            .setParameter("desc", description)
            .getResultStream()
            .findFirst()
            .orElse(null);
            
        if (type == null) {
            type = new EmploymentTypeEntity();
            type.setDescription(description);
            entityManager.persist(type);
        }
        
        return type;
    }
    
    private AgeGroupEntity findOrCreateAgeGroup(String description) {
        AgeGroupEntity ageGroup = entityManager
            .createQuery("SELECT a FROM AgeGroupEntity a WHERE a.description = :description", AgeGroupEntity.class)
            .setParameter("desc", description)
            .getResultStream()
            .findFirst()
            .orElse(null);
            
        if (ageGroup == null) {
            ageGroup = new AgeGroupEntity();
            ageGroup.setDescription(description);
            entityManager.persist(ageGroup);
        }
        
        return ageGroup;
    }
    
    private PeriodEntity findOrCreatePeriod(String periodStr) {
        String year = periodStr.substring(0, 4);
        String quarter = periodStr.substring(4);
        
        PeriodEntity period = entityManager
            .createQuery("SELECT p FROM PeriodEntity p WHERE p.year = :year AND p.quarter = :quarter", PeriodEntity.class)
            .setParameter("year", Integer.parseInt(year))
            .setParameter("quarter", quarter)
            .getResultStream()
            .findFirst()
            .orElse(null);
            
        if (period == null) {
            period = new PeriodEntity();
            period.setYear(Integer.parseInt(year));
            period.setQuarter(quarter);
            entityManager.persist(period);
        }
        
        return period;
    }
}