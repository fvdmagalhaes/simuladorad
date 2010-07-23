package Simulacao;

import java.util.ArrayList;
import java.util.List;

import Controle.Saida;

public class ncm {



		//Numero de rodadas pelo qual teremos que colher as medidas
		int numMaximoRodadas;
		
		//Numero de iterações que cada rodada terá
		int numMaximoIteracoes;
		
		//True se já tivermos colhido todas as rodadas
		Boolean acabou;
		
		List<Rodada> rodadas;
		
		public void adicionaMedida(int numero,Double tempo, Double faseTransiente){
			//O metodo verifica se o tempo em que vamos colher a medida é maior ou igual que a fase transiente. Se for menor não colhemos a medida
			if(tempo >= faseTransiente)
			{
			
				if(rodadas == null){
					//Caso ainda nao existam rodadas cria uma nova
					rodadas = new ArrayList<Rodada>();
					
					Rodada novaRodada = new Rodada();
					
					//Adiciona o numero de colisoes da mensagem
					novaRodada.setMediaInt(numero);
					//A rodada só teve 1 iteração até agora
					novaRodada.setNumIteracoes(1);
					//Adiciona a rodada na lista
					rodadas.add(novaRodada);
					//Ainda não acabou de simular
					acabou = false;
					
				}else if(rodadas.isEmpty()){
					//começo de coleta de dados, vamos comecar a primeira rodada
					Rodada novaRodada = new Rodada();
					novaRodada.setMediaInt(numero);
					novaRodada.setNumIteracoes(1);
					
					rodadas.add(novaRodada);
					
					acabou = false;
					
				}
				//pega a rodada anterior
				Rodada rodada = (Rodada) rodadas.get(rodadas.size()-1);
						
				
				if(rodada.getNumIteracoes() < numMaximoIteracoes){
					//essa rodada ainda pode receber iteracoes
					int iteracoes = rodada.getNumIteracoes()+1;
					rodada.setNumIteracoes(iteracoes);
					//Calcula a nova media da rodada(Média anterior mais o novo numero dividido pelo numero de iterações até o momento)
					int media = rodada.getMediaInt();
					int novaMedia = (media + numero)/iteracoes;
					
					rodada.setMediaInt(novaMedia);
					
					acabou = false;
				}else if(rodada.getNumIteracoes() == numMaximoIteracoes)
				{
					//Se ainda nao passou o numero maximo de rodadas vamos criar a proximo rodada pois a atual acabou
					
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
			
			//Imprime as medidas
			int i=1;
			if(rodadas != null){
				for(Rodada rodada : rodadas){
					Saida.saida="Media nmc da rodada " + i + ": " + rodada.getMediaInt()+"\n";
					Saida.escreve();
					i++;
				}
			}
			return 0;
		}
	

}
