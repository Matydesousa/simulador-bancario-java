import java.util.Date;

public class Main {
    public static void main(String[] args) {
        //crea instancia 
        Banco banco = new Banco(1, "Banco Ejemplo", "30-12345678-9", "Buenos Aires", new Date());
        // crea cajeros 
        Cajero cajero1 = new Cajero(1001, "San Miguel", "Activo", 500000.0, 1000, banco);
        Cajero cajero2 = new Cajero(1002, "Bella Vista", "Activo", 10000.0, 800, banco);
        // agrega cajeros creados a la lista del banco
        banco.agregarCajero(cajero1);
        banco.agregarCajero(cajero2);
        //crea cuenta bancaria de prueba y se registra en banco 
        CuentaBancaria cuenta001 = new CuentaBancaria(45210, 120000.0, "Maria Silvina",
                "Caja de ahorro", "0170099220000012345678");
        banco.agregarCuenta(cuenta001);
        //printea la informacion de cada objeto usando tosString 
        System.out.println(banco);
        System.out.println(cajero1);
        System.out.println(cajero2);
        System.out.println(cuenta001);
        // modifica la ubicacion de cajero 2 
        cajero2.setUbicacion("Sucursal Bella Vista");
        System.out.println("Ubicacion: " + cajero2.getUbicacion());
        System.out.println("Titular: " + cuenta001.getTitular());
        //Hace operacion de extraccion coordinada entre el cajero1 y la cuenta 1
        Boolean extraccionRealizada = cajero1.extraer(cuenta001, 20000.0);
        System.out.println("Extraccion realizada: " + extraccionRealizada);
        System.out.println("Saldo de la cuenta: " + cuenta001.consultarSaldo());
        System.out.println("Efectivo del cajero: " + cajero1.getSaldoDisponible());
        // demuestra el comportamiento de las referencias en java apuntando al mismo objeto
        CuentaBancaria otraReferencia = cuenta001;
        otraReferencia.depositar(5000.0);
        System.out.println("Saldo despues del deposito: " + cuenta001.consultarSaldo());
        System.out.println("Consulta desde otro cajero: " + cajero2.consultarSaldo(cuenta001));
        //recarga efectivo en el cajero 2 y muestra el cambio 
        cajero2.recargarEfectivo(30000.0);
        System.out.println(cajero2);
        //hace una busqueda de cajero dentro del baco por nro de serie 
        Cajero encontrado = banco.buscarCajero(1002);
        System.out.println("Cajero encontrado: " + encontrado);
        //recorre la lista de cajeros del banco 
        Cajero[] cajeros = banco.listarCajeros();
        for (Cajero cajero : cajeros) {
            System.out.println(cajero);
        }
    }
}
