package vistas.jsp;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;

import controladores.ControllerFactory;
import utils.Converter;
import vistas.AccesoTemasBean;
import vistas.AgregarTemaBean;
import vistas.BorrarTemaBean;
import vistas.ConsultarVotosBean;
import vistas.TemasVotoBean;
import vistas.VotarTemaBean;

@WebServlet("/jsp/*")
public class Dispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private static String PATH_ROOT_VIEW = "/views/jsp/";
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
        case TemasVotoBean.PATH_TEMAS_VOTOS:
            request.setAttribute("TemasVotoBean", new TemasVotoBean(controller));
            view = action;
            break;
        case VotarTemaBean.PATH_VOTAR_TEMA:
            view = TemasVotoBean.PATH_TEMAS_VOTOS;
            break;
        case ConsultarVotosBean.PATH_CONSULTAR_VOTOS:
            request.setAttribute("ConsultarVotosBean", new ConsultarVotosBean(controller));
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

            AgregarTemaBean agregarTemaBean = new AgregarTemaBean(controller);
            agregarTemaBean.setTemaPregunta(request.getParameter("pregunta"));
            agregarTemaBean.setTemaNombre(request.getParameter("nombre"));
            
            request.setAttribute("TemaBean", agregarTemaBean);
            view = agregarTemaBean.process();
            break;
        case AccesoTemasBean.PATH_ACCESO_TEMA:
            session = request.getSession(true);
            
            AccesoTemasBean accesoTemasBean = new AccesoTemasBean();
            accesoTemasBean.setClave(request.getParameter("clave"));
            view = accesoTemasBean.process();            
            if(!accesoTemasBean.isAccesoDenegado()){
                session.setAttribute(ACCESO_SESSION_ID, 1);
                request.setAttribute("BorrarTemaBean", new BorrarTemaBean(controller));
            }else{
                session.setAttribute(ACCESO_SESSION_ID, 0);
                request.setAttribute("AccesoTemaBean", accesoTemasBean);
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
        case TemasVotoBean.PATH_TEMAS_VOTOS:
            TemasVotoBean temasVotosBean = new TemasVotoBean(controller);
            temasVotosBean.setTemaId(request.getParameter("id"));
            request.setAttribute("TemasVotoBean", temasVotosBean);
            view = temasVotosBean.process();
            break;
        case VotarTemaBean.PATH_VOTAR_TEMA:
            VotarTemaBean votarTemaBean = new VotarTemaBean(controller);
            votarTemaBean.setTemaId(request.getParameter("id"));
            votarTemaBean.setEscolaridad(request.getParameter("escolaridad"));
            votarTemaBean.setVoto(request.getParameter("voto"));
            votarTemaBean.setIp(InetAddress.getLocalHost().getHostAddress());
            request.setAttribute("VotarTemaBean", votarTemaBean);
            view = votarTemaBean.process();
            break;            
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
            request.setAttribute("BorrarTemaBean", new BorrarTemaBean(controller));
            return BorrarTemaBean.PATH_BORRAR_TEMA;
        }else{
            return AccesoTemasBean.PATH_ACCESO_TEMA;
        }	
	}

}
