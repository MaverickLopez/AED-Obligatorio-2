package dominio;

import tads.lista.Lista;

public class Cliente implements Comparable<Cliente> {

    private String Cedula;
    private String Nombre;
    private Lista<Entrada> listaEntradas;

    public Cliente(String cedula, String nombre) {
        Cedula = cedula;
        Nombre = nombre;
        listaEntradas = new Lista();
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Lista<Entrada> getListaEntradas() {
        return listaEntradas;
    }

    public void setListaEntradas(Lista<Entrada> listaEntradas) {
        this.listaEntradas = listaEntradas;
    }

    @Override
    public boolean equals(Object obj) {
        Cliente c = (Cliente) obj;

        return getCedula().equals(c.getCedula());
    }

    @Override
    public String toString() {
        return getCedula() + "-" + getNombre();
    }

    @Override
    public int compareTo(Cliente c) {
        return getCedula().compareTo(c.getCedula());
    }

}
