import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class Man {
	public int timepass;
	public String doknow;
	static int count = 0;
	
	public Man() {
		count++;
	}
public	Man(String Name) {
		this.name=Name;
		count++;
	}
public	Man(String Name, int Age) {
	this.name=Name;
	this.age=Age;
	count++;
}

	private int age = 8;
	private String name = "";

	private int getAge() {
		return age;
	}

	private void setAge(int age) {
		this.age = age;
	}
	private void setAge(int age,String name) {
		this.age = age;
		this.name=name;
	}

	public static int nom() {
		return count;
	}
	
	public String str()
	{
		return ""+name+age;
	}
}

public class sample {

	public static void main(String[] args) {
		Man m = new Man();
		try {
			
			Class ManClass = Man.class;
			Class man = m.getClass();
			Constructor[] constructorsary = man.getConstructors();
			Constructor constructor = man.getConstructor(new Class[]{String.class});
			Constructor constructor2 = man.getConstructor(String.class ,  int.class);
			Man newm = (Man)constructor2.newInstance("Deepak",55);
			System.out.println(newm.str());
			Field[] allfields = man.getFields();
		
			for (Field aField:allfields)
				System.out.println(aField.getName());
			Field f = m.getClass().getField("doknow");
			Field ff = man.getDeclaredField("age");
			ff.setAccessible(true);
			//System.out.println(f.get(man));
			//f.set(man, 1000);
			Field fs = Man.class.getDeclaredField("count");
			Method mm = m.getClass().getDeclaredMethod("getAge");
			//Class[]  arg ;new Class[]{}
			Method mmm =
					m.getClass().getDeclaredMethod("setAge", new Class[]{int.class,String.class});
			//Method mmm = m.getClass().getDeclaredMethod("setAge", new class[]{Integer.class,String.class});
			Method mmmm = Man.class.getDeclaredMethod("nom");
			mm.setAccessible(true);
			mmm.setAccessible(true);
			f.setAccessible(true);
			fs.setAccessible(true);
			mmm.setAccessible(true);
			System.out.println(f.get(m));
			mmm.invoke(m, 99,"Deepak");
			System.out.println(mm.invoke(m));
			System.out.println(fs.get(null));
			System.out.println(mmmm.invoke(null));

			Constructor<Man> con = Man.class.getDeclaredConstructor();
			Man other = con.newInstance();

			Method om = other.getClass().getDeclaredMethod("setAge", int.class);
			om.setAccessible(true);
			om.invoke(other, 5);
			Field fo = other.getClass().getDeclaredField("age");
			fo.setAccessible(true);
			System.out.println(fo.get(other));

			Class aClass = (new Man()).getClass();
			System.out.println(aClass.getName()+"What is this");
			System.out.println(aClass.getSimpleName()+"What is this");
			System.out.println(aClass.getModifiers()+"  it is intiger na");
			System.out.println( Modifier.toString(  aClass.getModifiers())+"  it is actual");
			System.out.println(aClass.getSuperclass());
			System.out.println(aClass.getPackage());
			Class[] interfaces = aClass.getInterfaces();
			for (Class item : interfaces)
				System.out.println(item);
			Field[] fds = (new Man()).getClass().getFields();

			System.out.println(fds.length);
			for (Field item : fds)
				System.out.println(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
