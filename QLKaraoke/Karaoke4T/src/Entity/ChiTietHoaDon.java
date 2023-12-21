package Entity;

public class ChiTietHoaDon {
	private HoaDon hoaDon;
	private Phong phong;
	
	public ChiTietHoaDon(HoaDon hoaDon, Phong phong) {
		setHoaDon(hoaDon);
		setPhong(phong);
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	
	
}
