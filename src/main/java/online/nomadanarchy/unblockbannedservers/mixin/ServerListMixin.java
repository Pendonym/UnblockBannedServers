package online.nomadanarchy.unblockbannedservers.mixin;

//? if <=1.13.2 {
/*import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.option.ServerList;
*///?} elif <1.14.4 {
/*import net.minecraft.client.options.ServerEntry;
import net.minecraft.client.options.ServerList;
*///?} else {
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
//?}

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerList.class)
public class ServerListMixin {

    @Unique
    private static final String DEFAULT_SERVER_ADDRESS = "play.nomadanarchy.online";

    @Unique
    private static boolean isNomadServer(String address) {
        return address != null
            && address.equalsIgnoreCase(DEFAULT_SERVER_ADDRESS);
    }

    //? if <=1.13.2 {
    /*@Shadow
    @Final
    private List<ServerInfo> servers;

    @Inject(method = "loadFile", at = @At("RETURN"))
    private void afterLoad(CallbackInfo ci) {
        if (servers.stream().noneMatch(data -> isNomadServer(data.address))) {
            servers.add(0, new ServerInfo("Nomad Anarchy", DEFAULT_SERVER_ADDRESS, false));
        }
    }
    *///?} elif <1.14.4 {
    /*@Shadow
    @Final
    private List<ServerEntry> serverEntries;

    @Inject(method = "loadFile", at = @At("RETURN"))
    private void afterLoad(CallbackInfo ci) {
        if (serverEntries.stream().noneMatch(data -> isNomadServer(data.address))) {
            serverEntries.add(0, new ServerEntry("Nomad Anarchy", DEFAULT_SERVER_ADDRESS, false));
        }
    }
    *///?} else {
    @Shadow
    @Final
    private List<ServerData> serverList;

    @Inject(method = "load", at = @At("RETURN"))
    private void afterLoad(CallbackInfo ci) {
        if (serverList.stream().noneMatch(data -> isNomadServer(data.ip))) {
            //? if <1.20.2 {
            /*serverList.add(0, new ServerData("Nomad Anarchy", DEFAULT_SERVER_ADDRESS, false));
             *///?} elif <1.20.5 {
            /*serverList.add(0, new ServerData("Nomad Anarchy", DEFAULT_SERVER_ADDRESS, ServerData.Type.OTHER));
             *///?} else {
            serverList.addFirst(new ServerData("Nomad Anarchy", DEFAULT_SERVER_ADDRESS, ServerData.Type.OTHER));
            //?}
        }
    }
    //?}
}
