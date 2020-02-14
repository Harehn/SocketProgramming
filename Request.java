import java.nio.ByteBuffer;
import java.io.*;
import java.util.Random;

public class Request {
     private String queryType;
     private String domainName; // for initializing request 
     
     private static int headerSize =12; // header size is 12 bytes
     private int domainNameSize=domainName.length()+2;  // domain name size = name size + first label indication(1 byte) + zero length label(1 byte)
     private static int qtypeSize =2;
     private static int qclassSize=2;
     
     public Request(String queryType, String domainName) {
    	    this.queryType=queryType;
    	    this.domainName=domainName;
     }
    
     public byte[] createRequest() {
		 ByteBuffer request = ByteBuffer.allocate(headerSize+domainNameSize+qtypeSize+qclassSize); // buffer size = combined all sizes
    	 
		 // 1st step : create header 
		 
		 // create random ID and put it to the header buffer
		 Random r = new Random();
		 byte[] ID = new byte[2];
		 r.nextBytes(ID);
		 for(int i=0; i<2;i++) {
			 request.put(ID[i]);
		 }
		 
		 request.put((byte)0x01);
		 request.put((byte)0x00); // Flags
		 
		 request.put((byte)0x00);
		 request.put((byte)0x01); // QDCOUNT
		 
		 request.put((byte)0x00);
		 request.put((byte)0x00);// ANCOUNT
		 
		 request.put((byte)0x00);
		 request.put((byte)0x00);// NSCOUNT
		 
		 request.put((byte)0x00); 
		 request.put((byte)0x00);// ARCOUNT (header finish, now goes to the domain name
		 
		 // create domain name
		 
		 String[] name = domainName.split("\\.");
		 //TODO 
		 
		 
		 
    	 
    	 return null;
    	 
     }
     
     
     
}

