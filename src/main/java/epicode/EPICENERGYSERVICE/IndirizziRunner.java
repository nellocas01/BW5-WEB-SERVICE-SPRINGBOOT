package epicode.EPICENERGYSERVICE;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.EPICENERGYSERVICE.entities.Comune;
import epicode.EPICENERGYSERVICE.entities.Indirizzo;
import epicode.EPICENERGYSERVICE.repositories.ComuneRepository;
import epicode.EPICENERGYSERVICE.repositories.IndirizzoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(2)
public class IndirizziRunner implements CommandLineRunner {
	@Autowired
	ComuneRepository comuneRepo;
	@Autowired
	IndirizzoRepository indirizzoRepo;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		List<Comune> comuniDB = comuneRepo.findAll();
		List<Indirizzo> indirizzoDb = indirizzoRepo.findAll();

		if (indirizzoDb.size() == 0) {
			for (int i = 0; i < 50; i++) {
				try {

					String via = faker.address().streetName();
					String civico = faker.address().buildingNumber();
					String località = faker.address().city();
					Integer cap = faker.number().numberBetween(10000, 99999);
					Comune randomComune = comuniDB.get(faker.random().nextInt(comuniDB.size()));
					Indirizzo indirizzo = new Indirizzo(via, civico, località, cap, randomComune);
					indirizzoRepo.save(indirizzo);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

	}

}
