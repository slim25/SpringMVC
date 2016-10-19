package mentorship.program.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import mentorship.program.model.persistance.Level;
import mentorship.program.model.persistance.Skill;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by Oleksandr_Tertyshnyi on 10/3/2016.
 */
@Entity
@DiscriminatorValue(value = "UserMentee")
@AllArgsConstructor
@Data
public class UserMentee extends User implements Serializable{

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH})
    @JsonBackReference
    @JoinTable(uniqueConstraints = {
            @UniqueConstraint(columnNames = { "mentor_id", "mentee_id" })}, name = "mentor_mentees", joinColumns = @JoinColumn(name="mentee_id"), inverseJoinColumns = @JoinColumn(name = "mentor_id"))
    private UserMentor userMentor;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "mentorshipEndDate" )
    @MapKeyColumn(name = "mentorship_name")
    @Column(name = "end_date")
    private Map<String, Date> mentorshipEndDate;

    public UserMentee(){}

    public UserMentee(String name, String lastName, String email, Level level, Skill primarySkill, UserMentor mentor, Map<String, Date> mentorshipEndDate) {
        this.setName(name);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setLevel(level);
        this.setPrimarySkill(primarySkill);
        this.userMentor = mentor;
        this.mentorshipEndDate = mentorshipEndDate;
    }

}
