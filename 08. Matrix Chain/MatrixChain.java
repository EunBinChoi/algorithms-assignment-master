package algorithm_08;

import java.io.File; // 기존의 파일이나 폴더에 대한 제어를 하는 데 사용하는 클래스 
import java.io.FileWriter; // 출력할 유니코드 문자를 디폴트 문자 인코딩의 바이트로 변환하여 파일에 저장하고자 할 경우에 사용하는 클래스
import java.io.IOException; // 입출력에서 예외처리를 담당해주는 클래스
import java.util.Scanner; // 사용자에게 입력을 받기 위한 클래스

public class MatrixChain { // 최소행렬 곱셈을 구현하는 클래스
	
	public static void main(String[] args) throws IOException {
		// 메인 함수 정의 
		// 해당 메소드에서 IOException(입출력 예외처리)이 발생하면 이에 대한 처리를 함
		
		int[] arr = new int[501];
		// 각 행렬들의 행과 열의 개수를 저장해주는 배열
		
		System.out.println("----------------------------- MatrixChain Multiplication Start! -----------------------------");
	    System.out.println(); // 띄어쓰기
	    
	    Scanner input = new Scanner(System.in);
	    // 사용자에게 콘솔 입력을 받기 위한 Scanner 객체 정의
	    
	    String fname; // 사용자에게 행렬들의 행과 열의 개수가 저장되어있는 파일의 이름을 입력받기 위한 String 변수 정의
	    System.out.print("Input File Name Saved Row and Col of Matrices : ");
	    // 사용자가 행렬들의 행과 열의 개수가 저장되어있는 파일의 이름을 입력하라는 메세지 출력
	    fname = input.next(); // 사용자가 데이터를 읽어들일 파일이름을 입력함
	   
	    Scanner in = new Scanner(new File(fname));
	    // fname이라는 이름의 파일로부터 입력받기 위한 Scanner 객체 정의
	    int i = 0; // 인덱스를 접근하기 위한 변수 정의 
	    
	    System.out.println(); // 띄어쓰기
	    System.out.println("Loading Data In File........");
	   
	    while(in.hasNextInt()) // 다음 데이터가 정수형이면 true를 반환함
	    {
	    	arr[i] = in.nextInt(); // 다음 데이터를 arr 배열에 저장
	    	i++; // 인덱스 증가
	    }
	    
	    System.out.println("Success Loading Data !!");
	    System.out.println(); // 띄어쓰기
	    System.out.println(); // 띄어쓰기
	  
	    int[][] P = new int[501][501]; 
	    // 최소치를 주는 k의 값을 저장하는 P 배열
	    
	    System.out.println("The Number of Minimum Multiplication Matrix : " + minmult(500,arr,P));
	    // minmult 함수를 호출하여 최소 곱셈 행렬의 횟수를 출력
	    System.out.println(); // 띄어쓰기
	    System.out.println("Print Order Of Minimum Multiplication Matrix");
	   
	    order(1,500,P);  
	    // 행렬 1부터 500까지 행렬의 곱하는 최적의 순서를 출력하는 함수를 호출함
	   
	    System.out.println(); // 띄어쓰기
	    System.out.println(); // 띄어쓰기
	    System.out.println(); // 띄어쓰기
	    
		String fileName; // 행렬의 순서를 저장할 파일 이름을 입력 받기 위한 String 변수 정의
		System.out.print("Input File Name Saving Matrix Order : ");
		// 행렬의 순서를 저장할 파일의 이름을 입력하라는 메세지 출력
		
		fileName = input.next(); // 사용자가 행렬의 순서를 읽어들일 파일이름을 입력함
		
		
		FileWriter out = new FileWriter(fileName); 
		// fileName이라는 이름을 가진 파일에 데이터 값을 입력할 FileWriter 클래스의 객체 정의
	    
	    PrintOrderInFile(1,500,P,out);
	    
	    System.out.println(); // 띄어쓰기
	    System.out.println(); // 띄어쓰기
	    System.out.println("....................Success Writing Data In The File");
	    
	    // 모든 클래스를 close 시킴
	    input.close();
	    in.close();
	    out.close();
	}

