import java.awt.*;

public class Bus extends Vehicle{
    public Bus (int x, int y){
        super(x,y);
        width=45;
        height=10;
        speed=7;
    }
    public void paintMe(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(x,y, width, height);
    }
}
