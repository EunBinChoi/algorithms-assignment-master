// 피보나치 수열 (재귀적 방법)

package algorithm_fibo;
import java.util.Scanner; // 사용자에게 입력받기 위한 Scanner 객체를 사용하겠다는 의미


public class fibo { // 피보나치 수열을 구현할 클래스

	public static void main(String[] args) { // 메인 함수
		int num; // 몇 항까지 피보나치 수열의 값을 구할 지 저장할 변수
		
		Scanner sc = new Scanner(System.in); // Scanner 객체를 만들어줌
	
		System.out.println("---------------------- 피보나치 시작 ---------------------"+"\n");
		System.out.print("몇번째 항까지 값을 구할까요? ");
		num = sc.nextInt(); // 사용자에게 num의 값을 입력받음
		
		
		for(int i = 0; i <= num ; i++){ // 반복문을 돌면서 피보나치 각 항의 값을 출력함
			System.out.print(i+"번째 항 = " + fib(i)+"\n");
		}
	}
	
	public static int fib(int n){ // 피보나치 값을 구해주는 함수
		
		if(n<=1) return n; // 피보나치 수열은 n이 1보다 작거나 같을 때에는 n을 그대로 출력
		else return fib(n-1) + fib(n-2); // n이 2보다 크면, 앞의 두 항을 더한 값을 출력
	}

}
