package bio.client;

import bio.message.Message;
import bio.message.MessageType;
import bio.users.User;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 客户端
 *
 * @author: zhouxuke
 * @Date: 2018-11-02 2:04 PM
 */
public class Client {

    private ExecutorService executorService = Executors.newCachedThreadPool();
    private User user;

    @Getter
    @Setter
    private String name;

    private String serverIp;
    private int serverPort;

    public Client(String name, User user, String serverIp, int serverPort) {
        this.name = name;
        this.user = user;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public Client login() {
        Message message = new Message();
        message.setMessageType(MessageType.LOGIN);
        message.setUser(user);
        sendMessage(message);
        return this;
    }

    public Client sendMessage(String sendMessage, int userId) {
        Message message = new Message();
        message.setUser(user);
        message.setSendUserId(userId);
        message.setMessage(sendMessage);
        message.setMessageType(MessageType.SEND);
        sendMessage(message);
        return this;
    }


    public Client sendMessage(Message message) {
        if (message == null) {
            return this;
        }
        Socket socket = null;
        OutputStream out = null;
        try {
            socket = new Socket(serverIp, serverPort);
            out = socket.getOutputStream();
            out.write(JSON.toJSONString(message).getBytes(Charset.forName("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return this;

    }

    public Client start() {
        try {
            ServerSocket serverSocket = new ServerSocket(user.getPort());
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.submit(() -> {
                    new ClientHandler().handler(name, socket);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }


}
