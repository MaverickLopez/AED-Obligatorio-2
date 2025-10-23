package dominio;

import java.time.LocalDate;
import tads.cola.Cola;
import tads.lista.Lista;
import tads.pila.Pila;

public class Evento implements Comparable<Evento> {

    private String Codigo;
    private String Descripcion;
    private double PuntajePromedio;
    private int AforoNecesario;
    private LocalDate Fecha;
    private Sala Sala;

    private Pila<Entrada> entradas;
    private int cantEntradasVendidas;
    private int cantEntradasDisponibles;

    private Cola<Cliente> colaEspera;
    private Lista<Calificacion> calificaciones;

    public Evento(String codigo, String descripcion, int aforoNecesario, Sala sala, LocalDate fecha) {
        Codigo = codigo;
        Descripcion = descripcion;
        PuntajePromedio = 0;
        AforoNecesario = aforoNecesario;
        Fecha = fecha;
        Sala = sala;
        this.entradas = new Pila();
        cantEntradasVendidas = 0;
        cantEntradasDisponibles = aforoNecesario;
        this.colaEspera = new Cola();
        this.calificaciones = new Lista();
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getAforoNecesario() {
        return AforoNecesario;
    }

    public void setAforoNecesario(int AforoNecesario) {
        this.AforoNecesario = AforoNecesario;
    }

    public LocalDate getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDate Fecha) {
        this.Fecha = Fecha;
    }

    public Sala getSala() {
        return Sala;
    }

    public void setSala(Sala Sala) {
        this.Sala = Sala;
    }

    public Pila<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(Pila<Entrada> entradas) {
        this.entradas = entradas;
    }

    public Cola<Cliente> getColaEspera() {
        return colaEspera;
    }

    public void setColaEspera(Cola<Cliente> colaEspera) {
        this.colaEspera = colaEspera;
    }

    public Lista<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(Lista<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public int getCantEntradasVendidas() {
        return cantEntradasVendidas;
    }

    public void setCantEntradasVendidas(int cantEntradasVendidas) {
        this.cantEntradasVendidas = cantEntradasVendidas;
    }

    public int getCantEntradasDisponibles() {
        return cantEntradasDisponibles;
    }

    public void setCantEntradasDisponibles(int cantEntradasDisponibles) {
        this.cantEntradasDisponibles = cantEntradasDisponibles;
    }

    public double getPuntajePromedio() {
        return PuntajePromedio;
    }

    public void setPuntajePromedio(double PuntajePromedio) {
        this.PuntajePromedio = PuntajePromedio;
    }

    @Override
    public boolean equals(Object obj) {
        Evento e = (Evento) obj;

        return getCodigo().equals(e.getCodigo());
    }

    @Override
    public String toString() {
        return getCodigo() + "-" + getDescripcion() + "-"
                + getSala().getNombre() + "-" + getCantEntradasDisponibles() + "-" + getCantEntradasVendidas();
    }

    @Override
    public int compareTo(Evento e) {
        return getCodigo().compareTo(e.getCodigo());
    }

    public void calcularPromedio() {
        double promedio = 0;

        if (!getCalificaciones().esVacia()) {
            for (int i = 0; i < getCalificaciones().cantidadElementos(); i++) {
                int puntaje = getCalificaciones().obtenerElemento(i).getPuntaje();
                promedio += puntaje;
            }
            promedio = promedio / getCalificaciones().cantidadElementos();
            setPuntajePromedio(promedio);
        }
        
    }
    
    
    
    
}
