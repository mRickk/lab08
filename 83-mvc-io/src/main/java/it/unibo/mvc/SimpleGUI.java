package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private final int PROPORTION = 2;

    private SimpleGUI(final Controller cntrl) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JTextField output = new JTextField();
        JTextArea input = new JTextArea();
        JButton print = new JButton("Print");
        JButton history = new JButton("Show history");

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cntrl.setString(input.getText());
                    cntrl.print();
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }         
        });
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final List<String> temp = cntrl.showHistory();
                    String historyString = "";
                    for (String s : temp) {
                        historyString += s + "\n";
                    }
                    output.setText(historyString);
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(output, BorderLayout.NORTH);
        panel.add(input, BorderLayout.CENTER);
        panel.add(print, BorderLayout.SOUTH);
        panel.add(history, BorderLayout.LINE_END);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final double sw = screenSize.getWidth();
        final double sh = screenSize.getHeight();
        frame.setSize((int) (sw / PROPORTION), (int) (sh / PROPORTION));
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SimpleGUI gui = new SimpleGUI(new SimpleController());
        gui.display();
    }
}
