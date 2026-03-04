package Pain;

public interface IUser {
    String getId();
    String getUsername();
    String getPassword();
    String getName();
    String getRole();
    boolean isActive();
    boolean can(String action);
}
