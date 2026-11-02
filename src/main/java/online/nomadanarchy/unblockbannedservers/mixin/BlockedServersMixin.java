package online.nomadanarchy.unblockbannedservers.mixin;

import com.mojang.patchy.BlockedServers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockedServers.class)
public class BlockedServersMixin {

    @Inject(
        method = "isBlockedServerHostName",
        at = @At("HEAD"),
        cancellable = true,
        remap = false
    )
    //? if <1.17 {
    /*private static void isBlockedServerHostName(
        String server,
        CallbackInfoReturnable<Boolean> cir
    ) {
    *///?} else {
    private void isBlockedServerHostName(
        String server,
        CallbackInfoReturnable<Boolean> cir
    ) {
        //?}
        cir.setReturnValue(false);
    }
}
