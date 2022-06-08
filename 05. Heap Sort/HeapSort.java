package algorithm_05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HeapSort { // heap 정렬을 위한 클래스 정의

	public static int count = 0; // 배열의 크기를 정해줄 count 변수 선언
	public static int count2 = -1; // heap 정렬에서 완전이진트리를 접근하기 위한 count2 변수 선언(인덱스를 0부터 사용하기 위함)

	public static void main(String[] args) throws FileNotFoundException { // 메인함수 정의
		// 여기서는 파일을 읽어주는 부분이 있으므로 FileNotFoundException와 같이 예외처리 해준다

		int[] arr; // 파일에서 읽어온 값들을 배열에 저장함
		int[] arr2; // 값을 삽입시킨 결과를 저장해줄 배열을 저장함
		int[] arr3; // 값을 삭제시키며 정렬한 결과를 저장해줄 배열을 저장함(힙 정렬을 마친 다음 정렬된 배열을 저장해주는 공간)

		String fname; // 값을 읽어들일 파일 이름을 문자열 변수로 저장
		String fname2; // 값을 저장할 파일 이름을 문자열 변수로 저장
 
		Scanner in2 = new Scanner(System.in); // 사용자에게 입력을 받기위한 Scanner 클래스 생성

		System.out.println("----------------- Heap Sort Start!! -----------------");
		System.out.println();
		System.out.printf("읽고자하는 파일의 이름을 작성하시오(예 a.txt) => "); // 사용자에게 읽고자하는 파일의 이름을 입력하라는 메세지 출력
		fname = in2.nextLine(); // 사용자가 입력한 파일의 이름을 fname에 저장함
		System.out.println(); // 뛰어쓰기를 위한 명령문
		System.out.println(">> 정렬 전 : " + fname + "의 내용은 다음과 같습니다");
		System.out.println();

		
		
		Scanner in = new Scanner(new File(fname));
		// 파일을 읽어오기 위한 Scanner 클래스 생성 
		
		while (in.hasNextInt()) { // 만약 정수 데이터가 있다면
			in.nextInt(); // 정수 데이터를 읽어들임
			count++; // 읽어들인 파일에 저장된 데이터가 몇 개인지 세기 위헤 count 변수 증가
		}

		arr = new int[count]; // arr 배열을 count 크기로 동적할당 해줌
		arr2 = new int[count]; // arr2 배열을 count 크기로 동적할당 해줌 
		arr3 = new int[count]; // arr3 배열을 count 크기로 동적할당 해줌

		in = new Scanner(new File(fname));
		// 파일을 처음부터 다시 읽어주기 위함
		
		// 현재 파일에 저장된 데이터를 콘솔에 출력하기 위함
		int i = 0; // 반복을 위한 변수 i 선언
		while (i < count) { // i가 count보다 작으면 while문을 실행
			arr[i] = in.nextInt(); // arr 배열에 파일에서 정수 데이터를 하나씩 읽어 대입해준다
			
			// 한 줄에 5개의 데이터만 출력하기 위함
			if ((i + 1) % 5 == 0) { // 만약 i+1이 5의 배수라면(i+1로 하는 이유는 0부터 시작하면 제대로 출력이 되지 않기 때문)
				System.out.printf("%15d", arr[i]); // 데이터 출력
				System.out.println(); // 띄어쓰기를 위한 명령문
			}

			else // 만약 5의 배수가 아니라면
				System.out.printf("%15d", arr[i]); // 그냥 데이터 출력

			i++; // 변수 i를 증가시킴
		}

		for (int k = 0; k < count; k++) { // count 만큼 반복하면서
			insert_max_heap(arr2, arr[k]);
			// insert_max_heap이라는 함수를 실행시킨다. 이 함수는 arr의 배열에 있는 값들을 차례대로 접근하여 이진트리(arr2)에 넣어주기 위함

		}

		for (int k = 0; k < count; k++) { // count 만큼 반복하면서
			arr3[count2] = delete_max_heap(arr2);
			// delete_max_heap이라는 함수를 실행시킨다. 이 함수는 이진트리에 저장된 arr2 값들을 삭제시키면서 arr3에 저장하기 위함이다
			// 이진트리에 저장된 arr2 값들은 기본적으로 정렬이 되어있으므로 arr3의 값도 정렬되어있다.

		}

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.printf("저장하고자 하는 파일의 이름을 작성하시오(예 a.txt) => ");
		fname2 = in2.nextLine();
		// 배열의 값을 저장하려고 하는 파일의 이름을 사용자에게 입력받음 

		try { // FileWriter 클래스는 IOException을 추가해줘야함
			FileWriter out = new FileWriter(fname2);
			// 파일을 작성하는 클래스를 생성하여 out이라는 이름을 가진 객체를 생성함

			for (int k = 0; k < count; k++) { // 반복문을 0부터 count전까지 돌면서
				out.write(Integer.toString(arr3[k]));
				// 파일에 arr 배열의 값을 String으로 변환해서 작성한다
				out.write("\r\n"); // 개행 후 커서를 가장 앞으로 바꿔줌
			}
			out.close(); // 파일 객체 종료시킴
		} catch (IOException e) { // 만약 IOException이 발생하면
			System.out.println("Error!"); // 에러라는 메세지를 출력
		}

		System.out.println();
		System.out.println(">> 정렬 후 : " + fname2 + "의 내용은 다음과 같습니다");

		in = new Scanner(new File(fname2));
		// fname2의 이름을 가진 파일을 가져옴(파일의 처음부터 읽기 위한 방법임)
		
		int k = 0;

		while (k < count) { // k가 count보다 작을 때 반복문을 돌면서
			if ((k + 1) % 5 == 0) { // k+1이 5의 배수일 때는
				System.out.printf("%15d", in.nextInt()); // 파일에 저장된 값을 출력하고
				System.out.println(); // 띄어쓰기를 함
			} else // 5의 배수가 아닐 때에는
				System.out.printf("%15d", in.nextInt()); // 파일에 저장된 값을 출력만 함

			k++; // k를 증가시킴
		}

		in.close();
		in2.close();

	}

	public static void insert_max_heap(int[] arr, int key) { // 힙에 데이터를 저장하기 위한 함수

		int j; // 반복문을 돌기위한 변수 선언
		int temp; // 두 개의 값을 swap 하기 위한 임시 변수 선언

		count2 = count2 + 1; // 데이터가 삽입될 때마다 count2를 증가시킴
		j = count2; // 증가시킨 count2 변수를 j에 저장
		arr[j] = key; // 이진 트리의 가장 마지막에 key(삽입할 데이터)를 대입

		while (j != 0 && arr[j] > arr[(j - 1) / 2]) { // 만약 j가 0이 아니고 부모노드가 자식노드보다 작다면(이진트리에서 항상 부모노드는 자식노드보다 커야함)
			temp = arr[(j - 1) / 2]; // 부모노드를 temp에 저장해주고
			arr[(j - 1) / 2] = arr[j]; // 부모노드에 자식노드를 대입
			arr[j] = temp; // 자식노드에 temp의 값을 대입
			j = (j - 1) / 2; // j를 부모노드로 접근할 수 있게 함
		}

	}

	public static int delete_max_heap(int[] arr) { // 힙에 데이터를 삭제하기 위한 함수(항상 루트를 반환함)
		int item; // 삭제된 값을 저장하는 변수

		item = arr[0]; // 루트(최상위 부모노드)의 값을 item에 저장(item은 삭제된 값을 저장하기 위함)
		arr[0] = arr[count2]; // 루트의 값에 가장 말단의 값을 대입한다
		count2 = count2 - 1; // 이진트리에서 노드 하나가 삭제되었으므로 count2를 하나 감소시킴

		int i = 1; // 반복을 하기위한 변수 i 선언
		int largest; // 자식 노드 중 큰 값을 저장하기 위한 변수
		int temp; // 두 개의 값을 swap 하기 위한 임시 변수 선언

		while (i <= count2) { // i가 count2와 같을 때까지 while문을 돌림
			if ((i < count2) && (arr[i + 1] > arr[i])) // 만약 i가 count2보다 작고, 자식 노드 중에 더 큰 것을 largest로 저장
				largest = i + 1;
			else
				largest = i;

			if (arr[(largest - 1) / 2] > arr[largest]) // 만약 부모느도가 자식노드보다 크면 
				break; // break
			else { // 만약 부모노드가 자식노드보다 작으면
				// 부모노드와 자식노드 값을 swap 시킴
				temp = arr[(largest - 1) / 2];
				arr[(largest - 1) / 2] = arr[largest];
				arr[largest] = temp;
			}
			i = largest * 2 + 1; // 다음 자식노드를 검사하기 위해 i를 largest에 2배 +1을 한다

		}

		return item; // 삭제한 원소 return 함
	}
}
