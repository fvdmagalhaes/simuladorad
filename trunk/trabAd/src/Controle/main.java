package Controle;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.event.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vo.EventoVo;
import Rede.Canal;
import Rede.Estacao;
import Rede.Evento;
import Rede.Hub;
import Rede.Pacote;
import Rede.Quadro;
import Rede.TipoEvento;
import Simulacao.Tam;
import Simulacao.Tap;


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
	public static void main(String[] args) 
	{
		//vamos adicionar as informacoes do sistema
		
		//int CAPACIDADE = x;
		//instancia as classes das medidas do simulador
		
		new Interface();
		
		Tap tap1 = new Tap();
		tap1.setNumMaximoIteracoes(500);
		tap1.setNumMaximoRodadas(100);
		tap1.setAcabou(false);
		
		Tap tap2 = new Tap();
		tap2.setNumMaximoIteracoes(500);
		tap2.setNumMaximoRodadas(100);
		tap2.setAcabou(false);
		
		Tap tap3 = new Tap();
		tap3.setNumMaximoIteracoes(500);
		tap3.setNumMaximoRodadas(100);
		tap3.setAcabou(false);
		
		Tap tap4 = new Tap();
		tap4.setNumMaximoIteracoes(500);
		tap4.setNumMaximoRodadas(100);
		tap4.setAcabou(false);
		
		Tam tam1 = new Tam();
		tam1.setNumMaximoIteracoes(500);
		tam1.setNumMaximoRodadas(100);
		tam1.setAcabou(false);
		
		Tam tam2 = new Tam();
		tam2.setNumMaximoIteracoes(500);
		tam2.setNumMaximoRodadas(100);
		tam2.setAcabou(false);
		
		Tam tam3 = new Tam();
		tam3.setNumMaximoIteracoes(500);
		tam3.setNumMaximoRodadas(100);
		tam3.setAcabou(false);
		
		Tam tam4 = new Tam();
		tam4.setNumMaximoIteracoes(500);
		tam4.setNumMaximoRodadas(100);
		tam4.setAcabou(false);
		
		//instancia as estacoes do sistema
		Estacao e1 = new Estacao();
		Estacao e2 = new Estacao();
		Estacao e3 = new Estacao();
		Estacao e4 = new Estacao();
		e1.setNumColisoes(0);
		e2.setNumColisoes(0);
		e3.setNumColisoes(0);
		e4.setNumColisoes(0);
		
		e1.setTap(tap1);
		e2.setTap(tap2);
		e3.setTap(tap3);
		e4.setTap(tap4);
		
		e1.setTam(tam1);
		e2.setTam(tam2);
		e3.setTam(tam3);
		e4.setTam(tam4);
		
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
		//e1.setId(1);
		e1.setRx(rx1);
		e1.setTx(tx1);
		rx1.setEstacao(e1);
		tx1.setEstacao(e1);
		
		//e2.setId(2);
		e2.setRx(rx2);
		e2.setTx(tx2);
		rx2.setEstacao(e2);
		tx2.setEstacao(e2);
		
		//e3.setId(3);
		e3.setRx(rx3);
		e3.setTx(tx3);
		rx3.setEstacao(e3);
		tx3.setEstacao(e3);
		
		//e4.setId(4);
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
		EventoVo eventovo = new EventoVo();
		atual.setTempo(0.0);
		atual.setTipo(TipoEvento.RECEBE_PACOTE);
		atual.setEstacao(e1);
		//guarda a ultima transmissao com sucesso ou reforco de colisao
		Evento ultimaTransmissao = new Evento();
		ultimaTransmissao.setTempo(0.0);
		//Estacao 1 recebe 1 pacote de 40*1000bits
	
//		para o cenario 1 A1 = A2 = 40ms determistico p1=p2=40
		//só mudei a taxa de chegada do cenário 1 pra 80 pq eh o q tah na especificacao
		//se vcs tiverem colocado 40 por algum motivo, podem mudar
		e1.setTaxaDeChegada(80);
		e1.setPmf(40);
		/*e2.setTaxaDeChegada(40);
		e2.setPmf(40);*/
		
		Pacote pacote1 = new Pacote();
		pacote1.setEstacao(e1);
		//tamanho sempre em bits
		pacote1.setTamanho(pacote1.getEstacao().getPmf()*Quadro.TAMANHO);
		atual.setPacote(pacote1);
		
		//Estacao 2 recebe 1 pacote de 40*1000bits
		/*Pacote pacote2 = new Pacote();
		pacote2.setEstacao(e2);
		pacote2.setTamanho(40*8000);*/
		
		
		//e1.recebePacote(pacote1, 0.0, ultimoEvento);
		int i=0;
		//apenas para teste
		Evento primeiro=atual;
		while(!e1.getTap().getAcabou()){
			eventovo = Controle.trataEventos(atual,ultimaTransmissao);
			//Caso o ultimo evento executado seja uma transmissao com sucesso guarda ele
			if(eventovo.getVerificaTransmissao())
			{
				ultimaTransmissao = eventovo.getUltimoEvento();
			}
			atual=atual.getProximoEvento();
			i++;
		}
		
		e1.getTap().getMediaFinal();
		
	}
		
		//Controle.trataEventos(ultimoEvento,null);
		
		//System.out.println(ultimoEvento.getProximoEvento().getTipo());
		
		
		//e2.recebePacote(pacote2, 0.0, ultimoEvento);
		
		//aqui acaba a funcao main
		
	
	

}
