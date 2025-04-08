package com.zorominecraft.vscbicf;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;


@Mod("vscbicf")
public class VSCBICF {

	public VSCBICF() {
		IEventBus modEventBus = MinecraftForge.EVENT_BUS;
		modEventBus.addListener(this::commonSetup);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {

	}

	@SubscribeEvent
	public static void onServerStarting(FMLDedicatedServerSetupEvent event) {

	}

}
