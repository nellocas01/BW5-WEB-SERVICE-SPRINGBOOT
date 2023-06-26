package epicode.EPICENERGYSERVICE.entities;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue
	private UUID id;
	private String nome;

	//@OneToMany(mappedBy = "role")
	//private List<User> users;
	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users = new LinkedHashSet<>();

	public Role(String nome) {
		this.nome = nome;
	}

}
