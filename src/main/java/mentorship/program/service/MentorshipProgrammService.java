package mentorship.program.service;

import mentorship.program.model.MentorshipProgramm;
import mentorship.program.model.User;
import mentorship.program.model.persistance.Level;

import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 9/22/2016.
 */
public interface MentorshipProgrammService {

    public MentorshipProgramm getMentorshipProgramm(Long id);

    public List<MentorshipProgramm> getMentorshipProgramm(String name);

    public void addMentorshipProgramm(MentorshipProgramm mentorshipProgramm);

    public void removeMentorshipProgramm(Long id);

    public void editMentorshipProgramm(MentorshipProgramm mentorshipProgramm);

    public List<MentorshipProgramm> findByName(String name);

    public List<MentorshipProgramm> findAll();

}
