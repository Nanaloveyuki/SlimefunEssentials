package me.justahuman.slimefun_essentials.compat.jei.categories;

import me.justahuman.slimefun_essentials.api.OffsetBuilder;
import me.justahuman.slimefun_essentials.client.SlimefunRecipeCategory;
import me.justahuman.slimefun_essentials.client.SlimefunRecipe;
import me.justahuman.slimefun_essentials.compat.jei.JeiIntegration;
import me.justahuman.slimefun_essentials.utils.TextureUtils;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AncientAltarCategory extends ProcessCategory {
    public AncientAltarCategory(IGuiHelper guiHelper, SlimefunRecipeCategory slimefunRecipeCategory) {
        super(Type.ANCIENT_ALTAR, guiHelper, slimefunRecipeCategory);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SlimefunRecipe recipe, IFocusGroup focuses) {
        final OffsetBuilder offsets = new OffsetBuilder(this, recipe);
        recipe.fillInputs(9);
        recipe.fillOutputs(1);

        JeiIntegration.RECIPE_INTERPRETER.addIngredients(builder.addSlot(RecipeIngredientRole.INPUT, offsets.getX() + 1, offsets.slot() + 1), recipe.inputs().get(3));
        offsets.x().addSlot(false);
        JeiIntegration.RECIPE_INTERPRETER.addIngredients(builder.addSlot(RecipeIngredientRole.INPUT, offsets.getX() + 1, offsets.slot() + 1 + TextureUtils.SLOT.size()), recipe.inputs().get(0));
        JeiIntegration.RECIPE_INTERPRETER.addIngredients(builder.addSlot(RecipeIngredientRole.INPUT, offsets.getX() + 1, offsets.slot() + 1 - TextureUtils.SLOT.size()), recipe.inputs().get(6));
        offsets.x().addSlot(false);
        JeiIntegration.RECIPE_INTERPRETER.addIngredients(builder.addSlot(RecipeIngredientRole.INPUT, offsets.getX() + 1, offsets.slot() + 1 + TextureUtils.SLOT.size() * 2), recipe.inputs().get(1));
        JeiIntegration.RECIPE_INTERPRETER.addIngredients(builder.addSlot(RecipeIngredientRole.INPUT, offsets.getX() + 1, offsets.slot() + 1), recipe.inputs().get(4));
        JeiIntegration.RECIPE_INTERPRETER.addIngredients(builder.addSlot(RecipeIngredientRole.INPUT, offsets.getX() + 1, offsets.slot() + 1 - TextureUtils.SLOT.size() * 2), recipe.inputs().get(7));
        offsets.x().addSlot(false);
        JeiIntegration.RECIPE_INTERPRETER.addIngredients(builder.addSlot(RecipeIngredientRole.INPUT, offsets.getX() + 1, offsets.slot() + 1 + TextureUtils.SLOT.size()), recipe.inputs().get(2));
        JeiIntegration.RECIPE_INTERPRETER.addIngredients(builder.addSlot(RecipeIngredientRole.INPUT, offsets.getX() + 1, offsets.slot() + 1 - TextureUtils.SLOT.size()), recipe.inputs().get(8));
        offsets.x().addSlot(false);
        JeiIntegration.RECIPE_INTERPRETER.addIngredients(builder.addSlot(RecipeIngredientRole.INPUT, offsets.getX() + 1, offsets.slot() + 1), recipe.inputs().get(5));
        offsets.x().addSlot();
        offsets.x().addArrow();
        JeiIntegration.RECIPE_INTERPRETER.addIngredients(builder.addSlot(RecipeIngredientRole.OUTPUT, offsets.getX() + 1, offsets.slot() + 1), recipe.outputs().get(0));
    }

    @Override
    public void draw(SlimefunRecipe recipe, IRecipeSlotsView recipeSlotsView, DrawContext graphics, double mouseX, double mouseY) {
        final OffsetBuilder offsets = new OffsetBuilder(this, recipe);
        recipe.fillInputs(9);
        recipe.fillOutputs(1);

        TextureUtils.PEDESTAL.draw(graphics, offsets.getX(), offsets.slot());
        offsets.x().addSlot(false);
        TextureUtils.PEDESTAL.draw(graphics, offsets.getX(), offsets.slot() + TextureUtils.SLOT.size());
        TextureUtils.PEDESTAL.draw(graphics, offsets.getX(), offsets.slot() - TextureUtils.SLOT.size());
        offsets.x().addSlot(false);
        TextureUtils.PEDESTAL.draw(graphics, offsets.getX(), offsets.slot() + TextureUtils.SLOT.size() * 2);
        TextureUtils.ALTAR.draw(graphics, offsets.getX(), offsets.slot());
        TextureUtils.PEDESTAL.draw(graphics, offsets.getX(), offsets.slot() - TextureUtils.SLOT.size() * 2);
        offsets.x().addSlot(false);
        TextureUtils.PEDESTAL.draw(graphics, offsets.getX(), offsets.slot() + TextureUtils.SLOT.size());
        TextureUtils.PEDESTAL.draw(graphics, offsets.getX(), offsets.slot() - TextureUtils.SLOT.size());
        offsets.x().addSlot(false);
        TextureUtils.PEDESTAL.draw(graphics, offsets.getX(), offsets.slot());
        offsets.x().addSlot();
        addArrow(graphics, recipe, offsets.getX(), offsets.arrow(), false);
        offsets.x().addArrow();
        TextureUtils.SLOT.draw(graphics, offsets.getX(), offsets.slot());
    }

    @NotNull
    @Override
    public List<Text> getTooltipStrings(SlimefunRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        final List<Text> tooltips = new ArrayList<>();
        final OffsetBuilder offsets = new OffsetBuilder(this, recipe);
        offsets.x().addSlot(false).addSlot(false).addSlot(false).addSlot(false).addSlot();
        if (tooltipActive(mouseX, mouseY, offsets.getX(), offsets.arrow(), TextureUtils.ARROW)) {
            tooltips.add(timeTooltip(recipe));
        }
        return tooltips;
    }
}
