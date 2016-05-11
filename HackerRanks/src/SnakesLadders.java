import java.net.InetAddress;
import java.net.UnknownHostException;




public class SnakesLadders {

	public static void main(String[] args) {
      
      try {
		System.out.println(InetAddress.getLocalHost().getHostAddress());
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
    
}
