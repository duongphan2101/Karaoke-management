package Entity;

import java.util.Objects;

public class LoaiNhanVien {
	private String maLNV;
	private String tenLNV;
	public LoaiNhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoaiNhanVien(String maLNV, String tenLNV) {
		setMaLNV(maLNV);
		setTenLNV(tenLNV);
	}
	public String getMaLNV() {
		return maLNV;
	}
	public void setMaLNV(String maLNV) {
		this.maLNV = maLNV;
	}
	public String getTenLNV() {
		return tenLNV;
	}
	public void setTenLNV(String tenLNV) {
		this.tenLNV = tenLNV;
	}
	@Override
	public String toString() {
		return tenLNV;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLNV, tenLNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiNhanVien other = (LoaiNhanVien) obj;
		return Objects.equals(maLNV, other.maLNV) && Objects.equals(tenLNV, other.tenLNV);
	}
	
	
	
}
