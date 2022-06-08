
package algorithm_03; // package를 정의해준다
import java.util.*; // java.util 패키지를 import 해준다

public class algorithm_search { // 이진탐색과 피보나치 탐색을 정의해주는 클래스
	
	static int[] arr; // 편의성을 위해 배열을 클래스의 멤버변수로 정의한다
	static void initArray(){ // 배열을 초기화하는 함수(이 함수에서는 함수를 랜덤으로 초기화하고, 중복된 함수 값을 제거하는 역할을 한다)
		
		arr = new int[55]; // 55 크기를 갖는 배열을 할당한다
		for(int i = 0 ; i < 55 ; i++) // 반복문을 0부터 54까지 돌면서
		{
			arr[i] = (int)(Math.random()*100)+1; // 배열 값을 랜덤으로 지정해준다
			for(int j = 0 ; j < i ; j++){
				// 배열에서 i번째의 값을 랜덤값으로 지정받은 뒤, 변수 j를 하나 선언해서 i가 j보다 작은 배열 값 중에서 겹치는 값이 있으면
				// i를 하나 줄여, 새로운 랜덤 값을 지정할 수 있도록 한다. 이 방법은 배열에서 중복을 제거하기 위한 방법이다.
				// 이렇게 해주는 이유는 우리가 탐색함수를 사용하면서 우리가 찾고자하는 key 값의 인덱스를 탐색하기 위함인데, 만약 해당 값을 갖는 인덱스가 여러개 있으면 예외처리를 해줘야하기 때문에, 처음부터 중복을 제거한다
				if(arr[i] == arr[j])
					i--;
			}
		}
		
		Arrays.sort(arr); // sort함수는 배열을 오름차순으로 정렬하는 함수이다. 피보나치 탐색과 이진탐색은 둘다 정렬된 배열에서만 가능하다
		
	}
	
	public static int binary_search(int target, int low, int high){ // 이진 탐색을 수행하는 함수
		
		int middle; // low high의 중간값을 저장해주는 변수선언
		if(low > high){return -1;} // 만약 low가 high보다 크다면, 탐색하려는 값을 찾지 못했다는 의미거나, 함수의 파라미터로 잘못된 값이 들어왔다는 의미이므로 -1(오류)를 반환해준다
		else{
			
			middle = (low + high) /2; // middle 값은 low와 high을 더한 값을 반으로 나눈 값이다
			if(target == arr[middle]) return middle;
			// 만약 내가 찾으려고하는 key값의 인덱스가 middle과 같다면(key값과 배열의 middle 인덱스 값이 같다면), key값을 찾았다는 의미이므로, middle 값을 반환 
			else if(target < arr[middle]) return binary_search(target,low,middle-1);
			// 만약 내가 찾으려고 하는 key값이 인덱스 middle에 해당하는 값보다 작다면, 배열의 중간값보다 key 값이 더 작다는 의미이므로,
			// low는 그대로 low로 , high는 middle-1의 값으로 binary_search 함수를 호출한다
			else return binary_search(target, middle+1,high);
			// 만약 내가 찾으려고 하는 key값이 인덱스 middle에 해당하는 값보다 크다면, 배열의 중간값보다 key 값이 더 크다는 의미이므로,
			// low는 middle+1로 , high는 high의 값으로 binary_search 함수를 호출한다
		}
		
		
	}
	
