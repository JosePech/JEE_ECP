package vistas;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AccesoTemasBean {
    
    public static final String PATH_ACCESO_TEMA = "accesoTema";
        
    private Integer accesoDenegado;
    
    private final String CLAVE_OK = "666";
    
    private String clave;

    public String process(){
        try{
        if(getClave() != null && getClave().equals(CLAVE_OK)){
            setAccesoDenegado(0);
            return BorrarTemaBean.PATH_BORRAR_TEMA;
        }
        else{
            setAccesoDenegado(1);
            return PATH_ACCESO_TEMA;
        }
        }catch(Exception e){
            setAccesoDenegado(1);
            return PATH_ACCESO_TEMA;
        }
    }
    
    public Integer getAccesoDenegado() {
        return accesoDenegado;
    }

    private void setAccesoDenegado(Integer accesoDenegado) {
        this.accesoDenegado = accesoDenegado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
