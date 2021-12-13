import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;

public class Server1 {
	private static ServerSocket server = null;
	private static Socket socket = null;
	
	private static final int port = 9900;

	public static void main(String[] args) {

		// Create IO Objects
		BufferedReader in = null;
		PrintWriter out = null;
		Scanner consoleInput = new Scanner(System.in);

		// Start Server
		try {
			System.out.println("Server is starting ...");
			server = new ServerSocket(port);
			System.out.println("Server has started on port: " + port);
		} catch (IOException e) {
			System.out.println("Cannot listen to port: " + port);
			System.exit(-1);
		}

		while (true) {

			// Create Socket
			try {
				socket = server.accept();
				System.out.println("\nNew Client been connected");
			} catch (IOException e) {
				System.out.println("Communication Error with client");
				System.exit(-1);
			}

			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				out.println("Rock Paper Scissor Server");
				
				String this_client = in.readLine();
				System.out.println("Client Name: " + this_client + "\n");
				
				String choice = "To play Rock Paper Scissor, type the number of your choice: 1.Rock | 2.Paper | 3.Scissors | 4.Leave Server";
				out.println(choice);
				
				while (socket.isConnected()) {
					Random rand = new Random();
					int int_random = rand.nextInt(3);
					
					String rand_rps = "";
					switch(int_random) {
					    case 1: rand_rps = "Rock"; break;
					    case 2: rand_rps = "Paper"; break;
					    case 3: rand_rps = "Scissor"; break;
					    default: rand_rps = "Rock"; break;
					}
					
					// out.println("Server: Rock! Paper! Scissors! Go!");
					
					String received = in.readLine();
					
					switch (received) {
					    case "1": received = "Rock"; break;
					    case "2": received = "Paper"; break;
					    case "3": received = "Scissor"; break;
					    default: received = "Quit"; break;
					}
					
					if( received.equals("Quit") ) {
					    System.out.println("\n" + this_client + " has left the server\n");
					    break;
					}
					else if ( received.equals(rand_rps) ) {
					    System.out.println(this_client + " chose " + received);
					    out.println("Its a draw! Server also chose " + rand_rps);
					}
					
					else if ( received.equals("Paper") && rand_rps.equals("Rock") || received.equals("Rock") && rand_rps.equals("Scissor") || received.equals("Scissors") && rand_rps.equals("Paper") ) {
					    System.out.println(this_client + " chose " + received);
					    out.println(this_client + " Wins! Server chose " + rand_rps);
					}
					
					else if ( rand_rps.equals("Paper") && received.equals("Rock") || rand_rps.equals("Rock") && received.equals("Scissor") || rand_rps.equals("Scissors") && received.equals("Paper") ) {
					    System.out.println(this_client + " chose " + received);
					    out.println(this_client + " Loses! Server chose " + rand_rps);
					}
					
				}
			} catch (IOException e) {
				System.out.print("Client Left");
				consoleInput.close();
			}
		}
	}
}
