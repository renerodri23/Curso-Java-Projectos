package org.rplacios.junitapp.ejemplo.models;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.rplacios.junitapp.ejemplo.exception.DineroInsuficienteException;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {
    Cuenta c;
    @BeforeEach
    void initMetodoTes(){
        this.c = new Cuenta("Rael", new BigDecimal("10000.12345"));

        System.out.println("Iniciando el metodo.");
    }

    @AfterEach
    void tearDown(){
        System.out.println("Finalizando metodos");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicializando el test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finalizando el test");
    }

    @Test
    @DisplayName("Proando el nombre de la Cuenta Corriente")
    void testNombreCuenta() {
         c = new Cuenta("Rael", new BigDecimal("10000.12345"));

        //        c.setPersona("Rael");
        String esperado = "Rael";
        String real = c.getPersona();
        assertNotNull(real,() ->"La cuenta no puede ser nula");
        assertEquals(esperado, real,() ->"el nombre de la cuenta no es el que se esperaba"+esperado +" sin embargo fue "+ real);
        assertTrue(real.equals("Rael"),() ->"nombre cuet esperada debe ser igual a la real ");


    }

    @Test
    @DisplayName("Probando Saldo de cuenta corriente")
    void testSaldoCuenta() {
        Cuenta cuenta = new Cuenta("Rael", new BigDecimal("1000.12345"));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    @DisplayName("Test de referencias de cuenta y equals")
    void testReferenciaCuenta() {
        Cuenta cuenta = new Cuenta("John Doe", new BigDecimal(8900.999));
        Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal(8900.999));

//        assertNotEquals(cuenta2,cuenta);
        assertEquals(cuenta2, cuenta);

    }

    @Test
    void testDebitoCuenta() {
        Cuenta cuenta = new Cuenta("Rael", new BigDecimal(1000));
        cuenta.debito(new BigDecimal(100));

        assertEquals(900, cuenta.getSaldo().intValue());

    }

    @Test
    void testCreditoCuenta() {
        Cuenta cuenta = new Cuenta("Rael", new BigDecimal(1000));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
    }

    @Test
    void testDineroInsuficienteExceptionCuenta() {
        Cuenta cuenta = new Cuenta("Rael", new BigDecimal(1000.12345));
        Exception e = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.debito(new BigDecimal(1500));
        });
        String actual = e.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(esperado, actual);
    }

    @Test
    void testRelacionBancoCuenta() {
        Cuenta cuenta = new Cuenta("John Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Rael", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.addCuentas(cuenta);
        banco.addCuentas(cuenta2);
        banco.setName("Banco del Estado");
        banco.transferir(cuenta2, cuenta, new BigDecimal(500));
        assertAll(() -> {
                    assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
                },
                () -> {
                    assertEquals("3000", cuenta.getSaldo().toPlainString());
                },
                () -> {
                    assertEquals(2, banco.getCuentas().size());
                },
                () -> {
                    assertEquals("Banco del Estado", cuenta.getBanco().getName());
                },
                () -> {
                    assertEquals("Banco del Estado", cuenta.getBanco().getName());
                },
                () -> {
                    assertEquals("Rael", banco.getCuentas().stream().filter(c -> c.getPersona().equals("Rael")).findFirst()
                            .get().getPersona());
                },
                () -> {
                    assertTrue(banco.getCuentas().stream().anyMatch(c -> c.getPersona().equals("Rael")));
                });


    }
    @Test
    void imprimirVariable(){
        Map<String, String> ge = System.getenv();
        ge.forEach((k,v)-> System.out.println(k + " = "+ v));
    }


}