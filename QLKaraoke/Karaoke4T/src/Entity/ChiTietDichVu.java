package Entity;

public class ChiTietDichVu {
	private DichVu dichVu;
	private HoaDon hoaDon;
	private int soLuong;
	
	public ChiTietDichVu(DichVu dichVu, HoaDon hoaDon, int soLuong) {
		setDichVu(dichVu);
		setHoaDon(hoaDon);
		setSoLuong(soLuong);
	}

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
}
