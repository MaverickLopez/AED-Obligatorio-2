package tads.cola;

public interface ICola<T> {
    
    public void mostrar();
    
    public int cantidadElementos ();
    
    public boolean esVacia();
    
    public void vaciar();
            
    public void enqueue (T x);
    
    public void dequeue();
    
    public T front();
}
