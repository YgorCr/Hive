package business.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.HashMap;

public abstract class IngressoAB implements IngressoIF{
	private Long id; //precisa disso?
	private Long idEvento;
	private Long idUsuario;
	private Double valor;
	private Calendar dataDeValidade;
	private Boolean utilizado;
	private String codigo;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdEvento() {
		return idEvento;
	}
	
	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Calendar getDataDeValidade() {
		return dataDeValidade;
	}
	
	public void setDataDeValidade(Calendar dataDeValidade) {
		this.dataDeValidade = dataDeValidade;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the utilizado
	 */
	public Boolean getUtilizado() {
		return utilizado;
	}

	/**
	 * @param utilizado the utilizado to set
	 */
	public void setUtilizado(Boolean utilizado) {
		this.utilizado = utilizado;
	}
	
}
