package persistencia.models.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.ForeignKey;
import javax.xml.bind.annotation.XmlRootElement;

import persistencia.models.entities.utils.Escolaridad;

@XmlRootElement
@Entity
@Table(name = Voto.TABLE)
@NamedQueries({@NamedQuery(name = Voto.FIND_ALL_AVG_GROUP_TEMA_ESCOLARIDAD, query = "SELECT new persistencia.models.entities.utils.VotoSummary(v.tema, v.escolaridad, COUNT(v), AVG(v.valor) ) FROM Voto v GROUP BY v.escolaridad, v.tema ")})
public class Voto implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String TABLE = "VOTOS";

    public static final String FIND_ALL_AVG_GROUP_TEMA_ESCOLARIDAD = "FIND_ALL_AVG_GROUP_TEMA_ESCOLARIDAD";

    public Voto() {
        super();
    }

    public Voto(Tema tema2, Integer i, Escolaridad escolaridad, String ip) {
        this.setTema(tema2);
        this.setValor(i);
        this.setEscolaridad(escolaridad);
        this.setIp(ip);
    }

    @ManyToOne(optional = true)
    @JoinColumn(foreignKey = @ForeignKey(name = "VOTOS_TEMAS_FK", foreignKeyDefinition = "FOREIGN KEY (`TEMA_ID`) REFERENCES `miwjee`.`TEMAS` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT;"))
    private Tema tema;

    public static final String TEMA_ID = "TEMA_ID";

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Escolaridad escolaridad;

    private Integer valor;

    public static final String IP = "IP";

    private String ip;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getIp().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        assert (obj instanceof Voto);
        Voto cast = (Voto) obj;
        return cast.getId() == this.id;
    }

}
