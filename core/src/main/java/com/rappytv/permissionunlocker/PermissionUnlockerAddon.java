package com.rappytv.permissionunlocker;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class PermissionUnlockerAddon extends LabyAddon<PermissionUnlockerConfig> {

  @Override
  protected void enable() {
    this.registerSettingCategory();
  }

  @Override
  protected Class<? extends PermissionUnlockerConfig> configurationClass() {
    return PermissionUnlockerConfig.class;
  }
}
