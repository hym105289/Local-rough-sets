package comtool;

import java.util.Comparator;

/**
 * ���ܣ�ʹ��MyList<k,v>���԰���v�Ĵ�С����
 * �磺��2��8.4���루4��1.2�������Ϊ��4��1.2������2��8.4��
 * @author ������
 *
 */
public class MySort implements Comparator<MyList>{

	@Override
	public int compare(MyList o1, MyList o2) {
		// TODO Auto-generated method stub
		double flag=o1.v-o2.v;
		
		if(flag>0)
			return 1;
		else if(flag<0)
			return -1;
		else
			return 0;
	}	
}
