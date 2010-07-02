package Rede;


public class Canal {
	Boolean ocioso;
	Double tamanho;
	Estacao estacao;
	
	public Estacao getEstacao() {
		return estacao;
	}
	public void setEstacao(Estacao estacao) {
		this.estacao = estacao;
	}
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
		//Double tempo = tamanho / propagacao;
		//Nova forma de calcular o tempo de transmissao
		// Ttransmissão + Tpropagação = TAMANHOQUADRO/CANAL + DISTANCIA/VELOCIDADE_PROPAGACAO
		//rede Ethernet a 10 Mbps, canal
		//1 megabits = 1 048 576 bits logo 10Mbps = 1 048 576 bps
		Double velocidadeCanal = 1048576.00;
		Quadro quadro = new Quadro();
		int tamanhoQuadro = quadro.TAMANHO;
		Double tempo = (tamanhoQuadro / velocidadeCanal) + (tamanho/propagacao);
		return tempo;
	}
	
}
