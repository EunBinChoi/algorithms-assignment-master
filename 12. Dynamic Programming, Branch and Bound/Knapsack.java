// �б� ������

package algorithm_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.PriorityQueue;

class node implements Comparable<node> {
	// ť�� ���� ���� Ŭ���� ����
	// compartTo�� ���ǵǾ��ִ� Comparable�� �������̽��� ����ϱ� ���� implements Comparable<node>�� ���� ����

	int level; // Ʈ���� ������ ������ ������ ���� ����
	int profit; // ������ ������ ������ ���� ���� 
	int weight; // ����ġ�� ������ ������ ���� ����
	float bound; // �Ѱ谪�� ������ ������ ���� ���� 
	int[] arr; // ������ ��θ� ������ ������ �迭 ����
	// �� ��帶�� ������ ��θ� �迭�� �����ϴ� ������ ��尡 ������ ������ ������ ��ΰ� �޶����� ������

	public node() { // ������
		
		// node Ŭ������ ��������� �ʱ�ȭ�ϴ� ����
		level = 0; //Ʈ���� ������ 0���� �ʱ�ȭ
		profit = 0; // ������ 0���� �ʱ�ȭ
		weight = 0; // ����ġ�� 0���� �ʱ�ȭ
		bound = 0; // �Ѱ谪�� 0���� �ʱ�ȭ
		arr = new int[500]; // ������ ��θ� ������ �迭�� ũ�� 500���� �����Ҵ� 
		
		for (int z = 0; z < 500; z++) { // 0~499����  ���鼭
			arr[z] = 0; // ������ ��θ� �����ϴ� arr�� ��� ���Ҹ� 0���� �ʱ�ȭ
	
		}
	}

	@Override // compareTo �޼ҵ� ������
	public int compareTo(node o) { 
		// �� �Լ��� ���� ������ bound ���� ���� ��� o�� bound ���� ���Ͽ�,
		// ���ǽ��� true�� ���� -1�� false�϶��� 1�� ��ȯ�Ѵ�
		
		return bound <= o.bound ? -1 : 1;
		// ���� ��尡 ������ ����� bound���� �� ũ�ų� ������ -1, ������ 1
	}
}

public class Knapsack { // �б����������� knapsack�� ����ϴ� Ŭ���� ����
	// �Լ����� ���������� ���Ǹ� �ʱ�ȭ�Ǿ� ����� ������ ������� ������ �߻��ϹǷ� ������ ���� ���������� ������
	
	public static int maxprofit; // �ִ� ������ �����ϱ� ���� ������ ���� ����
	public static int BackPackWeight; // ������ �ִ��Ѱ蹫�Ը� �������ֱ� ���� ������ ����
	public static int[] Weight = new int[500]; // ������ ���Ը� �������� ������ �迭
	public static int[] Price = new int[500]; // ������ ����ġ�� �������� ������ �迭

	public static int numbest = 0; // ���� ��带 �����ϴ� ������ ����

