package petShop;
import java.io.*;
import java.util.ArrayList;
public class PetShop implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PetShop petshop;
	
	ArrayList<Pet> pets =null;				// ����������
	int allNumber = 0;
	int len=0;
	public PetShop(int len) {				// ���췽�����ٳ�������Ĵ�С
		pets= new ArrayList<Pet>();
		if(len>0) this.len=len;
	}
	public PetShop getPetShop(int len){
		if(petshop==null){
			petshop=new PetShop(len);
			
		}
		return petshop;
	}
	private void Reset(ArrayList<Pet> pets,int allNumber,int len)
	{
		this.len=len;this.allNumber=allNumber;
		for(int i=0;i<pets.size();i++)
		{
			this.pets.set(i, pets.get(i));
		}
	}
	public boolean add(Pet pet,boolean numHaveAllo) {			// ���ӳ���
		if(numHaveAllo==true)
		{
			this.add(pet);
			return true;
		}
		if (this.pets.size() < this.len-1) {		// �жϳ����̵���ĳ����Ƿ��Ѿ�����
			pet.setNumber(++allNumber);
			this.pets.add(pet);		// ���ӳ���
			return true;			// ���ӳɹ�
		} else {					
			return false;			// ����ʧ��
		}
	}
	public boolean add(Pet pet) {			// ���ӳ���
		if (this.pets.size() < this.len-1) {		// �жϳ����̵���ĳ����Ƿ��Ѿ�����
			pet.setNumber(++allNumber);
			this.pets.add(pet);		// ���ӳ���
			return true;			// ���ӳɹ�
		} else {					
			return false;			// ����ʧ��
		}
	}
	
	public boolean delete(Pet pet){           //ɾ������
		if (this.pets.size()>1) {		// �жϳ����̵����Ƿ��г���
			for(int i=0; i<this.pets.size(); ++i ){
				if(pets.get(i)==pet){
					pets.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	
	public Pet get(int number){					//Get����
		for(int i=0;i<this.pets.size();i++){
			if(this.pets.get(i)!=null&&this.pets.get(i).getNumber()==number){
				return this.pets.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Pet> search(String keyWord){ 	// �ؼ��ֲ���
		ArrayList<Pet> P=new ArrayList<Pet>();			// �����Ѿ�ȷ���ļ�¼�������ٶ�������
		for (int i = 0; i < this.pets.size(); i++) {
			if (this.pets.get(i) != null) {
				if (this.pets.get(i).getName().indexOf(keyWord) != -1
					|| this.pets.get(i).getColor().indexOf(keyWord) != -1) {
					P.add(this.pets.get(i)); // �����ϲ�ѯ�����ĳ�����Ϣ����
				}
			}
		}
		return P;
	}
	
	public boolean modify(Pet pet,String name,String color,int age){           //�޸ĳ���
		pet.setAge(age);
		pet.setName(name);
		pet.setColor(color);
		return true;
	}
	
	public boolean showall()
	{
		System.out.println("ShowAll:");
		for(int i=0;i<this.pets.size();i++)
		{
			if(this.pets.get(i)!=null)
				System.out.println(this.pets.get(i).getNumber()+this.pets.get(i).getName()+this.pets.get(i).getColor()+this.pets.get(i).getAge());
		}
		return true;
	}
	public void serveToFiles() throws Exception {
			
		File f = new File("d:" + File.separator + "PetInShop"+File.separator+"data.dat") ;	// ���屣��·��
		if(f.exists())
		{
			f.delete();
			f.createNewFile();
		}
		ObjectOutputStream oos = null ;	// �������������
		OutputStream out = new FileOutputStream(f) ;	// �ļ������
		oos = new ObjectOutputStream(out) ;
		oos.writeObject(this) ;	// �������
		oos.close() ;	// �ر�
	}
	public PetShop dserveFromFiles() throws Exception {
		File f=new File("d:"+File.separator+"PetInShop"+File.separator+"data.dat");
		if(f.exists()){
		InputStream input = new ObjectInputStream(new FileInputStream(f)) ;	// �ļ�������
		PetShop temp = (PetShop) ((ObjectInputStream) input).readObject() ;	// ��ȡ����
		input.close() ;	// �ر�
		return temp;
		}
		else{
			f.createNewFile();
			return this;
		}
	}
	
	public void setallNumber(int allNumber)
	{
		this.allNumber=allNumber;
	}
	public int getallNumber()
	{
		return this.allNumber;
	}

};
