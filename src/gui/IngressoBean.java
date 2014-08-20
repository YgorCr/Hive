package gui;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import javax.faces.context.FacesContext;

import util.DataDeValidadeException;
import util.PrecoException;
import business.controllers.IngressoController;
import business.controllers.IngressoControllerIF;
import business.model.EventoAB;
import business.model.IngressoAB;
import business.model.UsuarioAB;
//@ViewScoped
//@ManagedBean(name = "ingresso")
public class IngressoBean {
	private IngressoAB ingresso = new IngressoAB() {
	};
	private EventoAB evento = new EventoAB(){};
	private UsuarioAB usuario = new UsuarioAB(){};
	private IngressoControllerIF controller = new IngressoController();
	
	public IngressoAB[] getList(){
		return this.controller.listAll();
	}
	
	public IngressoAB[] getList(Long max, Long offset){
		return this.controller.listAll(offset, max);
		
	}
	
	public String getId(){
		if(this.ingresso.getId() == null)
			return "";
		return this.ingresso.getId().toString();
	}
	
	public String getCodigo(){
		return this.ingresso.getCodigo() == null ? "": ingresso.getCodigo();
	}
	
	public void setId(String id){
		this.ingresso.setId(Long.parseLong(id));
	}
	
	public void setCodigo(String codigo){
		this.ingresso.setCodigo(codigo);
	}
	
	public String getIdEvento(){
		if(this.ingresso.getIdEvento() == null)
			return "";
		return this.ingresso.getIdEvento().toString();
	}
	
	public String getValor(){
		if(this.ingresso.getValor() == null)
			return "";
		return this.ingresso.getValor().toString();
	}
	
	public String getIdUsuario(){
		if(this.ingresso.getIdUsuario() == null)
			return "";
		return this.ingresso.getIdUsuario().toString();
	}
	
	public String getDataDeValidade(){
		if(this.ingresso.getDataDeValidade() == null)
			return "";
		return this.ingresso.getDataDeValidade().toString();
	}
	
	public void setIdEvento(String id){
		this.ingresso.setIdEvento(Long.parseLong(id));
	}
	
	public void setValor(String valor){
		this.ingresso.setValor(Double.parseDouble(valor));
	}
	
	public void setIdUsuario(String id){
		this.ingresso.setIdUsuario(Long.parseLong(id));
	}
	
	public void setDataDeValidade(String data){ //consertar isso aqui, colocar uma data aqui, converter de string da interface para calendar
		this.ingresso.setDataDeValidade(Calendar.getInstance());
	}
	
	public String create(){
		HashMap<String,Object> objeto = new HashMap<String, Object>();
		objeto.put("valor", ingresso.getValor());
		Calendar data = Calendar.getInstance();
		data.set(Calendar.YEAR, 2015);
		objeto.put("dataDeValidade", data); //descobrir a data que vem no form
		objeto.put("idEvento", ingresso.getIdEvento()); //pegar no futuro um id real
		objeto.put("idUsuario", ingresso.getIdUsuario()); //pegar usuario logado
		try {
			controller.create(objeto);
		} catch (PrecoException | DataDeValidadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "list?faces-redirect=true";
	}
	
	public String update(){
		//System.out.println(ingresso.getId()+" "+ingresso.getValor() + " " +ingresso.getIdEvento() + " "+ ingresso.getIdUsuario() );
		
		HashMap<String,Object> objeto = new HashMap<String, Object>();
		objeto.put("valor", ingresso.getValor());
		Calendar data = Calendar.getInstance();
		data.set(Calendar.YEAR, 2015);
		objeto.put("dataDeValidade", data); //descobrir a data que vem no form
		objeto.put("idEvento", ingresso.getIdEvento()); //pegar no futuro um id real
		objeto.put("idUsuario", ingresso.getIdUsuario()); //pegar usuario logado
		try {
			controller.update(ingresso.getId(),objeto);
		} catch (PrecoException | DataDeValidadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return "list?faces-redirect=true";
	}
	
	public String delete(){
		controller.delete(ingresso.getId());
		
		return "list?faces-redirect=true";
	}
	
	public void show(String id){
		this.ingresso = controller.get(Long.parseLong(id));
		System.out.println("ID "+controller.get(Long.parseLong(id)).getId());
	}
	
}
