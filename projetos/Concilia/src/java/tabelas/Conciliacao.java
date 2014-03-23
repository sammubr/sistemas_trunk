/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tabelas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import persistencia.Persistencia;

/**
 *
 * @author samuel
 */
@Entity
@Table(name = "conciliacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conciliacao.findAll", query = "SELECT c FROM Conciliacao c"),
    @NamedQuery(name = "Conciliacao.findByIdConciliacao", query = "SELECT c FROM Conciliacao c WHERE c.idConciliacao = :idConciliacao"),
    @NamedQuery(name = "Conciliacao.findByDataConciliacao", query = "SELECT c FROM Conciliacao c WHERE c.dataConciliacao = :dataConciliacao"),
    @NamedQuery(name = "Conciliacao.findByNumeroCombinacoes", query = "SELECT c FROM Conciliacao c WHERE c.numeroCombinacoes = :numeroCombinacoes")})
public class Conciliacao extends Persistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_conciliacao")
    private Integer idConciliacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_conciliacao")
    @Temporal(TemporalType.DATE)
    private Date dataConciliacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_combinacoes")
    private int numeroCombinacoes;

    public Conciliacao() {
    }

    public Conciliacao(Integer idConciliacao) {
        this.idConciliacao = idConciliacao;
    }

    public Conciliacao(Integer idConciliacao, Date dataConciliacao, int numeroCombinacoes) {
        this.idConciliacao = idConciliacao;
        this.dataConciliacao = dataConciliacao;
        this.numeroCombinacoes = numeroCombinacoes;
    }

    public Integer getIdConciliacao() {
        return idConciliacao;
    }

    public void setIdConciliacao(Integer idConciliacao) {
        this.idConciliacao = idConciliacao;
    }

    public Date getDataConciliacao() {
        return dataConciliacao;
    }

    public void setDataConciliacao(Date dataConciliacao) {
        this.dataConciliacao = dataConciliacao;
    }

    public int getNumeroCombinacoes() {
        return numeroCombinacoes;
    }

    public void setNumeroCombinacoes(int numeroCombinacoes) {
        this.numeroCombinacoes = numeroCombinacoes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConciliacao != null ? idConciliacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conciliacao)) {
            return false;
        }
        Conciliacao other = (Conciliacao) object;
        if ((this.idConciliacao == null && other.idConciliacao != null) || (this.idConciliacao != null && !this.idConciliacao.equals(other.idConciliacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tabelas.Conciliacao[ idConciliacao=" + idConciliacao + " ]";
    }
    
}
