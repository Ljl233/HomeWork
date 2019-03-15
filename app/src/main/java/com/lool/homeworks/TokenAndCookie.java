package com.lool.homeworks;

public class TokenAndCookie {
    private String Mcookie;
    private String Mtoken;
    public TokenAndCookie(String Mtoken,String Mcookie) {
        this.Mcookie = Mcookie;
        this.Mtoken = Mtoken;
    }
    public String getMcookie( ) {
        return Mcookie;
    }
    public String getMtoken( ) {
        return Mtoken;
    }
}
