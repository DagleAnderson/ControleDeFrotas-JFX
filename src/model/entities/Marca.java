package model.entities;

import java.io.Serializable;

public class Marca  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private  String descricao;
	
	public Marca() {
		
	}
	
	public Marca(int id,String desc) {
		this.id = id;
		this.descricao = desc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Marca other = (Marca) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
