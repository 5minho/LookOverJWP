package lesson02.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 7. 29..
 */
public class SimpleHttpClient {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader br = null;
        PrintStream out = null;
        try {
            socket = new Socket("www.daun.net", 80);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
            // 요청 라인
            out.println("GET / HTTP/1.1");

            // 헤더 정보
            out.println("Host: www.daum.net");
            out.println("Uesr-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0)"
                    + " AppleWebkit/537.36 (KHTML, like Gecko)"
                    + " Chrome/30.0.1599.101 Safari/537.36");

            // 공백 라인
            out.println();

            // 응답 내용 출력
            String responseLine;

            while ((responseLine = br.readLine()) != null) {
                System.out.println(responseLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                out.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
