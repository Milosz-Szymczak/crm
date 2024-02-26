package pl.crm.repository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import pl.crm.model.users.User;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
    User findByUsername(String username);
}
