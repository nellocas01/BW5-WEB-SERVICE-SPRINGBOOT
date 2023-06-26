package epicode.EPICENERGYSERVICE.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import epicode.EPICENERGYSERVICE.entities.Role;
import epicode.EPICENERGYSERVICE.exceptions.BadRequestException;
import epicode.EPICENERGYSERVICE.exceptions.NotFoundException;
import epicode.EPICENERGYSERVICE.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepo;

	// ***** CREATE *****
	public Role create(String r) {
		roleRepo.findByNome(r).ifPresent(user -> {
			throw new BadRequestException("Ruolo " + r + " già in uso!");
		});

		Role newRuolo = new Role(r);

		return roleRepo.save(newRuolo);
	}

	//***** READ BY ID *****
	public Role findById(UUID id) {
		//Optional<Role> optionalRole = roleRepo.findById(id);
		//return optionalRole.orElse(null);
		return roleRepo.findById(id).orElseThrow(() -> new NotFoundException("RuoloId non trovato"));
	}

	//public List<Role> findAll() {
	//	return roleRepo.findAll();
	//}

	public Role findByNome(String r) throws NotFoundException {
		return roleRepo.findByNome(r).orElseThrow(() -> new NotFoundException("Ruolo con nome:" + r + "non trovato!!"));
	}

	//***** READ ALL *****
	public Page<Role> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return roleRepo.findAll(pageable);
	}

	//	public Optional<Role> findByUser(String user) {
	//		return roleRepository.findByUser(user);
	//	}
	//
	//	public Optional<Role> findByAdmin(String admin) {
	//		return roleRepository.findByAdmin(admin);
	//	}

	//public Role create(Role role) {
	//	return roleRepo.save(role);
	//}

	//***** READ BY ID AND UPDATE *****
	public Role findByIdAndUpdate(UUID id, Role r) throws NotFoundException {
		Role found = this.findById(id);
		found.setId(id);
		found.setNome(r.getNome());
		return roleRepo.save(found);
	}

	//***** DELETE BY ID *****
	public void deleteById(UUID id) {
		Role roleFound = this.findById(id);
		roleRepo.delete(roleFound);
	}
}
