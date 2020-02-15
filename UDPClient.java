import java.net.*; 

public class UDPClient {

	/*
	 *  
	 * 1. This Client class creates the request as well as interprets the response
	 *  
	 */

	/*
	 * sendRequest class -- sends a query to the server for the given domain name using a UDP socket
	 * 
	 * return receiveData datagramPacket from the server
	 */

	public DatagramPacket sendRequest(int timeout, int maxRetries, int portNumber,String queryType,
			String serverIp, String domainName,byte[]ipAddress) throws Exception {

		// request output based on HandOut

		System.out.println("DnsClient sending request for "+domainName);
		System.out.println("Server: "+serverIp);
		System.out.println("Request type: "+queryType);

		byte[] sendData= new byte[1024];
		byte[] receiveData = new byte[1024];
		InetAddress IPAddress = InetAddress.getByAddress(ipAddress);

		DatagramPacket sendPacket= new DatagramPacket(sendData, sendData.length, IPAddress, portNumber);

		DatagramPacket receivePacket= new DatagramPacket(receiveData, receiveData.length);

		// begin to try to sendRequest within limited times of attempt ( HandOut method)

		for (int i=0; i<maxRetries; i++) {

			try {
				DatagramSocket clientSocket= new DatagramSocket(); // create client socket

				sendData= new byte[1024];
				receiveData = new byte[1024];
			
				Request request= new Request(queryType,domainName);
				sendData= request.getDNSRequest();

				sendPacket= new DatagramPacket(sendData, sendData.length, IPAddress, portNumber);  // sendPacket is ready
				
				receivePacket= new DatagramPacket(receiveData, receiveData.length);
				long beginTime = System.currentTimeMillis();  // calculate total time for receiving and sending packets
				clientSocket.setSoTimeout(timeout);

				clientSocket.send(sendPacket);

				clientSocket.receive(receivePacket);
 

				long endOfTime= System.currentTimeMillis(); // end of time

				long totalTime= (endOfTime - beginTime)/1000; // convert from milliseconds to seconds

				int times = i+1; // real times of try

				System.out.println("Response received after "+totalTime+"seconds( "+times+"retrie(s) )");

				clientSocket.close();

				return receivePacket;

			}catch(Exception e) {
				System.out.println("when"+(i+1)+"retries, error:"+e.getMessage());
			}

		}

		throw new Exception("exceed maximum number of retries");
		

	}




}
