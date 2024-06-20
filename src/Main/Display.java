package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import particles.Particle;
// import particles.RedParticle;
// import particles.YellowParticle;

import javax.swing.JPanel;


public class Display extends JPanel implements Runnable{
    
    private Thread simulationThread;
    private List<List<Particle>> particleTypeList = new ArrayList<>();
    private List<Particle> yellowParticleList = new ArrayList<>();
    private List<Particle> redParticleList = new ArrayList<>();
    private List<Particle> greenParticleList = new ArrayList<>();
    private List<Particle> whiteParticleList = new ArrayList<>();

    private final int FPS = 60;


    public Display(){
        
        setPreferredSize(new Dimension(Main.WIN_WIDTH, Main.WIN_HEIGHT));
        setBackground(Color.BLACK);

        // test
        // Particle.createParticles(2, yellowParticleList, "yellow");

        Particle.createParticles(100, redParticleList, "red");
        Particle.createParticles(200, yellowParticleList, "yellow");
        // Particle.createParticles(50, whiteParticleList, "white");
        // Particle.createParticles(50, greenParticleList, "green");

        particleTypeList.add(whiteParticleList);
        particleTypeList.add(greenParticleList);
        particleTypeList.add(yellowParticleList);
        particleTypeList.add(redParticleList);
    }

    public void update() {
        for(List<Particle> particleList : particleTypeList){

            for(Particle particle : particleList){
                //test rule
                // particle.forceRule(yellowParticleList, yellowParticleList, -0.00001f);


                particle.forceRule(redParticleList, redParticleList, -0.01f);
                particle.forceRule(redParticleList, yellowParticleList, -0.001f);
                particle.forceRule(yellowParticleList, redParticleList, 0.001f);
            }
        }
    }


    public void runSimulation(){
        simulationThread = new Thread(this);
        simulationThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        for(List<Particle> particleList : particleTypeList){

            for(Particle particle : particleList){

                particle.draw(g2);

            }
        }


    }




    @Override
    public void run() {

        while(simulationThread != null){

            update();
            repaint();           

        }
    }
    
}
