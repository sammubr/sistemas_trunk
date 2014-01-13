/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controls;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import persistencia.Persistencia;

/**
 *
 * @author samuel
 */
@Entity
@Table(name = "conciliacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conciliacao.findAll", query = "SELECT c FROM Conciliacao c"),
    @NamedQuery(name = "Conciliacao.findByIdConciliacao", query = "SELECT c FROM Conciliacao c WHERE c.idConciliacao = :idConciliacao"),
    @NamedQuery(name = "Conciliacao.findByDataConciliacao", query = "SELECT c FROM Conciliacao c WHERE c.dataConciliacao = :dataConciliacao")})
public class Conciliacao extends Persistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_conciliacao")
    private Integer idConciliacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_conciliacao")
    @Temporal(TemporalType.DATE)
    private Date dataConciliacao;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "conciliacao")
    @Fetch(FetchMode.SUBSELECT)
    private Collection<Combinacao> combinacaoCollection;

    public Conciliacao() {
    }

    public Conciliacao(Integer idConciliacao) {
        this.idConciliacao = idConciliacao;
    }

    public Conciliacao(Integer idConciliacao, Date dataConciliacao) {
        this.idConciliacao = idConciliacao;
        this.dataConciliacao = dataConciliacao;
    }

    public Integer getIdConciliacao() {
        return idConciliacao;
    }

    public void setIdConciliacao(Integer idConciliacao) {
        this.idConciliacao = idConciliacao;
    }

    public Date getDataConciliacao() {
        return dataConciliacao;
    }

    public void setDataConciliacao(Date dataConciliacao) {
        this.dataConciliacao = dataConciliacao;
    }

    @XmlTransient
    public Collection<Combinacao> getCombinacaoCollection() {
        return combinacaoCollection;
    }

    public void setCombinacaoCollection(Collection<Combinacao> combinacaoCollection) {
        this.combinacaoCollection = combinacaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConciliacao != null ? idConciliacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conciliacao)) {
            return false;
        }
        Conciliacao other = (Conciliacao) object;
        if ((this.idConciliacao == null && other.idConciliacao != null) || (this.idConciliacao != null && !this.idConciliacao.equals(other.idConciliacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controls.Conciliacao[ idConciliacao=" + idConciliacao + " ]";
    }
    
}