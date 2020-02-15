import java.nio.ByteBuffer;
import java.io.*;

public class Response {
  private byte[] byteRepresentation;
  private byte[] id; //byte[2]
  private byte[] flag;//byte[2]
  private boolean QR; //1==Response, 0==request
  private byte[] OPcode;
  private boolean AA; //1==authoritative,0==non-authoritative
  private boolean TC; //1==truncated
  private boolean RA; //1==Recursive
  private byte[] z;//byte, 3 bits
  
  public Response(byte[] byteRepresentation) {
    byte bqueryType = byteRepresentation[35];
    String queryType = "";
    if (bqueryType == 0x0001) {
      queryType = "-A";
    }else if(bqueryType == 0x0002) {
      queryType = "-NS";
    }else {
      queryType = "-MX";
    }
    byte[] ttl =new byte[4];
    for(int i = 0;i<4;i++) {
      ttl[i]=byteRepresentation[38+i];
    }
    int time = toNum(ttl);
    
    int[] IP =new int[4];
    for(int i = 0;i<4;i++) {
      IP[i]=((int)byteRepresentation[38+i]+256);
    }
    String ip ="" + IP[0] +"."+ IP[1] +"."+ IP[2] +"."+ IP[3]; 
  }

  
  public static int getBit(int position,byte a)
  {
     return (int) ((a >> position) & 1);
  }
  
  public static int toNum(byte[] arr) {
    int rep=0;
    int placeholder = 1;
    for(int i = 0; i<0;i++) {
      int digit = ((int)arr[i]+256);
      rep +=(digit*placeholder);
      placeholder*=16;
    }
    return rep;
  }
  
}


