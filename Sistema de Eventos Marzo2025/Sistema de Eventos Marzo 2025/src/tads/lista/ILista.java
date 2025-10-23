package tads.lista;

public interface ILista<T> {

    /*Pos: Inserta el dato pasado como parámetro al inicio de la lista.*/
    public void agregarInicio(T x);

    /*Pos: muestra los elementos de la lista.*/
    public void mostrar();

    /*Pos: Retorna la cantidad de elementos de la lista.*/
    public int cantidadElementos();

    /*Pos: retorna true si la lista está vacía y false en otro caso*/
    public boolean esVacia();

    /*Pos: vacía la lista.*/
    public void vaciar();

    /*Pos: Retorna true si el dato pasado como parámetro pertenece a la lista*/
    public boolean existeElemento(T x);

    /*Pre: Existe un elemento en el índice indicado*/
 /*Pos: retorna el elemento ubicado en el índice pasado como parámetro.*/
    public T obtenerElemento(int indice);

    /*Pos: Inserta el dato pasado como parámetro al final de la lista.*/
    public void agregarFinal(T x);

    /*Pos: se elimina el primer elemento de la lista*/
    public void eliminarInicio();

    /*Pos: se elimina el último elemento de la lista*/
    public void eliminarFinal();

    /*Inserta el elemento en la posicion pos de la lista. pos>=0 y <= cantElementos*/
    public void insertarEnPos(int pos, T elemento);

    /*Elimina el elemento en la posicion pos de la lista.  pos>=0 y <= cantElementos*/
    public void eliminarEnPos(int pos);

    /*Agrega un elemeno ordenado segun el equals del objeto*/
    public void agregarOrdenado(T elemento);

    /*Pos: retorna el elemento segun el string otorgado.*/
    public T obtenerElementoEntero(T elemento);

    /*Pos: elimina el elemento segun el string otorgado.*/
    public void eliminarElemento(T elemento);

}
