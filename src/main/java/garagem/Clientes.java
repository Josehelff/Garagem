package garagem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

	@Entity
	@Table(name = "cliente") // indica que a classe Cliente corresponde à tabela tb_Cliente no banco de dados.
public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;  // Campo especial usado na serialização para verificar a compatibilidade das versões de uma classe.
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //@GeneratedValue: Indica que o valor para a chave primária será gerado automaticamente. //strategy = GenerationType.IDENTITY: Especifica a estratégia de geração de valor.
	@Column(name = "CodCliente") 
	
	Integer id;  
	@Column(name = "Nome")
	String nome;  // Campo para armazenar o nome da pessoa.
	@Column(name = "Telefone")
	String telefone; // Campo para armazenar o telefone da pessoa.
	
	@OneToMany(mappedBy = "cliente") //está sendo usada na classe Clientes para mapear o relacionamento entre as entidades Clientes e carros. Essa anotação indica que há uma relação de um-para-muitos (one-to-many) entre Clientes e carros.
    private List<carros> carros = new ArrayList<>();
	
	
	public Clientes(Integer id, String nome, String telefone) {
		super();  // Chama o construtor da superclasse (neste caso, Object).
		this.id = id;  // Atribui o valor do parâmetro 'id' ao campo 'id'.
		this.nome = nome;  // Atribui o valor do parâmetro 'nome' ao campo 'nome'.
		this.telefone = telefone;  // Atribui o valor do parâmetro 'email' ao campo 'telefone'.
		
	}
	
	public Clientes () {
		// Construtor padrão sem parâmetros. É necessário para a criação de instâncias pela JPA e outros frameworks.
	}
	
	@Override
	public String toString() {
		
		return "clientes [id=" + id + ", nome=" + nome + ", telfone=" + telefone + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	 public List<carros> getCarros() {
	        return carros;
	    }
	 public void setCarros(List<carros> carros) {
	        this.carros = carros;
	    }

	
}
