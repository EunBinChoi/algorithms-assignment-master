import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class Matrix_mutiplex { // 행렬의 곱을 계산하는 클래스 작성
	public static void main(String[] args) throws IOException { // 메인 함수 작성
		// 파일을 읽고 출력하는 부분이 있기 때문에 IOException 예외처리를 해줘야한다

		System.out.println(
				"----------------------------- Multiplication of Matrix Start!! -----------------------------");
		System.out.println();

		int[][] Naive_Result = new int[1024][1024]; // 무식한 행렬의 곱을 저장할 배열 생성
		int[][] Strassen_Result = new int[1024][1024]; // 쉬트라센의 행렬의 곱을 저장할 배열 생성
		int[][] arr = new int[1024][1024]; // a.txt 파일의 값들을 저장할 배열 생성
		int[][] arr2 = new int[1024][1024]; // b.txt 파일의 값들을 저장할 배열 생성

		Scanner input = new Scanner(System.in); // 사용자에게 입력을 받기위해 Scanner 클래스의 객체 변수 생성

		System.out.print("Input Name of File Saving 'A' Maxtrix(eg. a.txt) : ");
		String fname1 = input.next(); // 사용자에게 A 행렬이 저장되어있는 파일의 이름을 입력하도록 함
		FileInputStream fin = new FileInputStream(fname1); // 파일의 입력을 담당하는 FileInputStream 클래스의 객체 변수 생성

		int i, j; // 반복문을 돌기 위한 변수 선언
		for (i = 0; i < 1024; i++) { // 이중 반복문을 돌면서
			for (j = 0; j < 1024; j++) {
				arr[i][j] = fin.read(); // arr 배열에 파일에서 입력받은 값들을 저장한다
			}
		}
		System.out.println(">> Success reading A Matrix!");

		System.out.print("Input Name of File Saving 'B' Maxtrix(eg. b.txt) : ");
		String fname2 = input.next(); // 사용자에게 B 행렬이 저장되어있는 파일의 이름을 입력하도록 함
		FileInputStream fin2 = new FileInputStream(fname2);

		for (i = 0; i < 1024; i++) { // 이중 반복문을 돌면서
			for (j = 0; j < 1024; j++) {
				arr2[i][j] = fin2.read(); // arr2 배열에 파일에서 입력받은 값들을 저장한다
				
			}
		}
		System.out.println(">> Success reading B Matrix!");
		System.out.println();
		System.out.println();

		System.out.println(
				"----------------------------- Measure Navie Algorithm Run Time -----------------------------");
		
		// 무식한 행렬의 곱의 수행시간을 계산함
		long StartTime = System.nanoTime(); // 무식한 행렬의 곱의 함수가 불리기 전에 시간을 계산하여 StartTime 변수에 대입  
		Naive_Result = Naive_Matrix_Multiplication(1024, arr, arr2); // Naive_Matrix_Multiplication 함수를 호출하여 결과 값을 Naive_Result 배열에 대입한다
		long endTime = System.nanoTime(); // 무식한 행렬의 곱의 함수가 불린 후에 시간을 계산하여 endTime 변수에 대입
		long ITime = endTime - StartTime; // 두 간격의 시간을 계산하여 ITime 변수에 대입한다
		System.out.println(">> Naive Algorithm : " + ITime / 1000000.0 + " ms"); // 수행 시간을 출력
 		System.out.println();

		System.out
				.println("--------------------------- Measure Strassen Algorithm Run Time ---------------------------");

		// 쉬트라센의 행렬의 곱 수행시간을 계산함
		StartTime = System.nanoTime(); // 쉬트라센의 행렬의 곱 함수가 불리기 전에 시간을 계산하여 StartTime 변수에 대입  
		Strassen_Result = Strassen_Algorithm(1024, arr, arr2); // Strassen_Algorithm 함수를 호출하여 결과 값을 Strassen_Result 배열에 대입한다
		endTime = System.nanoTime(); // 쉬트라센의 행렬의 곱의 함수가 불린 후에 시간을 계산하여 endTime 변수에 대입
		ITime = endTime - StartTime; // 두 간격의 시간을 계산하여 ITime 변수에 대입한다
		System.out.println(">> Strassen Algorithm : " + ITime / 1000000.0 + " ms"); // 수행 시간을 출력
		System.out.println();
		System.out.println();
	
		System.out.println("----------------------------------- Save Result File !! -----------------------------------");

		System.out.print("Input Name to save Result of Naive Algorithm(eg. result.txt) : ");
		// 무식한 행렬의 곱의 결과를 저장하기 위한 파일 이름을 입력받는다
		String Naive_fname = input.next(); 
		printData(Naive_fname, Naive_Result); // 입력받은 이름의 파일을 생성한 뒤 무식한 행렬의 곱의 결과를 저장해준다

		System.out.print("Input Name to save Result of Strassen Algorithm(eg. result.txt) : ");
		// 쉬트라센의 행렬의 곱의 결과를 저장하기 위한 파일 이름을 입력받는다
		String Strassen_fname = input.next();
		printData(Strassen_fname, Strassen_Result); // 입력받은 이름의 파일을 생성한 뒤 싀투라센의 행렬의 곱의 결과를 저장해준다
		System.out.println();
		System.out.println(
				"----------------------------- End Multiplication of Matrix !! -----------------------------");

		input.close();
		fin.close();
		fin2.close();
	}

	

	public static void printData(String fileName, int[][] matrix) throws IOException { // 행렬의 곱의 결과를 파일에 작성하는 함수
		FileOutputStream out = new FileOutputStream(fileName); // 파일의 출력을 담당하는 FileOuputStream 클래스의 객체 변수 생성 

		int iLength = 0;
		String tChar;
		for (int i = 0; i < 1024; i++) { // 이중 반복문을 돌면서
			for (int j = 0; j < 1024; j++) {
				tChar = Integer.toString(matrix[i][j]); // 행렬의 i,j 인덱스에 저장된 숫자를 스트링으로 변환해 tChar 변수에 대입
				iLength = tChar.length(); // iLength 변수에는 tChar 변수에 저장된 값의 길이를 반환
				for (int k = 0; k < iLength; k++) { // 반복문을 돌면서
					out.write((int) tChar.charAt(k)); // tChar 변수(스트링으로 변환한 값이 저장되어있음)의 k 인덱스에 있는 문자를 작성해줌
				}
				out.write((int) ' ');
			}
			out.write((int) '\n');
		}
		out.close();
	}

	public static int getThreshold(int n) { // 임계값을 계산해주는 함수
		int th;
		double k = Math.floor(Math.log(n) / Math.log(2) - 4); // (log2(n)-4)의 floor값을 계산하여 k에 대입
		th = (int) Math.floor(n / Math.pow(2.0, k)) + 1; // n/2^k의 floor값에 +1을 계산하여 th에 대입
		return th; // th의 값을 반환

	}

	public static int[][] Naive_Matrix_Multiplication(int n, int[][] arr, int[][] arr2) { // 무식한 행렬의 곱을 계산하는 함수

		int i, j,k = 0; // 반복을 위한 변수
		int[][] result = new int[n][n]; // NxN 의 크기를 갖는 이차원 배열 생성
		for (i = 0; i < n; i++) {  // 이중 반복문을 돌면서
			for (j = 0; j < n; j++) { 
				result[i][j] = 0; // result[i][j] 을 0으로 초기화해주고
				for (k = 0; k < n; k++) { // 원래의 행렬의 곱으로 계산하여 result 배열에 저장
					result[i][j] = result[i][j] + arr[i][k] * arr2[k][j];

				}
			}

		}

		return result; // result 배열을 반환

	}
 
	public static int[][] Matrix_Sub(int n, int[][] arr, int[][] arr2) { // 행렬의 뺄셈을 계산하는 함수

		int i,j; // 반복문을 위한 변수 선언
		int[][] result = new int[n][n]; // NxN 의 크기를 갖는 이차원 배열 생성
		
		for (i = 0; i < n; i++) { // 이중 반복문을 돌면서
			for (j = 0; j < n; j++) { 
				result[i][j] = arr[i][j] - arr2[i][j]; // 두 행렬의 각 원소끼리 빼줌

			}
		}

		return result;

	}

	public static int[][] Matrix_Sum(int n, int[][] arr, int[][] arr2) { // 행렬의 덧셈을 계산하는 함수

		int i, j; // 반복문을 위한 변수 선언
		int[][] result = new int[n][n]; // NxN 의 크기를 갖는 이차원 배열 생성

		for (i = 0; i < n; i++) { // 이중 반복문을 돌면서
			for (j = 0; j < n; j++) {

				result[i][j] = arr[i][j] + arr2[i][j]; // 두 행렬의 각 원소끼리 더해줌

			}

		}
		return result;

	}

	public static int[][] Strassen_Algorithm(int n, int A[][], int B[][]) { // 쉬트라센의 행렬의 곱을 계산해주는 함수
		int[][] C = new int[n][n]; // 쉬트라센의 행렬의 곱의 결과를 저장해줄 이차원 배열 생성

		if (n <= getThreshold(n)) { // 만약 n의 값이 임계값보다 작거나 같으면
			// 여기서 임계값은 N x N 행렬곱을 계산할 때, Strassen 보다 Naive 행렬곱이 빨라지는 교차지점의 값 N이다

			C = Naive_Matrix_Multiplication(n, A, B); // 일반 행렬의 곱셈을 담당하는 함수(무식한 행렬의 곱을 담당하는 함수)를 호출시켜 C에 대입
		}

		else { // 그렇지 않다면

			// A의 부분행렬 생성
			int[][] A11 = new int[n / 2][n / 2];
			int[][] A12 = new int[n / 2][n / 2];
			int[][] A21 = new int[n / 2][n / 2];
			int[][] A22 = new int[n / 2][n / 2];
			
			// B의 부분행렬 생성
			int[][] B11 = new int[n / 2][n / 2];
			int[][] B12 = new int[n / 2][n / 2];
			int[][] B21 = new int[n / 2][n / 2];
			int[][] B22 = new int[n / 2][n / 2];

			for (int i = 0; i < n / 2; i++) { // 반복문을 돌면서
				for (int j = 0; j < n / 2; j++) {
					// 생성한 부분행렬에 해당하는 배열의 값들을 대입해준다
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
			// 쉬트라센의 정의에 따라 m1~m7을 생성
			int[][] m1 = new int[n / 2][n / 2];
			int[][] m2 = new int[n / 2][n / 2];
			int[][] m3 = new int[n / 2][n / 2];
			int[][] m4 = new int[n / 2][n / 2];
			int[][] m5 = new int[n / 2][n / 2];
			int[][] m6 = new int[n / 2][n / 2];
			int[][] m7 = new int[n / 2][n / 2];

			// 쉬트라센의 정의
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

			// 쉬트라센의 결과인 행렬 C를 구하기 위해 C의 부분원소들을 다음과 같이 정의
			// 쉬트라센의 정의
			// C1 = m1+m4-m5+m7
			// C2 = m3+m5
			// C3 = m2+m4
			// C4 = m1+m3-m2+m6
			
			int[][] C1 = Matrix_Sum(n / 2, Matrix_Sub(n / 2, Matrix_Sum(n / 2, m1, m4), m5), m7);
			int[][] C2 = Matrix_Sum(n / 2, m3, m5);
			int[][] C3 = Matrix_Sum(n / 2, m2, m4);
			int[][] C4 = Matrix_Sum(n / 2, Matrix_Sub(n / 2, Matrix_Sum(n / 2, m1, m3), m2), m6);

			// 부분행렬 C1,C2,C3,C4를 합쳐 행렬 C를 만듦
			for (int i = 0; i < n / 2; i++) {
				for (int j = 0; j < n / 2; j++) {
					C[i][j] = C1[i][j];
					C[i][j + n / 2] = C2[i][j];
					C[i + n / 2][j] = C3[i][j];
					C[i + n / 2][j + n / 2] = C4[i][j];

				}

			}

		}

		return C; // 위에서 부분행렬로 만든 행렬 C를 반환한다(쉬트라센 곱셈의 결과가 됨)
	}

}
