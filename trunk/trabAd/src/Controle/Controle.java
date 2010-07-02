package Controle;

import java.util.List;

import vo.EventoVo;
import Rede.Canal;
import Rede.Estacao;
import Rede.Evento;
import Rede.Hub;
import Rede.Pacote;
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
	
	public static EventoVo trataEventos(Evento evento, Evento ultimaTransmissao)
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
			
			//insere o evento2 na lista. precisa passar a ultimo evento executado que é o evento sente o meio que acabamos de executar
			insereEvento(evento2,evento);
			//Retorna o ultimo evento executado de sentir o meio
			eventoVo.setUltimoEvento(evento);
			eventoVo.setVerificaTransmissao(false);
			
			
			
			
		}else 	if(evento.getTipo() == TipoEvento.RECEBE_QUADRO){
			//acho que nao precisa mais do evento recebe quadro
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
		 }else if(evento.getTipo().equals(TipoEvento.TRANSMITE_QUADRO))
		 {
			 Estacao estacao = evento.getQuadro().getPacote().getEstacao();
			 Double tempoTransmissao = estacao.getTx().getTempoTransmissao();
			 //Cria um evento de retransmissao do hub
			 Evento eventoHub = new Evento();
			 eventoHub.setQuadro(evento.getQuadro());
			 //o evento sera realizado no tempo em q a estaçao começa a transmitir mais o tempo de propagaçao no seu tx
			 eventoHub.setTempo(evento.getTempo()+tempoTransmissao);
			 eventoHub.setTipo(TipoEvento.RETRANSMITE_QUADRO);
			 //insere o evento do hub na lista e passa o ultimo evento executado que foi o de transmitir o quadro
			 insereEvento(eventoHub,evento);
			 //Retorna o ultimo evento executado
			 eventoVo.setUltimoEvento(evento);
			 //true pois foi uma transmissao com sucesso
			 eventoVo.setVerificaTransmissao(true);
		 }else if(evento.getTipo().equals(TipoEvento.RETRANSMITE_QUADRO))
		 {
			 //Envia o quadro para o rx de tdas as estacoes
			 Hub hub = evento.getQuadro().getPacote().getEstacao().getHub();
			 List<Canal> listaCanais = hub.getListaCanais();
			 Evento eventoRecebeEstacao = new Evento();
			 for(Canal canal:(List<Canal>) listaCanais)
			 {
				 //Recupera o tempo de propagacao de cada canal e gera um evento de recepcao na estacao
				 //Eh necessario saber a estacao para a qual o hub esta enviando em caso. Nao tem mais como recuperar a estacao pelo quadro
				 eventoRecebeEstacao.setQuadro(evento.getQuadro());
				 eventoRecebeEstacao.setTempo(evento.getTempo()+canal.getTempoTransmissao());
				 eventoRecebeEstacao.setTipo(TipoEvento.RECEBE_QUADRO);
				 insereEvento(eventoRecebeEstacao,evento);
			 }
			 //Agora eu fiquei em duvida. GUardei o ultimo quadro transmitido pelo hub
			 eventoVo.setUltimoEvento(eventoRecebeEstacao);
			 eventoVo.setVerificaTransmissao(false);
		 }
		return eventoVo;
	}

}
