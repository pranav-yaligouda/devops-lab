import java.io.*;
import java.net.*;

public class TaskServer {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Java server running on port " + port);

            while (true) {
                Socket client = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                OutputStream out = client.getOutputStream();

                String requestLine = in.readLine();
                System.out.println("Request: " + requestLine);

                int contentLength = 0;
                String line;
                while (!(line = in.readLine()).isEmpty()) {
                    if (line.startsWith("Content-Length:")) {
                        contentLength = Integer.parseInt(line.split(":")[1].trim());
                    }
                }

                char[] body = new char[contentLength];
                if (contentLength > 0) {
                    in.read(body, 0, contentLength);
                }

                String requestBody = new String(body);
                System.out.println("Body: " + requestBody);

                String responseBody = "Task received: " + requestBody;

                String httpResponse =
                        "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/plain\r\n" +
                        "Content-Length: " + responseBody.getBytes().length + "\r\n" +
                        "Connection: close\r\n" +
                        "\r\n" +
                        responseBody;

                out.write(httpResponse.getBytes());
                out.flush();
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
