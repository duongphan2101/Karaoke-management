package Entity;

import java.util.Objects;

public class TaiKhoanNhanVien {
	private String maTaiKhoan;
	private String tenTaiKhoan;
	private String matKhau;	
	private String tenNV;
	private String email;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoanNhanVien other = (TaiKhoanNhanVien) obj;
		return Objects.equals(maTaiKhoan, other.maTaiKhoan);
	}

	public TaiKhoanNhanVien(String maTaiKhoan, String tenTaiKhoan, String matKhau, String tenNV, String email) {
		setMaTaiKhoan(maTaiKhoan);
		setTenTaiKhoan(tenTaiKhoan);
		setMatKhau(matKhau);
		setTenNV(tenNV);
		setEmail(email);
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTaiKhoan);
	}
	
}
