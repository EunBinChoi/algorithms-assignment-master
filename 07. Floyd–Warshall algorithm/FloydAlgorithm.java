package algorithm_07;

import java.io.*;
import java.util.Scanner;

public class FloydAlgorithm { // Floyd �˰����� �����ϱ� ���� Ŭ���� ����

   /*
    * q���� r�� ���� ��θ� ���Ϸ� ����ϴ� �Լ�. �ǻ� �ڵ带 ������ ����. printData �Լ����� �ʿ��� ����� �����ϱ� ����
    * ���� �Լ��̹Ƿ� �ٷ� ȣ������ �� ��.
    */
   public static void outFilePath(FileOutputStream out, int[][] P, int q, int r) throws IOException {

      if (P[q][r] != 0) { // ���� P[q][r]�� 0�� �ƴϸ�(q->r�� ���� ��ο��� ���İ��� �ٸ� ��尡 �ִٸ�)
         outFilePath(out, P, q, P[q][r]); // ����Լ��� ���� q-k�� ���� ��θ� �����ش�
         out.write((char) P[q][r]); // ���Ͽ� ��� ���
         outFilePath(out, P, P[q][r], r); // ����Լ��� ���� k-r�� ���� ��θ� �����ش�
      }
   }// ���� 1->2�� ���� �ִ� �Ÿ��� 1->3->4->2���, P[1][2]���� 4�� ����Ǿ��ְ�, P[1][4]���� 3��
      // ����Ǿ��ֱ� ������,
      // ��� �Լ��� ���� 3�� 4�� ����Ѵ�. �׸��� P[4][2]�� ���� 0�̱� ������ �ƹ��͵� ������� �ʾ� 1->3->4->2
      // �ִܰϷθ� ����� �� �ִ� ���̴�

   // ���Ϸ� ��� ������ ���� ��θ� ����ϴ� �Լ�.
   public static void printData(String fileName, int[][] P) throws IOException {

      FileOutputStream out = new FileOutputStream(fileName);

      // ���� �ݺ����� ���鼭 ������ �ִ� ��θ� ����Ѵ�
      for (int i = 0; i < 100; i++) {
         for (int j = 0; j < 100; j++) {
            // i->j�� ���� �ִܰ��
            out.write((char) i); // i���
            outFilePath(out, P, i, j); // outFileWeight �Լ��� ���� i�� j������ ��θ� ���
            out.write((char) j); // j���
         }
      }

      out.close(); // out ������ close ��Ŵ
   }

   // ��θ� ���� �ֿܼ� ����ϱ� ���� �Լ�
   // ���� �Լ�ó��  printPath �Լ����� �ʿ��� ����� �����ϱ� ���� ���� �Լ�
   public static void outPath(int[][] P, int q, int r) throws IOException { 

      if (P[q][r] != 0) { // ���� P[q][r]�� 0�� �ƴϸ�(q->r�� ���� ��ο��� ���İ��� �ٸ� ��尡 �ִٸ�)
         outPath(P, q, P[q][r]);// ����Լ��� ���� q-k�� ���� ��θ� �����ش�
         System.out.print(P[q][r] + " - ");// �ֿܼ� ��� ���
         outPath(P, P[q][r], r); // ����Լ��� ���� k-r�� ���� ��θ� �����ش�
      }
   }

   // �ֿܼ� ��� ������ ���� ��θ� ����ϴ� �Լ�.
   public static void printPath(int[][] P) throws IOException {
      // ���� �ݺ����� ���鼭 ������ �ִ� ��θ� ����Ѵ�
      for (int i = 0; i < 100; i++) {
         for (int j = 0; j < 100; j++) {
            System.out.print(i + " - "); // i���
            outPath(P, i, j); // outPath �Լ��� ���� i�� j������ ��θ� ���
            System.out.print(j + "\n"); // j���
         }
      }

   }

   public static void floyd(int n, int W[][], int D[][], int P[][]) { // Floyd �˰����� �⺻�� �Ǵ� �κ��� �����ϴ� �Լ�
      int i, j, k; // �ݺ����� ���� ���� ��������

      D = W; // ����, W ����� D�� ����

      // ���� �ݺ����� ���鼭
      for (i = 0; i < n; i++) { 
         for (j = 0; j < n; j++) {
            P[i][j] = 0; // P�� ������ ���� 0���� �ʱ�ȭ

         }

      }

      // ���� �ݺ����� ���鼭 
      for (k = 0; k < n; k++) {
         for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
               if (D[i][k] + D[k][j] < D[i][j]) { // ���� k ��带 ������ ��, i->j�� ���� ���� ��κ��� ����ġ�� ������
                  P[i][j] = k; // P�� k�� �����ϰ�
                  D[i][j] = D[i][k] + D[k][j]; // D[i][j]�� D[i][k]+D[k][j](k ��带 ���� ���� ���)�� �����Ѵ�

               }

            }

         }
      }

   }

   public static void main(String[] args) throws IOException { // ���� �Լ� ����
      
      System.out.println("----------------------------- FloydAlgorithm Start! -----------------------------");
      System.out.println();

      int Weight[][] = new int[100][100]; // ���Ͽ��� ���� �о���� �迭
      int Distance[][] = new int[100][100]; // �ִܰŸ� ������� ������ �迭
      int Path[][] = new int[100][100]; // ��� ��ȣ�� ������ �ִ� ������ �迭

      Scanner input = new Scanner(System.in); // ����ڰ� �Է��ϱ� ���� Scanner Ŭ���� ���� ����
      System.out.print("Input of File Name Saved Graph(ex. denseG.txt, sparseG.txt) : ");
      // ����ڰ� ���ϴ� �����̸��� �Է��϶�� �޼���
      String fname = input.next(); // ���� �̸��� �Է¹���
      FileInputStream fin = new FileInputStream(fname); // ���� �Է��� �ϱ� ���� FileInputStream Ŭ���� ���� ����
      
      int v, u;// �� ���� v,u�� �������ִ� ���� ���� 
      int w; // �� ���� v->u�� ����ġ�� �������ִ� ���� ����
      
      System.out.println();
      System.out.println(">> Content Of File");
      for (int i = 0; i < 100; i++) { // ���� �ݺ����� ���鼭
         for(int j = 0 ; j < 100 ; j ++){
         
         // v,u,w�� ���Ͽ��� �о���δ�   
         v = fin.read(); 
         u = fin.read(); 
         w = fin.read();

         Weight[v][u] = w; // �׸��� ���Ͽ��� �о���� w���� �迭 Weight�� �����Ѵ�

         System.out.printf("(%d,%d) : %d\n", v, u, w); // ���Ͽ��� �о���� �迭 ���
      }
   }

      floyd(100, Weight, Distance, Path); // floyd �Լ� ȣ��

      System.out.println();
      System.out.print("Input Name of File Saving Result(ex. Result.txt) : ");
      // ����ڿ��� ����� ���� �̸��� �Է¹޴´�
      fname = input.next(); 
      printData(fname, Path); // PrintData�� ���� ���Ͽ� �����͸� �Է�

      System.out.println();
      System.out.println(">> Content Of File");

      printPath(Path); // PrintPath�� ���� �ֿܼ� �����͸� ���

      fin.close(); // fin ���� close ��Ŵ
      input.close(); // input ���� close ��Ŵ

   }

}