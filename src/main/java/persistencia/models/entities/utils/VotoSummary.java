package persistencia.models.entities.utils;

import javax.xml.bind.annotation.XmlRootElement;

import persistencia.models.entities.Tema;

@XmlRootElement
public class VotoSummary {
    
    private Tema tema;
    
    private Escolaridad escolaridad;
    
    private Long total;
    
    private Double media;
    
    public VotoSummary(Tema tema, Escolaridad escolaridad, Long total, Double media ){
        this.setTema(tema);
        this.setEscolaridad(escolaridad);
        this.setTotal(total);
        this.setMedia(media);
    }
    
    public VotoSummary(){
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Escolaridad getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(Escolaridad escolaridad) {
        this.escolaridad = escolaridad;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return String.format("VotoSummary-> Tema: %s -> Escolaridad: %s -> Total: %d -> Media: %f ",
                this.getTema(), this.getEscolaridad(), this.getTotal(), this.getMedia());
    }
    
    

}
