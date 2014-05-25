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
import javax.persistence.FetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author samuel
 */
@Entity
@Table(name = "banco_subcategoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BancoSubcategoria.findAll", query = "SELECT b FROM BancoSubcategoria b"),
    @NamedQuery(name = "BancoSubcategoria.findByIdbancoSubcategoria", query = "SELECT b FROM BancoSubcategoria b WHERE b.idbancoSubcategoria = :idbancoSubcategoria"),
    @NamedQuery(name = "BancoSubcategoria.findByDescricao", query = "SELECT b FROM BancoSubcategoria b WHERE b.descricao = :descricao")})
public class BancoSubcategoria extends Persistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbanco_subcategoria")
    private Integer idbancoSubcategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "categoria", referencedColumnName = "idbanco_categoria")
    @ManyToOne(optional = false)
    private BancoCategoria categoria;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "subcategoria")
    @Fetch(FetchMode.SUBSELECT)
    private Collection<ContaBancariaMovimento> contaBancariaMovimentoCollection;

    public BancoSubcategoria() {
    }

    public BancoSubcategoria(Integer idbancoSubcategoria) {
        this.idbancoSubcategoria = idbancoSubcategoria;
    }

    public BancoSubcategoria(Integer idbancoSubcategoria, String descricao) {
        this.idbancoSubcategoria = idbancoSubcategoria;
        this.descricao = descricao;
    }

    public Integer getIdbancoSubcategoria() {
        return idbancoSubcategoria;
    }

    public void setIdbancoSubcategoria(Integer idbancoSubcategoria) {
        this.idbancoSubcategoria = idbancoSubcategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BancoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(BancoCategoria categoria) {
        this.categoria = categoria;
    }

    @XmlTransient
    public Collection<ContaBancariaMovimento> getContaBancariaMovimentoCollection() {
        return contaBancariaMovimentoCollection;
    }

    public void setContaBancariaMovimentoCollection(Collection<ContaBancariaMovimento> contaBancariaMovimentoCollection) {
        this.contaBancariaMovimentoCollection = contaBancariaMovimentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbancoSubcategoria != null ? idbancoSubcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BancoSubcategoria)) {
            return false;
        }
        BancoSubcategoria other = (BancoSubcategoria) object;
        if ((this.idbancoSubcategoria == null && other.idbancoSubcategoria != null) || (this.idbancoSubcategoria != null && !this.idbancoSubcategoria.equals(other.idbancoSubcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tabelas.BancoSubcategoria[ idbancoSubcategoria=" + idbancoSubcategoria + " ]";
    }
    
}
