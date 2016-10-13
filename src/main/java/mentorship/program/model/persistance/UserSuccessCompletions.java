package mentorship.program.model.persistance;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Oleksandr_Tertyshnyi on 10/12/2016.
 */
@Data
public class UserSuccessCompletions implements Serializable {

    private Long userId;
    private String userName;
    private Long successPercentage;

    public UserSuccessCompletions(Long userId, String userName, Long successPercentage){
        this.userId = userId;
        this.userName = userName;
        this.successPercentage = successPercentage;
    }

}
