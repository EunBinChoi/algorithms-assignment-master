package algorithm_04;

import java.util.*;

import java.io.*;

public class MergeSort { // 합병정렬을 담당하는 클래스

	public static void main(String[] args) throws FileNotFoundException { // 메인함수
		// 여기서는 파일을 읽어주는 부분이 있으므로 FileNotFoundException와 같이 예외처리를 해준다

		System.out.println("------------------------------- Merge Sort Start ! -------------------------------");
		System.out.println();

		int[] arr; // 합병정렬을 하기 위한 값들을 배열(arr)에 저장함
		int count = 0; // 읽어들이는 파일에 있는 값들의 갯수를 저장하는 변수
		String fname1; // 읽어들일 파일 이름을 저장할 문자형 변수

		Scanner in2 = new Scanner(System.in); // 사용자의 입력을 담당하는 Scanner 객체를 생성

		System.out.printf("읽고자하는 파일의 이름을 작성하시오(예 a.txt) => ");
		fname1 = in2.nextLine(); // fname1 변수에 in2 객체가 읽어들인 파일 이름을 저장함

		System.out.println(); // 뛰어쓰기를 위한 명령문
		System.out.println(">> 정렬 전 : " + fname1 + "의 내용은 다음과 같습니다"); // 정렬 전에
																		// 파일에
																		// 저장된
																		// 값들을
																		// 출력
		
		Scanner in = new Scanner(new File(fname1)); // 파일을 읽어오기 위한 Scanner 객체
													// 생성하고 fname1의 이름을 가진 파일을
													// 가져옴
		while (in.hasNextInt()) { // 만약 파일에 저장된 다음 값이 int 값이라면
			in.nextInt(); // 파일에서 다음 값을 읽음
			count++; // 파일에 저장된 정수의 갯수를 저장하는 count 변수를 증가시킴
		}
		arr = new int[count]; // 크기 count의 배열을 생성함

		in = new Scanner(new File(fname1)); // fname1의 이름을 가진 파일을 가져옴(파일의 처음부터
											// 다시 읽기 위한 방법임)
		int i = 0; // 파일에 저장된 값들을 배열에 저장된 인덱스로 접근하기 위해서 변수 i를 생성
		while (i < count) { // i가 배열의 크기보다 자으면

			arr[i] = in.nextInt(); // arr 배열에 순서대로 파일에서 읽어온 값을 저장함
			if ((i + 1) % 5 == 0) { // 만약 i+1이 5의 배수라면(출력을 5개씩 하기 위함, i+1인 이유는
									// i가 0부터 시작하기 때문이다)
				System.out.printf("%15d", arr[i]); // 배열의 값을 출력함
				System.out.println(); // 그리고 5개씩 출력할 것이므로, 5의 배수일때마다 띄어쓰기를 함
			} else
				System.out.printf("%15d", arr[i]); // 5의 배수가 아니라면, 배열의 값만 출력함

			i++; // i의 값을 증가시킴
		}

		mergeSort(count, arr); // mergesort 함수를 호출함

		String fname2; // 합병정렬의 결과를 파일로 저장하기 위해 파일 이름을 저장할 문자형 변수

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.printf("저장하고자 하는 파일의 이름을 작성하시오(예 a.txt) => ");
		// 합병정렬의 결과를 사용자가 원하는 파일의 이름으로 저장하기 위해 사용자가 원하는 파일 이름을 사용자가 지정해주는 명령문
		fname2 = in2.nextLine(); // 사용자가 작성한 파일이름을 in2 객체를 통해 입력받음

		try { // FileWriter 클래스는 IOException을 추가해줘야함
			FileWriter out = new FileWriter(fname2);
			// 파일을 작성하는 클래스를 생성하여 out이라는 이름을 가진 객체를 생성함

			for (int k = 0; k < count; k++) { // 반복문을 0부터 count전까지 돌면서
				out.write(Integer.toString(arr[k]));
				// 파일에 arr 배열의 값을 String으로 변환해서 작성한다
				out.write("\r\n"); // 개행 후 커서를 가장 앞으로 바꿔줌
			}
			out.close(); // 파일 객체 종료시킴
		} catch (IOException e) {
			System.out.println("Error!");
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
		in.close(); // 객체 in 종료시킴
		in2.close(); // 객체 in2 종료시킴

	}

	public static void mergeSort(int count, int arr[]) { // 배열을 두 개의 배열로 나누는 작업
		if (count > 1) { // count가 1보다 클 때에만 작동한다(count가 1보다 작아지면 배열을 두 개로 나눌 수
							// 없기 때문에)
			int h = count / 2; // count를 2로 나눈 값을 m에 저장
			int m = count - h; // count에서 h만큼 뺀 값을 m에 저장

			int[] U = new int[h]; // 배열 U의 값을 크기 h로 할당
			int[] V = new int[m]; // 배열 v의 값을 크기 m로 할당

			for (int i = 0; i < h; i++) { // 반복문을 돌면서
				U[i] = arr[i]; // arr에 0~h값을 배열 U에 대입

			}

			for (int i = h; i < count; i++) { // 반복문을 돌면서
				V[i - h] = arr[i]; // arr에 h~count값을 배열 V에 대입

			}

			mergeSort(h, U); // h의 크기의 배열 U를 다시 mergeSort로 호출하여 두 개의 배열로 나누는 작업을
								// 반복함
			mergeSort(m, V); // m의 크기의 배열 V를 다시 mergeSort로 호출하여 두 개의 배열로 나누는 작업을
								// 반복함
			merge(h, m, U, V, arr); // 나눠진 배열을 다시 합치는 merge 함수를 호출함
		}
	}

	public static void merge(int h, int m, int U[], int V[], int S[]) { // 나눠진
																		// 배열을
																		// 다시
																		// 합치는
																		// 함수

		int i = 0, j = 0, k = 0; // 반복을 위한 변수 선언

		while (i < h && j < m) { // i와 j가 h,m보다 작을 때 while문을 돌면서
			if (U[i] < V[j]) { // 만약 V[j]보다 U[i]가 더 작으면
				S[k] = U[i]; // S[k]에 U[i]을 대입한다(더 작은 걸 집어넣어서 오름차순으로 하기 위한 것임)
				i++; // 배열 U를 담당하는 인덱스 i에 해당하는 것을 집어넣었으므로, i를 증가
			}

			else { // 만약 V[j]보다 U[i]가 더 크다면
				S[k] = V[j]; // S[k]에 V[j]을 대입한다(더 작은 걸 집어넣어서 오름차순으로 하기 위한 것임)
				j++; // 배열 V를 담당하는 인덱스 j에 해당하는 것을 집어넣었으므로, j를 증가
			}
			k++; // 배열 S를 담당하는 인덱스 k를 증가시킴
		}

		if (i >= h) { // i가 h보다 커졌다는 의미는 V 배열의 값이 남았다는 의미이므로
			for (int x = j; x < m; x++) { // j부터 m까지 S배열에 대입함
				S[k] = V[x];
				k++;

			}

		} else { // 그게 아니라면, U 배열의 값이 남았다는 의미이므로
			for (int x = i; x < h; x++) { // i부터 h까지 S배열에 대입함
				S[k] = U[x];
				k++;

			}

		}
	}
}
