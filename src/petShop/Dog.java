package petShop;

import java.io.Serializable;

public class Dog implements Pet, Serializable {
	private String name;				// ��������
	private String color;				// ������ɫ
	private int age;				// ��������
	private int number;
	public Dog(String name, String color, int age) {		// ͨ��������������
		this.setName(name) ;
		this.setColor(color) ;
		this.setAge(age) ;
	}
	public String getName(){return name;}			// �õ����������
	public String getColor(){return color;}			// �õ��������ɫ
	public int getAge(){return age;}	
	public boolean setName(String name){ this.name=name; return true;}			// �޸ĳ��������
	public boolean setColor(String color){ this.color=color; return true;}			// �޸ĳ������ɫ
	public boolean setAge(int age){this.age=age;return true;}	
	public int getNumber() {return number;}
	public boolean setNumber(int number) {this.number = number;return true;}	
	
};
