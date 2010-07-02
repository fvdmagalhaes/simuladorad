package Controle;

import vo.EventoVo;
import Rede.Estacao;
import Rede.Evento;
import Rede.TipoEvento;
import Rede.Quadro;

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
	//Metodo que manipula os eventos. Deve verificar o tipo do evento e manipula-lo de acordo com a especificaçao.
	//Ele recebe o evento a ser executado, a ultima transmissao(ultima transmissao com sucesso ou sinal de reforço de colisao) e o ultimo evento executado
	//Agora o metodo retorna o ultimo evento executando por ele e um boolean que estará como true caso esse ultimo evento seja uma transmissao com sucesso ou reforço de colisao.
	
	public static EventoVo trataEventos(Evento evento, Evento ultimaTransmissao, Evento ultimoExecutado)
	{
		EventoVo eventoVo = null;
		Evento evento2 = null;
		if(evento.getTipo()==TipoEvento.SENTE_MEIO){
			//obtém a estação que está sentindo o meio
			Estacao estacao=evento.getQuadro().getPacote().getEstacao();
			if(estacao.getTx().getOcioso()){
				//se tx esta ocioso, verifica se já foram passados os 9,6 us = 9,6 x10^-6 s
				if(evento.getTempo() >= (ultimaTransmissao.getTempo()+0.0000096))
				{
					//caso já tenha passado cria um evento de transmissao
					evento2=new Evento();
					evento2.setTipo(TipoEvento.TRANSMITE_QUADRO);
					evento2.setQuadro(evento.getQuadro());
					//tempo de transmissao, inicia imediatamente
					//tempo do evento de transmissao e igual ao de sentir o meio
					evento2.setTempo(evento.getTempo());
				}else{
					//espera a contagem do tempo restante para passar os 9,6us e transmite imediatamente.
					//Entao vou criar um evento com o tempo atual + restante
					evento2=new Evento();
					evento2.setTipo(TipoEvento.TRANSMITE_QUADRO);
					evento2.setQuadro(evento.getQuadro());
					//evento de transmissao vai ocorrer 0,0000096 instante após a ultima transmissão
					evento2.setTempo(ultimaTransmissao.getTempo()+0.0000096);
					//evento2.setTempo(tempoAtual + (evento.getTempo()-(ultimaTransmissao.getTempo()+0.0000096)));
				}
			}else{
				//caso tx esteja ocupado,cria um novo evento sentindo o meio
				evento2=new Evento();
				evento2.setTipo(TipoEvento.SENTE_MEIO);
				//ele persiste sentindo o meio até a transmissao acabar. O tempo a ser sentido dinovo eh o tempo do proximo evento
				evento2.setTempo(evento.getProximoEvento().getTempo());
			}
			
			//insere o evento2 na lista. precisa passar a ultima transmissao.
			insereEvento(evento2,ultimoExecutado);
			//Retorna o ultimo evento executado de sentir o meio
			eventoVo.setUltimoEvento(evento);
			eventoVo.setVerificaTransmissao(false);
			
		}else 	if(evento.getTipo() == TipoEvento.RECEBE_QUADRO){
			//pega a estação que está recebendo o quadro
			Estacao estacao = evento.getQuadro().getPacote().getEstacao();
			
			//Verifica qual estação está recebendo o pacote para calcular o tempo
			if(estacao.getId() == '1'){
				
				/*
				 * TAMANHOQUADRO/CANAL + DISTANCIA/VELOCIDADE_PROPAGACAO

					Por exemplo, pra calcular o tempo que o Hub transmite alguma coisa pra estação 1:
					TamQuadro = 1000 bytes = 8000 bits
					Distancia = 80m = 0,008km

					tempo = 8000bits/10.000bps + 0,008km/ (5km/10^-6 segundos)
				 * */

			}else if(estacao.getId() == '2'){
				
			}else if(estacao.getId() == '3'){
				
			}else if(estacao.getId() == '4'){
				
			}
			
			/*
			 * 
			 * tem que verificar se essa estacao está pendente de confirmacao 
			 * de envio de quadro. 
			 */
		 }
		return eventoVo;
	}
	
}
