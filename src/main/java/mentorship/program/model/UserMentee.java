package mentorship.program.model;

import lombok.AllArgsConstructor;
import mentorship.program.model.persistance.Level;
import mentorship.program.model.persistance.Skill;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by Oleksandr_Tertyshnyi on 10/3/2016.
 */
@Entity
@DiscriminatorValue(value = "UserMentee")
@AllArgsConstructor
public class UserMentee extends User{

    @OneToOne(cascade = CascadeType.ALL)
    @ManyToMany(mappedBy = "mentees")
//    @JoinTable(uniqueConstraints = {
//            @UniqueConstraint(columnNames = { "mentor_id", "mentee_id" })}, name = "mentor_mentees", joinColumns = @JoinColumn(name="mentee_id"), inverseJoinColumns = @JoinColumn(name = "mentor_id"))
    private UserMentor mentor;

    @ElementCollection(fetch = FetchType.EAGER)
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
        this.mentor = mentor;
        this.mentorshipEndDate = mentorshipEndDate;
    }

}
