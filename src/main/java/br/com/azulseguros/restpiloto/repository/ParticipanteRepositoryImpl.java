package br.com.azulseguros.restpiloto.repository;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.azulseguros.restpiloto.entity.Participante;

@Named("participante-repository")
@RequestScoped
public class ParticipanteRepositoryImpl implements Repository<Participante> {

	@PersistenceContext(unitName = "PilotoPU")
	private EntityManager entityManager;

	public ParticipanteRepositoryImpl() {
		super();
	}
	
	public ParticipanteRepositoryImpl(EntityManager manager) {
		this.entityManager = manager;
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void add(Participante item) {
		entityManager.persist(item);
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void update(Participante item) {
		entityManager.merge(item);
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void remove(Participante item) {
		entityManager.remove(item);
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void remove(Integer id) {
		Participante toBeDelete = entityManager.find(Participante.class, id);
		entityManager.remove(toBeDelete);
	}

	public List<Participante> query(Integer id) {
		TypedQuery<Participante> query = null;
		if (null != id) {
			query = entityManager.createNamedQuery("Participante.findById", Participante.class);
			query.setParameter("id", id);
		} else {
			query = entityManager.createNamedQuery("Participante.findAll", Participante.class);
		}
		List<Participante> results = query.getResultList();
		return results;
	}

}
