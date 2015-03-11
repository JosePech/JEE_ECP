package persistencia.models.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TEMAS", uniqueConstraints={
  @UniqueConstraint(columnNames = {Tema.NOMBRE, Tema.PREGUNTA})
})
public class Tema implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
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
        this.setNombre(nombre);
        this.setPregunta(pregunta);
    }

    public void setId(Integer id){
        this.id = id;
    }
    
    public Integer getId(){
        return this.id;
    }
    
    public void setNombre(String nombre){
        assert(nombre != null);
        assert(nombre.length() > 0);
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setPregunta(String pregunta){
        assert(pregunta != null);
        assert(pregunta.length() > 0);
        this.pregunta = pregunta;
    }
    
    public String getPregunta(){
        return this.pregunta;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getPregunta().hashCode() + getNombre().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        assert (obj instanceof Tema);
        Tema cast = (Tema)obj;
        return cast.getId() == this.id;
    }

}
