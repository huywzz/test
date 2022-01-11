package test;


import javax.swing.JFrame;
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
public class testThem extends JFrame {
	private ResultSet rs;
	private DefaultTableModel tm;
	private JLabel lbID,lbTensp, lbNgay,lbSoLuong,lbTongTien,lbTieuDe,tongtien;
	private JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8,lb9,lb10,lb11;
	private JComboBox cbTensp;
	private JTextField tfID, tfNgay,tfSoLuong,tfThanhTien;
	private JButton btnThem, btnXoa, btnSua,btnReload,btnLuu;
	private JTable tbHoaDon,tbOrder;
	JScrollPane  spOrder;
	JPanel tb,pnChucNang,pnBtn,pnTieude,pnUnder;
	static Vector<ChiTietHoaDon> vCTHD= new Vector<ChiTietHoaDon>();
//	Vector<String> vHeaderCT= new  Vector<String>();
	
	public testThem() {
		this.gui();
		this.setVisible(true);
	}
	
	public void gui() {
		this.setLocation(200,50);
		this.setSize(600,750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(pnTieude(),BorderLayout.NORTH);
		this.add(pnUnder(),BorderLayout.CENTER);
	}
	
	public JPanel pnTieude() {
		pnTieude= new JPanel();
		lbTieuDe= new JLabel("Order", SwingConstants.CENTER);
		lbTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnTieude.setLayout(new BorderLayout());
		pnTieude.add(lbTieuDe,BorderLayout.CENTER);
		return pnTieude;
	}
	
	public JPanel pnBtn() {
		 pnBtn= new JPanel();
		pnBtn.setLayout(new BoxLayout(pnBtn,BoxLayout.Y_AXIS));
			btnLuu= new JButton("Lưu");
			btnLuu.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e){
					LuuHoaDon();
					}
				});
			
			lb5= new JLabel("           ");
			lb6= new JLabel("           ");
			lb7= new JLabel("           ");
			lb4= new JLabel("           ");
			lb8= new JLabel("           ");
			lb9= new JLabel("           ");
			lb10= new JLabel("           ");
			lb11= new JLabel("           ");
			pnBtn.add(btnThem);
			pnBtn.add(btnSua);
			pnBtn.add(btnXoa);
			pnBtn.add(lb6);
			pnBtn.add(lb7);
			pnBtn.add(lb8);
			pnBtn.add(lb9);
			pnBtn.add(lb10);
			pnBtn.add(btnLuu);
			return pnBtn;
	}
	
	public JPanel pnChucnang() {
		JPanel pnChucNang= new JPanel();
		lbID= new JLabel("Ma hoa don");
		tfID= new JTextField(20);
		
		lbNgay= new JLabel("Ngay");
		tfNgay= new JTextField(20);
		
		lbTensp= new JLabel("Tên sản phẩm");
		cbTensp= new JComboBox(vNuoc());
		
		lbSoLuong= new JLabel("SoLuong");
		tfSoLuong= new JTextField(20);
		
		lbTongTien=new JLabel("Tổng cộng");
		tongtien= new JLabel(" ");
		
		lb2= new JLabel("-----------------");
		lb3= new JLabel("-----------------");
		pnChucNang.setLayout(new GridLayout(6,2));
		
		pnChucNang.add(lbTensp);
		pnChucNang.add(cbTensp);
		
		
		pnChucNang.add(lbSoLuong);
		pnChucNang.add(tfSoLuong);
		
		pnChucNang.add(lb2);
		pnChucNang.add(lb3);
		
		pnChucNang.add(lbID);
		pnChucNang.add(tfID);
		
		pnChucNang.add(lbNgay);
		pnChucNang.add(tfNgay);
		
		pnChucNang.add(lbTongTien);
		pnChucNang.add(tongtien);
		return pnChucNang;
	}
	
	public JPanel pnBang() {
		tb= new JPanel();
		displayOrder();
		spOrder= new JScrollPane(tbOrder);
		tb.add(spOrder);
		return tb;
	}
	
	public JPanel pnUnder() {
		 pnUnder= new  JPanel();
		pnUnder.setLayout(new BorderLayout());
		btnThem= new JButton("Thêm");
		btnThem.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
					addList();
					pnUnder.add(pnBang(),BorderLayout.NORTH);
					pnUnder.revalidate();
				}
			});
		btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
					Sua();
					pnUnder.add(pnBang(),BorderLayout.NORTH);
					pnUnder.revalidate();
				}
			});
		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
					Xoa();
					pnUnder.add(pnBang(),BorderLayout.NORTH);
					pnUnder.revalidate();
				}
			});
		pnUnder.add(pnBang(),BorderLayout.NORTH);
		pnUnder.add(pnChucnang(),BorderLayout.CENTER);
		pnUnder.add(pnBtn(),BorderLayout.EAST);
		return pnUnder;
		
	}
	
	//----------------------------------------------------------------------------
	// nút reload
	public void reload() {
		this.setVisible(false);
		HoaDonView hoaDonView= new HoaDonView();
	}
	
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
		tm.fireTableDataChanged();
	}
	
		
	//List thức uống
	public Vector<LoaiNuoc> loainuoc(){
		ConnectDB conn= new ConnectDB();
		rs= conn.ListNuoc("Select * from SANPHAM");
		LoaiNuoc ln;
		Vector<LoaiNuoc> loainuoc= new Vector<LoaiNuoc>();
		try {
			while(rs.next()){
				ln= new LoaiNuoc(rs.getString("MASP"), rs.getString("TENSP"), rs.getString("LOAISP"), rs.getInt("GIA"));
				loainuoc.add(ln);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loainuoc;
	}
	
	//data của combobox
		public Vector<String> vNuoc() {
			Vector<String> vNuoc= new Vector<>();
			Vector<LoaiNuoc> vLn= loainuoc();
			for(int i=0;i<vLn.size();i++) {
				vNuoc.add(vLn.get(i).getTenNuoc());
			}
			return vNuoc;
		}

	//ma sp
		public String idsp(String sp) {
			
			String idsp="";
			Vector<LoaiNuoc> vLn= loainuoc();
			for(int i=0;i<vLn.size();i++) {
				if(sp.equals(vLn.get(i).getTenNuoc())) {
					idsp=vLn.get(i).getMaNuoc();
				}
			}
			return idsp;
		}
		
	//tiền của 1 chi tiết hóa đơn 
	public long tien() {
			String tensp=Objects.requireNonNull(cbTensp.getSelectedItem()).toString();
			long soluong= Long.parseLong(tfSoLuong.getText());
			long tongTien=0;
			for(int i=0;i<loainuoc().size();i++) {
				if(tensp.equals(loainuoc().get(i).getTenNuoc())){
					 tongTien=soluong*loainuoc().get(i).getGia();
				}
			}
		return tongTien;
	}
	
	//thêm chi tiết hóa đơn vào vector
	public void addList() {
		String tensp= Objects.requireNonNull(cbTensp.getSelectedItem()).toString();
		long soluong= Long.parseLong(tfSoLuong.getText());
		long gia= tien();
		ChiTietHoaDon cthd= new ChiTietHoaDon(tensp, soluong, gia);
		vCTHD.add(cthd);
	}
	
	//Hiện danh sách order lên table
	public void displayOrder() {
		String col[]= {"Ten san pham","So luong","Thanh tien"};
		tm= new DefaultTableModel(col,0);
		tbOrder= new JTable(tm);
		for(int i=0;i<vCTHD.size();i++) {
			Object data[]= {vCTHD.get(i).getTensp(),vCTHD.get(i).getSoLuong(),vCTHD.get(i).getThanhTien()};
			tm.addRow(data);
		}
		
	}
	
	//Tính tổng tiền hóa đơn
	public long tongHD() {
		long sum=0;
		for(int i=0;i<vCTHD.size();i++) {
			sum=vCTHD.get(i).getThanhTien()+sum;
		}
		return sum;
	}
	
	//Event button Lưu
	public void LuuHoaDon() {
		String ngay= tfNgay.getText();
		Date date=Date.valueOf(ngay);
		String id= tfID.getText();
			try {
				//insert HOADON
				ConnectDB conn=new ConnectDB();
				long tongTien=tongHD();
				int record=conn.executeDB("insert into HOADON values('"+tfID.getText()+"','"+date+"','"+tongTien+"') ");
							tfID.setText("");
							tfNgay.setText("");
							tfSoLuong.setText("");
				tongtien.setText(tongTien+" ");
				
				for(int i=0;i<vCTHD.size();i++) {
					String idsp=idsp(vCTHD.get(i).getTensp());
					int record1= conn.executeDB("insert into CHITIETHOADON values('"+id+"','"+idsp+"','"+vCTHD.get(i).getSoLuong()+"','"+vCTHD.get(i).getThanhTien()+"') ");
					record1 =0;
				}
				vCTHD.clear();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
	}
	
	//event button Sửa 
	public void Sua() {
		String tensp=Objects.requireNonNull(cbTensp.getSelectedItem()).toString();
		long soluong= Long.parseLong(tfSoLuong.getText());
		for(int i=0;i<vCTHD.size();i++) {
			if(tensp.equals(vCTHD.get(i).getTensp())) {
				vCTHD.get(i).setSoLuong(soluong);
				vCTHD.get(i).setThanhTien(tien());
			}
		}
	}
	public void Xoa() {
		String tensp=Objects.requireNonNull(cbTensp.getSelectedItem()).toString();
		for(int i=0;i<vCTHD.size();i++) {
			if(tensp.equals(vCTHD.get(i).getTensp())) {
				vCTHD.remove(i);
			}
		}
	}
	public static void main(String[] args) {
        // TODO Auto-generated method stub
        //new FacebookForm();
        new testThem();

    }
	
}
