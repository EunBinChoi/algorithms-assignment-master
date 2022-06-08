package algorithm_fibo; // package�� �������ش�
import java.util.*; // java.util ��Ű���� import ���ش�

public class fibo { // �Ǻ���ġ ������ �������ִ� Ŭ���� ����

	public static void main(String[] args) { // ���� �Լ�
		
		System.out.println("------------------- Fibonacci Start! -------------------");
		System.out.println();
		System.out.println("1. �ݺ��� ���");
	

		// �ݺ��� ���
		for (int i = 0; i <= 2000; i++) { // �ݺ����� i = 0 ���� 2000���� ���鼭
			if (i == 10 || i == 25 || i == 50 || i == 75 || i == 100 || i == 1000 || i == 2000) {
				// ���� �ش�Ǵ� i�϶����� ������ �������ش�
				
					// Start time�� ����
					long startTime1 = System.nanoTime();
					// �ݺ����� ���鼭 �Ǻ���ġ �ݺ��� ����� ���� f_tmp�� �����Ѵ�
					long f_tmp = fibo_iterative(i);
					// End time�� ����
					long endTime1 = System.nanoTime();

					// End time���� Start time�� ���� �Ǻ���ġ �ݺ��� ����� ����ð��� �� �� �ִ�
					long lTime1 = endTime1-startTime1;
					// ���� �ð� ���
					System.out.printf("n = %5d\t\tFibo : %20d\t\tTime : %5f(ms)", i,f_tmp,lTime1/1000000.0);
					System.out.println();
					
					
			}
		

		}
		System.out.println();
		System.out.println("2. ��ȯ�� ���");
		
		// ��ȯ�� ���
		for (long i = 0; i <= 2000; i++) { // �ݺ����� i = 0 ���� 2000���� ���鼭 (���⼭ long���� ���� ������ ���⼭ i�� ��ȯ�� ����� �μ��� ���� �����̴�.)
			// ��ȯ�� ����� �˰����� ���� �μ� n�� 1���� �۰ų� ���� �� n�� �״�� ��ȯ���ֱ� ������ longŸ���̿��� �Ѵ�(�츮�� ���� ���ϱ� ���ؼ� ���� ����Ʈ�� ū long���� �����߱� �����̴�.))
			if (i == 10 || i == 25 || i == 50 || i == 75 || i == 100 || i == 1000 || i == 2000) {
				// ���� �ش�Ǵ� i�϶����� ������ �������ش�
				if(i <= 50){ // ��ȯ�� ����� 50���� Ŀ���� ����ð��� �ſ� �����ɸ��Ƿ�, 50���� �۰ų� ���� ������ ����ð��� Ȯ�����ش�
					
					// Start time�� ����
					long startTime1 = System.nanoTime();
					// �ݺ����� ���鼭 �Ǻ���ġ �ݺ��� ����� ���� f_tmp�� �����Ѵ�
					long f_tmp = fibo_recursive(i);
					// End time�� ����
					long endTime1 = System.nanoTime();


					// End time���� Start time�� ���� �Ǻ���ġ �ݺ��� ����� ����ð��� �� �� �ִ�
					long lTime1 = endTime1-startTime1;
					// ���� �ð� ���
					System.out.printf("n = %5d\t\tFibo : %20d\t\tTime : %5f(ms)", i,f_tmp,lTime1/1000000.0);
					System.out.println();
				}
					
			}
		

		}
	}

	public static long fibo_recursive(long n) { // �Ǻ���ġ ��ȯ�� �˰���
		if (n <= 1) // ���� �μ��� ���� n�� 1�����̸� �μ��� ���� ���� �״�� ��ȯ
			return n;
		else // 1���ϰ� �ƴ϶��
			return fibo_recursive(n - 1) + fibo_recursive(n - 2); // ������ �� ���� ���� ���� ��ȯ
	}

	public static long fibo_iterative(int n) { // �Ǻ���ġ �ݺ��� �˰���
		long f[] = new long[n + 1]; // long Ÿ���� �迭�� n+1��ŭ �Ҵ�(0���� n���� ���尡��)
		f[0] = 0; // f[0]�� 0���� ����

		if (n > 0) { // ���� n�� ������
			f[1] = 1; // f[1]�� 1���� ����
			for (int i = 2; i <= n; i++) { // �ݺ����� ���鼭

				f[i] = f[i - 1] + f[i - 2];
				// ������ ���� �迭�� �������ش�. ������ ���� ������ �� ���� ���ϸ� �ȴ�.

			}

		}
		return f[n]; // �μ��� ���� n�� �ش��ϴ� �迭 ���� ��ȯ
	}

}
