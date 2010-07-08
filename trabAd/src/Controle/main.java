package Controle;

import java.util.ArrayList;
import java.util.List;

import Rede.Canal;
import Rede.Estacao;
import Rede.Evento;
import Rede.Hub;
import Rede.Pacote;
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
		
		//Controle controle = new Controle();
		
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
			
		//adiciona as informacoes dos canais
		tx1.setOcioso(true);
		tx1.setTamanho(100.0);
		
		rx1.setOcioso(true);
		rx1.setTamanho(100.0);
	
		tx2.setOcioso(true);
		tx2.setTamanho(80.0);
		
		rx2.setOcioso(true);
		rx2.setTamanho(80.0);
		
		tx3.setOcioso(true);
		tx3.setTamanho(60.0);
		
		rx3.setOcioso(true);
		rx3.setTamanho(60.0);

		tx4.setOcioso(true);
		tx4.setTamanho(40.0);
		
		rx4.setOcioso(true);
		rx4.setTamanho(40.0);		

		//adiciona as informacoes das estacoes
		
		//os ids são gerados automaticamente
		e1.setId(1);
		e1.setRx(rx1);
		e1.setTx(tx1);
		rx1.setEstacao(e1);
		tx1.setEstacao(e1);
		
		e2.setId(2);
		e2.setRx(rx2);
		e2.setTx(tx2);
		rx2.setEstacao(e2);
		tx2.setEstacao(e2);
		
		e3.setId(3);
		e3.setRx(rx3);
		e3.setTx(tx3);
		rx3.setEstacao(e3);
		tx3.setEstacao(e3);
		
		e4.setId(4);
		e4.setRx(rx4);
		e4.setTx(tx4);
		rx4.setEstacao(e4);
		tx4.setEstacao(e4);
		
		//Seta o hub das estacoes
		e1.setHub(hub);
		e2.setHub(hub);
		e3.setHub(hub);
		e4.setHub(hub);
		
		//Adiciona as informacoes do hub
		List<Canal> listaCanais = new ArrayList<Canal>();
		//Guarda apenas os canais de recepcao das estacoes pois na hr de calcular o tempo que demora para enviar para cada estacao vms 
		//calcular utilizando esses canais
		listaCanais.add(rx1);
		listaCanais.add(rx2);
		listaCanais.add(rx3);
		listaCanais.add(rx4);
		hub.setListaCanais(listaCanais);
		
	/*Agora podemos iniciar nossa rede... 
	 * temos que pegar do usuario que cenario que ele quer simular
	*	Os cenarios possiveis estao na descricao do trabalho
	*	Para a rede iniciar temos que no tempo 0 ja gerar os pacotes,
	*	calcular as taxas de chegadas de cada estacao tb de acordo com o que foi escolheido,
	*	exponencial ou deterministica	
	*/
		//para começar a funcionar a parada, vou simular o cenario 1, depois a gente muda pra todos
		
		//guarda o ultimo evento executado
		Evento atual = new Evento();
		atual.setTempo(0.0);
		atual.setTipo(TipoEvento.RECEBE_PACOTE);
		atual.setEstacao(e1);
		//guarda a ultima transmissao com sucesso ou reforco de colisao
		Evento ultimaTransmissao = new Evento();
		//Estacao 1 recebe 1 pacote de 40mb
		
		Pacote pacote1 = new Pacote();
		pacote1.setEstacao(e1);
		//tamanho sempre em KB
		pacote1.setTamanho(40*8000);
		atual.setPacote(pacote1);
		
		//Estacao 2 recebe 1 pacote de 40mb
		/*Pacote pacote2 = new Pacote();
		pacote2.setEstacao(e2);
		pacote2.setTamanho(40);*/
		
		//para o cenario 1 A1 = A2 = 40ms determistico p1=p2=40
		e1.setTaxaDeChegada(40);
		e1.setPmf(40);
		e1.setPmf(40);
		
		//e2.setTaxaDeChegada(40);
		
		//e1.recebePacote(pacote1, 0.0, ultimoEvento);
		
		while(atual!=null){
			Controle.trataEventos(atual,null);
			atual=atual.getProximoEvento();
		}
		
		//Controle.trataEventos(ultimoEvento,null);
		
		//System.out.println(ultimoEvento.getProximoEvento().getTipo());
		
		
		//e2.recebePacote(pacote2, 0.0, ultimoEvento);
		
		//aqui acaba a funcao main
		
	}
	

}
