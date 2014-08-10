package gui;

import javax.faces.bean.ManagedBean;

import business.controllers.IngressoController;
import business.controllers.IngressoControllerIF;
import business.model.IngressoAB;

@ManagedBean(name = "ingresso")
public class IngressoBean {
	private IngressoAB ingresso;
	private IngressoControllerIF controller = new IngressoController();
	
	public IngressoAB[] getList(){
		return controller.listAll();
	}
	
	public IngressoAB[] getList(Long max, Long offset){
		return controller.listAll(offset, max);
		
	}
	
}
