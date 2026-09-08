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

    public Integer getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuit() {
        return cuit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void agregarCajero(Cajero cajero) {
        if (cajero == null) {
            return;
        }
        if (cajero.getBanco() != this) {
            return;
        }
        if (buscarCajero(cajero.getNumeroSerie()) != null) {
            return;
        }

        cajeros = Arrays.copyOf(cajeros, cajeros.length + 1);
        cajeros[cajeros.length - 1] = cajero;
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
        if (cuenta == null) {
            return;
        }
        if (buscarCuenta(cuenta.getNumeroCuenta()) != null) {
            return;
        }

        cuentas = Arrays.copyOf(cuentas, cuentas.length + 1);
        cuentas[cuentas.length - 1] = cuenta;
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
        return "Banco{" +
                "codigo=" + codigo +
                ", nombre=" + nombre +
                ", cuit=" + cuit +
                ", direccion=" + direccion +
                ", fechaAlta=" + fechaAlta +
                ", cajeros=" + Arrays.toString(cajeros) +
                ", cuentas=" + Arrays.toString(cuentas) +
                '}';
    }
}
