/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import persistencia.Persistencia;

/**
 *
 * @author samuel
 */
@Entity
@Table(name = "conta_contabil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContaContabil.findAll", query = "SELECT c FROM ContaContabil c"),
    @NamedQuery(name = "ContaContabil.findByDescricao", query = "SELECT c FROM ContaContabil c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "ContaContabil.findByIdcontaContabil", query = "SELECT c FROM ContaContabil c WHERE c.idcontaContabil = :idcontaContabil")})
public class ContaContabil extends Persistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descricao")
    private String descricao;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconta_contabil")
    private Integer idcontaContabil;

    public ContaContabil() {
    }

    public ContaContabil(Integer idcontaContabil) {
        this.idcontaContabil = idcontaContabil;
    }

    public ContaContabil(Integer idcontaContabil, String descricao) {
        this.idcontaContabil = idcontaContabil;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdcontaContabil() {
        return idcontaContabil;
    }

    public void setIdcontaContabil(Integer idcontaContabil) {
        this.idcontaContabil = idcontaContabil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontaContabil != null ? idcontaContabil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContaContabil)) {
            return false;
        }
        ContaContabil other = (ContaContabil) object;
        if ((this.idcontaContabil == null && other.idcontaContabil != null) || (this.idcontaContabil != null && !this.idcontaContabil.equals(other.idcontaContabil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controls.ContaContabil[ idcontaContabil=" + idcontaContabil + " ]";
    }
    
}
