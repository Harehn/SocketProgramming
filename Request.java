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
		 
		 // create random ID and put it to the request buffer
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
		 // put the domain name in request buffer
		 for(int i=0; i<name.length;i++) {
			 String partOfName= name[i];
			 request.put((byte)partOfName.length());
			 for (int j=0;j<partOfName.length();j++){
				 request.put((byte)partOfName.charAt(j));				 
			 }
		 }
		 
		 request.put((byte)0x00); // out zero length label marking the end of the QNAME field.
		 
		 // now we need to put QTYPE 
		 
		 switch(queryType) {
		 case "type A":
			 request.put((byte)0x0001);
		 case "name server":
			 request.put((byte)0x0002);
		 case "mail Server":
			 request.put((byte)0x0003);
		 }
		 
		 // now we need to put QCLASS
		 request.put((byte) 0x0001);
		 
		 // REQUEST buffer finish, transfer it to the byte array.
		 
		 byte[]requestData = request.array();
    	 
    	 return requestData;
    	 
     }
     
     
     
}

