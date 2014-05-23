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

/**
 *
 * @author samuel
 */
@Entity
@Table(name = "banco_categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BancoCategoria.findAll", query = "SELECT b FROM BancoCategoria b"),
    @NamedQuery(name = "BancoCategoria.findByIdbancoCategoria", query = "SELECT b FROM BancoCategoria b WHERE b.idbancoCategoria = :idbancoCategoria"),
    @NamedQuery(name = "BancoCategoria.findByDescricao", query = "SELECT b FROM BancoCategoria b WHERE b.descricao = :descricao")})
public class BancoCategoria extends Persistencia implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private Collection<ContaBancariaMovimento> contaBancariaMovimentoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbanco_categoria")
    private Integer idbancoCategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private Collection<BancoSubcategoria> bancoSubcategoriaCollection;

    public BancoCategoria() {
    }

    public BancoCategoria(Integer idbancoCategoria) {
        this.idbancoCategoria = idbancoCategoria;
    }

    public BancoCategoria(Integer idbancoCategoria, String descricao) {
        this.idbancoCategoria = idbancoCategoria;
        this.descricao = descricao;
    }

    public Integer getIdbancoCategoria() {
        return idbancoCategoria;
    }

    public void setIdbancoCategoria(Integer idbancoCategoria) {
        this.idbancoCategoria = idbancoCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<BancoSubcategoria> getBancoSubcategoriaCollection() {
        return bancoSubcategoriaCollection;
    }

    public void setBancoSubcategoriaCollection(Collection<BancoSubcategoria> bancoSubcategoriaCollection) {
        this.bancoSubcategoriaCollection = bancoSubcategoriaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbancoCategoria != null ? idbancoCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BancoCategoria)) {
            return false;
        }
        BancoCategoria other = (BancoCategoria) object;
        if ((this.idbancoCategoria == null && other.idbancoCategoria != null) || (this.idbancoCategoria != null && !this.idbancoCategoria.equals(other.idbancoCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tabelas.BancoCategoria[ idbancoCategoria=" + idbancoCategoria + " ]";
    }

    @XmlTransient
    public Collection<ContaBancariaMovimento> getContaBancariaMovimentoCollection() {
        return contaBancariaMovimentoCollection;
    }

    public void setContaBancariaMovimentoCollection(Collection<ContaBancariaMovimento> contaBancariaMovimentoCollection) {
        this.contaBancariaMovimentoCollection = contaBancariaMovimentoCollection;
    }
    
}
