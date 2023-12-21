package Entity;

public class DatDichVu {
	private String maDDV;
	private Phong maPhong;
	private DichVu maDichVu;
	private int soluong;
	
	
	public DatDichVu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatDichVu(String maDDV, Phong maPhong, DichVu maDichVu, int soluong) {
		super();
		// TODO Auto-generated constructor stub
		setMaDDV(maDDV);
		setMaDichVu(maDichVu);
		setMaPhong(maPhong);
		setSoluong(soluong);
	}

	public String getMaDDV() {
		return maDDV;
	}

	public void setMaDDV(String maDDV) {
		this.maDDV = maDDV;
	}

	public Phong getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(Phong maPhong) {
		this.maPhong = maPhong;
	}

	public DichVu getMaDichVu() {
		return maDichVu;
	}

	public void setMaDichVu(DichVu maDichVu) {
		this.maDichVu = maDichVu;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
}
