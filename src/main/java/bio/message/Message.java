package bio.message;

import bio.users.User;
import lombok.Data;

/**
 * 消息类
 *
 * @author: zhouxuke
 * @Date: 2018-11-02 2:17 PM
 */
@Data
public class Message {
    /**
     * 发送的消息类型
     */
    private MessageType messageType;
    /**
     * 发送的消息内容
     */
    private String message;

    /**
     * 需要发送给用户的消息
     */
    private int sendUserId;

    /**
     * 当前用户
     */
    private User user;
}
