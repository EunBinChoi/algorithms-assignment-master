// �Ǻ���ġ ���� (����� ���)

package algorithm_fibo;
import java.util.Scanner; // ����ڿ��� �Է¹ޱ� ���� Scanner ��ü�� ����ϰڴٴ� �ǹ�


public class fibo { // �Ǻ���ġ ������ ������ Ŭ����

	public static void main(String[] args) { // ���� �Լ�
		int num; // �� �ױ��� �Ǻ���ġ ������ ���� ���� �� ������ ����
		
		Scanner sc = new Scanner(System.in); // Scanner ��ü�� �������
	
		System.out.println("---------------------- �Ǻ���ġ ���� ---------------------"+"\n");
		System.out.print("���° �ױ��� ���� ���ұ��? ");
		num = sc.nextInt(); // ����ڿ��� num�� ���� �Է¹���
		
		
		for(int i = 0; i <= num ; i++){ // �ݺ����� ���鼭 �Ǻ���ġ �� ���� ���� �����
			System.out.print(i+"��° �� = " + fib(i)+"\n");
		}
	}
	
	public static int fib(int n){ // �Ǻ���ġ ���� �����ִ� �Լ�
		
		if(n<=1) return n; // �Ǻ���ġ ������ n�� 1���� �۰ų� ���� ������ n�� �״�� ���
		else return fib(n-1) + fib(n-2); // n�� 2���� ũ��, ���� �� ���� ���� ���� ���
	}

}
