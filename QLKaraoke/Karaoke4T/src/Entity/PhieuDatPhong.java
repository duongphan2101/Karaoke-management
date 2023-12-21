package Entity;

public class PhieuDatPhong {
	private String maPDP;
	private KhachHang tenKH;
	private KhachHang sdtKH;
	private NhanVien tenNV;
	private String thoiGianDangKyPhong;
	private String thoiGianNhanPhong;
	public PhieuDatPhong() {
		super();
	}
	public PhieuDatPhong(KhachHang sdtKH, KhachHang tenKH, NhanVien tenNV,String thoiGianNhanPhong) {
		setSdtKH(sdtKH);
		setTenKH(tenKH);
		setTenNV(tenNV);
		setThoiGianDangKyPhong(thoiGianNhanPhong);
		setThoiGianNhanPhong(thoiGianNhanPhong);
	}
	public String getMaPDP() {
		return maPDP;
	}
	public void setMaPDP(String maPDP) {
		this.maPDP = maPDP;
	}
	public KhachHang getTenKH() {
		return tenKH;
	}
	public void setTenKH(KhachHang tenKH) {
		this.tenKH = tenKH;
	}
	public KhachHang getSdtKH() {
		return sdtKH;
	}
	public void setSdtKH(KhachHang sdtKH) {
		this.sdtKH = sdtKH;
	}
	public NhanVien getTenNV() {
		return tenNV;
	}
	public void setTenNV(NhanVien tenNV) {
		this.tenNV = tenNV;
	}
	public String getThoiGianDangKyPhong() {
		return thoiGianDangKyPhong;
	}
	public void setThoiGianDangKyPhong(String thoiGianDangKyPhong) {
		this.thoiGianDangKyPhong = thoiGianDangKyPhong;
	}
	public String getThoiGianNhanPhong() {
		return thoiGianNhanPhong;
	}
	public void setThoiGianNhanPhong(String thoiGianNhanPhong) {
		this.thoiGianNhanPhong = thoiGianNhanPhong;
	}
	
	
	
}
