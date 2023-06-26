package epicode.EPICENERGYSERVICE.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.opencsv.bean.CsvBindByName;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "province")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Provincia {
	@Id
	@GeneratedValue
	private UUID id;
	@CsvBindByName(column = "Sigle")
	private String sigla;
	@CsvBindByName(column = "Provincia")
	private String nome;
	@CsvBindByName(column = "Regione")
	private String regione;
	@OneToMany(mappedBy = "provincia")
	@JsonManagedReference
	private List<Comune> comuni;

	public Provincia(String sigla, String nome, String regione, List<Comune> comuni) {
		super();
		this.sigla = sigla;
		this.nome = nome;
		this.regione = regione;
		this.comuni = comuni;
	}

}
