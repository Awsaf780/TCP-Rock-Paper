import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
	public static Socket socket = null;

	public static void main(String[] args) {
		try {
			socket = new Socket("localhost", 9900);
			System.out.println(
					"Connected to Server\n" + "Socket: " + socket.getInetAddress() + ":" + socket.getPort() + "\n");
		} catch (IOException e) {
			System.out.print("Connection to network can not be established");
		}
		BufferedReader in = null;
		PrintWriter out = null;
		Scanner consoleInput = new Scanner(System.in);
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("Server: " + in.readLine());
			System.out.print("Enter your name: ");
			out.println(consoleInput.nextLine());
			
			while (true) {
				System.out.print("Server: ");
				System.out.println(in.readLine());
				System.out.println();
				
				System.out.print("Client: ");
				String s = consoleInput.nextLine();
				
				if (s.equals("4")){
				    System.out.println("Left the server");
				    out.println(s);
				    System.exit(-1); 
				}
				else if ( !s.equals("1") && !s.equals("2") && !s.equals("3") && !s.equals("4") ){
				    System.out.println("Invalid Choice\n");
				    System.exit(-1); 
				    //System.out.print("Client: ");
				    //s = consoleInput.nextLine();
				}
				out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
