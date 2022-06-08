package algorithm_08;

import java.io.File; // ������ �����̳� ������ ���� ��� �ϴ� �� ����ϴ� Ŭ���� 
import java.io.FileWriter; // ����� �����ڵ� ���ڸ� ����Ʈ ���� ���ڵ��� ����Ʈ�� ��ȯ�Ͽ� ���Ͽ� �����ϰ��� �� ��쿡 ����ϴ� Ŭ����
import java.io.IOException; // ����¿��� ����ó���� ������ִ� Ŭ����
import java.util.Scanner; // ����ڿ��� �Է��� �ޱ� ���� Ŭ����

public class MatrixChain { // �ּ���� ������ �����ϴ� Ŭ����
	
	public static void main(String[] args) throws IOException {
		// ���� �Լ� ���� 
		// �ش� �޼ҵ忡�� IOException(����� ����ó��)�� �߻��ϸ� �̿� ���� ó���� ��
		
		int[] arr = new int[501];
		// �� ��ĵ��� ��� ���� ������ �������ִ� �迭
		
		System.out.println("----------------------------- MatrixChain Multiplication Start! -----------------------------");
	    System.out.println(); // ����
	    
	    Scanner input = new Scanner(System.in);
	    // ����ڿ��� �ܼ� �Է��� �ޱ� ���� Scanner ��ü ����
	    
	    String fname; // ����ڿ��� ��ĵ��� ��� ���� ������ ����Ǿ��ִ� ������ �̸��� �Է¹ޱ� ���� String ���� ����
	    System.out.print("Input File Name Saved Row and Col of Matrices : ");
	    // ����ڰ� ��ĵ��� ��� ���� ������ ����Ǿ��ִ� ������ �̸��� �Է��϶�� �޼��� ���
	    fname = input.next(); // ����ڰ� �����͸� �о���� �����̸��� �Է���
	   
	    Scanner in = new Scanner(new File(fname));
	    // fname�̶�� �̸��� ���Ϸκ��� �Է¹ޱ� ���� Scanner ��ü ����
	    int i = 0; // �ε����� �����ϱ� ���� ���� ���� 
	    
	    System.out.println(); // ����
	    System.out.println("Loading Data In File........");
	   
	    while(in.hasNextInt()) // ���� �����Ͱ� �������̸� true�� ��ȯ��
	    {
	    	arr[i] = in.nextInt(); // ���� �����͸� arr �迭�� ����
	    	i++; // �ε��� ����
	    }
	    
	    System.out.println("Success Loading Data !!");
	    System.out.println(); // ����
	    System.out.println(); // ����
	  
	    int[][] P = new int[501][501]; 
	    // �ּ�ġ�� �ִ� k�� ���� �����ϴ� P �迭
	    
	    System.out.println("The Number of Minimum Multiplication Matrix : " + minmult(500,arr,P));
	    // minmult �Լ��� ȣ���Ͽ� �ּ� ���� ����� Ƚ���� ���
	    System.out.println(); // ����
	    System.out.println("Print Order Of Minimum Multiplication Matrix");
	   
	    order(1,500,P);  
	    // ��� 1���� 500���� ����� ���ϴ� ������ ������ ����ϴ� �Լ��� ȣ����
	   
	    System.out.println(); // ����
	    System.out.println(); // ����
	    System.out.println(); // ����
	    
		String fileName; // ����� ������ ������ ���� �̸��� �Է� �ޱ� ���� String ���� ����
		System.out.print("Input File Name Saving Matrix Order : ");
		// ����� ������ ������ ������ �̸��� �Է��϶�� �޼��� ���
		
		fileName = input.next(); // ����ڰ� ����� ������ �о���� �����̸��� �Է���
		
		
		FileWriter out = new FileWriter(fileName); 
		// fileName�̶�� �̸��� ���� ���Ͽ� ������ ���� �Է��� FileWriter Ŭ������ ��ü ����
	    
	    PrintOrderInFile(1,500,P,out);
	    
	    System.out.println(); // ����
	    System.out.println(); // ����
	    System.out.println("....................Success Writing Data In The File");
	    
	    // ��� Ŭ������ close ��Ŵ
	    input.close();
	    in.close();
	    out.close();
	}

