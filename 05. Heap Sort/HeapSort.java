package algorithm_05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HeapSort { // heap ������ ���� Ŭ���� ����

	public static int count = 0; // �迭�� ũ�⸦ ������ count ���� ����
	public static int count2 = -1; // heap ���Ŀ��� ��������Ʈ���� �����ϱ� ���� count2 ���� ����(�ε����� 0���� ����ϱ� ����)

	public static void main(String[] args) throws FileNotFoundException { // �����Լ� ����
		// ���⼭�� ������ �о��ִ� �κ��� �����Ƿ� FileNotFoundException�� ���� ����ó�� ���ش�

		int[] arr; // ���Ͽ��� �о�� ������ �迭�� ������
		int[] arr2; // ���� ���Խ�Ų ����� �������� �迭�� ������
		int[] arr3; // ���� ������Ű�� ������ ����� �������� �迭�� ������(�� ������ ��ģ ���� ���ĵ� �迭�� �������ִ� ����)

		String fname; // ���� �о���� ���� �̸��� ���ڿ� ������ ����
		String fname2; // ���� ������ ���� �̸��� ���ڿ� ������ ����
 
		Scanner in2 = new Scanner(System.in); // ����ڿ��� �Է��� �ޱ����� Scanner Ŭ���� ����

		System.out.println("----------------- Heap Sort Start!! -----------------");
		System.out.println();
		System.out.printf("�а����ϴ� ������ �̸��� �ۼ��Ͻÿ�(�� a.txt) => "); // ����ڿ��� �а����ϴ� ������ �̸��� �Է��϶�� �޼��� ���
		fname = in2.nextLine(); // ����ڰ� �Է��� ������ �̸��� fname�� ������
		System.out.println(); // �پ�⸦ ���� ��ɹ�
		System.out.println(">> ���� �� : " + fname + "�� ������ ������ �����ϴ�");
		System.out.println();

		
		
		Scanner in = new Scanner(new File(fname));
		// ������ �о���� ���� Scanner Ŭ���� ���� 
		
		while (in.hasNextInt()) { // ���� ���� �����Ͱ� �ִٸ�
			in.nextInt(); // ���� �����͸� �о����
			count++; // �о���� ���Ͽ� ����� �����Ͱ� �� ������ ���� ���� count ���� ����
		}

		arr = new int[count]; // arr �迭�� count ũ��� �����Ҵ� ����
		arr2 = new int[count]; // arr2 �迭�� count ũ��� �����Ҵ� ���� 
		arr3 = new int[count]; // arr3 �迭�� count ũ��� �����Ҵ� ����

		in = new Scanner(new File(fname));
		// ������ ó������ �ٽ� �о��ֱ� ����
		
		// ���� ���Ͽ� ����� �����͸� �ֿܼ� ����ϱ� ����
		int i = 0; // �ݺ��� ���� ���� i ����
		while (i < count) { // i�� count���� ������ while���� ����
			arr[i] = in.nextInt(); // arr �迭�� ���Ͽ��� ���� �����͸� �ϳ��� �о� �������ش�
			
			// �� �ٿ� 5���� �����͸� ����ϱ� ����
			if ((i + 1) % 5 == 0) { // ���� i+1�� 5�� ������(i+1�� �ϴ� ������ 0���� �����ϸ� ����� ����� ���� �ʱ� ����)
				System.out.printf("%15d", arr[i]); // ������ ���
				System.out.println(); // ���⸦ ���� ��ɹ�
			}

			else // ���� 5�� ����� �ƴ϶��
				System.out.printf("%15d", arr[i]); // �׳� ������ ���

			i++; // ���� i�� ������Ŵ
		}

		for (int k = 0; k < count; k++) { // count ��ŭ �ݺ��ϸ鼭
			insert_max_heap(arr2, arr[k]);
			// insert_max_heap�̶�� �Լ��� �����Ų��. �� �Լ��� arr�� �迭�� �ִ� ������ ���ʴ�� �����Ͽ� ����Ʈ��(arr2)�� �־��ֱ� ����

		}

		for (int k = 0; k < count; k++) { // count ��ŭ �ݺ��ϸ鼭
			arr3[count2] = delete_max_heap(arr2);
			// delete_max_heap�̶�� �Լ��� �����Ų��. �� �Լ��� ����Ʈ���� ����� arr2 ������ ������Ű�鼭 arr3�� �����ϱ� �����̴�
			// ����Ʈ���� ����� arr2 ������ �⺻������ ������ �Ǿ������Ƿ� arr3�� ���� ���ĵǾ��ִ�.

		}

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.printf("�����ϰ��� �ϴ� ������ �̸��� �ۼ��Ͻÿ�(�� a.txt) => ");
		fname2 = in2.nextLine();
		// �迭�� ���� �����Ϸ��� �ϴ� ������ �̸��� ����ڿ��� �Է¹��� 

		try { // FileWriter Ŭ������ IOException�� �߰��������
			FileWriter out = new FileWriter(fname2);
			// ������ �ۼ��ϴ� Ŭ������ �����Ͽ� out�̶�� �̸��� ���� ��ü�� ������

			for (int k = 0; k < count; k++) { // �ݺ����� 0���� count������ ���鼭
				out.write(Integer.toString(arr3[k]));
				// ���Ͽ� arr �迭�� ���� String���� ��ȯ�ؼ� �ۼ��Ѵ�
				out.write("\r\n"); // ���� �� Ŀ���� ���� ������ �ٲ���
			}
			out.close(); // ���� ��ü �����Ŵ
		} catch (IOException e) { // ���� IOException�� �߻��ϸ�
			System.out.println("Error!"); // ������� �޼����� ���
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

		in.close();
		in2.close();

	}

	public static void insert_max_heap(int[] arr, int key) { // ���� �����͸� �����ϱ� ���� �Լ�

		int j; // �ݺ����� �������� ���� ����
		int temp; // �� ���� ���� swap �ϱ� ���� �ӽ� ���� ����

		count2 = count2 + 1; // �����Ͱ� ���Ե� ������ count2�� ������Ŵ
		j = count2; // ������Ų count2 ������ j�� ����
		arr[j] = key; // ���� Ʈ���� ���� �������� key(������ ������)�� ����

		while (j != 0 && arr[j] > arr[(j - 1) / 2]) { // ���� j�� 0�� �ƴϰ� �θ��尡 �ڽĳ�庸�� �۴ٸ�(����Ʈ������ �׻� �θ���� �ڽĳ�庸�� Ŀ����)
			temp = arr[(j - 1) / 2]; // �θ��带 temp�� �������ְ�
			arr[(j - 1) / 2] = arr[j]; // �θ��忡 �ڽĳ�带 ����
			arr[j] = temp; // �ڽĳ�忡 temp�� ���� ����
			j = (j - 1) / 2; // j�� �θ���� ������ �� �ְ� ��
		}

	}

	public static int delete_max_heap(int[] arr) { // ���� �����͸� �����ϱ� ���� �Լ�(�׻� ��Ʈ�� ��ȯ��)
		int item; // ������ ���� �����ϴ� ����

		item = arr[0]; // ��Ʈ(�ֻ��� �θ���)�� ���� item�� ����(item�� ������ ���� �����ϱ� ����)
		arr[0] = arr[count2]; // ��Ʈ�� ���� ���� ������ ���� �����Ѵ�
		count2 = count2 - 1; // ����Ʈ������ ��� �ϳ��� �����Ǿ����Ƿ� count2�� �ϳ� ���ҽ�Ŵ

		int i = 1; // �ݺ��� �ϱ����� ���� i ����
		int largest; // �ڽ� ��� �� ū ���� �����ϱ� ���� ����
		int temp; // �� ���� ���� swap �ϱ� ���� �ӽ� ���� ����

		while (i <= count2) { // i�� count2�� ���� ������ while���� ����
			if ((i < count2) && (arr[i + 1] > arr[i])) // ���� i�� count2���� �۰�, �ڽ� ��� �߿� �� ū ���� largest�� ����
				largest = i + 1;
			else
				largest = i;

			if (arr[(largest - 1) / 2] > arr[largest]) // ���� �θ������ �ڽĳ�庸�� ũ�� 
				break; // break
			else { // ���� �θ��尡 �ڽĳ�庸�� ������
				// �θ���� �ڽĳ�� ���� swap ��Ŵ
				temp = arr[(largest - 1) / 2];
				arr[(largest - 1) / 2] = arr[largest];
				arr[largest] = temp;
			}
			i = largest * 2 + 1; // ���� �ڽĳ�带 �˻��ϱ� ���� i�� largest�� 2�� +1�� �Ѵ�

		}

		return item; // ������ ���� return ��
	}
}
