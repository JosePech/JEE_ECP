package vistas.jsp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;

import controladores.ControllerFactory;
import persistencia.models.entities.Tema;
import utils.Converter;
import vistas.AccesoTemasBean;
import vistas.AgregarTemaBean;
import vistas.BorrarTemaBean;

@WebServlet("/jsp/*")
public class Dispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private static String PATH_ROOT_VIEW = "/views-jsp/";
    private static final String PATH_HOME = "home";
    private static final String ACCESO_SESSION_ID = "accesoLista";
    private HttpSession session;
    private ControllerFactory controller;

	@Override
    public void init() throws ServletException {
	    controller = ControllerFactory.getFactory();
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    String action = request.getPathInfo().substring(1);
        
        String view;
        switch (action) {
        case AgregarTemaBean.PATH_AGREGAR_TEMA:
            view = action;
            break;
        case AccesoTemasBean.PATH_ACCESO_TEMA:
            view = validarAccesoTemas(request);
            break;
        case BorrarTemaBean.PATH_BORRAR_TEMA:
            view = validarAccesoTemas(request);
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
            
            AgregarTemaBean agregatTemaBean = new AgregarTemaBean(controller);
            agregatTemaBean.setTema(tema);
            
            request.setAttribute("TemaBean", agregatTemaBean);
            view = agregatTemaBean.process();
            break;
        case AccesoTemasBean.PATH_ACCESO_TEMA:
            session = request.getSession(true);
            
            AccesoTemasBean accesoTemasBean = new AccesoTemasBean();
            accesoTemasBean.setClave(request.getParameter("clave"));
            view = accesoTemasBean.process();
            request.setAttribute("AccesoTemaBean", accesoTemasBean);
            if(!accesoTemasBean.isAccesoDenegado()){
                session.setAttribute(ACCESO_SESSION_ID, 1);
            }else{
                session.setAttribute(ACCESO_SESSION_ID, 0);
            }
            break;
        case BorrarTemaBean.PATH_BORRAR_TEMA:              
            session = request.getSession(true);
            Integer acceso = Converter.parseInt(session.getAttribute(ACCESO_SESSION_ID));
            LogManager.getLogger(Dispatcher.class).debug(acceso);
            if(acceso != null && acceso == 1){
                BorrarTemaBean borrarTemaBean = new BorrarTemaBean(controller);
                Integer id = Converter.parseInt(request.getParameter("id"));
                borrarTemaBean.setTemaId(id);
                view = borrarTemaBean.process();
                request.setAttribute("BorrarTemaBean", borrarTemaBean);
            }else{
                view = AccesoTemasBean.PATH_ACCESO_TEMA;
            }            
            LogManager.getLogger(Dispatcher.class).debug(view);
        default:
            view = PATH_HOME;
        }

        this.getServletContext().getRequestDispatcher(PATH_ROOT_VIEW + view + ".jsp")
                .forward(request, response);
	}
	
	private String validarAccesoTemas(HttpServletRequest request){
	    session = request.getSession(true);
        Integer acceso = Converter.parseInt(session.getAttribute(ACCESO_SESSION_ID));
        if(acceso != null && acceso == 1){
            BorrarTemaBean borrarTemaBean = new BorrarTemaBean(controller);
            request.setAttribute("BorrarTemaBean", borrarTemaBean);
            return BorrarTemaBean.PATH_BORRAR_TEMA;
        }else{
            return AccesoTemasBean.PATH_ACCESO_TEMA;
        }	
	}

}
