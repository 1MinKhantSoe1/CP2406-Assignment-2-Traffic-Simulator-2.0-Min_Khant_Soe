import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class City_Content extends JPanel{
    int laneHeights =300;
    ArrayList<Vehicle> Vehicle_Containers =new ArrayList<>();
    ArrayList<TrafficLight> Traffic_Containers=new ArrayList<>();

    public City_Content(){
        setBackground(Color.WHITE);
        setSize( 855, 590);
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GRAY);
        g.fillRect(350, 0, 50, 600);

        g.setColor(Color.WHITE);
        for(int a = laneHeights; a< laneHeights *4; a=a+ laneHeights) {
            for(int b=0;b<getHeight();b+=40) {
                g.fillRect(375, b, 5, 30);
            }
        }
        g.setColor(Color.GRAY);
        g.fillRect(0, 100, 870, 50);

        g.setColor(Color.WHITE);
        for(int a = laneHeights; a< laneHeights *4; a=a+ laneHeights) {
            for(int b=0;b<getWidth();b+=40) {
                g.fillRect(b, 120, 30, 5);
            }
        }
        g.setColor(Color.GRAY);
        g.fillRect(0, 400, 870, 50);

        g.setColor(Color.WHITE);
        for(int a = laneHeights; a< laneHeights *4; a=a+ laneHeights) {
            for(int b=0;b<getWidth();b+=40) {
                g.fillRect(b, 420, 30, 5);
            }
        }
        g.setColor(Color.GRAY);
        g.fillRect(150, 400, 50, 600);

        g.setColor(Color.WHITE);
        for(int a = laneHeights; a< laneHeights *4; a=a+ laneHeights) {
            for(int b=430;b<getHeight();b+=40) {
                g.fillRect(173, b, 5, 30);
            }
        }
        g.setColor(Color.GRAY);
        g.fillRect(650, 400, 50, 600);

        g.setColor(Color.WHITE);
        for(int a = laneHeights; a< laneHeights *4; a=a+ laneHeights) {
            for(int b=430;b<getHeight();b+=40) {
                g.fillRect(673, b, 5, 30);
            }
        }
        for(int a = 0; a< Vehicle_Containers.size(); a++) {
            Vehicle_Containers.get(a).paintMe(g);
        }
        for(int a = 0; a< Traffic_Containers.size(); a++) {
            Traffic_Containers.get(a).paintMe(g);
        }
    }
    public void addVehicle(Vehicle vehicle) {
        Vehicle_Containers.add(vehicle);
    }
    public void addTraffic(TrafficLight trafficLight) {
        Traffic_Containers.add(trafficLight);
    }
    public void Move() {
        for(int i = 0; i< Vehicle_Containers.size(); i++) {
            Vehicle vehicle= Vehicle_Containers.get(i);
            if(vehicle.turn1 & !vehicle.road.equals("in") ) {
                vehicle.setY(vehicle.getY()+vehicle.getSpeed() );
                vehicle.setPosition(vehicle.getY()+vehicle.getSpeed());
                vehicle.setRoad(vehicle.getX(), vehicle.getY());
            }
            else if(vehicle.turn2 & !vehicle.road.equals("in")){
                vehicle.setY(vehicle.getY()-vehicle.getSpeed());
                vehicle.setPosition(vehicle.getY()-vehicle.getSpeed());
                vehicle.setRoad(vehicle.getX(), vehicle.getY());
            }
            else if(vehicle.turn3 & !vehicle.road.equals("in")){
                vehicle.setX(vehicle.getX()-vehicle.getSpeed());
                vehicle.setPosition(vehicle.getX()-vehicle.getSpeed());
                vehicle.setRoad(vehicle.getX(), vehicle.getY());
            }
            else if(!vehicle.turn3 & !vehicle.turn3 & !vehicle.turn3 & !vehicle.road.equals("in")) {
                vehicle.setX(vehicle.getX()+vehicle.getSpeed());
                vehicle.setPosition(vehicle.getX()+vehicle.getSpeed());
                vehicle.setRoad(vehicle.getX(), vehicle.getY());
            }
            else {
                if(vehicle.current) {
                    int count=0;
                    if(vehicle.signal.equals("s1")) {count=0;}
                    else if(vehicle.signal.equals("s2")) {count=1;}
                    else if(vehicle.signal.equals("s3")) {count=2;}
                    else if(vehicle.signal.equals("s4")) {count=3;}
                    else if(vehicle.signal.equals("s5")) {count=4;}
                    else if(vehicle.signal.equals("s6")) {count=5;}
                    else if(vehicle.signal.equals("s7")) {count=6;}
                    else if(vehicle.signal.equals("s8")) {count=7;}

                    if(count==0) {
                        Traffic_Containers.get(count).setStatus(false, true);
                    }
                    else {
                        Traffic_Containers.get(count-1).setStatus(true , false);
                        Traffic_Containers.get(count).setStatus(false, true);
                    }
                    if(vehicle.turn1) {
                        vehicle.setY(vehicle.getY()+vehicle.getSpeed() );
                        vehicle.setRoad(vehicle.getX(), vehicle.getY());
                    }
                    else if(vehicle.turn2){
                        vehicle.setY(vehicle.getY()-vehicle.getSpeed());
                        vehicle.setRoad(vehicle.getX(), vehicle.getY());
                    }
                    else if(vehicle.turn3){
                        vehicle.setX(vehicle.getX()-vehicle.getSpeed());
                        vehicle.setRoad(vehicle.getX(), vehicle.getY());
                    }
                    else if(!vehicle.turn3 & !vehicle.turn3 & !vehicle.turn3) {
                        vehicle.setX(vehicle.getX()+vehicle.getSpeed());
                        vehicle.setRoad(vehicle.getX(), vehicle.getY());
                    }
                }
                else {
                    vehicle.setRoad(vehicle.getX(), vehicle.getY());
                }
            }
        }
    }
}