package service;

import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.Cliente;
import model.Cuenta;
@Service
public class CuentasServiceImpl implements CuentasService {
	@PersistenceContext(unitName = "bancaPU")
	EntityManager em;
	@Override
	public List<Cuenta> obtenerCuentasNoCliente(int dni) {
		String jpql="Select c From Cuenta c where c not in ";
		jpql+="(Select distinct c From Cuenta c join c.clientes t Where t.dni=?1)";
		TypedQuery<Cuenta> query=em.createQuery(jpql,Cuenta.class);
		query.setParameter(1, dni);
		return query.getResultList();
	}
	@Transactional
	@Override
	public void actualizarCuenta(int numeroCuenta, int dni) {
		Cuenta cuenta=em.find(Cuenta.class, numeroCuenta);
		Cliente cliente=em.find(Cliente.class, dni);
//		cuenta.getClientes().add(cliente); No hace nada , no hay fallo pero no asigna cuenta
//		em.merge(cuenta);
		cliente.getCuentas().add(cuenta);// la propietaria de la relacion hace el merge
		em.merge(cliente);

	}
 	  @Override
	  public void ingresar(int numeroCuenta, double cantidad) {
		Cuenta cuenta=em.find(Cuenta.class, numeroCuenta);
    		cuenta.setSaldo(cuenta.getSaldo()+cantidad);
		em.merge(cuenta);
	}
	

}
