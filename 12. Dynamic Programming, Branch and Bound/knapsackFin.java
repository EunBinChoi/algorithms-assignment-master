// ���� ��ȹ

///**
// * 
// * @author Jaeu Jeong
// * email : wodndb@koreatech.ac.kr
// * 
// * ������ȹ������ 0-1 knapsack ���� �ذ��ϱ�
// * �� �ҽ��ڵ�� 2016�г⵵ 1�б� �ѱ�����������б� ��ǻ�Ͱ��к� �˰��� �� �ǽ� �ǽ����� �����ڷ�μ�,
// * Foundation of Algorithms using C++ Algorithm (Richard Neapolitan / Kumarss Naimipour ��)
// * ���� �Ұ��� ������ȹ�� �˰��� ������ ���� ���� �ҽ��ڵ��Դϴ�. (�ǻ��ڵ� ���� ���丸 ����) 
// * ����� �������� �̿�� �� ������ �����ϴ�.
// * 
// */
package algorithm_12_2;

import java.util.*;
import java.io.*;

// ���� 1����, ���� 0���� �����Ͽ����ϴ�
public class knapsackFin { // ������ȹ������ �賶ä��⸦ �����ϴ� Ŭ����

	// ������ ���� ����
	static int item[][]; 
	// ������ ���� : ������ ��ȣ(ID), ����(PRICE), ����(WEIGHT), �߷��� ����(RESULT)

	// �ε����� �����ϱ� ���� ��
	static int INDEX = 0; // ������ ��ȣ(ID) ���� 0���� ����
	static int PRICE = 1; // ����(PRICE) ���� 1���� ����
	static int WEIGHT = 2; // ����(WEIGHT) ���� 2���� ����
	static int RESULT = 3; // �߷��� ����(RESULT) ���� 3���� ����

	// ���� ��ȹ�� ����
	static int W; // ���� �Ѱ� ���Ը� �����ϴ� ������ ����(����ڰ� �Է¹��� �� �ְ� ��)
	static int maxProfit; // ������ ������ �����ϴ� ������ ����
	static int maxElement = 500; // �ִ� ������ ������ 500���� �ʱ�ȭ
	static int maxWeight = 500; // �ִ� ���Ը� 500���� �ʱ�ȭ
	
	static int numbest; // ������ ������ ���� �� �ִ� ������ �κ������� ������ ������ ��ȣ�� �����ϴ� ������ ����
	
	static int[] bestset; // ������ ������ ���� �� �ִ� �����۵��� �κ�����
	static int[][] P; // P[i][w] : 1~i��° �������� ������ �� w�̳����� ���� �� �ִ� ������ ����
	// ������ 0~i ������ ����� ����, �������� ���� 
	static boolean[][] E; // P[�ִ� ������ ����][������ �Ѱ� ����]�� ���ϱ� ���� �� �ʿ��� P[][]���� ��������
							// ǥ���ϱ� ���� �迭 (���� ���δ� ��Ʈ��)

	static int[] price = new int[500]; // ���Ͽ� ����Ǿ��ִ� 500���� ������ �޾ƿ��� ���� �迭�� ũ�⸦
										// 500���� ����
	static int[] weight = new int[500]; // ���Ͽ� ����Ǿ��ִ� 500���� ���Ը� �޾ƿ��� ���� �迭�� ũ�⸦
										// 500���� ����

	/**
	 * URL\
	 * http://stackoverflow.com/questions/15452429/java-arrays-sort-2d-array
	 * 
	 * @param arr
	 *            Item to sorting
	 */
	public static void readData(int n, String fileName, int[] A) throws IOException {
		// ���Ͽ��� ���� �о���̴� �Լ�
		// ���Ͽ��� ���� �о���� ������ IOException�� ���� ����ó���� ������Ѵ�.
		Scanner sc = new Scanner(new FileInputStream(fileName));
		// ���Ͽ��� ���� �о���̱� ���� Scanner Ŭ������ ��ü�� ����
		for (int i = 0; i < n; i++) { // �ݺ����� 0~n-1���� ���鼭
			A[i] = sc.nextInt(); // ���Ͽ� �д� ���� �о���̸鼭 A[i]�� ����
		}
		sc.close(); // sc ���� close ��Ŵ

	}

	static void sortItem(int[][] item, int n) { // �迭�� ����� ������ �����ϴ� �Լ�
		Arrays.sort(item, new Comparator<int[]>() {
			// Comparator��� ��ü�� ����µ�, JAVA������ �̷��� �ζ������� �����ϴ� ����� �ִ�.
			public int compare(int[] item1, int[] item2) {
				return Integer.compare(item1[n], item2[n]); // ��������. �ݴ�� ��������.
			}
		});
	}

