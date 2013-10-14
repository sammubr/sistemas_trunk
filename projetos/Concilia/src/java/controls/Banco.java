/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

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
@Table(name = "banco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banco.findAll", query = "SELECT b FROM Banco b"),
    @NamedQuery(name = "Banco.findByCodigo", query = "SELECT b FROM Banco b WHERE b.codigo = :codigo"),
    @NamedQuery(name = "Banco.findByIdbanco", query = "SELECT b FROM Banco b WHERE b.idbanco = :idbanco"),
    @NamedQuery(name = "Banco.findByDescricao", query = "SELECT b FROM Banco b WHERE b.descricao = :descricao")})
public class Banco extends Persistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private int codigo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbanco")
    private Integer idbanco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "banco")
    private Collection<ContaBancaria> contaBancariaCollection;

    public Banco() {
    }

    public Banco(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public Banco(Integer idbanco, int codigo, String descricao) {
        this.idbanco = idbanco;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Integer getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        hash += (idbanco != null ? idbanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        if ((this.idbanco == null && other.idbanco != null) || (this.idbanco != null && !this.idbanco.equals(other.idbanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controls.Banco[ idbanco=" + idbanco + " ]";
    }
    
}
