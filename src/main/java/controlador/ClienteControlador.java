package controlador;

import config.Conexion;
import dao.ClienteDAO;
import modelo.Cliente; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClienteControlador", urlPatterns = {"/ClienteControlador"})
public class ClienteControlador extends HttpServlet {
    private ClienteDAO dao = new ClienteDAO();

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Forzar lectura UTF-8 en peticiones GET
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

            
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                List<Cliente> lista = dao.getClientes();
                request.setAttribute("listaClientes", lista);
                request.getRequestDispatcher("vistas/frmListarcliente.jsp").forward(request, response);
                break;

            case "modificarForm":
                int idMod = Integer.parseInt(request.getParameter("id"));
                Cliente cliente = dao.consultarCliente(idMod);
                request.setAttribute("cliente", cliente);
                request.getRequestDispatcher("vistas/frm_modificarcliente.jsp").forward(request, response);
                break;

            case "eliminar":
                int idElim = Integer.parseInt(request.getParameter("id"));
                String msgElim = dao.eliminarCliente(idElim);
                response.sendRedirect("ClienteControlador?accion=listar&m=" + java.net.URLEncoder.encode(msgElim, "UTF-8"));
                break;
        }
    }

    // PASO 2: Peticiones POST (Registrar nuevo o Guardar edición)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Forzar lectura UTF-8 para capturar Ñ y tildes desde los formularios POST
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    
        String btnRegistrar = request.getParameter("btnRegistrar");
        String btnActualizar = request.getParameter("btnActualizar");

        if (btnRegistrar != null) {
            Cliente c = new Cliente(
                0,
                request.getParameter("dni"),
                request.getParameter("apellidos"),
                request.getParameter("nombres"),
                request.getParameter("email"),
                request.getParameter("celular"),
                request.getParameter("direccion")
            );
            String m = dao.insertarCliente(c);
            response.sendRedirect("ClienteControlador?accion=listar&m=" + java.net.URLEncoder.encode(m, "UTF-8"));

        } else if (btnActualizar != null) {
            Cliente c = new Cliente(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("dni"),
                request.getParameter("apellidos"),
                request.getParameter("nombres"),
                request.getParameter("email"),
                request.getParameter("celular"),
                request.getParameter("direccion")
            );
            String m = dao.modificarCliente(c);
            response.sendRedirect("ClienteControlador?accion=listar&m=" + java.net.URLEncoder.encode(m, "UTF-8"));
        }
    }
}