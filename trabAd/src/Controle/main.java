package Controle;

import Rede.Canal;
import Rede.Estacao;
import Rede.Evento;
import Rede.Hub;
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
		
		//adiciona as informacoes das estacoes
		e1.setId(1);
		e1.setRx(rx1);
		e1.setTx(tx1);
		
		e2.setId(2);
		e2.setRx(rx2);
		e2.setTx(tx2);
		
		e3.setId(3);
		e3.setRx(rx3);
		e3.setTx(tx3);
		
		e4.setId(4);
		e4.setRx(rx4);
		e4.setTx(tx4);
		
		//adiciona as informacoes dos canais
		tx1.setOcioso(true);
		tx1.setTamanho(100);
		
		rx1.setOcioso(true);
		rx1.setTamanho(100);
	
		tx2.setOcioso(true);
		tx2.setTamanho(80);
		
		rx2.setOcioso(true);
		rx2.setTamanho(80);
		
		tx3.setOcioso(true);
		tx3.setTamanho(60);
		
		rx3.setOcioso(true);
		rx3.setTamanho(60);

		tx4.setOcioso(true);
		tx4.setTamanho(40);
		
		rx4.setOcioso(true);
		rx4.setTamanho(40);
		
	/*Agora podemos iniciar nossa rede... 
	 * temos que pegar do usuario que cenario que ele quer simular
	*	Os cenarios possiveis estao na descricao do trabalho
	*	Para a rede iniciar temos que no tempo 0 ja gerar os pacotes,
	*	calcular as taxas de chegadas de cada estacao tb de acordo com o que foi escolheido,
	*	exponencial ou deterministica	
	*/
		//para começar a funcionar a parada, vou simular o cenario 1, depois a gente muda pra todos
		
		
		
		
		
	}
	

}
