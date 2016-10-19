package mentorship.program.dao;

import mentorship.program.model.MentorshipGroup;

import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 10/3/2016.
 */
public interface MentorshipGroupDao {

    public void create(MentorshipGroup mentorshipGroup);

    public void delete(MentorshipGroup mentorshipGroup);

    public void deleteById(Long mentorshipGroupId);

    public List<MentorshipGroup> getAll();

    public List<MentorshipGroup> getByName(String name);

    public MentorshipGroup getById(long id);

    public void update(MentorshipGroup mentorshipGroup);


}
