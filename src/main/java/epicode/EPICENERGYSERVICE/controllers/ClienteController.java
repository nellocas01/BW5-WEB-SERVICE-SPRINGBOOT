package epicode.EPICENERGYSERVICE.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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

import epicode.EPICENERGYSERVICE.entities.Cliente;
import epicode.EPICENERGYSERVICE.exceptions.NotFoundException;
import epicode.EPICENERGYSERVICE.services.ClienteService;

@RestController
@RequestMapping("/clienti")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@PostMapping("")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente createUtente(@RequestBody @Validated Cliente c) {
		return clienteService.create(c);
	}

	@GetMapping("")
	public Page<Cliente> readUtenti(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return clienteService.findAll(page, size, sortBy);
	}

	@GetMapping("/{clienteId}")
	public Cliente readUtente(@PathVariable UUID clienteId) throws Exception {
		return clienteService.findById(clienteId);
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Cliente>> getClientiByNome(@PathVariable String nome) {
		try {
			List<Cliente> clienti = clienteService.findByNome(nome);
			return ResponseEntity.ok(clienti);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/fatturato/{fatturatoAnnuo}")
	public ResponseEntity<List<Cliente>> getClientiByFatturatoAnnuo(@PathVariable double fatturatoAnnuo) {
		try {
			List<Cliente> clienti = clienteService.findByFatturatoAnnuo(fatturatoAnnuo);
			return ResponseEntity.ok(clienti);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/data-inserimento/{data}")
	public ResponseEntity<List<Cliente>> getClientiByDataInserimento(
			@PathVariable("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
		try {
			List<Cliente> clienti = clienteService.findByDataInserimento(data);
			return ResponseEntity.ok(clienti);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/data-ultimo-contatto/{data}")
	public ResponseEntity<List<Cliente>> getClientiByDataUltimoContatto(
			@PathVariable("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
		try {
			List<Cliente> clienti = clienteService.findByDataUltimoContatto(data);
			return ResponseEntity.ok(clienti);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{clienteId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Cliente updateCliente(@PathVariable UUID clienteId, @RequestBody Cliente c) throws Exception {
		return clienteService.update(clienteId, c);
	}

	@DeleteMapping("/{clienteId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCliente(@PathVariable UUID clienteId) throws Exception {
		clienteService.delete(clienteId);
	}

}
