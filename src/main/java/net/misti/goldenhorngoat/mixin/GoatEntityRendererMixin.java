package net.misti.goldenhorngoat.mixin;

import net.misti.goldenhorngoat.render.ScreamingGoatState;
import net.minecraft.client.renderer.entity.GoatRenderer;
import net.minecraft.client.renderer.entity.state.GoatRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.goat.Goat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GoatRenderer.class)
public class GoatEntityRendererMixin {
    @Unique
    private static final Identifier ADULT_SCREAMING_TEXTURE = Identifier.fromNamespaceAndPath("golden-horn-goat", "textures/entity/goat/screaming_goat.png");
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void copyScreamingFlag(Goat goat, GoatRenderState state, float partialTick, CallbackInfo ci) {
        ((ScreamingGoatState) state).setScreaming(goat.isScreamingGoat());
    }

    @Inject(method = "getTextureLocation", at = @At("HEAD"), cancellable = true)
    private void swapTexture(GoatRenderState state, CallbackInfoReturnable<Identifier> cir) {
        if (((ScreamingGoatState) state).isScreaming()) {
            cir.setReturnValue(ADULT_SCREAMING_TEXTURE);
        }
    }
}
