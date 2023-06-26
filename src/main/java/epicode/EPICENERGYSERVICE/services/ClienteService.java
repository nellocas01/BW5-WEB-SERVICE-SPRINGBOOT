package epicode.EPICENERGYSERVICE.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import epicode.EPICENERGYSERVICE.entities.Cliente;
import epicode.EPICENERGYSERVICE.exceptions.NotFoundException;
import epicode.EPICENERGYSERVICE.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepo;

	// ***** CREATE *****
	public Cliente create(Cliente c) {
		Cliente newCliente = new Cliente(c.getNome(), c.getPartitaIva(), c.getIndirizzoLegale(),
				c.getIndirizzoOperativo(), c.getEmail(), c.getTelefono(), c.getPec(), c.getEmailContatto(),
				c.getNomeContatto(), c.getCognomeContatto(), c.getTelefonoContatto(), LocalDate.now(), LocalDate.now(),
				c.getRagioneSociale(), c.getFatturatoAnnuo());
		clienteRepo.save(newCliente);
		// newCliente.setFatturatoAnnuo(newCliente.fatturatoAnnuo(newCliente.getFatture()));

		return newCliente;
	}

	// ***** READ *****
	public Page<Cliente> findAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return clienteRepo.findAll(pageable);
	}

	// read by Id
	public Cliente findById(UUID clienteId) throws NotFoundException {
		return clienteRepo.findById(clienteId).orElseThrow(() -> new NotFoundException("Cliente non trovato"));
	}

	// read by nome

	public List<Cliente> findByNome(String nome) throws NotFoundException {
		List<Cliente> clienti = clienteRepo.findByNomeContaining(nome);
		if (clienti.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata con stato " + nome);
		}
		return clienti;
	}

	// read by FatturatoAnnuo
	public List<Cliente> findByFatturatoAnnuo(double fatturatoAnnuo) throws NotFoundException {
		List<Cliente> clienti = clienteRepo.findByFatturatoAnnuo(fatturatoAnnuo);
		if (clienti.isEmpty()) {
			throw new NotFoundException("Nessun cliente trovato con il fatturato annuo: " + fatturatoAnnuo);
		}
		return clienti;
	}

	// read by DataInserimento
	public List<Cliente> findByDataInserimento(LocalDate data) throws NotFoundException {
		List<Cliente> clienti = clienteRepo.findByDataInserimento(data);
		if (clienti.isEmpty()) {
			throw new NotFoundException("Nessun cliente trovato con la data di inserimento: " + data);
		}
		return clienti;
	}

	// read by DataUltimoContatto
	public List<Cliente> findByDataUltimoContatto(LocalDate data) throws NotFoundException {
		List<Cliente> clienti = clienteRepo.findByDataUltimoContatto(data);
		if (clienti.isEmpty()) {
			throw new NotFoundException("Nessun cliente trovato con la data di ultimo contatto: " + data);
		}
		return clienti;
	}
//	 public double getFatturatoAnnuo(UUID clienteId) {
//	        BigDecimal fatturato = clienteRepo.calculateFatturatoAnnuo(clienteId);
//	        return fatturato != null ? fatturato.doubleValue() : 0.0;
//	    }

	// ***** UPDATE *****
	public Cliente update(UUID clienteId, Cliente c) throws NotFoundException {
		Cliente clienteFound = this.findById(clienteId);

		clienteFound.setId(clienteId);
		clienteFound.setNome(c.getNome());
		clienteFound.setPartitaIva(c.getPartitaIva());
		clienteFound.setIndirizzoLegale(c.getIndirizzoLegale());
		clienteFound.setIndirizzoOperativo(c.getIndirizzoOperativo());
		clienteFound.setEmail(c.getEmail());
		clienteFound.setTelefono(c.getTelefono());
		clienteFound.setPec(c.getPec());
		clienteFound.setEmailContatto(c.getEmailContatto());
		clienteFound.setNomeContatto(c.getNomeContatto());
		clienteFound.setCognomeContatto(c.getCognomeContatto());
		clienteFound.setTelefonoContatto(c.getTelefonoContatto());
		clienteFound.setDataUltimoContatto(c.getDataUltimoContatto());
		clienteFound.setRagioneSociale(c.getRagioneSociale());
		clienteFound.setFatturatoAnnuo(c.getFatturatoAnnuo());
		clienteFound.setFatture(c.getFatture());

		return clienteRepo.save(clienteFound);
	}

	// ***** DELETE *****
	public void delete(UUID clienteId) throws NotFoundException {
		Cliente clienteFound = this.findById(clienteId);

		clienteRepo.delete(clienteFound);
	}

}
