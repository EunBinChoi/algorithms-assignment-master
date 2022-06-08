import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class Matrix_mutiplex { // ����� ���� ����ϴ� Ŭ���� �ۼ�
	public static void main(String[] args) throws IOException { // ���� �Լ� �ۼ�
		// ������ �а� ����ϴ� �κ��� �ֱ� ������ IOException ����ó���� ������Ѵ�

		System.out.println(
				"----------------------------- Multiplication of Matrix Start!! -----------------------------");
		System.out.println();

		int[][] Naive_Result = new int[1024][1024]; // ������ ����� ���� ������ �迭 ����
		int[][] Strassen_Result = new int[1024][1024]; // ��Ʈ���� ����� ���� ������ �迭 ����
		int[][] arr = new int[1024][1024]; // a.txt ������ ������ ������ �迭 ����
		int[][] arr2 = new int[1024][1024]; // b.txt ������ ������ ������ �迭 ����

		Scanner input = new Scanner(System.in); // ����ڿ��� �Է��� �ޱ����� Scanner Ŭ������ ��ü ���� ����

		System.out.print("Input Name of File Saving 'A' Maxtrix(eg. a.txt) : ");
		String fname1 = input.next(); // ����ڿ��� A ����� ����Ǿ��ִ� ������ �̸��� �Է��ϵ��� ��
		FileInputStream fin = new FileInputStream(fname1); // ������ �Է��� ����ϴ� FileInputStream Ŭ������ ��ü ���� ����

		int i, j; // �ݺ����� ���� ���� ���� ����
		for (i = 0; i < 1024; i++) { // ���� �ݺ����� ���鼭
			for (j = 0; j < 1024; j++) {
				arr[i][j] = fin.read(); // arr �迭�� ���Ͽ��� �Է¹��� ������ �����Ѵ�
			}
		}
		System.out.println(">> Success reading A Matrix!");

		System.out.print("Input Name of File Saving 'B' Maxtrix(eg. b.txt) : ");
		String fname2 = input.next(); // ����ڿ��� B ����� ����Ǿ��ִ� ������ �̸��� �Է��ϵ��� ��
		FileInputStream fin2 = new FileInputStream(fname2);

		for (i = 0; i < 1024; i++) { // ���� �ݺ����� ���鼭
			for (j = 0; j < 1024; j++) {
				arr2[i][j] = fin2.read(); // arr2 �迭�� ���Ͽ��� �Է¹��� ������ �����Ѵ�
				
			}
		}
		System.out.println(">> Success reading B Matrix!");
		System.out.println();
		System.out.println();

		System.out.println(
				"----------------------------- Measure Navie Algorithm Run Time -----------------------------");
		
		// ������ ����� ���� ����ð��� �����
		long StartTime = System.nanoTime(); // ������ ����� ���� �Լ��� �Ҹ��� ���� �ð��� ����Ͽ� StartTime ������ ����  
		Naive_Result = Naive_Matrix_Multiplication(1024, arr, arr2); // Naive_Matrix_Multiplication �Լ��� ȣ���Ͽ� ��� ���� Naive_Result �迭�� �����Ѵ�
		long endTime = System.nanoTime(); // ������ ����� ���� �Լ��� �Ҹ� �Ŀ� �ð��� ����Ͽ� endTime ������ ����
		long ITime = endTime - StartTime; // �� ������ �ð��� ����Ͽ� ITime ������ �����Ѵ�
		System.out.println(">> Naive Algorithm : " + ITime / 1000000.0 + " ms"); // ���� �ð��� ���
 		System.out.println();

		System.out
				.println("--------------------------- Measure Strassen Algorithm Run Time ---------------------------");

		// ��Ʈ���� ����� �� ����ð��� �����
		StartTime = System.nanoTime(); // ��Ʈ���� ����� �� �Լ��� �Ҹ��� ���� �ð��� ����Ͽ� StartTime ������ ����  
		Strassen_Result = Strassen_Algorithm(1024, arr, arr2); // Strassen_Algorithm �Լ��� ȣ���Ͽ� ��� ���� Strassen_Result �迭�� �����Ѵ�
		endTime = System.nanoTime(); // ��Ʈ���� ����� ���� �Լ��� �Ҹ� �Ŀ� �ð��� ����Ͽ� endTime ������ ����
		ITime = endTime - StartTime; // �� ������ �ð��� ����Ͽ� ITime ������ �����Ѵ�
		System.out.println(">> Strassen Algorithm : " + ITime / 1000000.0 + " ms"); // ���� �ð��� ���
		System.out.println();
		System.out.println();
	
		System.out.println("----------------------------------- Save Result File !! -----------------------------------");

		System.out.print("Input Name to save Result of Naive Algorithm(eg. result.txt) : ");
		// ������ ����� ���� ����� �����ϱ� ���� ���� �̸��� �Է¹޴´�
		String Naive_fname = input.next(); 
		printData(Naive_fname, Naive_Result); // �Է¹��� �̸��� ������ ������ �� ������ ����� ���� ����� �������ش�

		System.out.print("Input Name to save Result of Strassen Algorithm(eg. result.txt) : ");
		// ��Ʈ���� ����� ���� ����� �����ϱ� ���� ���� �̸��� �Է¹޴´�
		String Strassen_fname = input.next();
		printData(Strassen_fname, Strassen_Result); // �Է¹��� �̸��� ������ ������ �� �������� ����� ���� ����� �������ش�
		System.out.println();
		System.out.println(
				"----------------------------- End Multiplication of Matrix !! -----------------------------");

		input.close();
		fin.close();
		fin2.close();
	}

	

	public static void printData(String fileName, int[][] matrix) throws IOException { // ����� ���� ����� ���Ͽ� �ۼ��ϴ� �Լ�
		FileOutputStream out = new FileOutputStream(fileName); // ������ ����� ����ϴ� FileOuputStream Ŭ������ ��ü ���� ���� 

		int iLength = 0;
		String tChar;
		for (int i = 0; i < 1024; i++) { // ���� �ݺ����� ���鼭
			for (int j = 0; j < 1024; j++) {
				tChar = Integer.toString(matrix[i][j]); // ����� i,j �ε����� ����� ���ڸ� ��Ʈ������ ��ȯ�� tChar ������ ����
				iLength = tChar.length(); // iLength �������� tChar ������ ����� ���� ���̸� ��ȯ
				for (int k = 0; k < iLength; k++) { // �ݺ����� ���鼭
					out.write((int) tChar.charAt(k)); // tChar ����(��Ʈ������ ��ȯ�� ���� ����Ǿ�����)�� k �ε����� �ִ� ���ڸ� �ۼ�����
				}
				out.write((int) ' ');
			}
			out.write((int) '\n');
		}
		out.close();
	}

	public static int getThreshold(int n) { // �Ӱ谪�� ������ִ� �Լ�
		int th;
		double k = Math.floor(Math.log(n) / Math.log(2) - 4); // (log2(n)-4)�� floor���� ����Ͽ� k�� ����
		th = (int) Math.floor(n / Math.pow(2.0, k)) + 1; // n/2^k�� floor���� +1�� ����Ͽ� th�� ����
		return th; // th�� ���� ��ȯ

	}

	public static int[][] Naive_Matrix_Multiplication(int n, int[][] arr, int[][] arr2) { // ������ ����� ���� ����ϴ� �Լ�

		int i, j,k = 0; // �ݺ��� ���� ����
		int[][] result = new int[n][n]; // NxN �� ũ�⸦ ���� ������ �迭 ����
		for (i = 0; i < n; i++) {  // ���� �ݺ����� ���鼭
			for (j = 0; j < n; j++) { 
				result[i][j] = 0; // result[i][j] �� 0���� �ʱ�ȭ���ְ�
				for (k = 0; k < n; k++) { // ������ ����� ������ ����Ͽ� result �迭�� ����
					result[i][j] = result[i][j] + arr[i][k] * arr2[k][j];

				}
			}

		}

		return result; // result �迭�� ��ȯ

	}
 
	public static int[][] Matrix_Sub(int n, int[][] arr, int[][] arr2) { // ����� ������ ����ϴ� �Լ�

		int i,j; // �ݺ����� ���� ���� ����
		int[][] result = new int[n][n]; // NxN �� ũ�⸦ ���� ������ �迭 ����
		
		for (i = 0; i < n; i++) { // ���� �ݺ����� ���鼭
			for (j = 0; j < n; j++) { 
				result[i][j] = arr[i][j] - arr2[i][j]; // �� ����� �� ���ҳ��� ����

			}
		}

		return result;

	}

	public static int[][] Matrix_Sum(int n, int[][] arr, int[][] arr2) { // ����� ������ ����ϴ� �Լ�

		int i, j; // �ݺ����� ���� ���� ����
		int[][] result = new int[n][n]; // NxN �� ũ�⸦ ���� ������ �迭 ����

		for (i = 0; i < n; i++) { // ���� �ݺ����� ���鼭
			for (j = 0; j < n; j++) {

				result[i][j] = arr[i][j] + arr2[i][j]; // �� ����� �� ���ҳ��� ������

			}

		}
		return result;

	}

	public static int[][] Strassen_Algorithm(int n, int A[][], int B[][]) { // ��Ʈ���� ����� ���� ������ִ� �Լ�
		int[][] C = new int[n][n]; // ��Ʈ���� ����� ���� ����� �������� ������ �迭 ����

		if (n <= getThreshold(n)) { // ���� n�� ���� �Ӱ谪���� �۰ų� ������
			// ���⼭ �Ӱ谪�� N x N ��İ��� ����� ��, Strassen ���� Naive ��İ��� �������� ���������� �� N�̴�

			C = Naive_Matrix_Multiplication(n, A, B); // �Ϲ� ����� ������ ����ϴ� �Լ�(������ ����� ���� ����ϴ� �Լ�)�� ȣ����� C�� ����
		}

		else { // �׷��� �ʴٸ�

			// A�� �κ���� ����
			int[][] A11 = new int[n / 2][n / 2];
			int[][] A12 = new int[n / 2][n / 2];
			int[][] A21 = new int[n / 2][n / 2];
			int[][] A22 = new int[n / 2][n / 2];
			
			// B�� �κ���� ����
			int[][] B11 = new int[n / 2][n / 2];
			int[][] B12 = new int[n / 2][n / 2];
			int[][] B21 = new int[n / 2][n / 2];
			int[][] B22 = new int[n / 2][n / 2];

			for (int i = 0; i < n / 2; i++) { // �ݺ����� ���鼭
				for (int j = 0; j < n / 2; j++) {
					// ������ �κ���Ŀ� �ش��ϴ� �迭�� ������ �������ش�
					A11[i][j] = A[i][j]; 
					A12[i][j] = A[i][n / 2 + j];
					A21[i][j] = A[n / 2 + i][j];
					A22[i][j] = A[n / 2 + i][n / 2 + j];

					B11[i][j] = B[i][j];
					B12[i][j] = B[i][n / 2 + j];
					B21[i][j] = B[n / 2 + i][j];
					B22[i][j] = B[n / 2 + i][n / 2 + j];

				}

			}
			// ��Ʈ���� ���ǿ� ���� m1~m7�� ����
			int[][] m1 = new int[n / 2][n / 2];
			int[][] m2 = new int[n / 2][n / 2];
			int[][] m3 = new int[n / 2][n / 2];
			int[][] m4 = new int[n / 2][n / 2];
			int[][] m5 = new int[n / 2][n / 2];
			int[][] m6 = new int[n / 2][n / 2];
			int[][] m7 = new int[n / 2][n / 2];

			// ��Ʈ���� ����
			// m1 = (a11+a22)(b11+b22)
			// m2 = (a21+a22)(b11)
			// m3 = (a11)(b12-b22)
			// m4 = (a22)(b21-b11)
			// m5 = (a11+a12)(b22)
			// m6 = (a21-a11)(b11+b12)
			// m7 = (a12-a22)(b21+b22)
			
			m1 = Strassen_Algorithm(n / 2, Matrix_Sum(n / 2, A11, A22), Matrix_Sum(n / 2, B11, B22));
			m2 = Strassen_Algorithm(n / 2, Matrix_Sum(n / 2, A21, A22), B11);
			m3 = Strassen_Algorithm(n / 2, A11, Matrix_Sub(n / 2, B12, B22));
			m4 = Strassen_Algorithm(n / 2, A22, Matrix_Sub(n / 2, B21, B11));
			m5 = Strassen_Algorithm(n / 2, Matrix_Sum(n / 2, A11, A12), B22);
			m6 = Strassen_Algorithm(n / 2, Matrix_Sub(n / 2, A21, A11), Matrix_Sum(n / 2, B11, B12));
			m7 = Strassen_Algorithm(n / 2, Matrix_Sub(n / 2, A12, A22), Matrix_Sum(n / 2, B21, B22));

			// ��Ʈ���� ����� ��� C�� ���ϱ� ���� C�� �κп��ҵ��� ������ ���� ����
			// ��Ʈ���� ����
			// C1 = m1+m4-m5+m7
			// C2 = m3+m5
			// C3 = m2+m4
			// C4 = m1+m3-m2+m6
			
			int[][] C1 = Matrix_Sum(n / 2, Matrix_Sub(n / 2, Matrix_Sum(n / 2, m1, m4), m5), m7);
			int[][] C2 = Matrix_Sum(n / 2, m3, m5);
			int[][] C3 = Matrix_Sum(n / 2, m2, m4);
			int[][] C4 = Matrix_Sum(n / 2, Matrix_Sub(n / 2, Matrix_Sum(n / 2, m1, m3), m2), m6);

			// �κ���� C1,C2,C3,C4�� ���� ��� C�� ����
			for (int i = 0; i < n / 2; i++) {
				for (int j = 0; j < n / 2; j++) {
					C[i][j] = C1[i][j];
					C[i][j + n / 2] = C2[i][j];
					C[i + n / 2][j] = C3[i][j];
					C[i + n / 2][j + n / 2] = C4[i][j];

				}

			}

		}

		return C; // ������ �κ���ķ� ���� ��� C�� ��ȯ�Ѵ�(��Ʈ�� ������ ����� ��)
	}

}
