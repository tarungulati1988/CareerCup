
import javax.swing.*;

class BrickFrame extends JFrame {
  public BrickFrame() {
	setTitle("My Empty Frame");
	addWindowListener( new Terminator());
	setSize(300,200); // default size is 0,0
	setLocation(10,200); // default is 0,0 (top left corner)
	
  }

  public static void main(String[] args) {
    JFrame f = new BrickFrame();
    f.show();
  }
}