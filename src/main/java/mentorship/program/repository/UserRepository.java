package mentorship.program.repository;

import mentorship.program.model.User;
import mentorship.program.model.persistance.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 9/22/2016.
 */

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByName(String name);

    public List<User> findByLevel(Level level);
}
