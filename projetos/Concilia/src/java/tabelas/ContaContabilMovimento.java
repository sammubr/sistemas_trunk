/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tabelas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import persistencia.Persistencia;

/**
 *
 * @author samuel
 */
@Entity
@Table(name = "conta_contabil_movimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContaContabilMovimento.findAll", query = "SELECT c FROM ContaContabilMovimento c"),
    @NamedQuery(name = "ContaContabilMovimento.findByIdcontaContabilMovimento", query = "SELECT c FROM ContaContabilMovimento c WHERE c.idcontaContabilMovimento = :idcontaContabilMovimento"),
    @NamedQuery(name = "ContaContabilMovimento.findByDataMov", query = "SELECT c FROM ContaContabilMovimento c WHERE c.dataMov = :dataMov"),
    @NamedQuery(name = "ContaContabilMovimento.findByValor", query = "SELECT c FROM ContaContabilMovimento c WHERE c.valor = :valor"),
    @NamedQuery(name = "ContaContabilMovimento.findByNumdoc", query = "SELECT c FROM ContaContabilMovimento c WHERE c.numdoc = :numdoc"),
    @NamedQuery(name = "ContaContabilMovimento.findByHistorico", query = "SELECT c FROM ContaContabilMovimento c WHERE c.historico = :historico"),
    @NamedQuery(name = "ContaContabilMovimento.findBySaldo", query = "SELECT c FROM ContaContabilMovimento c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "ContaContabilMovimento.findByCombinacao", query = "SELECT c FROM ContaContabilMovimento c WHERE c.combinacao = :combinacao"),
    @NamedQuery(name = "ContaContabilMovimento.findByDataConciliacao", query = "SELECT c FROM ContaContabilMovimento c WHERE c.dataConciliacao = :dataConciliacao")})
public class ContaContabilMovimento extends Persistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconta_contabil_movimento")
    private Integer idcontaContabilMovimento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_mov")
    @Temporal(TemporalType.DATE)
    private Date dataMov;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @Size(max = 20)
    @Column(name = "numdoc")
    private String numdoc;
    @Size(max = 50)
    @Column(name = "historico")
    private String historico;
    @Column(name = "saldo")
    private BigDecimal saldo;
    @Column(name = "combinacao")
    private Integer combinacao;
    @Column(name = "data_conciliacao")
    @Temporal(TemporalType.DATE)
    private Date dataConciliacao;
    @JoinColumn(name = "conta", referencedColumnName = "idconta_contabil")
    @ManyToOne(optional = false)
    private ContaContabil conta;

    public ContaContabilMovimento() {
    }

    public ContaContabilMovimento(Integer idcontaContabilMovimento) {
        this.idcontaContabilMovimento = idcontaContabilMovimento;
    }

    public ContaContabilMovimento(Integer idcontaContabilMovimento, Date dataMov, BigDecimal valor) {
        this.idcontaContabilMovimento = idcontaContabilMovimento;
        this.dataMov = dataMov;
        this.valor = valor;
    }

    public Integer getIdcontaContabilMovimento() {
        return idcontaContabilMovimento;
    }

    public void setIdcontaContabilMovimento(Integer idcontaContabilMovimento) {
        this.idcontaContabilMovimento = idcontaContabilMovimento;
    }

    public Date getDataMov() {
        return dataMov;
    }

    public void setDataMov(Date dataMov) {
        this.dataMov = dataMov;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Integer getCombinacao() {
        return combinacao;
    }

    public void setCombinacao(Integer combinacao) {
        this.combinacao = combinacao;
    }

    public Date getDataConciliacao() {
        return dataConciliacao;
    }

    public void setDataConciliacao(Date dataConciliacao) {
        this.dataConciliacao = dataConciliacao;
    }

    public ContaContabil getConta() {
        return conta;
    }

    public void setConta(ContaContabil conta) {
        this.conta = conta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontaContabilMovimento != null ? idcontaContabilMovimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContaContabilMovimento)) {
            return false;
        }
        ContaContabilMovimento other = (ContaContabilMovimento) object;
        if ((this.idcontaContabilMovimento == null && other.idcontaContabilMovimento != null) || (this.idcontaContabilMovimento != null && !this.idcontaContabilMovimento.equals(other.idcontaContabilMovimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tabelas.ContaContabilMovimento[ idcontaContabilMovimento=" + idcontaContabilMovimento + " ]";
    }
    
}
