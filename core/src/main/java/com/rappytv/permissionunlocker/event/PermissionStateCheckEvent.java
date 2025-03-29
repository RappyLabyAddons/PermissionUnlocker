package com.rappytv.permissionunlocker.event;

import net.labymod.api.event.Event;
import net.labymod.api.user.permission.ClientPermission;

public class PermissionStateCheckEvent implements Event {

  private final ClientPermission permission;
  private boolean enabled;

  public PermissionStateCheckEvent(ClientPermission permission, boolean enabled) {
    this.permission = permission;
    this.enabled = enabled;
  }

  public ClientPermission getPermission() {
    return this.permission;
  }

  public boolean isEnabled() {
    return this.enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}
