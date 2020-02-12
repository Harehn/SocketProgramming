
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
      i = increase_throwError(i, args.length);
      timeout = Integer.parseInt(args[i]);
      i = increase_throwError(i, args.length);
      t_flag = true;
    }
    
    if(args[i].equals("-r")) {
      i = increase_throwError(i, args.length);
      max_retries = Integer.parseInt(args[i]);
      i = increase_throwError(i, args.length);
      r_flag = true;
    }    
    
    if(args[i].equals("-p")) {
      i = increase_throwError(i, args.length);
      port = Integer.parseInt(args[i]);
      i = increase_throwError(i, args.length);
      port_flag = true;
    }
    
    if(args[i].equals("-mx")) {
      query_type = "mail Server";
      i = increase_throwError(i, args.length);
    }else if(args[i].equals("-ns")) {
      query_type = "name server";
      i = increase_throwError(i, args.length);
    }
    
    server = args[i].substring(1);
    i = increase_throwError(i, args.length);
    name = args[i];
    printVariables(timeout, max_retries, port, query_type, server, name);
  }
  
  public static int increase_throwError(int i, int length) {
    System.out.println("i = " + i + " length = " + length);
    if ((i+1)==length) { // Looks if index out of bounds
      throwError();
    }
    return i + 1;
  }
  
  public static void throwError() {
    System.out.println("Proper usage is java DnsClient [-t timeout] [-r max-retries] [-p port] [-mx|-ns] @server name");
    System.exit(-1);
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
