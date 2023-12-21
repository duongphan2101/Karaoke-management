package Entity;

import java.util.Objects;

public class ThongKeKhachHang {
	private KhachHang maKH;
	private KhachHang tenKH;
	private KhachHang SDT;
	private KhachHang CMND;
	private KhachHang diaChi;
	private PhieuDatPhong thoiGianNhanPhong;
	public ThongKeKhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ThongKeKhachHang(KhachHang maKH, KhachHang tenKH, KhachHang sDT, KhachHang cMND, KhachHang diaChi,
			PhieuDatPhong thoiGianNhanPhong) {
		setMaKH(maKH);
		setTenKH(tenKH);
		setSDT(sDT);
		setCMND(cMND);
		setDiaChi(diaChi);
		setThoiGianNhanPhong(thoiGianNhanPhong);
		
	}
	public KhachHang getMaKH() {
		return maKH;
	}
	public void setMaKH(KhachHang maKH) {
		this.maKH = maKH;
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
	public KhachHang getCMND() {
		return CMND;
	}
	public void setCMND(KhachHang cMND) {
		CMND = cMND;
	}
	public KhachHang getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(KhachHang diaChi) {
		this.diaChi = diaChi;
	}
	public PhieuDatPhong getThoiGianNhanPhong() {
		return thoiGianNhanPhong;
	}
	public void setThoiGianNhanPhong(PhieuDatPhong thoiGianNhanPhong) {
		this.thoiGianNhanPhong = thoiGianNhanPhong;
	}
	@Override
	public int hashCode() {
		return Objects.hash(CMND, SDT, diaChi, maKH, tenKH, thoiGianNhanPhong);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThongKeKhachHang other = (ThongKeKhachHang) obj;
		return Objects.equals(CMND, other.CMND) && Objects.equals(SDT, other.SDT)
				&& Objects.equals(diaChi, other.diaChi) && Objects.equals(maKH, other.maKH)
				&& Objects.equals(tenKH, other.tenKH) && Objects.equals(thoiGianNhanPhong, other.thoiGianNhanPhong);
	}
	@Override
	public String toString() {
		return "ThongKeKhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", SDT=" + SDT + ", CMND=" + CMND + ", diaChi="
				+ diaChi + ", thoiGianNhanPhong=" + thoiGianNhanPhong + "]";
	}
	
}
