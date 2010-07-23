package vo;

import Rede.Evento;

public class EventoVo {
	
	/*Esta classe guarda parametros de métodos que precisam retornar mais de um parametro*/
	
	//Guarda se a transmissao foi transmissao com sucesso ou reforço de colisao
	boolean transmissaoSucesso;
	//Guarda o ultimo evento executado
	Evento ultimoEvento;
	//Guarda o atrazo do binaryBackOff
	double atrazo;
	//Guarda se o quadro vai ser descartado
	boolean descartaQuadro;
	
	public double getAtrazo() {
		return atrazo;
	}
	public void setAtrazo(double atrazo) {
		this.atrazo = atrazo;
	}
	
	public boolean getDescartado() {
		return descartaQuadro;
	}
	public void setDescartado(boolean descartado) {
		this.descartaQuadro = descartado;
	}
	public Evento getUltimoEvento() {
		return ultimoEvento;
	}
	public void setUltimoEvento(Evento ultimoEvento) {
		this.ultimoEvento = ultimoEvento;
	}
	
	public boolean getVerificaTransmissao() {
		return transmissaoSucesso;
	}
	public void setVerificaTransmissao(boolean transmissaoSucesso) {
		this.transmissaoSucesso = transmissaoSucesso;
	}


}
