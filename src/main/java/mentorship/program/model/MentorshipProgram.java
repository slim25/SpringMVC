package mentorship.program.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by Oleksandr_Tertyshnyi on 9/21/2016.
 */

@Entity
@Table(name= "mentorship_program")
@Data
public class MentorshipProgram extends BaseEntity implements Serializable{

    @NotBlank
    @Size(min = 1, max = 20)
    private String name;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateOfStart;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateOfEnd;

    @NotBlank
    @Size(min = 1, max = 15)
    private String city;

    private Long mentorWeek;

    @OneToMany( mappedBy = "mentorshipProgram",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonBackReference
//    @JoinTable(name = "mentorship_program_and_groups", joinColumns = { @JoinColumn(name = "mentorship_program_id") }, inverseJoinColumns = { @JoinColumn(name = "group_id") })
    private List<MentorshipGroup> programGroups;

    public MentorshipProgram(String name, Date dateOfStart, Date dateOfEnd, String city, List<MentorshipGroup> programGroups){
        this.name = name;
        this.dateOfEnd = dateOfStart;
        this.dateOfStart = dateOfEnd;
        this.city = city;
        this.programGroups = programGroups;
    }
    @PrePersist
    public void calculateMentorWeek(){
        this.mentorWeek = ChronoUnit.WEEKS.between(dateOfEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),dateOfStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public MentorshipProgram(){
    }



}
