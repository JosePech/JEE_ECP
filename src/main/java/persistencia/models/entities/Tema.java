package persistencia.models.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Tema {
    
    public static final String TABLE = "temas";

    public static final String ID = "ID";
    @Id
    @GeneratedValue
    private Integer id;

    public static final String NOMBRE = "NOMBRE";
    private String nombre;

    public static final String PREGUNTA = "PREGUNTA";
    private String pregunta;
    
    public Tema(){        
    }
    
    public Tema(String nombre, String pregunta) {
        this.nombre = nombre;
        this.pregunta = pregunta;
    }

    public void setId(Integer id){
        this.id = id;
    }
    
    public Integer getId(){
        return this.id;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setPregunta(String pregunta){
        this.pregunta = pregunta;
    }
    
    public String getPregunta(){
        return this.pregunta;
    }

}
