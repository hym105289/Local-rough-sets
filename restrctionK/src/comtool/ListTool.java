package comtool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//״̬��ok
public class ListTool {
	//��ʼ��list
	public static void ini(List<Integer> list,int n){
		for(int i=0;i<n;i++){
			list.add(i);
		}
	}
	
	public static void iniByArray(List<Integer> list,int[] str){
		int n=str.length;
		for(int i=0;i<n;i++){
			list.add(str[i]);
		}
	}
	
	//������ͨ�б�
	public static void visitList(ArrayList<Integer> list){
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	
	//�����б���б�
	public static void visitListAll(ArrayList<ArrayList<Integer>> list){
	
		for(int i=0;i<list.size();i++){
			for(int j=0;j<list.get(i).size();j++){
				System.out.print(list.get(i).get(j));
			}
			System.out.println();
		}
	}
	
	//listת�ַ���
	public static String list2String(ArrayList<Integer> list){
		String str=null;
		for(int i=0;i<list.size();i++){
			str+=list.get(i).toString();
		}
		return str;
	}
	
	public static String[] list2StrArray(ArrayList<ArrayList<Integer>> list){
		String []str=new String[list.size()];
		int i=0,j=0;
		for(i=0;i<list.size();i++){
			str[i]="";
		}
		for(i=0;i<list.size();i++){
			for(j=0;j<list.get(i).size();j++){
				str[i]+=list.get(i).get(j).toString();
			}
		}
		return str;
	}
	
	//list�б�ȥ��
	public static ArrayList<Integer> distict(ArrayList<Integer> list){
		Set set = new HashSet(); 
		ArrayList<Integer> newList = new ArrayList(); 
        for (Iterator iter = list.iterator(); iter.hasNext();) { 
            Object element = iter.next(); 
            if (set.add(element)) 
                newList.add((Integer) element); 
        } 
        return newList; 
	}
	//list�б�ȥ����{{1,2,3}��{1,2,3}��{4,5}}��Ϊ{{1,2,3}��{4,5}}
	public static ArrayList<ArrayList<Integer>> distict2(ArrayList<ArrayList<Integer>> list){
		for (ArrayList<Integer> e:list) { 
			Collections.sort(e);
		}
		ArrayList<ArrayList<Integer>> newList =new ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> e:list) { 
			if(!newList.contains(e))
				newList.add(e);
		}
        return newList; 
	}
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> a= new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> b =new ArrayList<Integer>();
		b.add(1);b.add(2);b.add(3);b.add(4);
		a.add(b);
		b =new ArrayList<Integer>();
		b.add(1);b.add(2);b.add(3);a.add(b);
		b =new ArrayList<Integer>();
		b.add(5);b.add(4);a.add(b);
		System.out.println(equalityClassU(a));
		
	}
	//list��Ϊһ��ArrayList��ȥ��
	//��{{1,2,3��4}��{1,2,3}��{4,5}}��Ϊ{1,2,3,4,5}
	public static ArrayList<Integer> equalityClassU(ArrayList<ArrayList<Integer>> list){
		ArrayList<Integer> listU = new ArrayList<Integer>();
		for(ArrayList<Integer> e: list){
			listU.addAll(e);
		}
		HashSet h = new HashSet(listU);
	    listU.clear();
	    listU.addAll(h);
		return listU;
	    
	}
	
}
