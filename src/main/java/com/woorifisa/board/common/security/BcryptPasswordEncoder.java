package com.woorifisa.board.common.security;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptPasswordEncoder implements PasswordEncoder {

    public String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }

}
