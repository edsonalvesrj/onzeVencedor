package br.com.ambientinformatica.onzevencedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ambientinformatica.onzevencedor.dao.AtletaDao;
import br.com.ambientinformatica.onzevencedor.dao.ClubeDAO;
import br.com.ambientinformatica.onzevencedor.entity.Atleta;
import br.com.ambientinformatica.onzevencedor.entity.Clube;

@Service("atletaService")
public class AtletaService {
	@Autowired
	private AtletaDao dao;
	
	@Autowired
	private ClubeDAO daoCB;
	
	public List<Clube> getAllClubes(){
		return daoCB.findAll();
	}
	
	public Clube getClubeId(Integer idClube) {	
		
		return  daoCB.findById(idClube).get(0);
	}
	
	public List<Atleta> getAllAtletas(){
		return dao.findAll();
	}
	
	
	public List<Atleta> getAtletasByClubId(Integer id){
		return dao.findByClubId(id);
	}
	
	public List<Atleta> getAtletasByName(String nomeAtleta){
		return dao.findByName(nomeAtleta);
	}
	
	public Atleta createAtleta(Atleta atleta) {
		return dao.create(atleta);
	}
	
	public void updateAtleta(Atleta atleta) {
		dao.update(atleta);
	}
	
	public void deleteAtleta(Atleta atleta) {
		dao.delete(atleta);
	}
	
	public void setDao(AtletaDao dao) {
		this.dao = dao;
	}


	public ClubeDAO getDaoCB() {
		return daoCB;
	}


	public void setDaoCB(ClubeDAO daoCB) {
		this.daoCB = daoCB;
	}
	
}
