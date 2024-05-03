package me.justahuman.slimefun_essentials.compat.patchouli;

import me.justahuman.slimefun_essentials.api.IdInterpreter;
import me.justahuman.slimefun_essentials.client.SlimefunRecipeComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;

import java.util.Arrays;

public class PatchouliIdInterpreter implements IdInterpreter<PatchouliWidget> {
    public PatchouliWidget fromRecipeComponent(SlimefunRecipeComponent component) {
        if (component.getId() != null) {
            return interpretId(component, component.getId(), PatchouliWidget.EMPTY);
        } else if (component.getMultiId() != null) {
            return PatchouliWidget.wrap(component.getMultiId().stream().map(id -> interpretId(component, id, PatchouliWidget.EMPTY)).toList());
        }
        return PatchouliWidget.EMPTY;
    }

    @Override
    public PatchouliWidget fromTag(int chance, boolean consumed, TagKey<Item> tagKey, int amount, PatchouliWidget def) {
        return PatchouliWidget.wrap(Arrays.stream(Ingredient.fromTag(tagKey)
                .getMatchingStacks()).map(itemStack -> fromItemStack(chance, consumed, itemStack, amount, def)).toList());
    }

    @Override
    public PatchouliWidget fromItemStack(int chance, boolean consumed, ItemStack itemStack, int amount, PatchouliWidget def) {
        return PatchouliWidget.wrap(itemStack.copyWithCount(amount));
    }

    @Override
    public PatchouliWidget fromFluid(int chance, boolean consumed, Fluid fluid, int amount, PatchouliWidget def) {
        return fromItemStack(chance, consumed, fluid.getBucketItem().getDefaultStack(), amount, def);
    }

    @Override
    public PatchouliWidget fromEntityType(int chance, boolean consumed, EntityType<?> entityType, int amount, PatchouliWidget def) {
        final ClientWorld world = MinecraftClient.getInstance().world;
        if (world == null) {
            return def;
        }

        try {
            Entity entity = entityType.create(world);
            return entity != null ? PatchouliWidget.wrap(entity, amount) : def;
        } catch (Exception ignored) {}
        return def;
    }
}
