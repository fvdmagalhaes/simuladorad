package Rede;

import java.util.ArrayList;
import java.util.List;

import Controle.Controle;
import Simulacao.Tam;
import Simulacao.Tap;
import Simulacao.ncm;

public class Estacao {
	int id;
	//Canais da esta��o
	Canal tx;
	Canal rx;
	//Taxa de chegada de mensagens na esta��o
	int taxaDeChegada;
	//numero total de colis�es da esta��o na simula��o
	int numeroColisoes;
	//Periodo que a esta��o estava com o meio ocupado, isto �, refor�o de colis�o, colis�o ou transmissao com sucesso
	Double tempoOcupada;
	//Tempo total de simula�ao da esta��o
	Double tempoSimulacao;
	//n�mero de quadro em uma mensagem
	int pmf;
	//True caso tenha recebido confirma��o do ultimo quadro enviado por ela
	boolean RecebeuConfirmacaoUltimoQuadro;
	private static int ultimoId=0;
	
	//false = determin�stica ; true = exponencial
	boolean distribuicaoChegadaPacotes;
	//Lista de pacotes da esta��o
	private List<Pacote> pacotes;
	//Guarda o ultimo quadro enviado
	Quadro ultimoQuadroEnviado;
	//Medidas
	Tap tap;
	Tam tam;
	ncm ncm;
	//n�mero de quadros que foram transmitidos com sucesso em toda a simula��o
	int numeroQuadrosTransmitidosSucesso;
	
	
	public Double getTempoOcupada() {
		return tempoOcupada;
	}
	public void setTempoOcupada(Double tempoOcupada) {
		this.tempoOcupada = tempoOcupada;
	}
	public Double getTempoSimulacao() {
		return tempoSimulacao;
	}
	public void setTempoSimulacao(Double tempoSimulacao) {
		this.tempoSimulacao = tempoSimulacao;
	}
	
	public Quadro getUltimoQuadroEnviado(){
		return ultimoQuadroEnviado;
	}

	public void setUltimoQuadroEnviado(Quadro ultimoQuadroEnviado){
		this.ultimoQuadroEnviado = ultimoQuadroEnviado;		
	}
	
	public Tap getTap() {
		return tap;
	}
	public void setTap(Tap tap) {
		this.tap = tap;
	}
	public ncm getNcm() {
		return ncm;
	}
	public void setNcm(ncm ncm) {
		this.ncm = ncm;
	}

	public Tam getTam() {
		return tam;
	}
	public void setTam(Tam tam) {
		this.tam = tam;
	}
	
	//Salva o hub ao qual a estacao esta ligada
	Hub hub;
	
	public Estacao(){
		//Incrementa o id da esta��o
		ultimoId++;
		id=ultimoId;
		pacotes=new ArrayList<Pacote>(0);
	}
	public int getNumColisoes() {
		return numeroColisoes;
	}
	public void setNumColisoes(int numeroColisoes) {
		this.numeroColisoes = numeroColisoes;
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
		//Quando for exponencial a taxa de chegada de pacotes � 1/taxa de chegada
		if(distribuicaoChegadaPacotes){
			this.taxaDeChegada = 1/taxaDeChegada;
		}else{
			//Quando � deterministica � igual a taxa
			this.taxaDeChegada = taxaDeChegada;
		}
	}
		
	public int getPmf() {
		return pmf;
	}
	public void setPmf(int pmf) {
		this.pmf = pmf;
	}
	
	public int getNumeroQuadrosTransmitidosSucesso() {
		return numeroQuadrosTransmitidosSucesso;
	}
	public void setNumeroQuadrosTransmitidosSucesso(int numeroQuadrosTransmitidosSucesso) {
		this.numeroQuadrosTransmitidosSucesso = numeroQuadrosTransmitidosSucesso;
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
	
	public void setDistribuicaoChegadapacotes(boolean distribuicao){
		this.distribuicaoChegadaPacotes = distribuicao;
	}
	
	public boolean getDistribuicaoChegadaPacotes(){
		return this.distribuicaoChegadaPacotes;
	}
	
	public Pacote getPacote(){
		if(pacotes.size()>0){
			return pacotes.get(0);
		}else{
			return null;
		}
	}
	
	public void removerPacote(){
		pacotes.remove(0);
	}
	
	public void addPacote(Pacote pacote){
		pacotes.add(pacote);
	}
	
//	estacao recebe pacote no tempo x
	public void recebePacote (Pacote pacote, Double tempo, Evento ultimoEvento){
		/*
		 * Algoritmo:
		 * Estacao recebe pacote
		 * pega o numero de quadros pela pmf
		 * vai gerando os quadros e enviando
		 * 
		 */
		
		Quadro quadro;
		Evento evento;
		
		//a estacao gera o primeiro quadro da sequencia de quadros. Assim que a estacao confirmar o envio deste quadro, gera o proximo
		pacote.setSequenciaEnviada(1);
		
		
		quadro= new Quadro();
		quadro.setNumeroSequencia(1);
		quadro.setPacote(pacote);
		quadro.setQuadroConfirmado(false);
		quadro.setTap(tempo);
		quadro.setTam(tempo);
		//Inicialmente temos 0 colisoes de quadros desse pacote
		pacote.setNcm(0);
		//cria um evento de sentir o meio para tentar enviar o quadro
		evento = new Evento();
		evento.setQuadro(quadro);
		evento.setTipo(TipoEvento.SENTE_MEIO);
		evento.setTempo(tempo);
		evento.setPacote(pacote);
		evento.setEstacao(pacote.getEstacao());	
		
		//insere o evento na lista
		Controle.insereEvento(evento,ultimoEvento);		
				
	}
}
