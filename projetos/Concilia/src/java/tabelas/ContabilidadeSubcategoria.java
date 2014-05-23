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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import persistencia.Persistencia;

/**
 *
 * @author samuel
 */
@Entity
@Table(name = "contabilidade_subcategoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContabilidadeSubcategoria.findAll", query = "SELECT c FROM ContabilidadeSubcategoria c"),
    @NamedQuery(name = "ContabilidadeSubcategoria.findByIdcontabilidadeSubcategoria", query = "SELECT c FROM ContabilidadeSubcategoria c WHERE c.idcontabilidadeSubcategoria = :idcontabilidadeSubcategoria"),
    @NamedQuery(name = "ContabilidadeSubcategoria.findByDescricao", query = "SELECT c FROM ContabilidadeSubcategoria c WHERE c.descricao = :descricao")})
public class ContabilidadeSubcategoria extends Persistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontabilidade_subcategoria")
    private Integer idcontabilidadeSubcategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subcategoria")
    private Collection<ContaContabilMovimento> contaContabilMovimentoCollection;
    @JoinColumn(name = "categoria", referencedColumnName = "idcontabilidade_categoria")
    @ManyToOne(optional = false)
    private ContabilidadeCategoria categoria;

    public ContabilidadeSubcategoria() {
    }

    public ContabilidadeSubcategoria(Integer idcontabilidadeSubcategoria) {
        this.idcontabilidadeSubcategoria = idcontabilidadeSubcategoria;
    }

    public ContabilidadeSubcategoria(Integer idcontabilidadeSubcategoria, String descricao) {
        this.idcontabilidadeSubcategoria = idcontabilidadeSubcategoria;
        this.descricao = descricao;
    }

    public Integer getIdcontabilidadeSubcategoria() {
        return idcontabilidadeSubcategoria;
    }

    public void setIdcontabilidadeSubcategoria(Integer idcontabilidadeSubcategoria) {
        this.idcontabilidadeSubcategoria = idcontabilidadeSubcategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<ContaContabilMovimento> getContaContabilMovimentoCollection() {
        return contaContabilMovimentoCollection;
    }

    public void setContaContabilMovimentoCollection(Collection<ContaContabilMovimento> contaContabilMovimentoCollection) {
        this.contaContabilMovimentoCollection = contaContabilMovimentoCollection;
    }

    public ContabilidadeCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ContabilidadeCategoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontabilidadeSubcategoria != null ? idcontabilidadeSubcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContabilidadeSubcategoria)) {
            return false;
        }
        ContabilidadeSubcategoria other = (ContabilidadeSubcategoria) object;
        if ((this.idcontabilidadeSubcategoria == null && other.idcontabilidadeSubcategoria != null) || (this.idcontabilidadeSubcategoria != null && !this.idcontabilidadeSubcategoria.equals(other.idcontabilidadeSubcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tabelas.ContabilidadeSubcategoria[ idcontabilidadeSubcategoria=" + idcontabilidadeSubcategoria + " ]";
    }
    
}
