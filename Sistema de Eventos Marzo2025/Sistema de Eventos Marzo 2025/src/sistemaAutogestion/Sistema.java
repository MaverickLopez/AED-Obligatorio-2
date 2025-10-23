package sistemaAutogestion;

import dominio.Calificacion;
import dominio.Cliente;
import dominio.Entrada;
import dominio.Evento;
import dominio.Sala;
import java.time.LocalDate;
import tads.lista.Lista;
import tads.pila.Pila;

public class Sistema implements IObligatorio {

    private Lista<Entrada> listaEntradas;
    private Lista<Cliente> listaClientes;
    private Lista<Sala> listaSalas;
    private Lista<Evento> listaEventos;

    public Sistema() {
        listaEntradas = new Lista();
        listaClientes = new Lista();
        listaSalas = new Lista();
        listaEventos = new Lista();
    }

    @Override
    public Retorno crearSistemaDeGestion() {
        listaEntradas = new Lista();
        listaClientes = new Lista();
        listaSalas = new Lista();
        listaEventos = new Lista();
        System.out.println(Retorno.ok().resultado);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarSala(String nombre, int capacidad) {
        Sala s = new Sala(nombre, capacidad);

        if (listaSalas.cantidadElementos() != 0) {

            if (listaSalas.existeElemento(s)) {
                System.out.println(Retorno.error1().resultado);
                return Retorno.error1();
            } else if (s.getCapacidad() <= 0) {
                System.out.println(Retorno.error2().resultado);
                return Retorno.error2();
            } else {
                listaSalas.agregarInicio(s);
                System.out.println(Retorno.ok().resultado);
                return Retorno.ok();
            }

        } else if (s.getCapacidad() <= 0) {
            System.out.println(Retorno.error2().resultado);
            return Retorno.error2();
        } else {
            listaSalas.agregarInicio(s);
            System.out.println(Retorno.ok().resultado);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno eliminarSala(String nombre) {
        Sala s = new Sala(nombre, 100);

        if (!listaSalas.existeElemento(s)) {
            System.out.println(Retorno.error1().resultado);
            return Retorno.error1();
        } else {
            boolean encontrado = false;

            for (int i = 0; i < listaSalas.cantidadElementos() && !encontrado; i++) {
                Sala salaBuscada = listaSalas.obtenerElemento(i);

                if (s.equals(salaBuscada)) {
                    listaSalas.eliminarEnPos(i);
                }
            }
            System.out.println(Retorno.ok().resultado);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
        if (aforoNecesario > 0) {

            for (int i = 0; i < listaSalas.cantidadElementos(); i++) {
                Sala salaBuscada = listaSalas.obtenerElemento(i);
                boolean ocupada = salaBuscada.ocupada(fecha);

                if (salaBuscada.getCapacidad() >= aforoNecesario && !ocupada) {
                    Evento e = new Evento(codigo, descripcion, aforoNecesario, salaBuscada, fecha);

                    if (listaEventos.cantidadElementos() == 0 || !listaEventos.existeElemento(e)) {
                        listaEventos.agregarOrdenado(e);

                        Lista<Evento> eventosAux = salaBuscada.getListaEventos();
                        eventosAux.agregarInicio(e);
                        salaBuscada.setListaEventos(eventosAux);

                        return Retorno.ok();
                    } else {
                        System.out.println(Retorno.error1().resultado);
                        return Retorno.error1();
                    }
                }
            }

            System.out.println(Retorno.error3().resultado);
            return Retorno.error3();
        } else {
            System.out.println(Retorno.error2().resultado);
            return Retorno.error2();
        }
    }

    @Override
    public Retorno registrarCliente(String cedula, String nombre) {

        if (cedula.length() == 8) {

            // Aca valido que la cedula no tenga letras o simbolos
            for (int i = 0; i < cedula.length(); i++) {
                char c = cedula.charAt(i);
                if (c < '0' || c > '9') {
                    System.out.println(Retorno.error1().resultado);
                    return Retorno.error1();
                }
            }

            // Si no tiene letas o simbolos crea el cliente y continua
            Cliente c = new Cliente(cedula, nombre);

            if (listaClientes.cantidadElementos() == 0) {
                listaClientes.agregarInicio(c);
                System.out.println(Retorno.ok().resultado);
                return Retorno.ok();
            } else {

                if (!listaClientes.existeElemento(c)) {
                    listaClientes.agregarOrdenado(c);
                    System.out.println(Retorno.ok().resultado);
                    return Retorno.ok();
                } else {
                    System.out.println(Retorno.error2().resultado);
                    return Retorno.error2();
                }
            }

        } else {
            System.out.println(Retorno.error1().resultado);
            return Retorno.error1();
        }
    }

    @Override
    public Retorno comprarEntrada(String cedula, String codigoEvento) {
        Sala sS = new Sala("x", 1);
        Evento sE = new Evento(codigoEvento, "", 1, sS, LocalDate.now());
        Cliente sC = new Cliente(cedula, "");

        Evento evento = listaEventos.obtenerElementoEntero(sE);
        Cliente cliente = listaClientes.obtenerElementoEntero(sC);

        if (cliente == null) {
            System.out.println(Retorno.error1().resultado);
            return Retorno.error1();
        }

        if (evento == null) {
            System.out.println(Retorno.error2().resultado);
            return Retorno.error2();
        }

        // Entradas disponibles y vendidas
        int vendidas = evento.getCantEntradasVendidas();
        int disponibles = evento.getCantEntradasDisponibles();

        if (disponibles > 0) {
            Entrada entrada = new Entrada(evento, cliente);

            evento.getEntradas().push(entrada);
            cliente.getListaEntradas().agregarInicio(entrada);
            listaEntradas.agregarInicio(entrada);
            vendidas++;
            disponibles--;

            evento.setCantEntradasVendidas(vendidas);
            evento.setCantEntradasDisponibles(disponibles);
            System.out.println("Entrada comprada con exito");
        } else {
            evento.getColaEspera().enqueue(cliente);
            System.out.println("No hay entradas disponibles, agruegado a la lista de espera");
        }
        return Retorno.ok();
    }

    @Override
    public Retorno eliminarEvento(String codigo) {
        Sala sS = new Sala("x", 1);
        Evento sE = new Evento(codigo, "", 1, sS, LocalDate.now());

        // Busco el evento con un evento señuelo
        Evento evento = listaEventos.obtenerElementoEntero(sE);

        // Si no lo encontro, retorno error 1
        if (evento == null) {
            System.out.println(Retorno.error1().resultado);
            return Retorno.error1();
        }

        // Valido que tenga entradas vendidas
        if (evento.getCantEntradasVendidas() == 0) {
            // Libero la sala
            Sala salaEvento = listaSalas.obtenerElementoEntero(evento.getSala());
            salaEvento.eliminarEvento(evento);

            // Elimino el evento de la lista
            for (int i = 0; i < listaEventos.cantidadElementos() && listaEventos.existeElemento(evento); i++) {
                Evento e = listaEventos.obtenerElemento(i);
                if (e.equals(evento)) {
                    listaEventos.eliminarEnPos(i);
                }
            }

            System.out.println(Retorno.ok().resultado);
            return Retorno.ok();
        } else {
            System.out.println(Retorno.error2().resultado);
            return Retorno.error2();
        }
    }

    @Override
    public Retorno devolverEntrada(String cedula, String codigoEvento) {
        // Señuelos
        Sala sS = new Sala("x", 1);
        Evento sE = new Evento(codigoEvento, "", 1, sS, LocalDate.now());
        Cliente sC = new Cliente(cedula, "");

        // Busco el cliente
        Cliente cliente = listaClientes.obtenerElementoEntero(sC);

        // Si es vacio da error 1
        if (cliente == null) {
            System.out.println(Retorno.error1().resultado);
            return Retorno.error1();
        }

        // Busco el evento
        Evento evento = listaEventos.obtenerElementoEntero(sE);

        // Si es vacio da error 2
        if (evento == null) {
            System.out.println(Retorno.error2().resultado);
            return Retorno.error2();
        }

        // Señuelo de la entrada
        Entrada sEN = new Entrada(sE, cliente);

        // Busco la entrada en el evento
        Entrada entrada = null;
        Pila entradas = new Pila();
        for (int i = 0; i < evento.getEntradas().cantidadElementos()
                && entrada == null; i++) {
            Entrada e = evento.getEntradas().top();

            if (e.getCliente().getCedula() == sEN.getCliente().getCedula()) {
                entrada = e;
                listaEntradas.eliminarElemento(entrada);
                e.setDevuelta("D");
                evento.getEntradas().pop();
                evento.setCantEntradasVendidas(evento.getCantEntradasVendidas() - 1);
                evento.setCantEntradasDisponibles(evento.getCantEntradasDisponibles() + 1);
                System.out.println("Entrada devuelta con exito");
            } else {
                entradas.push(e);
                evento.getEntradas().pop();
            }
        }

        // Relleno las entradas
        for (int i = 0; i < entradas.cantidadElementos(); i++) {
            Entrada e = (Entrada) entradas.top();
            evento.getEntradas().push(e);
            entradas.pop();
        }

        // Asigno la entrada al primero en la fila de espera
        if (evento.getColaEspera().cantidadElementos() > 0) {
            comprarEntrada(evento.getColaEspera().front().getCedula(), evento.getCodigo());
            evento.getColaEspera().dequeue();
        }

        return Retorno.ok();
    }

    @Override
    public Retorno calificarEvento(String cedula, String codigoEvento, int puntaje, String comentario) {
        Sala x = new Sala("x", 1);
        Evento x2 = new Evento(codigoEvento, "", 1, x, LocalDate.now());
        Cliente x3 = new Cliente(cedula, "");

        // Valido que el puntaje sea entre 1 y 10
        if (puntaje < 1 || puntaje > 10) {
            System.out.println(Retorno.error3().resultado);
            return Retorno.error3();
        }

        // Busco el evento
        Evento evento = listaEventos.obtenerElementoEntero(x2);

        // Si no lo encuentro retorno error 2
        if (evento == null) {
            System.out.println(Retorno.error2().resultado);
            return Retorno.error2();
        }

        // Busco el cliente
        Cliente cliente = listaClientes.obtenerElementoEntero(x3);

        // Si no lo encuentro retorno error 1
        if (cliente == null) {
            System.out.println(Retorno.error1().resultado);
            return Retorno.error1();
        }

        // Busco si el cliente ya califico el evento
        Calificacion sC = new Calificacion(1, "", cliente);
        Calificacion c = evento.getCalificaciones().obtenerElementoEntero(sC);

        // Si no es nula, significa que hay una calificacion de ese cliente
        if (c != null) {
            System.out.println(Retorno.error4().resultado);
            return Retorno.error4();
        } else {
            //Creo la calificacion
            Calificacion calificacion = new Calificacion(puntaje, comentario, cliente);
            evento.getCalificaciones().agregarInicio(calificacion);

            // Aca hago el calculo de promedio de puntaje
            evento.calcularPromedio();
            System.out.println(evento.getPuntajePromedio());

            System.out.println(Retorno.ok().resultado);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno listarSalas() {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        String cadena = "";

        for (int i = 0; i < listaSalas.cantidadElementos(); i++) {
            Sala s = listaSalas.obtenerElemento(i);
            if (i < listaSalas.cantidadElementos() - 1) {
                cadena += s.toString() + "#";
            } else {
                cadena += s.toString();
            }
        }

        r.valorString = cadena;
        return r;
    }

    @Override
    public Retorno listarEventos() {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        String cadena = "";

        for (int i = 0; i < listaEventos.cantidadElementos(); i++) {
            Evento e = listaEventos.obtenerElemento(i);
            if (i < listaEventos.cantidadElementos() - 1) {
                cadena += e.toString() + "#";
            } else {
                cadena += e.toString();
            }
        }

        r.valorString = cadena;
        return r;
    }

    @Override
    public Retorno listarClientes() {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        String cadena = "";

        for (int i = 0; i < listaClientes.cantidadElementos(); i++) {
            Cliente c = listaClientes.obtenerElemento(i);
            if (i < listaClientes.cantidadElementos() - 1) {
                cadena += c.toString() + "#";
            } else {
                cadena += c.toString();
            }
        }

        r.valorString = cadena;
        return r;
    }

    @Override
    public Retorno esSalaOptima(String[][] vistaSala) {
        int contColumnasOptimas = 0;

        for (int i = 0; i < vistaSala.length; i++) {
            int contLibres = 0;
            int maxOcupadosConsecutivos = 0;
            int contOcupadosConsecutivos = 0;

            for (int j = 0; j < vistaSala[i].length; j++) {

                if (vistaSala[i][j] == "X") {
                    contLibres++;
                    if (maxOcupadosConsecutivos < contOcupadosConsecutivos) {
                        maxOcupadosConsecutivos = contOcupadosConsecutivos;
                    }
                    contOcupadosConsecutivos = 0;

                } else if (vistaSala[i][j] == "O") {
                    contOcupadosConsecutivos++;
                }

            }

            if (maxOcupadosConsecutivos > contLibres) {
                contColumnasOptimas++;
            }
        }

        if (contColumnasOptimas >= 2) {
            System.out.println("Es óptimo");
            return Retorno.ok("Es óptimo");
        } else {
            System.out.println("No es óptimo");
            return Retorno.ok("No es óptimo");
        }
    }

    @Override
    public Retorno listarClientesDeEvento(String codigo, int n) {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        Sala sS = new Sala("x", 1);
        Evento sE = new Evento(codigo, "", 1, sS, LocalDate.now());

        // Busco el evento
        Evento evento = listaEventos.obtenerElementoEntero(sE);

        // Si es null da error 1
        if (evento == null) {
            System.out.println(Retorno.error1().resultado);
            return Retorno.error1();
        }

        // Valido que n sea mayor o igual a 1
        if (n < 1) {
            System.out.println(Retorno.error2().resultado);
            return Retorno.error2();
        }

        String cadena = "";
        Pila<Entrada> entradas = new Pila();
        int cont = 1;

        // Aca muestro hasta n
        if (evento.getCantEntradasVendidas() > n) {
            while (evento.getEntradas().cantidadElementos() > 0) {
                Entrada e = evento.getEntradas().top();
                if (cont <= n - 1) {
                    cadena += e.getCliente().toString() + "#";
                    cont++;
                } else if (cont == n) {
                    cadena += e.getCliente().toString();
                    cont++;
                }
                entradas.push(e);
                evento.getEntradas().pop();
            }
            // Aca muestro todo
        } else {
            while (evento.getEntradas().cantidadElementos() > 0) {
                Entrada e = evento.getEntradas().top();

                if (evento.getEntradas().cantidadElementos() > 1) {
                    cadena += e.getCliente().toString() + "#";
                } else {
                    cadena += e.getCliente().toString();
                }

                entradas.push(e);
                evento.getEntradas().pop();
            }
        }

        while (entradas.cantidadElementos() > 0) {
            Entrada e = entradas.top();
            evento.getEntradas().push(e);
            entradas.pop();
        }

        r.valorString = cadena;
        return r;
    }

    @Override
    public Retorno listarEsperaEvento() {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        String cadena = "";

        for (int i = 0; i < listaEventos.cantidadElementos(); i++) {
            Evento evento = listaEventos.obtenerElemento(i);

            if (evento.getColaEspera().cantidadElementos() > 0) {
                Lista<Cliente> clientes = new Lista();

                // Aca consigo los clientes y los ordeno por cedula
                for (int j = 0; j < evento.getColaEspera().cantidadElementos(); j++) {
                    Cliente cliente = evento.getColaEspera().front();

                    clientes.agregarOrdenado(cliente);

                    evento.getColaEspera().dequeue();
                    evento.getColaEspera().enqueue(cliente);
                }

                // Aca armo la cadena ordenada por evento y despues por cedula del cliente
                for (int j = 0; j < clientes.cantidadElementos(); j++) {
                    Cliente c = clientes.obtenerElemento(j);

                    if (listaEventos.obtenerElemento(i + 1) != null) {
                        cadena += evento.getCodigo() + "-" + c.getCedula() + "#";
                    } else {
                        cadena += evento.getCodigo() + "-" + c.getCedula();
                    }
                }

            }
        }

        r.valorString = cadena;
        return r;
    }

    @Override
    public Retorno deshacerUtimasCompras(int n) {
        /* Necesito hacer una lista de entradas aparte porque si uso la listaEntradas
        cuando deshago la entrada, la quita de la lista y explota */
        Lista<Entrada> entradas = new Lista();
        for (int i = 0; i < listaEntradas.cantidadElementos(); i++) {
            entradas.agregarFinal(listaEntradas.obtenerElemento(i));
        }

        Retorno r = new Retorno(Retorno.Resultado.OK);
        String cadena = "";

        // a borrar
        Lista<Entrada> entradasD = new Lista();

        // Aca devuelvo las entradas
        for (int i = 0; i < n && i < entradas.cantidadElementos(); i++) {
            Entrada entrada = entradas.obtenerElemento(i);

            // a borrar
            entradasD.agregarFinal(entrada);

            /*
            if (i < n - 1) {
                cadena += entrada.getEvento().getCodigo() + "-" + entrada.getCliente().getCedula() + "#";
            } else {
                cadena += entrada.getEvento().getCodigo() + "-" + entrada.getCliente().getCedula();
            }
             */
            devolverEntrada(entrada.getCliente().getCedula(), entrada.getEvento().getCodigo());

        }

        // a borrar
        Lista<Cliente> clientes = new Lista();
        int mostrado = 1;
        
        for (int i = 0; i < entradasD.cantidadElementos(); i++) {
            Entrada entrada = entradasD.obtenerElemento(i);
            clientes.agregarOrdenado(entrada.getCliente());
            
            if (entradasD.obtenerElemento(i + 1) == null || !entradasD.obtenerElemento(i + 1).getEvento().equals(entrada.getEvento())) {
                
                for (int j = 0; j < clientes.cantidadElementos(); j++) {
                    // anda mal esto
                    if (mostrado < entradasD.cantidadElementos()) {
                        cadena += entrada.getEvento().getCodigo() + "-" + clientes.obtenerElemento(j).getCedula() + "#";
                    } else {
                        cadena += entrada.getEvento().getCodigo() + "-" + clientes.obtenerElemento(j).getCedula();
                    }
                    mostrado++;
                }
                
                clientes.vaciar();
            }
            
        }

        r.valorString = cadena;
        return r;
    }

    @Override
    public Retorno eventoMejorPuntuado() {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        Lista<Evento> mejoresEventos = new Lista();
        Evento mejorPuntuado = null;

        //ACA ARMO LA LISTA DE LOS MEJORES EVENTOS
        for (int i = 0; i < listaEventos.cantidadElementos(); i++) {
            Evento actual = listaEventos.obtenerElemento(i);
            if (i == 0) {
                mejorPuntuado = actual;
                mejoresEventos.agregarOrdenado(mejorPuntuado);
            } else {
                if (actual.getPuntajePromedio() == mejorPuntuado.getPuntajePromedio()) {
                    mejoresEventos.agregarOrdenado(actual);
                } else if (actual.getPuntajePromedio() > mejorPuntuado.getPuntajePromedio()) {

                    mejoresEventos.vaciar();
                    mejorPuntuado = actual;
                    mejoresEventos.agregarOrdenado(mejorPuntuado);
                }
            }
        }

        // ACA ARMO LA CADENA
        String cadena = "";
        for (int i = 0; i < mejoresEventos.cantidadElementos(); i++) {
            Evento e = mejoresEventos.obtenerElemento(i);
            if (i < mejoresEventos.cantidadElementos() - 1) {
                cadena += e.getCodigo() + "-" + e.getPuntajePromedio() + "#";
            } else {
                cadena += e.getCodigo() + "-" + e.getPuntajePromedio();
            }
        }

        r.valorString = cadena;
        return r;
    }

    @Override
    public Retorno comprasDeCliente(String cedula) {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        // Señuelo
        Cliente sC = new Cliente(cedula, "");

        // Busco al cliente
        Cliente cliente = listaClientes.obtenerElementoEntero(sC);

        if (cliente == null) {
            System.out.println(Retorno.error1().resultado);
            return Retorno.error1();
        }

        // ACA ARMO LA CADENA
        String cadena = "";
        for (int i = cliente.getListaEntradas().cantidadElementos() - 1; i >= 0; i--) {

            Entrada e = cliente.getListaEntradas().obtenerElemento(i);

            if (i > 0) {
                cadena += e.getEvento().getCodigo() + "-" + e.getDevuelta() + "#";
            } else {
                cadena += e.getEvento().getCodigo() + "-" + e.getDevuelta();
            }
        }

        r.valorString = cadena;
        return r;
    }

    @Override
    public Retorno comprasXDia(int mes) {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        String cadena = "";

        // Valido el mes
        if (mes < 1 || mes > 12) {
            System.out.println(Retorno.error1().resultado);
            return Retorno.error1();
        }

        // Armo la lista de entradas vendidas(no devueltas) ordenada segun el dia
        Lista<Entrada> lista = new Lista();
        for (int i = 0; i < listaEntradas.cantidadElementos(); i++) {
            Entrada e = listaEntradas.obtenerElemento(i);
            int mesEntrada = e.getEvento().getFecha().getMonthValue();

            // Aca reviso que sean del mes otorgado
            if (mesEntrada == mes) {
                lista.agregarOrdenado(e);
            }
        }

        // Aca reviso que si no hay ventas de ese mes no recorra
        if (lista.cantidadElementos() == 0) {
            return r;
        }

        boolean otroDia = false;
        boolean ultimo = false;
        int contDia = 0;
        int dia = 0;
        //Ahora tengo que arma la cadena
        for (int i = 0; i < lista.cantidadElementos() && ultimo == false; i++) {
            Entrada e = lista.obtenerElemento(i);
            Evento ev = e.getEvento();
            dia = ev.getFecha().getDayOfMonth();

            // Aca veo si es el ultimo elemento
            if (lista.obtenerElemento(i + 1) == null) {
                ultimo = true;
            } else {
                // Aca veo si la siguente entrada tiene distino dia
                if (lista.obtenerElemento(i + 1).getEvento().getFecha().getDayOfMonth() > dia) {
                    otroDia = true;
                }
            }

            //Cada recorrida suma un dia
            contDia++;

            if (otroDia == true || ultimo == true) {
                otroDia = false;
                if (ultimo == false) {
                    cadena += dia + "-" + contDia + "#";
                } else {
                    cadena += dia + "-" + contDia;
                }
                contDia = 0;
            }
        }

        r.valorString = cadena;
        return r;
    }

}
