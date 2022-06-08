// 분기 한정법

package algorithm_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.PriorityQueue;

class node implements Comparable<node> {
	// 큐에 넣을 원소 클래스 생성
	// compartTo가 정의되어있는 Comparable의 인터페이스를 사용하기 위해 implements Comparable<node>와 같이 정의

	int level; // 트리의 레벨을 저장할 정수형 변수 선언
	int profit; // 이윤을 저장할 정수형 변수 선언 
	int weight; // 가중치를 저장할 정수형 변수 선언
	float bound; // 한계값을 저장할 정수형 변수 선언 
	int[] arr; // 최적의 경로를 저장할 정수형 배열 선언
	// 한 노드마다 최적의 경로를 배열로 저장하는 이유는 노드가 생성될 때마다 최적의 경로가 달라지기 때문임

	public node() { // 생성자
		
		// node 클래스의 멤버변수를 초기화하는 과정
		level = 0; //트리의 레벨을 0으로 초기화
		profit = 0; // 이윤을 0으로 초기화
		weight = 0; // 가중치를 0으로 초기화
		bound = 0; // 한계값을 0으로 초기화
		arr = new int[500]; // 최적의 경로를 저장할 배열을 크기 500으로 동적할당 
		
		for (int z = 0; z < 500; z++) { // 0~499까지  돌면서
			arr[z] = 0; // 최적의 경로를 저장하는 arr의 모든 원소를 0으로 초기화
	
		}
	}

	@Override // compareTo 메소드 재정의
	public int compareTo(node o) { 
		// 이 함수는 새로 생성된 bound 값과 기존 노드 o의 bound 값을 비교하여,
		// 조건식이 true일 때는 -1을 false일때는 1을 반환한다
		
		return bound <= o.bound ? -1 : 1;
		// 기존 노드가 생성된 노드의 bound보다 더 크거나 같으면 -1, 작으면 1
	}
}

public class Knapsack { // 분기한정법에서 knapsack을 담당하는 클래스 정의
	// 함수에서 지역변수로 사용되면 초기화되어 저장된 값들이 사라지는 문제가 발생하므로 다음과 같이 전역변수로 선언함
	
	public static int maxprofit; // 최대 이윤을 저장하기 위한 정수형 변수 선언
	public static int BackPackWeight; // 가방의 최대한계무게를 저장해주기 위한 정수형 변수
	public static int[] Weight = new int[500]; // 물건의 무게를 저장해줄 정수형 배열
	public static int[] Price = new int[500]; // 물건의 값어치를 저장해줄 정수형 배열

	public static int numbest = 0; // 현재 노드를 저장하는 정수형 변수

