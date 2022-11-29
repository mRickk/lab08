package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.*;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final int SIZE_PROPORTION = 3;
    private final JFrame frame;

    private SimpleGUI(final Controller controller) {
        frame = new JFrame();

        final JPanel panel = new JPanel();
        final JTextArea text = new JTextArea();
        final LayoutManager layout = new BorderLayout();
        panel.setLayout(layout);
        panel.add(text, BorderLayout.NORTH);
        final JButton save = new JButton("Save");
        panel.add(save, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.writeOnFile(text.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final double screenWidth = screen.getWidth();
        final double screenHeight = screen.getHeight();
        frame.setSize((int) (screenWidth / SIZE_PROPORTION), (int) (screenHeight / SIZE_PROPORTION));
        frame.setLocationByPlatform(true); 
    }

    private void display() {
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SimpleGUI gui = new SimpleGUI(new Controller());
        gui.display();
    }
}
