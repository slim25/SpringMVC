package mentorship.program.service;

import mentorship.program.model.MentorshipGroup;
import mentorship.program.model.MentorshipProgram;
import mentorship.program.model.User;
import mentorship.program.model.persistance.CityStatistic;
import mentorship.program.model.persistance.Level;
import mentorship.program.model.persistance.UserSuccessCompletions;

import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 9/22/2016.
 */
public interface MentorshipProgrammService {

    public MentorshipProgram getMentorshipProgramm(Long id);

    public List<MentorshipProgram> getMentorshipProgramm(String name);

    public void addMentorshipProgramm(MentorshipProgram mentorshipProgramm);

    public void removeMentorshipProgramm(Long id);

    public void editMentorshipProgramm(MentorshipProgram mentorshipProgramm);

    public List<MentorshipProgram> findByName(String name);

    public List<MentorshipProgram> findAll();

    public List<CityStatistic> getStatistics();

    public List<UserSuccessCompletions> getStatisticOfSuccessCompletions();

    public List<MentorshipGroup> getAllMentorshipGroup();
}
