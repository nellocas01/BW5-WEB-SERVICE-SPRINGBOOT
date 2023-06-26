package epicode.EPICENERGYSERVICE.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "fatture")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Fattura {
	@Id
	@GeneratedValue
	private UUID id;
	private BigDecimal importo;
	private int numeroFattura;
	private LocalDate data;
	private int anno;
	@Enumerated(EnumType.STRING)
	private StatoFattura statoFattura;
	@ManyToOne
	@JsonBackReference // PERMETTE DI VISUALIZZARE IL DATO NEL JSON SU POSTMAN SENZA CREARE LOOP;
						// QUESTA VA SULLA PROPRIETà SINGOLA(VEDI CLIENTE ENTITY)
	private Cliente cliente;

	public Fattura(BigDecimal importo, int numeroFattura, LocalDate data, int anno, StatoFattura statoFattura,
			Cliente cliente) {
		super();
		this.importo = importo;
		this.numeroFattura = numeroFattura;
		this.data = data;
		this.anno = anno;
		this.statoFattura = statoFattura;
		this.cliente = cliente;
	}

}
