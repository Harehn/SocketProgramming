
public class Main {

  public static void main(String[] args) {
    System.out.println(args.length);
    
  }
  
  public void throwError() {
    System.out.println("Proper usage is java DnsClient [-t timeout] [-r max-retries] [-p port] [-mx|-ns] @server name");
  }
}
