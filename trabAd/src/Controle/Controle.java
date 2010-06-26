package Controle;

import Rede.Estacao;
import Rede.Evento;
import Rede.TipoEvento;

public class Controle {
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
			insereEvento(evento2,evento);
		}
	}
	
}
