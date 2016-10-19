package mentorship.program.service.impl;

import mentorship.program.dao.impl.MentorshipGroupDaoImpl;
import mentorship.program.dao.impl.MentorshipProgramDaoImpl;
import mentorship.program.model.MentorshipGroup;
import mentorship.program.service.MentorshipGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 10/18/2016.
 */
@Service
public class MentorshipGroupServiceImpl implements MentorshipGroupService {

    @Autowired
    private MentorshipGroupDaoImpl mentorshipGroupDao;

    public void create(MentorshipGroup mentorshipGroup){mentorshipGroupDao.create(mentorshipGroup);}

    public void delete(MentorshipGroup mentorshipGroup){mentorshipGroupDao.delete(mentorshipGroup);}

    public void deleteById(Long mentorshipGroupId){mentorshipGroupDao.deleteById(mentorshipGroupId);}

    public List<MentorshipGroup> getAll(){return mentorshipGroupDao.getAll();}

    public List<MentorshipGroup> getByName(String name){return mentorshipGroupDao.getByName(name);}

    public MentorshipGroup getById(long id){return mentorshipGroupDao.getById(id);}

    public void update(MentorshipGroup mentorshipGroup){mentorshipGroupDao.update(mentorshipGroup);}

}
