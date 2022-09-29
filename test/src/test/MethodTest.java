package test;

public class MethodTest {
	
		// 메서드를 만드는 이유는 반복실행 자주 쓰는 코드가 있으면 불러서 써라~!
		//printEq("*",10); // *모양으로 찍고싶어서 넘겨줌
		//printEq("=",20);
		//printEq("&",30);

	/**
	 * 
	 * @param s
	 * print shape
	 * @param j
	 * for length
	 */
	public static void printEq(String s, int j) { // 변수를 받아주는 값 다양하게 제어하고싶음 타입과 변수를 선언
		for (int i=0; i<j; i++) {
			if( i == 5 ) return;
			//System.out.print(s);
	}
		System.out.println();
		//return;
}
	public static int total (int n1, int n2) {
		int result = n1+n2;
		return result; // return 있으면 함수 끝날때 결과를 가지고 돌아감
	}
}

