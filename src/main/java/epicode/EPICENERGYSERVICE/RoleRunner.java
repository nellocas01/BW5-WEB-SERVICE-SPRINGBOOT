package epicode.EPICENERGYSERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import epicode.EPICENERGYSERVICE.entities.Role;
import epicode.EPICENERGYSERVICE.repositories.RoleRepository;

@Component
@Order(0)
public class RoleRunner implements CommandLineRunner {

	@Autowired
	//RoleRepository roleRepo;
	RoleRepository roleRepo;

	//@Autowired
	//UsersRepository usersRepo;

	@Override
	public void run(String... args) throws Exception {

		if (roleRepo.count() == 0) {
			String[] rolesDefault = new String[] { "USER", "ADMIN" };

			for (String role : rolesDefault) {
				Role newRole = new Role(role);
				roleRepo.save(newRole);
			}
		}

	}

}