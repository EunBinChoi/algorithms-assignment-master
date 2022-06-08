package algorithm_11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Knapsack { // BackTracking에서 knapsack을 담당하는 클래스 정의

	// 함수에서 지역변수로 사용되면 초기화되어 저장된 값들이 사라지는 문제가 발생하므로 다음과 같이 전역변수로 선언함
	public static int maxprofit; // 최대 이윤을 저장하기 위한 정수형 변수 선언
	public static int BackPackWeight; // 가방의 최대한계무게를 저장해주기 위한 정수형 변수
	public static int[] Weight = new int[500]; // 물건의 무게를 저장해줄 정수형 배열
	public static int[] Price = new int[500]; // 물건의 값어치를 저장해줄 정수형 배열
	public static int numbest = 0; // 현재 노드를 저장하는 정수형 변수
	public static int[] bestset = new int[500]; // 최적의 경로를 저장하는 정수형 배열
	public static int[] include = new int[500]; // 현재 노드가 최적의 경로에 포함되는지 안되는지 여부를
												// 저장해주는 정수형 배열(0 : 포함되지 않음 / 1
												// : 포함)

	public static void main(String[] args) throws FileNotFoundException { // 메인
																			// 함수
																			// 정의
		// 메인 함수 부분에서는 파일을 입력받으므로 FileNotFoundException 예외처리를 실행해준다

		int[] Result = new int[500]; // 물건의 무게당 값어치를 저장해줄 정수형 배열
		int[] Order = new int[500]; // 물건의 인덱스를 저장하기 위한 정수형 배열
		int i = 0; // 배열의 인덱스를 접근하기 위한 정수형 변수

		System.out.println("--------------------------- BackTracking Knapsack Start! ---------------------------");
		String fname; // 파일의 이름을 입력받을 문자열 변수

		Scanner input = new Scanner(System.in); // 사용자에게 입력을 받기 위한 Scanner 클래스
												// 객체 생성
		System.out.print("Input Name Saved Weight : "); // 사용자에게 물건의 무게가 저장되어있는
														// 파일의 이름을 입력하라는 메세지를
														// 출력함
		fname = input.next(); // 사용자에게 물건의 무게가 저장되어있는 파일의 이름을 입력받음

		Scanner in = new Scanner(new File(fname)); // 파일을 읽이 위한 Scanner 클래스 객체
													// 생성
		while (in.hasNextInt()) { // 파일에서 값을 읽었을 때 다음에 오는 값이 정수형이면
			Weight[i] = in.nextInt(); // 그 값을 Weight 배열에 읽어들인다
			i++; // i 값 증가
		}

		System.out.print("Input Name Saved Price : "); // 사용자에게 물건의 값어치가 저장되어있는
														// 파일의 이름을 입력하라는 메세지를
														// 출력함
		fname = input.next(); // 사용자에게 물건의 값어치가 저장되어있는 파일의 이름을 입력받음

		i = 0; // i를 0 으로 초기화
		in = new Scanner(new File(fname)); // 파일을 읽이 위한 Scanner 클래스 객체를 초기화 함
		while (in.hasNextInt()) { // 파일에서 값을 읽었을 때 다음에 오는 값이 정수형이면
			Price[i] = in.nextInt(); // 그 값을 Price 배열에 읽어들인다
			i++; // i 값 증가
		}

		for (int k = 0; k < 500; k++) { // 0 부터 499까지 반복문을 돌면서

			Result[k] = Price[k] / Weight[k]; // Result 배열에 무게당 값어치의 값을 저장함
			Order[k] = k; // 파일에 저장된 물건의 순서를 저장을 그대로 order 배열에 저장함
		}
		
	

		System.out.print("Input Max Weight of BackPack : "); // 가방의 최대한계 무게를
																// 사용자에게 입력하라는
																// 메세지를 출력함
		BackPackWeight = input.nextInt(); // 사용자에게 입력받음

		int temp; // 임시변수
		int j = 0; // 반복문을 실행할 정수형 변수
		i = 0; // i를 0으로 초기화

		// 무게당 값어치가 큰 것부터 정렬
		for (j = 0; j < 499; j++) {
			i = j;
			for (i = j; i < 500; i++) {
				if (Result[i] > Result[j]) {
					temp = Result[i];
					Result[i] = Result[j];
					Result[j] = temp;

					// Price 배열과 Weight 배열 모두 바꾸는 이유는 무게당 값어치가 큰 것부터 Price 배열와
					// Weight 배열도 같이 바꿔서
					// Price, Weight, Result의 같은 인덱스에 같은 물건의 값어치, 무게, 무게당 값어치가
					// 오게 하기 위함이다.
					// 현재 순서가 담겨져있는 Order 배열도 같이 바꿔서 바뀐 순서를 저장해준다.
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
		System.out.println();
		System.out.println("---------------------------------- Loading! ----------------------------------");

		knapsack(-1, 0, 0); // knapsack 함수 호출, i는 -1부터 호출해줌
		System.out.println("MaxProfit : " + maxprofit); // 최대 이익을 출력해주는 출력문
		System.out.println();
		for (int k = 0; k <= numbest; k++) { // 0부터 numbest까지 반복문을 돌면서 
			System.out.print("No." + Order[k] + " Node : "); 
			if (bestset[k] == 1) // 만약 bestset의 값이 1이라면 
				System.out.println("Include"); // k번째 노드를 포함시킨다는 메세지 출력
			else // 만약 bestset의 값이 0이라면 
				System.out.println("Not Include"); // k번째 노드를 포함시키지 않는다는 메세지 출력
		}

	}

	public static void knapsack(int i, int profit, int weight) { // 되추적방법으로 배낭채우기를 정의해주는 함수
		// 지금까지는 이 집합이 최고이다.
		// numbest는 고려한 아이템의 개수, bestset은 해답

		if (weight <= BackPackWeight && profit > maxprofit) { // 만약 현재 weight가 BackPackWeight(가방에 넣을 수 있는 최대 무게)보다 작고, profit이 maxprofit보다 크다면
			maxprofit = profit; // 현재 maxprofit을 profit으로 정의
			numbest = i; // 현재 i를 numbest에 저장
			for (int k = 0; k <= numbest; k++) { // 0부터 numbest까지 돌면서
				bestset[k] = include[k]; // include의 값을 bestset에 저장
			}

		}

		if (promissing(500, i, profit, weight)) { // promissing 함수 호출
			include[i + 1] = 1; // w[i + 1] 포함
			knapsack(i + 1, profit + Price[i + 1], weight + Weight[i + 1]); 
			// knapsack 함수 재귀 호출, i+1번째 노드가 포함되므로, 현재 profit과 weight에 i+1의 Price와 Weight의 값을 저장
			include[i + 1] = 0; // w[i + 1] 비포함
			knapsack(i + 1, profit, weight); 
			// knapsack 함수 재귀 호출, i+1번째 노드가 포함되지않으므로, 현재 profit과 weight에 i+1의 Price와 Weight의 값을 저장하지않음
		}

	}

	public static boolean promissing(int n, int i, int profit, int weight) { // promissing 함수 정의
		int j, k; // 인덱스를 접근하기 위한 정수형 변수 선언
		int totweight; // 현재 가방에 담긴 전체 무게를 저장하는 정수형 변수 저장
		float bound; // 얻을 수 있는 최대 금액을 저장해주는 정수형 변수 저장(잘라서까지 넣을 수 있는 최대값)

		// 자식마디로 확장할 수 있을 때만 마디는 유망하다. 공간이 없으면 유망하지 않다.
		if (weight >= BackPackWeight)// 만약 weight값이 BackPackWeight(가방에 물건을 넣을 수 있는 최대값)보다 크거나 같으면
			return false; // 더이상 가방에 물건을 넣을 수 없으므로 false 반환

		else { // 만약 weight값이 BackPackWeight(가방에 물건을 넣을 수 있는 최대값)보다 작으면
			j = i + 1; // j값을 i값에 하나 증가시켜서 대입
			bound = profit; // 현재 profit을 bound에 저장
			totweight = weight; // 현재 weight의 값을 totweight에 저장
			while (j < n && totweight + Weight[j] <= BackPackWeight) { // j가 n보다 작고, totweight에 Weight[j] 더한 것 보다 BackPackWeight이 크거나 같을 때까지
				totweight = totweight + Weight[j]; // 가능한 많은 아이템을 더해 totweight 값에 대입한다
				bound = bound + Price[j]; // bound에는 반복문을 돌면서 Price 값을 모두 더해준다
				j++; // j 증가
			}
			k = j; // 책에서 사용한 식과 일관성을 유지하기 위하여 k사용
			if (k < n) // k번째 아이템의 일부분을 취함
				bound = bound + (BackPackWeight - totweight) * Price[k] / Weight[k];
			// 현재 bound에 BackPackWeight - totweight(가방에 자리가 남은 만큼) 물건의 무게당 값어치만큼 더 더해 bound에 대입해준다.
			return bound > maxprofit; // 만약 bound값이 maxprofit보다 크면 true가 되므로 유망하고, 그렇지 않다면 유망하지 않음
		}
	}
}