	public static int[] Order = new int[500]; // 물건의 인덱스를 저장하기 위한 정수형 배열
	public static int[] Result = new int[500]; // 물건의 무게당 값어치를 저장해줄 정수형 배열
	public static int[] bestway = new int[500]; // 최적의 경로를 저장하는 정수형 배열
	
	
	public static void main(String[] args) throws FileNotFoundException { 
		// 메인 함수 정의
		// 메인 함수 부분에서는 파일을 입력받으므로 FileNotFoundException 예외처리를 실행해준다

		int i = 0; // 배열의 인덱스를 접근하기 위한 정수형 변수

		Scanner input = new Scanner(System.in); // 사용자에게 입력을 받기 위한 Scanner 클래스
		Scanner in = new Scanner(new File("weight.txt")); 
		// weight.txt 파일을 읽기 위한 Scanner 클래스 객체 생성
	
		while (in.hasNextInt()) { // 파일에서 값을 읽었을 때 다음에 오는 값이 정수형이면
			Weight[i] = in.nextInt(); // 그 값을 Weight 배열에 읽어들인다
			i++; // i 값 증가
		}

		i = 0; // 인덱스를 접근해주는 변수 i를 초기화
		in = new Scanner(new File("price.txt")); 
		// price.txt 파일을 읽기 위한 Scanner 클래스 객체 생성
		
		while (in.hasNextInt()) { // 파일에서 값을 읽었을 때 다음에 오는 값이 정수형이면
			Price[i] = in.nextInt(); // 그 값을 Price 배열에 읽어들인다
			i++; // i 값 증가
		}

		for (int k = 0; k < 500; k++) { // 0 부터 499까지 반복문을 돌면서

			Result[k] = Price[k] / Weight[k]; // Result 배열에 무게당 값어치의 값을 저장함
			Order[k] = k; // 파일에 저장된 물건의 순서를 저장을 그대로 order 배열에 저장함
		}

		System.out.print("Input Max Weight of BackPack : ");
		// 사용자에게 가방의 최대한계 무게를 입력하라는 메세지를 출력함
		BackPackWeight = input.nextInt(); // 사용자에게 입력받음

		int temp; // 임시변수
		int j = 0; // 반복문을 실행할 정수형 변수
		i = 0; // i를 0으로 초기화

		// 파일에 저장되어있는 무게와 값어치를 받아 배열에 저장하여 무게당 값어치(Result 배열에 저장되어있음)가 큰 것부터 정렬
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
		System.out.println("****** Result ******");
		maxprofit = best_first_branch_and_bound(500); // best_first_branch_and_bound 함수 호출

		System.out.println("MaxProfit : " + maxprofit); // 최대 이익을 출력해주는 출력문
		System.out.println();

		for (int k = 0; k <= numbest; k++) { // 0부터 numbest까지 반복문을 돌면서
			System.out.print("No." + Order[k] + " Node : ");
			if (bestway[k] == 1) // 만약 bestway의 값이 1이라면
				System.out.println("Include"); // bestway 배열의 k인덱스의 노드를 포함시킨다는 메세지 출력
			else // 만약 bestway의 값이 0이라면
				System.out.println("Not Include"); // bestway 배열의 k인덱스의 노드를 포함시키지 않는다는 메세지 출력
		}
		
		in.close(); // Scanner 객체 in을 close 시킴
		input.close(); // Scanner 객체 input을 close 시킴
	}

	public static int best_first_branch_and_bound(int n) { // 분기한정법으로 배낭채우기를 구현한 함수

		PriorityQueue<node> q = new PriorityQueue<node>(); // 우선순위 큐 생성
		
		node v = new node(); // 부모 노드를 생성
		node u = new node(); // 부모노드에서 자식노드를 접근하기 위한 노드 생성
		int maxprofit; // 최대 이윤을 저장하는 정수형 변수

		// 뿌리마디 먼저 접근하기 위함
		q.clear(); // 우선순위 큐 초기화
		v.level = -1; // v의 레벨의 -1로 대입
		v.profit = 0; // v의 이윤을 0으로 대입
		v.weight = 0; // v의 무게를 0으로 대입
		maxprofit = 0; // v가 뿌리마디가 되게 최소화
		v.bound = bound(v, n); // v의 bound값 계산
		q.add(v); // q라는 큐에 v의 값을 저장

		while (!(q.isEmpty())) { // 만약 q가 비어있지 않다면
			v = q.peek(); // q의 가장 처음 노드를 peek 해서 v에 저장한 다음
			q.remove(); // q의 가장 처음 노드를 삭제

			if (v.bound > maxprofit) { // v 노드가 최대 이윤보다 좋으면
				
				// 우선순위 큐에서 가장 높은 우선순위를 가지고 있던 v노드의 다음 아이템 추가 여부를 판단하고, 
				// 유망한 노드인 경우 다음 아이템을 추가한 노드를 큐에 삽입
				u = new node(); // u에 새로운 노드를 만들어
				
				// 현재 접근한 노드의 자식 노드들을 접근하여 우선순위 큐에 저장하기 위함
				u.level = v.level + 1; // u의 레벨을 v의 레벨보다 1 증가시켜주고
				u.weight = v.weight + Weight[u.level]; // u의 무게를 v의 무게에 현재 Weight 배열의 u의 레벨 인덱스에 있는 값을 더해준 다음
				u.profit = v.profit + Price[u.level]; // u의 이윤을 v의 이윤에 현재 Price 배열의 u의 레벨 인덱스에 있는 값을 더해준다
				u.arr = v.arr.clone(); // u의 배열에 v의 배열을 복제하여 대입한다
				u.arr[u.level] = 1; // u의 배열에 u의 레벨 인덱스를 1로 대입한다(u.arr의 배열이 1의 값을 갖는다는 것은 해당 레벨의 노드를 최적의 경로에 추가한다는 것이다)

				if (u.weight <= BackPackWeight && u.profit > maxprofit) { 
					// 만약 u의 무게가 가방에 넣을 수 있는 최대치보다 작거나 같고, u의 이윤이 최대 이윤보다 작다면
					maxprofit = u.profit; // 최대이윤을 u의 이윤으로 대입하고
					numbest = u.level; // 현재 노드를 저장하는 변수인 numbest의 값에 u의 레벨을 대입시킨다

					for (int z = 0; z <= numbest; z++) { // 0부터 numbest까지 돌면서
						bestway[z] = u.arr[z]; // bestway 배열에 u의 배열의 값을 대입
					}
				}

				u.bound = bound(u, n); // u의 한계값을 저장(bound 함수를 호출함)
				if (u.bound > maxprofit) { // 만약 u의 한계값이 현재 최대이윤보다 크다면
					q.add(u); // 우선순위 큐에 u의 노드를 추가
				}
				
				
				// 우선순위 큐에서 가장 높은 우선순위를 가지고 있던 v노드의 다음 아이템 추가 여부를 판단하고, 
				// 다음 아이템을 추가하지 않은 노드를 큐에 삽입
				u = new node(); // u의 새로운 노드를 만든다	
				u.level = v.level + 1; // u의 레벨을 v의 레벨보다 1 증가시켜주고
				u.weight = v.weight; // u의 무게에 v의 무게를 대입한다
				u.profit = v.profit; // u의 이윤에 v의 이윤을 대입한다
				u.arr = v.arr.clone(); // u의 배열에 v의 배열을 복제하여 대입한다
				u.arr[u.level] = 0; // u의 배열에 u의 레벨 인덱스를 0로 대입한다(u.arr의 배열이 0의 값을 갖는다는 것은 해당 아이템을 최적의 경로에 추가하지 않는다는 것이다)
				u.bound = bound(u, n); // // u의 한계값을 저장(bound 함수를 호출함)

				if (u.bound > maxprofit) { // 만약 u의 한계값이 현재 최대이윤보다 크다면

					q.add(u);// 우선순위 큐에 u의 노드를 추가
				}
			}
		}

		return maxprofit; // 최대 이윤을 반환

	}

