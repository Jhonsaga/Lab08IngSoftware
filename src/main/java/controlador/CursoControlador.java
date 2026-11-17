package controlador;

import dao.CursoDAO;
import modelo.Curso;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CursoControlador", urlPatterns = {"/CursoControlador"})
public class CursoControlador extends HttpServlet {
    private CursoDAO dao = new CursoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
            
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                List<Curso> lista = dao.getCursos();
                request.setAttribute("listaCursos", lista);
                request.getRequestDispatcher("vistas/frmListarcurso.jsp").forward(request, response);
                break;

            case "modificarForm":
                int idMod = Integer.parseInt(request.getParameter("id"));
                Curso curso = dao.consultarCurso(idMod);
                request.setAttribute("curso", curso);
                request.getRequestDispatcher("vistas/frm_modificarcurso.jsp").forward(request, response);
                break;

            case "eliminar":
                int idElim = Integer.parseInt(request.getParameter("id"));
                String msgElim = dao.eliminarCurso(idElim);
                response.sendRedirect("CursoControlador?accion=listar&m=" + java.net.URLEncoder.encode(msgElim, "UTF-8"));
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    
        String btnRegistrar = request.getParameter("btnRegistrar");
        String btnActualizar = request.getParameter("btnActualizar");

        if (btnRegistrar != null) {
            Curso c = new Curso(
                0,
                request.getParameter("codigo"),
                request.getParameter("nombre"),
                Integer.parseInt(request.getParameter("creditos")),
                request.getParameter("docente")
            );
            String m = dao.insertarCurso(c);
            response.sendRedirect("CursoControlador?accion=listar&m=" + java.net.URLEncoder.encode(m, "UTF-8"));

        } else if (btnActualizar != null) {
            Curso c = new Curso(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("codigo"),
                request.getParameter("nombre"),
                Integer.parseInt(request.getParameter("creditos")),
                request.getParameter("docente")
            );
            String m = dao.modificarCurso(c);
            response.sendRedirect("CursoControlador?accion=listar&m=" + java.net.URLEncoder.encode(m, "UTF-8"));
        }
    }
}