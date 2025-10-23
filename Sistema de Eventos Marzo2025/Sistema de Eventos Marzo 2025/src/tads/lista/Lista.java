package tads.lista;

public class Lista<T extends Comparable> implements ILista<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantElementos;

    public Lista() {
        this.inicio = null;
        this.fin = null;
        this.cantElementos = 0;
    }

    @Override
    public void agregarInicio(T x) {
        Nodo<T> nuevo = new Nodo(x);
        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
            cantElementos++;
        } else {
            nuevo.setSiguente(inicio);
            inicio = nuevo;
            cantElementos++;
        }
        System.out.println("Agregado al inicio");
    }

    @Override
    public void mostrar() {
        Nodo<T> aux = inicio;

        for (int i = 0; i < cantElementos; i++) {
            if (i < cantElementos - 1) {
                System.out.print(aux.getDato() + "#");
            } else {
                System.out.print(aux.getDato());
            }
            aux = aux.getSiguente();
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
        System.out.println("Vaciado correctamente");
    }

    @Override
    public boolean existeElemento(T x) {
        boolean ret = false;
        Nodo<T> aux = inicio;

        if (inicio.getDato().equals(x)) {
            ret = true;

        } else if (fin.getDato().equals(x)) {
            ret = true;

        } else {
            while (aux != null && ret == false) {
                if (aux.getDato().equals(x)) {
                    ret = true;
                }

                aux = aux.getSiguente();
            }
        }

        return ret;
    }

    @Override
    public T obtenerElemento(int indice) {
        Nodo<T> aux = inicio;
        T ret = null;

        if (!esVacia()) {
            for (int i = 0; i <= indice && aux != null && ret == null; i++) {
                if (i == indice) {
                    ret = aux.getDato();
                }
                aux = aux.getSiguente();
            }
        }

        return ret;
    }

    @Override
    public void agregarFinal(T x) {
        if (esVacia()) {
            agregarInicio(x);
        } else {
            Nodo<T> nuevo = new Nodo(x);

            fin.setSiguente(nuevo);
            fin = nuevo;
            cantElementos++;
        }
        System.out.println("Agregado al final correctamente");
    }

    @Override
    public void eliminarInicio() {
        if (!esVacia()) {
            if (cantElementos == 1) {
                vaciar();
            } else {
                Nodo<T> borrar = inicio;
                inicio = inicio.getSiguente();
                borrar.setSiguente(null);
                cantElementos--;
            }
            System.out.println("Eliminado inicio con exito");
        } else {
            System.out.println("Lista vacia");
        }
    }

    @Override
    public void eliminarFinal() {
        if (!esVacia()) {
            if (cantElementos == 1) { // Tengo un elemento solo
                vaciar();
            } else { // Mas de un elemento
                Nodo<T> aux = inicio;
                while (aux.getSiguente().getSiguente() != null) {
                    aux = aux.getSiguente();
                }

                aux.setSiguente(null);
                fin = aux;
                cantElementos--;
            }
        }
        System.out.println("Final eliminado con exito");
    }

    @Override
    public void insertarEnPos(int pos, T elemento) {
        if (pos == 0) {
            agregarInicio(elemento);
        } else {
            if (pos == cantElementos) {
                agregarFinal(elemento);
            } else {
                int indiceActual = 1;
                Nodo<T> anterior = inicio;
                Nodo<T> actual = inicio.getSiguente();

                while (pos != indiceActual) {
                    anterior = actual;
                    actual = actual.getSiguente();
                    indiceActual++;
                }
                Nodo<T> nuevo = new Nodo(elemento);
                nuevo.setSiguente(actual);
                anterior.setSiguente(actual);
                cantElementos++;
            }
        }
    }

    @Override
    public void eliminarEnPos(int pos) {
        if (esVacia() || pos == 0) {
            eliminarInicio();
        } else if (cantElementos <= pos) {
            eliminarFinal();
        } else {
            Nodo<T> aux = inicio;

            for (int i = 0; i < pos - 1; i++) {
                aux = aux.getSiguente();
            }

            Nodo<T> aux2 = aux.getSiguente().getSiguente();
            aux.setSiguente(aux2);
            cantElementos--;
            System.out.println("Eliminado con exito");
        }
    }

    @Override
    public void agregarOrdenado(T elem) {
        if (esVacia() || elem.compareTo(inicio.getDato()) < 0) {
            agregarInicio(elem);
        } else if (elem.compareTo(fin.getDato()) >= 0) {
            agregarFinal(elem);
        } else {
            Nodo<T> actual = inicio;

            while (actual.getSiguente() != null && actual.getSiguente().getDato().compareTo(elem) <= 0) {
                actual = actual.getSiguente();
            }

            if (actual.getSiguente() == null) {
                agregarFinal(elem);
            } else {
                Nodo<T> nuevo = new Nodo(elem);
                nuevo.setSiguente(actual.getSiguente());
                actual.setSiguente(nuevo);
                cantElementos++;
            }
        }

    }

    @Override
    public T obtenerElementoEntero(T elemento) {
        Nodo<T> aux = inicio;
        T ret = null;

        if (!esVacia()) {
            for (int i = 0; i <= cantElementos && aux != null && ret == null; i++) {
                if (aux.getDato().equals(elemento)) {
                    ret = aux.getDato();
                }
                aux = aux.getSiguente();
            }
        }

        return ret;
    }

    @Override
    public void eliminarElemento(T elemento) {
        boolean eliminado = false;

        if (inicio.getDato().equals(elemento)) {
            eliminarInicio();
        } else if (fin.getDato().equals(elemento)) {
            eliminarFinal();
        } else if (!esVacia()) {
            Nodo<T> anterior = inicio;
            Nodo<T> actual = inicio.getSiguente();

            for (int i = 0; i < cantElementos && eliminado == false; i++) {

                if (actual.getDato().equals(elemento)) {
                    anterior.setSiguente(actual.getSiguente());
                    cantElementos--;
                    System.out.println("Eliminado con exito");
                    eliminado = true;
                }

                anterior = anterior.getSiguente();
                actual = actual.getSiguente();
            }
        }

    }

    public int getCantElementos() {
        return cantElementos;
    }

    public void setCantElementos(int cantElementos) {
        this.cantElementos = cantElementos;

    }

    public Nodo<T> getFin() {
        return fin;
    }

}
