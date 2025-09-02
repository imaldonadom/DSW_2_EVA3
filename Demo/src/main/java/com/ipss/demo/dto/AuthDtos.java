package com.ipss.demo.dto;

public class AuthDtos {
  public static class RegisterRequest { public String username; public String password; }
  public static class LoginRequest { public String username; public String password; }
  public static class AuthResponse {
    public String token; public String username; public String role;
    public AuthResponse(String t, String u, String r){ token=t; username=u; role=r; }
  }
}
