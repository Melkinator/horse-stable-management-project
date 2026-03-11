package Pain;

public interface IUser {
    String getId();
    String getUsername();
    boolean isActive();
    boolean checkPassword(String input);
    String getName();
    String getRole();
    public abstract boolean can(String action);
}
