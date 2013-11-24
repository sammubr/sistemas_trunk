/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controls;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "conta_bancaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContaBancaria.findAll", query = "SELECT c FROM ContaBancaria c"),
    @NamedQuery(name = "ContaBancaria.findByAgencia", query = "SELECT c FROM ContaBancaria c WHERE c.agencia = :agencia"),
    @NamedQuery(name = "ContaBancaria.findByAgenciaDigito", query = "SELECT c FROM ContaBancaria c WHERE c.agenciaDigito = :agenciaDigito"),
    @NamedQuery(name = "ContaBancaria.findByNumero", query = "SELECT c FROM ContaBancaria c WHERE c.numero = :numero"),
    @NamedQuery(name = "ContaBancaria.findByNumeroDigito", query = "SELECT c FROM ContaBancaria c WHERE c.numeroDigito = :numeroDigito"),
    @NamedQuery(name = "ContaBancaria.findByIdcontaBancaria", query = "SELECT c FROM ContaBancaria c WHERE c.idcontaBancaria = :idcontaBancaria"),
    @NamedQuery(name = "ContaBancaria.findByDescricao", query = "SELECT c FROM ContaBancaria c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "ContaBancaria.findBySaldoInicial", query = "SELECT c FROM ContaBancaria c WHERE c.saldoInicial = :saldoInicial"),
    @NamedQuery(name = "ContaBancaria.findByTagValor", query = "SELECT c FROM ContaBancaria c WHERE c.tagValor = :tagValor"),
    @NamedQuery(name = "ContaBancaria.findByTagData", query = "SELECT c FROM ContaBancaria c WHERE c.tagData = :tagData"),
    @NamedQuery(name = "ContaBancaria.findByTagNumDoc", query = "SELECT c FROM ContaBancaria c WHERE c.tagNumDoc = :tagNumDoc"),
    @NamedQuery(name = "ContaBancaria.findByTagHistorico", query = "SELECT c FROM ContaBancaria c WHERE c.tagHistorico = :tagHistorico"),
    @NamedQuery(name = "ContaBancaria.findByTagInicioMovimento", query = "SELECT c FROM ContaBancaria c WHERE c.tagInicioMovimento = :tagInicioMovimento"),
    @NamedQuery(name = "ContaBancaria.findByTagFimMovimento", query = "SELECT c FROM ContaBancaria c WHERE c.tagFimMovimento = :tagFimMovimento")})
public class ContaBancaria extends Persistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "agencia")
    private Integer agencia;
    @Column(name = "agencia_digito")
    private Integer agenciaDigito;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "numero_digito")
    private Integer numeroDigito;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconta_bancaria")
    private Integer idcontaBancaria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial;
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
    @Column(name = "tag_historico")
    private String tagHistorico;
    @Size(max = 20)
    @Column(name = "tag_inicio_movimento")
    private String tagInicioMovimento;
    @Size(max = 20)
    @Column(name = "tag_fim_movimento")
    private String tagFimMovimento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conta")
    private Collection<ContaBancariaMovimento> contaBancariaMovimentoCollection;
    @JoinColumn(name = "rel_contabilidade_banco", referencedColumnName = "id")
    @ManyToOne
    private RelContabilidadeBanco relContabilidadeBanco;
    @JoinColumn(name = "banco", referencedColumnName = "idbanco")
    @ManyToOne(optional = false)
    private Banco banco;

    public ContaBancaria() {
    }

    public ContaBancaria(Integer idcontaBancaria) {
        this.idcontaBancaria = idcontaBancaria;
    }

    public ContaBancaria(Integer idcontaBancaria, String descricao, BigDecimal saldoInicial) {
        this.idcontaBancaria = idcontaBancaria;
        this.descricao = descricao;
        this.saldoInicial = saldoInicial;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getAgenciaDigito() {
        return agenciaDigito;
    }

    public void setAgenciaDigito(Integer agenciaDigito) {
        this.agenciaDigito = agenciaDigito;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumeroDigito() {
        return numeroDigito;
    }

    public void setNumeroDigito(Integer numeroDigito) {
        this.numeroDigito = numeroDigito;
    }

    public Integer getIdcontaBancaria() {
        return idcontaBancaria;
    }

    public void setIdcontaBancaria(Integer idcontaBancaria) {
        this.idcontaBancaria = idcontaBancaria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
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

    public String getTagHistorico() {
        return tagHistorico;
    }

    public void setTagHistorico(String tagHistorico) {
        this.tagHistorico = tagHistorico;
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
    public Collection<ContaBancariaMovimento> getContaBancariaMovimentoCollection() {
        return contaBancariaMovimentoCollection;
    }

    public void setContaBancariaMovimentoCollection(Collection<ContaBancariaMovimento> contaBancariaMovimentoCollection) {
        this.contaBancariaMovimentoCollection = contaBancariaMovimentoCollection;
    }

    public RelContabilidadeBanco getRelContabilidadeBanco() {
        return relContabilidadeBanco;
    }

    public void setRelContabilidadeBanco(RelContabilidadeBanco relContabilidadeBanco) {
        this.relContabilidadeBanco = relContabilidadeBanco;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontaBancaria != null ? idcontaBancaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContaBancaria)) {
            return false;
        }
        ContaBancaria other = (ContaBancaria) object;
        if ((this.idcontaBancaria == null && other.idcontaBancaria != null) || (this.idcontaBancaria != null && !this.idcontaBancaria.equals(other.idcontaBancaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controls.ContaBancaria[ idcontaBancaria=" + idcontaBancaria + " ]";
    }
    
}
