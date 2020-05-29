package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Motorista implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 private Integer id;
	 private String nome;
	 private String sobreNome;
	 private Date dataNascimento;
	 private String cpf;
	 private String cnh;
	 private String telefone;
	 private String email;
	 private Endereco endereco;
	 
	 public Motorista() {
		 
	 }

	public Motorista(Integer id, String nome, String sobreNome, Date dataNascimento, String cpf, String cnh,
			String telefone, String email, Endereco endereco) {
		this.id = id;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.cnh = cnh;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Motorista other = (Motorista) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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
	

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}
	
	
	public Date getDataNasc() {
		return dataNascimento;
	}
	
	public void setDataNasc(Date dataNasc) {
		this.dataNascimento = dataNasc;
		
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getNome();
	}
	 
}
