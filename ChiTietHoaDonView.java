package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;

import Connect.ConnectDB;
public class ChiTietHoaDonView extends JFrame{
	private JLabel lbCTHD;
	JTable tbCT;
	JPanel pnBang;
	JScrollPane spCT;
	DefaultTableModel tm;
	ResultSet rs;
	public ChiTietHoaDonView(String maHD) {
		this.setLocation(550,200);
		this.setSize(500,350);
//		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		lbCTHD= new JLabel("Chi tiet hoa don "+maHD,SwingConstants.CENTER);
		
		pnBang= new JPanel();
			pnBang.setLayout(new GridLayout(1,1));
			ctHD(maHD);
			spCT= new JScrollPane(tbCT);
			pnBang.add(spCT);
		
		this.add(lbCTHD,BorderLayout.NORTH);
		this.add(pnBang,BorderLayout.CENTER);
		this.setVisible(true);
	}
	public void ctHD(String maHD) {
		ConnectDB conn= new ConnectDB();
		rs= conn.ListNuoc("	select	DISTINCT	HOADON.MAHD, SANPHAM.MASP,TENSP,NGAY,SOLUONG,CHITIETHOADON.THANHTIEN from HOADON, CHITIETHOADON,SANPHAM \r\n"
				+ "where SANPHAM.MASP=CHITIETHOADON.MASP AND CHITIETHOADON.MAHD=HOADON.MAHD and HOADON.MAHD='"+maHD+"'");
		String col[]= {"Ma hoa don","Ma san pham","Ten san pham","So luong","Ngay","Thanh tien"};
		tm= new DefaultTableModel(col,0);
		try {
			while(rs.next()) {
				Object data[]= {rs.getString("MAHD"),rs.getString("MASP"),rs.getString("TENSP"),rs.getInt("SOLUONG"),rs.getString("NGAY"),rs.getInt("THANHTIEN")};
				tbCT= new JTable(tm);
				tm.addRow(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
