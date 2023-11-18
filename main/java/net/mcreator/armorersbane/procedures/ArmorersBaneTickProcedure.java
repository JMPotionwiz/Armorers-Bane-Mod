package net.mcreator.armorersbane.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.armorersbane.network.ArmorersBaneModVariables;
import net.mcreator.armorersbane.init.ArmorersBaneModGameRules;
import net.mcreator.armorersbane.init.ArmorersBaneModEnchantments;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ArmorersBaneTickProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double armorExhaustion = 0;
		double D = 0;
		double totalD = 0;
		double armorIndex = 0;
		double itemsEquiped = 0;
		double V = 0;
		V = entity.walkDist - (entity.getCapability(ArmorersBaneModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ArmorersBaneModVariables.PlayerVariables())).walkDistPrev;
		if (world.getLevelData().getGameRules().getBoolean(ArmorersBaneModGameRules.ARMORERSBANEFOODDRAIN) && entity.onGround() && !(entity.isInWaterOrBubble() || (entity instanceof Player _plr ? _plr.getAbilities().instabuild : false))) {
			if (!world.isClientSide()) {
				D = 0;
				totalD = 0;
				itemsEquiped = 0;
				armorIndex = 0;
				for (int index0 = 0; index0 < 4; index0++) {
					if (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem())) {
						D = D + (((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).isDamageableItem()
								? (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).getMaxDamage()
								: 32)
								- ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).isDamageableItem()
										? (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).getDamageValue()
										: 0))
								* Math.pow(0.5,
										((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY)
												.is(ItemTags.create(new ResourceLocation("armorers_bane:lightweight"))) ? 1 : 0)
												+ (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY)
														.getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get()))
								* Math.max((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY)
										.getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTINESS_CURSE.get()) + 1, 1)
								* ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).isEnchanted()
										&& !(EnchantmentHelper.getItemEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get(),
												(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY)) != 0) ? 1.25 : 1);
						totalD = totalD + ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).isDamageableItem()
								? (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).getMaxDamage()
								: 32)
								* Math.pow(0.5,
										((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY)
												.is(ItemTags.create(new ResourceLocation("armorers_bane:lightweight"))) ? 1 : 0)
												+ (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY)
														.getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get()))
								* Math.max((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY)
										.getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTINESS_CURSE.get()) + 1, 1)
								* ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).isEnchanted()
										&& !(EnchantmentHelper.getItemEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get(),
												(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY)) != 0) ? 1.25 : 1);
						itemsEquiped = itemsEquiped + 1
								* Math.pow(0.5,
										((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY)
												.is(ItemTags.create(new ResourceLocation("armorers_bane:lightweight"))) ? 1 : 0)
												+ (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY)
														.getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get()))
								* Math.max((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY)
										.getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTINESS_CURSE.get()) + 1, 1)
								* ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).isEnchanted()
										&& !(EnchantmentHelper.getItemEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get(),
												(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY)) != 0) ? 1.25 : 1);
					}
					armorIndex = armorIndex + 1;
				}
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("armorers_bane:heavy_handhelds")))) {
					D = D + (((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).isDamageableItem() ? (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getMaxDamage() : 32)
							- ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).isDamageableItem() ? (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getDamageValue() : 0))
							* Math.pow(0.5,
									((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("armorers_bane:lightweight"))) ? 1 : 0)
											+ (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get()))
							* Math.max((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTINESS_CURSE.get()) + 1, 1)
							* ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).isEnchanted()
									&& !(EnchantmentHelper.getItemEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get(), (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0) ? 1.25 : 1);
					totalD = totalD
							+ ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).isDamageableItem() ? (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getMaxDamage() : 32)
									* Math.pow(0.5,
											((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("armorers_bane:lightweight"))) ? 1 : 0)
													+ (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get()))
									* Math.max((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTINESS_CURSE.get()) + 1, 1)
									* ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).isEnchanted()
											&& !(EnchantmentHelper.getItemEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get(), (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0) ? 1.25 : 1);
					itemsEquiped = itemsEquiped + 1
							* Math.pow(0.5,
									((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("armorers_bane:lightweight"))) ? 1 : 0)
											+ (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get()))
							* Math.max((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTINESS_CURSE.get()) + 1, 1)
							* ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).isEnchanted()
									&& !(EnchantmentHelper.getItemEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get(), (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0) ? 1.25 : 1);
				}
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("armorers_bane:heavy_handhelds")))) {
					D = D + (((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).isDamageableItem() ? (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getMaxDamage() : 32)
							- ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).isDamageableItem() ? (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getDamageValue() : 0))
							* Math.pow(0.5,
									((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("armorers_bane:lightweight"))) ? 1 : 0)
											+ (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get()))
							* Math.max((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTINESS_CURSE.get()) + 1, 1)
							* ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).isEnchanted()
									&& !(EnchantmentHelper.getItemEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get(), (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)) != 0) ? 1.25 : 1);
					totalD = totalD
							+ ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).isDamageableItem() ? (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getMaxDamage() : 32)
									* Math.pow(0.5,
											((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("armorers_bane:lightweight"))) ? 1 : 0)
													+ (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get()))
									* Math.max((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTINESS_CURSE.get()) + 1, 1)
									* ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).isEnchanted()
											&& !(EnchantmentHelper.getItemEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get(), (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)) != 0) ? 1.25 : 1);
					itemsEquiped = itemsEquiped + 1
							* Math.pow(0.5,
									((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("armorers_bane:lightweight"))) ? 1 : 0)
											+ (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get()))
							* Math.max((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTINESS_CURSE.get()) + 1, 1)
							* ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).isEnchanted()
									&& !(EnchantmentHelper.getItemEnchantmentLevel(ArmorersBaneModEnchantments.WEIGHTLESS.get(), (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)) != 0) ? 1.25 : 1);
				}
				armorExhaustion = ((totalD * 0.5 * (D / Math.max(totalD, 1)) + totalD * 0.5) * 0.05 + (entity instanceof LivingEntity _livEnt ? _livEnt.getArmorValue() : 0) * 2 + itemsEquiped) * 0.002;
				if (entity instanceof Player _player)
					_player.causeFoodExhaustion((float) (V * armorExhaustion));
			}
		}
		if (world.getLevelData().getGameRules().getBoolean(ArmorersBaneModGameRules.ARMORERSBANEWEARANDTEAR) && (entity.onGround() || entity.isInWaterOrBubble()) && !(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			if (!world.isClientSide()) {
				armorIndex = 0;
				D = 15;
				for (int index1 = 0; index1 < 4; index1++) {
					if (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem())) {
						for (int index2 = 0; index2 < (int) Math.floor(V / D); index2++) {
							if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).getDamageValue()
									+ 1 < (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).getMaxDamage()) {
								{
									ItemStack _ist = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY);
									if (_ist.hurt(1, RandomSource.create(), null)) {
										_ist.shrink(1);
										_ist.setDamageValue(0);
									}
								}
							}
						}
						if (V / D - Math.floor(V / D) > Math.random()) {
							if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).getDamageValue()
									+ 1 < (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY).getMaxDamage()) {
								{
									ItemStack _ist = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) armorIndex)) : ItemStack.EMPTY);
									if (_ist.hurt(1, RandomSource.create(), null)) {
										_ist.shrink(1);
										_ist.setDamageValue(0);
									}
								}
							}
						}
					}
					armorIndex = armorIndex + 1;
					D = Math.min(D + 5, 25);
				}
			}
		}
		{
			double _setval = entity.walkDist;
			entity.getCapability(ArmorersBaneModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.walkDistPrev = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
