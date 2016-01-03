import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class BitOperetor {
	private String binaryForWriting = "";
	private int codeLength = 9;
	private int multiple = codeLength * 8;
	private int mapCounter=0;
	public static BufferedInputStream in;
	public int readingkeysCount=0;
	//MyHashMap map;
	String[] readingMap;
	
	HashMap<String,Integer> map ;
	BufferedOutputStream out;

	public BitOperetor() {

	}
    //compression
	public BitOperetor(FileOutputStream fileName) {
		out = new BufferedOutputStream(fileName);
		map= new HashMap();
		 //map= new HashMap<String,Integer>();
		for(int counter=0;counter<256;counter++)
		{
			map.put(""+(char)counter,counter );
		}
	}
    
   //decompression	
	public BitOperetor(FileInputStream fileName) {
		in=new BufferedInputStream(fileName);
		readingMap= new String[2048];
	for(int charater=0;charater<256;charater++)
		readingMap[readingkeysCount++]=""+(char)charater;
	readingkeysCount=256;	
	}
   public void write(String str,int start,int end)
   {
	   
   }
   
   public String read() {
       String nextLine = "";
		int character=0;
		int readingCounter = 0;
		int nextCode=0;
		 String Line="";
	       String val;
        String byteString=compressFileReader();
        /*if(byteString.equals(""))
        	return null;
        */
	   try{
       while (readingCounter + codeLength < byteString.length()) {
    	   character = Integer.parseInt(byteString.substring(readingCounter,readingCounter + codeLength), 2);
	       val = readingMap[character];
	       
			//System.out.println(byteString.substring(readingCounter,readingCounter + 9));
			       Line += val;
		          if(readingCounter + 2*codeLength<byteString.length()) 
		          {
			       nextCode = Integer.parseInt(byteString.substring(readingCounter+codeLength,readingCounter + 2*codeLength), 2); 
		           String s = readingMap[nextCode];
		           if (readingkeysCount == nextCode)
		               s = val + val.charAt(0);
		           if (readingkeysCount < 255 && val !="" && val !="\n")
		        	   readingMap[readingkeysCount++] = val + s.charAt(0);
		           val = s;
		       
		          }
		
			readingCounter += codeLength;
		}
       
       System.out.println(Line);
       return Line+'\n';
	   }
	   catch(Exception e)
	   {
		   System.out.println(map.get((""+(char)65)));//+" "+map.get(character));
		   
		   return "";
	   }
   }
	
	public void write(String line) {
		    String empty="";
	        if ( line.equals("")||line == null)
	            {return;}
	        else{
	        String buffer =  empty+line.charAt(0);
	        String key = empty;
	        for (char nextItem : line.substring(1).toCharArray()) {
	            key = buffer + nextItem;
	            if (map.get(key)!=null)
	                buffer = key;
	            else {
	            	compressFileWriter((int)map.get(buffer));
	            	
	            	//compressFileWriter(map.get(buffer).getValue());
	                if(mapCounter<1){    
	            	map.put(key, 256 + mapCounter++);}
	                buffer = empty+nextItem;
	            }
	        }
	        compressFileWriter(map.get(buffer));
	        closeWriting(false);
	        multiple=8*codeLength;
	    }}
	
	public void closeInput()
	{
		try {
			in.close();
			//in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close()
	{
		System.out.println(mapCounter);
		try {
			out.close();
			//in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void compressFileWriter(int CompressCode) {
		String temp = "";
		// System.out.println(Integer.toString(CompressCode,2));

		if (CompressCode <= 255)
		{	temp = padding(Integer.toString(CompressCode, 2), codeLength
					- Integer.toString(CompressCode, 2).length(), false);}
		else
		{temp= padding(Integer.toString(CompressCode,2),codeLength-Integer.toString(CompressCode,2).length(),false);}
		// System.out.println(temp+"Temp");
		binaryForWriting = binaryForWriting + temp;
		
		
		if (binaryForWriting.length() == multiple) {
			bitWriter(binaryForWriting);
			binaryForWriting = "";
		}

	}

	public String padding(String binary, int length, boolean back) {
		while (length > 0) {
			if (!back)
				binary = "0" + binary;
			else
				binary = binary + "0";
			length--;
		}
		return binary;

	}

	public void closeWriting(boolean isfileClosed) {
		multiple = binaryForWriting.length() + (8 - binaryForWriting.length()% 8);
		binaryForWriting = padding(binaryForWriting,(8 - binaryForWriting.length() % 8), true);
		
		bitWriter(binaryForWriting);
		multiple=8;
		binaryForWriting=padding(Integer.toString('\n',2), 8-Integer.toString('\n',2).length(), false);
		//multiple=8*codeLength;
		//System.out.println();
		bitWriter(binaryForWriting);
		if(isfileClosed)
		{
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	}

	public void bitWriter(String binary) {
		int bitWritten = 0;
		int oneByteWriten = 0;
		byte oneByte = 0;

		while (bitWritten < multiple) {
			while (oneByteWriten < 8) {
				//System.out.print(binary.charAt(bitWritten));
				int temp = Integer.parseInt(Character.toString(binary
						.charAt(bitWritten)));
				oneByte |= temp << (7 - oneByteWriten);
				bitWritten++;
				oneByteWriten++;
			}

			// write in file
			try {
				out.write(oneByte);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}

			 //System.out.println("\n"+ oneByte);

			oneByte = 0;
			oneByteWriten = 0;
		}
	}

	public void setUpOutputFile(String fileName) {

		try {
			out = new BufferedOutputStream(new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String compressFileReader() {
		
		String input="";
		try {
			byte nextByte = ' ';
			
			while (nextByte != (byte)'\n') {
				nextByte = (byte) in.read();
				input=input+Integer.toString(nextByte)+",";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] inputArray;
		inputArray=input.split(",");
		String byteString = "";
		String CompleteByte;
		//print(bytes);
		for (int byteCounter = 0; byteCounter < inputArray.length-1; byteCounter++) {

			if(Integer.parseInt(inputArray[byteCounter])>=0)
			{
			CompleteByte=padding(
					Integer.toBinaryString(Integer.parseInt(inputArray[byteCounter])), 
					8-Integer.toBinaryString(Integer.parseInt(inputArray[byteCounter])).length()
					, false);
			byteString = byteString +CompleteByte; 
			}
			else
				
			{
				int temp =(-1)*Integer.parseInt(inputArray[byteCounter]);
				//System.out.println(Integer.toBinaryString(temp)+"this is temp");
				String s=Integer.toBinaryString(temp);
				s=padding(s, 8-s.length(), false);
				s=togel(s);
				//System.out.println(s+" Checkup");
				temp=Integer.parseInt(s,2)+1;
				s=Integer.toBinaryString(temp);
				//System.out.println(Integer.toBinaryString(temp));
				byteString=byteString+s;
			}

		}
		return byteString;

		
	}
    public String togel(String str)
    {int len=0;
    String output="";
    	while(len<str.length())
    	{   if(str.charAt(len)=='0')
    		output=output+"1";
    	else
    		output+="0";
    		len++;
    	}
    	return output;
    }
	public void setUpInputFile(String fileName) {
		try {
			in = new BufferedInputStream(new FileInputStream(
					fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		BitOperetor bo;
		try {
			bo = new BitOperetor( new FileOutputStream("compressedSmall"));
		
		/*bo.setUpOutputFile("compressed");
		bo.compressFileWriter(65);
		bo.compressFileWriter(66);
		bo.compressFileWriter(256);
		bo.compressFileWriter(1024);
		bo.compressFileWriter(2048);
		bo.compressFileWriter(2048*2);
		bo.compressFileWriter(2048*4);
		
		bo.closeWriting(true);*/
		bo.write("ABCABBCCA");
		bo.close();
		bo.setUpInputFile("compressedSmall");
		// bo.compressFileReader("2");
//		String str = "2-11283632";
//		System.out.println("\n fucked of");
		bo.compressFileReader();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public void print(byte[] bytes) {
		//System.out.println("Start");
		for (int i = 0; i < bytes.length; i++) {
			//System.out.print(bytes[i]+"  ");
		}
		//System.out.println("ends");
	}
}
