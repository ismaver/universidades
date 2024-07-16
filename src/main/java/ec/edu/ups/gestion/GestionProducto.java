package ec.edu.ups.gestion;

import java.util.List;

import ec.edu.ups.DAO.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import jakarta.inject.Inject;

public class GestionProducto {

	@Inject//injectamos la clase para no necesitar inicializarla
    private ProductoDAO productoDao;

    //metodo para guardar el objeto de la clase
    public void guardarProducto(Producto producto){
        Producto prod = productoDao.read(producto.getCodigo());//buscamos si existe
        if(prod == null)//verificamos si nos devolvio un null
            productoDao.create(producto);//insertamos si no existe
        else
            productoDao.update(producto);//actualizamos si ya existe
    }

    //metodo para actualizar el objeto de la clase
    public void actualizarProducto(Producto producto) throws Exception{
    	Producto prod = productoDao.read(producto.getCodigo());//buscamos si existe
    	if(prod != null)//verificamos si nos devolvio un null
            productoDao.update(producto);//actualizamos si ya existe
        else
            throw new Exception("Producto no existe");//lanzamos un error si es que no existe
    }

    //metodo para borrar el objeto de la clase medante el id
    public void borrarProducto(int codigo){
        productoDao.delete(codigo);
    }


    //metodo para buscar el objeto de la clase medante el id
    public Producto buscarProductoId(int codigo){
        return productoDao.read(codigo);
    }

    //metodos para listar todos los objetos de la clase
    public List<Producto> getProductos(){
        return productoDao.getAll();
    }
}
