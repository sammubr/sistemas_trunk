package forms;

import controls.ContaBancaria;
import controls.ContaBancariaMovimento;
import controls.ContaContabil;
import controls.ContaContabilMovimento;
import controls.RelContabilidadeBanco;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

@Named("conciliacaoManual")
@ViewScoped
public class ConciliacaoManual implements Serializable {

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

// ---------------------------------------------------------------- CONSTRUCTOR    
    public ConciliacaoManual() {
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

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void filtraMovimentos() {
        filtraMovimentosContasContabeis();
        filtraMovimentosContasBancarias();
        setGridVisivel(false);
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

        if (listaDeMovimentoContaContabilNaoConciliadosSelecionados.size() > 0 && listaDeMovimentoContaBancariaNaoConciliadosSelecionados.size() > 0) {

            for (ContaContabilMovimento movimento : listaDeMovimentoContaContabilNaoConciliadosSelecionados) {
                movimento.setDataConciliacao(dataConciliacao);
                listaDeMovimentoContaContabilNaoConciliados.remove(movimento);
                listaDeMovimentoContaContabilConciliados.add(movimento);
            }

            for (ContaBancariaMovimento movimento : listaDeMovimentoContaBancariaNaoConciliadosSelecionados) {
                movimento.setDataConciliacao(dataConciliacao);
                listaDeMovimentoContaBancariaNaoConciliados.remove(movimento);
                listaDeMovimentoContaBancariaConciliados.add(movimento);
            }

        }
        
        listaDeMovimentoContaContabilNaoConciliadosSelecionados.clear();
        listaDeMovimentoContaBancariaNaoConciliadosSelecionados.clear();

    }
    
        public void desconcilia() {

        if (listaDeMovimentoContaContabilConciliadosSelecionados.size() > 0 && listaDeMovimentoContaBancariaConciliadosSelecionados.size() > 0) {

            for (ContaContabilMovimento movimento : listaDeMovimentoContaContabilConciliadosSelecionados) {
                movimento.setDataConciliacao(null);
                listaDeMovimentoContaContabilConciliados.remove(movimento);
                listaDeMovimentoContaContabilNaoConciliados.add(movimento);
            }

            for (ContaBancariaMovimento movimento : listaDeMovimentoContaBancariaConciliadosSelecionados) {
                movimento.setDataConciliacao(null);
                listaDeMovimentoContaBancariaConciliados.remove(movimento);
                listaDeMovimentoContaBancariaNaoConciliados.add(movimento);
            }

        }
        
        listaDeMovimentoContaContabilConciliadosSelecionados.clear();
        listaDeMovimentoContaBancariaConciliadosSelecionados.clear();

    }

}
