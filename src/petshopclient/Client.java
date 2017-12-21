package petshopclient;

import java.net.*;
import java.io.*;
import java.util.*;

import msocket.Server;

import petShop.PetShop;
import petshopclient.*;
public class Client{	
	static Socket s=null;
	static Map map=null;
	static ObjectInputStream inObjS = null ;	// �������
	static ObjectOutputStream outObjS = null;   //��������
	public Client()
	{
		this.map = new HashMap();
		try {
			this.s=new Socket("127.0.0.1",6666);
			this.outObjS = new ObjectOutputStream(this.s.getOutputStream()) ;
			this.inObjS = new ObjectInputStream(this.s.getInputStream()) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("���������������");
		ShowWindow sw=new ShowWindow("PetShop");
    	sw.setBounds(300,200,500,300);
	   	sw.setVisible(true);//����ͼ�ν���
	}
	public void quit()
	{
		map.put(1,"q");
		try {
			outObjS.writeObject(Client.map);
			outObjS.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	public static void update()
	{
		
		try{
			map.replace(1,"u");
			outObjS.writeObject(Client.map);	
			outObjS.flush();
		}catch(Exception e){						//�����쳣����ӡ�쳣
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void fresh()//fresh�ǲ���ǰ�ӷ������ϰ��µ���������
	{
		try{
				System.out.println("����f��������");
				Client.map.clear();
				Client.map.put(1, "f");
				Client.outObjS.writeObject(Client.map);
				Client.outObjS.flush();
				Client.map = (Map)Client.inObjS.readObject();		// ���շ������˷��͵�����
				System.out.println("f�յ���������Ϣ" );
				if(Client.map==null){
					System.out.println("map��");
				}else if((PetShop)Client.map.get(2)==null)
				{
					System.out.println("petshop��");
					System.out.println(map.get(1));//Ϊʲôֻ��һ��û�˰�
				}
				((PetShop) Client.map.get(2)).showall();
		}catch(Exception e){						//�����쳣����ӡ�쳣
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {	
		Client client=new Client();
	}
}
