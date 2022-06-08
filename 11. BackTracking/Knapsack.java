package algorithm_11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Knapsack { // BackTracking���� knapsack�� ����ϴ� Ŭ���� ����

	// �Լ����� ���������� ���Ǹ� �ʱ�ȭ�Ǿ� ����� ������ ������� ������ �߻��ϹǷ� ������ ���� ���������� ������
	public static int maxprofit; // �ִ� ������ �����ϱ� ���� ������ ���� ����
	public static int BackPackWeight; // ������ �ִ��Ѱ蹫�Ը� �������ֱ� ���� ������ ����
	public static int[] Weight = new int[500]; // ������ ���Ը� �������� ������ �迭
	public static int[] Price = new int[500]; // ������ ����ġ�� �������� ������ �迭
	public static int numbest = 0; // ���� ��带 �����ϴ� ������ ����
	public static int[] bestset = new int[500]; // ������ ��θ� �����ϴ� ������ �迭
	public static int[] include = new int[500]; // ���� ��尡 ������ ��ο� ���ԵǴ��� �ȵǴ��� ���θ�
												// �������ִ� ������ �迭(0 : ���Ե��� ���� / 1
												// : ����)

	public static void main(String[] args) throws FileNotFoundException { // ����
																			// �Լ�
																			// ����
		// ���� �Լ� �κп����� ������ �Է¹����Ƿ� FileNotFoundException ����ó���� �������ش�

		int[] Result = new int[500]; // ������ ���Դ� ����ġ�� �������� ������ �迭
		int[] Order = new int[500]; // ������ �ε����� �����ϱ� ���� ������ �迭
		int i = 0; // �迭�� �ε����� �����ϱ� ���� ������ ����

		System.out.println("--------------------------- BackTracking Knapsack Start! ---------------------------");
		String fname; // ������ �̸��� �Է¹��� ���ڿ� ����

		Scanner input = new Scanner(System.in); // ����ڿ��� �Է��� �ޱ� ���� Scanner Ŭ����
												// ��ü ����
		System.out.print("Input Name Saved Weight : "); // ����ڿ��� ������ ���԰� ����Ǿ��ִ�
														// ������ �̸��� �Է��϶�� �޼�����
														// �����
		fname = input.next(); // ����ڿ��� ������ ���԰� ����Ǿ��ִ� ������ �̸��� �Է¹���

		Scanner in = new Scanner(new File(fname)); // ������ ���� ���� Scanner Ŭ���� ��ü
													// ����
		while (in.hasNextInt()) { // ���Ͽ��� ���� �о��� �� ������ ���� ���� �������̸�
			Weight[i] = in.nextInt(); // �� ���� Weight �迭�� �о���δ�
			i++; // i �� ����
		}

		System.out.print("Input Name Saved Price : "); // ����ڿ��� ������ ����ġ�� ����Ǿ��ִ�
														// ������ �̸��� �Է��϶�� �޼�����
														// �����
		fname = input.next(); // ����ڿ��� ������ ����ġ�� ����Ǿ��ִ� ������ �̸��� �Է¹���

		i = 0; // i�� 0 ���� �ʱ�ȭ
		in = new Scanner(new File(fname)); // ������ ���� ���� Scanner Ŭ���� ��ü�� �ʱ�ȭ ��
		while (in.hasNextInt()) { // ���Ͽ��� ���� �о��� �� ������ ���� ���� �������̸�
			Price[i] = in.nextInt(); // �� ���� Price �迭�� �о���δ�
			i++; // i �� ����
		}

		for (int k = 0; k < 500; k++) { // 0 ���� 499���� �ݺ����� ���鼭

			Result[k] = Price[k] / Weight[k]; // Result �迭�� ���Դ� ����ġ�� ���� ������
			Order[k] = k; // ���Ͽ� ����� ������ ������ ������ �״�� order �迭�� ������
		}
		
	

		System.out.print("Input Max Weight of BackPack : "); // ������ �ִ��Ѱ� ���Ը�
																// ����ڿ��� �Է��϶��
																// �޼����� �����
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

					// Price �迭�� Weight �迭 ��� �ٲٴ� ������ ���Դ� ����ġ�� ū �ͺ��� Price �迭��
					// Weight �迭�� ���� �ٲ㼭
					// Price, Weight, Result�� ���� �ε����� ���� ������ ����ġ, ����, ���Դ� ����ġ��
					// ���� �ϱ� �����̴�.
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
		System.out.println();
		System.out.println("---------------------------------- Loading! ----------------------------------");

		knapsack(-1, 0, 0); // knapsack �Լ� ȣ��, i�� -1���� ȣ������
		System.out.println("MaxProfit : " + maxprofit); // �ִ� ������ ������ִ� ��¹�
		System.out.println();
		for (int k = 0; k <= numbest; k++) { // 0���� numbest���� �ݺ����� ���鼭 
			System.out.print("No." + Order[k] + " Node : "); 
			if (bestset[k] == 1) // ���� bestset�� ���� 1�̶�� 
				System.out.println("Include"); // k��° ��带 ���Խ�Ų�ٴ� �޼��� ���
			else // ���� bestset�� ���� 0�̶�� 
				System.out.println("Not Include"); // k��° ��带 ���Խ�Ű�� �ʴ´ٴ� �޼��� ���
		}

	}

	public static void knapsack(int i, int profit, int weight) { // ������������� �賶ä��⸦ �������ִ� �Լ�
		// ���ݱ����� �� ������ �ְ��̴�.
		// numbest�� ����� �������� ����, bestset�� �ش�

		if (weight <= BackPackWeight && profit > maxprofit) { // ���� ���� weight�� BackPackWeight(���濡 ���� �� �ִ� �ִ� ����)���� �۰�, profit�� maxprofit���� ũ�ٸ�
			maxprofit = profit; // ���� maxprofit�� profit���� ����
			numbest = i; // ���� i�� numbest�� ����
			for (int k = 0; k <= numbest; k++) { // 0���� numbest���� ���鼭
				bestset[k] = include[k]; // include�� ���� bestset�� ����
			}

		}

		if (promissing(500, i, profit, weight)) { // promissing �Լ� ȣ��
			include[i + 1] = 1; // w[i + 1] ����
			knapsack(i + 1, profit + Price[i + 1], weight + Weight[i + 1]); 
			// knapsack �Լ� ��� ȣ��, i+1��° ��尡 ���ԵǹǷ�, ���� profit�� weight�� i+1�� Price�� Weight�� ���� ����
			include[i + 1] = 0; // w[i + 1] ������
			knapsack(i + 1, profit, weight); 
			// knapsack �Լ� ��� ȣ��, i+1��° ��尡 ���Ե��������Ƿ�, ���� profit�� weight�� i+1�� Price�� Weight�� ���� ������������
		}

	}

	public static boolean promissing(int n, int i, int profit, int weight) { // promissing �Լ� ����
		int j, k; // �ε����� �����ϱ� ���� ������ ���� ����
		int totweight; // ���� ���濡 ��� ��ü ���Ը� �����ϴ� ������ ���� ����
		float bound; // ���� �� �ִ� �ִ� �ݾ��� �������ִ� ������ ���� ����(�߶󼭱��� ���� �� �ִ� �ִ밪)

		// �ڽĸ���� Ȯ���� �� ���� ���� ����� �����ϴ�. ������ ������ �������� �ʴ�.
		if (weight >= BackPackWeight)// ���� weight���� BackPackWeight(���濡 ������ ���� �� �ִ� �ִ밪)���� ũ�ų� ������
			return false; // ���̻� ���濡 ������ ���� �� �����Ƿ� false ��ȯ

		else { // ���� weight���� BackPackWeight(���濡 ������ ���� �� �ִ� �ִ밪)���� ������
			j = i + 1; // j���� i���� �ϳ� �������Ѽ� ����
			bound = profit; // ���� profit�� bound�� ����
			totweight = weight; // ���� weight�� ���� totweight�� ����
			while (j < n && totweight + Weight[j] <= BackPackWeight) { // j�� n���� �۰�, totweight�� Weight[j] ���� �� ���� BackPackWeight�� ũ�ų� ���� ������
				totweight = totweight + Weight[j]; // ������ ���� �������� ���� totweight ���� �����Ѵ�
				bound = bound + Price[j]; // bound���� �ݺ����� ���鼭 Price ���� ��� �����ش�
				j++; // j ����
			}
			k = j; // å���� ����� �İ� �ϰ����� �����ϱ� ���Ͽ� k���
			if (k < n) // k��° �������� �Ϻκ��� ����
				bound = bound + (BackPackWeight - totweight) * Price[k] / Weight[k];
			// ���� bound�� BackPackWeight - totweight(���濡 �ڸ��� ���� ��ŭ) ������ ���Դ� ����ġ��ŭ �� ���� bound�� �������ش�.
			return bound > maxprofit; // ���� bound���� maxprofit���� ũ�� true�� �ǹǷ� �����ϰ�, �׷��� �ʴٸ� �������� ����
		}
	}
}