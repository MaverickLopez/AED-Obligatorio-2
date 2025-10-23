package dominio;

import java.time.LocalDate;
import tads.lista.Lista;

public class Sala implements Comparable<Sala> {

    private String Nombre;
    private int Capacidad;
    private Lista<Evento> listaEventos;

    public Sala(String nombre, int capacidad) {
        Nombre = nombre;
        Capacidad = capacidad;
        this.listaEventos = new Lista();
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int Capacidad) {
        this.Capacidad = Capacidad;
    }

    public Lista<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(Lista<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    @Override
    public boolean equals(Object obj) {
        Sala s = (Sala) obj;

        return getNombre().equals(s.getNombre());
    }

    public boolean ocupada(LocalDate fecha) {
        boolean ret = false;

        for (int i = 0; i < listaEventos.cantidadElementos() && ret == false; i++) {
            Evento e = listaEventos.obtenerElemento(i);
            if (e.getFecha().getMonthValue() == fecha.getMonthValue()
                    && e.getFecha().getDayOfWeek() == fecha.getDayOfWeek()) {
                ret = true;
            }
        }

        return ret;
    }

    @Override
    public String toString() {
        return getNombre() + "-" + getCapacidad();
    }

    @Override
    public int compareTo(Sala s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void eliminarEvento(Evento evento) {
        boolean eliminado = false;
        for (int i = 0; i < listaEventos.cantidadElementos() && eliminado == false; i++) {
            Evento e = listaEventos.obtenerElemento(i);

            if (e.equals(evento)) {
                listaEventos.eliminarEnPos(i);
                eliminado = true;
            }
        }
    }
}
