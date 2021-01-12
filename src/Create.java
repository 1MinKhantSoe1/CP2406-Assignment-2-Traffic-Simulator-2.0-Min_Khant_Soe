import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Create extends JPanel{

    int NumRoad=0;
    String Cityname="";
    int laneHeight=300;
    ArrayList<Vehicle> Vehicle_Container =new ArrayList<>();
    ArrayList<TrafficLight> Traffic_Container=new ArrayList<>();

    public Create(String n,int NumberRoad){
        setBackground(Color.WHITE);
        setSize(855,590);
        setLayout(null);
        setVisible(true);
        this.Cityname =n;
        this.NumRoad=NumberRoad;
    }
    public void addVehicle(Vehicle vehicle){Vehicle_Container.add(vehicle);}
    public void addTraffic(TrafficLight trafficLight){Traffic_Container.add(trafficLight);}
    public void paint(Graphics g) {
        super.paintComponent(g);

        if (NumRoad <= 2) {
            g.setColor(Color.GRAY);
            g.fillRect(0, 100, 870, 50);

            g.setColor(Color.WHITE);
            for (int a = laneHeight; a < laneHeight * 4; a = a + laneHeight) {
                for (int b = 0; b < getWidth(); b += 40) {
                    g.fillRect(b, 120, 30, 5);
                }
            }
        } else if (NumRoad <= 4) {
            g.setColor(Color.GRAY);
            g.fillRect(0, 100, 870, 50);

            g.setColor(Color.WHITE);
            for (int a = laneHeight; a < laneHeight * 4; a = a + laneHeight) {
                for (int b = 0; b < getWidth(); b += 40) {
                    g.fillRect(b, 120, 30, 5);
                }
            }
            g.setColor(Color.GRAY);
            g.fillRect(350, 0, 50, 600);

            g.setColor(Color.WHITE);
            for (int a = laneHeight; a < laneHeight * 4; a = a + laneHeight) {
                for (int b = 0; b < getHeight(); b += 40) {
                    g.fillRect(375, b, 5, 30);
                }
            }
        } else if (NumRoad <= 6) {
            g.setColor(Color.GRAY);
            g.fillRect(0, 100, 870, 50);

            g.setColor(Color.WHITE);
            for (int a = laneHeight; a < laneHeight * 4; a = a + laneHeight) {
                for (int b = 0; b < getWidth(); b += 40) {
                    g.fillRect(b, 120, 30, 5);
                }
            }
            g.setColor(Color.GRAY);
            g.fillRect(350, 0, 50, 600);

            g.setColor(Color.WHITE);
            for (int a = laneHeight; a < laneHeight * 4; a = a + laneHeight) {
                for (int b = 0; b < getHeight(); b += 40) {
                    g.fillRect(375, b, 5, 30);
                }
            }
            g.setColor(Color.GRAY);
            g.fillRect(0, 400, 870, 50);

            g.setColor(Color.WHITE);
            for (int a = laneHeight; a < laneHeight * 4; a = a + laneHeight) {
                for (int b = 0; b < getWidth(); b += 40) {
                    g.fillRect(b, 420, 30, 5);
                }
            }
        } else if (NumRoad <= 10) {
            g.setColor(Color.GRAY);
            g.fillRect(0, 100, 870, 50);

            g.setColor(Color.WHITE);
            for (int a = laneHeight; a < laneHeight * 4; a = a + laneHeight) {
                for (int b = 0; b < getWidth(); b += 40) {
                    g.fillRect(b, 120, 30, 5);
                }
            }
            g.setColor(Color.GRAY);
            g.fillRect(350, 0, 50, 600);

            g.setColor(Color.WHITE);
            for (int a = laneHeight; a < laneHeight * 4; a = a + laneHeight) {
                for (int b = 0; b < getHeight(); b += 40) {
                    g.fillRect(375, b, 5, 30);
                }
            }
            g.setColor(Color.GRAY);
            g.fillRect(0, 400, 870, 50);

            g.setColor(Color.WHITE);
            for (int a = laneHeight; a < laneHeight * 4; a = a + laneHeight) {
                for (int b = 0; b < getWidth(); b += 40) {
                    g.fillRect(b, 420, 30, 5);
                }
            }
            g.setColor(Color.GRAY);
            g.fillRect(150, 400, 50, 600);

            g.setColor(Color.WHITE);
            for (int a = laneHeight; a < laneHeight * 4; a = a + laneHeight) {
                for (int b = 430; b < getHeight(); b += 40) {
                    g.fillRect(173, b, 5, 30);
                }
            }
            g.setColor(Color.GRAY);
            g.fillRect(650, 400, 50, 600);

            g.setColor(Color.WHITE);
            for (int a = laneHeight; a < laneHeight * 4; a = a + laneHeight) {
                for (int b = 430; b < getHeight(); b += 40) {
                    g.fillRect(673, b, 5, 30);
                }
            }
        }

        for (int a = 0; a < Traffic_Container.size(); a++) {
            Traffic_Container.get(a).paintMe(g);
        }

        for (int a = 0; a < Vehicle_Container.size(); a++) {
            Vehicle_Container.get(a).paintMe(g);
        }
    }
    public void Move() {
        for(int i = 0; i< Vehicle_Container.size(); i++) {
            Vehicle vehicle = Vehicle_Container.get(i);

            if (vehicle.turn1 & !vehicle.road.equals("in")) {
                vehicle.setY(vehicle.getY() + vehicle.getSpeed());
                vehicle.setPosition(vehicle.getY() + vehicle.getSpeed());
                vehicle.setRoad(vehicle.getX(), vehicle.getY());
            } else if (vehicle.turn2 & !vehicle.road.equals("in")) {
                vehicle.setY(vehicle.getY() - vehicle.getSpeed());
                vehicle.setPosition(vehicle.getY() - vehicle.getSpeed());
                vehicle.setRoad(vehicle.getX(), vehicle.getY());
            } else if (vehicle.turn3 & !vehicle.road.equals("in")) {
                vehicle.setX(vehicle.getX() - vehicle.getSpeed());
                vehicle.setPosition(vehicle.getX() - vehicle.getSpeed());
                vehicle.setRoad(vehicle.getX(), vehicle.getY());
            } else if (!vehicle.turn3 & !vehicle.turn3 & !vehicle.turn3 & !vehicle.road.equals("in")) {
                vehicle.setX(vehicle.getX() + vehicle.getSpeed());
                vehicle.setPosition(vehicle.getX() + vehicle.getSpeed());
                vehicle.setRoad(vehicle.getX(), vehicle.getY());
            } else {
                if (vehicle.current) {
                    int count = 0;
                    if (vehicle.signal.equals("s1")) {
                        count = 0;
                    } else if (vehicle.signal.equals("s2")) {
                        count = 1;
                    } else if (vehicle.signal.equals("s3")) {
                        count = 2;
                    } else if (vehicle.signal.equals("s4")) {
                        count = 3;
                    } else if (vehicle.signal.equals("s5")) {
                        count = 4;
                    } else if (vehicle.signal.equals("s6")) {
                        count = 5;
                    } else if (vehicle.signal.equals("s7")) {
                        count = 6;
                    } else if (vehicle.signal.equals("s8")) {
                        count = 7;
                    }
                    if (Traffic_Container.size() != 0) {
                        if (count == 0) {
                            Traffic_Container.get(count).setStatus(false,true);
                        } else {
                            if (Traffic_Container.size() == 5 & count == 5) {
                                Traffic_Container.get(count - 2).setStatus(true, false);
                                Traffic_Container.get(count - 1).setStatus(false, true);
                            } else {
                                Traffic_Container.get(count - 1).setStatus(true, false);
                                Traffic_Container.get(count).setStatus(false,true);
                            }

                        }
                    }
                    if (vehicle.turn1) {
                        vehicle.setY(vehicle.getY() + vehicle.getSpeed());
                        vehicle.setRoad(vehicle.getX(), vehicle.getY());
                    } else if (vehicle.turn2) {
                        vehicle.setY(vehicle.getY() - vehicle.getSpeed());
                        vehicle.setRoad(vehicle.getX(), vehicle.getY());
                    } else if (vehicle.turn3) {
                        vehicle.setX(vehicle.getX() - vehicle.getSpeed());
                        vehicle.setRoad(vehicle.getX(), vehicle.getY());
                    } else if (!vehicle.turn3 & !vehicle.turn3 & !vehicle.turn3) {
                        vehicle.setX(vehicle.getX() + vehicle.getSpeed());
                        vehicle.setRoad(vehicle.getX(), vehicle.getY());
                    }
                } else {
                    vehicle.setRoad(vehicle.getX(), vehicle.getY());
                }
            }
        }
    }
}