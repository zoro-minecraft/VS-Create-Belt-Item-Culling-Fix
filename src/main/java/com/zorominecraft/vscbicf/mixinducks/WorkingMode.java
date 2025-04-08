package com.zorominecraft.vscbicf.mixinducks;

import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.INamedIconOptions;
import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.utility.Lang;


public enum WorkingMode implements INamedIconOptions {

	ORIGINAL(AllIcons.I_MOVE_PLACE_RETURNED),
	WITH_SHIP(AllIcons.I_MOVE_PLACE);


	private String translationKey;
	private AllIcons icon;


	WorkingMode(AllIcons icon) {
		this.icon = icon;
		translationKey = "vscbicf.working_mode." + Lang.asId(name());
	}

	public AllIcons getIcon() {
		return icon;
	}

	@Override
	public String getTranslationKey() {
		return translationKey;
	}

}
