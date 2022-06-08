package algorithm_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FillBackPack { // ��ƴ���� �賶ä��⸦ �����ϱ� ���� Ŭ����

	public static void main(String[] args) throws FileNotFoundException { // ���� �Լ� ����
		// ���� �Լ� �κп����� ������ �Է¹����Ƿ� FileNotFoundException ����ó���� �������ش�
		int[] Weight = new int[500]; // ������ ���Ը� �������� ������ �迭
		int[] Price = new int[500]; // ������ ����ġ�� �������� ������ �迭
		int[] Result = new int[500]; // ������ ���Դ� ����ġ�� �������� ������ �迭
		int[] Order = new int[500]; // ���� ���Ͽ� ����� ������ ������ �������ֱ� ���� �迭
		
		int i = 0; // �迭�� �ε����� �����ϱ� ���� ������ ����
		int BackPackWeight; // ������ �ִ��Ѱ蹫�Ը� �������ֱ� ���� ������ ����

		
		System.out.println("--------------------------- FillBackPack Start! ---------------------------");
		System.out.println();
		System.out.println();
		String fname; // ������ �̸��� �Է¹��� ���ڿ� ����
		
		Scanner input = new Scanner(System.in); // ����ڿ��� �Է��� �ޱ� ���� Scanner Ŭ���� ��ü ����
		System.out.print("Input Name Saved Weight : "); // ����ڿ��� ������ ���԰� ����Ǿ��ִ� ������ �̸��� �Է��϶�� �޼����� �����
		fname = input.next(); // ����ڿ��� ������ ���԰� ����Ǿ��ִ� ������ �̸��� �Է¹���

		Scanner in = new Scanner(new File(fname)); // ������ ���� ���� Scanner Ŭ���� ��ü ����
		while (in.hasNextInt()) { // ���Ͽ��� ���� �о��� �� ������ ���� ���� �������̸�
			Weight[i] = in.nextInt(); // �� ���� Weight �迭�� �о���δ�
			i++; // i �� ����
		}
		

		System.out.print("Input Name Saved Price : "); // ����ڿ��� ������ ����ġ�� ����Ǿ��ִ� ������ �̸��� �Է��϶�� �޼����� �����
		fname = input.next(); // ����ڿ��� ������ ����ġ�� ����Ǿ��ִ� ������ �̸��� �Է¹���

		i = 0; // i�� 0 ���� �ʱ�ȭ
		in = new Scanner(new File(fname)); // ������ ���� ���� Scanner Ŭ���� ��ü�� �ʱ�ȭ ��
		while (in.hasNextInt()) { // ���Ͽ��� ���� �о��� �� ������ ���� ���� �������̸�
			Price[i] = in.nextInt(); // �� ���� Price �迭�� �о���δ�
			i++; // i �� ����
		}
	
		for (int k = 0; k < 500; k++) { // 0 ���� 499���� �ݺ����� ���鼭 

			Order[k] = k; // ���� ������ order �迭�� ����
			
		}

		for (int k = 0; k < 500; k++) { // 0 ���� 499���� �ݺ����� ���鼭 

			Result[k] = Price[k] / Weight[k]; // Result �迭�� ���Դ� ����ġ�� ���� ������
			
		}

		System.out.print("Input Max Weight of BackPack : "); // ������ �ִ��Ѱ� ���Ը� ����ڿ��� �Է��϶�� �޼����� �����
		BackPackWeight = input.nextInt(); // ����ڿ��� �Է¹���

		int temp; // �ӽú���
		int j = 0; // �ݺ����� ������ ������ ����
		i = 0; // i�� 0���� �ʱ�ȭ
		
		// ���Դ� ����ġ�� ū �ͺ��� ����
		for (j = 0; j < 499; j++) { 
			i = j;
			for (i = j; i < 500; i++) {
				if (Result[i] > Result[j]) {
					temp = Result[i];
					Result[i] = Result[j];
					Result[j] = temp;

					// Price �迭��  Weight �迭 ��� �ٲٴ� ������ ���Դ� ����ġ�� ū �ͺ��� Price �迭��  Weight �迭�� ���� �ٲ㼭 
					// Price, Weight, Result�� ���� �ε����� ���� ������ ����ġ, ����, ���Դ� ����ġ�� ���� �ϱ� �����̴�.
					// ���� ������ ������ִ� Order �迭�� ���� �ٲ㼭 �ٲ� ������ �������ش�.
					temp = Price[i];
					Price[i] = Price[j];
					Price[j] = temp;

					temp = Weight[i];
					Weight[i] = Weight[j];
					Weight[j] = temp;
					
					temp = Order[i];
					Order[i] = Order[j];
					Order[j] = temp;

				}
			}
		}

	
		System.out.println();
		System.out.println("--------------------------- Print Best Order! ---------------------------");
		System.out.println();
	
		
		int sum = 0; // ���ݱ��� ���濡 ������ ������ ���Ը� �����ϴ� ������ ����
		i = 0;
		while(sum < BackPackWeight){ // �ݺ����� ���鼭
			System.out.println(i+1+". "+ "Order Of Item : " + Order[i]); // ���Դ� ����ġ�� ū ������ ������ְ�
			sum += Weight[i]; // sum ������ �� ���� �����ش�
			i++; // i ����
		}
		
		in.close();
		input.close();
		
	}

}

