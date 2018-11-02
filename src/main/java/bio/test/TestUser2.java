package bio.test;

import bio.client.Client;
import bio.users.User;

/**
 * 测试user2
 *
 * @author: zhouxuke
 * @Date: 2018-11-02 3:06 PM
 */
public class TestUser2 {
    public static void main(String[] args) {
        User user = new User();
        user.setIp("127.0.0.1");
        user.setPort(8802);
        user.setUserId(1002);
        user.setUserName("user2");
        Client client = new Client("client2", user, "127.0.0.1", 8888);
        new Thread(() -> client.start()).start();
        client.login();
        client.sendMessage("hello", 1001);
    }
}
