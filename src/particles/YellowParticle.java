package particles;

import java.awt.Graphics2D;

public class YellowParticle extends Particle{

    public YellowParticle(int startingX, int startingY){
        super(startingX, startingY);
        particleType = "yellow";
        color =  MyColor.getColor(particleType);
    }

    
    public void draw(Graphics2D g2){
        g2.setPaint(color);
        g2.fillOval(this.x, this.y, 7, 7);
    }
}
