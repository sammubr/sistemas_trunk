/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.CollectionDataModel;
import javax.inject.Named;
import persistencia.SortableDataModel;
import util.JsfUtil;

/**
 *
 * @author samuel
 */
@Named("usuarioCadastro")
@SessionScoped
public class UsuarioCadastro implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private Usuario item;
    private SortableDataModel<Usuario> lista;
    private boolean sortAscending = true;
    private List<Usuario> itensSelecionados;
    private String ordem = "nome";
    private String filtro;

    // ---------------------------------------------------------------- CONSTRUCTOR    
    public UsuarioCadastro() {
        geraLista();
    }

    private void geraLista() {
        List<String> ordem_temp = new ArrayList<>();
        ordem_temp.add(ordem);
        Usuario consulta = new Usuario();
        lista = new SortableDataModel<>(new CollectionDataModel(consulta.obter(null, null, ordem_temp)));
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public Usuario getItem() {
        return item;
    }

    public void setItem(Usuario item) {
        this.item = item;
    }

    public SortableDataModel<Usuario> getLista() {
        return lista;
    }

    public void setLista(SortableDataModel<Usuario> lista) {
        this.lista = lista;
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }

    public List<Usuario> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<Usuario> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public String criaNovo() {
        setItem(new Usuario());
        return "cadastroItem.xhtml";
    }

    public String persiste() {
        getItem().persiste();
        geraLista();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        return "cadastroList.xhtml";
    }

    public String edita() {
        setItem((Usuario) getLista().getRowData());
        return "cadastroItem.xhtml";
    }

    public void exclui() {
        for (Usuario itemSelecionado : itensSelecionados) {
            itemSelecionado.exclui();
        }
        geraLista();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
    }

// ------------------------------------------------------------- FILTRO DO GRID
    public void filtraGrid() {

        if (getFiltro().equals("")) {
            geraLista();
        } else {

            List<String> atributos = new ArrayList<>();
            atributos.add(ordem);

            List<Object> valores = new ArrayList<>();
            valores.add(getFiltro());

            List<String> ordem_temp = new ArrayList<>();
            ordem_temp.add(ordem);

            Usuario consulta = new Usuario();
            lista = new SortableDataModel<>(new CollectionDataModel(consulta.obter(atributos, valores, ordem_temp)));

        }
    }
// -------------------------------------------------------------- ORDEM DO GRID

    public String sortByNome() {
        setOrdem("nome");
        if (isSortAscending()) {
            getLista().sortBy(new Comparator<Usuario>() {
                @Override
                public int compare(Usuario item1, Usuario item2) {
                    return item1.getNome().compareTo(item2.getNome());
                }
            });
            setSortAscending(false);
        } else {
            //descending order
            getLista().sortBy(new Comparator<Usuario>() {
                @Override
                public int compare(Usuario item1, Usuario item2) {
                    return item2.getNome().compareTo(item1.getNome());
                }
            });
            setSortAscending(true);
        }
        return null;
    }

    public String sortByLogin() {
        setOrdem("login");
        if (isSortAscending()) {
            getLista().sortBy(new Comparator<Usuario>() {
                @Override
                public int compare(Usuario item1, Usuario item2) {
                    return item1.getLogin().compareTo(item2.getLogin());
                }
            });
            setSortAscending(false);
        } else {
            //descending order
            getLista().sortBy(new Comparator<Usuario>() {
                @Override
                public int compare(Usuario item1, Usuario item2) {
                    return item2.getLogin().compareTo(item1.getLogin());
                }
            });
            setSortAscending(true);
        }
        return null;
    }

    public String sortByNivel() {
        setOrdem("nivel");
        if (isSortAscending()) {
            getLista().sortBy(new Comparator<Usuario>() {
                @Override
                public int compare(Usuario item1, Usuario item2) {
                    return Integer.compare(item1.getNivel(), item2.getNivel());
                }
            });
            setSortAscending(false);
        } else {
            //descending order
            getLista().sortBy(new Comparator<Usuario>() {
                @Override
                public int compare(Usuario item1, Usuario item2) {
                    return Integer.compare(item2.getNivel(), item1.getNivel());
                }
            });
            setSortAscending(true);
        }
        return null;
    }
}
