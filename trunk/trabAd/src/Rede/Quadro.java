package Rede;

public class Quadro {
	
	//Tamanho do quadro em bits: 1000 bytes = 1000 * 8 bits = 8000 bits
	public final static int TAMANHO = 8000;
	
	//guarda o ultimo id gerado
	private static int ultimoId=0;

	int id;
	//Guarda o numero do quadro relativo ao pacote, 1 se for o primeiro quadro a ser enviado do pacote, 2 se for o segundo..
	int numeroSequencia;
	//Verdadeiro se o quadro já foi confirmado
	boolean quadroConfirmado;
	
	//Guarda as medidas do quadro
	Double tap;
	Double tam;
	Double tempoOcupado;
	

	public Double getTempoOcupado(){
		return tempoOcupado;
	}
	public void setTempoOcupado(Double tempoOcupado){
		this.tempoOcupado = tempoOcupado;
	}
	public Double getTap() {
		return tap;
	}

	public void setTap(Double tap) {
		this.tap = tap;
	}

	public Double getTam() {
		return tam;
	}

	public void setTam(Double tam) {
		this.tam = tam;
	}
	
	
	public static int getUltimoId() {
		return ultimoId;
	}

	public static void setUltimoId(int ultimoId) {
		Quadro.ultimoId = ultimoId;
	}

	public boolean isQuadroConfirmado() {
		return quadroConfirmado;
	}

	public void setQuadroConfirmado(boolean quadroConfirmado) {
		this.quadroConfirmado = quadroConfirmado;
	}

	public static int getTamanho() {
		return TAMANHO;
	}
	Pacote pacote;
	
	public Quadro(){
		ultimoId++;
		id=ultimoId;
	}
	
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

	
}
