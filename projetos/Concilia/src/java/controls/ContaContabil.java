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
@Table(name = "conta_contabil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContaContabil.findAll", query = "SELECT c FROM ContaContabil c"),
    @NamedQuery(name = "ContaContabil.findByDescricao", query = "SELECT c FROM ContaContabil c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "ContaContabil.findByIdcontaContabil", query = "SELECT c FROM ContaContabil c WHERE c.idcontaContabil = :idcontaContabil")})
public class ContaContabil extends Persistencia implements Serializable {
    @Size(max = 20)
    @Column(name = "tag_historico")
    private String tagHistorico;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconta_contabil")
    private Integer idcontaContabil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldo_inicial")
    private Float saldoInicial;
    @Size(max = 20)
    @Column(name = "tag_valor")
    private String tagValor;
    @Size(max = 20)
    @Column(name = "tag_data")
    private String tagData;
    @Size(max = 20)
    @Column(name = "tag_num_doc")
    private String tagNumDoc;
    @Size(max = 20)
    @Column(name = "tag_inicio_movimento")
    private String tagInicioMovimento;
    @Size(max = 20)
    @Column(name = "tag_fim_movimento")
    private String tagFimMovimento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conta")
    private Collection<ContaContabilMovimento> contaContabilMovimentoCollection;

    public ContaContabil() {
    }

    public ContaContabil(Integer idcontaContabil) {
        this.idcontaContabil = idcontaContabil;
    }

    public ContaContabil(Integer idcontaContabil, String descricao) {
        this.idcontaContabil = idcontaContabil;
        this.descricao = descricao;
    }

    public Integer getIdcontaContabil() {
        return idcontaContabil;
    }

    public void setIdcontaContabil(Integer idcontaContabil) {
        this.idcontaContabil = idcontaContabil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Float saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getTagValor() {
        return tagValor;
    }

    public void setTagValor(String tagValor) {
        this.tagValor = tagValor;
    }

    public String getTagData() {
        return tagData;
    }

    public void setTagData(String tagData) {
        this.tagData = tagData;
    }

    public String getTagNumDoc() {
        return tagNumDoc;
    }

    public void setTagNumDoc(String tagNumDoc) {
        this.tagNumDoc = tagNumDoc;
    }

    public String getTagInicioMovimento() {
        return tagInicioMovimento;
    }

    public void setTagInicioMovimento(String tagInicioMovimento) {
        this.tagInicioMovimento = tagInicioMovimento;
    }

    public String getTagFimMovimento() {
        return tagFimMovimento;
    }

    public void setTagFimMovimento(String tagFimMovimento) {
        this.tagFimMovimento = tagFimMovimento;
    }

    @XmlTransient
    public Collection<ContaContabilMovimento> getContaContabilMovimentoCollection() {
        return contaContabilMovimentoCollection;
    }

    public void setContaContabilMovimentoCollection(Collection<ContaContabilMovimento> contaContabilMovimentoCollection) {
        this.contaContabilMovimentoCollection = contaContabilMovimentoCollection;
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

    public String getTagHistorico() {
        return tagHistorico;
    }

    public void setTagHistorico(String tagHistorico) {
        this.tagHistorico = tagHistorico;
    }
    
}
