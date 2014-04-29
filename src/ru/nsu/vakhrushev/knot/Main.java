package ru.nsu.vakhrushev.knot;

import ru.nsu.vakhrushev.knot.controller.ApplicationController;
import ru.nsu.vakhrushev.knot.model.Model;
import ru.nsu.vakhrushev.knot.view.MainFrame;

import javax.swing.*;

/**
 * @author Maxim Vakhrushev
 */
public class Main {
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Model model = new Model();
                    ApplicationController applicationController = new ApplicationController(model);
                    MainFrame mainFrame = new MainFrame(applicationController);
                    mainFrame.setVisible(true);
                } catch (Exception ex) {
                    System.err.println(ex.getLocalizedMessage());
                    ex.printStackTrace();
                }
            }
        });
    }
}
