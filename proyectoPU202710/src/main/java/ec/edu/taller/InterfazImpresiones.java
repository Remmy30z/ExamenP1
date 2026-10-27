package ec.edu.taller;

import javax.swing.*;

/** Completar manualmente la interfaz Swing e integrarla con GestorImpresiones. */
public class InterfazImpresiones {
    public static void main(String[] args) {
        JFrame frame=new JFrame("interfaz");
        frame.setContentPane(new interfaz().getPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
