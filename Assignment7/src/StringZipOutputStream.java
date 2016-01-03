import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StringZipOutputStream {
	//HashMap<String, Integer> map;
	BufferedOutputStream out;
	private String binaryForWriting = "";
	private int codeLength = 12;
	private int multiple = codeLength * 8;
	private int mapCounter = 0;
	public static BufferedInputStream in;
	public int readingkeysCount = 0;
	int keysPossible=3500;
	MyHashMap map;
	String[] readingMap;

	// Creates a new output stream with a default buffer size.
	public StringZipOutputStream(OutputStream out) {
		this.out = new BufferedOutputStream(out);
		map = new MyHashMap();
		 //map= new HashMap<String,Integer>();
		for (int counter = 0; counter < 256; counter++) {
			map.put("" + (char) counter, counter);
		}
	}

	// Writes aStrign compressed output stream. This method will block until all
	// data is written.
	public void write(String line) {
		String empty = "";
		if (line.equals("") || line == null) {
			return;
		} else {
			String reserve = empty + line.charAt(0);
			String key = empty;
			for (char nextItem : line.substring(1).toCharArray()) {
				key = reserve + nextItem;
				if (map.get(key)!=null)
					reserve = key;
				else {
					compressFileWriter(map.get(reserve).getValue());
					if (mapCounter < keysPossible) {
						map.put(key, 256 + mapCounter++);
					}
					reserve = empty + nextItem;
				}
			}
			compressFileWriter(map.get(reserve).getValue());
			compressFileWriter((int)'\n');
			
			multiple = 8 * codeLength;
		}
	}

	//write in compress file after modifiying the bits from 8 to CodeLenght so that 
	//we can store bits of keys of combinations along with normal words
	public void compressFileWriter(int CompressCode) {
		String temp = "";
		// System.out.println(Integer.toString(CompressCode,2));

		//if (CompressCode <= 255) {
			temp = padding(Integer.toString(CompressCode, 2), codeLength
					- Integer.toString(CompressCode, 2).length(), false);
	/*	} else {
			temp = padding(Integer.toString(CompressCode, 2), codeLength
					- Integer.toString(CompressCode, 2).length(), false);
		}*/
		// System.out.println(temp+"Temp");
		binaryForWriting = binaryForWriting + temp;

		//if (binaryForWriting.length() == multiple) {
			if (binaryForWriting.length()%8==0) {
			bitWriter(binaryForWriting);
			binaryForWriting = "";
	}

	}
 // pad zeros in the begining or at the end of bytes for changing there size.
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
   // write data in file bit wise from the buffer
	public void bitWriter(String binary) {
		int bitWritten = 0;
		int oneByteWriten = 0;
		byte oneByte = 0;
        int counter=binary.length()/8;
		//while (bitWritten < multiple) {
        while(counter-->0){
			while (oneByteWriten < 8) {
				// System.out.print(binary.charAt(bitWritten));
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
				// e.printStackTrace();
			}

			// System.out.println("\n"+ oneByte);

			oneByte = 0;
			oneByteWriten = 0;
		}
	}
  
	public void closeWriting(boolean isfileClosed) {
		
		binaryForWriting += padding(Integer.toString('\n', 2), codeLength - Integer
				.toString('\n', 2).length(), false);
		//multiple = binaryForWriting.length()
		//		+ (8 - binaryForWriting.length() % 8);
		//binaryForWriting = padding(binaryForWriting,
		//		(8 - binaryForWriting.length() % 8), true);

		bitWriter(binaryForWriting);
		//binaryForWriting = "";
	//	multiple = 8;
//	binaryForWriting = padding(Integer.toString('\n', 2), 8 - Integer
//				.toString('\n', 2).length(), false);
		// multiple=8*codeLength;
		// System.out.println();
		//bitWriter(binaryForWriting);
		if (isfileClosed) {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// Writes remaining data to the output stream and closes the underlying
	// stream.
	public void close() {
		if(binaryForWriting.length()%8!=0)
		{
			binaryForWriting = padding(binaryForWriting,
					(8 - binaryForWriting.length() % 8), true);
			bitWriter(binaryForWriting);
		}
		System.out.println(mapCounter);
		try {
			out.close();
			// in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
