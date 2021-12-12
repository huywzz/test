import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class testJFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame= new JFrame("Giao Dien");// tao 1 doi tuong cua lop JFrame
		JButton b= new JButton("Click");// tao 1 doi tuong cua lop Jbutton
		b.setBounds(0,0,0,0);// chinh sua vi tri cua button
	    frame.add(b);//them vao` frame 
	    JTextArea area= new JTextArea("CHinh sua");//tạo 1 vùng hiện thị nhiều nhiều dòng, có thể chính sửa đc
	    area.setBounds(10,30, 300,300);
	    frame.add(area);
		frame.setSize(400,300);// thay đổi diện tích của app
		frame.setLayout(null);
	    frame.setVisible( true );//giong in ra ma hinh
	    
	    
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
