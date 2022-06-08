// 동적 계획

///**
// * 
// * @author Jaeu Jeong
// * email : wodndb@koreatech.ac.kr
// * 
// * 동적계획법으로 0-1 knapsack 문제 해결하기
// * 본 소스코드는 2016학년도 1학기 한국기술교육대학교 컴퓨터공학부 알고리즘 및 실습 실습수업 교육자료로서,
// * Foundation of Algorithms using C++ Algorithm (Richard Neapolitan / Kumarss Naimipour 저)
// * 에서 소개한 동적계획법 알고리즘 개념을 보고 만든 소스코드입니다. (의사코드 없이 개념만 참고) 
// * 상업용 목적으로 이용될 수 없음을 밝힙니다.
// * 
// */
package algorithm_12_2;

import java.util.*;
import java.io.*;

// 행은 1부터, 열은 0부터 시작하였습니다
public class knapsackFin { // 동적계획법으로 배낭채우기를 구현하는 클래스

	// 아이템 관련 변수
	static int item[][]; 
	// 아이템 정보 : 아이템 번호(ID), 가격(PRICE), 무게(WEIGHT), 중량당 가격(RESULT)

	// 인덱스를 접근하기 위한 값
	static int INDEX = 0; // 아이템 번호(ID) 값을 0으로 설정
	static int PRICE = 1; // 가격(PRICE) 값을 1으로 설정
	static int WEIGHT = 2; // 무게(WEIGHT) 값을 2으로 설정
	static int RESULT = 3; // 중량당 가격(RESULT) 값을 3으로 설정

	// 동적 계획법 관련
	static int W; // 가방 한계 무게를 저장하는 정수형 변수(사용자가 입력받을 수 있게 함)
	static int maxProfit; // 최적의 이익을 저장하는 정수형 변수
	static int maxElement = 500; // 최대 아이템 개수을 500으로 초기화
	static int maxWeight = 500; // 최대 무게를 500으로 초기화
	
	static int numbest; // 최적의 이익을 얻을 수 있는 아이템 부분집합의 마지막 아이템 번호를 저장하는 정수형 변수
	
	static int[] bestset; // 최적의 이익을 얻을 수 있는 아이템들의 부분집합
	static int[][] P; // P[i][w] : 1~i번째 아이템을 따졌을 때 w이내에서 얻을 수 있는 최적의 이익
	// 세로축 0~i 아이템 고려한 상태, 가로축은 무게 
	static boolean[][] E; // P[최대 아이템 개수][가방의 한계 무게]를 구하기 위해 꼭 필요한 P[][]값이 무엇인지
							// 표시하기 위한 배열 (교재 용어로는 엔트리)

	static int[] price = new int[500]; // 파일에 저장되어있는 500개의 가격을 받아오기 위해 배열의 크기를
										// 500으로 설정
	static int[] weight = new int[500]; // 파일에 저장되어있는 500개의 무게를 받아오기 위해 배열의 크기를
										// 500으로 설정

	/**
	 * URL\
	 * http://stackoverflow.com/questions/15452429/java-arrays-sort-2d-array
	 * 
	 * @param arr
	 *            Item to sorting
	 */
	public static void readData(int n, String fileName, int[] A) throws IOException {
		// 파일에서 값을 읽어들이는 함수
		// 파일에서 값을 읽어오기 떄문에 IOException와 같은 예외처리를 해줘야한다.
		Scanner sc = new Scanner(new FileInputStream(fileName));
		// 파일에서 값을 읽어들이기 위해 Scanner 클래스의 객체를 생성
		for (int i = 0; i < n; i++) { // 반복문을 0~n-1까지 돌면서
			A[i] = sc.nextInt(); // 파일에 읽는 값을 읽어들이면서 A[i]에 대입
		}
		sc.close(); // sc 변수 close 시킴

	}

	static void sortItem(int[][] item, int n) { // 배열에 저장된 값으로 정렬하는 함수
		Arrays.sort(item, new Comparator<int[]>() {
			// Comparator라는 객체를 만드는데, JAVA에서는 이렇게 인라인으로 구현하는 방식이 있다.
			public int compare(int[] item1, int[] item2) {
				return Integer.compare(item1[n], item2[n]); // 내림차순. 반대면 오름차순.
			}
		});
	}

