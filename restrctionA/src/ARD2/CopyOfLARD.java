package ARD2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import reducetool.EquaClass;
import reducetool.NearR;
import reducetool.LD;
import reducetool.LMaxSig;
import reducetool.ReadCDFile;
import comtool.EqualClassPara;
import comtool.ListTool;
import config.Config;

public class CopyOfLARD {
	public static void main(String []argu) {
		System.out.println("startL...");
		Config.FileCDName="data\\10.test";
		float [] d={1,0.9f,0.7f,0.5f};
		for(int j=1;j<4;j++){
			Config.degree=d[j];
			System.out.print("------------");
			System.out.println(d[j]);
			for(int i=1;i<11;i++){
				lard(i);
			}
		}
		
	}
		
	public static void lard(int b){
		int a=b;
		//class1Ϊ����������ÿ��ĸ���
		int []class1={12332,6688};
		
		int strSize=0;
		for(int ii=0;ii<class1.length;ii++){
			strSize+=class1[ii]/(10/a);
		}
		Config.u=strSize;
		int [] str=new int[strSize];
		int count=0;
		for(int x1=0;x1<class1.length;x1++){
			int x2start=0;
			for(int i=0;i<x1;i++){
				x2start+=class1[i];
			}
			int y=class1[x1]/(10/a);
			y=y+x2start;
			for(int x2=x2start;x2<y;x2++){
				str[count++]=x2;
			}
		}
		//
		String[][][] matrix = ReadCDFile.fileToArrayEquality(Config.FileCDName);
		ArrayList<Integer> listR =new ArrayList<Integer> ();
		ListTool.ini(listR, Config.conAttrNum);
		ArrayList<Integer> listU =new ArrayList<Integer> ();
		ListTool.ini(listU, Config.objNum);
		ArrayList<Integer> listD =new ArrayList<Integer> ();
		ArrayList<Integer> partU = new ArrayList<Integer>();
		//ListTool.ini(partU, Config.u);
		ListTool.iniByArray(partU, str);
		ListTool.ini(listD, Config.desAttrNum);
		//��ʱ��ʼ
		long startTime=0,stopTime=0;
		ArrayList<ArrayList<Integer>> equD = new ArrayList<ArrayList<Integer>>();
	    equD=ListTool.distict2(EquaClass.equalClass(new EqualClassPara(listD,partU,partU,matrix[1])));
		startTime=System.nanoTime();
		ArrayList<Integer> red = new ArrayList<>();
		red=LMaxSig.maxInSigD(new EqualClassPara(listR,listD,listU,matrix[0]),equD);
		//4��ʼ��Pi,Ui,Ui/D
		ArrayList<ArrayList<Integer>> Pi = new ArrayList<ArrayList<Integer>>();
		Pi.add(red);
		ArrayList<Integer> Ui=new ArrayList<Integer>();  //Ui����
	    Ui.addAll(listU);
	    ArrayList<ArrayList<Integer>> Xi = new ArrayList<ArrayList<Integer>>();
	    //XiΪpartU��D�ȼ����һ������
	    Xi=ListTool.distict2(EquaClass.equalClass(new EqualClassPara(listD,partU,partU,matrix[1])));
	    //5��ʼ���жϺ�벿��
    	ArrayList<ArrayList<Integer>> CLXUiredArray=new ArrayList<ArrayList<Integer>>();
    	ArrayList<Integer> equalClassXiUired=new ArrayList<Integer>();
    	int stopNew =LD.DCD(new EqualClassPara(red,Ui,matrix[0]),equD).size();
 	    int stop=LD.DCD(new EqualClassPara(listR,Ui,matrix[0]),equD).size();
	    while(stopNew<stop){
	    	equalClassXiUired.clear();
	    	//����U����partU��صĶ��󼯺�
	    	for(ArrayList<Integer> Xj: Xi){
	    		equalClassXiUired.addAll(ListTool.equalityClassU(
	    				NearR.nearR(new EqualClassPara(red,Xj,Ui,matrix[0]))));
	    	}
	    	equalClassXiUired=ListTool.distict(equalClassXiUired);
	    	Ui.clear();Ui.addAll(equalClassXiUired);
	    	//5.4
	    	red=LMaxSig.maxOutSigD(new EqualClassPara(red,listD,Ui,matrix[0]),listR,equD);
	    	//5.5
	    	Pi.add(red);
	    	stopNew =LD.DCD(new EqualClassPara(red,Ui,matrix[0]),equD).size();
	    }
	    stopTime=System.nanoTime();
		Collections.sort(red);
		System.out.print(red);
		System.out.print(new DecimalFormat("#.0000").format((double)(stopTime-startTime)/1000000000)+" ");
		System.out.println("\tԼ�������"+red.size());
	}
	
	//���㵥����
	public static void monotonity(ArrayList<ArrayList<Integer>> Pi,EqualClassPara data,
			ArrayList<ArrayList<Integer>> equaD){
	    double[] d =new double [Pi.size()];
	    int i=0;
	    EqualClassPara data1;
	    for(ArrayList<Integer> e:Pi){
			data1=new EqualClassPara(e,data.U, data.U, data.matrix);
	    	d[i++]=(double)LD.DCD(data1,equaD).size()/Config.u;
	    }
	    for(double dd:d){
	    	System.out.println(new DecimalFormat("#.0000").format(dd)+" ");
	    }
	    System.out.println("�����Խ���");
	}
	
}
