package model.entities;

import java.util.Date;

public class Viagem {
	private Integer id;
	private String origem;
	private String destino;
	private String distancia;
	private Veiculo veiculo;
	private Motorista motorista;
	private Double valorDoFrete;
	private Date dataSaida;
	private Date dataChegada;
	
	
	public Viagem() {

	}


	public Viagem(Integer id, String origem, String destino, String distancia, Veiculo veiculo, Motorista motorista,
			Double valorDoFrete, Date dataSaida, Date dataChegada) {
		this.id = id;
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
		this.veiculo = veiculo;
		this.motorista = motorista;
		this.valorDoFrete = valorDoFrete;
		this.dataSaida = dataSaida;
		this.dataChegada = dataChegada;
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
		Viagem other = (Viagem) obj;
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


	public String getOrigem() {
		return origem;
	}


	public void setOrigem(String origem) {
		this.origem = origem;
	}


	public String getDestino() {
		return destino;
	}


	public void setDestino(String destino) {
		this.destino = destino;
	}


	public String getDistancia() {
		return distancia;
	}


	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}


	public Veiculo getVeiculo() {
		return veiculo;
	}


	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}


	public Motorista getMotorista() {
		return motorista;
	}


	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}


	public Double getValorDoFrete() {
		return valorDoFrete;
	}


	public void setValorDoFrete(Double valorDoFrete) {
		this.valorDoFrete = valorDoFrete;
	}


	public Date getDataSaida() {
		return dataSaida;
	}


	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}


	public Date getDataChegada() {
		return dataChegada;
	}


	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}

	
	


}


	