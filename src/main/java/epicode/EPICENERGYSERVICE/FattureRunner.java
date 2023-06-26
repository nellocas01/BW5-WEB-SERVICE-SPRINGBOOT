package epicode.EPICENERGYSERVICE;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.EPICENERGYSERVICE.entities.Cliente;
import epicode.EPICENERGYSERVICE.entities.Fattura;
import epicode.EPICENERGYSERVICE.entities.StatoFattura;
import epicode.EPICENERGYSERVICE.repositories.ClienteRepository;
import epicode.EPICENERGYSERVICE.repositories.FatturaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(4)
public class FattureRunner implements CommandLineRunner {
	@Autowired
	FatturaRepository fatturaRepo;
	@Autowired
	ClienteRepository clienteRepo;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		List<Fattura> fattureDB = fatturaRepo.findAll();
		List<Cliente> clienteDb = clienteRepo.findAll();

		if (fattureDB.size() == 0) {
			for (int i = 0; i < 20; i++) {
				try {

					String importo = faker.numerify("###.###");
					BigDecimal bigDecimal = new BigDecimal(importo);
					Integer numeroFattura = faker.number().numberBetween(111111, 999999);
					LocalDate data = LocalDate.now();
					Integer anno = faker.number().numberBetween(2019, 2023);
					StatoFattura statoFattura = faker.options().option(StatoFattura.class);
					Cliente randomCliente = clienteDb.get(faker.random().nextInt(clienteDb.size()));
					Fattura newFattura = new Fattura(bigDecimal, numeroFattura, data, anno, statoFattura,
							randomCliente);
					fatturaRepo.save(newFattura);

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

	}

}
