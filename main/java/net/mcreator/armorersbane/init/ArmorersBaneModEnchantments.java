
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.armorersbane.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.enchantment.Enchantment;

import net.mcreator.armorersbane.enchantment.WeightlessEnchantment;
import net.mcreator.armorersbane.enchantment.WeightinessCurseEnchantment;
import net.mcreator.armorersbane.ArmorersBaneMod;

public class ArmorersBaneModEnchantments {
	public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ArmorersBaneMod.MODID);
	public static final RegistryObject<Enchantment> WEIGHTINESS_CURSE = REGISTRY.register("weightiness_curse", () -> new WeightinessCurseEnchantment());
	public static final RegistryObject<Enchantment> WEIGHTLESS = REGISTRY.register("weightless", () -> new WeightlessEnchantment());
}
