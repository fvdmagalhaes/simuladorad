package Simulacao;

public class Rodada {
	/**Guarda os parametros utilizados para calcular as estatisticas. Eles sao usados nas classes Tam, Tap e Ncm**/
	int numIteracoes;
	Double media;
	int mediaInt;
	public int getNumIteracoes() {
		return numIteracoes;
	}
	public void setNumIteracoes(int numIteracoes) {
		this.numIteracoes = numIteracoes;
	}
	public Double getMedia() {
		return media;
	}
	public void setMedia(Double media) {
		this.media = media;
	}
	
	public int getMediaInt()
	{
		return mediaInt;
	}
	public void setMediaInt(int mediaInt)
	{
		this.mediaInt = mediaInt;
	}
	
}
