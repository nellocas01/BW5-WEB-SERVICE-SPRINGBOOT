package epicode.EPICENERGYSERVICE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import epicode.EPICENERGYSERVICE.entities.Comune;
import epicode.EPICENERGYSERVICE.entities.Provincia;
import epicode.EPICENERGYSERVICE.repositories.ComuneRepository;
import epicode.EPICENERGYSERVICE.repositories.ProvinciaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(0)
public class ProvinciaRunner implements CommandLineRunner {
	@Autowired
	ProvinciaRepository provinciaRepo;
	@Autowired
	ComuneRepository comuneRepo;

	@Override
	public void run(String... args) throws Exception {

		if (provinciaRepo.findAll().size() == 0) {

			String filePath = new File("province-italiane.csv").getAbsolutePath();
			boolean isFirstLine = true;

			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				String line;
				while ((line = br.readLine()) != null) {
					if (isFirstLine) {
						isFirstLine = false;
						continue;
					}

					String[] columns = line.split(";");
					String siglaProvincia = columns[0];
					String provincia = columns[1];
					String regione = columns[2];

					// System.out.println("Sigla: " + siglaProvincia + ", provincia: " + provincia +
					// ", Regione: " + regione);
					List<Comune> comuni = comuneRepo.findAll();
					List<Comune> comuniPerProvincia = new ArrayList();
					for (Comune comune : comuni) {
						if (comune.getNomeProvincia().equals(provincia)) {
							comuniPerProvincia.add(comune);

						}

					}
					Provincia provincia1 = new Provincia(siglaProvincia, provincia, regione, comuniPerProvincia);
					provinciaRepo.save(provincia1);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
