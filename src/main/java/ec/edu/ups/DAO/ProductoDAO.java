package ec.edu.ups.DAO;

import java.util.List;

import ec.edu.ups.modelo.Producto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class ProductoDAO {
	

	    @PersistenceContext
	    private EntityManager em;

	    public void create(Producto producto){
	        em.persist(producto);
	    }

	    public void update(Producto producto){
	        em.merge(producto);
	    }

	    public Producto read(int codigo){
	        Producto producto = em.find(Producto.class, codigo);
	        return producto;
	    }

	    public void delete(int codigo){
	        Producto producto = em.find(Producto.class, codigo);
	        System.out.println(producto);
	        em.remove(producto);
	    }

	    public List<Producto> getAll(){
	    	String jpql = "SELECT p FROM Producto p";
			Query q = em.createQuery(jpql, Producto.class);
			return q.getResultList();
	    }


}
