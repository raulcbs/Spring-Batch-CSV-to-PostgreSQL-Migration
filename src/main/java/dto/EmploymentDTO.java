package dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmploymentDTO {
    private String sex;
    private String typeOfJob;
    private String ageGroups;
    private String period;
    private Double total;
}
