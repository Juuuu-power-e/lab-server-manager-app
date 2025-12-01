package com.labadmin.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix="jwt")
public class JwtProperties {
    private String secret;
    private long expirationSeconds;
    public String getSecret(){return secret;}
    public void setSecret(String s){this.secret=s;}
    public long getExpirationSeconds(){return expirationSeconds;}
    public void setExpirationSeconds(long e){this.expirationSeconds=e;}
}
