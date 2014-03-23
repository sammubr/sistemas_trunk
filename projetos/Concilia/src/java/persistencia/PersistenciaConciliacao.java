/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import tabelas.Conciliacao;
import tabelas.ContaBancariaMovimento;
import tabelas.ContaContabilMovimento;
import java.util.List;
import java.util.ResourceBundle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.JsfUtil;
import util.NewHibernateUtil;

/**
 *
 * @author samuel
 */
public class PersistenciaConciliacao {

    private List<ContaContabilMovimento> listaDeMovimentoContaContabilNaoConciliados;
    private List<ContaContabilMovimento> listaDeMovimentoContaContabilConciliados;

    private List<ContaBancariaMovimento> listaDeMovimentoContaBancariaNaoConciliados;
    private List<ContaBancariaMovimento> listaDeMovimentoContaBancariaConciliados;

    private Conciliacao conciliacao;

    private Session session;

    public List<ContaContabilMovimento> getListaDeMovimentoContaContabilNaoConciliados() {
        return listaDeMovimentoContaContabilNaoConciliados;
    }

    public void setListaDeMovimentoContaContabilNaoConciliados(List<ContaContabilMovimento> listaDeMovimentoContaContabilNaoConciliados) {
        this.listaDeMovimentoContaContabilNaoConciliados = listaDeMovimentoContaContabilNaoConciliados;
    }

    public List<ContaContabilMovimento> getListaDeMovimentoContaContabilConciliados() {
        return listaDeMovimentoContaContabilConciliados;
    }

    public void setListaDeMovimentoContaContabilConciliados(List<ContaContabilMovimento> listaDeMovimentoContaContabilConciliados) {
        this.listaDeMovimentoContaContabilConciliados = listaDeMovimentoContaContabilConciliados;
    }

    public List<ContaBancariaMovimento> getListaDeMovimentoContaBancariaNaoConciliados() {
        return listaDeMovimentoContaBancariaNaoConciliados;
    }

    public void setListaDeMovimentoContaBancariaNaoConciliados(List<ContaBancariaMovimento> listaDeMovimentoContaBancariaNaoConciliados) {
        this.listaDeMovimentoContaBancariaNaoConciliados = listaDeMovimentoContaBancariaNaoConciliados;
    }

    public List<ContaBancariaMovimento> getListaDeMovimentoContaBancariaConciliados() {
        return listaDeMovimentoContaBancariaConciliados;
    }

    public void setListaDeMovimentoContaBancariaConciliados(List<ContaBancariaMovimento> listaDeMovimentoContaBancariaConciliados) {
        this.listaDeMovimentoContaBancariaConciliados = listaDeMovimentoContaBancariaConciliados;
    }

    public Conciliacao getConciliacao() {
        return conciliacao;
    }

    public void setConciliacao(Conciliacao conciliacao) {
        this.conciliacao = conciliacao;
    }

    public void updateMovimentosContaBancaria(List<ContaBancariaMovimento> lista) {

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getCombinacao() != null) {

                String hqlUpdate = "update ContaBancariaMovimento c set c.dataConciliacao = :data1, c.combinacao = :combinacao1 where c.idcontaBancariaMovimento = :id1";
                int updatedEntities = session.createQuery(hqlUpdate)
                        .setInteger("id1", lista.get(i).getIdcontaBancariaMovimento())
                        .setDate("data1", lista.get(i).getDataConciliacao())
                        .setInteger("combinacao1", lista.get(i).getCombinacao())
                        .executeUpdate();

            } else {

                String hqlUpdate = "update ContaBancariaMovimento c set c.dataConciliacao = null, c.combinacao = null where c.idcontaBancariaMovimento = :id1";
                int updatedEntities = session.createQuery(hqlUpdate)
                        .setInteger("id1", lista.get(i).getIdcontaBancariaMovimento())
                        .executeUpdate();

            }

            if (i % 50 == 0) { //50, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
        }

    }

    public void updateMovimentosContaContabil(List<ContaContabilMovimento> lista) {

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getCombinacao() != null) {

                String hqlUpdate = "update ContaContabilMovimento c set c.dataConciliacao = :data1, c.combinacao = :combinacao1 where c.idcontaContabilMovimento = :id1";
                int updatedEntities = session.createQuery(hqlUpdate)
                        .setInteger("id1", lista.get(i).getIdcontaContabilMovimento())
                        .setDate("data1", lista.get(i).getDataConciliacao())
                        .setInteger("combinacao1", lista.get(i).getCombinacao())
                        .executeUpdate();

            } else {

                String hqlUpdate = "update ContaContabilMovimento c set c.dataConciliacao = null, c.combinacao = null where c.idcontaContabilMovimento = :id1";
                int updatedEntities = session.createQuery(hqlUpdate)
                        .setInteger("id1", lista.get(i).getIdcontaContabilMovimento())
                        .executeUpdate();

            }

            if (i % 50 == 0) { //50, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
        }

    }

    public void salva() {

        session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            updateMovimentosContaBancaria(listaDeMovimentoContaBancariaConciliados);
            updateMovimentosContaBancaria(listaDeMovimentoContaBancariaNaoConciliados);
            updateMovimentosContaContabil(listaDeMovimentoContaContabilConciliados);
            updateMovimentosContaContabil(listaDeMovimentoContaContabilNaoConciliados);
            conciliacao.persiste();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"), e);
            throw e;
        } finally {
            session.close();
        }

    }

}
