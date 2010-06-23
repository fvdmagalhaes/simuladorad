package Rede;

import java.util.List;


public class Estacao {
	int id;
	Canal tx;
	Canal rx;
	int taxaDeChegada;

		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTaxaDeChegada() {
		return taxaDeChegada;
	}
	public void setTaxaDeChegada(int taxaDeChegada) {
		this.taxaDeChegada = taxaDeChegada;
	}
	public Canal getTx() {
		return tx;
	}
	public void setTx(Canal tx) {
		this.tx = tx;
	}
	public Canal getRx() {
		return rx;
	}
	public void setRx(Canal rx) {
		this.rx = rx;
	}
	public void enviaPacote (Pacote pacote){
		
	}
	public void recebePacote (Pacote pacote){
		
	}
}
