	package br.com.ambientinformatica.onzevencedor.controller;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import br.com.ambientinformatica.onzevencedor.entity.Atleta;
import br.com.ambientinformatica.onzevencedor.entity.Clube;
import br.com.ambientinformatica.onzevencedor.entity.Posicao;
import br.com.ambientinformatica.onzevencedor.service.AtletaService;

@ManagedBean(name = "atletaBean")
@ViewScoped
public class AtletaBean extends BaseBean{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{atletaService}")
	private AtletaService model;
	private Atleta atleta;	
	private String nomeAtleta;
	private List<Atleta> atletas;
	private boolean editMode;
	private Integer filtroIdfClube;
	private Integer idfClube;
	private List<Clube> clubes;
	private Clube clube;

	private List<Posicao> posicoes;
	
	
	public Atleta getAtleta() {
		if (atleta == null) {
			atleta = new Atleta();
		}
		
		return atleta;
	}

	public void salvar() {
		System.out.println("id atleta"+ atleta.getIdf());
		    clube = model.getClubeId(idfClube);
		    atleta.setClube(clube);
		if (atleta.getIdf() == null || atleta.getIdf().intValue() == 0) {
			
			atleta = model.createAtleta(atleta);
			atleta = new Atleta();
			addInfoMessage("Atleta criado com sucesso");
		}else {
			model.updateAtleta(atleta);
			addInfoMessage("Atleta alterado com sucesso");
		}
	}
	
	public void delete() {
		model.deleteAtleta(atleta);
		if (nomeAtleta != null && !nomeAtleta.isEmpty()) {
			atletas = model.getAtletasByName(nomeAtleta);
		}else {
			atletas = model.getAllAtletas();
		}
	}
	
	public void create() {
		this.atleta = new Atleta();
		this.editMode = true;
	}
	
	public void update() {
		this.editMode = true;
	}
	
	public void cancel() {
		this.editMode = false;
	}
	
	
	public void filtrarAtletaPorClube(AjaxBehaviorEvent event) {
		if (filtroIdfClube != null && filtroIdfClube !=0) {
			atletas = model.getAtletasByClubId(filtroIdfClube);			
		}else {
			atletas = model.getAllAtletas();
		}
	}
	
	public void filtrarAtleta(AjaxBehaviorEvent event) {
		if (nomeAtleta != null && !nomeAtleta.isEmpty()) {
			atletas = model.getAtletasByName(nomeAtleta);			
		}else {
			atletas = model.getAllAtletas();
		}
	}
	
	public List<Atleta> getAtletas(){
		if (atletas == null) {
			atletas = model.getAllAtletas();
		}
		return atletas;
	}

	public AtletaService getModel() {
		return model;
	}

	public void setModel(AtletaService model) {
		this.model = model;
	}

	public String getNomeAtleta() {
		return nomeAtleta;
	}

	public void setNomeAtleta(String nomeAtleta) {
		this.nomeAtleta = nomeAtleta;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	

	public void setAtleta(Atleta atleta) {
		this.atleta = atleta;
	}

	public void setAtletas(List<Atleta> atletas) {
		this.atletas = atletas;
	}

	public Integer getFiltroIdfClube() {
		return filtroIdfClube;
	}

	public void setFiltroIdfClube(Integer filtroIdfClube) {
		this.filtroIdfClube = filtroIdfClube;
	}

	public Integer getIdfClube() {
		return idfClube;
	}

	public void setIdfClube(Integer idfClube) {
		this.idfClube = idfClube;
	}

	public List<Clube> getClubes() {
		if(clubes == null) {
			
		  clubes =	model.getAllClubes();
		}
		return clubes ;
	}

	public void setClubes(List<Clube> clubes) {
		this.clubes = clubes;
	}

	public List<Posicao> getPosicoes() {
		if(posicoes == null) {
		posicoes = Arrays.asList(Posicao.values());
		}
		return posicoes;
		}
		

	public void setPosicoes(List<Posicao> posicoes) {
		this.posicoes = posicoes;
	}

	public Clube getClube() {
		return clube;
	}

	public void setClube(Clube clube) {
		this.clube = clube;
	}

	

	
	
	
}
