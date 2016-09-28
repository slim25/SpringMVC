package mentorship.program.model;

import lombok.Data;
import mentorship.program.model.persistance.Level;
import mentorship.program.model.persistance.Skill;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Oleksandr_Tertyshnyi on 9/21/2016.
 */

@Entity
@Table(name= "mentorship_programm")
@Data
public class MentorshipProgramm extends BaseEntity{

    @NotBlank
    @Size(min = 1, max = 10)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private List<User> participants = new ArrayList<>();

}
