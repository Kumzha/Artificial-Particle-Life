package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import particles.Particle;
import particles.RedParticle;
import particles.YellowParticle;

import javax.swing.JPanel;


public class Display extends JPanel implements Runnable{
    
    private Thread simulationThread;
    private List<List<Particle>> particleTypeList = new ArrayList<>();
    private List<Particle> yellowParticleList = new ArrayList<>();
    private List<Particle> redParticleList = new ArrayList<>();

    private final int FPS = 60;


    public Display(){
        
        setPreferredSize(new Dimension(Main.WIN_WIDTH, Main.WIN_HEIGHT));
        setBackground(Color.BLACK);

        Particle y1 = new YellowParticle(150, 150);
        Particle y2 = new YellowParticle(350, 350);
        Particle r1 = new RedParticle(100, 500);
        Particle r2 = new RedParticle(1000, 150);

        yellowParticleList.add(y1);
        yellowParticleList.add(y2);
        redParticleList.add(r1);
        redParticleList.add(r2);

        particleTypeList.add(yellowParticleList);
        particleTypeList.add(redParticleList);
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

                particle.forceRule(yellowParticleList, yellowParticleList, 1);
                particle.draw(g2);

            }
        }


    }



    @Override
    public void run() {

        double updateInterval = 1000000000 / FPS; 
        double nextUpdateTime = System.nanoTime() + updateInterval;
        int drawCount = 0;
        long timer = 0;

        while(simulationThread != null){
            long startLoopTime = System.nanoTime();

            update();
            repaint();           

            try {
                double remainingTime = nextUpdateTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                Thread.sleep((long)remainingTime);

                nextUpdateTime += updateInterval;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

            } catch (IllegalArgumentException e) {
            } catch (InterruptedException e) {
            }
            long endLoopTime = System.nanoTime();
            timer += endLoopTime - startLoopTime;
            drawCount ++;
            
            if(timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() {
        
    }

    
}