	/**
	 * ��Ʈ���� ���ϴ� �Լ� P[n][W]�� ���ϴ� �� �� �ʿ��� [][]���� ǥ���ϴ�, �� Entry(E[][])�� ���ϴ� �Լ�.
	 * ��������� ���ư��� ������ �Ķ���Ͱ� �ݵ�� �ʿ��ϴ�.
	 * 
	 * @param i
	 *            Ž�� ��� : 1~i��° ������
	 * @param accW
	 *            �Ѱ� ���� : 1~i��° �������� ���濡 ���� �� �Ѱ� ����. ����(accumulated) �����̶� ������
	 *            acc ����.
	 */
	
	static void decideEntry(int i, int accW) { // ��Ʈ��(E[][])�� ���ϴ� �Լ�(E �迭�� �ؿ������� �ö󰡸鼭 ���ȣ���� ������ ��ȿ�� ������ �ƴ����� ǥ������)
		// accw�� �ʱⰪ�� ����ڰ� �Է��� W�� ����
		E[i][accW] = true; // E[i][accW]�� ���� true�� ����
		if (i > 1) { // ���� i�� 1���� ũ��(���� �� �ִ� �������� ����������)
			if (!E[i - 1][accW]) { // ���� E[i][accW](���Ծȵ� ��)�� ���� false�̸� ������ ���� ����(�̹� true�� ���ȣ���� �ʿ䰡 ����)
				decideEntry(i - 1, accW); // ��� ȣ�� (���� �� �ִ� ������ ǥ��)
			} 
			if (accW >= item[i][WEIGHT]) { // accW�� i��° ���Ժ��� ũ�ų� ������(�� �� ����)
				if (!E[i - 1][accW - item[i][WEIGHT]]) { // ���� ���Ե� ���� false ���¸�
					decideEntry(i - 1, accW - item[i][WEIGHT]); // ��� ȣ�� (���� �� �ִ� ������ ǥ��)
				}
			}
		}
	}

	/**
	 * evalMaxP�� P[][] ���� ���ϴ� �Լ� �������� : decideEntry �Լ��� ������Ѿ� ��.
	 */
	static void evalMaxP()  {
	//	int maxKnapsackWeight = maxElement * maxWeight; // �ƹ��� ���濡 �������� ���� �־ ������ ���� * ������ �ִ� ���Ժ��ٴ� �۴�.
		// ������ ū ��	
		
		// ������ �迭 ��ü�� ���鼭
		for (int i = 1; i <= maxElement; i++) { 
			for (int j = 0; j <= W; j++) {
				if (E[i][j] == true) { // ���ؾ� �� ��Ʈ����� �ϸ�
					if (j >= item[i][WEIGHT]) { // �������� ���԰� ���� ���Ժ��� Ŭ ��(�Ʒ��� ������ ���Ⱑ ������ ��)
						P[i][j] = Math.max(P[i - 1][j], item[i][PRICE] + P[i - 1][j - item[i][WEIGHT]]);
						// ������ �������� �ȵ��� ���, �������� �������� ���� ���
					} else { // �������� ���� ���ϴ� ���
						P[i][j] = P[i - 1][j];
					}
				}
			}
		}
	}

	/**
	 * ������ ������ ������ ã�Ƽ� bestSet�� ����. �������� : decideEntry �Լ��� evalMaxP �Լ��� �����ؾ� �����
	 * �����Ѵ�.
	 */
	static void findBestSet() {
		int tWeight = W; // �ӽ÷� ������ ���� ����. �ϴ� P[n][W]���� Ž���ؾ� �ϹǷ� W(����ڰ� �Է��� ������ ����)�� �����Ѵ�.
		for (int i = 500; i >= 1; i--) {
			if (P[i][tWeight] == P[i - 1][tWeight]) { // i��° �������� �������� �ʾ����� 1~i-1 �������� ������
											// ���Ͱ� 1~i �������� ������ ������ �����ϴ�. �� i��°
											// �������� ���� �ʾҴٴ� �ǹ̴�.
				bestset[i] = 0; // i��° �������� ������ ������ �κ����տ� ���Ե��� �ʴ´�.
			} else { // i��° �������� ���������� 1~i-1 �������� ������ ���Ͱ� 1~i �������� ������ ������ ��������
						// �ʴ�. ��, i��° �������� �Ծ��ٴ� �ǹ̴�.
				numbest = Math.max(numbest, i); // numbest ����(���� �������� ������� �ε����� ����)
				bestset[i] = 1; // i��° �������� ������ ������ �κ����տ� ���Եȴ�.
				tWeight = tWeight - item[i][WEIGHT];
				// i��° �������� ������ ������ �κ����տ� ���Ե� ���̹Ƿ� ���Ը� �ݿ��Ͽ� i-1��°�� ��츦 �ٽ� ���Ѵ�.
					
			}
		}
	}

