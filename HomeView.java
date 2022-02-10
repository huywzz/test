package test;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HomeView extends JFrame{
	private JPanel pnTieuDe,pnBtn,pnBig;
	private JLabel lbTieuDe,lb1,lb2,lb3;
	private JButton btnThucUong,btnOrder,btnThongKe;
	public HomeView() {
		this.gui();
		this.setVisible(true);
	}
	public void gui() {
		this.setLocation(400,150);
		this.setSize(800,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(pnBig());
	}
	public JPanel pnBig() {
		pnBig= new JPanel(new GridLayout(2,1));
		pnBig.add(pnTieuDe(),BorderLayout.NORTH);
		pnBig.add(pnBtn(),BorderLayout.CENTER);
		return pnBig;
	}
	public JPanel pnTieuDe() {
		pnTieuDe= new JPanel(new GridLayout(4,1));
		lbTieuDe= new JLabel("              Quản lí doanh số bán hàng của quán cafe");
		lb1= new JLabel(" ");
		lb2= new JLabel(" ");
		lb3= new JLabel(" ");
		lbTieuDe.setFont(new Font("Roboto Slab ExtraBold", Font.PLAIN, 30));
		lbTieuDe.setForeground(Color.blue);
		pnTieuDe.add(lbTieuDe);
		pnTieuDe.add(lb1);
		pnTieuDe.add(lb2);
		pnTieuDe.add(lb3);
		return pnTieuDe;
	}
	public JPanel pnBtn() {
		pnBtn=new JPanel(new FlowLayout());
		btnThucUong= new JButton("Quản lí thức uống ");
		btnThucUong.setForeground(Color.white);
		btnThucUong.setBackground(Color.red);
		btnThucUong.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
					eventThucUong();
				}
			});
		btnOrder= new JButton("Order");
		btnOrder.setForeground(Color.white);
		btnOrder.setBackground(Color.blue);
		btnOrder.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
					eventOrder();
				}
			});
		btnThongKe= new JButton("Thống kê ");
		btnThongKe.setForeground(Color.white);
		btnThongKe.setBackground(Color.gray);
		btnThongKe.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
					eventThongKe();
				}
			});
		pnBtn.add(btnThucUong);
		pnBtn.add(btnOrder);
		pnBtn.add(btnThongKe);
		return pnBtn;
	}
	public void eventThucUong() {
		this.setVisible(false);
		ThucUongView tu= new ThucUongView();
	}
	public void eventOrder() {
		this.setVisible(false);
		testThem tt= new testThem();
	}
	public void eventThongKe() {
		this.setVisible(false);
		ThongKeView tKeView = new ThongKeView();
	}
	public static void main(String[] args) {
        // TODO Auto-generated method stub
        new HomeView();
    }

}
