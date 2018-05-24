/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import entidades.Payment;
import entidades.Rental;
import java.sql.Timestamp;
import modeloControl.AccesoDatos;
import modeloControl.ConexionSingleton;

/**
 *
 * @author LABO08
 */
public class DebtCardStrategy extends CardStrategy {

    private Payment payment;

    public DebtCardStrategy(Payment payment) {
        this.payment = payment;
    }

    @Override
    public void pay(Double amount) {
        payment.setAmount(amount);
        AccesoDatos ad = new AccesoDatos(ConexionSingleton.getConexion("sakila", "localhost", "root", ""));
        ad.insertRental(payment.getRental());
        ad.insertPayment(payment);
    }
}
