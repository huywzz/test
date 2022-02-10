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

import com.microsoft.sqlserver.jdbc.dataclassification.Label;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;	
import Connect.ConnectDB;
import Model.LoaiNuoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.*;
public class ThucUongView extends JFrame {
	private ResultSet rs;
	private DefaultTableModel tm;
	private JLabel lbID,lbTen, lbLoai,lbGia,lbTieuDe,lb1,lb2,lb3;
	JLabel bt1,bt2,bt3;
	private JTextField tfID, tfTen, tfLoai,tfGia;
	private JButton btnThem, btnXoa, btnSua,btnReload,btnHome;
	private JTable tbThucUong;
	JPanel temp;
	JPanel pnChucNang,pnBtn,pnUnder,pnTieuDe,pnBang,pnBig,pnHome;
	JScrollPane sp;
	public ThucUongView() {
		this.gui();
		this.setVisible(true);
	}
	public void gui() {
		this.setLocation(500,100);
		this.setSize(600,650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(pnBig());
		
	}
	public JPanel pnBig() {
		pnBig= new JPanel();
		pnBig.setLayout(new BorderLayout());
		
		pnBig.add(pnTieuDe(),BorderLayout.NORTH);
		
		pnBig.add(pnUnder(),BorderLayout.CENTER);
		lb3= new JLabel("   ");
		pnBig.add(lb3 ,BorderLayout.SOUTH);
		return pnBig;
	}
	public JPanel pnChucNang() {
		 pnChucNang= new JPanel();
		lbID= new JLabel("     ID");
		tfID= new JTextField(20);
		lbTen= new JLabel("    Tên nước");
		tfTen= new JTextField(20);
		lbLoai= new JLabel("     Loại nước");
		tfLoai= new JTextField(20);
		lbGia= new JLabel("     Giá");
		tfGia= new JTextField(20);
		pnChucNang.setLayout(new GridLayout(4,2,20,11));
		pnChucNang.add(lbID);
		pnChucNang.add(tfID);
		
		pnChucNang.add(lbTen);
		pnChucNang.add(tfTen);
		
		pnChucNang.add(lbLoai);
		pnChucNang.add(tfLoai);
		
		pnChucNang.add(lbGia);
		pnChucNang.add(tfGia);
		return pnChucNang;
	}
	
	public JPanel pnBtn() {
		pnBtn= new JPanel();
		lb1= new JLabel("                                ");
		bt1= new JLabel("    ");
		bt2= new JLabel(" ");
		bt3= new JLabel("  ");
		btnHome= new JButton("HOME");
		btnHome.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
					eventHome();
				}
			});
		pnBtn.setLayout(new GridLayout(4,2,20,10));
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(Color.red);
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBackground(Color.blue);
		btnSua.setForeground(Color.WHITE);
		btnSua.setBackground(Color.MAGENTA);
		pnBtn.add(btnThem);
		pnBtn.add(bt1);
		pnBtn.add(btnXoa);
		pnBtn.add(bt2);
		pnBtn.add(btnSua);
		pnBtn.add(bt3);
		pnBtn.add(btnHome);
		return pnBtn;
	}
	
	public JPanel pnUnder() {
		pnUnder= new  JPanel();
		pnUnder.setLayout(new BorderLayout());
		pnUnder.setLayout(new GridLayout(1,3,-90,10));
		lb1= new JLabel("                                        ");
		pnUnder.add(pnChucNang(),BorderLayout.WEST);
		pnUnder.add(lb1,BorderLayout.CENTER);
		pnUnder.add(pnBtn(),BorderLayout.EAST);
		return pnUnder;
	}
	
	public JPanel pnTieuDe() {
		pnTieuDe= new JPanel();
		lbTieuDe= new JLabel("Thức uống", SwingConstants.CENTER);
		
		lbTieuDe.setFont(new Font("Roboto Slab ExtraBold", Font.PLAIN, 25));
		lbTieuDe.setForeground(Color.blue);
		pnTieuDe.setLayout(new BorderLayout());
		btnThem = new JButton("Them");
		btnThem.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
					eventThem();
					
//					pnTieuDe.add(temp,BorderLayout.CENTER);
//					pnTieuDe.revalidate();
					temp.removeAll();
					temp.add(pnBang());
					temp.revalidate();
					temp.repaint();
				}
			});
		btnXoa = new JButton("Xoa");
		btnXoa.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
					eventXoa();
					temp.removeAll();
					temp.add(pnBang());
					temp.revalidate();
					temp.repaint();
				}
			});
		btnSua = new JButton("Sua");
		btnSua.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
					eventCapNhat();
					temp.removeAll();
					temp.add(pnBang());
					temp.revalidate();
					temp.repaint();
				}
			});
		temp= new JPanel(new BorderLayout(1,1));
		temp.add(pnBang());
		pnTieuDe.add(lbTieuDe,BorderLayout.NORTH);
		pnTieuDe.add(temp,BorderLayout.CENTER);
		return pnTieuDe;
	}
	
	public JPanel pnBang() {
		pnBang= new JPanel(new GridLayout(1,1));
		display();
		sp= new JScrollPane(tbThucUong);
		pnBang.add(sp);
		tbThucUong.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//String tensp=tm.getValueAt(tbOrder.getSelectedRow(), 0).toString();
				//long soluong=Long.parseLong(tm.getValueAt(tbOrder.getSelectedRow(), 1).toString());
				int row=tbThucUong.getSelectedRow();
				tfID.setText(tbThucUong.getValueAt(row, 0).toString());
				tfTen.setText(tbThucUong.getValueAt(row, 1).toString());
				tfLoai.setText(tbThucUong.getValueAt(row, 2).toString());
				tfGia.setText(tbThucUong.getValueAt(row, 3).toString());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			
		});
		
		return pnBang;
	}
	
	public void eventThem() {
		try {
			ConnectDB conn=new ConnectDB();
			int record = conn.executeDB("insert into SANPHAM values ('"+tfID.getText()+"','"+tfTen.getText()+"','"+tfLoai.getText()+"','"+tfGia.getText()+"')");
			tfID.setText("");
			tfTen.setText("");
			tfLoai.setText("");
			tfGia.setText("");
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	}
	public void eventXoa() {
		try {
			ConnectDB conn=new ConnectDB();
			int record = conn.executeDB("delete from  SANPHAM   where TENSP='"+tfTen.getText()+"'");
			tm = (DefaultTableModel)tbThucUong.getModel();
			tfID.setText("");
			tfTen.setText("");
			tfLoai.setText("");
			tfGia.setText("");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void eventCapNhat() {
		try {
			ConnectDB conn=new ConnectDB();
			int record = conn.executeDB("update SANPHAM set MASP ='"+tfID.getText()+"',TENSP='"+tfTen.getText()+"', LOAISP='"+tfLoai.getText()+"', GIA="+tfGia.getText()+" where MASP='"+tfID.getText()+"'");
			tfID.setText("");
			tfTen.setText("");
			tfLoai.setText("");
			tfGia.setText("");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	}
	public void display() {
		ConnectDB conn= new ConnectDB();
		rs= conn.ListNuoc("Select * from SANPHAM");
		String col[]= {"Mã sản phẩm","Tên sản phẩm","Loại sản phẩm","Giá"};
		tm= new DefaultTableModel(col,0);
		try {
			while(rs.next()) {
				Object data[]= {rs.getString("MASP"),rs.getString("TENSP"),rs.getString("LOAISP"),rs.getInt("GIA")};
				tbThucUong= new JTable(tm);
				tm.addRow(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public void eventHome() {
		this.setVisible(false);
		HomeView homeView= new HomeView();
	}
//	public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        new ThucUongView();
//
//    }
	
}
