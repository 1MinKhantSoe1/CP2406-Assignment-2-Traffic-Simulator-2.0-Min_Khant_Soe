import java.awt.*;

public class TrafficLight
{
    int x=0, y=0;
    boolean red= false;
    boolean green= false;
    boolean turn= false;

    public void setStatus(boolean Red, boolean Green)
    {
        this.red = Red;
        this.green = Green;

    }

    public TrafficLight(int x, int y, boolean Red, boolean Green, boolean turn)
    {
        this.x = x;
        this.y = y;
        this.red = Red;
        this.green = Green;

        this.turn = turn;
    }

    public void paintMe(Graphics g)
    {
        if (!turn)
        {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, 10, 40);

            if (this.red)
            {
                g.setColor(Color.RED);

            }
            else {
                g.setColor(Color.GREEN);
                g.fillOval(x + 1, y + 10, 8, 8);

            }
        }
        else
            {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, 40, 10);
            if (this.red)
            {
                g.setColor(Color.RED);
                g.fillOval(x + 10, y + 1, 8, 8);
            }
            else
                {
                g.setColor(Color.GREEN);
                g.fillOval(this.x + 10, this.y + 1, 8, 8);
            }

        }
    }


}

