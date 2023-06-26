package epicode.EPICENERGYSERVICE.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import epicode.EPICENERGYSERVICE.entities.Comune;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, UUID> {
	// List<Comune> findByProvincia(String nome);
	@Query("SELECT c FROM Comune c WHERE c.provincia = :provincia")
	List<Comune> findByProvincia(@Param("provincia") String provincia);

}
