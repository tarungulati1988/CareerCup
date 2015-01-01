import java.awt.event.*;
import javax.swing.*;

class EmptyFrame1 extends JFrame {

  // Constructor:
  public EmptyFrame1() {
	setTitle("My Closeable Frame");
	setSize(300,200); // default size is 0,0
	setLocation(10,200); // default is 0,0 (top left corner)

	// Window Listeners
	addWindowListener(new WindowAdapter() {
	  	public void windowClosing(WindowEvent e) {
		   System.exit(0);
	  	} //windowClosing
	} );
  } 

  public static void main(String[] args) {
    JFrame f = new EmptyFrame1();
    f.show();
  } //main
} //class EmptyFrame1