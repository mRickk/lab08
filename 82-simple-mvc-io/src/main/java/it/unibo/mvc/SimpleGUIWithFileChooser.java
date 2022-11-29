package it.unibo.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final int SIZE_PROPORTION = 2;
    private final JFrame frame;

    private SimpleGUIWithFileChooser(final Controller controller) {
        frame = new JFrame("SimpleGUIWithFileChooser");
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JButton save = new JButton("Save");
        JButton browse = new JButton("Browse...");
        JTextField text = new JTextField();
        JTextField path = new JTextField(controller.getFilePath());
        path.setEditable(false);
        LayoutManager layout1 = new BorderLayout();
        LayoutManager layout2 = new BorderLayout();
        panel1.setLayout(layout1);
        panel2.setLayout(layout2);
        panel1.add(text, BorderLayout.CENTER);
        panel1.add(save, BorderLayout.SOUTH);
        panel2.add(path, BorderLayout.CENTER);
        panel2.add(browse, BorderLayout.LINE_END);
        panel1.add(panel2, BorderLayout.NORTH);
        frame.getContentPane().add(panel1);
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
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser("Select your file...");
                fileChooser.setSelectedFile(controller.getFile());
                int res = fileChooser.showSaveDialog(frame);
                switch (res) {
                    case JFileChooser.APPROVE_OPTION:
                        controller.setFile(fileChooser.getSelectedFile());
                        path.setText(controller.getFilePath()); 
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, res , "Error", JOptionPane.ERROR_MESSAGE);
                }                
            }
        });

        final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        final double sw = screenDimension.getWidth();
        final double sh = screenDimension.getHeight();
        frame.setSize((int) (sw / SIZE_PROPORTION), (int) (sh / SIZE_PROPORTION));
        frame.setLocationByPlatform(true);
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
    }

}
