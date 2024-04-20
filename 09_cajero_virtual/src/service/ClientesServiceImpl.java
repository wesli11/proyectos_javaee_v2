package service;

import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.Cliente;
@Service
public class ClientesServiceImpl implements ClientesService {
	@PersistenceContext(unitName = "bancaPU")
	EntityManager em;
	@Override
	public List<Cliente> obtenerClientes() {
		TypedQuery<Cliente> query=em.createNamedQuery("Cliente.findAll", Cliente.class);
		return query.getResultList();
	}
	@Transactional
	@Override
	public void actualizarCliente(Cliente cliente) {
		em.merge(cliente);

	}

}
