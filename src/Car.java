import java.awt.*;

public class Car extends Vehicle{
    public Car(int x, int y) {
        super(x, y);
        width=30;
        height=20;
        speed=15;
    }
    public void paintMe(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width,height);
    }
}