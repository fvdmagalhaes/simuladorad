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
		//propagacao eletrica = 5km/ns passando para m/ms = 5 x 10^9
		Double propagacao = 5000000000.00;
		//Double tempo = tamanho / propagacao;
		//Nova forma de calcular o tempo de transmissao
		
		//rede Ethernet a 10 Mbps
		//1 megabits = 1 048 576 bits logo:
		Double velocidadeCanal = 1048.576;
		
		int tamanhoQuadro = Quadro.TAMANHO;
//		 Ttransmiss�o + Tpropaga��o = TAMANHOQUADRO/CANAL + DISTANCIA/VELOCIDADE_PROPAGACAO
		Double tempo = (tamanhoQuadro / velocidadeCanal) + (tamanho/propagacao);
		return tempo;
	}
	
}
