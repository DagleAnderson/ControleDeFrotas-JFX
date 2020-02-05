package model.entities;

import java.io.Serializable;

public class Veiculo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
private Integer id;
  private String descricao;
  private String ano;
  private Double kmRodado;
  private String placa;
  private String chassi;
  private String renavam;
  private Modelo modelo;
  
  
  public Veiculo() {
	  
  }


public Veiculo(Integer id, String descricao, String ano, Double kmRodado, String placa, String chassi, String renavam,Modelo modelo) {
	this.id = id;
	this.descricao = descricao;
	this.ano = ano;
	this.kmRodado = kmRodado;
	this.placa = placa;
	this.chassi = chassi;
	this.renavam = renavam;
	this.modelo = modelo;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((placa == null) ? 0 : placa.hashCode());
	result = prime * result + ((renavam == null) ? 0 : renavam.hashCode());
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
	Veiculo other = (Veiculo) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (placa == null) {
		if (other.placa != null)
			return false;
	} else if (!placa.equals(other.placa))
		return false;
	if (renavam == null) {
		if (other.renavam != null)
			return false;
	} else if (!renavam.equals(other.renavam))
		return false;
	return true;
}


public Integer getId() {
	return id;
}


public void setId(Integer id) {
	this.id = id;
}


public String getDescricao() {
	return descricao;
}


public void setDescricao(String descricao) {
	this.descricao = descricao;
}


public String getAno() {
	return ano;
}


public void setAno(String ano) {
	this.ano = ano;
}


public Double getKmRodado() {
	return kmRodado;
}


public void setKmRodado(Double kmRodado) {
	this.kmRodado = kmRodado;
}


public String getPlaca() {
	return placa;
}


public void setPlaca(String placa) {
	this.placa = placa;
}


public String getChassi() {
	return chassi;
}


public void setChassi(String chassi) {
	this.chassi = chassi;
}


public String getRenavam() {
	return renavam;
}


public void setRenavam(String renavam) {
	this.renavam = renavam;
}
  

public Modelo getModelo() {
	return modelo;
}


public void setModelo(Modelo modelo) {
	this.modelo = modelo;
}


@Override
public String toString() {
	return "Veiculo [id=" + id + ", descricao=" + descricao + ", ano=" + ano + ", kmRodado=" + kmRodado + ", placa="
			+ placa + ", chassi=" + chassi + ", renavam=" + renavam + ", modelo=" + modelo.getDescricao()+", marca="+modelo.getMarca().getDescricao() + "] \n";
}



  
}
