package me.justahuman.slimefun_essentials.client;

import me.justahuman.slimefun_essentials.utils.TextureUtils;
import net.minecraft.util.Identifier;

public enum DrawMode {
    LIGHT, DARK, BOOK;

    public Identifier defaultIdentifier() {
        return switch (this) {
            case LIGHT -> TextureUtils.WIDGETS;
            case DARK -> TextureUtils.WIDGETS_DARK;
            case BOOK -> TextureUtils.WIDGETS_BOOK;
        };
    }
}
