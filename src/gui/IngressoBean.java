package gui;

import java.util.Calendar;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;

import util.DataDeValidadeException;
import util.PrecoException;
import business.controllers.IngressoController;
import business.controllers.IngressoControllerIF;
import business.model.IngressoAB;

@ManagedBean(name = "ingresso")
public class IngressoBean {
	private IngressoAB ingresso;
	private IngressoControllerIF controller = new IngressoController();
	
	public IngressoAB[] getList(){
		HashMap<String, Object> obj = new HashMap<String, Object>();
		obj.put("idEvento", 1);
		obj.put("valor", 3.25);
		Calendar umDiaQualquer = Calendar.getInstance();
		umDiaQualquer.set(Calendar.YEAR, 2015);
		obj.put("dataDeValidade", umDiaQualquer);
		try {
			controller.create(obj);
		} catch (PrecoException e) {
			// TODO Auto-generated catch block
			System.out.println("NAOAAAAAAA");
			e.printStackTrace();
		} catch (DataDeValidadeException e) {
			// TODO Auto-generated catch block
			System.out.println("NAOAAAAAAA");
			e.printStackTrace();
		}
		
		return controller.listAll();
	}
	
	public IngressoAB[] getList(Long max, Long offset){
		return controller.listAll(offset, max);
		
	}
	
}
