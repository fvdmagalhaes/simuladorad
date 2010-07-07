package Rede;

import Controle.Controle;

public class Estacao {
	int id;
	Canal tx;
	Canal rx;
	int taxaDeChegada;
	//numero de quadros em uma mensagem
	int pmf;
	Quadro ultimoQuadroEnviado;
	boolean RecebeuConfirmacaoUltimoQuadro;
	private static int ultimoId=-1;
	
	//Salva o hub ao qual a estacao esta ligada
	Hub hub;
	
	public Estacao(){
		ultimoId++;
		id=ultimoId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public int getTaxaDeChegada() {
		return taxaDeChegada;
	}
	public void setTaxaDeChegada(int taxaDeChegada) {
		this.taxaDeChegada = taxaDeChegada;
	}
		
	public int getPmf() {
		return pmf;
	}
	public void setPmf(int pmf) {
		this.pmf = pmf;
	}
	
	public Quadro getUltimoQuadroEnviado(){
		return ultimoQuadroEnviado;
	}
	
	public void setUltimoQuadroEnviado(Quadro ultimoQuadroEnviado){
		this.ultimoQuadroEnviado = ultimoQuadroEnviado;		
	}
	
	public boolean getRecebeuConfirmacaoUltimoQuadro(){
		return RecebeuConfirmacaoUltimoQuadro;
	}
	
	public void setRecebeuConfirmacaoUltimoQuadro(boolean RecebeuConfirmacaoUltimoQuadro){
		this.RecebeuConfirmacaoUltimoQuadro = RecebeuConfirmacaoUltimoQuadro;		
	}
	public void setHub(Hub hub)
	{
		this.hub = hub;
	}
	public Hub getHub()
	{
		return hub;
	}
	
	/*public void enviaPacote (Pacote pacote, Evento ultimoEvento){
		
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
			Controle.insereEvento(proximoEvento, ultimoEvento);
			
		}else{
			//espera um tempo e tenta enviar dinovo
			Evento eventoSenteMeio = new Evento();
			eventoSenteMeio.setTipo(TipoEvento.SENTE_MEIO);
			//eventoSenteMeio.setTempo()
			Controle.insereEvento(eventoSenteMeio, ultimoEvento);
		}
		
	}*/
	
//	estacao recebe pacote no tempo x
	public void recebePacote (Pacote pacote, Double tempo, Evento ultimoEvento){
		/*
		 * Algoritmo:
		 * Estacao recebe pacote
		 * pega o numero de quadros pela pmf
		 * vai gerando os quadros e enviando
		 * 
		 */
		//Controle controle = new Controle();Comentei porque como ela usa um metodo static achei que não precisasse ser instanciada,mas qualquer coisa descomentem
		Quadro quadro;
		Evento evento;
		for(int i=0;i<pmf;i++){
			quadro= new Quadro();
			quadro.setNumeroSequencia(i);
			quadro.setPacote(pacote);
//			cria um evento de enviar quadro para o hub para cada quadro
			evento = new Evento();
			evento.setQuadro(quadro);
			evento.setTipo(TipoEvento.TRANSMITE_QUADRO);
			evento.setTempo(tempo+i);
			//insere o evento na lista
			Controle.insereEvento(evento,ultimoEvento);
			
		}
		
		
	}
	
}
