package Entity;

public class DiaChi {
	private String maDC;
	private String phuongXa;
	private String quanHuyen;
	private String tinhTP;
	public DiaChi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiaChi(String maDC, String phuongXa, String quanHuyen, String tinhTP) {
		super();
		this.maDC = maDC;
		this.phuongXa = phuongXa;
		this.quanHuyen = quanHuyen;
		this.tinhTP = tinhTP;
	}
	public String getMaDC() {
		return maDC;
	}
	public void setMaDC(String maDC) {
		this.maDC = maDC;
	}
	public String getPhuongXa() {
		return phuongXa;
	}
	public void setPhuongXa(String phuongXa) {
		this.phuongXa = phuongXa;
	}
	public String getQuanHuyen() {
		return quanHuyen;
	}
	public void setQuanHuyen(String quanHuyen) {
		this.quanHuyen = quanHuyen;
	}
	public String getTinhTP() {
		return tinhTP;
	}
	public void setTinhTP(String tinhTP) {
		this.tinhTP = tinhTP;
	}
	@Override
	public String toString() {
		return "DiaChi [maDC=" + maDC + ", phuongXa=" + phuongXa + ", quanHuyen=" + quanHuyen + ", tinhTP=" + tinhTP
				+ "]";
	}
	
}
