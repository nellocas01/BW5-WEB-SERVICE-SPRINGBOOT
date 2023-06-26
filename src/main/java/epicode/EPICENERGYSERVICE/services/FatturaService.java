package epicode.EPICENERGYSERVICE.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import epicode.EPICENERGYSERVICE.entities.Fattura;
import epicode.EPICENERGYSERVICE.entities.StatoFattura;
import epicode.EPICENERGYSERVICE.exceptions.NotFoundException;
import epicode.EPICENERGYSERVICE.repositories.ClienteRepository;
import epicode.EPICENERGYSERVICE.repositories.FatturaRepository;

@Service
public class FatturaService {

	@Autowired
	FatturaRepository fatturaRepo;

	@Autowired
	ClienteRepository clienteRepo;

	public Fattura create(Fattura f) {
		Fattura newFattura = new Fattura(f.getImporto(), f.getNumeroFattura(), f.getData(), f.getAnno(),
				f.getStatoFattura(), f.getCliente());
		return fatturaRepo.save(newFattura);
	}

	public Page<Fattura> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return fatturaRepo.findAll(pageable);
	}

	public Fattura findById(UUID id) throws NotFoundException {
		return fatturaRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente con Id:" + id + "non trovato!!"));
	}

	public List<Fattura> findByStato(StatoFattura stato) throws NotFoundException {
		List<Fattura> fatture = fatturaRepo.findByStatoFattura(stato);
		if (fatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata con stato " + stato);
		}
		return fatture;
	}

	public List<Fattura> findByData(LocalDate data) throws NotFoundException {
		List<Fattura> fatture = fatturaRepo.findByData(data);
		if (fatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata in data " + data);
		}
		return fatture;
	}

	public List<Fattura> findByAnno(int anno) throws NotFoundException {
		List<Fattura> fatture = fatturaRepo.findByAnno(anno);
		if (fatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata nell' anno " + anno);
		}
		return fatture;

	}

	public List<Fattura> findByImporti(BigDecimal importoMinimo, BigDecimal importoMassimo) throws NotFoundException {
		List<Fattura> fatture = fatturaRepo.findByImportoRange(importoMinimo, importoMassimo);
		if (fatture.isEmpty()) {
			throw new NotFoundException(
					"Nessuna fattura trovata con importo compreso tra " + importoMinimo + " e " + importoMassimo);
		}
		return fatture;
	}

	public Fattura findByIdAndUpdate(UUID id, Fattura f) throws NotFoundException {
		Fattura found = this.findById(id);

		found.setId(id);
		found.setImporto(f.getImporto());
		found.setNumeroFattura(f.getNumeroFattura());
		found.setData(f.getData());
		found.setAnno(f.getAnno());
		found.setStatoFattura(f.getStatoFattura());
		found.setCliente(f.getCliente());

		return fatturaRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Fattura found = this.findById(id);
		fatturaRepo.delete(found);
	}

}
