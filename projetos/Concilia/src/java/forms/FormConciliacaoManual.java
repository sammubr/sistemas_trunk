package forms;

import tabelas.Conciliacao;
import tabelas.ContaBancaria;
import tabelas.ContaBancariaMovimento;
import tabelas.ContaContabil;
import tabelas.ContaContabilMovimento;
import tabelas.RelContabilidadeBanco;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import persistencia.PersistenciaConciliacao;
import util.JsfUtil;

@Named("formConciliacaoManual")
@ViewScoped
public class FormConciliacaoManual implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private boolean gridVisivel = true;
    private RelContabilidadeBanco relacionamento;
    private List<RelContabilidadeBanco> listaDeRelacionamentos;

    private List<ContaContabilMovimento> listaDeMovimentoContaContabil;

    private List<ContaContabilMovimento> listaDeMovimentoContaContabilNaoConciliados;
    private List<ContaContabilMovimento> listaDeMovimentoContaContabilConciliados;

    private List<ContaContabilMovimento> listaDeMovimentoContaContabilNaoConciliadosSelecionados;
    private List<ContaContabilMovimento> listaDeMovimentoContaContabilConciliadosSelecionados;

    private List<ContaBancariaMovimento> listaDeMovimentoContaBancaria;

    private List<ContaBancariaMovimento> listaDeMovimentoContaBancariaNaoConciliados;
    private List<ContaBancariaMovimento> listaDeMovimentoContaBancariaConciliados;

    private List<ContaBancariaMovimento> listaDeMovimentoContaBancariaNaoConciliadosSelecionados;
    private List<ContaBancariaMovimento> listaDeMovimentoContaBancariaConciliadosSelecionados;

    private BigDecimal saldoContaContabil;
    private BigDecimal saldoContaBancaria;

    private Date dataConciliacao;

    private Conciliacao conciliacao;

    private List<String> listaDeHistoricosContabeis;
    private List<String> listaDeHistoricosBancarios;
    private Date dataInicial;
    private Date dataFinal;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public FormConciliacaoManual() {
        geraListaDeRelacionamentos();
    }

    private void geraListaDeRelacionamentos() {
        RelContabilidadeBanco consulta = new RelContabilidadeBanco();
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("descricao"));
        setListaDeRelacionamentos(consulta.obterLista(null, ordem));
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public boolean isGridVisivel() {
        return gridVisivel;
    }

    public void setGridVisivel(boolean gridVisivel) {
        this.gridVisivel = gridVisivel;
    }

    public RelContabilidadeBanco getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(RelContabilidadeBanco relacionamento) {
        this.relacionamento = relacionamento;
    }

    public List<RelContabilidadeBanco> getListaDeRelacionamentos() {
        return listaDeRelacionamentos;
    }

    public void setListaDeRelacionamentos(List<RelContabilidadeBanco> listaDeRelacionamentos) {
        this.listaDeRelacionamentos = listaDeRelacionamentos;
    }

    public List<ContaContabilMovimento> getListaDeMovimentoContaContabil() {
        return listaDeMovimentoContaContabil;
    }

    public void setListaDeMovimentoContaContabil(List<ContaContabilMovimento> listaDeMovimentoContaContabil) {
        this.listaDeMovimentoContaContabil = listaDeMovimentoContaContabil;
    }

    public List<ContaContabilMovimento> getListaDeMovimentoContaContabilNaoConciliadosSelecionados() {
        return listaDeMovimentoContaContabilNaoConciliadosSelecionados;
    }

    public void setListaDeMovimentoContaContabilNaoConciliadosSelecionados(List<ContaContabilMovimento> listaDeMovimentoContaContabilNaoConciliadosSelecionados) {
        this.listaDeMovimentoContaContabilNaoConciliadosSelecionados = listaDeMovimentoContaContabilNaoConciliadosSelecionados;
    }

    public List<ContaContabilMovimento> getListaDeMovimentoContaContabilConciliadosSelecionados() {
        return listaDeMovimentoContaContabilConciliadosSelecionados;
    }

    public void setListaDeMovimentoContaContabilConciliadosSelecionados(List<ContaContabilMovimento> listaDeMovimentoContaContabilConciliadosSelecionados) {
        this.listaDeMovimentoContaContabilConciliadosSelecionados = listaDeMovimentoContaContabilConciliadosSelecionados;
    }

    public List<ContaBancariaMovimento> getListaDeMovimentoContaBancaria() {
        return listaDeMovimentoContaBancaria;
    }

    public void setListaDeMovimentoContaBancaria(List<ContaBancariaMovimento> listaDeMovimentoContaBancaria) {
        this.listaDeMovimentoContaBancaria = listaDeMovimentoContaBancaria;
    }

    public List<ContaBancariaMovimento> getListaDeMovimentoContaBancariaNaoConciliadosSelecionados() {
        return listaDeMovimentoContaBancariaNaoConciliadosSelecionados;
    }

    public void setListaDeMovimentoContaBancariaNaoConciliadosSelecionados(List<ContaBancariaMovimento> listaDeMovimentoContaBancariaNaoConciliadosSelecionados) {
        this.listaDeMovimentoContaBancariaNaoConciliadosSelecionados = listaDeMovimentoContaBancariaNaoConciliadosSelecionados;
    }

    public BigDecimal getSaldoContaContabil() {
        return saldoContaContabil;
    }

    public void setSaldoContaContabil(BigDecimal saldoContaContabil) {
        this.saldoContaContabil = saldoContaContabil;
    }

    public BigDecimal getSaldoContaBancaria() {
        return saldoContaBancaria;
    }

    public void setSaldoContaBancaria(BigDecimal saldoContaBancaria) {
        this.saldoContaBancaria = saldoContaBancaria;
    }

    public Date getDataConciliacao() {
        return dataConciliacao;
    }

    public void setDataConciliacao(Date dataConciliacao) {
        this.dataConciliacao = dataConciliacao;
    }

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

    public List<ContaBancariaMovimento> getListaDeMovimentoContaBancariaConciliadosSelecionados() {
        return listaDeMovimentoContaBancariaConciliadosSelecionados;
    }

    public void setListaDeMovimentoContaBancariaConciliadosSelecionados(List<ContaBancariaMovimento> listaDeMovimentoContaBancariaConciliadosSelecionados) {
        this.listaDeMovimentoContaBancariaConciliadosSelecionados = listaDeMovimentoContaBancariaConciliadosSelecionados;
    }

    public Conciliacao getConciliacao() {
        return conciliacao;
    }

    public void setConciliacao(Conciliacao conciliacao) {
        this.conciliacao = conciliacao;
    }

    public List<String> getListaDeHistoricosContabeis() {
        return listaDeHistoricosContabeis;
    }

    public void setListaDeHistoricosContabeis(List<String> listaDeHistoricosContabeis) {
        this.listaDeHistoricosContabeis = listaDeHistoricosContabeis;
    }

    public List<String> getListaDeHistoricosBancarios() {
        return listaDeHistoricosBancarios;
    }

    public void setListaDeHistoricosBancarios(List<String> listaDeHistoricosBancarios) {
        this.listaDeHistoricosBancarios = listaDeHistoricosBancarios;
    }

    /**
     * @return the dataInicial
     */
    public Date getDataInicial() {
        return dataInicial;
    }

    /**
     * @param dataInicial the dataInicial to set
     */
    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    /**
     * @return the dataFinal
     */
    public Date getDataFinal() {
        return dataFinal;
    }

    /**
     * @param dataFinal the dataFinal to set
     */
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void filtraMovimentos() {

        Conciliacao consulta = new Conciliacao();
        List<Criterion> filtro = new ArrayList<>();
        filtro.add(Restrictions.eq("dataConciliacao", dataConciliacao));
        filtro.add(Restrictions.eq("relacionamento", relacionamento));

        conciliacao = (Conciliacao) consulta.obterObjeto(filtro, null);

        if (conciliacao == null) {
            conciliacao = new Conciliacao();
            conciliacao.setDataConciliacao(dataConciliacao);
            conciliacao.setRelacionamento(relacionamento);
        }

        filtraMovimentosContasContabeis();
        filtraMovimentosContasBancarias();
        ordenaConciliados();
        limpaHistoricos();
        setGridVisivel(false);
    }

    public void limpaHistoricos() {

        if (listaDeHistoricosContabeis == null) {
            listaDeHistoricosContabeis = new ArrayList<>();
        }

        if (listaDeHistoricosBancarios == null) {
            listaDeHistoricosBancarios = new ArrayList<>();
        }

        listaDeHistoricosContabeis.clear();

        for (int i = 0; i < 10; i++) {
            listaDeHistoricosContabeis.add("");
        }

        listaDeHistoricosBancarios.clear();

        for (int i = 0; i < 10; i++) {
            listaDeHistoricosBancarios.add("");
        }

        dataInicial = null;
        dataFinal = null;

    }

    private List<ContaBancaria> getContasBancarias() {
        ContaBancaria consulta = new ContaBancaria();
        List<Criterion> filtro = new ArrayList<>();
        filtro.add(Restrictions.eq("relContabilidadeBanco", relacionamento));
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("idcontaBancaria"));
        return consulta.obterLista(filtro, ordem);
    }

    private List<ContaContabil> getContasContabeis() {
        ContaContabil consulta = new ContaContabil();
        List<Criterion> filtro = new ArrayList<>();
        filtro.add(Restrictions.eq("relContabilidadeBanco", relacionamento));
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("idcontaContabil"));
        return consulta.obterLista(filtro, ordem);
    }

    private void filtraMovimentosContasContabeis() {
        List<ContaContabil> contasContabeis = getContasContabeis();
        ContaContabilMovimento consulta = new ContaContabilMovimento();
        List<Criterion> filtro = new ArrayList<>();
        filtro.add(Restrictions.in("conta", contasContabeis));
        filtro.add(Restrictions.and(Restrictions.le("dataMov", dataConciliacao),
                Restrictions.or(Restrictions.eq("dataConciliacao", dataConciliacao),
                        Restrictions.isNull("dataConciliacao"))));
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("dataMov"));
        ordem.add(Order.asc("idcontaContabilMovimento"));
        listaDeMovimentoContaContabil = consulta.obterLista(filtro, ordem);

        listaDeMovimentoContaContabilNaoConciliados = new ArrayList<>();
        listaDeMovimentoContaContabilConciliados = new ArrayList<>();

        for (ContaContabilMovimento movimento : listaDeMovimentoContaContabil) {

            if (movimento.getDataConciliacao() == null) {
                listaDeMovimentoContaContabilNaoConciliados.add(movimento);
            } else {
                listaDeMovimentoContaContabilConciliados.add(movimento);
            }
        }

        saldoContaContabil = apuraSaldoContaContabil();
    }

    private void filtraMovimentosContasBancarias() {
        List<ContaBancaria> contasBancarias = getContasBancarias();
        ContaBancariaMovimento consulta = new ContaBancariaMovimento();
        List<Criterion> filtro = new ArrayList<>();
        filtro.add(Restrictions.in("conta", contasBancarias));
        filtro.add(Restrictions.and(Restrictions.le("dataMov", dataConciliacao),
                Restrictions.or(Restrictions.eq("dataConciliacao", dataConciliacao),
                        Restrictions.isNull("dataConciliacao"))));
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("dataMov"));
        ordem.add(Order.asc("idcontaBancariaMovimento"));
        listaDeMovimentoContaBancaria = consulta.obterLista(filtro, ordem);

        listaDeMovimentoContaBancariaNaoConciliados = new ArrayList<>();
        listaDeMovimentoContaBancariaConciliados = new ArrayList<>();

        for (ContaBancariaMovimento movimento : listaDeMovimentoContaBancaria) {

            if (movimento.getDataConciliacao() == null) {
                listaDeMovimentoContaBancariaNaoConciliados.add(movimento);
            } else {
                listaDeMovimentoContaBancariaConciliados.add(movimento);
            }
        }

        saldoContaBancaria = apuraSaldoContaBancaria();
    }

    private BigDecimal apuraSaldoContaContabil() {

        BigDecimal saldo = new BigDecimal(0);

        for (ContaContabil conta : relacionamento.getContaContabilCollection()) {
            saldo = saldo.add(getSaldoContaContabil(listaDeMovimentoContaContabil, conta));
        }

        return saldo;
    }

    public BigDecimal getSaldoContaContabil(List<ContaContabilMovimento> lista, ContaContabil filtro) {

        BigDecimal saldo = new BigDecimal(0);

        for (ContaContabilMovimento item : lista) {
            if (item.getConta().equals(filtro)) {
                saldo = (item.getSaldo());
            }
        }
        return saldo;
    }

    private BigDecimal apuraSaldoContaBancaria() {

        BigDecimal saldo = new BigDecimal(0);

        for (ContaBancaria conta : relacionamento.getContaBancariaCollection()) {
            saldo = saldo.add(getSaldoContaBancaria(listaDeMovimentoContaBancaria, conta));
        }

        return saldo;
    }

    public BigDecimal getSaldoContaBancaria(List<ContaBancariaMovimento> lista, ContaBancaria filtro) {

        BigDecimal saldo = new BigDecimal(0);

        for (ContaBancariaMovimento item : lista) {
            if (item.getConta().equals(filtro)) {
                saldo = (item.getSaldo());
            }
        }
        return saldo;
    }

    public BigDecimal totalMovimentosContaContabilNaoConciliados() {
        BigDecimal total = new BigDecimal(0);

        if (listaDeMovimentoContaContabilNaoConciliados != null) {
            for (ContaContabilMovimento movimento : listaDeMovimentoContaContabilNaoConciliados) {
                total = total.add(movimento.getValor());
            }
        }

        return total;
    }

    public BigDecimal totalMovimentosContaContabilNaoConciliadosSelecionados() {
        BigDecimal total = new BigDecimal(0);
        if (listaDeMovimentoContaContabilNaoConciliadosSelecionados != null) {
            for (ContaContabilMovimento movimento : listaDeMovimentoContaContabilNaoConciliadosSelecionados) {
                total = total.add(movimento.getValor());
            }
        }
        return total;
    }

    public BigDecimal totalMovimentosContaBancariaNaoConciliados() {
        BigDecimal total = new BigDecimal(0);
        for (ContaBancariaMovimento movimento : listaDeMovimentoContaBancariaNaoConciliados) {
            total = total.add(movimento.getValor());
        }
        return total;
    }

    public BigDecimal totalMovimentosContaBancariaNaoConciliadosSelecionados() {
        BigDecimal total = new BigDecimal(0);
        if (listaDeMovimentoContaBancariaNaoConciliadosSelecionados != null) {
            for (ContaBancariaMovimento movimento : listaDeMovimentoContaBancariaNaoConciliadosSelecionados) {
                total = total.add(movimento.getValor());
            }
        }
        return total;
    }

    public BigDecimal totalMovimentosContaContabilConciliados() {
        BigDecimal total = new BigDecimal(0);

        if (listaDeMovimentoContaContabilConciliados != null) {
            for (ContaContabilMovimento movimento : listaDeMovimentoContaContabilConciliados) {
                total = total.add(movimento.getValor());
            }
        }

        return total;
    }

    public BigDecimal totalMovimentosContaContabilConciliadosSelecionados() {
        BigDecimal total = new BigDecimal(0);
        if (listaDeMovimentoContaContabilConciliadosSelecionados != null) {
            for (ContaContabilMovimento movimento : listaDeMovimentoContaContabilConciliadosSelecionados) {
                total = total.add(movimento.getValor());
            }
        }
        return total;
    }

    public BigDecimal totalMovimentosContaBancariaConciliados() {
        BigDecimal total = new BigDecimal(0);
        for (ContaBancariaMovimento movimento : listaDeMovimentoContaBancariaConciliados) {
            total = total.add(movimento.getValor());
        }
        return total;
    }

    public BigDecimal totalMovimentosContaBancariaConciliadosSelecionados() {
        BigDecimal total = new BigDecimal(0);
        if (listaDeMovimentoContaBancariaConciliadosSelecionados != null) {
            for (ContaBancariaMovimento movimento : listaDeMovimentoContaBancariaConciliadosSelecionados) {
                total = total.add(movimento.getValor());
            }
        }
        return total;
    }

    public void concilia() {

        if (listaDeMovimentoContaContabilNaoConciliadosSelecionados.size() > 0 || listaDeMovimentoContaBancariaNaoConciliadosSelecionados.size() > 0) {

            for (ContaContabilMovimento movimento : listaDeMovimentoContaContabilNaoConciliadosSelecionados) {
                movimento.setDataConciliacao(dataConciliacao);
                movimento.setCombinacao(conciliacao.getNumeroCombinacoes() + 1);
                listaDeMovimentoContaContabilNaoConciliados.remove(movimento);
                listaDeMovimentoContaContabilConciliados.add(movimento);
            }

            for (ContaBancariaMovimento movimento : listaDeMovimentoContaBancariaNaoConciliadosSelecionados) {
                movimento.setDataConciliacao(dataConciliacao);
                movimento.setCombinacao(conciliacao.getNumeroCombinacoes() + 1);
                listaDeMovimentoContaBancariaNaoConciliados.remove(movimento);
                listaDeMovimentoContaBancariaConciliados.add(movimento);
            }

            conciliacao.setNumeroCombinacoes(conciliacao.getNumeroCombinacoes() + 1);
            listaDeMovimentoContaContabilNaoConciliadosSelecionados.clear();
            listaDeMovimentoContaBancariaNaoConciliadosSelecionados.clear();

        }

    }

    public void desconcilia() {

        if (listaDeMovimentoContaContabilConciliadosSelecionados.size() > 0 || listaDeMovimentoContaBancariaConciliadosSelecionados.size() > 0) {

            List<Integer> combinacoes = new ArrayList<>();

            for (ContaContabilMovimento movimento : listaDeMovimentoContaContabilConciliadosSelecionados) {
                if (!combinacoes.contains(movimento.getCombinacao())) {
                    combinacoes.add(movimento.getCombinacao());
                }
            }

            for (ContaBancariaMovimento movimento : listaDeMovimentoContaBancariaConciliadosSelecionados) {
                if (!combinacoes.contains(movimento.getCombinacao())) {
                    combinacoes.add(movimento.getCombinacao());
                }
            }

            List<ContaContabilMovimento> movimentosContabeisADesconciliar = new ArrayList<>();
            List<ContaBancariaMovimento> movimentosBancariosADesconciliar = new ArrayList<>();

            for (Integer combinacao : combinacoes) {

                for (ContaContabilMovimento movimento : listaDeMovimentoContaContabilConciliados) {
                    if (Objects.equals(movimento.getCombinacao(), combinacao)) {
                        movimento.setCombinacao(null);
                        movimentosContabeisADesconciliar.add(movimento);
                    }
                }

                for (ContaBancariaMovimento movimento : listaDeMovimentoContaBancariaConciliados) {
                    if (Objects.equals(movimento.getCombinacao(), combinacao)) {
                        movimento.setCombinacao(null);
                        movimentosBancariosADesconciliar.add(movimento);
                    }
                }

            }

            listaDeMovimentoContaContabilConciliados.removeAll(movimentosContabeisADesconciliar);
            listaDeMovimentoContaContabilNaoConciliados.addAll(movimentosContabeisADesconciliar);

            listaDeMovimentoContaBancariaConciliados.removeAll(movimentosBancariosADesconciliar);
            listaDeMovimentoContaBancariaNaoConciliados.addAll(movimentosBancariosADesconciliar);

            listaDeMovimentoContaContabilConciliadosSelecionados.clear();
            listaDeMovimentoContaBancariaConciliadosSelecionados.clear();

        }

        ordenaNaoConciliados();

    }

    public void cancelaConciliacao() {

        relacionamento = null;
        dataConciliacao = null;
        geraListaDeRelacionamentos();
        setGridVisivel(true);

    }

    public void salvaConciliacao() {

        PersistenciaConciliacao concilia = new PersistenciaConciliacao();
        concilia.setListaDeMovimentoContaBancariaConciliados(listaDeMovimentoContaBancariaConciliados);
        concilia.setListaDeMovimentoContaBancariaNaoConciliados(listaDeMovimentoContaBancariaNaoConciliados);
        concilia.setListaDeMovimentoContaContabilConciliados(listaDeMovimentoContaContabilConciliados);
        concilia.setListaDeMovimentoContaContabilNaoConciliados(listaDeMovimentoContaContabilNaoConciliados);
        concilia.setConciliacao(conciliacao);

        concilia.salva();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");

        relacionamento = null;
        dataConciliacao = null;
        geraListaDeRelacionamentos();
        setGridVisivel(true);
    }

    public void ordenaConciliados() {
        Collections.sort(listaDeMovimentoContaBancariaConciliados, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                ContaBancariaMovimento p1 = (ContaBancariaMovimento) o1;
                ContaBancariaMovimento p2 = (ContaBancariaMovimento) o2;
                return p1.getCombinacao() < p2.getCombinacao() ? -1 : (p1.getCombinacao() > p2.getCombinacao() ? +1 : 0);
            }
        });

        Collections.sort(listaDeMovimentoContaContabilConciliados, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                ContaContabilMovimento p1 = (ContaContabilMovimento) o1;
                ContaContabilMovimento p2 = (ContaContabilMovimento) o2;
                return p1.getCombinacao() < p2.getCombinacao() ? -1 : (p1.getCombinacao() > p2.getCombinacao() ? +1 : 0);
            }
        });

    }

    public void ordenaNaoConciliados() {

        Collections.sort(listaDeMovimentoContaContabilNaoConciliados, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                ContaContabilMovimento p1 = (ContaContabilMovimento) o1;
                ContaContabilMovimento p2 = (ContaContabilMovimento) o2;
                return p1.getIdcontaContabilMovimento() < p2.getIdcontaContabilMovimento() ? -1 : (p1.getIdcontaContabilMovimento() > p2.getIdcontaContabilMovimento() ? +1 : 0);
            }
        });

        Collections.sort(listaDeMovimentoContaContabilNaoConciliados, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                ContaContabilMovimento p1 = (ContaContabilMovimento) o1;
                ContaContabilMovimento p2 = (ContaContabilMovimento) o2;
                return p1.getDataMov().before(p2.getDataMov()) ? -1 : (p1.getDataMov().after(p2.getDataMov()) ? +1 : 0);
            }
        });

        Collections.sort(listaDeMovimentoContaBancariaNaoConciliados, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                ContaBancariaMovimento p1 = (ContaBancariaMovimento) o1;
                ContaBancariaMovimento p2 = (ContaBancariaMovimento) o2;
                return p1.getIdcontaBancariaMovimento() < p2.getIdcontaBancariaMovimento() ? -1 : (p1.getIdcontaBancariaMovimento() > p2.getIdcontaBancariaMovimento() ? +1 : 0);
            }
        });

        Collections.sort(listaDeMovimentoContaBancariaNaoConciliados, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                ContaBancariaMovimento p1 = (ContaBancariaMovimento) o1;
                ContaBancariaMovimento p2 = (ContaBancariaMovimento) o2;
                return p1.getDataMov().before(p2.getDataMov()) ? -1 : (p1.getDataMov().after(p2.getDataMov()) ? +1 : 0);
            }
        });

    }

    public void insereHistoricoContabil() {
        listaDeHistoricosContabeis.add("");
    }

    public void excluiHistoricoContabil(String historico) {
        listaDeHistoricosContabeis.remove(historico);
    }

    public void conciliaHistoricos() {

        List<ContaContabilMovimento> listaDeMovimentoContaContabilNaoConciliadosTemp = new ArrayList();
        List<ContaBancariaMovimento> listaDeMovimentoContaBancariaNaoConciliadosTemp = new ArrayList();

        if ((dataInicial == null) && dataFinal == null) {
            listaDeMovimentoContaContabilNaoConciliadosTemp = listaDeMovimentoContaContabilNaoConciliados;
            listaDeMovimentoContaBancariaNaoConciliadosTemp = listaDeMovimentoContaBancariaNaoConciliados;
        };

        if ((dataInicial != null) && dataFinal == null) {

            for (ContaContabilMovimento movimentoContabilNaoConciliado : listaDeMovimentoContaContabilNaoConciliados) {
                if ((movimentoContabilNaoConciliado.getDataMov().after(dataInicial)) || (movimentoContabilNaoConciliado.getDataMov().equals(dataInicial))) {
                    listaDeMovimentoContaContabilNaoConciliadosTemp.add(movimentoContabilNaoConciliado);
                }
            }

            for (ContaBancariaMovimento movimentoContaBancariaNaoConciliado : listaDeMovimentoContaBancariaNaoConciliados) {
                if ((movimentoContaBancariaNaoConciliado.getDataMov().after(dataInicial)) || (movimentoContaBancariaNaoConciliado.getDataMov().equals(dataInicial))) {
                    listaDeMovimentoContaBancariaNaoConciliadosTemp.add(movimentoContaBancariaNaoConciliado);
                }
            }

        };

        if ((dataInicial == null) && dataFinal != null) {

            for (ContaContabilMovimento movimentoContabilNaoConciliado : listaDeMovimentoContaContabilNaoConciliados) {
                if ((movimentoContabilNaoConciliado.getDataMov().before(dataFinal)) || (movimentoContabilNaoConciliado.getDataMov().equals(dataFinal))) {
                    listaDeMovimentoContaContabilNaoConciliadosTemp.add(movimentoContabilNaoConciliado);
                }
            }

            for (ContaBancariaMovimento movimentoContaBancariaNaoConciliado : listaDeMovimentoContaBancariaNaoConciliados) {
                if ((movimentoContaBancariaNaoConciliado.getDataMov().before(dataFinal)) || (movimentoContaBancariaNaoConciliado.getDataMov().equals(dataFinal))) {
                    listaDeMovimentoContaBancariaNaoConciliadosTemp.add(movimentoContaBancariaNaoConciliado);
                }
            }

        };

        if ((dataInicial != null) && dataFinal != null) {

            for (ContaContabilMovimento movimentoContabilNaoConciliado : listaDeMovimentoContaContabilNaoConciliados) {
                if ((movimentoContabilNaoConciliado.getDataMov().equals(dataInicial))
                        || (movimentoContabilNaoConciliado.getDataMov().equals(dataFinal))
                        || ((movimentoContabilNaoConciliado.getDataMov().after(dataInicial)) && (movimentoContabilNaoConciliado.getDataMov().before(dataFinal)))) {

                    listaDeMovimentoContaContabilNaoConciliadosTemp.add(movimentoContabilNaoConciliado);
                }
            }

            for (ContaBancariaMovimento movimentoContaBancariaNaoConciliado : listaDeMovimentoContaBancariaNaoConciliados) {
                if ((movimentoContaBancariaNaoConciliado.getDataMov().equals(dataInicial))
                        || (movimentoContaBancariaNaoConciliado.getDataMov().equals(dataFinal))
                        || ((movimentoContaBancariaNaoConciliado.getDataMov().after(dataInicial)) && (movimentoContaBancariaNaoConciliado.getDataMov().before(dataFinal)))) {

                    listaDeMovimentoContaBancariaNaoConciliadosTemp.add(movimentoContaBancariaNaoConciliado);
                }
            }

        };

        conciliaPorHistorico(listaDeMovimentoContaContabilNaoConciliadosTemp, listaDeMovimentoContaBancariaNaoConciliadosTemp);

    }

    private void conciliaPorHistorico(List<ContaContabilMovimento> pListaDeMovimentoContaContabilNaoConciliados, List<ContaBancariaMovimento> pListaDeMovimentoContaBancariaNaoConciliados) {

        //SOMA LANÇAMENTOS CONTABEIS
        List<ContaContabilMovimento> listaMovimentoContaContabilTemp = new ArrayList<>();
        BigDecimal totalMovimentoContaContabilTemp = new BigDecimal(0);

        for (ContaContabilMovimento movimentoContabilNaoConciliado : pListaDeMovimentoContaContabilNaoConciliados) {
            for (String historicoContabil : listaDeHistoricosContabeis) {
                if ((movimentoContabilNaoConciliado.getHistorico().equalsIgnoreCase(historicoContabil)) && (historicoContabil != null)) {
                    listaMovimentoContaContabilTemp.add(movimentoContabilNaoConciliado);
                    totalMovimentoContaContabilTemp = totalMovimentoContaContabilTemp.add(movimentoContabilNaoConciliado.getValor());
                }
            }
        }

        //SOMA LANÇAMENTOS BANCÁRIOS
        List<ContaBancariaMovimento> listaMovimentoContaBancariaTemp = new ArrayList<>();
        BigDecimal totalMovimentoContaBancariaTemp = new BigDecimal(0);

        for (ContaBancariaMovimento movimentoContaBancariaNaoConciliado : pListaDeMovimentoContaBancariaNaoConciliados) {
            for (String historicoContaBancaria : listaDeHistoricosBancarios) {
                if ((movimentoContaBancariaNaoConciliado.getHistorico().equalsIgnoreCase(historicoContaBancaria)) && (historicoContaBancaria != null)) {
                    listaMovimentoContaBancariaTemp.add(movimentoContaBancariaNaoConciliado);
                    totalMovimentoContaBancariaTemp = totalMovimentoContaBancariaTemp.add(movimentoContaBancariaNaoConciliado.getValor());
                }
            }
        }

        if ((!listaMovimentoContaContabilTemp.isEmpty())
                && (!listaMovimentoContaBancariaTemp.isEmpty())
                && (totalMovimentoContaContabilTemp.equals(totalMovimentoContaBancariaTemp))) {

            listaDeMovimentoContaContabilNaoConciliadosSelecionados = listaMovimentoContaContabilTemp;
            listaDeMovimentoContaBancariaNaoConciliadosSelecionados = listaMovimentoContaBancariaTemp;
            concilia();
            limpaHistoricos();
            JsfUtil.addSuccessMessage("Movimentos conciliados com sucesso", "");
        } else {
            JsfUtil.addErrorMessage("Nenhum movimento encontrado para conciliar!", "");
        }

    }

}
