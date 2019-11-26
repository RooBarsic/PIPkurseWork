package myClasses;

public enum Permissions {
    ADMIN ("admin"),
    USER("user");

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    String permission;
    Permissions(String prem) {
        this.permission = prem;
    }
}
