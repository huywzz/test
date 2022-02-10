package test;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet.ColorAttribute;

import Connect.ConnectDB;
import Model.ChiTietHoaDon;
import Model.LoaiNuoc;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;


public class ThongKeView extends JFrame {
	private ResultSet rs;
	private DefaultTableModel tm;
	private JLabel lbTieuDe,lb1,lb2,lb3;
	JPanel tb,pnChucNang,pnBtn,pnTieude,pnUnder;
	JPanel pnCongCu,pnTable,pnBig,pnNorth;
	JScrollPane  spHoaDon;
	JLabel lbDate,lbTongCong,lbGiaTri;
	JTextField tfDay,tfMonth,tfYear;
	JButton btnSearch,btnHome;
	JTable tbHoaDon;
	JPanel temp;
	long tongCong=0;
	static Vector<ChiTietHoaDon> vCTHD= new Vector<ChiTietHoaDon>();	
	public ThongKeView(){
		this.gui();
		this.setVisible(true);
	}
	public void gui() {
		this.setLocation(500,100);
		this.setSize(600,550);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.add(pnBig());
		
	}
	public JPanel pnBig() {
		pnBig= new JPanel();
		pnBig.setLayout(new BorderLayout());
//		pnBig.add(pnTieude(),BorderLayout.NORTH);
//		pnBig.add(pnCongCu(),BorderLayout.CENTER);
		pnBig.add(pnNorth(),BorderLayout.NORTH);
		temp= new JPanel( new BorderLayout(1,1));
		temp.add(pnBang());
		pnBig.add(temp,BorderLayout.CENTER);
		 btnSearch.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e){
						seacrhHoaDon();
						temp.removeAll();
//						pnBig.add(pnBangSearch(),BorderLayout.CENTER);
//						
//						pnBig.revalidate();
						temp.add(pnBangSearch());
						temp.revalidate();
						temp.repaint();
					
					}
				});
		pnBig.add(pnTC(), BorderLayout.SOUTH);
		
		return pnBig;
	}
//	
	public JPanel pnTieude() {
		pnTieude= new JPanel();
		lbTieuDe= new JLabel("Thống kê", SwingConstants.CENTER);
		lbTieuDe.setFont(new Font("Roboto Slab ExtraBold", Font.PLAIN, 25));
		lbTieuDe.setForeground(Color.blue);
		pnTieude.setLayout(new BorderLayout());
		pnTieude.add(lbTieuDe,BorderLayout.CENTER);
		return pnTieude;
	}
	public JPanel pnCongCu() {
		 pnCongCu= new JPanel();
		 pnCongCu.setLayout(new FlowLayout());
		 lbDate = new JLabel("DD/MM/YYYY:");
		 lb1=new JLabel("           ");
		 lb2= new JLabel("                                     ");
		 tfDay= new JTextField(2);
		 tfMonth= new JTextField(2);
		 tfYear= new JTextField(4);
		 btnSearch= new JButton("Tìm kiếm");
		 btnSearch.setForeground(Color.white);
		 btnSearch.setBackground(Color.DARK_GRAY);
		 btnHome= new JButton("HOME");
		 btnHome.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e){
						eventHome();
					}
				});
		 pnCongCu.add(lbDate);
		 pnCongCu.add(tfDay);
		 pnCongCu.add(tfMonth);
		 pnCongCu.add(tfYear);
		 pnCongCu.add(btnSearch);
		 pnCongCu.add(btnHome);
		 pnCongCu.add(lb1);
		 pnCongCu.add(lb2);
		 return pnCongCu;
	}
	public JPanel pnNorth() {
		pnNorth= new JPanel(new BorderLayout());
		pnNorth.add(pnTieude(),BorderLayout.NORTH);
		pnNorth.add(pnCongCu(),BorderLayout.CENTER);
		return pnNorth;
	}
	public JPanel pnBang() {
		tb= new JPanel(new GridLayout(1,1));

		displayHoaDon(); 
		//seacrhHoaDon();

		spHoaDon= new JScrollPane(tbHoaDon);
		tb.add(spHoaDon);
		
		lbTongCong= new JLabel("Tổng cộng");
		lbGiaTri= new JLabel("0");
		return tb;
	}
	public JPanel pnTC() {
		JPanel pn = new JPanel(new FlowLayout());
		pn.add(lbTongCong);
		pn.add(lbGiaTri);
		return pn;
		
		
	}
	public JPanel pnBangSearch() {
		tb= new JPanel(new GridLayout(1,1));
		seacrhHoaDon();
		spHoaDon= new JScrollPane(tbHoaDon);
		tb.add(spHoaDon);
		tbHoaDon.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//String tensp=tm.getValueAt(tbOrder.getSelectedRow(), 0).toString();
				//long soluong=Long.parseLong(tm.getValueAt(tbOrder.getSelectedRow(), 1).toString());
				int row=tbHoaDon.getSelectedRow();
				String maHD= tbHoaDon.getValueAt(row, 0).toString();
				ChiTietHoaDonView ct= new ChiTietHoaDonView(maHD);
				
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
		return tb;
	}

	// tìm kiếm 
	public void seacrhHoaDon() {
		ConnectDB conn= new ConnectDB();
		String sql="select * from HOADON";
		if(tfDay.getText().length()==0&&tfMonth.getText().length()==0) {
			sql=sql+" where YEAR(HOADON.NGAY)='"+tfYear.getText()+"'";
		}
		else if(tfDay.getText().length()==0) {
			sql="select * from HOADON where YEAR(HOADON.NGAY)='"+tfYear.getText()+"' and MONTH(HOADON.NGAY)='"+tfMonth.getText()+"'";
		}else if(tfDay.getText().length()>0 && tfMonth.getText().length()>0&&tfYear.getText().length()>0){
			sql="select * from HOADON where YEAR(HOADON.NGAY)='"+tfYear.getText()+"' and MONTH(HOADON.NGAY)='"+tfMonth.getText()+"' and DAY(HOADON.NGAY)= '"+tfDay.getText()+" '";
		}
		//tm.setRowCount(0);
		rs= conn.ListNuoc(sql);
		String col[]= {"Ma hoa don","Ngay","Tong tien"};
		Vector data = null;
		tm= new DefaultTableModel(col,0);
		try {
			while(rs.next()) {
//				Object data[]= {rs.getString("MAHD"),rs.getString("NGAY"),rs.getInt("TONGTIEN")};
				data = new Vector();
				data.add(rs.getString("MAHD"));
				data.add(rs.getString("NGAY"));
				data.add(rs.getString("TONGTIEN"));
//				
				tongCong=tongCong+rs.getInt("TONGTIEN");
				tm.addRow(data);
			}
			tbHoaDon= new JTable();
			tbHoaDon.setModel(tm);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		lbGiaTri.setText(tongCong+"");
		tongCong=0;
	}
	
	//---------------------------------------------------------------------------
	//đưa dữ liệu lên table 
	public void displayHoaDon() {
		ConnectDB conn= new ConnectDB();
		rs= conn.ListNuoc("select * from HOADON");
		String col[]= {"Ma hoa don","Ngay","Tong tien"};
		tm= new DefaultTableModel(col,0);
		try {
			while(rs.next()) {
				Object data[]= {rs.getString("MAHD"),rs.getString("NGAY"),rs.getInt("TONGTIEN")};
				tbHoaDon= new JTable(tm);
				tm.addRow(data);
				tm.fireTableDataChanged();
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
//        new ThongKeView();
//    }
}
