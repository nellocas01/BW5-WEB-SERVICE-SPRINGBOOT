package epicode.EPICENERGYSERVICE.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.EPICENERGYSERVICE.entities.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {

	Provincia findByNome(String nome);

}
