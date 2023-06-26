package epicode.EPICENERGYSERVICE.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "indirizzi")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Indirizzo {
	@Id
	@GeneratedValue
	private UUID id;
	private String via;
	private String civico;
	private String località;
	private int cap;
	@ManyToOne
	// @JoinColumn(name = "comune_id", referencedColumnName = "id", nullable =
	// false)
	@JsonBackReference
	private Comune comune;

	public Indirizzo(String via, String civico, String località, int cap, Comune comune) {
		super();
		this.via = via;
		this.civico = civico;
		this.località = località;
		this.cap = cap;
		this.comune = comune;
	}

}
