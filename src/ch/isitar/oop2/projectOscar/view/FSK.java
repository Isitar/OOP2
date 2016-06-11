package ch.isitar.oop2.projectOscar.view;

public enum FSK {
	none(0), FSK0(0), FSK6(6), FSK12(12), FSK16(16), FSK18(18);

	private final int val;

	private FSK(int v) {
		val = v;
	}

	public int getVal() {
		return val;
	}
}
