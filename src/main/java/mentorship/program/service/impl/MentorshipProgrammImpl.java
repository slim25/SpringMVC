package mentorship.program.service.impl;

import mentorship.program.model.User;
import mentorship.program.repository.MentorshipProgramRepository;
import mentorship.program.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mentorship.program.model.MentorshipProgramm;
import mentorship.program.model.persistance.Level;
import mentorship.program.service.MentorshipProgrammService;

import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 9/23/2016.
 */
@Service
public class MentorshipProgrammImpl implements MentorshipProgrammService{

    @Autowired
    private MentorshipProgramRepository mentorshipProgramRepository;

    public MentorshipProgramm getMentorshipProgramm(Long id) {
        return mentorshipProgramRepository.findOne(id);
    }

    public List<MentorshipProgramm> getMentorshipProgramm(String name) {
        return mentorshipProgramRepository.findByName(name);
    }

    public void addMentorshipProgramm(MentorshipProgramm mentorshipProgramm) {
        mentorshipProgramRepository.save(mentorshipProgramm);
    }

    public void removeMentorshipProgramm(Long id) {
        mentorshipProgramRepository.delete(id);
    }

    public void editMentorshipProgramm(MentorshipProgramm mentorshipProgramm) {
        mentorshipProgramRepository.save(mentorshipProgramm);
    }

    public List<MentorshipProgramm> findByName(String name) {
        return mentorshipProgramRepository.findByName(name);
    }

    @Override
    public List<MentorshipProgramm> findAll() {
        return mentorshipProgramRepository.findAll();
    }
}
