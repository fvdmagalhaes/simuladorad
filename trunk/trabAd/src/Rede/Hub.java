package Rede;

import java.util.List;

public class Hub {
	//O hub guarda apenas sua lista de canais tx (rx da esta��o)
	List<Canal> listaCanais;

	public List<Canal> getListaCanais() {
		return listaCanais;
	}

	public void setListaCanais(List<Canal> listaCanais) {
		this.listaCanais = listaCanais;
	}
	
	
	
}
