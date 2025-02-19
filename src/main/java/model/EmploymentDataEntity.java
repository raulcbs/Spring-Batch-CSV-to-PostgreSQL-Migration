package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employment_data")
public class EmploymentDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employment_data_id")
    private Long employmentDataId;
    
    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private GenderEntity gender;
    
    @ManyToOne
    @JoinColumn(name = "employment_type_id", nullable = false)
    private EmploymentTypeEntity employmentType;
    
    @ManyToOne
    @JoinColumn(name = "age_group_id", nullable = false)
    private AgeGroupEntity ageGroup;
    
    @ManyToOne
    @JoinColumn(name = "period_id", nullable = false)
    private PeriodEntity period;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double total;

}
