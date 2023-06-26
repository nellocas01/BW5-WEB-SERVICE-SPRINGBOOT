package epicode.EPICENERGYSERVICE.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import epicode.EPICENERGYSERVICE.entities.Fattura;
import epicode.EPICENERGYSERVICE.entities.StatoFattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

	List<Fattura> findByStatoFattura(StatoFattura statoFattura);

	List<Fattura> findByData(LocalDate data);

	List<Fattura> findByAnno(int anno);

	@Query("SELECT f FROM Fattura f WHERE f.importo >= :importoMinimo AND f.importo <= :importoMassimo")
	List<Fattura> findByImportoRange(@Param("importoMinimo") BigDecimal importoMinimo,
			@Param("importoMassimo") BigDecimal importoMassimo);
}
