package net.misti.goldenhorngoat.mixin;

import net.misti.goldenhorngoat.render.ScreamingGoatState;
import net.minecraft.client.render.entity.state.GoatEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(GoatEntityRenderState.class)
public class GoatEntityRenderStateMixin implements ScreamingGoatState {
    @Unique
    private boolean screaming;

    @Override
    public boolean isScreaming() {
        return this.screaming;
    }

    @Override
    public void setScreaming(boolean screaming) {
        this.screaming = screaming;
    }
}