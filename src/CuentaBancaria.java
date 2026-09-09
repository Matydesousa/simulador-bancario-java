//Atributos
public class CuentaBancaria {
    private Integer numeroCuenta;
    private double saldo;
    private String titular;
    private String tipoCuenta;
    private String cbu;
    //Inicializa la cuenta bancaria con sus datos
    public CuentaBancaria(Integer numeroCuenta, double saldo, String titular, String tipoCuenta, String cbu) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.titular = titular;
        this.tipoCuenta = tipoCuenta;
        this.cbu = cbu;
    }
    //METODOS
    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getCbu() {
        return cbu;
    }
    //Hace un deposito en la cuenta bancaria agregando el monto especifico al saldo si el monto es valido
    public void depositar(Double monto) {
        //Si el monto es mayor a cero, se suma al saldo
        if (monto > 0) {
            saldo = saldo + monto;
        }
    }
    //Extrae un monto mayor a cero si la cuenta tiene saldo suficiente
    public Boolean extraer(Double monto) {
        //validar que el monto sea positivo y menor o igual al saldo disponible
        if (monto > 0 && monto <= saldo) {
            saldo = saldo - monto;
            return true;
        }
        return false;
    }
    //operacion de transferencia de monto
    public Boolean transferir(CuentaBancaria destino, double monto) {
        if (destino == null || destino.equals(this)) {
            return false;
        }

        // extraer ya revisa que el monto sea positivo y alcance el saldo.
        boolean extraccionExitosa = this.extraer(monto);
        if (extraccionExitosa) {
            destino.depositar(monto);
            return true;
        }
        return false;
    }

    // consulta y da saldo actual de la cuenta
    public Double consultarSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "numeroCuenta=" + numeroCuenta +
                ", saldo=" + saldo +
                ", titular=" + titular +
                ", tipoCuenta=" + tipoCuenta +
                ", cbu=" + cbu +
                '}';
    }
}
