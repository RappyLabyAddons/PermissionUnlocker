package com.rappytv.permissionunlocker.listener;

import com.rappytv.permissionunlocker.PermissionUnlockerAddon;
import com.rappytv.permissionunlocker.event.PermissionStateCheckEvent;
import net.labymod.api.event.Subscribe;

public class PermissionStateCheckListener {

  private final PermissionUnlockerAddon addon;

  public PermissionStateCheckListener(PermissionUnlockerAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onPermissionStateCheck(PermissionStateCheckEvent event) {
    if (this.addon.configuration().enabled().get() && !event.isEnabled()) {
      event.setEnabled(true);
    }
  }

}
