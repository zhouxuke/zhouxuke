package bio.test;

import bio.server.Server;

/**
 * 启动server
 *
 * @author: zhouxuke
 * @Date: 2018-11-02 3:05 PM
 */
public class ServerStart {
    public static void main(String[] args) {
        Server server = new Server();
        server.start(8888);
    }
}
