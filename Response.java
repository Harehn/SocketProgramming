import java.nio.ByteBuffer;
import java.io.*;

public class Response {
  public byte[] byteRepresentation;
  public byte[] id; //byte[2]
  public byte[] flag;//byte[2]
  public boolean QR; //1==Response, 0==request
  public byte[] OPcode;
  public boolean AA; //1==authoritative,0==non-authoritative
  public boolean TC; //1==truncated
  public boolean RA; //1==Recursive
  public byte[] z;//byte, 3 bits
  String ip = "";
  String queryType = "-A";
  int time=0;
  
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
    time = toNum(ttl);
    AA = getBit(6, byteRepresentation[2])==0x001;
    
    int[] IP =new int[4];
    for(int i = 0;i<4;i++) {
      IP[i] = getInt(byteRepresentation[43+i]);
    }
    ip ="" + IP[0] +"."+ IP[1] +"."+ IP[2] +"."+ IP[3]; 
  }
  
  public String writeData() {
    String toRet = "";
    if (this.queryType.equals("-A")){
      toRet += "IP\t";
    }
    else if (this.queryType.equals("-NS")) {
      toRet += "NS\t";
    }else {
      toRet += "MX\t";
    }
    toRet += this.ip;
    toRet += "\t";
    toRet += this.time;
    toRet += "\t";
    if (this.AA){
      toRet += ("auth");
    }else {
      toRet += ("nonauth");
    }
    return toRet;
  }

  
  public static int getBit(int position,byte a)
  {
     return (int) ((a >> position) & 1);
  }
  
  public static int toNum(byte[] arr) {
    int rep=0;
    int placeholder = 1;
    for(int i = 0; i<0;i++) {
      int digit = getInt(arr[i]);
      rep +=(digit*placeholder);
      placeholder*=16;
    }
    return rep;
  }
  
  public static int getInt(byte a) {
    int digit = (int)a;
    if (digit<0) {
      digit = (digit+256);
    }
    return digit;
  }
  
}


