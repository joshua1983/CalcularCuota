package catalina.joshua.calcularcuota;

/**
 * Created by jbarrios on 18/10/2017.
 */

public class Cuota {
    private int numeroCuota;
    private double capital;
    private double interes;
    private double pago;
    private double balance;

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
