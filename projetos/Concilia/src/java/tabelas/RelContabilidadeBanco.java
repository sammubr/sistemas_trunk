/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tabelas;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
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
@Table(name = "rel_contabilidade_banco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelContabilidadeBanco.findAll", query = "SELECT r FROM RelContabilidadeBanco r"),
    @NamedQuery(name = "RelContabilidadeBanco.findById", query = "SELECT r FROM RelContabilidadeBanco r WHERE r.id = :id"),
    @NamedQuery(name = "RelContabilidadeBanco.findByDescricao", query = "SELECT r FROM RelContabilidadeBanco r WHERE r.descricao = :descricao")})
public class RelContabilidadeBanco extends Persistencia implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relacionamento")
    private Collection<Conciliacao> conciliacaoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "relContabilidadeBanco")
    @Fetch(FetchMode.SUBSELECT)
    private Collection<ContaContabil> contaContabilCollection;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "relContabilidadeBanco")
    @Fetch(FetchMode.SUBSELECT)
    private Collection<ContaBancaria> contaBancariaCollection;

    public RelContabilidadeBanco() {
    }

    public RelContabilidadeBanco(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<ContaContabil> getContaContabilCollection() {
        return contaContabilCollection;
    }

    public void setContaContabilCollection(Collection<ContaContabil> contaContabilCollection) {
        this.contaContabilCollection = contaContabilCollection;
    }

    @XmlTransient
    public Collection<ContaBancaria> getContaBancariaCollection() {
        return contaBancariaCollection;
    }

    public void setContaBancariaCollection(Collection<ContaBancaria> contaBancariaCollection) {
        this.contaBancariaCollection = contaBancariaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelContabilidadeBanco)) {
            return false;
        }
        RelContabilidadeBanco other = (RelContabilidadeBanco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tabelas.RelContabilidadeBanco[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Conciliacao> getConciliacaoCollection() {
        return conciliacaoCollection;
    }

    public void setConciliacaoCollection(Collection<Conciliacao> conciliacaoCollection) {
        this.conciliacaoCollection = conciliacaoCollection;
    }
    
}
