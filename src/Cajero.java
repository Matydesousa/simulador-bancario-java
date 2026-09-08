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

    public Banco getBanco() {
        return banco;
    }

    public Double getSaldoDisponible() {
        return saldoDisponible;
    }

    public Boolean dispensarEfectivo(Double monto) {
        if ("Activo".equals(estado) && monto != null && Double.isFinite(monto)
                && monto > 0 && monto <= saldoDisponible) {
            saldoDisponible = saldoDisponible - monto;
            return true;
        }
        return false;
    }

    // Comprueba ambos saldos antes de modificar la cuenta y el cajero.
    public Boolean extraer(CuentaBancaria cuenta, Double monto) {
        if (cuenta == null || !"Activo".equals(estado) || monto == null
                || !Double.isFinite(monto) || monto <= 0 || monto > saldoDisponible) {
            return false;
        }
        if (cuenta.extraer(monto)) {
            return dispensarEfectivo(monto);
        }
        return false;
    }

    public Double consultarSaldo(CuentaBancaria cuenta) {
        return cuenta.consultarSaldo();
    }

    public void recargarEfectivo(Double monto) {
        if (monto != null && Double.isFinite(monto) && monto > 0) {
            saldoDisponible = saldoDisponible + monto;
        }
    }

    @Override
    public String toString() {
        return "Cajero{" + "numeroSerie=" + numeroSerie + ", ubicacion='" + ubicacion + '\''
                + ", estado='" + estado + '\'' + ", saldoDisponible=" + saldoDisponible
                + ", capacidadBilletes=" + capacidadBilletes + ", banco=" + banco.getNombre() + '}';
    }
}
