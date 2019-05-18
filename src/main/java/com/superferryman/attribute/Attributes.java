package com.superferryman.attribute;

import com.superferryman.session.Session;
import io.netty.util.AttributeKey;

/**
 * @Author superferryman
 * @Date 2019/4/22 12:38
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
