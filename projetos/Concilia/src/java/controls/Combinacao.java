/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controls;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "combinacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Combinacao.findAll", query = "SELECT c FROM Combinacao c"),
    @NamedQuery(name = "Combinacao.findByIdCombinacao", query = "SELECT c FROM Combinacao c WHERE c.idCombinacao = :idCombinacao")})
public class Combinacao extends Persistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_combinacao")
    private Integer idCombinacao;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "combinacao")
    @Fetch(FetchMode.SUBSELECT)
    private Collection<ContaBancariaMovimento> contaBancariaMovimentoCollection;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "combinacao")
    @Fetch(FetchMode.SUBSELECT)
    private Collection<ContaContabilMovimento> contaContabilMovimentoCollection;
    @JoinColumn(name = "conciliacao", referencedColumnName = "id_conciliacao")
    @ManyToOne(optional = false)
    private Conciliacao conciliacao;

    public Combinacao() {
    }

    public Combinacao(Integer idCombinacao) {
        this.idCombinacao = idCombinacao;
    }

    public Integer getIdCombinacao() {
        return idCombinacao;
    }

    public void setIdCombinacao(Integer idCombinacao) {
        this.idCombinacao = idCombinacao;
    }

    @XmlTransient
    public Collection<ContaBancariaMovimento> getContaBancariaMovimentoCollection() {
        return contaBancariaMovimentoCollection;
    }

    public void setContaBancariaMovimentoCollection(Collection<ContaBancariaMovimento> contaBancariaMovimentoCollection) {
        this.contaBancariaMovimentoCollection = contaBancariaMovimentoCollection;
    }

    @XmlTransient
    public Collection<ContaContabilMovimento> getContaContabilMovimentoCollection() {
        return contaContabilMovimentoCollection;
    }

    public void setContaContabilMovimentoCollection(Collection<ContaContabilMovimento> contaContabilMovimentoCollection) {
        this.contaContabilMovimentoCollection = contaContabilMovimentoCollection;
    }

    public Conciliacao getConciliacao() {
        return conciliacao;
    }

    public void setConciliacao(Conciliacao conciliacao) {
        this.conciliacao = conciliacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCombinacao != null ? idCombinacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Combinacao)) {
            return false;
        }
        Combinacao other = (Combinacao) object;
        if ((this.idCombinacao == null && other.idCombinacao != null) || (this.idCombinacao != null && !this.idCombinacao.equals(other.idCombinacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controls.Combinacao[ idCombinacao=" + idCombinacao + " ]";
    }
    
}
