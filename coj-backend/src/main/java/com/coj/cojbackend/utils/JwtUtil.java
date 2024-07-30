package com.coj.cojbackend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Data
public class JwtUtil {
    private PrivateKey privateKey = null;
    private PublicKey publicKey = null;

    public JwtUtil() throws GeneralSecurityException, IOException {
        try {
            this.privateKey = loadPrivateKey("src/main/resources/keys/private_key.pem");
            this.publicKey = loadPublicKey("src/main/resources/keys/public_key.pem");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PrivateKey loadPrivateKey(String filepath) throws GeneralSecurityException, IOException {
        String key = new String(Files.readAllBytes(Paths.get(filepath)));
        key = key.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    private PublicKey loadPublicKey(String filepath) throws GeneralSecurityException, IOException {
        String key = new String(Files.readAllBytes(Paths.get(filepath)));
        key = key.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    // 生成 JWT
    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 1 week
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    // 验证 JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
