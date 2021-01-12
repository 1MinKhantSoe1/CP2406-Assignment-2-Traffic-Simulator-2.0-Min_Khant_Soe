import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Traffic_Simulator extends JFrame implements Runnable, ActionListener {

    private JPanel contentPane;
    JLabel Value = new JLabel("");
    JButton BtnRun = new JButton("Run");
    JButton BtnStop = new JButton("Stop");
    JLabel Status_Sim = new JLabel("Simulator Status: Idle");

    int total = 0;
    int index = 0;
    int i = 0;
    int city = 0;
    int count = 0;

    private Vehicle Vehicle_Containers[] = new Vehicle[30];
    private Create CityContainer[] = new Create[5];

    private City_Content City = new City_Content();
    boolean running = false;
    boolean open = false;

    public Traffic_Simulator() {

        setTitle("Traffic Simulator by Min Khant Soe");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150,150 , 1058, 629);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel MenuTitle = new JLabel("Menu");
        MenuTitle.setFont(new Font("Arial", Font.BOLD, 35));
        MenuTitle.setBounds(910, 10, 100, 35);
        MenuTitle.setForeground(Color.WHITE);
        contentPane.add(MenuTitle);

        JButton Btn_City = new JButton("City\r\n");
        Btn_City.setBounds(865, 80, 85, 23);
        contentPane.add(Btn_City);

        JButton Btn_Simulator = new JButton("Sim");
        Btn_Simulator.setBounds(960, 80, 75, 23);
        contentPane.add(Btn_Simulator);

        JButton OpenBtn_City = new JButton("Open City\r\n");
        OpenBtn_City.setBounds(910, 270, 90, 25);
        OpenBtn_City.setVisible(false);
        contentPane.add(OpenBtn_City);

        JButton EditBtn_City = new JButton("Edit City");
        EditBtn_City.setBounds(910, 215, 90, 25);
        EditBtn_City.setVisible(false);
        contentPane.add(EditBtn_City);

        JButton CreateBtn_City = new JButton("Create New City");
        CreateBtn_City.setBounds(880, 155, 150, 25);
        CreateBtn_City.setVisible(false);
        contentPane.add(CreateBtn_City);

        addTraffic(-10, 0);
        BtnStop.setBounds(910, 220, 90, 25);
        BtnStop.addActionListener(this);
        contentPane.add(BtnStop);

        BtnRun.setBounds(910, 155, 90, 25);
        BtnRun.addActionListener(this);
        contentPane.add(BtnRun);

        Status_Sim.setForeground(Color.GREEN);
        Status_Sim.setBounds(880, 430, 150, 25);
        contentPane.add(Status_Sim);

        JLabel ModeText = new JLabel("Mode: Simulation");
        ModeText.setForeground(Color.GREEN);
        ModeText.setBounds(880, 400, 120, 25);
        contentPane.add(ModeText);

        JLabel CityName = new JLabel("City Name: Default City");
        CityName.setForeground(Color.GREEN);
        CityName.setBounds(880, 465, 150, 25);
        contentPane.add(CityName);

        JLabel VehicleNum = new JLabel("Total Vehicles");
        VehicleNum.setForeground(Color.GREEN);
        VehicleNum.setBounds(880, 500, 150, 25);
        contentPane.add(VehicleNum);

        Value.setText(String.valueOf(total));
        Value.setForeground(Color.GREEN);
        Value.setBounds(968, 500, 70, 25);
        contentPane.add(Value);

        contentPane.add(City);
        Btn_Simulator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                BtnRun.setVisible(true);
                BtnStop.setVisible(true);

                OpenBtn_City.setVisible(false);
                EditBtn_City.setVisible(false);
                CreateBtn_City.setVisible(false);
                Status_Sim.setVisible(true);
                ModeText.setText("Mode: Simulation");
                Status_Sim.setText("Simulator Status: Idle");
                VehicleNum.setText("Total Vehicles: ");
                CityName.setText("City Name: Default City");
                CityName.setVisible(true);
                VehicleNum.setVisible(true);
                Value.setVisible(true);
                City.setVisible(true);

            }
        });
        Btn_City.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                BtnRun.setVisible(false);
                BtnStop.setVisible(false);

                OpenBtn_City.setVisible(true);
                EditBtn_City.setVisible(true);
                CreateBtn_City.setVisible(true);
                Status_Sim.setVisible(true);
                ModeText.setText("Mode: City Editing");
                Status_Sim.setText("Simulator Status: Editing");
                VehicleNum.setText("Total Vehicle: ");
                CityName.setText("City Name: -");
                CityName.setVisible(true);
                VehicleNum.setVisible(true);
                CityName.setText("City Name: -");
                Value.setVisible(false);
            }
        });
        CreateBtn_City.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (city < 5) {
                    City.setVisible(true);
                    JOptionPane j = new JOptionPane();
                    String name = j.showInputDialog("Enter you city name");
                    int roads = Integer.parseInt(j.showInputDialog("Enter number of roads you want "));

                    BtnRun.setVisible(false);
                    BtnStop.setVisible(false);

                    ModeText.setText("Mode: City Editing");
                    Status_Sim.setText("Simulator Status: Editing");
                    VehicleNum.setText("Total Vehicle: ");
                    CityName.setVisible(true);
                    VehicleNum.setVisible(true);
                    Value.setVisible(false);
                    Create create = new Create(name, roads);
                    CityContainer[city] = create;
                    j.showMessageDialog(contentPane, "You created successfully " + name + ".");
                    CityName.setText("City Name : " + name);
                    City.setVisible(false);
                    contentPane.add(CityContainer[city]);
                    for (int i = 0; i < city; i++) {
                        CityContainer[i].setVisible(false);
                    }
                    addTraffic(CityContainer[city].NumRoad, city);
                    CityContainer[city].setVisible(true);
                    city++;

                } else {
                    JOptionPane.showMessageDialog(contentPane, "You can't create cities more then 5");
                }


            }
        });
        EditBtn_City.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OpenBtn_City.setVisible(true);
                EditBtn_City.setVisible(true);
                CreateBtn_City.setVisible(true);

                BtnRun.setVisible(false);
                BtnStop.setVisible(false);

                JOptionPane j = new JOptionPane();
                if (city != 0) {
                    String name = j.showInputDialog("Enter your city name");
                    int i = 0;
                    while (i != city) {
                        if (CityContainer[i].Cityname.equalsIgnoreCase(name)) {
                            int roads = Integer.parseInt(j.showInputDialog("Enter new number of Roads "));
                            CityContainer[i].NumRoad = roads;
                            JOptionPane.showMessageDialog(contentPane, CityContainer[i].Cityname + " Edited succesfully!");
                            for (int m = 0; m < city; m++) {
                                CityContainer[m].setVisible(false);
                            }
                            CityContainer[i].Traffic_Container.clear();
                            addTraffic(roads, i);
                            CityContainer[i].setVisible(true);
                        }
                        i++;
                    }

                } else {
                    JOptionPane.showMessageDialog(contentPane, "City not Found!");

                }
            }

        });
        OpenBtn_City.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (city > 0) {
                    String name = JOptionPane.showInputDialog("Enter your city name");
                    for (int i = 0; i < city; i++) {
                        if (CityContainer[i].Cityname.equalsIgnoreCase(name)) {
                            open = true;
                            index = CityContainer[i].NumRoad;
                            i = i;
                            CityContainer[i].setVisible(true);
                            BtnRun.setVisible(true);
                            BtnStop.setVisible(true);

                            OpenBtn_City.setVisible(false);
                            EditBtn_City.setVisible(false);
                            CreateBtn_City.setVisible(false);
                            ModeText.setText("Mode: Simulation");
                            CityName.setText("City:" + CityContainer[i].Cityname);
                            CityName.setVisible(true);
                            VehicleNum.setVisible(true);
                            Value.setVisible(true);
                            total = 0;

                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(contentPane, "No Cities Found!");
                }
            }
        });

    }

    public void check() {
        for (int j = 0; j < total; j++) {
            if (Vehicle_Containers[j].check) {
                for (int k = j; k < total; k++) {
                    Vehicle_Containers[k] = Vehicle_Containers[k + 1];
                }
                total--;
            }

        }
    }

    public void addTraffic(int index, int i) {
        if (index > 0 & index <= 2) {
        } else if (index > 2 & index <= 4) {
            CityContainer[i].addTraffic(new TrafficLight(310, 117, true, false, true));
            CityContainer[i].addTraffic(new TrafficLight(372, 60, true, false, false));
            CityContainer[i].addTraffic(new TrafficLight(372, 150, true, false, false));
            CityContainer[i].addTraffic(new TrafficLight(395, 117, true, false, true));
        } else if (index > 4 & index <= 6) {
            CityContainer[i].addTraffic(new TrafficLight(310, 117, true, false, true));
            CityContainer[i].addTraffic(new TrafficLight(372, 60, true, false, false));
            CityContainer[i].addTraffic(new TrafficLight(372, 150, true, false, false));
            CityContainer[i].addTraffic(new TrafficLight(395, 117, true, false, true));
            CityContainer[i].addTraffic(new TrafficLight(372, 445, true, false, false));
        } else if (index > 6 & index <= 8) {
            TrafficLight traffic = new TrafficLight(310, 117, true, false, true);
            CityContainer[i].addTraffic(traffic);
            traffic = new TrafficLight(372, 60, true, false, false);
            CityContainer[i].addTraffic(traffic);

            traffic = new TrafficLight(372, 150, true, false, false);
            CityContainer[i].addTraffic(traffic);
            traffic = new TrafficLight(395, 117, true, false, true);
            CityContainer[i].addTraffic(traffic);

            traffic = new TrafficLight(170, 445, true, false, false);
            CityContainer[i].addTraffic(traffic);
            traffic = new TrafficLight(372, 445, true, false, false);
            CityContainer[i].addTraffic(traffic);
            traffic = new TrafficLight(672, 445, true, false, false);
            CityContainer[i].addTraffic(traffic);
        } else {
            City.addTraffic(new TrafficLight(310, 117, true, false, true));
            City.addTraffic(new TrafficLight(372, 60, true, false, false));

            City.addTraffic(new TrafficLight(372, 150, true, false, false));
            City.addTraffic(new TrafficLight(395, 117, true, false, true));

            City.addTraffic(new TrafficLight(170, 445, true, false, false));
            City.addTraffic(new TrafficLight(372, 445, true, false, false));
            City.addTraffic(new TrafficLight(672, 445, true, false, false));
        }
    }

    public void addVehicle(int index, int i) {
        if (index > 0 & index <= 2) {
            Vehicle_Containers[total] = new Bus(804, 135);
            Vehicle_Containers[total].turn3();
            CityContainer[i].addVehicle(Vehicle_Containers[total]);
            total++;
        } else if (index > 2 & index <= 4) {
            int a = (int) (Math.random() * 3);
            if (a == 0) {
                Vehicle_Containers[total] = new Bus(804, 135);
                Vehicle_Containers[total].turn3();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;
            } else if (a == 1) {
                Vehicle_Containers[total] = new Car(354, 0);
                Vehicle_Containers[total].turn1();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;
            }
        } else if (index > 4 & index <= 6) {
            int a = (int) (Math.random() * 5);
            if (a == 0) {
                Vehicle_Containers[total] = new Bus(804, 135);
                Vehicle_Containers[total].turn3();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;
            } else if (a == 1) {
                Vehicle_Containers[total] = new Car(354, 0);
                Vehicle_Containers[total].turn1();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;
            } else if (a == 2) {
                Vehicle_Containers[total] = new Bike(385, 580);
                Vehicle_Containers[total].turn2();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;
            } else if (a == 3) {

                Vehicle_Containers[total] = new Car(0, 404);

                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;
            }
        } else if (index > 6 & index <= 8) {
            int a = (int) (Math.random() * 5);
            if (a == 0) {
                Vehicle_Containers[total] = new Bus(804, 135);
                Vehicle_Containers[total].turn3();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;
            } else if (a == 1) {
                Vehicle_Containers[total] = new Car(354, 0);
                Vehicle_Containers[total].turn1();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;
            } else if (a == 2) {
                Vehicle_Containers[total] = new Bike(385, 580);
                Vehicle_Containers[total].turn2();
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;
            } else if (a == 3) {
                Vehicle_Containers[total] = new Car(0, 404);
                CityContainer[i].addVehicle(Vehicle_Containers[total]);
                total++;
            } else if (a == 4) {
                Vehicle_Containers[total] = new Bike(185, 580);
                Vehicle_Containers[total].turn2();
                City.addVehicle(Vehicle_Containers[total]);
                total++;
            } else if (a == 5) {
                Vehicle_Containers[total] = new Bike(685, 580);
                Vehicle_Containers[total].turn2();
                City.addVehicle(Vehicle_Containers[total]);
                total++;
            }
        } else {
            int a = (int) (Math.random() * 5);
            if (a == 0) {
                Vehicle_Containers[total] = new Bike(185, 580);
                Vehicle_Containers[total].turn2();
                City.addVehicle(Vehicle_Containers[total]);
                total++;
            } else if (a == 1) {
                Vehicle_Containers[total] = new Car(385, 580);
                Vehicle_Containers[total].turn2();
                City.addVehicle(Vehicle_Containers[total]);
                total++;
            } else if (a == 2) {
                Vehicle_Containers[total] = new Bike(685, 580);
                Vehicle_Containers[total].turn2();
                City.addVehicle(Vehicle_Containers[total]);
                total++;
            } else if (a == 3) {
                Vehicle_Containers[total] = new Car(354, 0);
                Vehicle_Containers[total].turn1();
                City.addVehicle(Vehicle_Containers[total]);
                total++;
            } else if (a == 4) {
                Vehicle_Containers[total] = new Bus(810, 135);
                Vehicle_Containers[total].turn3 = true;
                City.addVehicle(Vehicle_Containers[total]);
                total++;
            } else {
                Vehicle_Containers[total] = new Bus(0, 404);
                City.addVehicle(Vehicle_Containers[total]);
                total++;
            }

        }
    }

    public void run() {
        while (running & !open) {
            City.Move();
            City.repaint();
            check();
            Value.setText(String.valueOf(total));
            if (count == 10 & total != 29) {
                addVehicle(-10, 0);
                count = 0;
            }
            count++;

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while (running & open) {
            CityContainer[i].Move();
            CityContainer[i].repaint();
            check();
            Value.setText(String.valueOf(total));
            if (count == 10 & total != 29) {
                addVehicle(index, i);
                count = 0;
            }
            count++;

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(BtnRun)) {
            if (!running) {
                running = true;
                Status_Sim.setText("Simulator Status: Run");
                Thread t = new Thread(this);
                t.start();
            }
        }
        if (event.getSource().equals(BtnStop)) {
            Status_Sim.setText("Simulator Status: Stop");
            running = false;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Traffic_Simulator Frame = new Traffic_Simulator();
                    Frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

