package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employment {
    private String sex;
    private String typeOfJob;
    private String ageGroups;
    private String period;
    private Double total;
}
