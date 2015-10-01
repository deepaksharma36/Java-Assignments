public class S5 {

  public int instanceV = 1;		

  public String toString()	{
	return "S5: " + instanceV;
  }
  public void both()    {
        instanceV = 200;
  }
  public static void main(String args[]) {
	System.out.println(new S5());
  }
}

