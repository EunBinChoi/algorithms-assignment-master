package algorithm_04;

import java.util.*;

import java.io.*;

public class MergeSort { // �պ������� ����ϴ� Ŭ����

	public static void main(String[] args) throws FileNotFoundException { // �����Լ�
		// ���⼭�� ������ �о��ִ� �κ��� �����Ƿ� FileNotFoundException�� ���� ����ó���� ���ش�

		System.out.println("------------------------------- Merge Sort Start ! -------------------------------");
		System.out.println();

		int[] arr; // �պ������� �ϱ� ���� ������ �迭(arr)�� ������
		int count = 0; // �о���̴� ���Ͽ� �ִ� ������ ������ �����ϴ� ����
		String fname1; // �о���� ���� �̸��� ������ ������ ����

		Scanner in2 = new Scanner(System.in); // ������� �Է��� ����ϴ� Scanner ��ü�� ����

		System.out.printf("�а����ϴ� ������ �̸��� �ۼ��Ͻÿ�(�� a.txt) => ");
		fname1 = in2.nextLine(); // fname1 ������ in2 ��ü�� �о���� ���� �̸��� ������

		System.out.println(); // �پ�⸦ ���� ��ɹ�
		System.out.println(">> ���� �� : " + fname1 + "�� ������ ������ �����ϴ�"); // ���� ����
																		// ���Ͽ�
																		// �����
																		// ������
																		// ���
		
		Scanner in = new Scanner(new File(fname1)); // ������ �о���� ���� Scanner ��ü
													// �����ϰ� fname1�� �̸��� ���� ������
													// ������
		while (in.hasNextInt()) { // ���� ���Ͽ� ����� ���� ���� int ���̶��
			in.nextInt(); // ���Ͽ��� ���� ���� ����
			count++; // ���Ͽ� ����� ������ ������ �����ϴ� count ������ ������Ŵ
		}
		arr = new int[count]; // ũ�� count�� �迭�� ������

		in = new Scanner(new File(fname1)); // fname1�� �̸��� ���� ������ ������(������ ó������
											// �ٽ� �б� ���� �����)
		int i = 0; // ���Ͽ� ����� ������ �迭�� ����� �ε����� �����ϱ� ���ؼ� ���� i�� ����
		while (i < count) { // i�� �迭�� ũ�⺸�� ������

			arr[i] = in.nextInt(); // arr �迭�� ������� ���Ͽ��� �о�� ���� ������
			if ((i + 1) % 5 == 0) { // ���� i+1�� 5�� ������(����� 5���� �ϱ� ����, i+1�� ������
									// i�� 0���� �����ϱ� �����̴�)
				System.out.printf("%15d", arr[i]); // �迭�� ���� �����
				System.out.println(); // �׸��� 5���� ����� ���̹Ƿ�, 5�� ����϶����� ���⸦ ��
			} else
				System.out.printf("%15d", arr[i]); // 5�� ����� �ƴ϶��, �迭�� ���� �����

			i++; // i�� ���� ������Ŵ
		}

		mergeSort(count, arr); // mergesort �Լ��� ȣ����

		String fname2; // �պ������� ����� ���Ϸ� �����ϱ� ���� ���� �̸��� ������ ������ ����

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.printf("�����ϰ��� �ϴ� ������ �̸��� �ۼ��Ͻÿ�(�� a.txt) => ");
		// �պ������� ����� ����ڰ� ���ϴ� ������ �̸����� �����ϱ� ���� ����ڰ� ���ϴ� ���� �̸��� ����ڰ� �������ִ� ��ɹ�
		fname2 = in2.nextLine(); // ����ڰ� �ۼ��� �����̸��� in2 ��ü�� ���� �Է¹���

		try { // FileWriter Ŭ������ IOException�� �߰��������
			FileWriter out = new FileWriter(fname2);
			// ������ �ۼ��ϴ� Ŭ������ �����Ͽ� out�̶�� �̸��� ���� ��ü�� ������

			for (int k = 0; k < count; k++) { // �ݺ����� 0���� count������ ���鼭
				out.write(Integer.toString(arr[k]));
				// ���Ͽ� arr �迭�� ���� String���� ��ȯ�ؼ� �ۼ��Ѵ�
				out.write("\r\n"); // ���� �� Ŀ���� ���� ������ �ٲ���
			}
			out.close(); // ���� ��ü �����Ŵ
		} catch (IOException e) {
			System.out.println("Error!");
		}

		System.out.println();
		System.out.println(">> ���� �� : " + fname2 + "�� ������ ������ �����ϴ�");

		in = new Scanner(new File(fname2));
		// fname2�� �̸��� ���� ������ ������(������ ó������ �б� ���� �����)
		int k = 0;

		while (k < count) { // k�� count���� ���� �� �ݺ����� ���鼭
			if ((k + 1) % 5 == 0) { // k+1�� 5�� ����� ����
				System.out.printf("%15d", in.nextInt()); // ���Ͽ� ����� ���� ����ϰ�
				System.out.println(); // ���⸦ ��
			} else // 5�� ����� �ƴ� ������
				System.out.printf("%15d", in.nextInt()); // ���Ͽ� ����� ���� ��¸� ��

			k++; // k�� ������Ŵ
		}
		in.close(); // ��ü in �����Ŵ
		in2.close(); // ��ü in2 �����Ŵ

	}

