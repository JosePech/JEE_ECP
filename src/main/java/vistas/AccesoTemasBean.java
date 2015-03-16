package vistas;

public class AccesoTemasBean {
    
    public static final String PATH_ACCESO_TEMA = "accesoTema";
    
    private boolean accesoDenegado;
    
    private final String CLAVE_OK = "666";
    
    private String clave;
    
    public String process(){
        if(getClave().equals(CLAVE_OK)){
            setAccesoDenegado(false);
            return BorrarTemaBean.PATH_BORRAR_TEMA;
        }
        else{
            setAccesoDenegado(true);
            return PATH_ACCESO_TEMA;
        }
    }
    
    public boolean isAccesoDenegado() {
        return accesoDenegado;
    }

    private void setAccesoDenegado(boolean accesoDenegado) {
        this.accesoDenegado = accesoDenegado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
