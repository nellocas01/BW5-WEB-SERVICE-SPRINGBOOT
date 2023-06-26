package epicode.EPICENERGYSERVICE;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.EPICENERGYSERVICE.entities.Cliente;
import epicode.EPICENERGYSERVICE.entities.Indirizzo;
import epicode.EPICENERGYSERVICE.entities.RagioneSociale;
import epicode.EPICENERGYSERVICE.repositories.ClienteRepository;
import epicode.EPICENERGYSERVICE.repositories.FatturaRepository;
import epicode.EPICENERGYSERVICE.repositories.IndirizzoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(3)
public class ClienteRunner implements CommandLineRunner {
	@Autowired
	ClienteRepository clienteRepo;
	@Autowired
	FatturaRepository fatturaRepo;
	@Autowired
	IndirizzoRepository indirizzoRepo;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		Random random = new Random();

		List<Cliente> clienteDb = clienteRepo.findAll();
		List<Indirizzo> indirizzoDb = indirizzoRepo.findAll();
		indirizzoDb.get(0);
		if (clienteDb.size() == 0) {
			for (int i = 0; i < 20; i++) {
				try {

					String nome = faker.name().firstName();
					Integer partitaIva = faker.number().numberBetween(111111, 999999);

					Indirizzo indirizzoLegale = indirizzoDb.get(i);
					Indirizzo indirizzoOperativo = indirizzoDb.get(i + 20);

					String email = faker.internet().emailAddress();
					String telefono = faker.phoneNumber().cellPhone();
					String pec = faker.internet().emailAddress();
					String emailContatto = faker.internet().emailAddress();
					String nomeContatto = faker.name().firstName();
					String cognomeContatto = faker.name().lastName();
					String telefonoContatto = faker.phoneNumber().cellPhone();
					LocalDate dataInserimento = LocalDate.now();
					LocalDate dataUltimoContatto = LocalDate.now();
					RagioneSociale ragioneSociale = faker.options().option(RagioneSociale.class);
					Double fatturatoAnnuo = random.nextDouble() * 10000.0;
					// Double fatturatoAnnuo = Cliente.
					// List<Fattura> fatture = new ArrayList<>();
					Cliente newcliente = new Cliente(nome, partitaIva, indirizzoLegale, indirizzoOperativo, email,
							telefono, pec, emailContatto, nomeContatto, cognomeContatto, telefonoContatto,
							dataInserimento, dataUltimoContatto, ragioneSociale, fatturatoAnnuo);
					// newcliente.fatturatoAnnuo(newcliente.getFatture());
					clienteRepo.save(newcliente);

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

	}

}
