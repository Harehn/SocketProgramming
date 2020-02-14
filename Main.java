
public class Main {

  public static void main(String[] args) {
    int timeout = 5;
    boolean t_flag = false;
    int max_retries = 3;
    boolean r_flag = false;
    int port = 53;
    boolean port_flag = false;
    String query_type = "A"; 
    String server = "";
    String name = "";
    
    
    if(args.length == 0) {
      System.out.println("Need at least 2 arguments");
      throwError();
    }
    
    
    int i = 0; 
    if(args[i].equals("-t")) {
      i = increase_throwError(i, args.length);
      timeout = convertToInt(args[i]);
      i = increase_throwError(i, args.length);
      t_flag = true;
    }
    
    if(args[i].equals("-r")) {
      i = increase_throwError(i, args.length);
      max_retries = convertToInt(args[i]);
      i = increase_throwError(i, args.length);
      r_flag = true;
    }    
    
    if(args[i].equals("-p")) {
      i = increase_throwError(i, args.length);
      port = convertToInt(args[i]);
      i = increase_throwError(i, args.length);
      port_flag = true;
    }
    
    if(args[i].equals("-mx")) {
      query_type = "MX";
      i = increase_throwError(i, args.length);
    }else if(args[i].equals("-ns")) {
      query_type = "NS";
      i = increase_throwError(i, args.length);
    }
    
    server = args[i].substring(1);
    i = increase_throwError(i, args.length);
    name = args[i];
    
    if((i+1)<args.length) { // if too many arguments, throw error
      System.out.println("Too many arguments");
      throwError();
    }
    
    printVariables(timeout, max_retries, port, query_type, server, name);
    printRequest(name, server, query_type);
  }
  
  public static int increase_throwError(int i, int length) {
    if ((i+1)==length) { // Looks if index out of bounds
      System.out.println("Arguments missing");
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
  
  public static int convertToInt(String val) {
    try {
      return Integer.parseInt(val);
    }catch(Exception e){
      System.out.println("Expected int");
      throwError();
    }
    return -1;
  }
  
  public static void printRequest(String name, String server, String query_type) {
    System.out.println("DnsClient sending request for " + name);
    System.out.println("Server: " + server);
    System.out.println("Request type: " + query_type);
  }
  
}
