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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import persistencia.Persistencia;
import javax.persistence.FetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author samuel
 */
@Entity
@Table(name = "contabilidade_categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContabilidadeCategoria.findAll", query = "SELECT c FROM ContabilidadeCategoria c"),
    @NamedQuery(name = "ContabilidadeCategoria.findByIdcontabilidadeCategoria", query = "SELECT c FROM ContabilidadeCategoria c WHERE c.idcontabilidadeCategoria = :idcontabilidadeCategoria"),
    @NamedQuery(name = "ContabilidadeCategoria.findByDescricao", query = "SELECT c FROM ContabilidadeCategoria c WHERE c.descricao = :descricao")})
public class ContabilidadeCategoria extends Persistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontabilidade_categoria")
    private Integer idcontabilidadeCategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private Collection<ContabilidadeSubcategoria> contabilidadeSubcategoriaCollection;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "categoria")
    @Fetch(FetchMode.SUBSELECT)
    private Collection<ContaContabilMovimento> contaContabilMovimentoCollection;

    public ContabilidadeCategoria() {
    }

    public ContabilidadeCategoria(Integer idcontabilidadeCategoria) {
        this.idcontabilidadeCategoria = idcontabilidadeCategoria;
    }

    public ContabilidadeCategoria(Integer idcontabilidadeCategoria, String descricao) {
        this.idcontabilidadeCategoria = idcontabilidadeCategoria;
        this.descricao = descricao;
    }

    public Integer getIdcontabilidadeCategoria() {
        return idcontabilidadeCategoria;
    }

    public void setIdcontabilidadeCategoria(Integer idcontabilidadeCategoria) {
        this.idcontabilidadeCategoria = idcontabilidadeCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<ContabilidadeSubcategoria> getContabilidadeSubcategoriaCollection() {
        return contabilidadeSubcategoriaCollection;
    }

    public void setContabilidadeSubcategoriaCollection(Collection<ContabilidadeSubcategoria> contabilidadeSubcategoriaCollection) {
        this.contabilidadeSubcategoriaCollection = contabilidadeSubcategoriaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontabilidadeCategoria != null ? idcontabilidadeCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContabilidadeCategoria)) {
            return false;
        }
        ContabilidadeCategoria other = (ContabilidadeCategoria) object;
        if ((this.idcontabilidadeCategoria == null && other.idcontabilidadeCategoria != null) || (this.idcontabilidadeCategoria != null && !this.idcontabilidadeCategoria.equals(other.idcontabilidadeCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tabelas.ContabilidadeCategoria[ idcontabilidadeCategoria=" + idcontabilidadeCategoria + " ]";
    }

    @XmlTransient
    public Collection<ContaContabilMovimento> getContaContabilMovimentoCollection() {
        return contaContabilMovimentoCollection;
    }

    public void setContaContabilMovimentoCollection(Collection<ContaContabilMovimento> contaContabilMovimentoCollection) {
        this.contaContabilMovimentoCollection = contaContabilMovimentoCollection;
    }
    
}
