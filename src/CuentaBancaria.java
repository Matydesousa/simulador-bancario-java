public class CuentaBancaria {
    private Integer numeroCuenta;
    private double saldo;
    private String titular;
    private String tipoCuenta;
    private String cbu;

    public CuentaBancaria(Integer numeroCuenta, double saldo, String titular, String tipoCuenta, String cbu) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.titular = titular;
        this.tipoCuenta = tipoCuenta;
        this.cbu = cbu;
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public void depositar(Double monto) {
        if (monto != null && Double.isFinite(monto) && monto > 0) {
            saldo = saldo + monto;
        }
    }

    public Boolean extraer(Double monto) {
        if (monto != null && Double.isFinite(monto) && monto > 0 && monto <= saldo) {
            saldo = saldo - monto;
            return true;
        }
        return false;
    }

    public Double consultarSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" + "numeroCuenta=" + numeroCuenta + ", saldo=" + saldo
                + ", titular='" + titular + '\'' + ", tipoCuenta='" + tipoCuenta + '\''
                + ", cbu='" + cbu + '\'' + '}';
    }
}
