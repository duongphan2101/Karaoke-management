package Entity;

public class LoaiPhong {
	private String maLoaiPhong;
	private String tenLoaiPhong;
	private double giaTien;
	
	public LoaiPhong(String tenLoaiPhong) {
		super();
		setTenLoaiPhong(tenLoaiPhong);
	}
	
	public LoaiPhong(String tenLoaiPhong, double giaTien) {
		super();
		// TODO Auto-generated constructor stub
		setTenLoaiPhong(tenLoaiPhong);
		setGiaTien(giaTien);
	}

	public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, double giaTien) {
		super();
		setMaLoaiPhong(maLoaiPhong);
		setTenLoaiPhong(tenLoaiPhong);
		setGiaTien(giaTien);
	}

	public String getMaLoaiPhong() {
		return maLoaiPhong;
	}

	public void setMaLoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}

	

	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}

	public void setTenLoaiPhong(String tenLoaiPhong) {
		this.tenLoaiPhong = tenLoaiPhong;
	}

	public double getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}

	@Override
	public String toString() {
		return maLoaiPhong;
	}
	
	
	
}
