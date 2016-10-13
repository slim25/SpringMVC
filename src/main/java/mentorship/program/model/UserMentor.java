package mentorship.program.model;

import lombok.Data;
import mentorship.program.model.persistance.Level;
import mentorship.program.model.persistance.Skill;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Oleksandr_Tertyshnyi on 10/3/2016.
 */
@Entity
@DiscriminatorValue(value = "UserMentor")
@Data
public class UserMentor extends User{


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(uniqueConstraints = {
            @UniqueConstraint(columnNames = { "mentor_id", "mentee_id" })},name = "mentor_mentees", joinColumns = { @JoinColumn(name = "mentor_id") }, inverseJoinColumns = { @JoinColumn(name = "mentee_id") })
    private List<UserMentee> mentees = new ArrayList<>();

    public UserMentor(){}

    public UserMentor(String name, String lastName, String email, Level level, Skill primarySkill) {
        this.setName(name);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setLevel(level);
        this.setPrimarySkill(primarySkill);
    }


    public void addMentee(UserMentee mentee){
        mentees.add(mentee);
    }

}
