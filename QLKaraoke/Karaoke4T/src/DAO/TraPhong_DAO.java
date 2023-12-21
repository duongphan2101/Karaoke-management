package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Entity.TrangThaiPhong;
import connectDB.connectDB;

public class TraPhong_DAO {
	ArrayList<TrangThaiPhong> dsTTP;
	
	public ArrayList<TrangThaiPhong> docbang(){
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "Select p.maTTP , tenTrangThaiPhong from Phong p inner join TrangThaiPhong ttp on p.maTTP = ttp.maTTP";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()) {
				String maTTP = rs.getString(1);
				String tenTTP = rs.getString(2);
				TrangThaiPhong ttp = new TrangThaiPhong(maTTP, tenTTP);
				dsTTP.add(ttp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return dsTTP;
	}
}
