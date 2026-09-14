import java.util.Date;

public class SimuladorBancarioTest {
    public static void main(String[] args) {
        pruebaRegistroYBusqueda();
        pruebaOperacionesDeCuenta();
        pruebaExtraccionDesdeCajero();

        System.out.println("Todas las pruebas pasaron.");
    }

    private static void pruebaRegistroYBusqueda() {
        Banco banco = new Banco(1, "Banco de prueba", "30-00000000-0", "Buenos Aires", new Date());
        Cajero cajero = new Cajero(1001, "Sucursal centro", "Activo", 10000.0, 100, banco);
        CuentaBancaria cuenta = new CuentaBancaria(2001, 5000.0, "Cliente de prueba",
                "Caja de ahorro", "0000000000000000000000");

        banco.agregarCajero(cajero);
        banco.agregarCuenta(cuenta);

        comprobar(banco.buscarCajero(1001) == cajero, "El banco debe encontrar el cajero registrado");
        comprobar(banco.buscarCuenta(2001) == cuenta, "El banco debe encontrar la cuenta registrada");

        Cajero[] copia = banco.listarCajeros();
        copia[0] = null;
        comprobar(banco.buscarCajero(1001) == cajero, "La lista de cajeros debe devolverse como copia");
    }

    private static void pruebaOperacionesDeCuenta() {
        CuentaBancaria origen = new CuentaBancaria(1, 1000.0, "Origen", "Caja de ahorro", "1");
        CuentaBancaria destino = new CuentaBancaria(2, 500.0, "Destino", "Caja de ahorro", "2");

        origen.depositar(250.0);
        comprobarSaldo(1250.0, origen.consultarSaldo(), "El depósito debe aumentar el saldo");

        comprobar(origen.extraer(200.0), "La extracción con fondos suficientes debe aprobarse");
        comprobarSaldo(1050.0, origen.consultarSaldo(), "La extracción debe descontar el saldo");

        comprobar(!origen.extraer(2000.0), "La extracción sin fondos suficientes debe rechazarse");
        comprobarSaldo(1050.0, origen.consultarSaldo(), "Una extracción rechazada no debe modificar el saldo");

        comprobar(origen.transferir(destino, 300.0), "La transferencia válida debe aprobarse");
        comprobarSaldo(750.0, origen.consultarSaldo(), "La transferencia debe descontar la cuenta de origen");
        comprobarSaldo(800.0, destino.consultarSaldo(), "La transferencia debe acreditar la cuenta de destino");

        comprobar(!origen.transferir(origen, 100.0), "No se debe transferir a la misma cuenta");
        comprobar(!origen.transferir(null, 100.0), "No se debe transferir a una cuenta inexistente");
    }

    private static void pruebaExtraccionDesdeCajero() {
        Banco banco = new Banco(1, "Banco de prueba", "30-00000000-0", "Buenos Aires", new Date());
        Cajero cajero = new Cajero(1001, "Sucursal centro", "Activo", 2000.0, 100, banco);
        CuentaBancaria cuenta = new CuentaBancaria(2001, 5000.0, "Cliente de prueba",
                "Caja de ahorro", "0000000000000000000000");

        comprobar(cajero.extraer(cuenta, 1000.0), "El cajero debe aprobar una extracción con fondos");
        comprobarSaldo(4000.0, cuenta.consultarSaldo(), "El cajero debe descontar el saldo de la cuenta");
        comprobarSaldo(1000.0, cajero.getSaldoDisponible(), "El cajero debe descontar su efectivo");

        comprobar(!cajero.extraer(cuenta, 1500.0), "El cajero debe rechazar una extracción sin efectivo");
        comprobarSaldo(4000.0, cuenta.consultarSaldo(), "El rechazo no debe modificar la cuenta");
        comprobarSaldo(1000.0, cajero.getSaldoDisponible(), "El rechazo no debe modificar el cajero");
    }

    private static void comprobar(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError(mensaje);
        }
    }

    private static void comprobarSaldo(double esperado, double actual, String mensaje) {
        if (Math.abs(esperado - actual) > 0.001) {
            throw new AssertionError(mensaje + ": esperado=" + esperado + ", actual=" + actual);
        }
    }
}