	/**
	 * �غ��Լ� ����� �� ���Ͽ��� �����͸� �о����, �迭�� �ʱ�ȭ�ϴ� ������ �Ѵ�.
	 * 
	 * @throws IOException
	 *             // ���� ����� ����ó��
	 */
	static void prepareDPKnapSack() throws IOException { // �賶ä��⿡ �ʿ��� ���������� �ʱ�ȭ �� �������� ������������ �����ϴ� �Լ�
		// ���Ͽ��� �о���̴� readData �Լ��� �����Ƿ�, IOException���� ����ó���� �������ش�
		// 1. ������ �Ѱ� ���Ը� ����ڷκ��� �Է¹޴´�.
		System.out.print("Input Max Weight of BackPack : "); // ����ڷκ��� �ִ� �Ѱ� ���Ը�
																// �Է¹ޱ� ���� �޼���
		Scanner sc = new Scanner(System.in); // Scanner Ŭ������ ��ü ���� sc ����
		W = sc.nextInt(); // ����ڿ��� �ִ� �Ѱ� ���Ը� �Է¹���

		// 2. ���� �������� �ʱ�ȭ�Ѵ�.
		item = new int[maxElement + 1][4]; // ������ �ʱ�ȭ
		bestset = new int[maxElement + 1]; // �ְ� ������ ���� �� �ִ� ���ǵ�
		P = new int[maxElement + 1][W + 1];
		// ���� ��ȹ�� : ������ ���Կ� ���� ���� �� �ִ� �ִ� ������ ������ �迭
		E = new boolean[maxWeight + 1][W + 1];
		// ���� ��ȹ�� : ������ ���Կ� ���� ���� �� �ִ� �ִ� ������ ������ �迭

		numbest = 0; // numbest�� ���� 0���� �ʱ�ȭ
		maxProfit = 0; // maxProfit�� 0���� �ʱ�ȭ

		// 3. ���Ͽ��� �������� ������ �Է¹޴´�.
		readData(500, "price.txt", price);
		readData(500, "weight.txt", weight);

		for (int i = 0; i < 500; i++) { // item �迭�� ���Ҹ� ����
			item[i][0] = i; // ù��° ���Ҹ� index�� ����
			item[i][1] = price[i]; // �ι�° ���Ҹ� �������� ����
			item[i][2] = weight[i]; // ����° ���Ҹ� ���Է� ����
			item[i][3] = price[i] / weight[i]; // �׹�° ���Ҹ� �߷��� �������� ����
		}

		// 4. �Է¹��� �������� ���Դ� ����ġ�� �����Ѵ�.
		sortItem(item, RESULT);
	}

	// ���� �Լ�: ��Ʈ��ŷ �Լ��� �����ϰ� �� ����� ����ϴ� �Լ�.
	// prepareBT �Լ��� �����Ų ������ �� �Լ��� �����Ͼ� �Ѵ�.
	static void startDPKnapSack() {
		decideEntry(maxElement, W); // ����� ��Ʈ�� ���ϱ�
		evalMaxP(); // ������ ���� ��Ʈ������ �̿��Ͽ� ��������� P[n][W]�� ���ϱ�
		findBestSet(); // ������ ������ ã�´�.
		maxProfit = P[maxElement][W]; // ������ ����
	}

	public static void main(String[] args) throws IOException {
		prepareDPKnapSack(); // �賶ä��⿡ �ʿ��� ���������� �ʱ�ȭ �� �������� ������������ �����ϴ� �Լ�
		startDPKnapSack(); // �����Լ�(��Ʈ��ŷ �Լ�)�� �����ϰ� �� ����� ����ϴ� �Լ� ȣ��

		System.out.println();
		System.out.println("****** Result ******");
		System.out.println("MaxProfit : " + maxProfit);
		
		for (int r = 0; r <= numbest; r++) {
			System.out.print("No." + item[r][INDEX] + " Node : ");
			// �ε��� ���
			if (bestset[r] == 1) // ���� bestset�� ���� 1�̶��
				System.out.println("Include"); // bestset �迭�� r�ε����� ��带 ���Խ�Ų�ٴ�
												// �޼��� ���
			else // ���� bestset�� ���� 0�̶��
				System.out.println("Not Include"); // bestset �迭�� r�ε����� ��带
													// ���Խ�Ű�� �ʴ´ٴ� �޼��� ���

		}

	}
}
