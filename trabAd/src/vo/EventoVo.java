package vo;

import Rede.Evento;

public class EventoVo {
	
	//true caso a ultima transmissao seja uma transmissao com sucesso ou 
	//um refor�o de colisao
	boolean transmissaoSucesso;
	Evento ultimoEvento;
	double atrazo;
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