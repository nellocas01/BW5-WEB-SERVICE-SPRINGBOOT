package epicode.EPICENERGYSERVICE.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.EPICENERGYSERVICE.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

	public Optional<Cliente> findByEmail(String email);

	public List<Cliente> findByNomeContaining(String nome);

	public List<Cliente> findByFatturatoAnnuo(double fatturatoAnnuo);

	public List<Cliente> findByDataInserimento(LocalDate data);

	public List<Cliente> findByDataUltimoContatto(LocalDate data);

//	@Query("SELECT COALESCE(SUM(f.importo), 0) FROM Cliente c JOIN c.fatture f WHERE c.id = :clienteId GROUP BY f.importo")
//	BigDecimal calculateFatturatoAnnuo(@Param("clienteId") UUID clienteId);

}
