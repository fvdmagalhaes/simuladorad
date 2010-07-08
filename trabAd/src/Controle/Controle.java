package Controle;

import java.util.List;

import vo.EventoVo;
import Rede.Canal;
import Rede.Estacao;
import Rede.Evento;
import Rede.Hub;
import Rede.Quadro;
import Rede.TipoEvento;

public class Controle {
	//Insere o evento na posicao correta, retorna o ultimo evento
	public static Evento insereEvento(Evento evento, Evento ultimo)
	{
		/*
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
		*/
		//Caso contrario apenas retorna o evento como ultimo
		
		//evento=a ser inserido
		//ultimo=evento de refrencia
		Evento anterior,posterior;
		
		if(evento.getTempo()>=ultimo.getTempo()){
			anterior=ultimo;
			posterior=ultimo.getProximoEvento();
		}else{
			anterior=ultimo.getEventoAnterior();
			posterior=ultimo;
		}
		
		do{
			if(posterior==null){
				ultimo.setProximoEvento(evento);
				evento.setEventoAnterior(ultimo);
			}else	if(anterior==null){
						evento.setProximoEvento(ultimo);
						ultimo.setEventoAnterior(evento);
			}else	if(anterior!=null && anterior.getTempo()>=evento.getTempo()){
							posterior=anterior;
							anterior=anterior.getEventoAnterior();
					}else	if(posterior!=null && evento.getTempo()>posterior.getTempo()){
									anterior=posterior;
									posterior=posterior.getProximoEvento();
							}
		}while(!(anterior==null || posterior==null || (anterior.getTempo()<=evento.getTempo() &&
				evento.getTempo()<=posterior.getTempo())));
		
			if(anterior==null){
				evento.setEventoAnterior(anterior);
				posterior.setEventoAnterior(evento);
				evento.setProximoEvento(posterior);
			}else	if(posterior==null){
				anterior.setProximoEvento(evento);
				evento.setEventoAnterior(anterior);
				evento.setProximoEvento(posterior);
			}else{
				evento.setEventoAnterior(anterior);
				anterior.setProximoEvento(evento);
				posterior.setEventoAnterior(evento);
				evento.setProximoEvento(posterior);
			}
	
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
		EventoVo eventoVo = new EventoVo();
		
		System.out.println("---------------------------------------");
		if(evento.getTipo()==TipoEvento.RETRANSMITE_QUADRO)
			System.out.println("Evento a ser tratado no tempo " + evento.getTempo()+ ": HUB Retransmite o quadro");
		else
			System.out.println("Evento a ser tratado no tempo " + evento.getTempo()+ ": Estacao " + evento.getEstacao().getId() + " " + evento.getTipo());
		
		System.out.println("\n");
		System.out.println("Existem esses eventos para serem tratados:");
		System.out.println("\n");
		
		Evento ev = evento;
		
		while(ev != null){
			ev = ev.getProximoEvento();
			
			if(ev != null){
				if(ev.getTipo()==TipoEvento.RETRANSMITE_QUADRO)
					System.out.println("Evento a ser tratado no tempo " + ev.getTempo()+ ": HUB Retransmite o quadro");
				else
					System.out.println("Evento a ser tratado no tempo " + ev.getTempo()+ ": Estacao " + ev.getEstacao().getId() + " " + ev.getTipo());

			}
		}
		
		if(evento.getTipo()==TipoEvento.SENTE_MEIO){
			Evento evento2 = null;
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
			//O evento recebe quadro soh sera ocasionado quando um hub enviar um quadro para tdas as estacoes.
			//Logo nao eh valido recuperar a estacao pelo quadro. Deve recupera-la pelo metodo get estacao do evento que foi preenchido quando
			//criamos o evento quando o hub enviou os quadros
			/*
			 * 
			 * tem que verificar se essa estacao está pendente de confirmacao 
			 * de envio de quadro. 
			 * e verificar se o quadro que ela recebeu é essa confirmacao ou nao..
			 * 
			 */
			Estacao estacao = evento.getEstacao();
			//verifica se o quadro recebido eh o ultimo que foi enviado por ela
			if(evento.getPacote().getSequenciaEnviada() == evento.getQuadro().getNumeroSequencia())
			{
				estacao.setUltimoQuadroEnviado(evento.getQuadro());
				
				//retorna o ultimo evento executado
				eventoVo.setUltimoEvento(evento);
				//o evento nao eh uma transmissao com sucesso nem reforco de colisao
				eventoVo.setVerificaTransmissao(false);
				
				int numeroSequencia = evento.getQuadro().getNumeroSequencia()+1;
				
				if(numeroSequencia > estacao.getPmf()){
					//agora tem que gerar o proximo quadro que vai ser enviado...
					Quadro quadro;
					Evento eventoQ;
					quadro= new Quadro();
					//a estacao gera o primeiro quadro da sequencia de quadros. Assim que a estacao confirmar o envio deste quadro, gera o proximo
					evento.getPacote().setSequenciaEnviada(numeroSequencia);
					
					quadro.setNumeroSequencia(numeroSequencia);
					quadro.setPacote(evento.getPacote());
					//cria um evento de enviar quadro para o hub para cada quadro
					eventoQ = new Evento();
					eventoQ.setQuadro(quadro);
					eventoQ.setTipo(TipoEvento.TRANSMITE_QUADRO);
					eventoQ.setTempo(evento.getTempo());
					eventoQ.setEstacao(estacao);

					//insere o evento na lista
					insereEvento(eventoQ,evento);	
				}

			}else{
				//caso nao seja deve dar colisao
			}
			
		 }else if(evento.getTipo().equals(TipoEvento.TRANSMITE_QUADRO))
		 {
			 Estacao estacao = evento.getQuadro().getPacote().getEstacao();
			 Double tempoTransmissao = estacao.getTx().getTempoTransmissao();
			 //Cria um evento de retransmissao do hub
			 Evento eventoHub = new Evento();
			 eventoHub.setQuadro(evento.getQuadro());
			 eventoHub.setEstacao(estacao);
			 //o evento sera realizado no tempo em q a estaçao começa a transmitir mais o tempo de propagaçao no seu tx
			 eventoHub.setTempo(evento.getTempo()+tempoTransmissao);
			 eventoHub.setTipo(TipoEvento.RETRANSMITE_QUADRO);
			 //insere o evento do hub na lista e passa o ultimo evento executado que foi o de transmitir o quadro
			 insereEvento(eventoHub,evento);
			 //Retorna o ultimo evento executado
			 eventoVo.setUltimoEvento(evento);
			 //true pois foi uma transmissao com sucesso
			 eventoVo.setVerificaTransmissao(true);
			 //Seta esse quadro como o ultimo enviado pela estacao
			 estacao.setUltimoQuadroEnviado(evento.getQuadro());
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
				 eventoRecebeEstacao.setEstacao(evento.getEstacao());
				 eventoRecebeEstacao.setPacote(evento.getQuadro().getPacote());
				 eventoRecebeEstacao.setTempo(evento.getTempo()+canal.getTempoTransmissao());
				 eventoRecebeEstacao.setTipo(TipoEvento.RECEBE_QUADRO);
				 eventoRecebeEstacao.setEstacao(canal.getEstacao());
				 insereEvento(eventoRecebeEstacao,evento);
			 }
			 //Agora eu fiquei em duvida. GUardei o ultimo quadro transmitido pelo hub
			 eventoVo.setUltimoEvento(eventoRecebeEstacao);
			 eventoVo.setVerificaTransmissao(false);
		 }else if(evento.getTipo().equals(TipoEvento.RECEBE_PACOTE)){
			 evento.getEstacao().recebePacote(evento.getPacote(), evento.getTempo(), evento);
			 Evento receberProximo=new Evento();
			 receberProximo.setTipo(TipoEvento.RECEBE_PACOTE);
			 receberProximo.setEstacao(evento.getEstacao());
			 receberProximo.setTempo(evento.getTempo()+evento.getEstacao().getTaxaDeChegada());
			 receberProximo.setEstacao(evento.getEstacao());
			 
			 Controle.insereEvento(receberProximo,evento);
		 }
		return eventoVo;
	}

}
