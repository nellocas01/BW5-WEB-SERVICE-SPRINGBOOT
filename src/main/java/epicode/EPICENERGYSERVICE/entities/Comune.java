package epicode.EPICENERGYSERVICE.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.opencsv.bean.CsvBindByName;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "comuni")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Comune {
	@Id
	@GeneratedValue
	private UUID id;
	@CsvBindByName(column = "Codice Provincia")
	private String codiceProvincia;
	// private int codiceProvincia(storico)(1);
	@CsvBindByName(column = "Progressivo del Comune")
	private String progressivoDelComune;
	@CsvBindByName(column = "Denominazione in Italiano")
	private String denominazioneInItaliano;
	@CsvBindByName(column = "Nome Provincia")
	private String nomeProvincia;
	@ManyToOne
	@JsonBackReference
	private Provincia provincia;
	@OneToMany(mappedBy = "comune")
	@JsonManagedReference
	private List<Indirizzo> indirizzi;

	public Comune(String codiceProvincia, String progressivoDelComune, String denominazioneInItaliano,
			String nomeProvincia, Provincia provincia) {
		super();
		this.codiceProvincia = codiceProvincia;
		this.progressivoDelComune = progressivoDelComune;
		this.denominazioneInItaliano = denominazioneInItaliano;
		this.nomeProvincia = nomeProvincia;
		this.provincia = provincia;
	}

}
