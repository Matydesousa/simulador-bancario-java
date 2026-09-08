import java.util.Arrays;
import java.util.Date;
// Demostracion de la entidad bancaria asi coo listas de cajeros y cuenta bancaria 
public class Banco {
    //Atributos
    private Integer codigo;
    private String nombre;
    private String cuit;
    private String direccion;
    private Date fechaAlta;
    private Cajero[] cajeros;                //Almacenar los  cajero asociados
    private CuentaBancaria[] cuentas;        //Almacenar los  cuenta bancaria asociada 
    // inicializa en la clase banco con sus datos principales y deja los arreglos de cajero y cuenta vacios
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
    //Agrega cajero si esta cumple con las condiciones establecidas 

    public void agregarCajero(Cajero cajero) {
        if (cajero == null) {
            return;
        }
        //validar que el cajero sea del banco 
        if (cajero.getBanco() != this) {
            return;
        }
        //Validar si ya existe un cajero registrado con el mismo número de serie dentro del banco 
        if (buscarCajero(cajero.getNumeroSerie()) != null) {
            return;
        }
        //Crea un nuevo arreglo que copia todos los elementos del arreglo anterior pero le suma una posición adicional a su tamaño
        cajeros = Arrays.copyOf(cajeros, cajeros.length + 1);
        //Asigna el nuevo cajero en la ultima posicion disponible de ese nuevo arreglo recien ampliado 
        cajeros[cajeros.length - 1] = cajero;
    }
    //da copia del arreglo con los cajeros asociados al banco 

    public Cajero[] listarCajeros() {
        return Arrays.copyOf(cajeros, cajeros.length);
    }
    // busca y devuelve cuenta bancaria apartir de su nro de cuenta y da null si no se encuentra 
    public Cajero buscarCajero(Integer numeroSerie) {
        for (Cajero cajero : cajeros) {
            if (cajero.getNumeroSerie().equals(numeroSerie)) {
                return cajero;
            }
        }
        return null;
    }
    // Cunetas bancarias 
    //se agrega cuenta bancaria si no es nula y si no existe otra cuenta registrada con el mismo nro 

    public void agregarCuenta(CuentaBancaria cuenta) {
        if (cuenta == null) {
            return;
        }
        if (buscarCuenta(cuenta.getNumeroCuenta()) != null) {
            return;
        }
        //Reorganiz ael arreglo y agrega la nueva

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
