package Rede;

public class Quadro {
	
	//Tamanho do quadro em bits: 1000 bytes = 1000 * 8 bits = 8000 bits
	public final static int TAMANHO = 8000;
	
	int id;
	int numeroSequencia;
	Pacote pacote;
	
	public Pacote getPacote() {
		return pacote;
	}
	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumeroSequencia() {
		return numeroSequencia;
	}
	public void setNumeroSequencia(int numeroSequencia) {
		this.numeroSequencia = numeroSequencia;
	}
	
	//Outros dados necessarioss...
	
	
}
