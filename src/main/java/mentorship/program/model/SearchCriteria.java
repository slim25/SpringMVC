package mentorship.program.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by Oleksandr_Tertyshnyi on 9/25/2016.
 */
@Data
public class SearchCriteria {
    @NotBlank
    @Size(min = 1, max = 10)
    private String criteria;

    public String getCriteria() {
        return criteria;
    }
}
