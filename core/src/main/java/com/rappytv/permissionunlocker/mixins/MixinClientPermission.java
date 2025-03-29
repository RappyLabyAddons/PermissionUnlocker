package com.rappytv.permissionunlocker.mixins;

import com.rappytv.permissionunlocker.event.PermissionStateCheckEvent;
import net.labymod.api.Laby;
import net.labymod.api.user.permission.ClientPermission;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ClientPermission.class, remap = false)
public class MixinClientPermission {

  @Shadow
  private boolean enabled;

  @Inject(method = "isEnabled", at = @At("HEAD"), cancellable = true)
  private void isEnabled(CallbackInfoReturnable<Boolean> cir) {
    PermissionStateCheckEvent event = new PermissionStateCheckEvent(
        (ClientPermission) (Object) this,
        this.enabled
    );
    Laby.fireEvent(event);
    cir.setReturnValue(event.isEnabled());
  }
}
