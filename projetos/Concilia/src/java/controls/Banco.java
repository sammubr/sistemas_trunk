/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
    @NamedQuery(name = "Banco.findByDescricao", query = "SELECT b FROM Banco b WHERE b.descricao = :descricao"),
    @NamedQuery(name = "Banco.findByTagValor", query = "SELECT c FROM Banco c WHERE c.tagValor = :tagValor"),
    @NamedQuery(name = "Banco.findByTagData", query = "SELECT c FROM Banco c WHERE c.tagData = :tagData"),
    @NamedQuery(name = "Banco.findByTagNumDoc", query = "SELECT c FROM Banco c WHERE c.tagNumDoc = :tagNumDoc"),
    @NamedQuery(name = "Banco.findByTagHistorico", query = "SELECT c FROM Banco c WHERE c.tagHistorico = :tagHistorico"),
    @NamedQuery(name = "Banco.findByTagInicioMovimento", query = "SELECT c FROM Banco c WHERE c.tagInicioMovimento = :tagInicioMovimento"),
    @NamedQuery(name = "Banco.findByTagFimMovimento", query = "SELECT c FROM Banco c WHERE c.tagFimMovimento = :tagFimMovimento")})
public class Banco extends Persistencia implements Serializable {
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
    
}
