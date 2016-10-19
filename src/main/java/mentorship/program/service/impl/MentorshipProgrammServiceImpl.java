package mentorship.program.service.impl;

import mentorship.program.dao.impl.MentorshipProgramDaoImpl;
import mentorship.program.dao.impl.UserDaoImpl;
import mentorship.program.model.MentorshipGroup;
import mentorship.program.model.MentorshipProgram;
import mentorship.program.model.User;
import mentorship.program.model.persistance.CityStatistic;
import mentorship.program.model.persistance.UserSuccessCompletions;
import mentorship.program.repository.MentorshipProgramRepository;
import mentorship.program.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mentorship.program.model.persistance.Level;
import mentorship.program.service.MentorshipProgrammService;

import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 9/23/2016.
 */
@Service
public class MentorshipProgrammServiceImpl implements MentorshipProgrammService{

//    @Autowired
//    private MentorshipProgramRepository mentorshipProgramRepository;

    @Autowired
    private MentorshipProgramDaoImpl mentorshipProgramDao;

    public MentorshipProgram getMentorshipProgramm(Long id) {
        return mentorshipProgramDao.getById(id);
    }

    public List<MentorshipGroup> getAllMentorshipGroup() {
        return mentorshipProgramDao.getAllMentorshipGroup();
    }

    public List<MentorshipProgram> getMentorshipProgramm(String name) {
        return mentorshipProgramDao.getByName(name);
    }

    public void addMentorshipProgramm(MentorshipProgram mentorshipProgramm) {
        mentorshipProgramDao.create(mentorshipProgramm);
    }

    public void removeMentorshipProgramm(Long id) {
        mentorshipProgramDao.deleteById(id);
    }

    public void editMentorshipProgramm(MentorshipProgram mentorshipProgramm) {
        mentorshipProgramDao.update(mentorshipProgramm);
    }

    public List<MentorshipProgram> findByName(String name) {
        return mentorshipProgramDao.getByName(name);
    }

    @Override
    public List<MentorshipProgram> findAll() {
        return mentorshipProgramDao.getAll();
    }

    @Override
    public List<CityStatistic> getStatistics() {
        return mentorshipProgramDao.getCitiesStatistic();
    }

    public List<UserSuccessCompletions> getStatisticOfSuccessCompletions() {
        return mentorshipProgramDao.getStatisticOfSuccessCompletions();
    }

}
