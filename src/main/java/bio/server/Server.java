package bio.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端
 *
 * @author: zhouxuke
 * @Date: 2018-11-02 2:04 PM
 */
public class Server {
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.submit(() -> {
                    new ServerHandler().handler(socket);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
