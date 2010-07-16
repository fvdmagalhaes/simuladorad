package Controle;

import java.util.List;
import java.util.Random;

import javax.naming.BinaryRefAddr;

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
		
		Evento eventoTemp = new Evento();
		eventoTemp=ultimo;
		//caso exista algum evento na lista
		if(ultimo != null)
		{
			//O evento soh podera ter o tempo maior ou igual do q o ultimo, nunca menor. Pois o ultimo evento eh o ultimo evento que foi tratado
			while(verificaProximo(eventoTemp, evento))
			{
				//Vai pegando os proximos eventos na lista
				if(eventoTemp.getProximoEvento() != null)
				{
					eventoTemp = eventoTemp.getProximoEvento();
				}else{
					break;
				}
			}
			//Como o evento que vms inserir tem tempo maior que o evento em q estamos na lista:
			eventoTemp.setProximoEvento(evento);
			evento.setEventoAnterior(eventoTemp);
		}
		return evento;
	
		//Caso contrario apenas retorna o evento como ultimo
		
		//evento=a ser inserido
		//ultimo=evento de refrencia
		/*
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
		*/
		
	}
	public static boolean verificaProximo(Evento eventoTemp, Evento eventoInsere)
	{
		boolean tempoMaior = false;
		if(eventoTemp != null)
		{
			if(eventoTemp.getTempo() < eventoInsere.getTempo())
			{
				//Se o tempo do ultimo evento é menor do que o que eu quero inserir, o evento que eu vou inserir vai ser inserido depois dele
				tempoMaior = true;
			}
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
				//caso ainda n tenha tido a ultima transmissao transmite imediatamente
				if(ultimaTransmissao.getQuadro() == null || evento.getTempo() >= (ultimaTransmissao.getTempo()+0.0000096))
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
				evento2.setTempo(evento.getProximoEvento().getTempo()+1);
			}
			
			//insere o evento2 na lista. precisa passar o ultimo evento executado que é o evento sente o meio que acabamos de executar
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
			
			if(estacao.getUltimoQuadroEnviado().equals(evento.getQuadro()) && evento.getPacote().getSequenciaEnviada() == evento.getQuadro().getNumeroSequencia())
			{			
				int numeroSequencia = evento.getQuadro().getNumeroSequencia()+1;
				
				if(numeroSequencia < estacao.getPmf()){
					//agora tem que gerar o proximo quadro que vai  ser enviado...
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
			

			//Agora o canal rx da estacao esta ocioso
			estacao.getRx().setOcioso(true);
			
			//retorna o ultimo evento executado
			eventoVo.setUltimoEvento(evento);
			//o evento eh uma transmissao com sucesso
			eventoVo.setVerificaTransmissao(true);
			
		 }else if(evento.getTipo().equals(TipoEvento.TRANSMITE_QUADRO))
		 {
			 Estacao estacao = evento.getQuadro().getPacote().getEstacao();
			 EventoVo eventovoAtrazo = new EventoVo();
			 //Caso o canal da estacao esteja ocioso
			 if(estacao.getTx().getOcioso())
			 {
				 estacao.getTx().setOcioso(false);
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
				 eventoVo.setVerificaTransmissao(false);
				 //Seta esse quadro como o ultimo enviado pela estacao
				 estacao.setUltimoQuadroEnviado(evento.getQuadro());
			 }else{
				 System.out.println("O quadro"+evento.getQuadro().getNumeroSequencia()+"da estacao"+estacao.getId()+"foi perdido pois o canal estava ocupado");
				 //aborta a transmissão em andamento, transmite um sinal de reforço de colisão por 3,2 ?s (equivalente a tx de 32 bits) e, após este atraso, escolhe um instante
				 //aleatório para retransmissão, segundo o algoritmo Binary Backoff 
				 //Apenas insere no eventovo o a ultima transmissao como um evento de reforço de colisao para que isso seja verificado
				 //no sente o meio. Cria um novo evento de transmissao com o tempo igual ao tempo de atrazo do reforço + tempo aleatorio
				 
				 double atrazo = 0.0;
				 Evento reforcoColisao = new Evento();
				 reforcoColisao.setTipo(TipoEvento.REFORCO_COLISAO);
				 reforcoColisao.setEstacao(estacao);
				 reforcoColisao.setQuadro(evento.getQuadro());
				 //3,2^10-6
				 reforcoColisao.setTempo(evento.getTempo()+0.0000032);
				 eventoVo.setUltimoEvento(reforcoColisao);
				 eventoVo.setVerificaTransmissao(true);
				 
				 eventovoAtrazo = binaryBackof(estacao);
				//Caso o quadro nao tenha sido descartado
				 if(!eventovoAtrazo.getDescartado())
				 {
					 Evento transmiteQuadro = new Evento();
					 transmiteQuadro.setEstacao(estacao);
					 transmiteQuadro.setPacote(evento.getQuadro().getPacote());
					 transmiteQuadro.setQuadro(evento.getQuadro());
					 transmiteQuadro.setTipo(TipoEvento.TRANSMITE_QUADRO);
					 transmiteQuadro.setTempo(evento.getTempo()+0.0000032+eventovoAtrazo.getAtrazo());
					
				 }else{
					 //descarta o quadro
					 System.out.println("quadro"+evento.getQuadro().getId()+"descartado");
				 }
				 estacao.setNumColisoes(estacao.getNumColisoes()+1);
			 }
		 }else if(evento.getTipo().equals(TipoEvento.RETRANSMITE_QUADRO))
		 {
			 //Envia o quadro para o rx de tdas as estacoes
			 Hub hub = evento.getQuadro().getPacote().getEstacao().getHub();
			 List<Canal> listaCanais = hub.getListaCanais();
			 Evento eventoRecebeEstacao = new Evento();
			 for(Canal canal:(List<Canal>) listaCanais)
			 {
				 System.out.println("estacao que vai receber o quadro:  " + canal.getEstacao().getId());
				 //Recupera o tempo de propagacao de cada canal e gera um evento de recepcao na estacao
				 //Eh necessario saber a estacao para a qual o hub esta enviando em caso. Nao tem mais como recuperar a estacao pelo quadro
				 if(canal.getOcioso())
				 {
					 eventoRecebeEstacao.setQuadro(evento.getQuadro());
					 eventoRecebeEstacao.setEstacao(canal.getEstacao());
					 eventoRecebeEstacao.setPacote(evento.getQuadro().getPacote());
					 eventoRecebeEstacao.setTempo(evento.getTempo() + canal.getTempoTransmissao());
					 eventoRecebeEstacao.setTipo(TipoEvento.RECEBE_QUADRO);
					 //O canal rx agora está ocupado
					 canal.setOcioso(false);
					 //O tx agora esta ocioso
					 canal.getEstacao().getTx().setOcioso(true);
					 insereEvento(eventoRecebeEstacao,evento);
					 eventoVo.setUltimoEvento(eventoRecebeEstacao);
					 eventoVo.setVerificaTransmissao(false);
				 }else{
					 System.out.println("o pacote"+evento.getQuadro().getPacote().getSequenciaEnviada()+"foi perdido pois o canal"+canal.getEstacao().getId()+"estava ocupado");
					 double atrazo = 0.0;
					 Estacao estacao = canal.getEstacao();
					 //Transmite um reforco de colisao
					 EventoVo eventovoAtrazo = new EventoVo();
					 Evento reforcoColisao = new Evento();
					 reforcoColisao.setTipo(TipoEvento.REFORCO_COLISAO);
					 reforcoColisao.setEstacao(canal.getEstacao());
					 reforcoColisao.setQuadro(evento.getQuadro());
					 //3,2^10-6
					 reforcoColisao.setTempo(evento.getTempo()+0.0000032);
					 eventoVo.setUltimoEvento(reforcoColisao);
					 eventoVo.setVerificaTransmissao(true);
					 
					 //Tenta renviar o quadro apos esse atrazo mais um tempo aleatorio
					 eventovoAtrazo = binaryBackof(canal.getEstacao());
					//Caso o quadro nao tenha sido descartado
					 if(!eventovoAtrazo.getDescartado())
					 {
						 Evento transmiteQuadro = new Evento();
						 transmiteQuadro.setEstacao(canal.getEstacao());
						 transmiteQuadro.setPacote(evento.getQuadro().getPacote());
						 transmiteQuadro.setQuadro(evento.getQuadro());
						 transmiteQuadro.setTipo(TipoEvento.TRANSMITE_QUADRO);
						 transmiteQuadro.setTempo(evento.getTempo()+0.0000032+eventovoAtrazo.getAtrazo());
						
					 }else{
						 //descarta o quadro
						 System.out.println("quadro"+evento.getQuadro().getId()+"descartado");
					 }
					 estacao.setNumColisoes(estacao.getNumColisoes()+1);
				 }
			 }
			
			 
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
	
	public static EventoVo binaryBackof(Estacao estacao)
	{
		/*Para dar um exemplo, imagine que o quadro na estação PC1 já sofreu 1 colisão. Quando este
quadro começa sua segunda tentativa de transmissão, ele colide com a primeira tentativa de
transmissão de um quadro da estação PC2. Cada uma das estações ao detectar colisão gera
o reforço de colisão e escolhe o próximo instante de retransmissão segundo o algoritmo de
colisão.
A estação PC1, com k=2, irá escolher aleatoriamente um atraso dentre as seguintes quatro
possibilidades:
• 0 ?s (tenta transmitir imediatamente), com probabilidade ¼
• 51,2 ?s, com probabilidade ¼
• 102,4 ?s, com probabilidade ¼
• 153,6 ?s, com probabilidade ¼
A estação PC2, por seu lado, só sofreu uma colisão, e irá escolher aleatoriamente um atraso
dentre os seguintes:
• 0 ?s (tenta transmitir imediatamente), com probabilidade ½
• 51,2 ?s, com probabilidade ½*/
		EventoVo eventovo = new EventoVo();
		int numColisoes = estacao.getNumColisoes();
		double atrazo = 0.0;
		boolean descartaQuadro = false;
		Random random = new Random();
		int aleatorio = 0;
		//51,2 us = 51,2x10-6, tempo do intervalo
		double tempo = 0.0000512;
		if(numColisoes <= 10)
		{
			//Após k colisões, a estação escolhe aleatoriamente um atraso entre 0 e 2k - 1 intervalos para retransmitir.
			//escolhe um numero aleatorio entre 0 e o numero de colisoes
			aleatorio = random.nextInt(numColisoes+1);
			atrazo = Math.pow(2,aleatorio);
			atrazo--;
			atrazo=atrazo*tempo;
		}else{
			if(numColisoes < 16)
			{
				//Quando o número de colisões sobe além de 10, continua-se a usar k=10
				aleatorio = random.nextInt(11);
				atrazo = Math.pow(2,aleatorio);
				atrazo--;
			}else{
				//Se o número de colisões consecutivas chega a 16, o quadro é imediatamente descartado após a detecção da 16ª colisão.
				descartaQuadro = true;
				//A submissão do quadro descartado para uma nova transmissão é uma tarefa relegada a protocolos de nível superior, que não serão simulados neste trabalho.
			}
		}
		eventovo.setAtrazo(atrazo);
		eventovo.setDescartado(descartaQuadro);
		return eventovo;
		
	}

}
