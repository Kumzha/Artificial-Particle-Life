package particles;

import java.awt.Color;

public class RedParticle extends Particle{
    
    public RedParticle(int startingX, int startingY) {
        super(startingX, startingY);
        particleType = "red";
        color =  MyColor.getColor(particleType);
    }
    
}
