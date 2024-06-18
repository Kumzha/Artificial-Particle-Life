package particles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Vector;

public class Particle {

    String particleType;
    Color color;
    int x,y;
    Vector<Integer> vector;
    int velocity;

    public Particle(int startingX, int startingY){
        x = startingX;
        y = startingY;
    }

    public void forceRule(List<Particle> firstParticles, List<Particle> secondParticles, int forceConstant) {
        int fx = 0;
        int fy = 0;

        Particle a = firstParticles.get(0);
        Particle b = secondParticles.get(1);

        int dx = a.x - b.x;
        int dy = a.y - b.y; 
        int d = (int) Math.sqrt((dx * dx) + (dy*dy));

        if(d>0){
            int force = forceConstant * 1/d;
            fx += (force * dx);
            fy += (force * dy);
        }

        a.x += fx;
        a.y += fy;
        b.x += fx;
        b.y += fy;
        
        System.out.println("a.x = " + a.x);
    }

    public void update(){



    }

    public void draw(Graphics2D g2){
        g2.setPaint(color);
        g2.fillOval(this.x, this.y, 7, 7);
    }

}