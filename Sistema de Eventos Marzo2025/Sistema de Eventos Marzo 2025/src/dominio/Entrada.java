package dominio;

public class Entrada implements Comparable<Entrada> {

    private Evento Evento;
    private Cliente Cliente;
    private String Devuelta;

    public Entrada(Evento evento, Cliente cliente) {
        Evento = evento;
        Cliente = cliente;
        Devuelta = "N";
    }

    public Evento getEvento() {
        return Evento;
    }

    public void setEvento(Evento Evento) {
        this.Evento = Evento;
    }

    public Cliente getCliente() {
        return Cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.Cliente = Cliente;
    }

    public String getDevuelta() {
        return Devuelta;
    }

    public void setDevuelta(String devuelta) {
        this.Devuelta = devuelta;
    }

    @Override
    public int compareTo(Entrada e) {
        int dia1 = getEvento().getFecha().getDayOfMonth();
        int dia2 = e.getEvento().getFecha().getDayOfMonth();

        if (dia1 < dia2) {
            return -1;
        } else if (dia1 > dia2) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Entrada e = (Entrada) obj;

        return getEvento().getFecha().getDayOfMonth() == e.getEvento().getFecha().getDayOfMonth();
    }
}
