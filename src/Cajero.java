public class Cajero {
    private Integer numeroSerie;
    private String ubicacion;
    private String estado;
    private Double saldoDisponible;
    private int capacidadBilletes;
    private Banco banco;

    public Cajero(Integer numeroSerie, String ubicacion, String estado, Double saldoDisponible,
                  int capacidadBilletes, Banco banco) {
        this.numeroSerie = numeroSerie;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.saldoDisponible = saldoDisponible;
        this.capacidadBilletes = capacidadBilletes;
        this.banco = banco;
    }

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

    public Boolean dispensarEfectivo(Double monto) {
        if (monto > 0 && monto <= saldoDisponible) {
            saldoDisponible = saldoDisponible - monto;
            return true;
        }
        return false;
    }

    public Boolean extraer(CuentaBancaria cuenta, Double monto) {
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

    public Double consultarSaldo(CuentaBancaria cuenta) {
        return cuenta.consultarSaldo();
    }

    public void recargarEfectivo(Double monto) {
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
