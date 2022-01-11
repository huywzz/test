package test;

import javax.swing.table.DefaultTableModel;

import Connect.ConnectDB;
import Model.ChiTietHoaDon;
import Model.LoaiNuoc;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JLabel lbID,lbTensp, lbNgay,lbSoLuong,lbTongTien,lbTieuDe,tongtien;
	private JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8,lb9,lb10,lb11;
	private JComboBox cbTensp;
	private JTextField tfID, tfNgay,tfSoLuong,tfThanhTien;
	private JButton btnThem, btnXoa, btnSua,btnReload,btnLuu;
	private JTable tbOrder;
	JScrollPane  spOrder;
	JPanel tb,pnChucNang,pnBtn,pnTieude,pnUnder;
	//
	JPanel pnCongCu,pnTable;
	JScrollPane  spHoaDon;
	JLabel lbDate,lbTongCong,lbGiaTri;
	JTextField tfDay,tfMonth,tfYear;
	JButton btnSearch;
	JTable tbHoaDon;
	long tongCong=0;
	static Vector<ChiTietHoaDon> vCTHD= new Vector<ChiTietHoaDon>();	
	public ThongKeView(){
		this.gui();
		this.setVisible(true);
	}
	
	public void gui() {
		this.setLocation(200,50);
		this.setSize(600,550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(pnTieude(),BorderLayout.NORTH);
		this.add(pnCongCu(),BorderLayout.CENTER);
		this.add(pnBang(),BorderLayout.SOUTH);
	}
	
	public JPanel pnTieude() {
		pnTieude= new JPanel();
		lbTieuDe= new JLabel("Thống kê", SwingConstants.CENTER);
		lbTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnTieude.setLayout(new BorderLayout());
		pnTieude.add(lbTieuDe,BorderLayout.CENTER);
		return pnTieude;
	}
	
	public JPanel pnCongCu() {
		 pnCongCu= new JPanel();
		 pnCongCu.setLayout(new FlowLayout());
		 
		 lbDate = new JLabel("DD/MM/YYYY:");
		 tfDay= new JTextField(2);
		 tfMonth= new JTextField(2);
		 tfYear= new JTextField(4);
		 btnSearch= new JButton("Tìm kiếm");
		 btnSearch.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e){
						pnCongCu.add(pnBangSearch(),BorderLayout.NORTH);
						pnCongCu.revalidate();
					}
				});
		 pnCongCu.add(lbDate);
		 pnCongCu.add(tfDay);
		 pnCongCu.add(tfMonth);
		 pnCongCu.add(tfYear);
		 pnCongCu.add(btnSearch);
		 return pnCongCu;
	}
	public JPanel pnBang() {
		tb= new JPanel();
		tb.setLayout(new BorderLayout());
		displayHoaDon();
		spHoaDon= new JScrollPane(tbHoaDon);
		tb.add(spHoaDon,BorderLayout.CENTER);
		lbTongCong= new JLabel("Tổng cộng");
		lbGiaTri= new JLabel("0");
		JPanel pnTC= new JPanel();
			pnTC.setLayout(new FlowLayout());
			pnTC.add(lbTongCong);
			pnTC.add(lbGiaTri);
		tb.add(pnTC,BorderLayout.SOUTH);
		return tb;
	}
	public JPanel pnBangSearch() {
		tb= new JPanel();
		tb.setLayout(new BorderLayout());
		seacrhHoaDon();
		spHoaDon= new JScrollPane(tbHoaDon);
		tb.add(spHoaDon,BorderLayout.NORTH);
		return tb;
	}

	// tìm kiếm 
	public void seacrhHoaDon() {
		ConnectDB conn= new ConnectDB();
		String sql="select * from HOADON where YEAR(HOADON.NGAY)="+tfYear.getText()+"";
		if(tfDay.getText().equals(" ")&&tfMonth.getText().equals(" ")) {
			sql="select * from HOADON where YEAR(HOADON.NGAY)="+tfYear.getText()+"";
		}
		else if(tfDay.getText().equals(" ")) {
			sql="select * from HOADON where YEAR(HOADON.NGAY)="+tfYear.getText()+" and MONTH(HOADON.NGAY)="+tfMonth.getText()+" ";
		}else if(tfDay.getText().length()>0 && tfMonth.getText().length()>0&&tfYear.getText().length()>0){
			sql="select * from HOADON where YEAR(HOADON.NGAY)="+tfYear.getText()+" and MONTH(HOADON.NGAY)="+tfMonth.getText()+"and DAY(HOADON.NGAY)= "+tfDay.getText()+" ";
		}
		tm.setRowCount(0);
		rs= conn.ListNuoc(sql);
		String col[]= {"Ma hoa don","Ngay","Tong tien"};
		tm= new DefaultTableModel(col,0);
		try {
			while(rs.next()) {
				Object data[]= {rs.getString("MAHD"),rs.getString("NGAY"),rs.getInt("TONGTIEN")};
				//tbHoaDon= new JTable(tm);
				tongCong=tongCong+rs.getInt("TONGTIEN");
				tbHoaDon.setModel(tm);
				tm.addRow(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		lbGiaTri.setText(tongCong+"");
	}
	
	//---------------------------------------------------------------------------
	//đưa dữ liệu lên table 
	public void displayHoaDon() {
		ConnectDB conn= new ConnectDB();
		rs= conn.ListNuoc("select * from HOADON, CHITIETHOADON where HOADON.MAHD=CHITIETHOADON.MAHD");
		String col[]= {"Ma hoa don","Ngay","Tong tien"};
		tm= new DefaultTableModel(col,0);
		try {
			while(rs.next()) {
				Object data[]= {rs.getString("MAHD"),rs.getString("NGAY"),rs.getInt("TONGTIEN")};
				tbHoaDon= new JTable(tm);
				tm.addRow(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
        // TODO Auto-generated method stub
        //new FacebookForm();
        new ThongKeView();

    }
}
