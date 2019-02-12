package pl.pawel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.pawel.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{

	User findByUsername(String username);
}
