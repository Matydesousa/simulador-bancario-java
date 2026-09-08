//representacion de un cajero automatico realiza operaciones como dispensar, extraer y consultar saldo 
// Atributos 
public class Cajero {
    private Integer numeroSerie;
    private String ubicacion;
    private String estado; 
    private Double saldoDisponible;
    private int capacidadBilletes;
    private Banco banco;
    // Inicia la clase cajero con sus datos 
    public Cajero(Integer numeroSerie, String ubicacion, String estado, Double saldoDisponible,
                  int capacidadBilletes, Banco banco) {
        this.numeroSerie = numeroSerie;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.saldoDisponible = saldoDisponible;
        this.capacidadBilletes = capacidadBilletes;
        this.banco = banco;
    }
    //METODOS 
    public Integer getNumeroSerie() {
        return numeroSerie;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getSaldoDisponible() {
        return saldoDisponible;
    }

    public int getCapacidadBilletes() {
        return capacidadBilletes;
    }

    public Banco getBanco() {
        return banco;
    }
    // Dispensa monto si el cajero esta activo y si tiene saldo suficiente, tambien actualiza saldo disponible 
    public Boolean dispensarEfectivo(Double monto) {
        //Valida que el cajero se encuentre activo
        if (!"Activo".equals(estado)) {
            return false;
        }
        //Valida que el monto no sea nulo 
        if (monto == null) {
            return false;
        }
        //Valida que el monto sea positivo menor o igual al saldo disponible en el cajero 
        if (monto > 0 && monto <= saldoDisponible) {
            saldoDisponible = saldoDisponible - monto;
            return true;
        }
        return false;
    }
    // Realiza una extraccion de dinero coordinado la cuenta bancaria y el cajero 
    public Boolean extraer(CuentaBancaria cuenta, Double monto) {
        //validar la cuenta y el monto no sean nulos
        if (cuenta == null || monto == null) {
            return false;
        }
        //validar que el cajero este activo 
        if (!"Activo".equals(estado)) {
            return false;
        }

        // La cuenta y el cajero deben tener fondos antes de descontar.
        if (monto > 0 && monto <= saldoDisponible) {
            Boolean extraccionRealizada = cuenta.extraer(monto);
            if (extraccionRealizada) {
                dispensarEfectivo(monto);
                return true;
            }
        }
        return false;
    }
    //Consultar el saldo actual de una cuenta bancaria 
    public Double consultarSaldo(CuentaBancaria cuenta) {
        return cuenta.consultarSaldo();
    }
    // recargar el saldo disponible del cajero agregando monto especifico 
    public void recargarEfectivo(Double monto) {
        if (monto == null) {
            return;
        }
        // isFinite descarta valores especiales como infinito.
        if (!Double.isFinite(monto)) {
            return;
        }
        // si el monto es mayor a cero se suma al saldo disponible 
        if (monto > 0) {
            saldoDisponible = saldoDisponible + monto;
        }
    }
     
    @Override
    public String toString() {
        return "Cajero{" +
                "numeroSerie=" + numeroSerie +
                ", ubicacion=" + ubicacion +
                ", estado=" + estado +
                ", saldoDisponible=" + saldoDisponible +
                ", capacidadBilletes=" + capacidadBilletes +
                ", banco=" + banco.getNombre() +
                '}';
    }
}
