package bio.server;

import bio.login.UserLogin;
import bio.message.Message;
import bio.message.MessageType;
import bio.users.User;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * 服务的处理类
 *
 * @author: zhouxuke
 * @Date: 2018-11-02 2:05 PM
 */
public class ServerHandler {


    /**
     * 记录登录的用户
     */
    private Map<Integer, User> loginUsers = Maps.newHashMap();

    public void handler(Socket socket) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"))
        ) {
            String data = null;
            StringBuilder sb = new StringBuilder();
            while (true) {
                data = br.readLine();
                if (data == null) {
                    break;
                }
                sb.append(data);
            }
            Message message = JSON.parseObject(sb.toString(), Message.class);
            processMessage(message);

        } catch (Exception e) {

        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void processMessage(Message message) {
        if (message == null) {
            return;
        }
        if (message.getMessageType() == MessageType.LOGIN) {
            //用户登录
            UserLogin.login(message.getUser());
            System.out.println("登录成功");
        } else if (message.getMessageType() == MessageType.SEND) {
            //发送消息，向指定用户发送消息
            sendMessage(message.getMessage(), message.getSendUserId());
            System.out.println("消息转发成功");
        }

    }

    private void sendMessage(String message, Integer userId) {
        User sendUser = UserLogin.getUser(userId);
        if (sendUser == null) {
            return;
        }
        Socket socket = null;
        OutputStream out = null;
        try {
            //用用户的ID作为端口
            socket = new Socket(sendUser.getIp(), sendUser.getPort());
            out = socket.getOutputStream();
            out.write(message.getBytes(Charset.forName("utf-8")));
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

    }
}
