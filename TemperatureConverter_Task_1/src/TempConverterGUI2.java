import java.awt.*;
import javax.swing.*;

public class TempConverterGUI2 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Temperature Converter");
        frame.setSize(420,320);
        frame.setLayout(null);

        frame.getContentPane().setBackground(new Color(240,240,240));

        JLabel label = new JLabel("Enter Temperature:");
        label.setBounds(40,40,150,30);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JTextField input = new JTextField();
        input.setBounds(200,40,140,30);
        input.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        String units[] = {"Celsius","Fahrenheit","Kelvin"};

        JComboBox<String> unitBox = new JComboBox<>(units);
        unitBox.setBounds(140,90,140,30);
        unitBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton convertBtn = new JButton("Convert");
        convertBtn.setBounds(150,140,120,35);
        convertBtn.setBackground(new Color(70,130,180));
        convertBtn.setForeground(Color.WHITE);
        convertBtn.setFocusPainted(false);
        convertBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel result1 = new JLabel("");
        result1.setBounds(120,200,250,25);
        result1.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel result2 = new JLabel("");
        result2.setBounds(120,230,250,25);
        result2.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        convertBtn.addActionListener(e -> {

            try {

                double temp = Double.parseDouble(input.getText());
                String unit = unitBox.getSelectedItem().toString();

                double c, f, k;

                if(unit.equals("Celsius")) {

                    c = temp;
                    f = (temp * 9/5) + 32;
                    k = temp + 273.15;

                    result1.setText("Fahrenheit: " + String.format("%.2f", f));
                    result2.setText("Kelvin: " + String.format("%.2f", k));
                }

                else if(unit.equals("Fahrenheit")) {

                    f = temp;
                    c = (temp - 32) * 5.0/9;
                    k = c + 273.15;

                    result1.setText("Celsius: " + c);
                    result2.setText("Kelvin: " + k);
                }

                else {

                    k = temp;
                    c = temp - 273.15;
                    f = (c * 9/5) + 32;

                    result1.setText("Celsius: " + c);
                    result2.setText("Fahrenheit: " + f);
                }

            }

            catch(NumberFormatException ex) {

                JOptionPane.showMessageDialog(frame,
                        "Please enter a valid numeric temperature value.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);

            }

        });

        frame.add(label);
        frame.add(input);
        frame.add(unitBox);
        frame.add(convertBtn);
        frame.add(result1);
        frame.add(result2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}