package bio.test;

import bio.client.Client;
import bio.users.User;

/**
 * 模拟用户1
 *
 * @author: zhouxuke
 * @Date: 2018-11-02 3:06 PM
 */
public class TestUser1 {
    public static void main(String[] args) {
        User user = new User();
        user.setIp("127.0.0.1");
        user.setPort(8801);
        user.setUserId(1001);
        user.setUserName("user1");
        Client client = new Client("client1", user, "127.0.0.1", 8888);
        new Thread(() -> client.start()).start();
        client.login();
    }
}
