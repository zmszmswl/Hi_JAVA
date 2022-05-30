package com.yedam.java.ch09_02;

public class MathClassExamlpe {
	public static void main(String[] args) {

		// 절대값
		int v1 = Math.abs(-5);
		double v2 = Math.abs(-3.14);
		System.out.println("v1 : " + v1);
		System.out.println("v2 : " + v2);

		// 올림값
		double v3 = Math.ceil(5.3);
		double v4 = Math.ceil(-5.3);
		System.out.println("v3 : " + v3);
		System.out.println("v4 : " + v4);

		// 버림값
		double v5 = Math.floor(5.3);
		double v6 = Math.floor(-5.3);
		System.out.println("v5 : " + v5);
		System.out.println("v6 : " + v6);

		// 최대값
		int v7 = Math.max(5, 9);
		double v8 = Math.max(5.3, 2.5);
		System.out.println("v7 : " + v7);
		System.out.println("v8 : " + v8);

		// 최소값
		int v9 = Math.min(5, 9);
		double v10 = Math.min(5.3, 2.5);
		System.out.println("v9 : " + v9);
		System.out.println("v10 : " + v10);

		// 랜덤값
		double v11 = Math.random();
		System.out.println("v11 : " + v11);

		// 가까운 정수의 실수값 (소수점 이하 첫번째 자리)
		double v12 = Math.rint(5.3);
		double v13 = Math.rint(5.7);
		System.out.println("v12 : " + v12);
		System.out.println("v13 : " + v13);

		// 반올림값 (소수점 이하 첫번째 자리)
		long v14 = Math.round(5.3);
		long v15 = Math.round(5.7);
		System.out.println("v14 : " + v14);
		System.out.println("v15 : " + v15);

		// 소수점 이하 세번째 자리 반올림
		double value = 12.2346;
		double temp1 = value * 100;
		long temp2 = Math.round(temp1);
		double v16 = temp2 / 100.0;
		System.out.println("v16 : " + v16);

		// round와 rint 차이
		System.out.println("round(0.5) : " + Math.round(0.5));
		System.out.println("rint(0.5) : " + Math.rint(0.5));

		System.out.println("round(1.5) : " + Math.round(1.5));
		System.out.println("rint(1.5) : " + Math.rint(1.5));

	}

}
