package epicode.EPICENERGYSERVICE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epicode.EPICENERGYSERVICE.entities.Provincia;
import epicode.EPICENERGYSERVICE.repositories.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepo;

	public Provincia create(Provincia p) {
		Provincia newProvincia = new Provincia(p.getSigla(), p.getNome(), p.getRegione(), p.getComuni());
		return provinciaRepo.save(newProvincia);
	}

}
