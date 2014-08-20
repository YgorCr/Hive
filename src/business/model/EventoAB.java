package business.model;

import java.util.Calendar;
import java.util.LinkedList;

public abstract class EventoAB implements EventoIF{
	private Long id;
	private String nome;
	private String descricao;
	private Calendar dataInicio;
	private Calendar dataFim;
	private String endereco;
	private Long idDono;								// id do Usuário dono do evento
	private LinkedList<Long> listaModeradores;			// ids dos moderadores
	private LinkedList<Long> listaParticipantes;		// ids dos participantes
	//private LinkedList<String> listaTickets;				// id dos Tickets do evento	
	private Long lotacao;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Calendar getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public Calendar getDataFim() {
		return dataFim;
	}
	
	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Long getIdDoDono() {
		return idDono;
	}
	
	public void setIdDoDono(Long idDono) {
		this.idDono = idDono;
	}
	
	public LinkedList<Long> getListaModeradores() {
		return listaModeradores;
	}
	
	public void setListaModeradores(LinkedList<Long> listaModeradores) {
		this.listaModeradores = listaModeradores;
	}
	
	public LinkedList<Long> getListaParticipantes() {
		return listaParticipantes;
	}
	
	public void setListaParticipantes(LinkedList<Long> listaParticipantes) {
		this.listaParticipantes = listaParticipantes;
	}
	/*
	public LinkedList<Long> getListaTickets() {
		return listaTickets;
	}
	
	public void setListaTickets(LinkedList<Long> listaTickets) {
		this.listaTickets = listaTickets;
	}
	*/
	public Long getLotacao() {
		return lotacao;
	}

	public void setLotacao(Long lotacao) {
		this.lotacao = lotacao;
	}
	
}
