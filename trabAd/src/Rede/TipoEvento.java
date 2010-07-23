package Rede;

public enum TipoEvento {
	
	/**Guarda os tipos de eventos**/
	
	
	//ESTACAO RECEBE PACOTE DE FORA COM UMA TAXA QUALQUER. Acho q esse evento pode ser apagado
	RECEBE_PACOTE,
	//ESTACAO SENTE O MEIO PARA ENVIAR O QUADRO
	SENTE_MEIO,
	//ESTACAO TRANSMITE O QUADRO PARA TX
	TRANSMITE_QUADRO,
	//HUB RETRANSMITE O QUADRO PARA OS TX DELE
	RETRANSMITE_QUADRO,
	//ESTACAO RECEBE O PACOTE RETRANSMITIDO
	RECEBE_QUADRO,
	//Apenas guarda o tempo de final de reforço de colisao
	REFORCO_COLISAO
}
