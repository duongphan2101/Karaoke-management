package Entity;

public class Phong {
	private String maPhong;
	private String soNguoi;
	private String tenPhong;
	private LoaiPhong loaiPhong;
	private LoaiPhong giaPhong;
	private TrangThaiPhong trangThaiPhong;
	private String tinTrangPhong;
	
	
	

	public Phong(String maPhong) {
		super();
		// TODO Auto-generated constructor stub
		setMaPhong(maPhong);
	}



	public Phong(String maPhong,String soNguoi, String tenPhong, LoaiPhong loaiPhong, LoaiPhong giaPhong, TrangThaiPhong trangThaiPhong) {
		super();
		setMaPhong(maPhong);
		setSoNguoi(soNguoi);
		setTenPhong(tenPhong);
		setGiaPhong(giaPhong);
		setLoaiPhong(loaiPhong);
		setTrangThaiPhong(trangThaiPhong);
	}
	
	

	public Phong(String maPhong, String soNguoi, String tenPhong, String tinhTrangPhong, LoaiPhong loaiPhong,TrangThaiPhong trangThaiPhong) {
		super();
		setMaPhong(maPhong);
		setSoNguoi(soNguoi);
		setTenPhong(tenPhong);
		setTinTrangPhong(tinhTrangPhong);
		setTrangThaiPhong(trangThaiPhong);
		setLoaiPhong(loaiPhong);
	}



	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	
	public String getSoNguoi() {
		return soNguoi;
	}

	public void setSoNguoi(String soNguoi) {
		this.soNguoi = soNguoi;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public LoaiPhong getGiaPhong() {
		return giaPhong;
	}

	public void setGiaPhong(LoaiPhong giaPhong) {
		this.giaPhong = giaPhong;
	}

	public TrangThaiPhong getTrangThaiPhong() {
		return trangThaiPhong;
	}

	public void setTrangThaiPhong(TrangThaiPhong trangThaiPhong) {
		this.trangThaiPhong = trangThaiPhong;
	}



	public String getTinTrangPhong() {
		return tinTrangPhong;
	}



	public void setTinTrangPhong(String tinTrangPhong) {
		this.tinTrangPhong = tinTrangPhong;
	}


	
	
}
