package dominio;

public class Calificacion implements Comparable<Calificacion> {

    private int Puntaje;
    private String Comentario;
    private Cliente Cliente;

    public Calificacion(int puntaje, String comentario, Cliente cliente) {
        Puntaje = puntaje;
        Comentario = comentario;
        Cliente = cliente;
    }

    public int getPuntaje() {
        return Puntaje;
    }

    public void setPuntaje(int Puntaje) {
        this.Puntaje = Puntaje;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }

    public Cliente getCliente() {
        return Cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.Cliente = Cliente;
    }

    @Override
    public boolean equals(Object obj) {
        Calificacion c = (Calificacion) obj;
        return getCliente().equals(c.getCliente());
    }

    @Override
    public int compareTo(Calificacion c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
