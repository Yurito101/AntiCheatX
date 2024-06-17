package me.yurito.anticheatx.enums;

public enum Permissions {
    ADMIN("anticheatx.admin"),
    BYPASS("anticheatx.bypass"),
    COMMAND_ALERTS("anticheatx.commands.alerts");

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}