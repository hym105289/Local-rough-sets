package comtool;
//״̬��ok
public class StringTool {
	
	//����һά����
	public static void visitArray(String[][][]str){
		for(int i=0;i<str.length;i++){
			visitArray(str[i]);
		}
	}
	
	//������ά����
	public static void visitArray(String[][]str){
		for(int i=0;i<str.length;i++){
			for(int j=0;j<str[i].length;j++){
				System.out.print(str[i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * strD�Ƿ����strC�е�ÿ���ַ�����strD="1234" strC="124"����true
	 * @param strC
	 * @param strD
	 */
	public static boolean contains(String strC,String strD){
		boolean flag=true;
		for(int i=0;i<strC.length();i++){
			if(!strD.contains(Character.toString(strC.charAt(i)))){
				flag=false;
				break;
			}
		}
		return flag;	
	}
	/**
	 * ����strC��strD����ͬ�ַ�����
	 * @param strC
	 * @param strD
	 * @return
	 */
	public static int containsNum(String strC,String strD){
		int count=0;
		for(int i=0;i<strC.length();i++){
			if(strD.contains(Character.toString(strC.charAt(i)))){
				count++;
			}
		}
		return count;	
	}

	//���ַ���ȥ���磺"12312"-->"123"
	public static String distict(String s){
		if (s == null)  
            return s;  
		boolean flag=true;
        StringBuffer sb = new StringBuffer();  
        int i = 0, j=0,len = s.length();  
        sb.append(s.charAt(0));
        for(i=1;i<len;i++){
        	for(j=0;j<i;j++){
        		if(s.charAt(j)==s.charAt(i)){
        			flag=false;
        			break;
        		}
        	}
        	if(flag)
        		sb.append(s.charAt(i));
        }
        return sb.toString();  
	}

}
