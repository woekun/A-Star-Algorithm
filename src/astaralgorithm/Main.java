package astaralgorithm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class Main {

    Timer time;

    public static void main(String[] args) {
        Main testGrid02 = new Main();

    }

    int t = 0;
    int[][] MT = new int[20][20];

    public Main() {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }
            
            JFrame frame = new JFrame("Astar Algorithm");
            frame.setSize(640, 480);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            try {
                frame.add(new TestPane());
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    }

    public class TestPane extends JPanel{

        public CellPane[][] cellPane = new CellPane[20][20];

        public TestPane() throws InterruptedException {
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            
            for (int row = 0; row < 20; row++) {
                for (int col = 0; col < 20; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;

                    cellPane[row][col] = new CellPane(row, col);
                    Border border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    cellPane[row][col].setBorder(border);
                    add(cellPane[row][col], gbc);
                }
            }
            gbc.gridx = 21;
            gbc.gridy = 2;
            gbc.gridheight = 2;
            JButton bt = new JButton("Run");
            bt.addActionListener((ActionEvent e) -> {

                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {
                        if (cellPane[i][j].getBackground() == Color.RED) 
                            MT[i][j] = 2;
                        if (cellPane[i][j].getBackground() == Color.GREEN)
                            MT[i][j] = 3;
                        if (cellPane[i][j].getBackground() == Color.DARK_GRAY)
                            MT[i][j] = 1;
                    }
                }

                AstarAlgorithm astar = new AstarAlgorithm(MT);
               
                Timer timer = new Timer(1000, (ActionEvent e1) -> {
                    astar.create_Trace(cellPane);
                });

                timer.start();
                
            });

            add(bt, gbc);

            cellPane[0][0].setBackground(Color.red);
            cellPane[0][1].setBackground(Color.green);

        }
    }

    public class CellPane extends JPanel {

        private Color defaultBackground = getBackground();
        private final int gtrix, gtriy;

        public CellPane(int a, int b) {
            gtrix = a;
            gtriy = b;

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == 1 && getBackground() == defaultBackground) {
                        t = 1;
                        setBackground(Color.DARK_GRAY);
                    }
                    if (e.getButton() == 3 && getBackground() == Color.DARK_GRAY) {
                        t = 2;
                        setBackground(defaultBackground);
                    }
                    if (e.getButton() == 1 && getBackground() == Color.RED) {
                        t = 3;
                    }
                    if (e.getButton() == 1 && getBackground() == Color.GREEN) {
                        t = 4;
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    t = 0;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (t == 1 && getBackground() == defaultBackground) {
                        setBackground(Color.DARK_GRAY);
                    }
                    if (t == 2 && getBackground() == Color.DARK_GRAY) {
                        setBackground(defaultBackground);
                    }
                    if (t == 3 && getBackground() == defaultBackground) {
                        setBackground(Color.RED);
                    }
                    if (t == 4 && getBackground() == defaultBackground) {
                        setBackground(Color.GREEN);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (t == 3 && getBackground() == Color.RED) {
                        setBackground(defaultBackground);
                    }
                    if (t == 4 && getBackground() == Color.GREEN) {
                        setBackground(defaultBackground);
                    }
                }
            });
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(30, 30);
        }
    }
}
