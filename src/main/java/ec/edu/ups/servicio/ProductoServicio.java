package ec.edu.ups.servicio;

import java.util.List;

import ec.edu.ups.DAO.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.servicio.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/universidades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductoServicio {

	@Inject
    private ProductoDAO prodDao;

    @POST
    public Response crearProducto(Producto producto) {
    	try {
        prodDao.create(producto);
        ErrorMessage error = new ErrorMessage(1, "ok");
        return Response.status(Response.Status.CREATED).entity(producto).build();
    	}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		}
    }

    @GET
    @Path("/{codigo}")
    public Response leerUniversidad(@PathParam("codigo") int codigo) {
    	try {
        Producto producto = prodDao.read(codigo);
        return Response.ok(producto).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
    }

    @PUT
    @Path("/{codigo}")
    public Response actualizarProducto(@PathParam("codigo") int codigo, Producto producto) {
        try {
    	producto.setCodigo(codigo);
        prodDao.update(producto);
        return Response.ok(producto).build();
        }catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
    }

    @DELETE
    @Path("/{codigo}")
    public Response eliminarProducto(@PathParam("codigo") int codigo) {
        try {
    	prodDao.delete(codigo);
        return Response.noContent().build();
        }catch (Exception e) {
			// TODO: handle exception
			return Response.noContent().build();
		}
    }

    @GET
    public Response listarUniversidades() {
        List<Producto> universidades = prodDao.getAll();
        return Response.ok(universidades).build();
        
    }
}
