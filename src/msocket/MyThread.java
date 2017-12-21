package msocket;
import java.io.*;
import java.net.*;
import java.util.*;

import petShop.*;
class MyThread extends Thread {							//�Զ�����߳���
	private Socket s;
	private InetAddress ip;
	ObjectInputStream buf = null ;	// ����������
	ObjectOutputStream out = null ;		//���������
	public MyThread(Socket s){
		this.s=s;
	}
	public void run(){
		
		BufferedReader in = null ;	    // ����������
		try{
			//�������������
			ip=s.getInetAddress();						//��ȡ�ͻ���IP��ַ
			System.out.println("�ͻ������� ip��"+ip);
			out = new ObjectOutputStream(s.getOutputStream()) ;
			buf = new ObjectInputStream(s.getInputStream()) ;
			boolean flag=true;
			while(flag)
			{  								//���������ͻ���̸
				Map tempmap = (Map)this.buf.readObject();		// ���շ������˷��͵�����
				System.out.println("�������յ�"+ip+"���͵�����");
				String strIn=(String) tempmap.get(1);
				if(strIn.equals("u"))
				{
					Server.map=tempmap;
					System.out.println(ip+"update����");
					System.out.println("�������˱�"+ip+"ˢ��һ��");
				}
				else if(strIn.equals("f"))
				{
					Server.map.replace(1, "o");
					System.out.println(ip+"fresh����");
					out.writeObject(Server.map);
					out.flush();
					System.out.println("�ͻ���"+ip+"ˢ��һ��");
				}
				else if(strIn.equals("q"))
				{
					System.out.println("ip"+"quit����");
					flag=false;
					Server.users.remove(this);
				}
				((PetShop) Server.map.get(2)).serveToFiles();
			}
		}catch(Exception e){
		}
	}

	//�����Ƕ�username��ip���Եĸ�ֵ/ȡֵ����
	public InetAddress getIp() {
		return ip;
	}
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
}