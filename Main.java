
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
    if(args[i].equals("-t")) {
      i++;
      timeout = Integer.parseInt(args[i]);
      i++;
      t_flag = true;
    }
    
    if(args[i].equals("-r")) {
      i++;
      max_retries = Integer.parseInt(args[i]);
      i++;
      r_flag = true;
    }    
    
    if(args[i].equals("-p")) {
      i++;
      port = Integer.parseInt(args[i]);
      i++;
      port_flag = true;
    }
    
    if(args[i].equals("-mx")) {
      query_type = "mail Server";
      i++;
    }else if(args[i].equals("-ns")) {
      query_type = "name server";
      i++;
    }
    
    server = args[i].substring(1);
    i++;
    name = args[i];
    printVariables(timeout, max_retries, port, query_type, server, name);
  }
  
  public void throwError() {
    System.out.println("Proper usage is java DnsClient [-t timeout] [-r max-retries] [-p port] [-mx|-ns] @server name");
  }
  
  public static void printVariables(int timeout,int max_retries, int port, String query_type, String server, String name) {
    System.out.println("Timeout is "+ timeout);
    System.out.println("Max-retries is " + max_retries);
    System.out.println("Port is " + port);
    System.out.println("Query type is " + query_type);
    System.out.println("Server is " + server);
    System.out.println("Server name is " + name);
  }
  
  
}
