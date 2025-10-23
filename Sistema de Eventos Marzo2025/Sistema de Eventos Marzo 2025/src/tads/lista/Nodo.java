package tads.lista;

public class Nodo<T> {

    private T dato;
    private Nodo<T> siguente;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getSiguente() {
        return siguente;
    }

    public void setSiguente(Nodo siguente) {
        this.siguente = siguente;
    }
}
