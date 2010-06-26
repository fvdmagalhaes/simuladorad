package Rede;


public class Canal {
	Boolean ocioso;
	Double tamanho;
	
	
	
	public Boolean getOcioso() {
		return ocioso;
	}
	public void setOcioso(Boolean ocioso) {
		this.ocioso = ocioso;
	}
	public Double getTamanho() {
		return tamanho;
	}
	public void setTamanho(Double tamanho) {
		this.tamanho = tamanho;
	}
	//Este metodo serve para facilitar os nossos calculos de envios de quadros.
	//Passa a taxa e retorna quanto tempo demora pra enviar 1 bit pelo canal
	
	public Double getTempoTransmissao(){
		//propagacao eletrica = 5km/ns passando para m/s = 5 x 10^13
		Double propagacao = 50000000000000.00;
		Double tempo = tamanho / propagacao;
		return tempo;
	}
	
}