	public static int fibonacci_search(int target) { // 피보나치 탐색을 수행하는 경우
	      int k = 1, p = 1, q = 0; // 피보나치 값, 0항의 값 = 0, 1항의 값 = 1, 2항의 값 = 1이다
	      while (k + p < arr.length) { // k + p가 배열의 크기보다 작을 때, 아래의 문장을 실행한다 
	         q = p; // q가 p가 되고, 
	         p = k; // p가 k가 되고,
	         k = k + q; // k는 k+q가 된다
	         // 배열의 크기와 가장 가까운 피보나치 수를 구하는 과정
	      } // 탐색과정
	      
	      while (true) {
	         if (target == arr[k]) 
	        	 // 만약 내가 찾으려고 하는 key값과 arr의 k 인덱스 있는 값이 같다면
	            return k; // 내가 찾으려고 하는 key 값의 인덱스가 k라는 것이므로, k를 반환한다
	         else if (target < arr[k]) {// 만약 내가 찾으려고 하는 key값이 arr의 k 인덱스 있는 값보다 작다면
	            if (q == 0) { // q가 0 일때는 -1(오류)을 반환한다
	               return -1;
	            } else { 
	               int tmp = q; // tmp에 현재 q 값을 저장
	               k = k - q; // k는 q만큼 뺀 값을 k에 저장
	               q = p - q; // q는 p에서 q만큼 뺀 값을 대입
	               p = tmp; // p는 현재 q 값을 저장했던 tmp를 대입한다           
	            }
	         } else { // 만약 내가 찾으려고 하는 key값이 arr의 k 인덱스 있는 값보다 크다면
	            if (p == 1) { // p가 1일때는 -1(오류)를 반환한다
	               return -1;
	            } else {
	            	int tmp = q; // tmp에 현재 q 값을 저장
	            	k = k +q; 
	            	// 인덱스 k를 q 만큼 오른쪽으로 이동 시킴
	            	//(남은 탐색 영역(K(i-1))을 K(i-2) 와 K(i-3) 영역으로 나누기 위해)  
	            	q = 2*q - p; 
	            	/*//남은 탐색 영역(K(i-1))을 K(i-2) 와 K(i-3) 영역으로 나누었기 때문에, 
	            	 * 다음 영역 분할을 위해서, q는 K(i-4)가 되야 하고, 
	            	 * 따라서 현재 q(K(i-2))에서 K(i-3)을 빼야함.  
	            	 * 여기서 K(i-3)은 (p - q)이므로 최종적으로 다음과 같은 수식이 됨.
	            	 * => q = q - (p - q)  = 2*q-p*/
	            	p = p - tmp; 
	            	/*//남은 탐색 영역(K(i-1))을 K(i-2) 와 K(i-3) 영역으로 나누었기 때문에, p는 K(i-3)이 되야함 
	            	 * 따라서, 현재 p(K(i-1))에서 임시 저장했던 q(K(i-2))을 빼야함.*/
	            }
	         }
	      }
	   }
	    public static void main(String arg[]){ // 메인 함수
		
	    	int result1; // 이진 탐색의 결과를 저장하기 위한 변수
	    	int result2; // 피보나치 탐색의 결과를 저장하기 위한 변수
	    	
	    	initArray(); // 배열을 초기화하는 함수 호출
	    	System.out.println("* 랜덤 값으로 지정된 배열 출력을 시작합니다 *");
	    	for(int i = 0 ; i < arr.length ; i++){
	    		if(i%10 ==0) System.out.println();
	    		// 만약 i가 10의 배수가 되면 띄어쓰기를 실행
	    		System.out.printf("%3d ", arr[i]); // 랜덤으로 저장한 배열을 출력한다
	    		
	    	}
	    	System.out.println();
	    	System.out.println();
	    	
	    	
	    	System.out.println("------------Binary & Fibonacci Search Start!-----------");
	    	System.out.println();
	    	System.out.println("  i값     이진탐색  피보나치 ");
	    	System.out.printf("--------------------");
	    	System.out.println();
	    	for(int i = 1 ; i <= 100; i++){ // 반복문을  1부터 100까지 돌면서
	    		result1 = binary_search(i,0,arr.length-1); // 이진 탐색을 실행하는 함수의 반환값을 result1에 저장
	    		result2 = fibonacci_search(i); // 피보나치 탐색을 실행하는 함수의 반환값을 result2에 저장
	    		
	    		if(result2 ==-1){ // 만약 result2가 -1이면서
	    			if(result1 == -1) System.out.printf("%3d |  탐색실패   탐색실패\n",i);
	    			// result1도 -1이라면  이진탐색, 피보나치 탐색 모두 탐색실패
	    			else System.out.printf("%3d | %5d   탐색실패\n",i,result1);
	    			// result2이 -1이면서 result1이 -1이 아니라면, result2는 탐색에 실패했지만, result1은 반환값이 존재
	    			
	    		}
		    	else {System.out.printf("%3d | %5d %5d\n",i,result1,result2);}
	    		// result1과 result2의 값이 모두 존재한다면 이를 출력한다
	    		
	    	}
	    	
		
	    	
	    
	}
	
	
}