	public static void mergeSort(int count, int arr[]) { // �迭�� �� ���� �迭�� ������ �۾�
		if (count > 1) { // count�� 1���� Ŭ ������ �۵��Ѵ�(count�� 1���� �۾����� �迭�� �� ���� ���� ��
							// ���� ������)
			int h = count / 2; // count�� 2�� ���� ���� m�� ����
			int m = count - h; // count���� h��ŭ �� ���� m�� ����

			int[] U = new int[h]; // �迭 U�� ���� ũ�� h�� �Ҵ�
			int[] V = new int[m]; // �迭 v�� ���� ũ�� m�� �Ҵ�

			for (int i = 0; i < h; i++) { // �ݺ����� ���鼭
				U[i] = arr[i]; // arr�� 0~h���� �迭 U�� ����

			}

			for (int i = h; i < count; i++) { // �ݺ����� ���鼭
				V[i - h] = arr[i]; // arr�� h~count���� �迭 V�� ����

			}

			mergeSort(h, U); // h�� ũ���� �迭 U�� �ٽ� mergeSort�� ȣ���Ͽ� �� ���� �迭�� ������ �۾���
								// �ݺ���
			mergeSort(m, V); // m�� ũ���� �迭 V�� �ٽ� mergeSort�� ȣ���Ͽ� �� ���� �迭�� ������ �۾���
								// �ݺ���
			merge(h, m, U, V, arr); // ������ �迭�� �ٽ� ��ġ�� merge �Լ��� ȣ����
		}
	}

	public static void merge(int h, int m, int U[], int V[], int S[]) { // ������
																		// �迭��
																		// �ٽ�
																		// ��ġ��
																		// �Լ�

		int i = 0, j = 0, k = 0; // �ݺ��� ���� ���� ����

		while (i < h && j < m) { // i�� j�� h,m���� ���� �� while���� ���鼭
			if (U[i] < V[j]) { // ���� V[j]���� U[i]�� �� ������
				S[k] = U[i]; // S[k]�� U[i]�� �����Ѵ�(�� ���� �� ����־ ������������ �ϱ� ���� ����)
				i++; // �迭 U�� ����ϴ� �ε��� i�� �ش��ϴ� ���� ����־����Ƿ�, i�� ����
			}

			else { // ���� V[j]���� U[i]�� �� ũ�ٸ�
				S[k] = V[j]; // S[k]�� V[j]�� �����Ѵ�(�� ���� �� ����־ ������������ �ϱ� ���� ����)
				j++; // �迭 V�� ����ϴ� �ε��� j�� �ش��ϴ� ���� ����־����Ƿ�, j�� ����
			}
			k++; // �迭 S�� ����ϴ� �ε��� k�� ������Ŵ
		}

		if (i >= h) { // i�� h���� Ŀ���ٴ� �ǹ̴� V �迭�� ���� ���Ҵٴ� �ǹ��̹Ƿ�
			for (int x = j; x < m; x++) { // j���� m���� S�迭�� ������
				S[k] = V[x];
				k++;

			}

		} else { // �װ� �ƴ϶��, U �迭�� ���� ���Ҵٴ� �ǹ��̹Ƿ�
			for (int x = i; x < h; x++) { // i���� h���� S�迭�� ������
				S[k] = U[x];
				k++;

			}

		}
	}
}
