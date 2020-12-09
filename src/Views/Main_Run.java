/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import javax.swing.JOptionPane;

/**
 *
 * @author 1styrGroupC
 */
public class Main_Run {

    public static void main(String[] args) {
        try {
            Sign_In signIn = new Sign_In(null, true);
            signIn.setVisible(true);
            if (signIn.level == 1 || signIn.level == 2) {
                Main main = new Main(signIn.id);
                main.setVisible(true);

            } else {
                Transactions transaction = new Transactions(signIn.id);
                transaction.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

}
