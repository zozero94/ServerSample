import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ServerThread extends Thread {
	private Socket client;
	private BufferedReader reader;
	private PrintWriter writer;

	public ServerThread(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8")),
					true);
			JsonParser parser = new JsonParser();
			while (true) {// flag로 바꾸어 로그아웃되면 스레드 종료되도록 만들어야 함

				JsonObject data = (JsonObject) parser.parse(reader.readLine());
				int key = data.get("key").getAsInt();
				
				System.out.println(data);
				writer.println(""/*data*/);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
