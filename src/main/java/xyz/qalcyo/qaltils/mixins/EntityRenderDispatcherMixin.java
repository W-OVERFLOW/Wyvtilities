package xyz.qalcyo.qaltils.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.qalcyo.qaltils.config.QaltilsConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {

    @Shadow
    private boolean renderHitboxes;

    @Inject(method = "renderHitbox", at = @At("HEAD"), cancellable = true)
    private static void cancelHitbox(MatrixStack matrices, VertexConsumer vertices, Entity entity, float tickDelta, CallbackInfo ci) {
        if (!QaltilsConfig.INSTANCE.getHitbox()) ci.cancel();
        if (!(entity instanceof PlayerEntity) && !QaltilsConfig.INSTANCE.getNonplayerHitbox()) ci.cancel();
        if (entity instanceof ClientPlayerEntity && QaltilsConfig.INSTANCE.getDisableForSelf()) ci.cancel();
    }

    @ModifyArgs(method = "renderHitbox", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;drawBox(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/util/math/Box;FFFF)V"))
    private static void modifyArgs(Args args, MatrixStack matrices, VertexConsumer vertices, Entity entity, float tickDelta) {
        if (!(MinecraftClient.getInstance().targetedEntity == null) && MinecraftClient.getInstance().targetedEntity == entity) {
            args.set(3, (float) QaltilsConfig.INSTANCE.getHitboxRangeColor().getRed() / 255);
            args.set(4, (float) QaltilsConfig.INSTANCE.getHitboxRangeColor().getGreen() / 255);
            args.set(5, (float) QaltilsConfig.INSTANCE.getHitboxRangeColor().getBlue() / 255);
            args.set(6, (float) QaltilsConfig.INSTANCE.getHitboxRangeColor().getAlpha() / 255);
        } else {
            args.set(3, (float) QaltilsConfig.INSTANCE.getHitboxColor().getRed() / 255);
            args.set(4, (float) QaltilsConfig.INSTANCE.getHitboxColor().getGreen() / 255);
            args.set(5, (float) QaltilsConfig.INSTANCE.getHitboxColor().getBlue() / 255);
            args.set(6, (float) QaltilsConfig.INSTANCE.getHitboxColor().getAlpha() / 255);
        }
    }

    @Redirect(method = "renderHitbox", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;drawBox(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;DDDDDDFFFF)V"))
    private static void modifyLineOfSight(MatrixStack matrices, VertexConsumer vertexConsumer, double x1, double y1, double z1, double x2, double y2, double z2, float red, float green, float blue, float alpha) {
        if (QaltilsConfig.INSTANCE.getHitboxLineOfSight()) {
            WorldRenderer.drawBox(matrices, vertexConsumer, x1, y1, z1, x2, y2, z2, (float) QaltilsConfig.INSTANCE.getHitboxLineOfSightColor().getRed() / 255, (float) QaltilsConfig.INSTANCE.getHitboxLineOfSightColor().getGreen() / 255, (float) QaltilsConfig.INSTANCE.getHitboxLineOfSightColor().getBlue() / 255, (float) QaltilsConfig.INSTANCE.getHitboxLineOfSightColor().getAlpha() / 255);
        }
    }

    @Inject(method = "renderHitbox", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/VertexConsumer;vertex(Lnet/minecraft/util/math/Matrix4f;FFF)Lnet/minecraft/client/render/VertexConsumer;"), cancellable = true)
    private static void cancelEyeline(MatrixStack matrices, VertexConsumer vertices, Entity entity, float tickDelta, CallbackInfo ci) {
        if (!QaltilsConfig.INSTANCE.getHitboxEyeLine()) ci.cancel();
    }

    @ModifyArgs(method = "renderHitbox", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/VertexConsumer;color(IIII)Lnet/minecraft/client/render/VertexConsumer;"))
    private static void modifyArgs2(Args args) {
        args.set(0, QaltilsConfig.INSTANCE.getHitboxEyelineColor().getRed());
        args.set(1, QaltilsConfig.INSTANCE.getHitboxEyelineColor().getGreen());
        args.set(2, QaltilsConfig.INSTANCE.getHitboxEyelineColor().getBlue());
        args.set(3, QaltilsConfig.INSTANCE.getHitboxEyelineColor().getAlpha());
    }

    @Shadow
    public abstract void setRenderHitboxes(boolean value);

    @Inject(method = "render", at = @At("HEAD"))
    private void forceHitboxes(Entity entity, double x, double y, double z, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (!renderHitboxes && QaltilsConfig.INSTANCE.getForceHitbox()) {
            setRenderHitboxes(true);
        }
    }
}