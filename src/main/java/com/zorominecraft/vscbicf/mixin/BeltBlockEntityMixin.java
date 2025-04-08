package com.zorominecraft.vscbicf.mixin;

import com.simibubi.create.AllBlocks;
import com.zorominecraft.vscbicf.mixinducks.WorkingMode;

import com.simibubi.create.content.kinetics.belt.BeltBlock;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltPart;
import com.simibubi.create.content.kinetics.belt.BeltSlope;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.CenteredSideValueBoxTransform;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollOptionBehaviour;
import com.simibubi.create.foundation.utility.Lang;

import net.minecraft.core.Direction;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Mixin(BeltBlockEntity.class)
public abstract class BeltBlockEntityMixin {

	@Inject(method = "addBehaviours", at = @At("RETURN"), remap = false)
	public void vscbicf$addBehaviours(List<BlockEntityBehaviour> behaviours, CallbackInfo ci) {
		ScrollOptionBehaviour<WorkingMode> workingMode = new ScrollOptionBehaviour<>(WorkingMode.class, Lang.translateDirect("vscbicf.working_mode"), (BeltBlockEntity) (Object) this, vscbicf$getWorkingModeValueBoxSlot());
		behaviours.add(workingMode);
	}

	@Unique
	private ValueBoxTransform vscbicf$getWorkingModeValueBoxSlot() {
		return new CenteredSideValueBoxTransform((blockState, direction) -> {
			if (!AllBlocks.BELT.has(blockState)) {
				return false;
			}

			BeltSlope beltSlope = blockState.getValue(BeltBlock.SLOPE);
			BeltPart part = blockState.getValue(BeltBlock.PART);
			Direction facing = blockState.getValue(BeltBlock.HORIZONTAL_FACING);

			if (beltSlope == BeltSlope.SIDEWAYS || beltSlope == BeltSlope.VERTICAL || part != BeltPart.START) {
				return false;
			}

			Direction.Axis axis = direction.getAxis();
			Direction.Axis beltAxis = facing.getAxis();

			return beltAxis == axis || axis != Direction.Axis.Y;
		});
	}

}
