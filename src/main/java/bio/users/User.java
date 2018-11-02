package bio.users;

import lombok.Data;

/**
 * 用户类
 *
 * @author: zhouxuke
 * @Date: 2018-11-02 2:14 PM
 */
@Data
public class User {
    private int userId;
    private String userName;
    private String ip;
    private int port;
}
