package bio.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 客户端处理类
 *
 * @author: zhouxuke
 * @Date: 2018-11-02 2:07 PM
 */
public class ClientHandler {

    public void handler(String name, Socket socket) {
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"))
        ) {
            String data;
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                data = bufferedReader.readLine();
                if (data == null) {
                    break;
                }
                stringBuilder.append(data);
            }
            System.out.println(name + " received message :" + stringBuilder.toString());
        } catch (Exception e) {

        }

    }
}
