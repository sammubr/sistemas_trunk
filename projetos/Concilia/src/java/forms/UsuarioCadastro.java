/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.sun.xml.bind.util.ListImpl;
import controls.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
    private Usuario usuario;
    private SortableDataModel<Usuario> lista;
    private boolean sortAscending = true;
    private String filtro;
    private Collection<Usuario> selectedDataList;
    ;
    private boolean selecionado;
    private List<Usuario> selectedCars;
    private List<Usuario> usuariosFiltrados;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public UsuarioCadastro() {
        geraListaDeUsuarios();
    }

    private void geraListaDeUsuarios() {
        Usuario consultaUsuario = new Usuario();
        lista = new SortableDataModel<>(new CollectionDataModel(consultaUsuario.obter(null, null, null)));
        usuariosFiltrados = new ArrayList<>();
        for (int i = 0; i < this.lista.getRowCount(); i++) {
            this.lista.setRowIndex(i);
            usuariosFiltrados.add((Usuario) this.lista.getRowData());
        }
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public String criaNovo() {
        setUsuario(new Usuario());
        return "cadastroItem.xhtml";
    }

    public String persiste() {
        getUsuario().persiste();
        geraListaDeUsuarios();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        return "cadastroList.xhtml";
    }

    public String edita() {
        setUsuario((Usuario) getLista().getRowData());
        return "cadastroItem.xhtml";
    }

    public void exclui() {
        for (Usuario usuario2 : selectedCars) {
            usuario2.exclui();
        }
        geraListaDeUsuarios();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
    }

// ------------------------------------------------------------- FILTRO DO GRID
    public void filtraGrid() {
        String teste = "teste";
    }
// -------------------------------------------------------------- ORDEM DO GRID

    public String sortByNome() {
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

    public void adiciona(Usuario usuario2) {
        if (selectedDataList == null) {
            selectedDataList = new ArrayList<>();
        }
        if (isSelecionado()) {

            selectedDataList.add(usuario2);
        } else {
            selectedDataList.remove(usuario2);
        }

    }

    /**
     * @return the selecionado
     */
    public boolean isSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the selectedCars
     */
    public List<Usuario> getSelectedCars() {
        return selectedCars;
    }

    /**
     * @param selectedCars the selectedCars to set
     */
    public void setSelectedCars(List<Usuario> selectedCars) {
        this.selectedCars = selectedCars;
    }

    /**
     * @return the usuariosFiltrados
     */
    public List<Usuario> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    /**
     * @param usuariosFiltrados the usuariosFiltrados to set
     */
    public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
        this.usuariosFiltrados = usuariosFiltrados;
    }
}