	/**
	 * 엔트리를 구하는 함수 P[n][W]를 구하는 데 꼭 필요한 [][]값을 표시하는, 즉 Entry(E[][])를 구하는 함수.
	 * 재귀적으로 돌아가기 때문에 파라미터가 반드시 필요하다.
	 * 
	 * @param i
	 *            탐색 대상 : 1~i번째 아이템
	 * @param accW
	 *            한계 무게 : 1~i번째 아이템을 가방에 담을 때 한계 무게. 누적(accumulated) 개념이라 변수명에
	 *            acc 붙임.
	 */
	
	static void decideEntry(int i, int accW) { // 엔트리(E[][])를 구하는 함수(E 배열은 밑에서부터 올라가면서 재귀호출을 시행해 유효한 값인지 아닌지만 표시해줌)
		// accw의 초기값은 사용자가 입력한 W와 같다
		E[i][accW] = true; // E[i][accW]의 값을 true로 설정
		if (i > 1) { // 만약 i가 1보다 크면(넣을 수 있는 아이템이 남아있으면)
			if (!E[i - 1][accW]) { // 만약 E[i][accW](포함안된 값)의 값이 false이면 다음과 같이 실행(이미 true면 재귀호출할 필요가 없음)
				decideEntry(i - 1, accW); // 재귀 호출 (넣을 수 있는 값으로 표시)
			} 
			if (accW >= item[i][WEIGHT]) { // accW가 i번째 무게보다 크거나 같으면(뺄 수 있음)
				if (!E[i - 1][accW - item[i][WEIGHT]]) { // 만약 포함된 값이 false 상태면
					decideEntry(i - 1, accW - item[i][WEIGHT]); // 재귀 호출 (넣을 수 있는 값으로 표시)
				}
			}
		}
	}

	/**
	 * evalMaxP는 P[][] 값을 구하는 함수 전제조건 : decideEntry 함수를 실행시켜야 함.
	 */
	static void evalMaxP()  {
	//	int maxKnapsackWeight = maxElement * maxWeight; // 아무리 가방에 아이템을 전부 넣어도 아이템 개수 * 아이템 최대 무게보다는 작다.
		// 무작정 큰 갑	
		
		// 이차원 배열 전체를 돌면서
		for (int i = 1; i <= maxElement; i++) { 
			for (int j = 0; j <= W; j++) {
				if (E[i][j] == true) { // 구해야 할 엔트리라고 하면
					if (j >= item[i][WEIGHT]) { // 아이템의 무게가 현재 무게보다 클 때(아래의 연산이 빼기가 가능할 때)
						P[i][j] = Math.max(P[i - 1][j], item[i][PRICE] + P[i - 1][j - item[i][WEIGHT]]);
						// 왼쪽은 이전값이 안들어갔던 경우, 오른족은 이전값이 들어갔던 경우
					} else { // 아이템을 넣지 못하는 경우
						P[i][j] = P[i - 1][j];
					}
				}
			}
		}
	}

	/**
	 * 최적의 아이템 집합을 찾아서 bestSet에 저장. 전제조건 : decideEntry 함수와 evalMaxP 함수를 실행해야 제대로
	 * 동작한다.
	 */
	static void findBestSet() {
		int tWeight = W; // 임시로 저장할 무게 변수. 일단 P[n][W]부터 탐색해야 하므로 W(사용자가 입력한 가방의 무게)를 대입한다.
		for (int i = 500; i >= 1; i--) {
			if (P[i][tWeight] == P[i - 1][tWeight]) { // i번째 아이템을 선택하지 않았으면 1~i-1 아이템의 최적의
											// 이익과 1~i 아이템의 최적의 이익이 동일하다. 즉 i번째
											// 아이템을 먹지 않았다는 의미다.
				bestset[i] = 0; // i번째 아이템은 최적의 아이템 부분집합에 포함되지 않는다.
			} else { // i번째 아이템을 선택했으면 1~i-1 아이템의 최적의 이익과 1~i 아이템의 최적의 이익이 동일하지
						// 않다. 즉, i번째 아이템을 먹었다는 의미다.
				numbest = Math.max(numbest, i); // numbest 결정(가장 마지막에 고려해준 인덱스를 저장)
				bestset[i] = 1; // i번째 아이템은 최적의 아이템 부분집합에 포함된다.
				tWeight = tWeight - item[i][WEIGHT];
				// i번째 아이템이 최적의 아이템 부분집합에 포함된 것이므로 무게를 반영하여 i-1번째의 경우를 다시 구한다.
					
			}
		}
	}

