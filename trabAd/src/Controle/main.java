package Controle;

import Rede.Canal;
import Rede.Estacao;
import Rede.Evento;
import Rede.Hub;
import Rede.TipoEvento;

/*
 * Trabalho de AD 2010-1
 * 
 * Grupo
 * a b c d e
 */
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//vamos adicionar as informacoes do sistema
		
		//int CAPACIDADE = x;
		
		//instancia as estacoes do sistema
		Estacao e1 = new Estacao();
		Estacao e2 = new Estacao();
		Estacao e3 = new Estacao();
		Estacao e4 = new Estacao();
		
		//canais
		Canal tx1 = new Canal();
		Canal tx2 = new Canal();
		Canal tx3 = new Canal();
		Canal tx4 = new Canal();
		Canal rx1 = new Canal();
		Canal rx2 = new Canal();
		Canal rx3 = new Canal();
		Canal rx4 = new Canal();
		
		//o hub
		Hub hub = new Hub();
		
		//adiciona as informacoes das estacoes
		e1.setId(1);
		e1.setRx(rx1);
		e1.setTx(tx1);
		
		e2.setId(2);
		e2.setRx(rx2);
		e2.setTx(tx2);
		
		e3.setId(3);
		e3.setRx(rx3);
		e3.setTx(tx3);
		
		e4.setId(4);
		e4.setRx(rx4);
		e4.setTx(tx4);
		
		//adiciona as informacoes dos canais
		tx1.setOcioso(true);
		tx1.setTamanho(100);
		
		rx1.setOcioso(true);
		rx1.setTamanho(100);
	
		tx2.setOcioso(true);
		tx2.setTamanho(80);
		
		rx2.setOcioso(true);
		rx2.setTamanho(80);
		
		tx3.setOcioso(true);
		tx3.setTamanho(60);
		
		rx3.setOcioso(true);
		rx3.setTamanho(60);

		tx4.setOcioso(true);
		tx4.setTamanho(40);
		
		rx4.setOcioso(true);
		rx4.setTamanho(40);
		
	/*Agora podemos iniciar nossa rede... 
	 * temos que pegar do usuario que cenario que ele quer simular
	*	Os cenarios possiveis estao na descricao do trabalho
	*	Para a rede iniciar temos que no tempo 0 ja gerar os pacotes,
	*	calcular as taxas de chegadas de cada estacao tb de acordo com o que foi escolheido,
	*	exponencial ou deterministica	
	*/
		//para começar a funcionar a parada, vou simular o cenario 1, depois a gente muda pra todos
		
		
		
		
	}
	//Insere o evento na posicao correta, retorna o ultimo evento
	public static Evento insereEvento(Evento evento, Evento ultimo)
	{
		Evento eventoTemp = new Evento();
		eventoTemp=ultimo;
		//caso exista algum evento na lista
		if(ultimo != null)
		{
			while(verificaAnterior(eventoTemp, evento))
			{
				//Vai pegando os eventos anteriores na lista
				eventoTemp = eventoTemp.getEventoAnterior();
			}
			//Como o evento que vms inserir tem tempo maior que o evento em q estamos na lista:
			eventoTemp.setProximoEvento(evento);
			evento.setEventoAnterior(eventoTemp);
		}
		//Caso contrario apenas retorna o evento como ultimo
		

		return evento;
		
	}
	public static boolean verificaAnterior(Evento eventoTemp, Evento eventoInsere)
	{
		boolean tempoMaior = false;
		if(eventoTemp.getTempo() > eventoInsere.getTempo())
		{
			//Se o tempo do ultimo evento é maior do que o que eu quero inserir, o evento que eu vou inserir vai ser inserido antes dele
			tempoMaior = true;
		}
		return tempoMaior;
	}
	//Metodo que manipula os eventos. Deve verificar o tipo do evento e manipula-lo de acordo com a especificaçao
	public static void trataEventos(Evento evento)
	{
		Evento evento2;
		if(evento.getTipo()==TipoEvento.SENTE_MEIO){
			//obtém a estação que está sentindo o meio
			Estacao estacao=evento.getQuadro().getPacote().getEstacao();
			if(estacao.getTx().getOcioso()){
				//se tx esta ocioso,cria um evento de transmitir quadro após o evento de sentir o meio
				evento2=new Evento();
				evento2.setTipo(TipoEvento.TRANSMITE_QUADRO);
				//100 é o tempo provisorio entre sentir o meio ocioso e a transmissao do quadro
				evento2.setTempo(evento.getTempo()+100);
			}else{
				//caso tx esteja ocupado,cria um novo evento sentindo o meio
				evento2=new Evento();
				evento2.setTipo(TipoEvento.SENTE_MEIO);
				//1 é o instante provisório após a primeira inspeção do meio
				evento2.setTempo(evento.getTempo()+1);
			}
			evento2.setEventoAnterior(evento);
			evento.setProximoEvento(evento2);
			main.insereEvento(evento2,evento);
		}
	}
	

}