	public static int[] Order = new int[500]; // ������ �ε����� �����ϱ� ���� ������ �迭
	public static int[] Result = new int[500]; // ������ ���Դ� ����ġ�� �������� ������ �迭
	public static int[] bestway = new int[500]; // ������ ��θ� �����ϴ� ������ �迭
	
	
	public static void main(String[] args) throws FileNotFoundException { 
		// ���� �Լ� ����
		// ���� �Լ� �κп����� ������ �Է¹����Ƿ� FileNotFoundException ����ó���� �������ش�

		int i = 0; // �迭�� �ε����� �����ϱ� ���� ������ ����

		Scanner input = new Scanner(System.in); // ����ڿ��� �Է��� �ޱ� ���� Scanner Ŭ����
		Scanner in = new Scanner(new File("weight.txt")); 
		// weight.txt ������ �б� ���� Scanner Ŭ���� ��ü ����
	
		while (in.hasNextInt()) { // ���Ͽ��� ���� �о��� �� ������ ���� ���� �������̸�
			Weight[i] = in.nextInt(); // �� ���� Weight �迭�� �о���δ�
			i++; // i �� ����
		}

		i = 0; // �ε����� �������ִ� ���� i�� �ʱ�ȭ
		in = new Scanner(new File("price.txt")); 
		// price.txt ������ �б� ���� Scanner Ŭ���� ��ü ����
		
		while (in.hasNextInt()) { // ���Ͽ��� ���� �о��� �� ������ ���� ���� �������̸�
			Price[i] = in.nextInt(); // �� ���� Price �迭�� �о���δ�
			i++; // i �� ����
		}

		for (int k = 0; k < 500; k++) { // 0 ���� 499���� �ݺ����� ���鼭

			Result[k] = Price[k] / Weight[k]; // Result �迭�� ���Դ� ����ġ�� ���� ������
			Order[k] = k; // ���Ͽ� ����� ������ ������ ������ �״�� order �迭�� ������
		}

		System.out.print("Input Max Weight of BackPack : ");
		// ����ڿ��� ������ �ִ��Ѱ� ���Ը� �Է��϶�� �޼����� �����
		BackPackWeight = input.nextInt(); // ����ڿ��� �Է¹���

		int temp; // �ӽú���
		int j = 0; // �ݺ����� ������ ������ ����
		i = 0; // i�� 0���� �ʱ�ȭ

		// ���Ͽ� ����Ǿ��ִ� ���Կ� ����ġ�� �޾� �迭�� �����Ͽ� ���Դ� ����ġ(Result �迭�� ����Ǿ�����)�� ū �ͺ��� ����
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
		System.out.println("****** Result ******");
		maxprofit = best_first_branch_and_bound(500); // best_first_branch_and_bound �Լ� ȣ��

		System.out.println("MaxProfit : " + maxprofit); // �ִ� ������ ������ִ� ��¹�
		System.out.println();

		for (int k = 0; k <= numbest; k++) { // 0���� numbest���� �ݺ����� ���鼭
			System.out.print("No." + Order[k] + " Node : ");
			if (bestway[k] == 1) // ���� bestway�� ���� 1�̶��
				System.out.println("Include"); // bestway �迭�� k�ε����� ��带 ���Խ�Ų�ٴ� �޼��� ���
			else // ���� bestway�� ���� 0�̶��
				System.out.println("Not Include"); // bestway �迭�� k�ε����� ��带 ���Խ�Ű�� �ʴ´ٴ� �޼��� ���
		}
		
		in.close(); // Scanner ��ü in�� close ��Ŵ
		input.close(); // Scanner ��ü input�� close ��Ŵ
	}

	public static int best_first_branch_and_bound(int n) { // �б����������� �賶ä��⸦ ������ �Լ�

		PriorityQueue<node> q = new PriorityQueue<node>(); // �켱���� ť ����
		
		node v = new node(); // �θ� ��带 ����
		node u = new node(); // �θ��忡�� �ڽĳ�带 �����ϱ� ���� ��� ����
		int maxprofit; // �ִ� ������ �����ϴ� ������ ����

		// �Ѹ����� ���� �����ϱ� ����
		q.clear(); // �켱���� ť �ʱ�ȭ
		v.level = -1; // v�� ������ -1�� ����
		v.profit = 0; // v�� ������ 0���� ����
		v.weight = 0; // v�� ���Ը� 0���� ����
		maxprofit = 0; // v�� �Ѹ����� �ǰ� �ּ�ȭ
		v.bound = bound(v, n); // v�� bound�� ���
		q.add(v); // q��� ť�� v�� ���� ����

		while (!(q.isEmpty())) { // ���� q�� ������� �ʴٸ�
			v = q.peek(); // q�� ���� ó�� ��带 peek �ؼ� v�� ������ ����
			q.remove(); // q�� ���� ó�� ��带 ����

			if (v.bound > maxprofit) { // v ��尡 �ִ� �������� ������
				
				// �켱���� ť���� ���� ���� �켱������ ������ �ִ� v����� ���� ������ �߰� ���θ� �Ǵ��ϰ�, 
				// ������ ����� ��� ���� �������� �߰��� ��带 ť�� ����
				u = new node(); // u�� ���ο� ��带 �����
				
				// ���� ������ ����� �ڽ� ������ �����Ͽ� �켱���� ť�� �����ϱ� ����
				u.level = v.level + 1; // u�� ������ v�� �������� 1 ���������ְ�
				u.weight = v.weight + Weight[u.level]; // u�� ���Ը� v�� ���Կ� ���� Weight �迭�� u�� ���� �ε����� �ִ� ���� ������ ����
				u.profit = v.profit + Price[u.level]; // u�� ������ v�� ������ ���� Price �迭�� u�� ���� �ε����� �ִ� ���� �����ش�
				u.arr = v.arr.clone(); // u�� �迭�� v�� �迭�� �����Ͽ� �����Ѵ�
				u.arr[u.level] = 1; // u�� �迭�� u�� ���� �ε����� 1�� �����Ѵ�(u.arr�� �迭�� 1�� ���� ���´ٴ� ���� �ش� ������ ��带 ������ ��ο� �߰��Ѵٴ� ���̴�)

				if (u.weight <= BackPackWeight && u.profit > maxprofit) { 
					// ���� u�� ���԰� ���濡 ���� �� �ִ� �ִ�ġ���� �۰ų� ����, u�� ������ �ִ� �������� �۴ٸ�
					maxprofit = u.profit; // �ִ������� u�� �������� �����ϰ�
					numbest = u.level; // ���� ��带 �����ϴ� ������ numbest�� ���� u�� ������ ���Խ�Ų��

					for (int z = 0; z <= numbest; z++) { // 0���� numbest���� ���鼭
						bestway[z] = u.arr[z]; // bestway �迭�� u�� �迭�� ���� ����
					}
				}

				u.bound = bound(u, n); // u�� �Ѱ谪�� ����(bound �Լ��� ȣ����)
				if (u.bound > maxprofit) { // ���� u�� �Ѱ谪�� ���� �ִ��������� ũ�ٸ�
					q.add(u); // �켱���� ť�� u�� ��带 �߰�
				}
				
				
				// �켱���� ť���� ���� ���� �켱������ ������ �ִ� v����� ���� ������ �߰� ���θ� �Ǵ��ϰ�, 
				// ���� �������� �߰����� ���� ��带 ť�� ����
				u = new node(); // u�� ���ο� ��带 �����	
				u.level = v.level + 1; // u�� ������ v�� �������� 1 ���������ְ�
				u.weight = v.weight; // u�� ���Կ� v�� ���Ը� �����Ѵ�
				u.profit = v.profit; // u�� ������ v�� ������ �����Ѵ�
				u.arr = v.arr.clone(); // u�� �迭�� v�� �迭�� �����Ͽ� �����Ѵ�
				u.arr[u.level] = 0; // u�� �迭�� u�� ���� �ε����� 0�� �����Ѵ�(u.arr�� �迭�� 0�� ���� ���´ٴ� ���� �ش� �������� ������ ��ο� �߰����� �ʴ´ٴ� ���̴�)
				u.bound = bound(u, n); // // u�� �Ѱ谪�� ����(bound �Լ��� ȣ����)

				if (u.bound > maxprofit) { // ���� u�� �Ѱ谪�� ���� �ִ��������� ũ�ٸ�

					q.add(u);// �켱���� ť�� u�� ��带 �߰�
				}
			}
		}

		return maxprofit; // �ִ� ������ ��ȯ

	}

