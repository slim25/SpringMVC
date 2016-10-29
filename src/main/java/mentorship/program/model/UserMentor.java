package mentorship.program.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import mentorship.program.model.persistance.Level;
import mentorship.program.model.persistance.Skill;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Oleksandr_Tertyshnyi on 10/3/2016.
 */
@Entity
@DiscriminatorValue(value = "UserMentor")
@AllArgsConstructor
@Data
public class UserMentor extends User implements Serializable{


    @OneToMany(mappedBy = "userMentor", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH})
    @JsonManagedReference
//    @JoinTable(uniqueConstraints = {
//            @UniqueConstraint(columnNames = { "mentor_id", "mentee_id" })},name = "mentor_mentees", joinColumns = { @JoinColumn(name = "mentor_id") }, inverseJoinColumns = { @JoinColumn(name = "mentee_id") })
    private List<UserMentee> mentees = new ArrayList<>();

    public UserMentor(){}

    public UserMentor(String name, String lastName, String email, Level level, Skill primarySkill, String password) {
        this.setName(name);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setLevel(level);
        this.setPrimarySkill(primarySkill);
        this.setMentor(true);
        this.setPassword(password);
    }


    public void addMentee(UserMentee mentee){
        mentees.add(mentee);
    }

}
