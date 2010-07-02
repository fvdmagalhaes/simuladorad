package vo;

import Rede.Evento;

public class EventoVo {
	
	//true caso a ultima transmissao seja uma transmissao com sucesso ou 
	//um reforço de colisao
	boolean transmissaoSucesso;
	Evento ultimoEvento;
	
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
