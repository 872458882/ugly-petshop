package msocket;

import java.io.*;
import java.net.*;
import java.util.*;

import petShop.PetShop;
public class Server{
	public static ArrayList<MyThread> users=new ArrayList<MyThread>();			//����һ�����ϣ�װ���е��û�
	public static Map<Integer, Serializable> map;
	public static void main(String[] args) throws Exception {
		Server.map = new HashMap();
		Server.map.put(1,"f");
		Server.map.put(2,new PetShop(100));
		Server.map.replace(2,((PetShop) Server.map.get(2)).dserveFromFiles());
		System.out.println("showall:");
		((PetShop) Server.map.get(2)).showall();
		ServerSocket server=null; 						//��������
		try{
			System.out.println("����������");
			server=new ServerSocket(6666); 			//�󶨷�������6666��
			while(true){
				System.out.println("�������ȴ�����");
				//��ѭ������������������
				//accept�����������ڴ˴����ӵ��ͻ������ʱ��Ż����ִ��
				//����һ�����ͻ��˵�����Socket
				Socket s=server.accept();
				MyThread mt=new MyThread(s); 		//�����̶߳���
				mt.start();								//�����߳�
				users.add(mt); 						//�����̼߳����û��б�
				//�������˼���ѭ�����ܿͻ�����
			}
		}catch(Exception e){ 							//�����쳣����ӡ�쳣��Ϣ
			e.printStackTrace(); 
		}
	}
}