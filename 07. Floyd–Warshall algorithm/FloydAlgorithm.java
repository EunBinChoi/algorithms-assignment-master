package algorithm_07;

import java.io.*;
import java.util.Scanner;

public class FloydAlgorithm { // Floyd 알고리즘을 구현하기 위한 클래스 정의

   /*
    * q에서 r로 가는 경로를 파일로 출력하는 함수. 의사 코드를 수정한 것임. printData 함수에서 필요한 기능을 구현하기 위해
    * 만든 함수이므로 바로 호출하지 말 것.
    */
   public static void outFilePath(FileOutputStream out, int[][] P, int q, int r) throws IOException {

      if (P[q][r] != 0) { // 만약 P[q][r]이 0이 아니면(q->r로 가는 경로에서 거쳐가는 다른 노드가 있다면)
         outFilePath(out, P, q, P[q][r]); // 재귀함수를 통해 q-k로 가는 경로를 구해준다
         out.write((char) P[q][r]); // 파일에 경로 출력
         outFilePath(out, P, P[q][r], r); // 재귀함수를 통해 k-r로 가는 경로를 구해준다
      }
   }// 만약 1->2로 가는 최단 거리가 1->3->4->2라면, P[1][2]에는 4가 저장되어있고, P[1][4]에는 3이
      // 저장되어있기 떄문에,
      // 재귀 함수를 통해 3과 4를 출력한다. 그리고 P[4][2]의 값은 0이기 때문에 아무것도 출력하지 않아 1->3->4->2
      // 최단겅로를 출력할 수 있는 것이다

   // 파일로 모든 정점에 대한 경로를 출력하는 함수.
   public static void printData(String fileName, int[][] P) throws IOException {

      FileOutputStream out = new FileOutputStream(fileName);

      // 이중 반복문을 돌면서 파일의 최단 경로를 출력한다
      for (int i = 0; i < 100; i++) {
         for (int j = 0; j < 100; j++) {
            // i->j로 가는 최단경로
            out.write((char) i); // i출력
            outFilePath(out, P, i, j); // outFileWeight 함수를 통해 i와 j사이의 경로를 출력
            out.write((char) j); // j출력
         }
      }

      out.close(); // out 변수를 close 시킴
   }

   // 경로를 구해 콘솔에 출력하기 위한 함수
   // 위의 함수처럼  printPath 함수에서 필요한 기능을 구현하기 위해 만든 함수
   public static void outPath(int[][] P, int q, int r) throws IOException { 

      if (P[q][r] != 0) { // 만약 P[q][r]이 0이 아니면(q->r로 가는 경로에서 거쳐가는 다른 노드가 있다면)
         outPath(P, q, P[q][r]);// 재귀함수를 통해 q-k로 가는 경로를 구해준다
         System.out.print(P[q][r] + " - ");// 콘솔에 경로 출력
         outPath(P, P[q][r], r); // 재귀함수를 통해 k-r로 가는 경로를 구해준다
      }
   }

   // 콘솔에 모든 정점에 대한 경로를 출력하는 함수.
   public static void printPath(int[][] P) throws IOException {
      // 이중 반복문을 돌면서 파일의 최단 경로를 출력한다
      for (int i = 0; i < 100; i++) {
         for (int j = 0; j < 100; j++) {
            System.out.print(i + " - "); // i출력
            outPath(P, i, j); // outPath 함수를 통해 i와 j사이의 경로를 출력
            System.out.print(j + "\n"); // j출력
         }
      }

   }

   public static void floyd(int n, int W[][], int D[][], int P[][]) { // Floyd 알고리즘의 기본이 되는 부분을 구현하는 함수
      int i, j, k; // 반복문을 돌기 위한 변수선언

      D = W; // 먼저, W 행렬을 D에 저장

      // 이중 반복문을 돌면서
      for (i = 0; i < n; i++) { 
         for (j = 0; j < n; j++) {
            P[i][j] = 0; // P의 원소의 값을 0으로 초기화

         }

      }

      // 이중 반복문을 돌면서 
      for (k = 0; k < n; k++) {
         for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
               if (D[i][k] + D[k][j] < D[i][j]) { // 만약 k 노드를 거쳤을 때, i->j로 직접 가는 경로보다 가중치가 작으면
                  P[i][j] = k; // P에 k를 저장하고
                  D[i][j] = D[i][k] + D[k][j]; // D[i][j]를 D[i][k]+D[k][j](k 노드를 거쳐 가는 경로)로 저장한다

               }

            }

         }
      }

   }

   public static void main(String[] args) throws IOException { // 메인 함수 정의
      
      System.out.println("----------------------------- FloydAlgorithm Start! -----------------------------");
      System.out.println();

      int Weight[][] = new int[100][100]; // 파일에서 값을 읽어들일 배열
      int Distance[][] = new int[100][100]; // 최단거리 계산결과를 저장할 배열
      int Path[][] = new int[100][100]; // 노드 번호를 가지고 있는 이차원 배열

      Scanner input = new Scanner(System.in); // 사용자가 입력하기 위한 Scanner 클래스 변수 선언
      System.out.print("Input of File Name Saved Graph(ex. denseG.txt, sparseG.txt) : ");
      // 사용자가 원하는 파일이름을 입력하라는 메세지
      String fname = input.next(); // 파일 이름일 입력받음
      FileInputStream fin = new FileInputStream(fname); // 파일 입력을 하기 위한 FileInputStream 클래스 변수 선언
      
      int v, u;// 두 정점 v,u를 저장해주는 변수 선언 
      int w; // 두 정점 v->u의 가중치를 저장해주는 변수 선언
      
      System.out.println();
      System.out.println(">> Content Of File");
      for (int i = 0; i < 100; i++) { // 이중 반복문을 돌면서
         for(int j = 0 ; j < 100 ; j ++){
         
         // v,u,w를 파일에서 읽어들인다   
         v = fin.read(); 
         u = fin.read(); 
         w = fin.read();

         Weight[v][u] = w; // 그리고 파일에서 읽어들인 w값을 배열 Weight에 저장한다

         System.out.printf("(%d,%d) : %d\n", v, u, w); // 파일에서 읽어들인 배열 출력
      }
   }

      floyd(100, Weight, Distance, Path); // floyd 함수 호출

      System.out.println();
      System.out.print("Input Name of File Saving Result(ex. Result.txt) : ");
      // 사용자에게 출력할 파일 이름을 입력받는다
      fname = input.next(); 
      printData(fname, Path); // PrintData를 통해 파일에 데이터를 입력

      System.out.println();
      System.out.println(">> Content Of File");

      printPath(Path); // PrintPath를 통해 콘솔에 데이터를 출력

      fin.close(); // fin 변수 close 시킴
      input.close(); // input 변수 close 시킴

   }

}