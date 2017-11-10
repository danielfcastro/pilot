package br.com.azulseguros.restpiloto.repository;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.azulseguros.restpiloto.entity.Projeto;

@Named("projeto-repository")
@RequestScoped
public class ProjetoRepositoryImpl implements Repository<Projeto> {

	@PersistenceContext(unitName = "PilotoPU")
	private EntityManager entityManager;

	public ProjetoRepositoryImpl() {
		super();
	}
	
	public ProjetoRepositoryImpl(EntityManager manager) {
		this.entityManager = manager;
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void add(Projeto item) {
		entityManager.persist(item);
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void update(Projeto item) {
		entityManager.merge(item);
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void remove(Projeto item) {
		entityManager.remove(item);
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void remove(Integer id) {
		Projeto toBeDelete = entityManager.find(Projeto.class, id);
		entityManager.remove(toBeDelete);
	}

	public List<Projeto> query(Integer id) {
		TypedQuery<Projeto> query = null;
		if (null != id) {
			query = entityManager.createNamedQuery("Projeto.findBookById", Projeto.class);
			query.setParameter("id", id);
		} else {
			query = entityManager.createNamedQuery("Projeto.findAll", Projeto.class);
		}
		List<Projeto> results = query.getResultList();
		return results;
	}

	public List<Projeto> query(String infix) {
		TypedQuery<Projeto> query = null;
		if (null != infix) {
			query = entityManager.createNamedQuery("Projeto.findBookById", Projeto.class);
			query.setParameter("id", id);
		} else {
			query = entityManager.createNamedQuery("Projeto.findAll", Projeto.class);
		}
		List<Projeto> results = query.getResultList();
		return results;
	}	
	//SELECT * from TableName Where ColumnName LIKE 'o hai%'

}
