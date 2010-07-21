package Simulacao;

import java.util.ArrayList;
import java.util.List;

public class ncm {



		//qtas rodadas teremos pra colher os dados
		int numMaximoRodadas;
		
		int numMaximoIteracoes;
		
		Boolean acabou;
		
		List<Rodada> rodadas;
		
		public void adicionaMedida(int numero){
			//esse metodo retorna true se continuar simulando
			//e retorna false se ja pegou todas as simulacoes
			//aqui vamos adicionar as medidas às rodadas
			
			if(rodadas == null){
				rodadas = new ArrayList<Rodada>();
				
				Rodada novaRodada = new Rodada();
				novaRodada.setMediaInt(numero);
				novaRodada.setNumIteracoes(1);
				
				rodadas.add(novaRodada);
				
				acabou = false;
				
			}else if(rodadas.isEmpty()){
				//comeco de coleta de dados, comecar a primeira rodada
				Rodada novaRodada = new Rodada();
				novaRodada.setMediaInt(numero);
				novaRodada.setNumIteracoes(1);
				
				rodadas.add(novaRodada);
				
				acabou = false;
				
			}
			Rodada rodada = (Rodada) rodadas.get(rodadas.size()-1);
					
			if(rodada.getNumIteracoes() < numMaximoIteracoes){
				//essa rodada ainda pode receber iteracoes
				int iteracoes = rodada.getNumIteracoes()+1;
				rodada.setNumIteracoes(iteracoes);
				
				int media = rodada.getMediaInt();
				int novaMedia = (media + numero)/iteracoes;
				
				rodada.setMediaInt(novaMedia);
				
				acabou = false;
			}else if(rodada.getNumIteracoes() == numMaximoIteracoes)
			{
				//uma nova rodada tem que começar
				//mas pra isso é necessario verificar se ja passou o numero maximo de rodadas
				
				//quantas rodadas eu ja tenho
				int numRodada = rodadas.size();
				
				if(numRodada < numMaximoRodadas){
					//inserir uma nova rodada
					Rodada novaRodada = new Rodada();
					novaRodada.setMediaInt(numero);
					novaRodada.setNumIteracoes(1);
					
					rodadas.add(novaRodada);	
					acabou = false;
				}else if (numRodada == numMaximoRodadas){
					//acabou a coleta de dados
					acabou = true;
				}
			}
		}
		public Boolean getAcabou() {
			return acabou;
		}
		public void setAcabou(Boolean acabou) {
			this.acabou = acabou;
		}
		public int getNumMaximoRodadas() {
			return numMaximoRodadas;
		}
		public void setNumMaximoRodadas(int numMaximoRodadas) {
			this.numMaximoRodadas = numMaximoRodadas;
		}
		public int getNumMaximoIteracoes() {
			return numMaximoIteracoes;
		}
		public void setNumMaximoIteracoes(int numMaximoIteracoes) {
			this.numMaximoIteracoes = numMaximoIteracoes;
		}
		public List<Rodada> getRodadas() {
			return rodadas;
		}
		public void setRodadas(List<Rodada> rodadas) {
			this.rodadas = rodadas;
		}
		public Double getMedidaFinalRodada(int rodadaIndex){
			Rodada rodada = rodadas.get(rodadaIndex);
			
			return rodada.getMedia();
		}
		public int getMediaFinal(){
			int i=1;
			if(rodadas != null){
				for(Rodada rodada : rodadas){
					System.out.println("Media nmc da rodada " + i + ": " + rodada.getMediaInt());
					i++;
				}
			}
			return 0;
		}
	

}
