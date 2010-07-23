package Controle;

import java.util.ArrayList;
import java.util.List;

import vo.EventoVo;
import Rede.Canal;
import Rede.Estacao;
import Rede.Evento;
import Rede.Hub;
import Rede.Pacote;
import Rede.Quadro;
import Rede.TipoEvento;
import Simulacao.Tam;
import Simulacao.Tap;
import Simulacao.ncm;
import View.Interface;


/*
 * Trabalho de AD 2010-1
 */
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{		
		ControleCenarios controle = new ControleCenarios();
		controle.intfc();
	}	
		
	
}
