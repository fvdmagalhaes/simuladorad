package Rede;


public class Evento {
	Double tempo;
	Evento proximoEvento;
	Evento eventoAnterior;
	TipoEvento tipo;
	Quadro quadro;
	Estacao estacao;
	Pacote pacote;
	
	public Double getTempo() {
		return tempo;
	}
	public void setTempo(Double tempo) {
		this.tempo = tempo;
	}
	public Evento getProximoEvento() {
		return proximoEvento;
	}
	public void setProximoEvento(Evento proximoEvento) {
		this.proximoEvento = proximoEvento;
	}
	public Evento getEventoAnterior() {
		return eventoAnterior;
	}
	public void setEventoAnterior(Evento eventoAnterior) {
		this.eventoAnterior = eventoAnterior;
	}
	public TipoEvento getTipo() {
		return tipo;
	}
	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}
	public Quadro getQuadro() {
		return quadro;
	}
	public void setQuadro(Quadro quadro) {
		this.quadro = quadro;
	}	
	public Estacao getEstacao() {
		return estacao;
	}
	public void setEstacao(Estacao estacao) {
		this.estacao = estacao;
	}
	public Pacote getPacote(){
		return this.pacote;
	}
	public void setPacote(Pacote pacote){
		this.pacote=pacote;
	}
	
	
}
