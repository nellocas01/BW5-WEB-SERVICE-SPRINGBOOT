package epicode.EPICENERGYSERVICE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import epicode.EPICENERGYSERVICE.entities.Comune;
import epicode.EPICENERGYSERVICE.entities.Provincia;
import epicode.EPICENERGYSERVICE.repositories.ComuneRepository;
import epicode.EPICENERGYSERVICE.repositories.ProvinciaRepository;
import epicode.EPICENERGYSERVICE.services.ComuneService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(1)
public class ComuneRunner implements CommandLineRunner {

	@Autowired
	ComuneService comuneService;
	@Autowired
	ProvinciaRepository provinciaRepo;
	@Autowired
	ComuneRepository comuneRepo;

	@Override
	public void run(String... args) throws Exception {

		if (comuneRepo.findAll().size() == 0) {

			String filePath = new File("comuni-italiani.csv").getAbsolutePath();
			boolean isFirstLine = true;

			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				String line;
				while ((line = br.readLine()) != null) {
					if (isFirstLine) {
						isFirstLine = false;
						continue;
					}

					String[] columns = line.split(";");
					String codiceProvincia = columns[0];
					String progressivoComune = columns[1];
					String denominazione = columns[2];
					String nomeProvincia = columns[3];

					// System.out.println("Codice provincia: " + codiceProvincia + ", progressivo
					// del comune: " + progressivoComune
					// + ", denominazione in italiano: " + denominazione + ", nome provincia: " +
					// nomeProvincia);

					Provincia provincia = provinciaRepo.findByNome(nomeProvincia);
					if (provincia != null) {
						Comune newComune = new Comune(codiceProvincia, progressivoComune, denominazione, nomeProvincia,
								provincia);

						comuneRepo.save(newComune);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
