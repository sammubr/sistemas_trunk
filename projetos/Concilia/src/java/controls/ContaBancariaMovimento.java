/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import java.io.Serializable;
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
@Table(name = "conta_bancaria_movimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContaBancariaMovimento.findAll", query = "SELECT c FROM ContaBancariaMovimento c"),
    @NamedQuery(name = "ContaBancariaMovimento.findByDataMov", query = "SELECT c FROM ContaBancariaMovimento c WHERE c.dataMov = :dataMov"),
    @NamedQuery(name = "ContaBancariaMovimento.findByValor", query = "SELECT c FROM ContaBancariaMovimento c WHERE c.valor = :valor"),
    @NamedQuery(name = "ContaBancariaMovimento.findByNumdoc", query = "SELECT c FROM ContaBancariaMovimento c WHERE c.numdoc = :numdoc"),
    @NamedQuery(name = "ContaBancariaMovimento.findByHistorico", query = "SELECT c FROM ContaBancariaMovimento c WHERE c.historico = :historico"),
    @NamedQuery(name = "ContaBancariaMovimento.findByIdcontaBancariaMovimento", query = "SELECT c FROM ContaBancariaMovimento c WHERE c.idcontaBancariaMovimento = :idcontaBancariaMovimento")})
public class ContaBancariaMovimento extends Persistencia  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_mov")
    @Temporal(TemporalType.DATE)
    private Date dataMov;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private float valor;
    @Size(max = 20)
    @Column(name = "numdoc")
    private String numdoc;
    @Size(max = 20)
    @Column(name = "historico")
    private String historico;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconta_bancaria_movimento")
    private Integer idcontaBancariaMovimento;
    @JoinColumn(name = "conta", referencedColumnName = "idconta_bancaria")
    @ManyToOne(optional = false)
    private ContaBancaria conta;

    public ContaBancariaMovimento() {
    }

    public ContaBancariaMovimento(Integer idcontaBancariaMovimento) {
        this.idcontaBancariaMovimento = idcontaBancariaMovimento;
    }

    public ContaBancariaMovimento(Integer idcontaBancariaMovimento, Date dataMov, float valor) {
        this.idcontaBancariaMovimento = idcontaBancariaMovimento;
        this.dataMov = dataMov;
        this.valor = valor;
    }

    public Date getDataMov() {
        return dataMov;
    }

    public void setDataMov(Date dataMov) {
        this.dataMov = dataMov;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
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

    public Integer getIdcontaBancariaMovimento() {
        return idcontaBancariaMovimento;
    }

    public void setIdcontaBancariaMovimento(Integer idcontaBancariaMovimento) {
        this.idcontaBancariaMovimento = idcontaBancariaMovimento;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        this.conta = conta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontaBancariaMovimento != null ? idcontaBancariaMovimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContaBancariaMovimento)) {
            return false;
        }
        ContaBancariaMovimento other = (ContaBancariaMovimento) object;
        if ((this.idcontaBancariaMovimento == null && other.idcontaBancariaMovimento != null) || (this.idcontaBancariaMovimento != null && !this.idcontaBancariaMovimento.equals(other.idcontaBancariaMovimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controls.ContaBancariaMovimento[ idcontaBancariaMovimento=" + idcontaBancariaMovimento + " ]";
    }
    
}
