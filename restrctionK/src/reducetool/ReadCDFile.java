package reducetool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import config.Config;
/**
 * @status		ok
 * @verify		2014-07-15
 * @author 		xinyan
 */
public class ReadCDFile {

	public  static  String[][][] fileToArrayEquality(String fileName ){
		String [][][]matrix=null; 
		File file = new File(fileName);
		BufferedReader buf=null;
		FileReader frin=null;
		try {
			frin = new FileReader(file);
			buf = new BufferedReader(frin);
			String str = null;       //���ļ�ʱ������ÿһ�е��м�洢����
			int i=0;
			//������������������Ը�����Ϣ�洢��strConfig[]������
			str=buf.readLine();
			String[] strConfig =new String[3];
			strConfig=str.split(",");
			for(i=0 ;i<strConfig.length;i++){
				strConfig[i]=strConfig[i].substring(strConfig[i].indexOf("=")+1);
			}
			Config.objNum=Integer.parseInt(strConfig[0]);
			Config.conAttrNum=Integer.parseInt(strConfig[1]);
			Config.desAttrNum=Integer.parseInt(strConfig[2]);
			//���������ԡ��������Ա��浽3ά����
			//matrix[0]�洢�������ԣ�matrix[1]�洢��������
			matrix =new String[2][][];
			matrix[0]=new String[Config.objNum][Config.conAttrNum+1];
			matrix[1]=new String[Config.objNum][Config.desAttrNum+1];
			String strArrayCol[] = new String[Config.conAttrNum+Config.conAttrNum];
			i=0;
			int j=0;
			while((str=buf.readLine())!=null){
				strArrayCol =str.split(Config.splitStr);
				for(j=0;j<Config.conAttrNum;j++){
					matrix[0][i][j]=strArrayCol[j];
				}
				matrix[0][i][j]=Config.tag;
				for(j=0;j<Config.desAttrNum;j++){
					matrix[1][i][j]=strArrayCol[j+Config.conAttrNum];
				}
				matrix[1][i][j]=Config.tag;
				i++;	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				buf.close();
				frin.close();
			} catch (IOException e) {
			}
		}
		return matrix;
	}
	
}
