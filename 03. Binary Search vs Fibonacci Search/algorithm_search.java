
package algorithm_03; // package�� �������ش�
import java.util.*; // java.util ��Ű���� import ���ش�

public class algorithm_search { // ����Ž���� �Ǻ���ġ Ž���� �������ִ� Ŭ����
	
	static int[] arr; // ���Ǽ��� ���� �迭�� Ŭ������ ��������� �����Ѵ�
	static void initArray(){ // �迭�� �ʱ�ȭ�ϴ� �Լ�(�� �Լ������� �Լ��� �������� �ʱ�ȭ�ϰ�, �ߺ��� �Լ� ���� �����ϴ� ������ �Ѵ�)
		
		arr = new int[55]; // 55 ũ�⸦ ���� �迭�� �Ҵ��Ѵ�
		for(int i = 0 ; i < 55 ; i++) // �ݺ����� 0���� 54���� ���鼭
		{
			arr[i] = (int)(Math.random()*100)+1; // �迭 ���� �������� �������ش�
			for(int j = 0 ; j < i ; j++){
				// �迭���� i��°�� ���� ���������� �������� ��, ���� j�� �ϳ� �����ؼ� i�� j���� ���� �迭 �� �߿��� ��ġ�� ���� ������
				// i�� �ϳ� �ٿ�, ���ο� ���� ���� ������ �� �ֵ��� �Ѵ�. �� ����� �迭���� �ߺ��� �����ϱ� ���� ����̴�.
				// �̷��� ���ִ� ������ �츮�� Ž���Լ��� ����ϸ鼭 �츮�� ã�����ϴ� key ���� �ε����� Ž���ϱ� �����ε�, ���� �ش� ���� ���� �ε����� ������ ������ ����ó���� ������ϱ� ������, ó������ �ߺ��� �����Ѵ�
				if(arr[i] == arr[j])
					i--;
			}
		}
		
		Arrays.sort(arr); // sort�Լ��� �迭�� ������������ �����ϴ� �Լ��̴�. �Ǻ���ġ Ž���� ����Ž���� �Ѵ� ���ĵ� �迭������ �����ϴ�
		
	}
	
	public static int binary_search(int target, int low, int high){ // ���� Ž���� �����ϴ� �Լ�
		
		int middle; // low high�� �߰����� �������ִ� ��������
		if(low > high){return -1;} // ���� low�� high���� ũ�ٸ�, Ž���Ϸ��� ���� ã�� ���ߴٴ� �ǹ̰ų�, �Լ��� �Ķ���ͷ� �߸��� ���� ���Դٴ� �ǹ��̹Ƿ� -1(����)�� ��ȯ���ش�
		else{
			
			middle = (low + high) /2; // middle ���� low�� high�� ���� ���� ������ ���� ���̴�
			if(target == arr[middle]) return middle;
			// ���� ���� ã�������ϴ� key���� �ε����� middle�� ���ٸ�(key���� �迭�� middle �ε��� ���� ���ٸ�), key���� ã�Ҵٴ� �ǹ��̹Ƿ�, middle ���� ��ȯ 
			else if(target < arr[middle]) return binary_search(target,low,middle-1);
			// ���� ���� ã������ �ϴ� key���� �ε��� middle�� �ش��ϴ� ������ �۴ٸ�, �迭�� �߰������� key ���� �� �۴ٴ� �ǹ��̹Ƿ�,
			// low�� �״�� low�� , high�� middle-1�� ������ binary_search �Լ��� ȣ���Ѵ�
			else return binary_search(target, middle+1,high);
			// ���� ���� ã������ �ϴ� key���� �ε��� middle�� �ش��ϴ� ������ ũ�ٸ�, �迭�� �߰������� key ���� �� ũ�ٴ� �ǹ��̹Ƿ�,
			// low�� middle+1�� , high�� high�� ������ binary_search �Լ��� ȣ���Ѵ�
		}
		
		
	}
	
