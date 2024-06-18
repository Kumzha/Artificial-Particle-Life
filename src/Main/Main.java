package Main;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static final int WIN_WIDTH = 800;
    public static final int WIN_HEIGHT = WIN_WIDTH * 9 / 16 ; 
    static private Display display;

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Life Particle Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.validate();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setLayout(new FlowLayout());
            frame.setResizable(false);

            display = new Display();
            frame.add(display);


            frame.setSize(WIN_WIDTH, WIN_HEIGHT);
            frame.pack();
            frame.setLocationRelativeTo(null);
            display.runSimulation();

        });

        
        
    }


    
}
