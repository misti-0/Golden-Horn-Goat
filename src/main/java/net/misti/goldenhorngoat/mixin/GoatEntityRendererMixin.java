package net.misti.goldenhorngoat.mixin;

import net.misti.goldenhorngoat.render.ScreamingGoatState;
import net.minecraft.client.render.entity.GoatEntityRenderer;
import net.minecraft.client.render.entity.state.GoatEntityRenderState;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GoatEntityRenderer.class)
public class GoatEntityRendererMixin {
    @Unique
    private static final Identifier SCREAMING_TEXTURE = Identifier.of("golden-horn-goat", "textures/entity/goat/screaming_goat.png");

    @Inject(method = "updateRenderState", at = @At("TAIL"))
    private void copyScreamingFlag(GoatEntity goatEntity, GoatEntityRenderState goatEntityRenderState, float f, CallbackInfo ci) {
        ((ScreamingGoatState) goatEntityRenderState).setScreaming(goatEntity.isScreaming());
    }

    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    private void swapTexture(GoatEntityRenderState goatEntityRenderState, CallbackInfoReturnable<Identifier> cir) {
        if (((ScreamingGoatState) goatEntityRenderState).isScreaming()) {
            cir.setReturnValue(SCREAMING_TEXTURE);
        }
    }
}