package Entity;
import java.util.Objects;

public class HoaDon {
	private String maHD;
	private String gioKetThuc;
	private String gioNhanPhong;
	private String ngayLap;
	private float tienKhachTra;
	private float tongTien;
	private KhachHang sdtKH;
	private KhachHang tenKH;
	private NhanVien tenNV;
	public HoaDon() {
		super();
	}
	public HoaDon(String maHD, String gioKetThuc, String gioNhanPhong, String ngayLap, float tienKhachTra, float tongTien,
			KhachHang sdtKH, KhachHang tenKH , NhanVien tenNV) {
			
		setMaHD(maHD);
		setGioKetThuc(gioKetThuc);
		setGioNhanPhong(gioNhanPhong);
		setNgayLap(ngayLap);
		setTienKhachTra(tienKhachTra);
		setTongTien(tongTien);
		setSdtKH(sdtKH);
		setTenKH(tenKH);
		setTenNV(tenNV);
	}
	public void setGioKetThuc(String gioKetThuc) {
		this.gioKetThuc = gioKetThuc;
	}
	public void setGioNhanPhong(String gioNhanPhong) {
		this.gioNhanPhong = gioNhanPhong;
	}
	public void setNgayLap(String ngayLap) {
		this.ngayLap = ngayLap;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public String getGioKetThuc() {
		return gioKetThuc;
	}
	
	public String getGioNhanPhong() {
		return gioNhanPhong;
	}

	public String getNgayLap() {
		return ngayLap;
	}
	public float getTienKhachTra() {
		return tienKhachTra;
	}
	public void setTienKhachTra(float tienKhachTra) {
		this.tienKhachTra = tienKhachTra;
	}
	public float getTongTien() {
		return tongTien;
	}
	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}
	public KhachHang getSdtKH() {
		return sdtKH;
	}
	public void setSdtKH(KhachHang sdtKH) {
		this.sdtKH = sdtKH;
	}
	public KhachHang getTenKH() {
		return tenKH;
	}
	public void setTenKH(KhachHang tenKH) {
		this.tenKH = tenKH;
	}
	public NhanVien getTenNV() {
		return tenNV;
	}
	public void setTenNV(NhanVien tenNV) {
		this.tenNV = tenNV;
	}
	@Override
	public int hashCode() {
		return Objects.hash(gioKetThuc, gioNhanPhong, maHD, ngayLap, sdtKH, tenKH, tenNV, tienKhachTra, tongTien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(gioKetThuc, other.gioKetThuc) && Objects.equals(gioNhanPhong, other.gioNhanPhong)
				&& Objects.equals(maHD, other.maHD) && Objects.equals(ngayLap, other.ngayLap)
				&& Objects.equals(sdtKH, other.sdtKH) && Objects.equals(tenKH, other.tenKH)
				&& Objects.equals(tenNV, other.tenNV)
				&& Float.floatToIntBits(tienKhachTra) == Float.floatToIntBits(other.tienKhachTra)
				&& Float.floatToIntBits(tongTien) == Float.floatToIntBits(other.tongTien);
	}
	
	
	
	
}
