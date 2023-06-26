package epicode.EPICENERGYSERVICE.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.EPICENERGYSERVICE.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User, UUID> {
	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

}
