package algorithm_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FillBackPack { // 빈틈없이 배낭채우기를 구현하기 위한 클래스

	public static void main(String[] args) throws FileNotFoundException { // 메인 함수 정의
		// 메인 함수 부분에서는 파일을 입력받으므로 FileNotFoundException 예외처리를 실행해준다
		int[] Weight = new int[500]; // 물건의 무게를 저장해줄 정수형 배열
		int[] Price = new int[500]; // 물건의 값어치를 저장해줄 정수형 배열
		int[] Result = new int[500]; // 물건의 무게당 값어치를 저장해줄 정수형 배열
		int[] Order = new int[500]; // 현재 파일에 저장된 물건의 순서를 저장해주기 위한 배열
		
		int i = 0; // 배열의 인덱스를 접근하기 위한 정수형 변수
		int BackPackWeight; // 가방의 최대한계무게를 저장해주기 위한 정수형 변수

		
		System.out.println("--------------------------- FillBackPack Start! ---------------------------");
		System.out.println();
		System.out.println();
		String fname; // 파일의 이름을 입력받을 문자열 변수
		
		Scanner input = new Scanner(System.in); // 사용자에게 입력을 받기 위한 Scanner 클래스 객체 생성
		System.out.print("Input Name Saved Weight : "); // 사용자에게 물건의 무게가 저장되어있는 파일의 이름을 입력하라는 메세지를 출력함
		fname = input.next(); // 사용자에게 물건의 무게가 저장되어있는 파일의 이름을 입력받음

		Scanner in = new Scanner(new File(fname)); // 파일을 읽이 위한 Scanner 클래스 객체 생성
		while (in.hasNextInt()) { // 파일에서 값을 읽었을 때 다음에 오는 값이 정수형이면
			Weight[i] = in.nextInt(); // 그 값을 Weight 배열에 읽어들인다
			i++; // i 값 증가
		}
		

		System.out.print("Input Name Saved Price : "); // 사용자에게 물건의 값어치가 저장되어있는 파일의 이름을 입력하라는 메세지를 출력함
		fname = input.next(); // 사용자에게 물건의 값어치가 저장되어있는 파일의 이름을 입력받음

		i = 0; // i를 0 으로 초기화
		in = new Scanner(new File(fname)); // 파일을 읽이 위한 Scanner 클래스 객체를 초기화 함
		while (in.hasNextInt()) { // 파일에서 값을 읽었을 때 다음에 오는 값이 정수형이면
			Price[i] = in.nextInt(); // 그 값을 Price 배열에 읽어들인다
			i++; // i 값 증가
		}
	
		for (int k = 0; k < 500; k++) { // 0 부터 499까지 반복문을 돌면서 

			Order[k] = k; // 현재 순서를 order 배열에 저장
			
		}

		for (int k = 0; k < 500; k++) { // 0 부터 499까지 반복문을 돌면서 

			Result[k] = Price[k] / Weight[k]; // Result 배열에 무게당 값어치의 값을 저장함
			
		}

		System.out.print("Input Max Weight of BackPack : "); // 가방의 최대한계 무게를 사용자에게 입력하라는 메세지를 출력함
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

					// Price 배열과  Weight 배열 모두 바꾸는 이유는 무게당 값어치가 큰 것부터 Price 배열와  Weight 배열도 같이 바꿔서 
					// Price, Weight, Result의 같은 인덱스에 같은 물건의 값어치, 무게, 무게당 값어치가 오게 하기 위함이다.
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
		System.out.println("--------------------------- Print Best Order! ---------------------------");
		System.out.println();
	
		
		int sum = 0; // 지금까지 가방에 저장한 물건의 무게를 저장하는 정수형 변수
		i = 0;
		while(sum < BackPackWeight){ // 반복문을 돌면서
			System.out.println(i+1+". "+ "Order Of Item : " + Order[i]); // 무게당 값어치가 큰 값부터 출력해주고
			sum += Weight[i]; // sum 변수에 이 값을 더해준다
			i++; // i 증가
		}
		
		in.close();
		input.close();
		
	}

}