	/**
	 * 준비함수 사용자 및 파일에서 데이터를 읽어오고, 배열을 초기화하는 역할을 한다.
	 * 
	 * @throws IOException
	 *             // 파일 입출력 예외처리
	 */
	static void prepareDPKnapSack() throws IOException { // 배낭채우기에 필요한 변수값들을 초기화 및 아이템을 내림차순으로 정렬하는 함수
		// 파일에서 읽어들이는 readData 함수가 있으므로, IOException으로 예외처리를 실행해준다
		// 1. 가방의 한계 무게를 사용자로부터 입력받는다.
		System.out.print("Input Max Weight of BackPack : "); // 사용자로부터 최대 한계 무게를
																// 입력받기 위한 메세지
		Scanner sc = new Scanner(System.in); // Scanner 클래스의 객체 변수 sc 생성
		W = sc.nextInt(); // 사용자에게 최대 한계 무게를 입력받음

		// 2. 각종 변수들을 초기화한다.
		item = new int[maxElement + 1][4]; // 아이템 초기화
		bestset = new int[maxElement + 1]; // 최고 이익을 얻을 수 있는 물건들
		P = new int[maxElement + 1][W + 1];
		// 동적 계획법 : 가방의 무게에 따라 얻을 수 있는 최대 이익을 저장한 배열
		E = new boolean[maxWeight + 1][W + 1];
		// 동적 계획법 : 가방의 무게에 따라 얻을 수 있는 최대 이익을 저장한 배열

		numbest = 0; // numbest의 값을 0으로 초기화
		maxProfit = 0; // maxProfit을 0으로 초기화

		// 3. 파일에서 아이템의 정보를 입력받는다.
		readData(500, "price.txt", price);
		readData(500, "weight.txt", weight);

		for (int i = 0; i < 500; i++) { // item 배열에 원소를 저장
			item[i][0] = i; // 첫번째 원소를 index로 저장
			item[i][1] = price[i]; // 두번째 원소를 가격으로 저장
			item[i][2] = weight[i]; // 세번째 원소를 무게로 저장
			item[i][3] = price[i] / weight[i]; // 네번째 원소를 중량당 가격으로 저장
		}

		// 4. 입력받은 아이템을 무게당 값어치로 정렬한다.
		sortItem(item, RESULT);
	}

	// 시작 함수: 백트래킹 함수를 수행하고 그 결과를 출력하는 함수.
	// prepareBT 함수를 실행시킨 다음에 이 함수를 실행하야 한다.
	static void startDPKnapSack() {
		decideEntry(maxElement, W); // 계산할 엔트리 구하기
		evalMaxP(); // 위에서 구한 엔트리들을 이용하여 상향식으로 P[n][W]값 구하기
		findBestSet(); // 최적의 집합을 찾는다.
		maxProfit = P[maxElement][W]; // 최적의 이익
	}

	public static void main(String[] args) throws IOException {
		prepareDPKnapSack(); // 배낭채우기에 필요한 변수값들을 초기화 및 아이템을 내림차순으로 정렬하는 함수
		startDPKnapSack(); // 시작함수(백트래킹 함수)를 수행하고 그 결과를 출력하는 함수 호출

		System.out.println();
		System.out.println("****** Result ******");
		System.out.println("MaxProfit : " + maxProfit);
		
		for (int r = 0; r <= numbest; r++) {
			System.out.print("No." + item[r][INDEX] + " Node : ");
			// 인덱스 출력
			if (bestset[r] == 1) // 만약 bestset의 값이 1이라면
				System.out.println("Include"); // bestset 배열의 r인덱스의 노드를 포함시킨다는
												// 메세지 출력
			else // 만약 bestset의 값이 0이라면
				System.out.println("Not Include"); // bestset 배열의 r인덱스의 노드를
													// 포함시키지 않는다는 메세지 출력

		}

	}
}
