package Entity;

import java.util.Objects;

public class ThongKeHoaDon {
	private HoaDon maHD;
	private KhachHang tenKH;
	private KhachHang SDT;
	private HoaDon ngayLap;
	private NhanVien tenNV;
	private HoaDon tongTien;
	public ThongKeHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ThongKeHoaDon(HoaDon maHD, KhachHang tenKH, KhachHang sDT, HoaDon ngayLap, NhanVien tenNV, HoaDon tongTien) {
		setMaHD(maHD);
		setTenKH(tenKH);
		setSDT(sDT);
		setNgayLap(ngayLap);
		setTenNV(tenNV);
		setTongTien(tongTien);
	}
	public HoaDon getMaHD() {
		return maHD;
	}
	public void setMaHD(HoaDon maHD) {
		this.maHD = maHD;
	}
	public KhachHang getTenKH() {
		return tenKH;
	}
	public void setTenKH(KhachHang tenKH) {
		this.tenKH = tenKH;
	}
	public KhachHang getSDT() {
		return SDT;
	}
	public void setSDT(KhachHang sDT) {
		SDT = sDT;
	}
	public HoaDon getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(HoaDon ngayLap) {
		this.ngayLap = ngayLap;
	}
	public NhanVien getTenNV() {
		return tenNV;
	}
	public void setTenNV(NhanVien tenNV) {
		this.tenNV = tenNV;
	}
	public HoaDon getTongTien() {
		return tongTien;
	}
	public void setTongTien(HoaDon tongTien) {
		this.tongTien = tongTien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(SDT, maHD, ngayLap, tenKH, tenNV, tongTien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThongKeHoaDon other = (ThongKeHoaDon) obj;
		return Objects.equals(SDT, other.SDT) && Objects.equals(maHD, other.maHD)
				&& Objects.equals(ngayLap, other.ngayLap) && Objects.equals(tenKH, other.tenKH)
				&& Objects.equals(tenNV, other.tenNV) && Objects.equals(tongTien, other.tongTien);
	}
	
	
}
