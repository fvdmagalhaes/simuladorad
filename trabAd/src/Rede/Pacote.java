package Rede;



public class Pacote {
	int id;
	int tamanho;
	Estacao estacao;
	int sequenciaEnviada;
	//public boolean recebidoPelaEstacao;
	//em milisegundos
	Quadro ultimoQuadroEnviado;
	Double tam;
	int ncm;
	

	public Double getTam() {
		return tam;
	}

	public void setTam(Double tam) {
		this.tam = tam;
	} 
	public int getNcm() {
		return ncm;
	}

	public void setNcm(int ncm) {
		this.ncm = ncm;
	} 
	
	public Quadro getUltimoQuadroEnviado(){
		return ultimoQuadroEnviado;
	}

	public void setUltimoQuadroEnviado(Quadro ultimoQuadroEnviado){
		this.ultimoQuadroEnviado = ultimoQuadroEnviado;		
	}
	
	public int getSequenciaEnviada() {
		return sequenciaEnviada;
	}

	public void setSequenciaEnviada(int sequenciaEnviada) {
		this.sequenciaEnviada = sequenciaEnviada;
	}
	private static int ultimoId=0;
	
	public Pacote(){
		ultimoId++;
		id=ultimoId;
	}
	
	public Estacao getEstacao() {
		return estacao;
	}
	public void setEstacao(Estacao estacao) {
		this.estacao = estacao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
}
