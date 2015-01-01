import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class GameFrame extends JPanel {
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    //g.drawRect(10, 10, 80, 30);
    g.setColor(Color.blue);
    //g.fillRect(10, 10, 80, 30);
    
    int thickness = 4;
 
   // g.fill3DRect(200, 10, 80, 30, true);
   // for (int i = 1; i <= thickness; i++)
     //g.draw3DRect(200, 10, 80, 30, true);
     g.fill3DRect(150, 50, 90, 5, true);
    // g.Rectangle(50, 50, 90, 5);
    }
 
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setTitle("FillRect");
    frame.setSize(500, 300);
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    Container contentPane = frame.getContentPane();
    contentPane.add(new GameFrame());
 
    frame.show();
  }
 
}