package br.com.ambientinformatica.onzevencedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ambientinformatica.onzevencedor.dao.AtletaDao;
import br.com.ambientinformatica.onzevencedor.dao.ClubeDAO;
import br.com.ambientinformatica.onzevencedor.entity.Atleta;
import br.com.ambientinformatica.onzevencedor.entity.Clube;

@Service("clubeService")
public class ClubeService {
	@Autowired
	private ClubeDAO dao;

	@Autowired
	private AtletaDao daoAtleta;
	
	public List<Clube> getAllClubes() {
		return dao.findAll();
	}
	
	public List<Clube> getAllClubesTesteBety() {
		return dao.findAllTeste();
	}
	
	public List<Clube> getClubesById(Integer idClube) {
		return dao.findById(idClube);
	}
	
	public List<Clube> getClubesByName(String nomeClube) {
		return dao.findByName(nomeClube);
	}

	public Clube createClube(Clube clube) {
		return dao.create(clube);
	}

	public void updateClube(Clube clube) {
		dao.update(clube);
	}

	public void deleteClube(Clube clube) {
		for(Atleta at: daoAtleta.findByClubId(clube.getId())) {
			daoAtleta.delete(at);		
		}		
		dao.delete(clube);
	}

	public void setDao(ClubeDAO dao) {
		this.dao = dao;
	}

	public AtletaDao getDaoAtleta() {
		return daoAtleta;
	}

	public void setDaoAtleta(AtletaDao daoAtleta) {
		this.daoAtleta = daoAtleta;
	}

	public ClubeDAO getDao() {
		return dao;
	}
}
