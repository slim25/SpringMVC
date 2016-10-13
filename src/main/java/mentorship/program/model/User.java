package mentorship.program.model;

import lombok.Data;
import mentorship.program.model.persistance.Level;
import mentorship.program.model.persistance.Skill;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Oleksandr_Tertyshnyi on 9/21/2016.
 */

@Entity
@Table(name= "user")
@Data
public abstract class User extends BaseEntity {

    @NotBlank
    @Size(min = 1, max = 10)
    private String name;
    @NotBlank
    @Size(min = 1, max = 15)
    private String lastName;
    @NotBlank
    @Size(min = 1, max = 30)
    @Pattern(regexp = "^\\S+@\\S+$")
    private String email;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthday;
    @ManyToOne( fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
    @JoinTable(name = "group_attending_users", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "group_id") })
    private MentorshipGroup mentorshipGroup;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Level level;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Skill primarySkill;

    @NotNull
    private boolean isMentor;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", mentorshipGroup=" + mentorshipGroup +
                ", level=" + level +
                ", primarySkill=" + primarySkill +
                ", isMentor=" + isMentor +
                '}';
    }
}
