package Rede;



public class Estacao {
	int id;
	Canal tx;
	Canal rx;
	int taxaDeChegada;
	int pmf;
	int idUltimoQuadro;
	boolean RecebeuConfirmacaoUltimoQuadro;
	
	
	
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
	
	public int getIdUltimoQuadro(){
		return idUltimoQuadro;
	}
	
	public void setIdUltimoQuadro(int idUltimoQuadro){
		this.idUltimoQuadro = idUltimoQuadro;		
	}
	
	public boolean getRecebeuConfirmacaoUltimoQuadro(){
		return RecebeuConfirmacaoUltimoQuadro;
	}
	
	public void setRecebeuConfirmacaoUltimoQuadro(boolean RecebeuConfirmacaoUltimoQuadro){
		this.RecebeuConfirmacaoUltimoQuadro = RecebeuConfirmacaoUltimoQuadro;		
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
	
	//estacao recebe pacote no tempo x
	public void recebePacote (Pacote pacote, Double tempo){
		/*
		 * Algoritmo:
		 * Estacao recebe pacote
		 * pega o numero de quadros pela pmf
		 * vai gerando os quadros e enviando
		 * 
		 */
		Quadro quadro = new Quadro();
		quadro.setNumeroSequencia(0);
		quadro.setPacote(pacote);
		
	}
}