	public static int minmult(int n, int d[], int P[][]) { 
		// i < j�� ��, Ai���� Aj������ ����� ���ϴµ� �ʿ��� �⺻���� ������ Ƚ���� M ��Ŀ� �����ϰ�, ������ Ƚ���� �ּ�ġ�� ��ȯ���ִ� �Լ� 
		int i, j, k, diagonal; // M ����� �ε����� �����ϱ� ���� ������ ���� ����
		int[][] M = new int[n+1][n+1]; // M ����� n+1 x n+1 �� ũ���� ������ �迭�� ����(0 �ε����� ������� ����)
		
		for(i = 1; i <= n ; i++){ // ��� �ʱ�ȭ �� �밢�� �ʱ�ȭ
			for(j = i; j <= n ; j++)
			{
				if(i==j) M[i][j] = 0; // ���� i�� j�� ���� ���ٸ�(�밢�� �̶��) 0���� �ʱ�ȭ ���ְ�
				else M[i][j] = Integer.MAX_VALUE; // i�� j�� ���� �ٸ��ٸ� int�� �ִ밪���� �������ش�
				
			}
		}
		
		// diagonal : diagonal+1�� �ּҰ��� ���� ����� ������ �����Ѵ�, m ����� ��� ���� ���� ����
		for (diagonal = 1; diagonal <= n - 1; diagonal++) { // m ����� ��� ���� ���̸� �������ִ� diagonal�� 1���� n-1���� ������Ų��
			for (i = 1; i <= n - diagonal; i++) { // i�� 1���� n-diagonal���� �ݺ����� ���鼭 ������Ų��(m ����� ��� ���� ���̰� ���� diagonal�� ���� ��� �����ش�)
				j = i + diagonal; // m ����� ��� ���� ���̸� �����ϴ� diagonal ��ŭ �����ش�
				for (k = i; k <= j - 1; k++) { // k�� i���� j-1���� �ݺ����� ���鼭 ������Ų��
					if (M[i][j] > M[i][k] + M[k + 1][j] + d[i - 1] * d[k] * d[j]) { // ���� M[i][j](���� ���� int�� �ִ밪)���� �ּ��� ��ΰ� �ִٸ�
						M[i][j] = M[i][k] + M[k + 1][j] + d[i - 1] * d[k] * d[j]; //  M[i][j]�� �ּ��� ��θ� �����Ѵ�
						P[i][j] = k; // P �迭�� �ּҰ��� �ִ� k�� �����Ѵ�
					}
				}
			}
		}
		
		
		return M[1][n]; // �ּ� ���� ����� Ƚ���� ��ȯ
	}

	public static void order(int i, int j, int P[][]) { // ����� ���ϴ� ������ ������ ����ϴ� �Լ�
		if (i == j) // ���� i�� j�� ������
			System.out.print("A" + i); // A�� i�� ���
		else { // ���� i�� j�� �ٸ���
			int k = P[i][j]; // k�� P[i][j]�� ���� �����ϰ�
			System.out.print("("); // ���� ��ȣ ���
			order(i, k, P); // i~k���� order �Լ��� ��� �Լ��� ȣ��
			order(k + 1, j, P); // k+1~j���� order �Լ��� ��� �Լ��� ȣ��
			System.out.print(")"); // ������ ��ȣ ���
		}
	}
	
	public static void PrintOrderInFile(int i, int j, int P[][],FileWriter out) throws IOException
	// ����� ���ϴ� ������ ������ ���Ͽ� ����ϴ� �Լ�, ���� �Լ��� �ٸ� ���� FileWriter Ŭ������ ��ü�� �μ��� ����
	{
	
		if (i == j) // ���� i�� j�� ������
			out.write("A" + (int)i); // out ��ü�� write �Լ��� �Ἥ A�� i�� ���
		else { // ���� i�� j�� �ٸ���
			int k = P[i][j]; // k�� P[i][j]�� ���� �����ϰ�
			out.write("("); // out ��ü�� write �Լ��� �Ἥ ���� ��ȣ ���
			PrintOrderInFile(i, k, P,out); // i~k���� PrintOrderInFile �Լ��� ��� �Լ��� ȣ��
			PrintOrderInFile(k + 1, j, P,out);// k+1~j���� PrintOrderInFile �Լ��� ��� �Լ��� ȣ��
			out.write(")"); // ������ ��ȣ ���
		}
	}
}