	// 얼마나 더 넣을 수 있냐(유망성)
	public static float bound(node u, int n) { // 노드의 한계치를 계산해주는 함수
		int j, k; // 인덱스를 접근하기 위한 정수형 변수 선언
		int totweight; // 현재 가방에 담긴 전체 무게를 저장하는 정수형 변수 저장
		float result; // 결과를 저장할 실수형 변수 선언

		if (u.weight >= BackPackWeight) // 만약 현재 가방이 가방의 전체 무게보다 크거나 같으면 
			return 0; // 더이상 가방에 넣을 수 없으므로 유망하지 않음 (0 반환)

		else { // 만약 현재 가방이 가방의 전체 무게보다 작으면 
			result = u.profit; // 결과를 저장하는 변수에 노드의 이윤 값을 저장
			j = u.level + 1; // j에 노드 레벨에 1을 증가
			totweight = u.weight; // 현재 가방에 담긴 전체 무게를 저장하는 변수에 노드의 무게를 대입

			while (j < n && totweight + Weight[j] <= BackPackWeight) { 
				// 만약 j가 n보다 작고, 현재 가방에 담긴 전체 무게에 Weight 배열의 j번째 값을 더한 것이 가방이 넣을 수 있는 최대치보다 작거나 같으면
				totweight = totweight + Weight[j]; // 현재 가방에 담긴 전체 무게를 저장하는 정수형 변수 totweight에 Weight 배열에 j번째 값을 더한 것을 totweight에 대입하고
				result = result + Price[j]; // 결과를 저장하는 result에 현재 값어치를 저장하는 Price를 더한 다음 result에 대입한다
				j++; // j를 증가
			}

			k = j; // k에 j를 대입한 뒤
			if (k < n) // 만약 k가 n보다 작으면
				result = result + (BackPackWeight - totweight) * Price[k] / Weight[k];
			// 현재 bound에 BackPackWeight - totweight(가방에 자리가 남은 만큼)* 물건의 무게당 값어치만큼 더 더해 result에 대입해준다.
			return result; // result 반환(실제 bound 값이 됨)

		}

	}
}