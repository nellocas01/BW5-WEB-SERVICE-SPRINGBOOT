package epicode.EPICENERGYSERVICE.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import epicode.EPICENERGYSERVICE.entities.Role;
import epicode.EPICENERGYSERVICE.exceptions.NotFoundException;
import epicode.EPICENERGYSERVICE.services.RoleService;

@RestController
@RequestMapping("/roles")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("")
	public Page<Role> getRoles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		return roleService.find(page, size, sortBy);
	}

	@GetMapping("/{roleId}")
	public Role getRole(@PathVariable UUID roleId) throws Exception {
		return roleService.findById(roleId);
	}

	@PostMapping("/roleNome")
	@ResponseStatus(HttpStatus.CREATED)
	public Role saveRole(@PathVariable String roleNome) {
		return roleService.create(roleNome);
	}

	@PutMapping("/{roleId}")
	public Role updateRole(@PathVariable UUID roleId, @RequestBody Role body) throws Exception {
		return roleService.findByIdAndUpdate(roleId, body);
	}

	@DeleteMapping("/{roleId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRole(@PathVariable UUID roleId) throws NotFoundException {
		roleService.deleteById(roleId);
	}
}