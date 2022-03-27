package com.nelanga.cas.utility;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class HS256KeyUtil {

    public static String generate() {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Encoders.BASE64.encode(key.getEncoded());
    }

    public static SecretKey secretKeyFrom(String secretString) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
    }
}
