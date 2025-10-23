package tads.cola;


public class Cola<T extends Comparable> implements ICola<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantElementos;

    public Cola() {
        this.inicio = null;
        this.fin = null;
        cantElementos = 0;
    }

    private void agregarInicio(T x) {
        Nodo<T> nuevo = new Nodo(x);

        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }

        cantElementos++;
    }

    @Override
    public void mostrar() {
        Nodo<T> aux = inicio;
        while (aux != null) {
            System.out.print(aux.getDato() + " - ");
            aux = aux.getSiguiente();
        }
    }

    @Override
    public int cantidadElementos() {
        return cantElementos;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public void vaciar() {
        inicio = null;
        fin = null;
        cantElementos = 0;
    }

    @Override
    public void enqueue(T x) {
        if (esVacia()) {
            agregarInicio(x);
        } else {
            Nodo<T> nuevo = new Nodo(x);
            fin.setSiguiente(nuevo);
            fin = nuevo;
            cantElementos++;
        }
    }

    @Override
    public void dequeue() {
        if (!esVacia()) {
            if (cantElementos == 1) {
                vaciar();
            } else {
                Nodo aBorrar = inicio;
                inicio = inicio.getSiguiente();
                aBorrar.setSiguiente(null);
                cantElementos--;
            }

        }
    }

    @Override
    public T front() {
        return inicio.getDato();
    }

    public Nodo<T> getInicio() {
        return inicio;
    }

    public void setInicio(Nodo<T> inicio) {
        this.inicio = inicio;
    }

    public int getCantElementos() {
        return cantElementos;
    }

    public void setCantElementos(int cantElementos) {
        this.cantElementos = cantElementos;
    }

}
