import java.util.Arrays;
import java.util.Date;
// Demostracion de la entidad bancaria asi como sus listas de cajeros y cuentas bancarias
public class Banco {
    //Atributos
    private Integer codigo;
    private String nombre;
    private String cuit;
    private String direccion;
    private Date fechaAlta;
    private Cajero[] cajeros;                //Almacenar los cajeros asociados
    private CuentaBancaria[] cuentas;        //Almacenar las cuentas bancarias asociadas
    // Inicializa el banco con sus datos principales y deja los arreglos de cajeros y cuentas vacios
    public Banco(Integer codigo, String nombre, String cuit, String direccion, Date fechaAlta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cuit = cuit;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.cajeros = new Cajero[0];
        this.cuentas = new CuentaBancaria[0];
    }
    // METODOS
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
    // Gestion de cajeros
    //Agrega cajero

    public void agregarCajero(Cajero cajero) {
        //Crea un nuevo arreglo que copia todos los elementos del arreglo anterior pero le suma una posición adicional a su tamaño
        cajeros = Arrays.copyOf(cajeros, cajeros.length + 1);
        //Asigna el nuevo cajero en la ultima posicion disponible de ese nuevo arreglo recien ampliado
        cajeros[cajeros.length - 1] = cajero;
    }
    //da copia del arreglo con los cajeros asociados al banco

    public Cajero[] listarCajeros() {
        return Arrays.copyOf(cajeros, cajeros.length);
    }
    // Busca y devuelve un cajero a partir de su numero de serie y da null si no se encuentra
    public Cajero buscarCajero(Integer numeroSerie) {
        for (Cajero cajero : cajeros) {
            if (cajero.getNumeroSerie().equals(numeroSerie)) {
                return cajero;
            }
        }
        return null;
    }
    // Cuentas bancarias
    //se agrega cuenta bancaria

    public void agregarCuenta(CuentaBancaria cuenta) {
        //Reorganiza el arreglo y agrega la nueva cuenta

        cuentas = Arrays.copyOf(cuentas, cuentas.length + 1);
        cuentas[cuentas.length - 1] = cuenta;
    }
    //busca cuenta bancaria y da null si no la encuentra
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
