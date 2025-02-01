package me.justahuman.slimefun_essentials.api;

import me.justahuman.slimefun_essentials.client.ResourceLoader;
import me.justahuman.slimefun_essentials.client.SlimefunRecipeComponent;
import me.justahuman.slimefun_essentials.utils.Utils;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public interface IdInterpreter<T> {
    default T interpretId(@NotNull SlimefunRecipeComponent component, @NotNull String id, @NotNull T def) {
        if (id.isEmpty() || id.isBlank()) {
            return def;
        }
        
        if (!id.contains(":")) {
            Utils.warn("Invalid Ingredient Id:" + id);
            return def;
        }

        int damage = 0;
        if (id.contains("^")) {
            try {
                damage = Integer.parseInt(id.substring(id.indexOf('^') + 1));
                id = id.substring(0, id.indexOf('^'));
            } catch (Exception ignored) {}
        }

        float chance = 1;
        if (id.contains("%")) {
            try {
                chance = Float.parseFloat(id.substring(id.indexOf('%') + 1));
                id = id.substring(0, id.indexOf('%'));
            } catch (Exception ignored) {}
        }
        
        final String type = id.substring(0, id.indexOf(':'));
        final String count = id.substring(id.indexOf(':') + 1);
        int amount = 1;
        try {
            amount = Integer.parseInt(count);
        } catch (Exception ignored) {}

        // Slimefun Item
        if (ResourceLoader.getSlimefunItems().containsKey(type)) {
            final ItemStack itemStack = ResourceLoader.getSlimefunItems().get(type).copy().itemStack();
            if (damage > 0) {
                itemStack.setDamage(damage);
            }
            return fromItemStack(chance, itemStack, amount, def);
        }
        // Complex Item
        else if (type.startsWith("?")) {
            int index = 0;
            try {
                index = Integer.parseInt(type.substring(1));
            } catch (Exception ignored) {}
            return fromItemStack(chance, component.getComplexStacks().get(index), amount, def);
        }
        // Entity
        else if (type.startsWith("@")) {
            final boolean baby = type.startsWith("baby_", 1);
            final Identifier identifier = new Identifier("minecraft:" + type.substring(baby ? 6 : 1));
            if (! Registries.ENTITY_TYPE.containsId(identifier)) {
                Utils.warn("Invalid Ingredient Entity Id: " + id);
                return def;
            }
            return fromEntityType(chance, Registries.ENTITY_TYPE.get(identifier), baby, amount, def);
        }
        // Fluid
        else if (type.startsWith("~")) {
            final Identifier identifier = new Identifier("minecraft:" + type.substring(1));
            if (!Registries.FLUID.containsId(identifier)) {
                Utils.warn("Invalid Ingredient Fluid Id: " + id);
                return def;
            }
            return fromFluid(chance, FluidVariant.of(Registries.FLUID.get(identifier)), amount, def);
        }
        // Tag
        else if (type.startsWith("#")) {
            final Identifier identifier = new Identifier("minecraft:" + type.substring(1));
            return fromTag(chance, TagKey.of(Registries.ITEM.getKey(), identifier), amount, def);
        }
        // Experience
        else if (type.equals("$")) {
            return fromEntityType(chance, EntityType.EXPERIENCE_ORB, false, amount, def);
        }
        // Item (Or Mistake)
        else {
            final Identifier identifier = new Identifier("minecraft:" + type.toLowerCase());
            if (!Registries.ITEM.containsId(identifier)) {
                Utils.warn("Invalid Ingredient ItemStack Id: " + id);
                return def;
            }

            final ItemStack itemStack = Registries.ITEM.get(identifier).getDefaultStack().copy();
            if (damage > 0) {
                itemStack.setDamage(damage);
            }
            return fromItemStack(chance, itemStack, amount, def);
        }
    }
    
    T fromTag(float chance, TagKey<Item> tagKey, int amount, T def);
    T fromItemStack(float chance, ItemStack itemStack, int amount, T def);
    T fromFluid(float chance, FluidVariant fluid, int amount, T def);
    T fromEntityType(float chance, EntityType<?> entityType, boolean baby, int amount, T def);
}