import java.util.Arrays;
import java.util.Date;

public class Banco {
    private Integer codigo;
    private String nombre;
    private String cuit;
    private String direccion;
    private Date fechaAlta;
    private Cajero[] cajeros;
    private CuentaBancaria[] cuentas;

    public Banco(Integer codigo, String nombre, String cuit, String direccion, Date fechaAlta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cuit = cuit;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.cajeros = new Cajero[0];
        this.cuentas = new CuentaBancaria[0];
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarCajero(Cajero cajero) {
        if (cajero != null && cajero.getBanco() == this
                && buscarCajero(cajero.getNumeroSerie()) == null) {
            // El arreglo nuevo tiene una posicion mas; se copian las referencias.
            cajeros = Arrays.copyOf(cajeros, cajeros.length + 1);
            cajeros[cajeros.length - 1] = cajero;
        }
    }

    public Cajero[] listarCajeros() {
        return Arrays.copyOf(cajeros, cajeros.length);
    }

    public Cajero buscarCajero(Integer numeroSerie) {
        for (Cajero cajero : cajeros) {
            if (cajero.getNumeroSerie().equals(numeroSerie)) {
                return cajero;
            }
        }
        return null;
    }

    public void agregarCuenta(CuentaBancaria cuenta) {
        if (cuenta != null && buscarCuenta(cuenta.getNumeroCuenta()) == null) {
            cuentas = Arrays.copyOf(cuentas, cuentas.length + 1);
            cuentas[cuentas.length - 1] = cuenta;
        }
    }

    public CuentaBancaria buscarCuenta(Integer numeroCuenta) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Banco{" + "codigo=" + codigo + ", nombre='" + nombre + '\''
                + ", cuit='" + cuit + '\'' + ", direccion='" + direccion + '\''
                + ", fechaAlta=" + fechaAlta + ", cajeros=" + Arrays.toString(cajeros)
                + ", cuentas=" + Arrays.toString(cuentas) + '}';
    }
}
