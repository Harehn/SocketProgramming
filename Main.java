
public class Main {

  public static void main(String[] args) {
    int timeout = 5;
    boolean t_flag = false;
    int max_retries = 3;
    boolean r_flag = false;
    int port = 53;
    boolean port_flag = false;
    String query_type = "type A"; 
    String server = "";
    String name = "";
    
    
    
    int i = 0; 
    if(args[i]=="-t") {
      i++;
      timeout = Integer.parseInt(args[i]);
      i++;
      t_flag = true;
    }
    
    if(args[i]=="-r") {
      i++;
      max_retries = Integer.parseInt(args[i]);
      i++;
      r_flag = true;
    }    
    
    if(args[i]=="-p") {
      i++;
      port = Integer.parseInt(args[i]);
      i++;
      port_flag = true;
    }
    
    if(args[i]=="-mx") {
      query_type = "mail Server";
      i++;
    }else if(args[i]=="-ns") {
      query_type = "name server";
      i++;
    }
    
    server = args[i].substring(1);
    i++;
    name = args[i];
  }
  
  public void throwError() {
    System.out.println("Proper usage is java DnsClient [-t timeout] [-r max-retries] [-p port] [-mx|-ns] @server name");
  }
}
