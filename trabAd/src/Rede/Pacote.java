package Rede;



public class Pacote {
	int id;
	int tamanho;
	Estacao estacao;
	private static int ultimoId=-1;
	
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
