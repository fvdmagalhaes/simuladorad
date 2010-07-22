package Controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

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
		
		public void actionPerformed(ActionEvent arg0) {
			
			//Guarda a fase transiente
			Double faseTransiente = 0.0;
			
			Tap tap1 = new Tap();
			
			tap1.setNumMaximoIteracoes(5);
			tap1.setNumMaximoRodadas(5);

			tap1.setAcabou(false);
			
			Tap tap2 = new Tap ();

			tap2.setNumMaximoIteracoes(5);
			tap2.setNumMaximoRodadas(5);

			tap2.setAcabou(false);
			
			Tap tap3 = new Tap();
			tap3.setNumMaximoIteracoes(5);
			tap3.setNumMaximoRodadas(5);
			tap3.setAcabou(false);
			
			Tap tap4 = new Tap();
			tap4.setNumMaximoIteracoes(5);
			tap4.setNumMaximoRodadas(5);
			tap4.setAcabou(false);
			
			Tam tam1 = new Tam();
			tam1.setNumMaximoIteracoes(5);
			tam1.setNumMaximoRodadas(5);
			tam1.setAcabou(false);
			
			Tam tam2 = new Tam();
			tam2.setNumMaximoIteracoes(5);
			tam2.setNumMaximoRodadas(5);
			tam2.setAcabou(false);
			
			Tam tam3 = new Tam();
			tam3.setNumMaximoIteracoes(5);
			tam3.setNumMaximoRodadas(5);
			tam3.setAcabou(false);
			
			Tam tam4 = new Tam();
			tam4.setNumMaximoIteracoes(5);
			tam4.setNumMaximoRodadas(5);
			tam4.setAcabou(false);
			
			ncm ncm1 = new ncm();
			ncm1.setNumMaximoIteracoes(5);
			ncm1.setNumMaximoRodadas(5);
			ncm1.setAcabou(false);
			
			ncm ncm2 = new ncm();
			ncm2.setNumMaximoIteracoes(5);
			ncm2.setNumMaximoRodadas(5);
			ncm2.setAcabou(false);
			
			ncm ncm3 = new ncm();
			ncm3.setNumMaximoIteracoes(5);
			ncm3.setNumMaximoRodadas(5);
			ncm3.setAcabou(false);
			
			ncm ncm4 = new ncm();
			ncm4.setNumMaximoIteracoes(5);
			ncm4.setNumMaximoRodadas(5);
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
			
//			comeca como true pois a estacao ainda nao enviou nenhum quadro
			e1.setRecebeuConfirmacaoUltimoQuadro(true);
			e2.setRecebeuConfirmacaoUltimoQuadro(true);
			e3.setRecebeuConfirmacaoUltimoQuadro(true);
			e4.setRecebeuConfirmacaoUltimoQuadro(true);
			
			if(intfc.getRadioSelected() == 1){
				/*
				 * Cen�rio 1: esta��o 1 e esta��o 2 transmitem mensagens de 40 quadros de 1000
				 * bytes, totalizando 4 Mbps por esta��o, sincronizadas (mensagens chegam nas duas
				 * esta��es ao mesmo tempo).
				 * Par�metros: A1 = A2 = 80 ms, ambos determin�sticos; p1 = p2 = 40; esta��es 3 e 4
				 * sem tr�fego.	
				 */
				//evento da estacao 1
				Evento ev1 = new Evento();
				EventoVo eventovo = new EventoVo();
				ev1.setTempo(0.0);
				ev1.setTipo(TipoEvento.RECEBE_PACOTE);
				ev1.setEstacao(e1);
				Evento ultimaTransmissao = new Evento();
				ultimaTransmissao.setTempo(0.0);
				e1.setDistribuicaoChegadapacotes(false);
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
				e2.setDistribuicaoChegadapacotes(false);
				e2.setTaxaDeChegada(80);
				e2.setPmf(40);
				Pacote pacote2 = new Pacote();
				pacote2.setEstacao(e2);
				pacote2.setTamanho(pacote2.getEstacao().getPmf()*Quadro.TAMANHO);
				ev2.setPacote(pacote2);
				
				ev1.setProximoEvento(ev2);
				ev2.setEventoAnterior(ev1);
				
				while(!e1.getTap().getAcabou() && !e2.getTap().getAcabou() && !e1.getTam().getAcabou() && !e2.getTam().getAcabou() && !e1.getNcm().getAcabou() && !e2.getNcm().getAcabou()){
					eventovo = Controle.trataEventos(ev1,ultimaTransmissao, faseTransiente);
					//Caso o ultimo evento executado seja uma transmissao com sucesso guarda ele
					if(eventovo.getVerificaTransmissao())
					{
						ultimaTransmissao = eventovo.getUltimoEvento();
					}
					//Evento ev=ev1;
					ev1=ev1.getProximoEvento();
					/*ev1.setEventoAnterior(null);
					ev=null;*/
				}
				//Busca o ultimo evento da estacao 1
				Evento ultimoEventoTemp1 = eventovo.getUltimoEvento();
				while(!ultimoEventoTemp1.getEstacao().equals(e1))
				{
					ultimoEventoTemp1 = ultimoEventoTemp1.getEventoAnterior();
				}
				
				//O tempo de simulacao da estacao eh o tempo do ultimo evento dela menos a fase transiente
				e1.setTempoSimulacao(ultimoEventoTemp1.getTempo()-faseTransiente);

				
				
//				Busca o ultimo evento da estacao 2
				Evento ultimoEventoTemp2 = eventovo.getUltimoEvento();
				while(!ultimoEventoTemp2.getEstacao().equals(e2))
				{
					ultimoEventoTemp2 = ultimoEventoTemp2.getEventoAnterior();
				}
				
				//O tempo de simulacao da estacao eh o tempo do ultimo evento dela menos a fase transiente
				e2.setTempoSimulacao(ultimoEventoTemp2.getTempo()-faseTransiente);
				
				
//				Calcula a utilizacao para a estacao 1
				Double utilizacao = e1.getTempoOcupada()/e1.getTempoSimulacao();
				
				//Calcula a vazao das estacoes 1 e 2
				Double vazao1 = e1.getNumeroQuadrosTransmitidosSucesso()/e1.getTempoSimulacao();
				Double vazao2 = e2.getNumeroQuadrosTransmitidosSucesso()/e2.getTempoSimulacao();
				
				System.out.println("Medidas da estacao 1: ");
				e1.getTap().getMediaFinal();	
				e1.getTam().getMediaFinal();
				e1.getNcm().getMediaFinal();
				System.out.println("Utiliza��o:"+utilizacao);
				System.out.println("Vaz�o:"+vazao1);
				
				System.out.println("Medidas da estacao 2");
				e2.getTap().getMediaFinal();
				e2.getTam().getMediaFinal();
				e2.getNcm().getMediaFinal();
				System.out.println("Vaz�o:"+vazao2);
				
				
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e1.getTap().getRodadas().size();i++){
					dataset.addValue(e1.getTap().getRodadas().get(i).getMedia(), "ESTA��O 1","Rodada "+i);
				}
				
				JFreeChart j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAP", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				ChartPanel panel=new ChartPanel(j);
				JFrame f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tap");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e2.getTap().getRodadas().size();i++){
					dataset.addValue(e2.getTap().getRodadas().get(i).getMedia(), "ESTA��O 2","Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAP", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tap");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				dataset = new DefaultCategoryDataset();
				
				if(e1.getTam().getRodadas() != null){
					for(int i=0;i<e1.getTam().getRodadas().size();i++){
						dataset.addValue(e1.getTam().getRodadas().get(i).getMedia(), "ESTA��O 1","Rodada "+i);
					}
				
					j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
					panel=new ChartPanel(j);
					f=new JFrame();
					f.setSize(700, 700);
					f.add(panel);
					f.setTitle("Tam");
					f.setVisible(true);
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					dataset = new DefaultCategoryDataset();
				}
				
				if(e2.getTam().getRodadas() != null){
					for(int i=0;i<e2.getTam().getRodadas().size();i++){
						dataset.addValue(e2.getTam().getRodadas().get(i).getMedia(), "ESTA��O 2","Rodada "+i);
					}
					
					j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAM", dataset,PlotOrientation.VERTICAL, true, false,false);
					
					panel=new ChartPanel(j);
					f=new JFrame();
					f.setSize(700, 700);
					f.add(panel);
					f.setTitle("Tam");
					f.setVisible(true);
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				dataset = new DefaultCategoryDataset();
				
				if(e1.getNcm().getRodadas() != null){

					for(int i=0;i<e1.getNcm().getRodadas().size();i++){
						dataset.addValue(e1.getNcm().getRodadas().get(i).getMedia(), "ESTA��O 1","Rodada "+i);
					}
					
					j=ChartFactory.createLineChart("Gr�fico", "Rodada", "NCM", dataset,PlotOrientation.VERTICAL, true, false,false);
					
					panel=new ChartPanel(j);
					f=new JFrame();
					f.setSize(700, 700);
					f.add(panel);
					f.setTitle("ncm");
					f.setVisible(true);
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				dataset = new DefaultCategoryDataset();
				
				if(e2.getNcm().getRodadas() != null){

					for(int i=0;i<e2.getNcm().getRodadas().size();i++){
						dataset.addValue(e2.getNcm().getRodadas().get(i).getMedia(), "ESTA��O 2","Rodada "+i);
					}
					
					j=ChartFactory.createLineChart("Gr�fico", "Rodada", "NCM", dataset,PlotOrientation.VERTICAL, true, false,false);
					
					panel=new ChartPanel(j);
					f=new JFrame();
					f.setSize(700, 700);
					f.add(panel);
					f.setTitle("ncm");
					f.setVisible(true);
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				
			}else if(intfc.getRadioSelected() == 2){
				/*
				 * Cen�rio 2: esta��o 1 e esta��o 2 transmitem mensagens de 40 quadros de 1000
				 * bytes, totalizando 4 Mbps por esta��o, chegada de mensagens Poisson
				 * 
				 * Par�metros: A1 = A2 = 80 ms exponencial; p1 = p2 = 40; esta��es 3 e 4
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
				e1.setDistribuicaoChegadapacotes(true);
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
				e2.setDistribuicaoChegadapacotes(true);
				e2.setTaxaDeChegada(80);
				e2.setPmf(40);
				Pacote pacote2 = new Pacote();
				pacote2.setEstacao(e2);
				pacote2.setTamanho(pacote2.getEstacao().getPmf()*Quadro.TAMANHO);
				ev2.setPacote(pacote2);
				
				ev1.setProximoEvento(ev2);
				ev2.setEventoAnterior(ev1);
				
				while(!e1.getTap().getAcabou() && !e2.getTap().getAcabou() && !e1.getTam().getAcabou() && !e2.getTam().getAcabou() && !e1.getNcm().getAcabou() && !e2.getNcm().getAcabou()){
					eventovo = Controle.trataEventos(ev1,ultimaTransmissao,faseTransiente);
					//Caso o ultimo evento executado seja uma transmissao com sucesso guarda ele
					if(eventovo.getVerificaTransmissao())
					{
						ultimaTransmissao = eventovo.getUltimoEvento();
					}
//					Evento ev=ev1;
					ev1=ev1.getProximoEvento();
					/*ev1.setEventoAnterior(null);
					ev=null;*/
				}
//				Busca o ultimo evento da estacao 1
				Evento ultimoEventoTemp1 = eventovo.getUltimoEvento();
				while(!ultimoEventoTemp1.getEstacao().equals(e1))
				{
					ultimoEventoTemp1 = ultimoEventoTemp1.getEventoAnterior();
				}
				
				//O tempo de simulacao da estacao eh o tempo do ultimo evento dela
				e1.setTempoSimulacao(ultimoEventoTemp1.getTempo()-faseTransiente);

				
				
//				Busca o ultimo evento da estacao 2
				Evento ultimoEventoTemp2 = eventovo.getUltimoEvento();
				while(!ultimoEventoTemp2.getEstacao().equals(e2))
				{
					ultimoEventoTemp2 = ultimoEventoTemp2.getEventoAnterior();
				}
				
				//O tempo de simulacao da estacao eh o tempo do ultimo evento dela
				e2.setTempoSimulacao(ultimoEventoTemp2.getTempo()-faseTransiente);
				
				
//				Calcula a utilizacao para a estacao 1
				Double utilizacao = e1.getTempoOcupada()/e1.getTempoSimulacao();
				
				//Calcula a vazao das estacoes 1 e 2
				Double vazao1 = e1.getNumeroQuadrosTransmitidosSucesso()/e1.getTempoSimulacao();
				Double vazao2 = e2.getNumeroQuadrosTransmitidosSucesso()/e2.getTempoSimulacao();
				
				System.out.println("Medidas da estacao 1: ");
				e1.getTap().getMediaFinal();	
				e1.getTam().getMediaFinal();
				e1.getNcm().getMediaFinal();
				System.out.println("Utiliza��o:"+utilizacao);
				System.out.println("Vaz�o:"+vazao1);
				
				System.out.println("Medidas da estacao 2");
				e2.getTap().getMediaFinal();
				e2.getTam().getMediaFinal();
				e2.getNcm().getMediaFinal();
				System.out.println("Vaz�o:"+vazao2);
				
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e1.getTap().getRodadas().size();i++){
					dataset.addValue(e1.getTap().getRodadas().get(i).getMedia(), "ESTA��O 1","Rodada "+i);
				}
				
				JFreeChart j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAP", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				ChartPanel panel=new ChartPanel(j);
				JFrame f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tap");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e2.getTap().getRodadas().size();i++){
					dataset.addValue(e2.getTap().getRodadas().get(i).getMedia(), "ESTA��O 2","Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAP", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tap");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				if(e1.getTam().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				for(int i=0;i<e1.getTam().getRodadas().size();i++){
					dataset.addValue(e1.getTam().getRodadas().get(i).getMedia(), "ESTA��O 1","Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tam");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getTam().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e2.getTam().getRodadas().size();i++){
					dataset.addValue(e2.getTam().getRodadas().get(i).getMedia(), "ESTA��O 2","Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tam");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getNcm().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e1.getNcm().getRodadas().size();i++){
					dataset.addValue(e1.getNcm().getRodadas().get(i).getMedia(), "ESTA��O 1","Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "NCM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("ncm");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getNcm().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e2.getNcm().getRodadas().size();i++){
					dataset.addValue(e2.getNcm().getRodadas().get(i).getMedia(), "ESTA��O 2","Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "NCM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("ncm");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				/*System.out.println("Medidas da estacao 1: ");
				e1.getTap().getMediaFinal();				
				
				System.out.println("Medidas da estacao 2");
				e2.getTap().getMediaFinal();*/
				
			}else if(intfc.getRadioSelected() == 3){
				
				/*
				 * Cen�rio 3: esta��o 1 injetando tr�fego determin�stico (msgs de 40 quadros) e 
				 * esta��es 2, 3 e 4 gerando tr�fego de mensagens com um �nico quadro na taxa de 0,5 Mbps 
				 * por esta��o tamb�m determin�stico - todas sincronizadas na gera��o.
				 * Par�metros: A1 = 80ms, determin�stico; p1 = 40; A2 = A3 = A4 = 16 ms, todos deterministicos
				 * p2 = p3 = p4 = 1
				 */
				
				//envento da estacao 1
				Evento ev1 = new Evento();
				EventoVo eventovo = new EventoVo();
				ev1.setTempo(0.0);
				ev1.setTipo(TipoEvento.RECEBE_PACOTE);
				ev1.setEstacao(e1);
				Evento ultimaTransmissao = new Evento();
				ultimaTransmissao.setTempo(0.0);
				e1.setDistribuicaoChegadapacotes(false);
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
				e2.setDistribuicaoChegadapacotes(false);
				e2.setTaxaDeChegada(16);
				e2.setPmf(1);
				Pacote pacote2 = new Pacote();
				pacote2.setEstacao(e2);
				pacote2.setTamanho(pacote2.getEstacao().getPmf()*Quadro.TAMANHO);
				ev2.setPacote(pacote2);
				
				ev1.setProximoEvento(ev2);
				ev2.setEventoAnterior(ev1);
				
				//evento da estacao 3
				Evento ev3 = new Evento();
				ev3.setTempo(0.0);
				ev3.setTipo(TipoEvento.RECEBE_PACOTE);
				ev3.setEstacao(e3);
				e3.setDistribuicaoChegadapacotes(false);
				e3.setTaxaDeChegada(16);
				e3.setPmf(1);
				Pacote pacote3 = new Pacote();
				pacote3.setEstacao(e3);
				pacote3.setTamanho(pacote3.getEstacao().getPmf()*Quadro.TAMANHO);
				ev3.setPacote(pacote3);
				
				ev2.setProximoEvento(ev3);
				ev3.setEventoAnterior(ev2);
				
				//evento da estacao 4
				Evento ev4 = new Evento();
				ev4.setTempo(0.0);
				ev4.setTipo(TipoEvento.RECEBE_PACOTE);
				ev4.setEstacao(e4);
				e4.setDistribuicaoChegadapacotes(false);
				e4.setTaxaDeChegada(16);
				e4.setPmf(1);
				Pacote pacote4 = new Pacote();
				pacote4.setEstacao(e4);
				pacote4.setTamanho(pacote4.getEstacao().getPmf()*Quadro.TAMANHO);
				ev4.setPacote(pacote4);
				
				ev3.setProximoEvento(ev4);
				ev4.setEventoAnterior(ev3);
				
				while(!e1.getTap().getAcabou()&& !e2.getTap().getAcabou() && !e3.getTap().getAcabou() && !e4.getTap().getAcabou()
						&& !e1.getTam().getAcabou()&& !e2.getTam().getAcabou() && !e3.getTam().getAcabou() && !e4.getTam().getAcabou()
						&& !e1.getNcm().getAcabou()&& !e2.getNcm().getAcabou() && !e3.getNcm().getAcabou() && !e4.getNcm().getAcabou()){
					eventovo = Controle.trataEventos(ev1,ultimaTransmissao,faseTransiente);
					//Caso o ultimo evento executado seja uma transmissao com sucesso guarda ele
					if(eventovo.getVerificaTransmissao())
					{
						ultimaTransmissao = eventovo.getUltimoEvento();
					}
//					Evento ev=ev1;
					ev1=ev1.getProximoEvento();
					/*ev1.setEventoAnterior(null);
					ev=null;*/
				}
//				Busca o ultimo evento da estacao 1
				Evento ultimoEventoTemp1 = eventovo.getUltimoEvento();
				while(!ultimoEventoTemp1.getEstacao().equals(e1))
				{
					ultimoEventoTemp1 = ultimoEventoTemp1.getEventoAnterior();
				}
				
				//O tempo de simulacao da estacao eh o tempo do ultimo evento dela
				e1.setTempoSimulacao(ultimoEventoTemp1.getTempo()-faseTransiente);

				
				
//				Busca o ultimo evento da estacao 2
				Evento ultimoEventoTemp2 = eventovo.getUltimoEvento();
				while(!ultimoEventoTemp2.getEstacao().equals(e2))
				{
					ultimoEventoTemp2 = ultimoEventoTemp2.getEventoAnterior();
				}
				
				//O tempo de simulacao da estacao eh o tempo do ultimo evento dela
				e2.setTempoSimulacao(ultimoEventoTemp2.getTempo()-faseTransiente);
				
//				Busca o ultimo evento da estacao 3
				Evento ultimoEventoTemp3 = eventovo.getUltimoEvento();
				while(!ultimoEventoTemp3.getEstacao().equals(e3))
				{
					ultimoEventoTemp3 = ultimoEventoTemp3.getEventoAnterior();
				}
				
				//O tempo de simulacao da estacao eh o tempo do ultimo evento dela
				e3.setTempoSimulacao(ultimoEventoTemp3.getTempo()-faseTransiente);
				
				//				Busca o ultimo evento da estacao 4
				Evento ultimoEventoTemp4 = eventovo.getUltimoEvento();
				while(!ultimoEventoTemp4.getEstacao().equals(e4))
				{
					ultimoEventoTemp4 = ultimoEventoTemp4.getEventoAnterior();
				}
				
				//O tempo de simulacao da estacao eh o tempo do ultimo evento dela
				e4.setTempoSimulacao(ultimoEventoTemp4.getTempo()-faseTransiente);
				
				
//				Calcula a utilizacao para a estacao 1
				Double utilizacao = e1.getTempoOcupada()/e1.getTempoSimulacao();
				
				//Calcula a vazao das estacoes 1,2,3 e 4
				Double vazao1 = e1.getNumeroQuadrosTransmitidosSucesso()/e1.getTempoSimulacao();
				Double vazao2 = e2.getNumeroQuadrosTransmitidosSucesso()/e2.getTempoSimulacao();
				Double vazao3 = e3.getNumeroQuadrosTransmitidosSucesso()/e3.getTempoSimulacao();
				Double vazao4 = e4.getNumeroQuadrosTransmitidosSucesso()/e4.getTempoSimulacao();
				
				
				System.out.println("Medidas da estacao 1: ");
				e1.getTap().getMediaFinal();
				e1.getTam().getMediaFinal();
				e1.getNcm().getMediaFinal();
				System.out.println("Utiliza��o:"+utilizacao);
				System.out.println("Vaz�o:"+vazao1);
				
				
				
				System.out.println("Medidas da estacao 2: ");
				e2.getTap().getMediaFinal();
				e2.getTam().getMediaFinal();
				e2.getNcm().getMediaFinal();
				System.out.println("Vaz�o:"+vazao2);
				
				
				System.out.println("Medidas da estacao 3: ");
				e3.getTap().getMediaFinal();
				e3.getTam().getMediaFinal();
				e3.getNcm().getMediaFinal();
				System.out.println("Vaz�o:"+vazao3);
				
				System.out.println("Medidas da estacao 4: ");
				e4.getTap().getMediaFinal();
				e4.getTam().getMediaFinal();
				e4.getNcm().getMediaFinal();
				System.out.println("Vaz�o:"+vazao4);
				
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e1.getTap().getRodadas().size();i++){
					dataset.addValue(e1.getTap().getRodadas().get(i).getMedia(), "ESTA��O 1","Rodada "+i);
				}
				
				JFreeChart j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAP", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				ChartPanel panel=new ChartPanel(j);
				JFrame f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tap");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e2.getTap().getRodadas().size();i++){
					dataset.addValue(e2.getTap().getRodadas().get(i).getMedia(), "ESTA��O 2", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAP", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tap");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e3.getTap().getRodadas().size();i++){
					dataset.addValue(e3.getTap().getRodadas().get(i).getMedia(), "ESTA��O 3", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAP", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tap");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e4.getTap().getRodadas().size();i++){
					dataset.addValue(e4.getTap().getRodadas().get(i).getMedia(), "ESTA��O 4", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAP", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tap");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				if(e1.getTam().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e1.getTam().getRodadas().size();i++){
					dataset.addValue(e1.getTam().getRodadas().get(i).getMedia(), "ESTA��O 1","Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tam");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getTam().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e2.getTam().getRodadas().size();i++){
					dataset.addValue(e2.getTam().getRodadas().get(i).getMedia(), "ESTA��O 2", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tam");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getTam().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e3.getTam().getRodadas().size();i++){
					dataset.addValue(e3.getTam().getRodadas().get(i).getMedia(), "ESTA��O 3", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tam");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getTam().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e4.getTam().getRodadas().size();i++){
					dataset.addValue(e4.getTam().getRodadas().get(i).getMedia(), "ESTA��O 4", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tam");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getNcm().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e1.getNcm().getRodadas().size();i++){
					dataset.addValue(e1.getNcm().getRodadas().get(i).getMedia(), "ESTA��O 1","Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "NCM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Ncm");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getNcm().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e2.getNcm().getRodadas().size();i++){
					dataset.addValue(e2.getNcm().getRodadas().get(i).getMedia(), "ESTA��O 2", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "NCM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Ncm");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getNcm().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e3.getNcm().getRodadas().size();i++){
					dataset.addValue(e3.getNcm().getRodadas().get(i).getMedia(), "ESTA��O 3", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "NCM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Ncm");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getNcm().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e4.getNcm().getRodadas().size();i++){
					dataset.addValue(e4.getNcm().getRodadas().get(i).getMedia(), "ESTA��O 4", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "NCM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Ncm");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
			}else if(intfc.getRadioSelected() == 4){

				/*
				 * Cen�rio 4: esta��o 1 injetando tr�fego determin�stico (msgs de 40 quadros) e 
				 * esta��es 2, 3 e 4 gerando tr�fego de mensagens com um �nico quadro na taxa de 0,5 Mbps 
				 * por esta��o Poisson
				 * Par�metros: A1 = 80ms, determin�stico; p1 = 40; A2 = A3 = A4 = 16 ms, todos exponenciais
				 * p2 = p3 = p4 = 1
				 */
				
				//envento da estacao 1
				Evento ev1 = new Evento();
				EventoVo eventovo = new EventoVo();
				ev1.setTempo(0.0);
				ev1.setTipo(TipoEvento.RECEBE_PACOTE);
				ev1.setEstacao(e1);
				Evento ultimaTransmissao = new Evento();
				ultimaTransmissao.setTempo(0.0);
				e1.setDistribuicaoChegadapacotes(false);
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
				e2.setDistribuicaoChegadapacotes(true);
				e2.setTaxaDeChegada(16);
				e2.setPmf(1);
				Pacote pacote2 = new Pacote();
				pacote2.setEstacao(e2);
				pacote2.setTamanho(pacote2.getEstacao().getPmf()*Quadro.TAMANHO);
				ev2.setPacote(pacote2);
				
				ev1.setProximoEvento(ev2);
				ev2.setEventoAnterior(ev1);
				
				//evento da estacao 3
				Evento ev3 = new Evento();
				ev3.setTempo(0.0);
				ev3.setTipo(TipoEvento.RECEBE_PACOTE);
				ev3.setEstacao(e3);
				e3.setDistribuicaoChegadapacotes(true);
				e3.setTaxaDeChegada(16);
				e3.setPmf(1);
				Pacote pacote3 = new Pacote();
				pacote3.setEstacao(e3);
				pacote3.setTamanho(pacote3.getEstacao().getPmf()*Quadro.TAMANHO);
				ev3.setPacote(pacote3);
				
				ev2.setProximoEvento(ev3);
				ev3.setEventoAnterior(ev2);
				
				//evento da estacao 4
				Evento ev4 = new Evento();
				ev4.setTempo(0.0);
				ev4.setTipo(TipoEvento.RECEBE_PACOTE);
				ev4.setEstacao(e4);
				e4.setDistribuicaoChegadapacotes(true);
				e4.setTaxaDeChegada(16);
				e4.setPmf(1);
				Pacote pacote4 = new Pacote();
				pacote4.setEstacao(e4);
				pacote4.setTamanho(pacote4.getEstacao().getPmf()*Quadro.TAMANHO);
				ev4.setPacote(pacote4);
				
				ev3.setProximoEvento(ev4);
				ev4.setEventoAnterior(ev3);
				
				while(!e1.getTap().getAcabou()&& !e2.getTap().getAcabou() && !e3.getTap().getAcabou() && !e4.getTap().getAcabou()
						&& !e1.getTam().getAcabou()&& !e2.getTam().getAcabou() && !e3.getTam().getAcabou() && !e4.getTam().getAcabou()
						&& !e1.getNcm().getAcabou()&& !e2.getNcm().getAcabou() && !e3.getNcm().getAcabou() && !e4.getNcm().getAcabou()){
					eventovo = Controle.trataEventos(ev1,ultimaTransmissao,faseTransiente);
					//Caso o ultimo evento executado seja uma transmissao com sucesso guarda ele
					if(eventovo.getVerificaTransmissao())
					{
						ultimaTransmissao = eventovo.getUltimoEvento();
					}
//					Evento ev=ev1;
					ev1=ev1.getProximoEvento();
					/*ev1.setEventoAnterior(null);
					ev=null;*/
				}
//				Busca o ultimo evento da estacao 1
				Evento ultimoEventoTemp1 = eventovo.getUltimoEvento();
				while(!ultimoEventoTemp1.getEstacao().equals(e1))
				{
					ultimoEventoTemp1 = ultimoEventoTemp1.getEventoAnterior();
				}
				
				//O tempo de simulacao da estacao eh o tempo do ultimo evento dela
				e1.setTempoSimulacao(ultimoEventoTemp1.getTempo()-faseTransiente);

				
				
//				Busca o ultimo evento da estacao 2
				Evento ultimoEventoTemp2 = eventovo.getUltimoEvento();
				while(!ultimoEventoTemp2.getEstacao().equals(e2))
				{
					ultimoEventoTemp2 = ultimoEventoTemp2.getEventoAnterior();
				}
				
				//O tempo de simulacao da estacao eh o tempo do ultimo evento dela
				e2.setTempoSimulacao(ultimoEventoTemp2.getTempo()-faseTransiente);
				
//				Busca o ultimo evento da estacao 3
				Evento ultimoEventoTemp3 = eventovo.getUltimoEvento();
				while(!ultimoEventoTemp3.getEstacao().equals(e3))
				{
					ultimoEventoTemp3 = ultimoEventoTemp3.getEventoAnterior();
				}
				
				//O tempo de simulacao da estacao eh o tempo do ultimo evento dela
				e3.setTempoSimulacao(ultimoEventoTemp3.getTempo()-faseTransiente);
				
//				Busca o ultimo evento da estacao 4
				Evento ultimoEventoTemp4 = eventovo.getUltimoEvento();
				while(!ultimoEventoTemp4.getEstacao().equals(e4))
				{
					ultimoEventoTemp4 = ultimoEventoTemp4.getEventoAnterior();
				}
				
				//O tempo de simulacao da estacao eh o tempo do ultimo evento dela
				e4.setTempoSimulacao(ultimoEventoTemp4.getTempo()-faseTransiente);
				
				
//				Calcula a utilizacao para a estacao 1
				Double utilizacao = e1.getTempoOcupada()/e1.getTempoSimulacao();
				
				//Calcula a vazao das estacoes 1,2,3 e 4
				Double vazao1 = e1.getNumeroQuadrosTransmitidosSucesso()/e1.getTempoSimulacao();
				Double vazao2 = e2.getNumeroQuadrosTransmitidosSucesso()/e2.getTempoSimulacao();
				Double vazao3 = e3.getNumeroQuadrosTransmitidosSucesso()/e3.getTempoSimulacao();
				Double vazao4 = e4.getNumeroQuadrosTransmitidosSucesso()/e4.getTempoSimulacao();
				
				
				System.out.println("Medidas da estacao 1: ");
				e1.getTap().getMediaFinal();
				e1.getTam().getMediaFinal();
				e1.getNcm().getMediaFinal();
				System.out.println("Utiliza��o:"+utilizacao);
				System.out.println("Vaz�o:"+vazao1);
				
				
				
				System.out.println("Medidas da estacao 2: ");
				e2.getTap().getMediaFinal();
				e2.getTam().getMediaFinal();
				e2.getNcm().getMediaFinal();
				System.out.println("Vaz�o:"+vazao2);
				
				
				System.out.println("Medidas da estacao 3: ");
				e3.getTap().getMediaFinal();
				e3.getTam().getMediaFinal();
				e3.getNcm().getMediaFinal();
				System.out.println("Vaz�o:"+vazao3);
				
				System.out.println("Medidas da estacao 4: ");
				e4.getTap().getMediaFinal();
				e4.getTam().getMediaFinal();
				e4.getNcm().getMediaFinal();
				System.out.println("Vaz�o:"+vazao4);
			
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e1.getTap().getRodadas().size();i++){
					dataset.addValue(e1.getTap().getRodadas().get(i).getMedia(), "ESTA��O 1","Rodada "+i);
				}
				
				JFreeChart j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAP", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				ChartPanel panel=new ChartPanel(j);
				JFrame f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tap");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e2.getTap().getRodadas().size();i++){
					dataset.addValue(e2.getTap().getRodadas().get(i).getMedia(), "ESTA��O 2", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAP", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tap");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e3.getTap().getRodadas().size();i++){
					dataset.addValue(e3.getTap().getRodadas().get(i).getMedia(), "ESTA��O 3", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAP", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tap");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e4.getTap().getRodadas().size();i++){
					dataset.addValue(e4.getTap().getRodadas().get(i).getMedia(), "ESTA��O 4", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAP", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tap");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				if(e1.getTam().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e1.getTam().getRodadas().size();i++){
					dataset.addValue(e1.getTam().getRodadas().get(i).getMedia(), "ESTA��O 1","Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tam");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getTam().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e2.getTam().getRodadas().size();i++){
					dataset.addValue(e2.getTam().getRodadas().get(i).getMedia(), "ESTA��O 2", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tam");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getTam().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e3.getTam().getRodadas().size();i++){
					dataset.addValue(e3.getTam().getRodadas().get(i).getMedia(), "ESTA��O 3", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tam");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getTam().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e4.getTam().getRodadas().size();i++){
					dataset.addValue(e4.getTam().getRodadas().get(i).getMedia(), "ESTA��O 4", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "TAM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Tam");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getNcm().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e1.getNcm().getRodadas().size();i++){
					dataset.addValue(e1.getNcm().getRodadas().get(i).getMedia(), "ESTA��O 1","Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "NCM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Ncm");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getNcm().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e2.getNcm().getRodadas().size();i++){
					dataset.addValue(e2.getNcm().getRodadas().get(i).getMedia(), "ESTA��O 2", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "NCM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Ncm");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getNcm().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e3.getNcm().getRodadas().size();i++){
					dataset.addValue(e3.getNcm().getRodadas().get(i).getMedia(), "ESTA��O 3", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "NCM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Ncm");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				if(e1.getNcm().getRodadas() != null){
				dataset = new DefaultCategoryDataset();
				
				for(int i=0;i<e4.getNcm().getRodadas().size();i++){
					dataset.addValue(e4.getNcm().getRodadas().get(i).getMedia(), "ESTA��O 4", "Rodada "+i);
				}
				
				j=ChartFactory.createLineChart("Gr�fico", "Rodada", "NCM", dataset,PlotOrientation.VERTICAL, true, false,false);
				
				panel=new ChartPanel(j);
				f=new JFrame();
				f.setSize(700, 700);
				f.add(panel);
				f.setTitle("Ncm");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}

			}
			
		}
	}
}
