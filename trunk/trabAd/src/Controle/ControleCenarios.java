package Controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
import Simulacao.ncm;
import View.Interface;

public class ControleCenarios {

	public Interface intfc = null;
	
	public void intfc(){
		intfc = new Interface();
		intfc.setVisible(true);
		intfc.addSimularListener(new SimularListener());
	}
	
	
	class SimularListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
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
			
			ncm ncm1 = new ncm();
			ncm1.setNumMaximoIteracoes(1000);
			ncm1.setNumMaximoRodadas(100);
			ncm1.setAcabou(false);
			
			ncm ncm2 = new ncm();
			ncm2.setNumMaximoIteracoes(1000);
			ncm2.setNumMaximoRodadas(100);
			ncm2.setAcabou(false);
			
			ncm ncm3 = new ncm();
			ncm3.setNumMaximoIteracoes(1000);
			ncm3.setNumMaximoRodadas(100);
			ncm3.setAcabou(false);
			
			ncm ncm4 = new ncm();
			ncm4.setNumMaximoIteracoes(1000);
			ncm4.setNumMaximoRodadas(100);
			ncm4.setAcabou(false);
			
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
			
			e1.setNcm(ncm1);
			e2.setNcm(ncm2);
			e3.setNcm(ncm3);
			e4.setNcm(ncm4);
			
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
			
			//os ids s�o gerados automaticamente
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
			
			if(intfc.getRadioSelected() == 1){
				/*
				 * Cen�rio 1: esta��o 1 e esta��o 2 transmitem mensagens de 40 quadros de 1000
				 * bytes, totalizando 4 Mbps por esta��o, sincronizadas (mensagens chegam nas duas
				 * esta��es ao mesmo tempo).
				 * Par�metros: A1 = A2 = 80 ms, ambos determin�sticos; p1 = p2 = 40; esta��es 3 e 4
				 * sem tr�fego.	
				 */
				//envento da estacao 1
				Evento ev1 = new Evento();
				EventoVo eventovo = new EventoVo();
				ev1.setTempo(0.0);
				ev1.setTipo(TipoEvento.RECEBE_PACOTE);
				ev1.setEstacao(e1);
				Evento ultimaTransmissao = new Evento();
				ultimaTransmissao.setTempo(0.0);
				e1.setTaxaDeChegada(80);
				e1.setPmf(40);
				Pacote pacote1 = new Pacote();
				pacote1.setEstacao(e1);
				pacote1.setTamanho(pacote1.getEstacao().getPmf()*Quadro.TAMANHO);
				ev1.setPacote(pacote1);
				
				//evento da estacao 2
				Evento ev2 = new Evento();
				ev2.setTempo(0.0);
				ev2.setTipo(TipoEvento.RECEBE_PACOTE);
				ev2.setEstacao(e2);
				e2.setTaxaDeChegada(80);
				e2.setPmf(40);
				Pacote pacote2 = new Pacote();
				pacote2.setEstacao(e2);
				pacote2.setTamanho(pacote2.getEstacao().getPmf()*Quadro.TAMANHO);
				ev2.setPacote(pacote2);
				
				ev1.setProximoEvento(ev2);
				ev2.setEventoAnterior(ev1);
				
				while(!e1.getTap().getAcabou()){
					eventovo = Controle.trataEventos(ev1,ultimaTransmissao);
					//Caso o ultimo evento executado seja uma transmissao com sucesso guarda ele
					if(eventovo.getVerificaTransmissao())
					{
						ultimaTransmissao = eventovo.getUltimoEvento();
					}
					ev1=ev1.getProximoEvento();
				}
				
				e1.getTap().getMediaFinal();				
				
			}else if(intfc.getRadioSelected() == 2){
				
			}else if(intfc.getRadioSelected() == 3){
				
			}else if(intfc.getRadioSelected() == 4){
				
			}else if(intfc.getRadioSelected() == 5){
				
			}
		}
		
	}
}
