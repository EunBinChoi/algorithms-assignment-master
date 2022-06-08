package algorithm_fibo; // package를 정의해준다
import java.util.*; // java.util 패키지를 import 해준다

public class fibo { // 피보나치 수열을 수행해주는 클래스 정의

	public static void main(String[] args) { // 메인 함수
		
		System.out.println("------------------- Fibonacci Start! -------------------");
		System.out.println();
		System.out.println("1. 반복적 방법");
	

		// 반복적 방법
		for (int i = 0; i <= 2000; i++) { // 반복문을 i = 0 부터 2000까지 돌면서
			if (i == 10 || i == 25 || i == 50 || i == 75 || i == 100 || i == 1000 || i == 2000) {
				// 위에 해당되는 i일때에는 다음을 실행해준다
				
					// Start time을 저장
					long startTime1 = System.nanoTime();
					// 반복문을 돌면서 피보나치 반복적 방법의 값을 f_tmp에 저장한다
					long f_tmp = fibo_iterative(i);
					// End time을 저장
					long endTime1 = System.nanoTime();

					// End time에서 Start time을 뺴면 피보나치 반복적 방법의 실행시간을 알 수 있다
					long lTime1 = endTime1-startTime1;
					// 실행 시간 출력
					System.out.printf("n = %5d\t\tFibo : %20d\t\tTime : %5f(ms)", i,f_tmp,lTime1/1000000.0);
					System.out.println();
					
					
			}
		

		}
		System.out.println();
		System.out.println("2. 순환적 방법");
		
		// 순환적 방법
		for (long i = 0; i <= 2000; i++) { // 반복문을 i = 0 부터 2000까지 돌면서 (여기서 long으로 해준 이유는 여기서 i는 순환적 방법의 인수로 들어가기 때문이다.)
			// 순환적 방법의 알고리즘은 들어온 인수 n이 1보다 작거나 같을 때 n을 그대로 반환해주기 때문에 long타입이여야 한다(우리가 값을 구하기 위해서 가장 바이트가 큰 long으로 설정했기 때문이다.))
			if (i == 10 || i == 25 || i == 50 || i == 75 || i == 100 || i == 1000 || i == 2000) {
				// 위에 해당되는 i일때에는 다음을 실행해준다
				if(i <= 50){ // 순환적 방법은 50보다 커지면 실행시간이 매우 오래걸리므로, 50보다 작거나 같을 때에만 수행시간을 확인해준다
					
					// Start time을 저장
					long startTime1 = System.nanoTime();
					// 반복문을 돌면서 피보나치 반복적 방법의 값을 f_tmp에 저장한다
					long f_tmp = fibo_recursive(i);
					// End time을 저장
					long endTime1 = System.nanoTime();


					// End time에서 Start time을 뺴면 피보나치 반복적 방법의 실행시간을 알 수 있다
					long lTime1 = endTime1-startTime1;
					// 실행 시간 출력
					System.out.printf("n = %5d\t\tFibo : %20d\t\tTime : %5f(ms)", i,f_tmp,lTime1/1000000.0);
					System.out.println();
				}
					
			}
		

		}
	}

	public static long fibo_recursive(long n) { // 피보나치 순환적 알고리즘
		if (n <= 1) // 만약 인수로 들어온 n이 1이하이면 인수로 들어온 값을 그대로 반환
			return n;
		else // 1이하가 아니라면
			return fibo_recursive(n - 1) + fibo_recursive(n - 2); // 이전의 두 값을 더한 것을 반환
	}

	public static long fibo_iterative(int n) { // 피보나치 반복적 알고리즘
		long f[] = new long[n + 1]; // long 타입의 배열을 n+1만큼 할당(0부터 n까지 저장가능)
		f[0] = 0; // f[0]은 0으로 설정

		if (n > 0) { // 만약 n이 양수라면
			f[1] = 1; // f[1]을 1으로 설정
			for (int i = 2; i <= n; i++) { // 반복문을 돌면서

				f[i] = f[i - 1] + f[i - 2];
				// 나머지 값을 배열에 저장해준다. 나머지 값은 이전의 두 항을 더하면 된다.

			}

		}
		return f[n]; // 인수로 들어온 n에 해당하는 배열 값을 반환
	}

}
