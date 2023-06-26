package epicode.EPICENERGYSERVICE.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import epicode.EPICENERGYSERVICE.entities.Fattura;
import epicode.EPICENERGYSERVICE.entities.StatoFattura;
import epicode.EPICENERGYSERVICE.exceptions.NotFoundException;
import epicode.EPICENERGYSERVICE.services.FatturaService;

@RestController
@RequestMapping("/fatture")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
public class FatturaController {
	@Autowired
	private FatturaService fatturaService;

	@GetMapping("")
	public Page<Fattura> getFatture(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return fatturaService.find(page, size, sortBy);
	}

	@PostMapping("")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public Fattura createFattura(@RequestBody @Validated Fattura f) {
		return fatturaService.create(f);
	}

	@GetMapping("/{fatturaId}")
	public Fattura getFattura(@PathVariable UUID fatturaId) throws Exception {
		return fatturaService.findById(fatturaId);
	}

	@GetMapping("/stato/{stato}")
	public ResponseEntity<List<Fattura>> getFattureByStato(@PathVariable StatoFattura stato) {
		try {
			List<Fattura> fatture = fatturaService.findByStato(stato);
			return ResponseEntity.ok(fatture);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/data/{dataString}")
	public ResponseEntity<List<Fattura>> getFattureByData(@PathVariable("dataString") String dataString) {
		try {
			LocalDate data = LocalDate.parse(dataString);

			List<Fattura> fatture = fatturaService.findByData(data);
			return ResponseEntity.ok(fatture);
		} catch (DateTimeParseException e) {
			// Gestione dell'errore di parsing della data
			return ResponseEntity.badRequest().build();
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/anno/{anno}")
	public ResponseEntity<List<Fattura>> getFattureByAnno(@PathVariable int anno) {
		try {
			List<Fattura> fatture = fatturaService.findByAnno(anno);
			return ResponseEntity.ok(fatture);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/importo")
	public ResponseEntity<List<Fattura>> getFattureByImportoRange(
			@RequestParam("importoMinimo") BigDecimal importoMinimo,
			@RequestParam("importoMassimo") BigDecimal importoMassimo) {
		try {
			List<Fattura> fatture = fatturaService.findByImporti(importoMinimo, importoMassimo);
			return ResponseEntity.ok(fatture);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{fatturaId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Fattura updateUser(@PathVariable UUID fatturaId, @RequestBody Fattura f) throws Exception {
		return fatturaService.findByIdAndUpdate(fatturaId, f);
	}

	@DeleteMapping("/{fatturaId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID fatturaId) throws NotFoundException {
		fatturaService.findByIdAndDelete(fatturaId);
	}

}
