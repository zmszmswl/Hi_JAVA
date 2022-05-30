package com.yedam.java.homework;

public interface Keypad {

	// 상수필드
	public static final int NORMAL_MODE = 0;
	public static final int HARD_MODE = 1;

	// 추상메서드
	public abstract void leftUpButton(); // 왼쪽에서 위쪽 버튼을 눌렀을 때 동작하는 기능

	public abstract void leftDownButton(); // 왼쪽에서 아래쪽 버튼을 눌렀을 때 동작하는 기능

	public abstract void rightUpButton(); // 오른쪽에서 위쪽 버튼을 눌렀을 때 동작하는 기능

	public abstract void rightDownButton(); // 오른쪽에서 아래쪽 버튼을 눌렀을 때 동작하는 기능

	public abstract void changeMode(); // 모드를 바꾸고 현재 모드를 출력하는 기능 ( NORMAL_MODE -> HARD_MODE / HARD_MODE -> NORMAL_MODE)

}
