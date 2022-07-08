package com.javafree.cloud.common.utils;

/**
 * @version V1.0
 * @Description: 密码加密工具类
 * @Author gwz  gwz126@126.com
 * @Date 2022/6/22 9:24
 */

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

  private final static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  /**
   * 密码加密
   * @param rawPassword
   * @return
   */
  public static String encryption(String rawPassword) {
    return encoder.encode(rawPassword);
  }

  /**
   * 密码匹配
   * @param rawPassword
   * @param encodedPassword
   * @return
   */
  public static boolean matches(String rawPassword, String encodedPassword) {
    return encoder.matches(rawPassword, encodedPassword);
  }

}
