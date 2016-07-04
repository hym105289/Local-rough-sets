package reducetool;

import java.util.ArrayList;

import comtool.EqualClassPara;
import comtool.ListTool;
import config.Config;
/**
 * @data   2014-07-25
 * @author xinyan
 * @status ok
 */
public class NearR{
	public static void main(String[] args) {
		ArrayList r=new ArrayList<Integer>();
		ArrayList x=new ArrayList<Integer>();
		ArrayList u=new ArrayList<Integer>();
		ListTool.ini(x, 417);
		ListTool.ini(u, 4177);
		ListTool.ini(r, 8);
		Config.FileCDName="data\\"+10+".test";
		String[][][] matrix = ReadCDFile.fileToArrayEquality(Config.FileCDName);
		EqualClassPara data=new EqualClassPara(r,x,u,matrix[0]);
		
		ArrayList<ArrayList<Integer>> ni=nearR(data);
		for(ArrayList<Integer> e:ni){
			System.out.println(e);
		}
	}
	/**
	 * ����listX*listU�ڹ�ϵlistR�ϵ������ϵ����ʾһ����ά�ַ�������table��ʾ
	 * ���������ϵ��nearR
	 */
	public static ArrayList<ArrayList<Integer>> nearR(EqualClassPara data) {
		
		ArrayList<ArrayList<Integer>> nearR = new ArrayList<ArrayList<Integer>>();//��������ϵ
		ArrayList<Integer> nearRItem;	//���ĳһ���������ϵ
		double distance = 0; //�����������ľ���
		for(Integer xi:data.X){
			nearRItem= new ArrayList<Integer>();
			nearRItem.add(xi);
			for(Integer xj:data.U){
				if(!xi.equals(xj)){
					distance=0;
					for(Integer ri:data.R){
						//��������ƽ��
						distance+=Math.pow(Double.parseDouble(data.matrix[xi][ri])-Double.parseDouble(data.matrix[xj][ri]),2);
					}
					//System.out.println(Math.sqrt(distance));
					if(Math.sqrt(distance)<=Config.distanceD){
						nearRItem.add(xj);
					}
				}
			}
			nearR.add(nearRItem);
		}	
		return nearR;	
	}
	
}