	// �󸶳� �� ���� �� �ֳ�(������)
	public static float bound(node u, int n) { // ����� �Ѱ�ġ�� ������ִ� �Լ�
		int j, k; // �ε����� �����ϱ� ���� ������ ���� ����
		int totweight; // ���� ���濡 ��� ��ü ���Ը� �����ϴ� ������ ���� ����
		float result; // ����� ������ �Ǽ��� ���� ����

		if (u.weight >= BackPackWeight) // ���� ���� ������ ������ ��ü ���Ժ��� ũ�ų� ������ 
			return 0; // ���̻� ���濡 ���� �� �����Ƿ� �������� ���� (0 ��ȯ)

		else { // ���� ���� ������ ������ ��ü ���Ժ��� ������ 
			result = u.profit; // ����� �����ϴ� ������ ����� ���� ���� ����
			j = u.level + 1; // j�� ��� ������ 1�� ����
			totweight = u.weight; // ���� ���濡 ��� ��ü ���Ը� �����ϴ� ������ ����� ���Ը� ����

			while (j < n && totweight + Weight[j] <= BackPackWeight) { 
				// ���� j�� n���� �۰�, ���� ���濡 ��� ��ü ���Կ� Weight �迭�� j��° ���� ���� ���� ������ ���� �� �ִ� �ִ�ġ���� �۰ų� ������
				totweight = totweight + Weight[j]; // ���� ���濡 ��� ��ü ���Ը� �����ϴ� ������ ���� totweight�� Weight �迭�� j��° ���� ���� ���� totweight�� �����ϰ�
				result = result + Price[j]; // ����� �����ϴ� result�� ���� ����ġ�� �����ϴ� Price�� ���� ���� result�� �����Ѵ�
				j++; // j�� ����
			}

			k = j; // k�� j�� ������ ��
			if (k < n) // ���� k�� n���� ������
				result = result + (BackPackWeight - totweight) * Price[k] / Weight[k];
			// ���� bound�� BackPackWeight - totweight(���濡 �ڸ��� ���� ��ŭ)* ������ ���Դ� ����ġ��ŭ �� ���� result�� �������ش�.
			return result; // result ��ȯ(���� bound ���� ��)

		}

	}
}