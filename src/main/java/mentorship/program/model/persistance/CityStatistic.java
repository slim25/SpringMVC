package mentorship.program.model.persistance;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Oleksandr_Tertyshnyi on 10/3/2016.
 */
@Data
public class CityStatistic implements Serializable{

    private String city;
    private Long amountOfPrograms;
    private Long peopleInvolved;

    public CityStatistic(String city, Long amountOfPrograms, Long peopleInvolved){
        this.city = city;
        this.amountOfPrograms = amountOfPrograms;
        this.peopleInvolved = peopleInvolved;
    }
}
