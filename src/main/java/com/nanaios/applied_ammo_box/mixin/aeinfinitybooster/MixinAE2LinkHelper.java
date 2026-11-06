package com.nanaios.applied_ammo_box.mixin.aeinfinitybooster;

import appeng.blockentity.networking.WirelessAccessPointBlockEntity;
import com.nanaios.applied_ammo_box.util.AE2LinkHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AE2LinkHelper.class, remap = false)
public class MixinAE2LinkHelper {
    @Inject(method = "getWapSqDistance", at = @At(value = "INVOKE", target = "appeng/blockentity/networking/WirelessAccessPointBlockEntity.getRange()D"), cancellable = true)
    private static void applied_ammo_box$mixinGetWapSqDistance$infinityCardInject(WirelessAccessPointBlockEntity wap, BlockPos pos, Level level, CallbackInfoReturnable<Double> cir) {
        // Infinityカード装着時は距離を問わず256mに設定
        if (applied_ammo_box$hasBoosterCard(wap, "infinity_card")) {
            cir.setReturnValue(256.0D);
        }
    }

    @Inject(method = "getWapSqDistance", at = @At(value = "INVOKE", target = "appeng/blockentity/networking/WirelessAccessPointBlockEntity.getLocation()Lappeng/api/util/DimensionalBlockPos;"), cancellable = true)
    private static void applied_ammo_box$mixinGetWapSqDistance$dimensionCardInject(WirelessAccessPointBlockEntity wap, BlockPos pos, Level level, CallbackInfoReturnable<Double> cir) {
        // Dimensionカード装着時はディメンションを問わず1024mに設定
        if (applied_ammo_box$hasBoosterCard(wap, "dimension_card")) {
            cir.setReturnValue(1024.0);
        }
    }

    /// アクセスポイントに指定したAE Infinity Boosterのカードが装着されているかを返す
    ///
    /// AE Infinity Boosterの`ModItems`のフィールドはバージョンによって型が異なる
    /// (1.x: `RegistryObject<Item>`、2.x以降: `Supplier<Item>`)ため、直接参照すると`NoSuchFieldError`でクラッシュする。
    /// そのためフィールドは参照せず、レジストリIDで比較してバージョンに依存しないようにする
    ///
    /// @param wap      対象のアクセスポイント
    /// @param cardPath カードのレジストリIDのパス
    @Unique
    private static boolean applied_ammo_box$hasBoosterCard(WirelessAccessPointBlockEntity wap, String cardPath) {
        ItemStack card = wap.getInternalInventory().getStackInSlot(0);
        if (card.isEmpty()) return false;

        ResourceLocation id = BuiltInRegistries.ITEM.getKey(card.getItem());
        return "aeinfinitybooster".equals(id.getNamespace()) && cardPath.equals(id.getPath());
    }
}
