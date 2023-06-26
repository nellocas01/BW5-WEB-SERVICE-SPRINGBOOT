package epicode.EPICENERGYSERVICE.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import epicode.EPICENERGYSERVICE.entities.Indirizzo;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, UUID> {

}
