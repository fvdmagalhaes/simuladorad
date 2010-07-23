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
		//O evento temp começa igual ao ultimo evento executado
		eventoTemp=ultimo;
		boolean naoexistemaior = false;
		//caso exista algum evento na lista
		if(ultimo != null)
		{
			
			//O evento só podera ter o tempo maior ou igual do q o ultimo, nunca menor. Pois o ultimo evento eh o ultimo evento que foi tratado
			while(verificaProximo(eventoTemp, evento))
			{
				//Vai pegando os proximos eventos na lista enquanto o tempo do evento em questao nao for menor que o proximo
				if(eventoTemp.getProximoEvento() != null)
				{
					//O evento temp agora recebe o proximo
					eventoTemp = eventoTemp.getProximoEvento();
				}else{
					//Caso nao haja mais proximos eventos significa que nao existe nenhum evento com tempo maior que o evento que vamos inserir
					//Logo o evento que vamos inserir é o ultimo e o proximo evento do ultimo evento passa a ser ele
					eventoTemp.setProximoEvento(evento);
					//O evento anterior é o evento que estava em ultimo na lista
					evento.setEventoAnterior(eventoTemp);
					naoexistemaior = true;
					break;
				}
			}
			//Como o evento que vms inserir tem tempo menor que o evento em que estamos na lista:
			if(!naoexistemaior)
			{
				//O proximo evento vai ser o evento temp que tem o tempo maior do que o dele
				evento.setProximoEvento(eventoTemp);
				//o evento anterior do evento passa a ser o anterior do ultimo pois o novo evento será posicionado no meio deles
				evento.setEventoAnterior(eventoTemp.getEventoAnterior());
				//O anterior do evento temp passa a ter o proximo evento como o novo
				eventoTemp.getEventoAnterior().setProximoEvento(evento);
				//O evento anterior do tempo agora é o evento que estamos inserindo
				eventoTemp.setEventoAnterior(evento);
				
			}
		}
		return evento;
	
		
		
	}
	public static boolean verificaProximo(Evento eventoTemp, Evento eventoInsere)
	{
		boolean tempoMaior = false;
		if(eventoTemp != null)
		{
			//verifica se o tempo a ser inserido é maior ou igual que o que estamos percorrendo
			if(eventoTemp.getTempo() <= eventoInsere.getTempo())
			{
				//Se o tempo do ultimo evento é menor do que o que eu quero inserir, o evento que eu vou inserir vai ser inserido depois dele
				tempoMaior = true;
			}
		}
		return tempoMaior;
	}
	//Metodo que manipula os eventos. Deve verificar o tipo do evento e manipula-lo de acordo com a especificaçao.
	//Ele recebe o evento a ser executado e a ultima transmissao(ultima transmissao com sucesso ou sinal de reforço de colisao)
	//O metodo retorna o ultimo evento executando por ele e um boolean que estará como true caso esse ultimo evento seja uma transmissao com sucesso ou reforço de colisao.
	
	public static EventoVo trataEventos(Evento evento, Evento ultimaTransmissao, Double faseTransiente)
	{
		EventoVo eventoVo = new EventoVo();
		
		Saida.saida="---------------------------------------";
		Saida.escreve();
		Saida.saida="\n";
		Saida.escreve();
		if(evento.getTipo()==TipoEvento.RETRANSMITE_QUADRO){
			Saida.saida="Evento a ser tratado no tempo " + evento.getTempo()+ ": HUB Retransmite o quadro";
			Saida.escreve();
			Saida.saida="\n";
			Saida.escreve();
		}else{
			Saida.saida="Evento a ser tratado no tempo " + evento.getTempo()+ ": Estacao " + evento.getEstacao().getId() + " " + evento.getTipo();
			Saida.escreve();
			Saida.saida="\n";
			Saida.escreve();
		}
		
		Saida.saida="\n";Saida.escreve();
		Saida.saida="\n";Saida.escreve();
		Saida.saida="Existem esses eventos para serem tratados:";Saida.escreve();
		Saida.saida="\n";Saida.escreve();
		Saida.saida="\n";Saida.escreve();
		Saida.saida="\n";Saida.escreve();
		
		Evento ev = evento;
		
		while(ev != null){
			ev = ev.getProximoEvento();
			
			if(ev != null){
				if(ev.getTipo()==TipoEvento.RETRANSMITE_QUADRO){
					Saida.saida="Evento a ser tratado no tempo " + ev.getTempo()+ ": HUB Retransmite o quadro";
					Saida.escreve();
					Saida.saida="\n";
					Saida.escreve();
				}else{
					Saida.saida="Evento a ser tratado no tempo " + ev.getTempo()+ ": Estacao " + ev.getEstacao().getId() + " " + ev.getTipo();
					Saida.escreve();
					Saida.saida="\n";
					Saida.escreve();
				}
			}
		}
		
		if(evento.getTipo()==TipoEvento.SENTE_MEIO){
			Evento evento2 = null;
			//obtém a estação que está sentindo o meio
			Estacao estacao=evento.getQuadro().getPacote().getEstacao();
			
			//Caso o canal de transmissao da estaçao esteja ocioso
			if(estacao.getTx().getOcioso()){
				
				//Verifica se já foram passados os 9,6 us = 9,6 x10^-3 ms
				//Caso a estacao ainda nao tenha transmitido nenhum quadro transmite imediatamente
				if(ultimaTransmissao.getQuadro() == null || evento.getTempo() >= (ultimaTransmissao.getTempo()+0.0096))
				{
					//caso já tenha passado cria um evento de transmissao
					evento2=new Evento();
					evento2.setTipo(TipoEvento.TRANSMITE_QUADRO);
					evento2.setQuadro(evento.getQuadro());
					evento2.setEstacao(evento.getEstacao());
					evento2.setPacote(evento.getPacote());
					//tempo de transmissao, inicia imediatamente
					//tempo do evento de transmissao e igual ao de sentir o meio
					evento2.setTempo(evento.getTempo());
				}else{
					//espera a contagem do tempo restante para passar os 9,6us e transmite imediatamente.
					//Entao vamos criar um evento com o tempo atual + restante
					evento2=new Evento();
					evento2.setTipo(TipoEvento.TRANSMITE_QUADRO);
					evento2.setQuadro(evento.getQuadro());
					evento2.setEstacao(evento.getEstacao());
					evento2.setPacote(evento.getPacote());
					//evento de transmissao vai ocorrer 0,0000096 instante após a ultima transmissão
					evento2.setTempo(ultimaTransmissao.getTempo()+0.0096);
					
				}
			}else{
				//caso tx esteja ocupado,cria um novo evento sentindo o meio
				evento2=new Evento();
				evento2.setTipo(TipoEvento.SENTE_MEIO);
				evento2.setEstacao(evento.getEstacao());
				evento2.setPacote(evento.getPacote());
				evento2.setQuadro(evento.getQuadro());
				//ele persiste sentindo o meio até a transmissao acabar. O tempo a ser sentido novamente eh o tempo do proximo evento
				evento2.setTempo(evento.getProximoEvento().getTempo()+1);
			}
			
			//insere o evento2 na lista. Precisa passar o ultimo evento executado que é o evento sente o meio que acabamos de executar
			insereEvento(evento2,evento);
			//Retorna o ultimo evento executado de sentir o meio
			eventoVo.setUltimoEvento(evento);
			//Este atributo eh setado como false pois o ultimo evento nao foi transmissao com sucesso nem reforco de colisao
			eventoVo.setVerificaTransmissao(false);
			
			
			
			
		}else 	if(evento.getTipo() == TipoEvento.RECEBE_QUADRO){
			//O evento recebe quadro soh sera ocasionado quando um hub enviar um quadro para todas as estacoes.
			
			Estacao estacao = evento.getEstacao();
			
			//verifica se o quadro recebido eh o ultimo que foi enviado por ela
			if(estacao.getUltimoQuadroEnviado() != null && estacao.getUltimoQuadroEnviado().equals(evento.getQuadro()) && evento.getPacote().getSequenciaEnviada() == evento.getQuadro().getNumeroSequencia() && evento.getEstacao().equals(evento.getPacote().getEstacao()))
			{
				//Verifica se no tempo em que recebeu o quadro a fase transiente ja tinha acabado
				if(evento.getTempo() >= faseTransiente)
				{
					estacao.setNumeroQuadrosTransmitidosSucesso(estacao.getNumeroQuadrosTransmitidosSucesso()+1);

					//Como o quadro foi enviado com sucesso vamos adicionar o tempo de envio do quadro
					
					//Tempo ocupado do quadro é o tempo atual menos o tempo em que começou a transmissão
					Double tempoOcupado = evento.getTempo()-evento.getQuadro().getTempoOcupado();
					if(estacao.getTempoOcupada() != null)
					{
						estacao.setTempoOcupada(estacao.getTempoOcupada()+tempoOcupado);
					}else{
						estacao.setTempoOcupada(tempoOcupado);
					}
				}
//				Como o quadro foi enviado com sucesso agora podemos colher o tap
				evento.getEstacao().getTap().adicionaMedida(evento.getQuadro().getTap(), evento.getTempo(),faseTransiente);
				
				//O numero sequencia guarda quantos quadros ja foram enviados. Vamos incrementa-lo e enviar o proximo
				int numeroSequencia = evento.getQuadro().getNumeroSequencia()+1;
				
//				A estacao recebeu o ultimo quadro enviado
				estacao.setRecebeuConfirmacaoUltimoQuadro(true);
				//O quadro foi confirmado
				evento.getQuadro().setQuadroConfirmado(true);
				
				//Caso ainda hajam quadros da mensagem para ser gerados vamos gerar um novo quadro
				if(numeroSequencia < estacao.getPmf()){
					
					//Vai gerar um novo quadro do pacote entao vamos apenas somar o tam
					//Sera somado o tempo de envio do quadro. Isto eh, o tempo em que ele eh considerado para a trnamissao ateh a transmissa com sucesso
					Double tam = evento.getTempo() - evento.getQuadro().getTam();
					if(evento.getQuadro().getPacote().getTam()!=null)
					{
						evento.getQuadro().getPacote().setTam(evento.getQuadro().getPacote().getTam()+tam);
					}else{
						evento.getQuadro().getPacote().setTam(tam);
					}
					
					//Vamos gerar o proximo quadro a ser enviado
					Quadro quadro = new Quadro();
					Evento eventoQ; 
					//a estacao gera o primeiro quadro da sequencia de quadros. Assim que a estacao confirmar o envio deste quadro, gera o proximo
					evento.getPacote().setSequenciaEnviada(numeroSequencia);
					
					//Cria o novo quadro com o numero de sequencia incrementado
					quadro.setNumeroSequencia(numeroSequencia);
					quadro.setPacote(evento.getPacote());
					quadro.setTap(evento.getTempo());
					quadro.setTam(evento.getTempo());

					//Cria um novo evento de sente o meio e adiciona o quadro criado a ele
					eventoQ = new Evento();
					eventoQ.setQuadro(quadro);
					eventoQ.setTipo(TipoEvento.SENTE_MEIO);
					eventoQ.setTempo(evento.getTempo());
					eventoQ.setEstacao(estacao);
					eventoQ.setPacote(quadro.getPacote());

					//insere o evento na lista
					insereEvento(eventoQ,evento);	
				}else{
					//Caso contrario todos os quadros do pacote ja foram enviados
					
					//Podemos adicionar ao tam uma nova iteracao com o tempo do momento em que o primeiro quadro e criado ate quando o ultimo e
					//considerado para transmissao
					if(evento.getQuadro().getPacote().getTam()!=null)
					{
						evento.getEstacao().getTam().adicionaMedida(evento.getQuadro().getPacote().getTam()+evento.getQuadro().getTam(),evento.getTempo(),faseTransiente);
					}else{
						evento.getEstacao().getTam().adicionaMedida(evento.getQuadro().getTam(), evento.getTempo(),faseTransiente);
					}
					//Enviamos todo o pacote, portanto vamos adicionar a rodada do numero de colisoes do pacote sobre o numero de quadros no pacote que eh a pmf
					
					evento.getEstacao().getNcm().adicionaMedida(evento.getQuadro().getPacote().getNcm()/estacao.getPmf(), evento.getTempo(),faseTransiente);
				}
				
//				retorna o ultimo evento executado
				eventoVo.setUltimoEvento(evento);
				//o evento é uma transmissao com sucesso
				eventoVo.setVerificaTransmissao(true);

			}else{
	
				//Caso a estacao ja tenha enviado um quadro e ainda nao recebeu sua confirmacao deve dar colisao
				if(!estacao.getRecebeuConfirmacaoUltimoQuadro())
				{
//					Tempo ocupado do quadro é o tempo atual menos o tempo em que começou a transmissão
					Double tempoOcupado = evento.getTempo()-evento.getQuadro().getTempoOcupado();
//					Como deu colisao devemos somar ao tempo ocupado da estacao o tempo até o quadro colidir. 
					if(evento.getTempo()>=faseTransiente)
					{
						if(estacao.getTempoOcupada() != null)
						{
							estacao.setTempoOcupada(estacao.getTempoOcupada()+tempoOcupado);
						}else{
							estacao.setTempoOcupada(tempoOcupado);
						}
					}
//					Para que o quadro possa ser enviado dinovo
					estacao.setRecebeuConfirmacaoUltimoQuadro(true);
					
					//É criado um evento de reforço de colisão
					 EventoVo eventovoAtrazo = new EventoVo();
					 Evento reforcoColisao = new Evento();
					 reforcoColisao.setTipo(TipoEvento.REFORCO_COLISAO);
					 reforcoColisao.setEstacao(estacao);
					 reforcoColisao.setQuadro(estacao.getUltimoQuadroEnviado());
					 //3,2^10-3ms
					 reforcoColisao.setTempo(evento.getTempo()+0.0032);
					 
					 //Devemos adicionar o tempo de reforco ao tempo ocupado da estacao
					 if(evento.getTempo()>=faseTransiente)
					 {
						 if(estacao.getTempoOcupada() != null)
						{
								
							 estacao.setTempoOcupada(estacao.getTempoOcupada()+0.0032);
						}else{
							estacao.setTempoOcupada(0.0032);
						}
					 }
					 //O evento de reforço de colisão não é adicionado na lista pois ele só é usado para comparações no evento de sentir o meio
					 eventoVo.setUltimoEvento(reforcoColisao);
					 eventoVo.setVerificaTransmissao(true);
					 
					 //É executado o algoritmo de binary back off para obter o atrazo
					 eventovoAtrazo = binaryBackof(estacao);
					 
					//Caso o quadro nao tenha sido descartado
					 if(!eventovoAtrazo.getDescartado())
					 {
						 //Cria um evento para transmitir novamente o quadro.  
						 Evento transmiteQuadro = new Evento();
						 transmiteQuadro.setEstacao(estacao);
						 //O quadro a ser transmitido novamente é o ultimo quadro enviado da estação que recebeu o quadro errado
						 transmiteQuadro.setPacote(estacao.getUltimoQuadroEnviado().getPacote());
						 transmiteQuadro.setQuadro(estacao.getUltimoQuadroEnviado());
						 
						 transmiteQuadro.setTipo(TipoEvento.SENTE_MEIO);
						 //O novo tempo é o tempo do evento mais o reforço e colisao e mais o atrazo aleátorio retornado pelo binaryBackOff
						 transmiteQuadro.setTempo(evento.getTempo()+0.0032+eventovoAtrazo.getAtrazo());
						 
//						insere o evento na lista de eventos
							insereEvento(transmiteQuadro,evento);
						
					 }else{
						 //descarta o quadro
						 Saida.saida="quadro"+evento.getQuadro().getId()+"descartado";
						 Saida.escreve();
						 Saida.saida="\n";
						 Saida.escreve();
					 }
					 //A estação colidiu entao vamos adicionar 1 ao seu numero de colisoes
					 estacao.setNumColisoes(estacao.getNumColisoes()+1);

					//soma o ncm do pacote pois houve colisão
					 evento.getQuadro().getPacote().setNcm(evento.getQuadro().getPacote().getNcm()+1);
					
					
					
				}else{
	//				retorna o ultimo evento executado
					eventoVo.setUltimoEvento(evento);
					//o evento nao é uma transmissao com sucesso nem reforço de colisão
					eventoVo.setVerificaTransmissao(false);
				}
			}
			

			//Agora o canal rx da estacao esta ocioso pois o quadro enviado pelo hub já chegou na estação
			estacao.getRx().setOcioso(true);
			
			
			
		 }else if(evento.getTipo().equals(TipoEvento.TRANSMITE_QUADRO))
		 {
			 Estacao estacao = evento.getQuadro().getPacote().getEstacao();
			 EventoVo eventovoAtrazo = new EventoVo();
			
			 //Caso o canal da estacao esteja ocioso e a estacao ja tenha recebido a confirmacao do ultimo quadro que foi enviado
			 if(estacao.getTx().getOcioso() && estacao.getRecebeuConfirmacaoUltimoQuadro())
			 {
				 //A estação ainda não recebeu a confirmação para o quadro que vai ser enviado agora
				 estacao.setRecebeuConfirmacaoUltimoQuadro(false);
				 
				//Como vamos enviar o quadro vamos calcular o tap fazendo o tempo atual menos o tempo em que o quadro foi criado
				Double tap = evento.getTempo() - evento.getQuadro().getTap();
				evento.getQuadro().setTap(tap);
				
				//O tempo ocupado não conta o tempo de sentir meio. Portanto iniciamos o tempo ocupado agora.
				evento.getQuadro().setTempoOcupado(evento.getTempo());
				
				//Caso estejamos enviando o ultimo quadro da mensagem o tam do ultimo quadro sera somente o tempo até o inicio da sua transmissao com sucesso
				if(evento.getQuadro().getNumeroSequencia() == estacao.getPmf()){
					Double tam = evento.getTempo() - evento.getQuadro().getTam();
					evento.getQuadro().setTam(tam);
				}
				
				//O tx da estação não esta mais ocioso pois ela vai enviar o quadro
				 estacao.getTx().setOcioso(false);
				 //Recupera o tempo de transmissão do canal tx da estação
				 Double tempoTransmissao = estacao.getTx().getTempoTransmissao();
				 //Cria um evento de retransmissao do hub
				 Evento eventoHub = new Evento();
				 eventoHub.setQuadro(evento.getQuadro());
				 eventoHub.setEstacao(estacao);
				 //o evento sera realizado no tempo em que a estaçao começa a transmitir mais o tempo de propagaçao no seu tx
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
				 Saida.saida="O quadro"+evento.getQuadro().getNumeroSequencia()+" da estacao "+estacao.getId()+" foi perdido pois o canal estava ocupado";
				 Saida.escreve();
				 //Como deu colisao devemos somar ao tempo ocupado da estacao o tempo ateh o quadro colidir.
				 if(evento.getQuadro().getTempoOcupado() != null)
				 {
					 Double tempoOcupado = evento.getTempo() - evento.getQuadro().getTempoOcupado();
					 if(evento.getTempo()>=faseTransiente)
					 {
						 if(estacao.getTempoOcupada() !=null)
						 {
							 estacao.setTempoOcupada(estacao.getTempoOcupada()+tempoOcupado);
						 }else{
							 estacao.setTempoOcupada(tempoOcupado);
						 }
					 }
				 }
				 //Se deu colisao soma um ao nmc do pacote
				 evento.getQuadro().getPacote().setNcm(evento.getQuadro().getPacote().getNcm()+1);
				 
				 //Cria um evento de reforço de colisão por 3,2us
				 Evento reforcoColisao = new Evento();
				 reforcoColisao.setTipo(TipoEvento.REFORCO_COLISAO);
				 reforcoColisao.setEstacao(estacao);
				 reforcoColisao.setQuadro(evento.getQuadro());
				 //3,2^10-3ms
				 reforcoColisao.setTempo(evento.getTempo()+0.0032);
				 
//				Devemos adicionar o tempo de reforco ao tempo ocupado da estacao
				 if(estacao.getTempoOcupada() != null)
				 {
					 estacao.setTempoOcupada(estacao.getTempoOcupada()+0.0032);
				 }else{
					 estacao.setTempoOcupada(0.0032);
				 }
				 //Retorna o reforço de colisao para que ele possa ser verificado no evento de sentir o meio
				 eventoVo.setUltimoEvento(reforcoColisao);
				 eventoVo.setVerificaTransmissao(true);
				 
				 //Calcula o atrazo aleatório pelo binaryBackOff
				 eventovoAtrazo = binaryBackof(estacao);
				//Caso o quadro não tenha sido descartado
				 if(!eventovoAtrazo.getDescartado())
				 {
					 //Cria um evento para retransmitir o quadro
					 Evento transmiteQuadro = new Evento();
					 transmiteQuadro.setEstacao(estacao);
					 transmiteQuadro.setPacote(evento.getQuadro().getPacote());
					 transmiteQuadro.setQuadro(evento.getQuadro());
					 //O evento criado é de sentir o meio
					 transmiteQuadro.setTipo(TipoEvento.SENTE_MEIO);
					 //O tempo é o tempo atual mais o reforço de colisao mais o atrazo
					 transmiteQuadro.setTempo(evento.getTempo()+0.0032+eventovoAtrazo.getAtrazo());
					 insereEvento(transmiteQuadro,evento);
					
				 }else{
					 //descarta o quadro
					 Saida.saida="quadro"+evento.getQuadro().getId()+"descartado";
					 Saida.escreve();
					 Saida.saida="\n";
					 Saida.escreve();
					 
				 }
				 //Como teve colisão soma 1 ao numero de colisões da estação
				 estacao.setNumColisoes(estacao.getNumColisoes()+1);
			 }
		 }else if(evento.getTipo().equals(TipoEvento.RETRANSMITE_QUADRO))
		 {
			// O tx agora esta ocioso porque o quadro já percorreu todo o canal e chegou no hub
			 evento.getEstacao().getTx().setOcioso(true);
			 int CanaisTdsOciosos = 0;

			 //Recupera o hub e sua lista de canais
			 Hub hub = evento.getQuadro().getPacote().getEstacao().getHub();
			 List<Canal> listaCanais = hub.getListaCanais();
			 
			 //Verifica se todos os canais rx(tx do hub) das estações estão ociosos
			 for(Canal canal2:(List<Canal>)listaCanais)
			 {
				 if(canal2.getOcioso() == true)
				 {
					 CanaisTdsOciosos++;
				 }
			 }
			 
			 for(Canal canal:(List<Canal>) listaCanais)
			 {

				 
				 Evento eventoRecebeEstacao = new Evento();
				
				 
				 
				 //Caso todos os canais estejam ociosos
				 if(CanaisTdsOciosos == listaCanais.size())
				 {
					 //cria um evento para a estação receber o quadro
					 eventoRecebeEstacao.setQuadro(evento.getQuadro());
					 eventoRecebeEstacao.setEstacao(canal.getEstacao());
					 eventoRecebeEstacao.setPacote(evento.getQuadro().getPacote());
					 //A estação recebe o quadro no tempo atual mais o tempo de transmissão do seu canal rx
					 eventoRecebeEstacao.setTempo(evento.getTempo() + canal.getTempoTransmissao());
					 eventoRecebeEstacao.setTipo(TipoEvento.RECEBE_QUADRO);
					 //O canal rx agora está ocupado pois está transmitindo o quadro
					 canal.setOcioso(false);
					 
					 //Insere o evento na lista
					 insereEvento(eventoRecebeEstacao,evento);
					 //Retorna o evento
					 eventoVo.setUltimoEvento(evento);
					 //O evento executado nao é transmissao com sucesso nem reforço de colisão
					 eventoVo.setVerificaTransmissao(false);
				 }
				 
			 }
		
			 
		 }else if(evento.getTipo().equals(TipoEvento.RECEBE_PACOTE)){
			 //Chama o método recebe pacote da estação passando o pacote, o tempo de envio e o evento
			 evento.getEstacao().recebePacote(evento.getPacote(), evento.getTempo(), evento);
			 //Cria o evento para receber o proximo pacote
			 Evento receberProximo=new Evento();
			 receberProximo.setTipo(TipoEvento.RECEBE_PACOTE);
			 receberProximo.setPacote(evento.getPacote());
			 receberProximo.setEstacao(evento.getEstacao());
			 //O tempo a ser recebido o novo pacote é o tempo atual mais a taxa de chegada da estação
			 receberProximo.setTempo(evento.getTempo()+evento.getEstacao().getTaxaDeChegada());
			 
			 
			 Controle.insereEvento(receberProximo,evento);
		 }
		return eventoVo;
	}
	
	public static EventoVo binaryBackof(Estacao estacao)
	{
		EventoVo eventovo = new EventoVo();
		//Recupera o numero de vezes que a estação já colidiu
		int numColisoes = estacao.getNumColisoes();
		double atrazo = 0.0;
		boolean descartaQuadro = false;
		 
		Random random = new Random();
		int aleatorio = 0;
		
		//51,2 us = 51,2x10-6, tempo do intervalo
		double tempo = 0.0000512;
		//Caso o numero de colisões da estação seja menor ou igual a 10
		if(numColisoes <= 10)
		{
			//Após k colisões, a estação escolhe aleatoriamente um atraso entre 0 e 2k - 1 intervalos para retransmitir.
			
			//escolhe um numero aleatorio entre 0 e o numero de colisoes
			aleatorio = random.nextInt(numColisoes+1);
			//Eleva a 2 o k
			atrazo = Math.pow(2,aleatorio);
			//diminui um do atrazo 
			atrazo--;
			//Multiplica o atrazo pelo intervalo
			atrazo=atrazo*tempo;
		}else{
			//Caso o numero de colisoes seja menor que 16
			if(numColisoes < 16)
			{
				//Quando o número de colisões sobe além de 10, continua-se a usar k=10
				aleatorio = random.nextInt(11);
				atrazo = Math.pow(2,aleatorio);
				atrazo--;
			}else{
				//Se o número de colisões consecutivas chega a 16, o quadro é imediatamente descartado após a detecção da 16ª colisão.
				descartaQuadro = true;
				
			}
		}
		//Retorna o atrazo e um boolean dizendo se o quadro foi descartado ou nao
		eventovo.setAtrazo(atrazo);
		eventovo.setDescartado(descartaQuadro);
		return eventovo;
		
	}

}
