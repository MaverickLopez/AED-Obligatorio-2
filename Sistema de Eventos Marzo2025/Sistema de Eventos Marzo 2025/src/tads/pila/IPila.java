package tads.pila;


public interface IPila<T> {

    public void push(T x);

    public void mostrar();

    public boolean esVacia();

    public void vaciar();

    public int cantidadElementos();

    public void pop();

    public T top();
}
