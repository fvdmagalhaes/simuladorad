package Simulacao;

import java.util.ArrayList;
import java.util.List;

import Controle.Saida;

public class Tam {
	
	int numMaximoRodadas;
	
	int numMaximoIteracoes;
	
	Boolean acabou;
	
	List<Rodada> rodadas;
	
	public void adicionaMedida(Double tempo, Double tempoEvento, Double faseTransiente){
		//esse metodo retorna true se continuar simulando
		//e retorna false se ja pegou todas as simulacoes
		//aqui vamos adicionar as medidas �s rodadas
		
		if(tempoEvento >= faseTransiente)
		{
			if(rodadas == null){
				rodadas = new ArrayList<Rodada>();
				
				Rodada novaRodada = new Rodada();
				novaRodada.setMedia(tempo);
				novaRodada.setNumIteracoes(1);
				
				rodadas.add(novaRodada);
				
				acabou = false;
				
			}else if(rodadas.isEmpty()){
				//come�o de coleta de dados
				Rodada novaRodada = new Rodada();
				novaRodada.setMedia(tempo);
				novaRodada.setNumIteracoes(1);
				
				rodadas.add(novaRodada);
				
				acabou = false;
				
			}
			Rodada rodada = (Rodada) rodadas.get(rodadas.size()-1);
					
			if(rodada.getNumIteracoes() < numMaximoIteracoes){
				//essa rodada ainda pode receber iteracoes
				int iteracoes = rodada.getNumIteracoes()+1;
				rodada.setNumIteracoes(iteracoes);
				
				Double media = rodada.getMedia();
				Double novaMedia = (media + tempo)/iteracoes;
				
				rodada.setMedia(novaMedia);
				
				acabou = false;
			}else if(rodada.getNumIteracoes() == numMaximoIteracoes)
			{
				//uma nova rodada tem que come�ar
				//� necessario verificar se ja passou o numero maximo de rodadas
				
				//quantas rodadas eu ja tenho
				int numRodada = rodadas.size();
				
				if(numRodada < numMaximoRodadas){
					//inserir uma nova rodada
					Rodada novaRodada = new Rodada();
					novaRodada.setMedia(tempo);
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
	
	public Double getMediaFinal(){
		int i=1;
		if(rodadas != null){
			for(Rodada rodada : rodadas){
				Saida.saida="Media Tam da rodada " + i + ": " + rodada.getMedia();
				Saida.saida+="\n";
				Saida.escreve();
				i++;
			}
		}
		return null;
	}
}
