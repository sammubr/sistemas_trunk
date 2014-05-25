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
@Table(name = "credor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credor.findAll", query = "SELECT c FROM Credor c"),
    @NamedQuery(name = "Credor.findByIdcredor", query = "SELECT c FROM Credor c WHERE c.idcredor = :idcredor"),
    @NamedQuery(name = "Credor.findByCnpjCpf", query = "SELECT c FROM Credor c WHERE c.cnpjCpf = :cnpjCpf"),
    @NamedQuery(name = "Credor.findByRazaoSocialNome", query = "SELECT c FROM Credor c WHERE c.razaoSocialNome = :razaoSocialNome")})
public class Credor extends Persistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcredor")
    private Integer idcredor;
    @Size(max = 18)
    @Column(name = "cnpj_cpf")
    private String cnpjCpf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "razao_social_nome")
    private String razaoSocialNome;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "credor")
    @Fetch(FetchMode.SUBSELECT)    
    private Collection<ContaContabilMovimento> contaContabilMovimentoCollection;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "credor")
    @Fetch(FetchMode.SUBSELECT)    
    private Collection<ContaBancariaMovimento> contaBancariaMovimentoCollection;

    public Credor() {
    }

    public Credor(Integer idcredor) {
        this.idcredor = idcredor;
    }

    public Credor(Integer idcredor, String razaoSocialNome) {
        this.idcredor = idcredor;
        this.razaoSocialNome = razaoSocialNome;
    }

    public Integer getIdcredor() {
        return idcredor;
    }

    public void setIdcredor(Integer idcredor) {
        this.idcredor = idcredor;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getRazaoSocialNome() {
        return razaoSocialNome;
    }

    public void setRazaoSocialNome(String razaoSocialNome) {
        this.razaoSocialNome = razaoSocialNome;
    }

    @XmlTransient
    public Collection<ContaContabilMovimento> getContaContabilMovimentoCollection() {
        return contaContabilMovimentoCollection;
    }

    public void setContaContabilMovimentoCollection(Collection<ContaContabilMovimento> contaContabilMovimentoCollection) {
        this.contaContabilMovimentoCollection = contaContabilMovimentoCollection;
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
        hash += (idcredor != null ? idcredor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credor)) {
            return false;
        }
        Credor other = (Credor) object;
        if ((this.idcredor == null && other.idcredor != null) || (this.idcredor != null && !this.idcredor.equals(other.idcredor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tabelas.Credor[ idcredor=" + idcredor + " ]";
    }
    
}
