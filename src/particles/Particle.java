package particles;

import Main.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

public class Particle {

    String particleType;
    Color color;
    float x,y;
    float velocityX, velocityY;

    public Particle(float startingX, float startingY, String type){
        x = startingX;
        y = startingY;
        color = MyColor.getColor(type);
        particleType = type;
        
    }

    public static void createParticles(int number, List<Particle> list, String type){
        for(int i = 0; i<number; i++){           
            int x = (int)(Math.random() * Main.WIN_WIDTH);
            int y = (int) (Math.random() * Main.WIN_HEIGHT);
            list.add(new Particle(x,y, type));
        }
    }

    public void forceRule(List<Particle> firstParticles, List<Particle> secondParticles, float forceConstant) {

        for(int i = 0; i<firstParticles.size(); i++){

            float fx = 0;
            float fy = 0;
            float force = 0; 
            Particle p1 = null;
            Particle p2 = null;


            for(int j = 0; j<secondParticles.size(); j++){

                p1 = firstParticles.get(i);
                p2 = secondParticles.get(j);

                float dx = p1.x - p2.x;
                float dy = p1.y - p2.y; 
                float d = (int) Math.sqrt((dx * dx) + (dy*dy));

                if(d > 0&&d<80){
                    force = forceConstant * 1/d;
                    fx += (force * dx);
                    fy += (force * dy);
                }
                p1.velocityX = ((p1.velocityX + fx)*0.5f);
                p1.velocityY = ((p1.velocityY + fy)*0.5f);

                if(p1.x <= 0 || p1.x >= Main.WIN_WIDTH-7){p1.velocityX *= -1;}
                if(p1.y <= 0 || p1.y >= Main.WIN_HEIGHT-7){p1.velocityY *= -1;}

                // if(p1.x <=0 ){p1.x=Main.WIN_WIDTH-7;}
                // else if(p1.x >=Main.WIN_WIDTH-7){p1.x = 0;}
                // if(p1.y <=0 ){p1.y=Main.WIN_HEIGHT-7;}
                // else if(p1.y >=Main.WIN_HEIGHT-7){p1.y = 0;}

                p1.x += p1.velocityX;
                p1.y += p1.velocityY;
                            

            }
            
            
        }
    }

    public void draw(Graphics2D g2) {
        g2.setPaint(color);
        g2.fillOval((int) this.x, (int) this.y, 7, 7);
    }
}