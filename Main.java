
public class Main {

  private static int timeout = 5;
  private static boolean t_flag = false;
  private static int max_retries = 3;
  private static boolean r_flag = false;
  private static int port = 53;
  private static boolean port_flag = false;
  private static String query_type = "A"; 
  private static String server = "";
  private static String name = "";
  private static byte[] ipAddress;

  public static void main(String[] args) {
    
    
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
    ipAddress = getIP(server);
    for(byte a: ipAddress) {
      System.out.print(a+":");
    }
    System.out.println();
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
  
  public static byte[] getIP(String server) {
    String[] splitServer= server.split("\\.");
    if(splitServer.length != 4) {
        System.out.println("Incorrect IP address");
        throwError();
      }
    byte[] ipAddress =new byte[4];
    for(int k=0;k<4;k++) {
        int ip=convertToInt(splitServer[k]);
        if (ip<0 | ip >=256) {
            System.out.println("Wrong ip value[0,255]");
            throwError();
          }
        ipAddress[k]=(byte) ip;    // get IP address as a byte array
      }
    return ipAddress;
  }
}
