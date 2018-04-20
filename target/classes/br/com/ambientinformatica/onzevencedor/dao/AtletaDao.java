package br.com.ambientinformatica.onzevencedor.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ambientinformatica.onzevencedor.entity.Atleta;

@Repository("atletaDao")
public class AtletaDao {
	@PersistenceContext
	protected EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Atleta> findAll(){
		String jpql = "SELECT a FROM Atleta a ORDER BY a.nome";
		Query query = entityManager.createQuery(jpql);
		List<Atleta> atleta = (List<Atleta>) query.getResultList();
		return atleta;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Atleta> findByClubId(Integer id){
		String jpql = "SELECT a FROM Atleta a WHERE a.clube.id = :id ORDER BY a.nome";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id );
		List<Atleta> atleta = (List<Atleta>) query.getResultList();
		return atleta;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Atleta> findByName(String nomeAtleta){
		String jpql = "SELECT a FROM Atleta a WHERE nome LIKE :nome ORDER BY a.nome";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("nome", nomeAtleta + "%");
		List<Atleta> atleta = (List<Atleta>) query.getResultList();
		return atleta;
	}
	
	@Transactional
	public Atleta create(Atleta atleta) {
		entityManager.persist(atleta);
		return atleta;
	}
	
	@Transactional
	public void update(Atleta atleta) {
		entityManager.merge(atleta);
	}
	
	@Transactional
	public void delete(Atleta atleta) {
		atleta = entityManager.find(Atleta.class, atleta.getIdf());
		entityManager.remove(atleta);
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
