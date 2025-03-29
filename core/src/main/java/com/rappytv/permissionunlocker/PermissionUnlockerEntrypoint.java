package com.rappytv.permissionunlocker;

import java.lang.reflect.Field;
import java.util.Set;
import net.labymod.api.addon.entrypoint.Entrypoint;
import net.labymod.api.models.addon.annotation.AddonEntryPoint;
import net.labymod.api.models.version.Version;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.mixin.Mixins;

@AddonEntryPoint(priority = 1001)
@SuppressWarnings("all")
public class PermissionUnlockerEntrypoint implements Entrypoint {

  @Override
  public void initialize(Version version) {
    try {
      Field field = LaunchClassLoader.class.getDeclaredField("transformerExceptions");
      field.setAccessible(true);
      Set<String> transformerExceptions = (Set<String>) field.get(Launch.classLoader);
      transformerExceptions.remove("net.labymod.core.");
      field.setAccessible(false);
    } catch (Exception e) {
      new Thread(this::transform, "Mixin-Transformer").start();
    }
    Mixins.addConfiguration("permissionunlocker.mixins.json");
  }

  private void transform() {
    try {
      Thread.sleep(8000L);
    } catch (InterruptedException ignored) {
    }
    String clazz = "net.labymod.api.user.permission.ClientPermission";
    try {
      LaunchClassLoader loader = Launch.classLoader;
      byte[] classData = loader.getClassBytes(clazz);
      for (IClassTransformer transformer : loader.getTransformers()) {
        classData = transformer.transform(clazz, clazz, classData);
      }
      Class<?> aClass = loader.defineClassObject(clazz, classData, 0, classData.length, null);
      loader.addClassToCache(clazz, aClass);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
