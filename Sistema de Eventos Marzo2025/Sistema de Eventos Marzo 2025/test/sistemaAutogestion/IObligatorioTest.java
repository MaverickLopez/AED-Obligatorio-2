/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sistemaAutogestion;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pesce
 */
public class IObligatorioTest {

    private Sistema miSistema;

    public IObligatorioTest() {
    }

    @Before
    public void setUp() {
        miSistema = new Sistema();
    }

    @Test
    public void testCrearSistemaDeGestion() {
        Retorno r = miSistema.crearSistemaDeGestion();
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testRegistrarSalaOk() {
        Retorno r = miSistema.registrarSala("Alfonso", 50);
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testRegistrarSalaError1() {
        miSistema.registrarSala("Alfonso", 50);
        miSistema.registrarSala("Zorrilla", 700);
        miSistema.registrarSala("Mario", 580);
        miSistema.registrarSala("Padre", 980);

        Retorno r = miSistema.registrarSala("Zorrilla", 80);
        assertEquals(Retorno.error1().resultado, r.resultado);

        Retorno r2 = miSistema.registrarSala("Padre", 50);
        assertEquals(Retorno.error1().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarSalaError2() {
        Retorno r = miSistema.registrarSala("Padre", 0);
        assertEquals(Retorno.error2().resultado, r.resultado);

        Retorno r2 = miSistema.registrarSala("Carlos", -10);
        assertEquals(Retorno.error2().resultado, r2.resultado);
    }

    @Test
    public void testEliminarSalaOk() {
        miSistema.registrarSala("Mariana", 90);
        miSistema.registrarSala("Pepito", 550);
        miSistema.registrarSala("Alfonso", 50);
        miSistema.registrarSala("Zorrilla", 700);
        miSistema.registrarSala("Mario", 580);
        miSistema.registrarSala("Padre", 980);

        Retorno r = miSistema.eliminarSala("Alfonso");
        assertEquals(Retorno.ok().resultado, r.resultado);

        Retorno r2 = miSistema.eliminarSala("Mario");
        assertEquals(Retorno.ok().resultado, r2.resultado);
    }

    @Test
    public void testEliminarSalaError1() {
        miSistema.registrarSala("Mariana", 90);
        miSistema.registrarSala("Pepito", 550);
        miSistema.registrarSala("Alfonso", 50);
        miSistema.registrarSala("Zorrilla", 700);
        miSistema.registrarSala("Mario", 580);
        miSistema.registrarSala("Padre", 980);

        Retorno r = miSistema.eliminarSala("Matias");
        assertEquals(Retorno.error1().resultado, r.resultado);

        Retorno r2 = miSistema.eliminarSala("Pedro");
        assertEquals(Retorno.error1().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarEventoOk() {
        miSistema.registrarSala("Mariana", 90);
        miSistema.registrarSala("Pepito", 550);

        Retorno r = miSistema.registrarEvento("12400", "Star Wars", 150, LocalDate.now());
        assertEquals(Retorno.ok().resultado, r.resultado);

        Retorno r2 = miSistema.registrarEvento("12401", "Star Wars 2", 500, LocalDate.now().minusDays(2));
        assertEquals(Retorno.ok().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarEventoError1() {
        miSistema.registrarSala("Mariana", 90);
        miSistema.registrarSala("Pepito", 550);
        miSistema.registrarEvento("12400", "Star Wars", 150, LocalDate.now());
        miSistema.registrarEvento("12401", "Como entrenar a tu dragon", 50, LocalDate.now().minusDays(7));

        Retorno r = miSistema.registrarEvento("12400", "Star Wars 2", 500, LocalDate.now().minusDays(2));
        assertEquals(Retorno.error1().resultado, r.resultado);

        Retorno r2 = miSistema.registrarEvento("12401", "Star Wars 3", 300, LocalDate.now().minusDays(4));
        assertEquals(Retorno.error1().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarEventoError2() {
        miSistema.registrarSala("Mariana", 90);
        miSistema.registrarSala("Pepito", 550);
        miSistema.registrarEvento("12400", "Star Wars", 150, LocalDate.now());
        miSistema.registrarEvento("12401", "Como entrenar a tu dragon", 50, LocalDate.now().minusDays(7));

        Retorno r = miSistema.registrarEvento("12402", "Star Wars 2", 0, LocalDate.now().minusDays(2));
        assertEquals(Retorno.error2().resultado, r.resultado);

        Retorno r2 = miSistema.registrarEvento("12403", "Star Wars 3", 0, LocalDate.now().minusDays(4));
        assertEquals(Retorno.error2().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarEventoError3() {
        miSistema.registrarSala("Mariana", 90);
        miSistema.registrarSala("Pepito", 550);
        miSistema.registrarEvento("12400", "Star Wars", 150, LocalDate.now());
        miSistema.registrarEvento("12403", "Star Wars 4", 80, LocalDate.now());

        Retorno r = miSistema.registrarEvento("12401", "Star Wars 2", 400, LocalDate.now());
        assertEquals(Retorno.error3().resultado, r.resultado);

        Retorno r2 = miSistema.registrarEvento("12402", "Star Wars 3", 50, LocalDate.now());
        assertEquals(Retorno.error3().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarClienteOk() {
        Retorno r = miSistema.registrarCliente("54849161", "Maverick Lopez");
        assertEquals(Retorno.ok().resultado, r.resultado);

        Retorno r2 = miSistema.registrarCliente("33105350", "Lourdes Patron");
        assertEquals(Retorno.ok().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarClienteError1() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        Retorno r = miSistema.registrarCliente("3548", "Carlos Marimar");
        assertEquals(Retorno.error1().resultado, r.resultado);

        Retorno r2 = miSistema.registrarCliente("9845615", "Pedro Place");
        assertEquals(Retorno.error1().resultado, r2.resultado);
    }

    @Test
    public void testRegistrarClienteError2() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        Retorno r = miSistema.registrarCliente("54849161", "Carlos Marimar");
        assertEquals(Retorno.error2().resultado, r.resultado);

        Retorno r2 = miSistema.registrarCliente("33105350", "Pedro Place");
        assertEquals(Retorno.error2().resultado, r2.resultado);
    }

    @Test
    public void testComprarEntradaOk() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        miSistema.registrarSala("Sala A", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 1, LocalDate.of(2025, 5, 10));

        Retorno r = miSistema.comprarEntrada("54849161", "EVT01");
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testComprarEntradaError1() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        miSistema.registrarSala("Sala A", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 80, LocalDate.of(2025, 5, 10));

        Retorno r = miSistema.comprarEntrada("53849161", "EVT01");
        assertEquals(Retorno.error1().resultado, r.resultado);

    }

    @Test
    public void testComprarEntradaError2() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        miSistema.registrarSala("Sala A", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 1, LocalDate.of(2025, 5, 10));

        Retorno r = miSistema.comprarEntrada("54849161", "EVT02");
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    @Test
    public void testEliminarEventoOk() {
        miSistema.registrarSala("Sala A", 100);
        miSistema.registrarSala("Sala B", 100);
        miSistema.registrarSala("Sala C", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 1, LocalDate.of(2025, 5, 10));
        miSistema.registrarEvento("EVT02", "Concierto", 1, LocalDate.of(2025, 5, 10));
        miSistema.registrarEvento("EVT03", "Concierto", 1, LocalDate.of(2025, 5, 10));
        Retorno r = miSistema.eliminarEvento("EVT02");
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testEliminarEventoError1() {
        miSistema.registrarSala("Sala A", 100);
        miSistema.registrarSala("Sala B", 100);
        miSistema.registrarSala("Sala C", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 1, LocalDate.of(2025, 5, 10));
        Retorno r = miSistema.eliminarEvento("EVT02");
        assertEquals(Retorno.error1().resultado, r.resultado);
    }

    @Test
    public void testEliminarEventoError2() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");

        miSistema.registrarSala("Sala A", 100);
        miSistema.registrarSala("Sala B", 100);
        miSistema.registrarSala("Sala C", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 10, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");

        Retorno r = miSistema.eliminarEvento("EVT01");
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    @Test
    public void testDevolverEntradaOk() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");
        miSistema.registrarCliente("23105350", "Lourdes");
        miSistema.registrarCliente("13105350", "Lourdes Lopez");

        miSistema.registrarSala("Sala A", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 4, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("33105350", "EVT01");
        miSistema.comprarEntrada("23105350", "EVT01");
        miSistema.comprarEntrada("54849161", "EVT01");
        miSistema.comprarEntrada("13105350", "EVT01");
        miSistema.comprarEntrada("54849161", "EVT01");

        Retorno r = miSistema.devolverEntrada("54849161", "EVT01");
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testDevolverEntradaError1() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        miSistema.registrarSala("Sala A", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 1, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");

        Retorno r = miSistema.devolverEntrada("34642161", "EVT01");
        assertEquals(Retorno.error1().resultado, r.resultado);
    }

    @Test
    public void testDevolverEntradaError2() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        miSistema.registrarSala("Sala A", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 1, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");

        Retorno r = miSistema.devolverEntrada("54849161", "EVT02");
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    @Test
    public void testCalificarEventoOk() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");

        miSistema.registrarSala("Sala A", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 10, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");

        Retorno r = miSistema.calificarEvento("54849161", "EVT01", 9, "Muy buen concierto");
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testCalificarEventoError1() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");

        miSistema.registrarSala("Sala A", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 10, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");

        Retorno r = miSistema.calificarEvento("13849161", "EVT01", 9, "Muy buen concierto");
        assertEquals(Retorno.error1().resultado, r.resultado);
    }

    @Test
    public void testCalificarEventoError2() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");

        miSistema.registrarSala("Sala A", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 10, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");

        Retorno r = miSistema.calificarEvento("54849161", "EVT02", 9, "Muy buen concierto");
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    @Test
    public void testCalificarEventoError3() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");

        miSistema.registrarSala("Sala A", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 10, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");

        Retorno r = miSistema.calificarEvento("54849161", "EVT01", 12, "Muy buen concierto");
        assertEquals(Retorno.error3().resultado, r.resultado);

        Retorno r2 = miSistema.calificarEvento("54849161", "EVT01", -1, "Muy buen concierto");
        assertEquals(Retorno.error3().resultado, r2.resultado);
    }

    @Test
    public void testCalificarEventoError4() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");

        miSistema.registrarSala("Sala A", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 10, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");

        miSistema.calificarEvento("54849161", "EVT01", 4, "Horrible");
        Retorno r = miSistema.calificarEvento("54849161", "EVT01", 9, "Muy buen concierto");
        assertEquals(Retorno.error4().resultado, r.resultado);
    }

    @Test
    public void testListarSalasOk() {
        miSistema.registrarSala("Sala Zorrilla", 700);
        miSistema.registrarSala("Sala Mario", 580);
        miSistema.registrarSala("Sala Padre", 980);

        Retorno r = miSistema.listarSalas();
        System.out.println(r.valorString);
        assertEquals("Sala Padre-980#Sala Mario-580#Sala Zorrilla-700",
                r.valorString);
    }

    @Test
    public void testListarEventosOk() {
        miSistema.registrarSala("Mariana", 90);
        miSistema.registrarSala("Pepito", 550);
        miSistema.registrarSala("Alfonso", 50);
        miSistema.registrarSala("Zorrilla", 700);
        miSistema.registrarSala("Mario", 580);
        miSistema.registrarSala("Padre", 980);

        miSistema.registrarEvento("gh124", "Star Wars", 150, LocalDate.now());
        miSistema.registrarEvento("a48b5", "Star Wars 2", 500, LocalDate.now());
        miSistema.registrarEvento("o48b5", "Star Wars 3", 80, LocalDate.now());

        Retorno r = miSistema.listarEventos();
        System.out.println(r.valorString);
        assertEquals("a48b5-Star Wars 2-Mario-500-0#gh124-Star Wars-Padre-150-0#o48b5-Star Wars 3-Zorrilla-80-0",
                r.valorString);
    }

    @Test
    public void testListarClientesOk() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("15489162", "Calos Martinez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        Retorno r = miSistema.listarClientes();
        System.out.println(r.valorString);
        assertEquals("15489162-Calos Martinez#33105350-Lourdes Patron#54849161-Maverick Lopez",
                r.valorString);
    }

    @Test
    public void testEsSalaOptimaOk() {
        String[][] sala = {
            {"#", "#", "#", "#", "#"},
            {"#", "O", "O", "X", "#"},
            {"#", "O", "X", "X", "#"},
            {"#", "O", "O", "X", "#"},
            {"#", "#", "#", "#", "#"}
        };

        String[][] sala2 = {
            {"#", "#", "#", "#", "#", "#"},
            {"#", "O", "O", "X", "X", "#"},
            {"#", "O", "O", "X", "O", "#"},
            {"#", "O", "O", "X", "O", "#"},
            {"#", "#", "#", "#", "#", "#"}
        };

        Retorno r = miSistema.esSalaOptima(sala);
        assertEquals(Retorno.ok("Es Optimo").resultado, r.resultado);

        Retorno r2 = miSistema.esSalaOptima(sala2);
        assertEquals(Retorno.ok("Es Optimo").resultado, r2.resultado);
    }

    // Ninguna columna cumple
    @Test
    public void testEsSalaOptimaError1() {
        String[][] sala = {
            {"#", "#", "#", "#", "#"},
            {"#", "O", "X", "X", "#"},
            {"#", "X", "O", "X", "#"},
            {"#", "X", "X", "O", "#"},
            {"#", "#", "#", "#", "#"}
        };

        String[][] sala2 = {
            {"#", "#", "#", "#", "#"},
            {"#", "X", "X", "X", "#"},
            {"#", "X", "O", "X", "#"},
            {"#", "X", "X", "X", "#"},
            {"#", "X", "O", "X", "#"}
        };

        Retorno r = miSistema.esSalaOptima(sala);
        assertEquals(Retorno.ok("No es Optimo").resultado, r.resultado);

        Retorno r2 = miSistema.esSalaOptima(sala2);
        assertEquals(Retorno.ok("No es Optimo").resultado, r2.resultado);
    }

    // Solo una columna cumple
    @Test
    public void testEsSalaOptimaError2() {
        String[][] sala = {
            {"#", "#", "#", "#"},
            {"#", "O", "X", "#"},
            {"#", "O", "O", "#"},
            {"#", "X", "X", "#"}
        };

        String[][] sala2 = {
            {"#", "#", "#", "#", "#", "#"},
            {"#", "X", "O", "X", "O", "#"},
            {"#", "O", "X", "X", "X", "#"},
            {"#", "O", "X", "O", "X", "#"},
            {"#", "O", "X", "O", "X", "#"},
            {"#", "#", "#", "#", "#", "#"}
        };

        Retorno r = miSistema.esSalaOptima(sala);
        assertEquals(Retorno.ok("No es Optimo").resultado, r.resultado);

        Retorno r2 = miSistema.esSalaOptima(sala2);
        assertEquals(Retorno.ok("No es Optimo").resultado, r2.resultado);
    }

    // Sin columnas validas
    @Test
    public void testEsSalaOptimaError3() {
        String[][] sala = {
            {"#", "#", "#"},
            {"#", "#", "#"},
            {"#", "#", "#"}
        };

        Retorno r = miSistema.esSalaOptima(sala);
        assertEquals(Retorno.ok("No es Optimo").resultado, r.resultado);
    }

    @Test
    public void testListarClientesDeEventoOk() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        miSistema.registrarSala("Sala A", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 2, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");
        miSistema.comprarEntrada("33105350", "EVT01");

        Retorno r = miSistema.listarClientesDeEvento("EVT01", 1);
        System.out.println(r.valorString);
        assertEquals("33105350-Lourdes Patron", r.valorString);
    }

    @Test
    public void testListarClientesDeEventoError1() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        miSistema.registrarSala("Sala A", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 2, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");
        miSistema.comprarEntrada("33105350", "EVT01");

        Retorno r = miSistema.listarClientesDeEvento("EVT02", 1);
        System.out.println(r.valorString);
        assertEquals(Retorno.error1().resultado, r.resultado);
    }

    @Test
    public void testListarClientesDeEventoError2() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        miSistema.registrarSala("Sala A", 100);

        miSistema.registrarEvento("EVT01", "Concierto", 2, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");
        miSistema.comprarEntrada("33105350", "EVT01");

        Retorno r = miSistema.listarClientesDeEvento("EVT01", -1);
        System.out.println(r.valorString);
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    @Test
    public void testListarEsperaEventoOk() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        miSistema.registrarSala("Sala A", 100);
        miSistema.registrarSala("Sala B", 500);

        miSistema.registrarEvento("EVT01", "Concierto", 1, LocalDate.of(2025, 5, 10));
        miSistema.registrarEvento("AVT02", "Musical", 1, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("33105350", "EVT01");
        miSistema.comprarEntrada("54849161", "AVT02");

        miSistema.comprarEntrada("33105350", "EVT01");
        miSistema.comprarEntrada("54849161", "EVT01");
        miSistema.comprarEntrada("54849161", "AVT02");
        miSistema.comprarEntrada("33105350", "AVT02");

        Retorno r = miSistema.listarEsperaEvento();
        System.out.println(r.valorString);
        assertEquals("AVT02-33105350#AVT02-54849161#EVT01-33105350EVT01-54849161", r.valorString);
    }

    @Test
    public void testDeshacerUtimasComprasOk() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        miSistema.registrarSala("Sala A", 100);
        miSistema.registrarSala("Sala B", 500);

        miSistema.registrarEvento("EVT01", "Concierto", 10, LocalDate.of(2025, 5, 10));
        miSistema.registrarEvento("AVT02", "Musical", 50, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("33105350", "EVT01");
        
        
        miSistema.comprarEntrada("54849161", "EVT01");
        miSistema.comprarEntrada("33105350", "AVT02");
        miSistema.comprarEntrada("54849161", "AVT02");
         
        
        Retorno r = miSistema.deshacerUtimasCompras(5);
        System.out.println(r.valorString);
        assertEquals("AVT02-33105350#AVT02-54849161#EVT01-33105350#EVT01-54849161", r.valorString);
    }

    @Test
    public void testEventoMejorPuntuadoOk() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");

        miSistema.registrarSala("Sala A", 100);
        miSistema.registrarSala("Sala B", 500);

        miSistema.registrarEvento("EVT01", "Concierto", 10, LocalDate.of(2025, 5, 10));
        miSistema.registrarEvento("AVT02", "Musical", 50, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");
        miSistema.comprarEntrada("54849161", "AVT02");

        miSistema.calificarEvento("54849161", "EVT01", 9, "Muy buen concierto");
        miSistema.calificarEvento("54849161", "AVT02", 9, "Muy buen concierto");

        Retorno r = miSistema.eventoMejorPuntuado();
        System.out.println(r.valorString);
        assertEquals("AVT02-9.0#EVT01-9.0", r.valorString);
    }

    @Test
    public void testComprasDeClienteOk() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");

        miSistema.registrarSala("Sala A", 100);
        miSistema.registrarSala("Sala B", 500);

        miSistema.registrarEvento("EVT01", "Concierto", 10, LocalDate.of(2025, 5, 10));
        miSistema.registrarEvento("AVT02", "Musical", 50, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");
        miSistema.comprarEntrada("54849161", "AVT02");

        miSistema.devolverEntrada("54849161", "AVT02");

        Retorno r = miSistema.comprasDeCliente("54849161");
        System.out.println(r.valorString);
        assertEquals("EVT01-N#AVT02-D", r.valorString);
    }

    @Test
    public void testComprasDeClienteError1() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");

        miSistema.registrarSala("Sala A", 100);
        miSistema.registrarSala("Sala B", 500);

        miSistema.registrarEvento("EVT01", "Concierto", 10, LocalDate.of(2025, 5, 10));
        miSistema.registrarEvento("AVT02", "Musical", 50, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");
        miSistema.comprarEntrada("54849161", "AVT02");

        miSistema.devolverEntrada("54849161", "AVT02");

        Retorno r = miSistema.comprasDeCliente("36844561");
        System.out.println(r.valorString);
        assertEquals(Retorno.error1().resultado, r.resultado);
    }

    @Test
    public void testComprasXDiaOk() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");
        miSistema.registrarCliente("33105350", "Lourdes Patron");

        miSistema.registrarSala("Sala A", 100);
        miSistema.registrarSala("Sala B", 500);

        miSistema.registrarEvento("EVT01", "Concierto", 10, LocalDate.of(2025, 5, 12));
        miSistema.registrarEvento("EVT02", "Musical", 50, LocalDate.of(2025, 5, 16));
        miSistema.registrarEvento("EVT03", "Festival", 50, LocalDate.of(2025, 5, 20));
        miSistema.registrarEvento("EVT04", "Partido Basket", 50, LocalDate.of(2025, 5, 31));
        miSistema.registrarEvento("EVT05", "Seminario", 50, LocalDate.of(2025, 5, 5));
        miSistema.registrarEvento("EVT06", "Partido Futbol", 50, LocalDate.of(2025, 5, 2));

        miSistema.comprarEntrada("54849161", "EVT01");
        miSistema.comprarEntrada("54849161", "EVT02");
        miSistema.comprarEntrada("54849161", "EVT03");
        miSistema.comprarEntrada("54849161", "EVT04");
        miSistema.comprarEntrada("54849161", "EVT05");
        miSistema.comprarEntrada("54849161", "EVT06");
        miSistema.comprarEntrada("33105350", "EVT03");
        miSistema.comprarEntrada("33105350", "EVT02");
        miSistema.comprarEntrada("33105350", "EVT05");

        Retorno r = miSistema.comprasXDia(5);
        System.out.println(r.valorString);
        assertEquals("2-1#5-2#12-1#16-2#20-2#31-1", r.valorString);
    }

    @Test
    public void testComprasXDiaError1() {
        miSistema.registrarCliente("54849161", "Maverick Lopez");

        miSistema.registrarSala("Sala A", 100);
        miSistema.registrarSala("Sala B", 500);

        miSistema.registrarEvento("EVT01", "Concierto", 10, LocalDate.of(2025, 5, 10));
        miSistema.registrarEvento("AVT02", "Musical", 50, LocalDate.of(2025, 5, 10));

        miSistema.comprarEntrada("54849161", "EVT01");
        miSistema.comprarEntrada("54849161", "AVT02");

        Retorno r = miSistema.comprasXDia(-90);
        System.out.println(r.valorString);
        assertEquals(Retorno.error1().resultado, r.resultado);
    }
}
