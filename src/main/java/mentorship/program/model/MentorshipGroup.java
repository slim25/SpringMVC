package mentorship.program.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import mentorship.program.model.BaseEntity;
import mentorship.program.model.User;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 10/3/2016.
 */
@Entity
@Table(name= "mentorship_group")
@Data
public class MentorshipGroup extends BaseEntity implements Serializable {

    @NotBlank
    @Size(min = 1, max = 10)
    private String groupName;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH}, fetch = FetchType.EAGER )
    @JsonManagedReference
    @JoinTable(name = "mentorship_program_and_groups", joinColumns = { @JoinColumn( table="mentorship_group", name = "group_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(table="mentorship_program", name = "mentorship_program_id", referencedColumnName = "id") })
    private MentorshipProgram mentorshipProgram;

    @OneToMany( mappedBy = "mentorshipGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
//    @JoinTable(uniqueConstraints = name = "group_attending_users", joinColumns = { @JoinColumn(name = "group_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<User> attendendingUsers;

    public MentorshipGroup(String groupName, List<User> attendendingUsers){
        this.attendendingUsers = attendendingUsers;
        this.groupName = groupName;
    }

    public MentorshipGroup(){
    }

}
