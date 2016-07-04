package reducetool;

import java.util.ArrayList;
import java.util.Collections;

import comtool.EqualClassPara;
import comtool.ListTool;
import comtool.MyList;
import comtool.MySort;
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
		ListTool.ini(x, 5);
		ListTool.ini(u, 5);
		ListTool.ini(r, 3);
		String [][]a={{"1","2","2"},{"1","2","4"},{"2","3","4"},
				{"2","3","4"},{"1","3","4"}};
		EqualClassPara data=new EqualClassPara(r,x,u,a);
		System.out.println(nearR(data));
	}
	/**
	 * ����listX*listU�ڹ�ϵlistR�ϵ������ϵ����ʾһ����ά�ַ�������table��ʾ
	 * ���������ϵ��nearR
	 */
	public static ArrayList<ArrayList<Integer>> nearR(EqualClassPara data) {
		
		MySort mySort = new MySort();
		ArrayList<ArrayList<Integer>> nearR = new ArrayList<ArrayList<Integer>>();//��������ϵ
		ArrayList<Integer> nearRItemk;	//���ĳһ���������ϵ�����K��
		ArrayList<MyList> nearRItem;   //���ĳһ���������ϵ
		MyList myList;
		double distance = 0; //�����������ľ���
		int j=0;
		for(Integer xi:data.X){
			nearRItem= new ArrayList<MyList>();
			myList = new MyList(xi,0);
			nearRItem.add(myList);
			for(Integer xj:data.U){
				if(!xi.equals(xj)){
					distance=0;
					for(Integer ri:data.R){
						distance+=Math.pow(Double.parseDouble(data.matrix[xi][ri])-Double.parseDouble(data.matrix[xj][ri]),2);
					}
					distance=Math.sqrt(distance);
					myList = new MyList(xj,distance);
					nearRItem.add(myList);
				}	
			}
			
            Collections.sort(nearRItem, mySort);	                              
            nearRItemk=new ArrayList<Integer>();
            //System.out.println(nearRItem.size());
			for(int i=0;i<Config.k;i++){
				nearRItemk.add(nearRItem.get(i).k);
			}
			nearR.add(nearRItemk);
		}	
		return nearR;	
	}
	
}
