package model;

public interface IUser {
    String getId();
    String getUsername();
    String getPassword();
    String getRole();
    boolean isActive();
    
    boolean can(String action);
}
