package bio.login;

import bio.users.User;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 用户登录模块
 *
 * @author: zhouxuke
 * @Date: 2018-11-02 2:29 PM
 */
public class UserLogin {

    private static Map<Integer, User> loginUser = Maps.newConcurrentMap();

    public static void login(User user) {
        loginUser.put(user.getUserId(), user);
    }

    public static User getUser(Integer userId) {
        return loginUser.get(userId);
    }
}
