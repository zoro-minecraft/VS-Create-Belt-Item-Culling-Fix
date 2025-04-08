package com.zorominecraft.vscbicf.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zorominecraft.vscbicf.mixinducks.WorkingMode;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltRenderer;

import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollOptionBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;

import net.minecraft.client.renderer.MultiBufferSource;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(BeltRenderer.class)
public abstract class BeltRendererMixin extends SafeBlockEntityRenderer<BeltBlockEntity> {

	@Unique
	private BeltBlockEntity vscbicf$be;


	@Inject(method = "renderItems", at = @At("HEAD"), remap = false)
	protected void vscbicf$renderItems(BeltBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay, CallbackInfo ci) {
		this.vscbicf$be = be;
	}

	@Override
	public boolean shouldCullItem(Vec3 itemPos, Level level) {
		if (vscbicf$getWorkingMode() == WorkingMode.WITH_SHIP) {
			return false;
		}

		return super.shouldCullItem(itemPos, level);
	}

	@Unique
	private WorkingMode vscbicf$getWorkingMode() {
		if (vscbicf$be != null) {
			ScrollValueBehaviour behaviour = vscbicf$be.getBehaviour(ScrollOptionBehaviour.TYPE);

			if (behaviour != null) {
				if (behaviour.value == 1) {
					return WorkingMode.WITH_SHIP;
				}
			}
		}

		return WorkingMode.ORIGINAL;
	}

}
