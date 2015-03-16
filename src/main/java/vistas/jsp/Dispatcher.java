package vistas.jsp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistencia.models.entities.Tema;
import vistas.AgregarTemaBean;

@WebServlet("/jsp/*")
public class Dispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private static String PATH_ROOT_VIEW = "/views-jsp/";
    private static final String PATH_HOME = "home";    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    String action = request.getPathInfo().substring(1);
        
        String view;
        switch (action) {
        case AgregarTemaBean.PATH_AGREGAR_TEMA:
            view = action;
            break;
        case "borrarTema":
            view = action;
            break;
        default:
            view = PATH_HOME;
        }

        this.getServletContext().getRequestDispatcher(PATH_ROOT_VIEW + view + ".jsp")
                .forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    String action = request.getPathInfo().substring(1);
	    
        String view;
        switch (action) {
        case AgregarTemaBean.PATH_AGREGAR_TEMA:
            Tema tema = new Tema();
            tema.setNombre(request.getParameter("nombre"));
            tema.setPregunta(request.getParameter("pregunta"));
            AgregarTemaBean teamBean = new AgregarTemaBean();
            teamBean.setTema(tema);
            request.setAttribute("TemaBean", teamBean);
            view = teamBean.process();
            break;
        case "borrarTema":            
            HttpSession session = request.getSession(true);
            session.setAttribute("accesos", 1);
            view = action;
            break;
        default:
            view = PATH_HOME;
        }

        this.getServletContext().getRequestDispatcher(PATH_ROOT_VIEW + view + ".jsp")
                .forward(request, response);
	}

}
