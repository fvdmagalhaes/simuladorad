package Rede;

import Controle.main;


public class Estacao {
	int id;
	Canal tx;
	Canal rx;
	int taxaDeChegada;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTaxaDeChegada() {
		return taxaDeChegada;
	}
	public void setTaxaDeChegada(int taxaDeChegada) {
		this.taxaDeChegada = taxaDeChegada;
	}
	public Canal getTx() {
		return tx;
	}
	public void setTx(Canal tx) {
		this.tx = tx;
	}
	public Canal getRx() {
		return rx;
	}
	public void setRx(Canal rx) {
		this.rx = rx;
	}
	public void enviaPacote (Pacote pacote, Evento ultimoEvento){
		
		//Antes de enviar o pacote a estacao sente o meio e "seta" o proximo evento como o evento de enviar
		if(tx.getOcioso())
		{
			//muda o canal de transmissao para ocioso
			tx.setOcioso(true);
			//Cria o proximo evento para transmitir o pacote
			Evento proximoEvento = new Evento();
			//proximoEvento.setQuadro()
			//proximoEvento.setTempo()
			proximoEvento.setTipo(TipoEvento.TRANSMITE_QUADRO);
			main.insereEvento(proximoEvento, ultimoEvento);
			
		}else{
			//espera um tempo e tenta enviar dinovo
			Evento eventoSenteMeio = new Evento();
			eventoSenteMeio.setTipo(TipoEvento.SENTE_MEIO);
			//eventoSenteMeio.setTempo()
			main.insereEvento(eventoSenteMeio, ultimoEvento);
		}
		
	}
	public void recebePacote (Pacote pacote){
		
	}
}
