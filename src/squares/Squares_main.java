package squares;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Squares_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//double x[] = { 0.0, 0.2, 0.4, 0.6, 0.8, 1.0, 1.2 };
		//double y[] = { 1.0, 1.9, 3.2, 4.3, 4.8, 6.1, 7.2 };
		Squares_main smain = new Squares_main();
		double x[] = smain.getCSV1("csv/x.csv.csv",7);
		double y[] = smain.getCSV1("csv/y.csv.csv",7);
		double theta[] = new double[2];

		Squares_lib slib = new Squares_lib(x, y);
		double value[][] = new double[100][4];
		for(int i = 0; i < 100; i++){
			theta = slib.getDx();
			System.out.println(i+" : theta[0] = "+theta[0]);
			System.out.println(i+" : theta[1] = "+theta[1]);
			System.out.println(i+" : Objective function = "+slib.getObject());
		    value[i][0] = i;
			value[i][1] = slib.getObject();
		    value[i][2] = theta[0];
		    value[i][3] = theta[1];
		}
	
	Graph graph = new Graph(value);
	graph.setBounds(5,5,700,700);
	graph.setVisible(true);
	
	smain.writeCSV("csv/result.csv",value);


	}//main�֐��I��
		public double[] getCSV1(String path, int n) { //CSV����1�����f�[�^��荞��(n:�f�[�^��)
	        //CSV�����荞��
	        double csvdata[] = new double[n];
	        try {
	            File f = new File(path);
	            BufferedReader br = new BufferedReader(new FileReader(f));
	                 
	            String[] data = new String[n];
	            String line = br.readLine();
	            data = line.split(",", 0);
	            line = br.readLine();
	            br.close();
	            
	            // CSV����ǂݍ��񂾔z��̒��g����荞��
	            for(int i = 0; i < data.length; i++) {
	                csvdata[i] = Double.parseDouble(data[i]);
	            }

	        } catch (IOException e) {
	            System.out.println(e);
	        }
	        System.out.println("CSV��荞�� = "+Arrays.toString(csvdata));
	        return csvdata;
	        //CSV�����荞�݂����܂�
	    }
        
		public void writeCSV(String path, double[][] data) {
		try {
		FileWriter fw = new FileWriter(path, true); //true:�ǋL�Afalse:�㏑��
        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
        //���e���w�肷��
        pw.println("id,objective,theta0,theta1");
        pw.println();
        
        for(int i = 0; i < data.length; i++) {
        	for(int j=0;j < data[0].length;j++) {
        		pw.print(data[i][j]);
        		pw.print(".");
        	}
            //pw.print(data[i]);
            //pw.print(",");
            pw.println();
        }
        pw.println();
        //�t�@�C���ɏ����o��
        pw.close();

        //�I�����b�Z�[�W����ʂɏo�͂���
        System.out.println("�o�͊��� : "+path);
		}catch(IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
}
