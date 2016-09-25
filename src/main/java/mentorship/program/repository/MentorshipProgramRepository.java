package mentorship.program.repository;

import mentorship.program.model.MentorshipProgramm;
import mentorship.program.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 9/22/2016.
 */
@Repository
@Transactional
public interface MentorshipProgramRepository extends JpaRepository<MentorshipProgramm, Long> {

    public List<MentorshipProgramm> findByName(String name);

}
