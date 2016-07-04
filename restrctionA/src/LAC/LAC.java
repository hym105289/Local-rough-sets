package LAC;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import reducetool.GD;
import reducetool.LD;
import reducetool.ReadCDFile;
import comtool.EqualClassPara;
import comtool.ListTool;
import config.Config;

/*
 * �ֱ��þֲ��ֲڼ���ȫ�ֲִڼ�
 * ��һ��Ŀ��������
 * ����LLAC��GLAC
 *״̬��ok
 */
public class LAC {
	
	public static void main(String []argu) {
		//ʵ��1
		Config.u=1902;
		//Config.distanceD=0.1;
		float [] d={1,0.9f,0.7f,0.5f};  
		
		System.out.println("ȫ��");
		for(int j=0;j<4;j++){
			Config.degree=d[j];
			System.out.print("------------");
			System.out.println(d[j]);
			for(int i=1;i<11;i++){
				Config.FileCDName="data\\"+i+".test";
				GLAC();
			} 
		}
		System.out.println("�ֲ�...");
		for(int j=0;j<4;j++){
			Config.degree=d[j];
			System.out.print("------------");
			System.out.println(d[j]);
			for(int i=1;i<11;i++){
				Config.FileCDName="data\\"+i+".test";
				LLAC();
			} 
		}
		
	}
	public static ArrayList<Integer> LLAC(){
		ArrayList<Integer> pos=new ArrayList<Integer>();
		String[][][] matrix = ReadCDFile.fileToArrayEquality(Config.FileCDName);
	    ArrayList<Integer> listR =new ArrayList<Integer> ();
		ListTool.ini(listR, Config.conAttrNum);
		ArrayList<Integer> listU =new ArrayList<Integer> ();
		ListTool.ini(listU, Config.objNum);
		ArrayList<Integer> listX =new ArrayList<Integer> ();
		ListTool.ini(listX, Config.u);
		long startTime=0,stopTime=0;
		startTime=System.nanoTime();
		EqualClassPara data=new EqualClassPara(listR,listX, listU, matrix[0]);
		pos=LD.DX(data,Config.degree);
		stopTime=System.nanoTime();
		System.out.println(new DecimalFormat("#.0000").format((double)(stopTime-startTime)/1000000000)+" ");
		Collections.sort(pos);
		return pos;
	}
	
	public static ArrayList<Integer> GLAC(){
		ArrayList<Integer> pos=new ArrayList<Integer>();
		String[][][] matrix = ReadCDFile.fileToArrayEquality(Config.FileCDName);
		ArrayList<Integer> listR =new ArrayList<Integer> ();
		ListTool.ini(listR, Config.conAttrNum);
		ArrayList<Integer> listU =new ArrayList<Integer> ();
		ListTool.ini(listU, Config.objNum);
		ArrayList<Integer> listX =new ArrayList<Integer> ();
		ListTool.ini(listX, Config.u);
		//ע��EqualityClass.equalityClass�ڶ�������ΪlistUʱ�������U�ϵĵȼ���
		//��listXʱ��X�ϵĵȼ���
		long startTime=0,stopTime=0;
		startTime=System.nanoTime();
		EqualClassPara data=new EqualClassPara(listR, listX, listU, matrix[0]);
		pos=GD.DX(data,Config.degree);
		stopTime=System.nanoTime();
		System.out.println(new DecimalFormat("#.0000").format((double)(stopTime-startTime)/1000000000)+" ");
		return pos;
	}
}