	public static int fibonacci_search(int target) { // �Ǻ���ġ Ž���� �����ϴ� ���
	      int k = 1, p = 1, q = 0; // �Ǻ���ġ ��, 0���� �� = 0, 1���� �� = 1, 2���� �� = 1�̴�
	      while (k + p < arr.length) { // k + p�� �迭�� ũ�⺸�� ���� ��, �Ʒ��� ������ �����Ѵ� 
	         q = p; // q�� p�� �ǰ�, 
	         p = k; // p�� k�� �ǰ�,
	         k = k + q; // k�� k+q�� �ȴ�
	         // �迭�� ũ��� ���� ����� �Ǻ���ġ ���� ���ϴ� ����
	      } // Ž������
	      
	      while (true) {
	         if (target == arr[k]) 
	        	 // ���� ���� ã������ �ϴ� key���� arr�� k �ε��� �ִ� ���� ���ٸ�
	            return k; // ���� ã������ �ϴ� key ���� �ε����� k��� ���̹Ƿ�, k�� ��ȯ�Ѵ�
	         else if (target < arr[k]) {// ���� ���� ã������ �ϴ� key���� arr�� k �ε��� �ִ� ������ �۴ٸ�
	            if (q == 0) { // q�� 0 �϶��� -1(����)�� ��ȯ�Ѵ�
	               return -1;
	            } else { 
	               int tmp = q; // tmp�� ���� q ���� ����
	               k = k - q; // k�� q��ŭ �� ���� k�� ����
	               q = p - q; // q�� p���� q��ŭ �� ���� ����
	               p = tmp; // p�� ���� q ���� �����ߴ� tmp�� �����Ѵ�           
	            }
	         } else { // ���� ���� ã������ �ϴ� key���� arr�� k �ε��� �ִ� ������ ũ�ٸ�
	            if (p == 1) { // p�� 1�϶��� -1(����)�� ��ȯ�Ѵ�
	               return -1;
	            } else {
	            	int tmp = q; // tmp�� ���� q ���� ����
	            	k = k +q; 
	            	// �ε��� k�� q ��ŭ ���������� �̵� ��Ŵ
	            	//(���� Ž�� ����(K(i-1))�� K(i-2) �� K(i-3) �������� ������ ����)  
	            	q = 2*q - p; 
	            	/*//���� Ž�� ����(K(i-1))�� K(i-2) �� K(i-3) �������� �������� ������, 
	            	 * ���� ���� ������ ���ؼ�, q�� K(i-4)�� �Ǿ� �ϰ�, 
	            	 * ���� ���� q(K(i-2))���� K(i-3)�� ������.  
	            	 * ���⼭ K(i-3)�� (p - q)�̹Ƿ� ���������� ������ ���� ������ ��.
	            	 * => q = q - (p - q)  = 2*q-p*/
	            	p = p - tmp; 
	            	/*//���� Ž�� ����(K(i-1))�� K(i-2) �� K(i-3) �������� �������� ������, p�� K(i-3)�� �Ǿ��� 
	            	 * ����, ���� p(K(i-1))���� �ӽ� �����ߴ� q(K(i-2))�� ������.*/
	            }
	         }
	      }
	   }
	    public static void main(String arg[]){ // ���� �Լ�
		
	    	int result1; // ���� Ž���� ����� �����ϱ� ���� ����
	    	int result2; // �Ǻ���ġ Ž���� ����� �����ϱ� ���� ����
	    	
	    	initArray(); // �迭�� �ʱ�ȭ�ϴ� �Լ� ȣ��
	    	System.out.println("* ���� ������ ������ �迭 ����� �����մϴ� *");
	    	for(int i = 0 ; i < arr.length ; i++){
	    		if(i%10 ==0) System.out.println();
	    		// ���� i�� 10�� ����� �Ǹ� ���⸦ ����
	    		System.out.printf("%3d ", arr[i]); // �������� ������ �迭�� ����Ѵ�
	    		
	    	}
	    	System.out.println();
	    	System.out.println();
	    	
	    	
	    	System.out.println("------------Binary & Fibonacci Search Start!-----------");
	    	System.out.println();
	    	System.out.println("  i��     ����Ž��  �Ǻ���ġ ");
	    	System.out.printf("--------------------");
	    	System.out.println();
	    	for(int i = 1 ; i <= 100; i++){ // �ݺ�����  1���� 100���� ���鼭
	    		result1 = binary_search(i,0,arr.length-1); // ���� Ž���� �����ϴ� �Լ��� ��ȯ���� result1�� ����
	    		result2 = fibonacci_search(i); // �Ǻ���ġ Ž���� �����ϴ� �Լ��� ��ȯ���� result2�� ����
	    		
	    		if(result2 ==-1){ // ���� result2�� -1�̸鼭
	    			if(result1 == -1) System.out.printf("%3d |  Ž������   Ž������\n",i);
	    			// result1�� -1�̶��  ����Ž��, �Ǻ���ġ Ž�� ��� Ž������
	    			else System.out.printf("%3d | %5d   Ž������\n",i,result1);
	    			// result2�� -1�̸鼭 result1�� -1�� �ƴ϶��, result2�� Ž���� ����������, result1�� ��ȯ���� ����
	    			
	    		}
		    	else {System.out.printf("%3d | %5d %5d\n",i,result1,result2);}
	    		// result1�� result2�� ���� ��� �����Ѵٸ� �̸� ����Ѵ�
	    		
	    	}
	    	
		
	    	
	    
	}
	
	
}
