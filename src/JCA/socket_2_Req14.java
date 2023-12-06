public class socket_2_Req14 {
    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket("localhost", 443);

            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            output.println("GET / HTTP/1.1");
            output.println();
            output.flush();

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }

            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}