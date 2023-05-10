
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.armorersbane.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArmorersBaneModGameRules {
	public static final GameRules.Key<GameRules.BooleanValue> ARMORERSBANEWEARANDTEAR = GameRules.register("armorersBaneWearAndTear", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));
	public static final GameRules.Key<GameRules.BooleanValue> ARMORERSBANEFOODDRAIN = GameRules.register("armorersBaneFoodDrain", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));
}