	public static int minmult(int n, int d[], int P[][]) { 
		// i < j일 때, Ai부터 Aj까지의 행렬을 곱하는데 필요한 기본적인 곱셈의 횟수를 M 행렬에 저장하고, 곱셈의 횟수의 최소치를 반환해주는 함수 
		int i, j, k, diagonal; // M 행렬의 인덱스를 접근하기 위한 정수형 변수 선언
		int[][] M = new int[n+1][n+1]; // M 행렬을 n+1 x n+1 의 크기의 이차원 배열로 선언(0 인덱스는 사용하지 않음)
		
		for(i = 1; i <= n ; i++){ // 행렬 초기화 및 대각선 초기화
			for(j = i; j <= n ; j++)
			{
				if(i==j) M[i][j] = 0; // 만약 i와 j의 값이 같다면(대각선 이라면) 0으로 초기화 해주고
				else M[i][j] = Integer.MAX_VALUE; // i와 j의 값이 다르다면 int의 최대값으로 정의해준다
				
			}
		}
		
		// diagonal : diagonal+1은 최소값을 구할 행렬의 개수를 저장한다, m 행렬의 행과 열의 차이 저장
		for (diagonal = 1; diagonal <= n - 1; diagonal++) { // m 행렬의 행과 열의 차이를 저장해주는 diagonal을 1부터 n-1까지 증가시킨다
			for (i = 1; i <= n - diagonal; i++) { // i를 1부터 n-diagonal까지 반복문을 돌면서 증가시킨다(m 행렬의 행과 열의 차이가 현재 diagonal인 것을 모두 구해준다)
				j = i + diagonal; // m 행렬의 행과 열의 차이를 저장하는 diagonal 만큼 더해준다
				for (k = i; k <= j - 1; k++) { // k를 i부터 j-1까지 반복문을 돌면서 증가시킨다
					if (M[i][j] > M[i][k] + M[k + 1][j] + d[i - 1] * d[k] * d[j]) { // 만약 M[i][j](현재 값은 int의 최대값)보다 최소의 경로가 있다면
						M[i][j] = M[i][k] + M[k + 1][j] + d[i - 1] * d[k] * d[j]; //  M[i][j]에 최소의 경로를 저장한다
						P[i][j] = k; // P 배열에 최소값을 주는 k를 저장한다
					}
				}
			}
		}
		
		
		return M[1][n]; // 최소 곱셈 행렬의 횟수를 반환
	}

	public static void order(int i, int j, int P[][]) { // 행렬의 곱하는 최적의 순서를 출력하는 함수
		if (i == j) // 만약 i와 j가 같으면
			System.out.print("A" + i); // A와 i를 출력
		else { // 만약 i와 j가 다르면
			int k = P[i][j]; // k에 P[i][j]의 값을 저장하고
			System.out.print("("); // 왼쪽 괄호 출력
			order(i, k, P); // i~k까지 order 함수를 재귀 함수로 호출
			order(k + 1, j, P); // k+1~j까지 order 함수를 재귀 함수로 호출
			System.out.print(")"); // 오른쪽 괄호 출력
		}
	}
	
	public static void PrintOrderInFile(int i, int j, int P[][],FileWriter out) throws IOException
	// 행렬의 곱하는 최적의 순서를 파일에 출력하는 함수, 위의 함수와 다른 점은 FileWriter 클래스의 객체를 인수로 보냄
	{
	
		if (i == j) // 만약 i와 j가 같으면
			out.write("A" + (int)i); // out 객체의 write 함수를 써서 A와 i를 출력
		else { // 만약 i와 j가 다르면
			int k = P[i][j]; // k에 P[i][j]의 값을 저장하고
			out.write("("); // out 객체의 write 함수를 써서 왼쪽 괄호 출력
			PrintOrderInFile(i, k, P,out); // i~k까지 PrintOrderInFile 함수를 재귀 함수로 호출
			PrintOrderInFile(k + 1, j, P,out);// k+1~j까지 PrintOrderInFile 함수를 재귀 함수로 호출
			out.write(")"); // 오른쪽 괄호 출력
		}
	}
}
