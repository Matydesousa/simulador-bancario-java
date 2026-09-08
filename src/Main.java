import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco(1, "Banco Ejemplo", "30-12345678-9", "Buenos Aires", new Date());
        Cajero cajero1 = new Cajero(1001, "San Miguel", "Activo", 500000.0, 1000, banco);
        Cajero cajero2 = new Cajero(1002, "Bella Vista", "Activo", 10000.0, 800, banco);
        banco.agregarCajero(cajero1);
        banco.agregarCajero(cajero2);

        CuentaBancaria cuenta001 = new CuentaBancaria(45210, 120000.0, "Maria Silvina",
                "Caja de ahorro", "0170099220000012345678");
        banco.agregarCuenta(cuenta001);

        System.out.println(banco);
        System.out.println("Saldo inicial: " + cajero1.consultarSaldo(cuenta001));
        System.out.println("Extraccion de 20000: " + cajero1.extraer(cuenta001, 20000.0));
        System.out.println("Saldo esperado 100000: " + cuenta001.consultarSaldo());

        CuentaBancaria otraReferencia = cuenta001;
        otraReferencia.depositar(5000.0);
        System.out.println("Saldo desde la referencia original (105000): " + cuenta001.consultarSaldo());
        System.out.println("Monto negativo rechazado: " + !cuenta001.extraer(-100.0));
        System.out.println("Saldo insuficiente rechazado: " + !cajero1.extraer(cuenta001, 200000.0));
        System.out.println("Efectivo insuficiente rechazado: " + !cajero2.extraer(cuenta001, 20000.0));
        System.out.println("Saldo sin cambios (105000): " + cuenta001.consultarSaldo());

        cajero2.recargarEfectivo(30000.0);
        System.out.println("Cajero recargado: " + cajero2);
        for (Cajero cajero : banco.listarCajeros()) {
            System.out.println(cajero);
        }
        System.out.println("Busqueda 1002: " + banco.buscarCajero(1002));
        System.out.println("Busqueda inexistente: " + banco.buscarCajero(9999));
    }
}
