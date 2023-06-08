package me.luligabi.miningutility.client.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import me.luligabi.miningutility.common.MiningUtility;
import me.luligabi.miningutility.common.ModConfig;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigScreenEntrypoint implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return this::createConfigScreen;
    }

    public Screen createConfigScreen(Screen parent) {
        ModConfig config = MiningUtility.CONFIG;

        /*
         * Rope Ladders
         */
        Option<Integer> ropeLadderBlockLimit = Option.<Integer>createBuilder()
                .name(Text.translatable("configOption.coxinhautilities.ropeLadderBlockLimit"))
                .description(OptionDescription.of(Text.translatable("configOption.coxinhautilities.ropeLadderBlockLimit.tooltip")))
                .binding(
                        64,
                        () -> config.ropeLadderBlockLimit,
                        newValue -> config.ropeLadderBlockLimit = newValue
                )
                .controller(option -> IntegerFieldControllerBuilder.create(option).range(2, 320))
                .build();

        Option<Integer> invertedRopeLadderBlockLimit = Option.<Integer>createBuilder()
                .name(Text.translatable("configOption.coxinhautilities.invertedRopeLadderBlockLimit"))
                .description(OptionDescription.of(Text.translatable("configOption.coxinhautilities.invertedRopeLadderBlockLimit.tooltip")))
                .binding(
                        64,
                        () -> config.invertedRopeLadderBlockLimit,
                        newValue -> config.invertedRopeLadderBlockLimit = newValue
                )
                .controller(option -> IntegerFieldControllerBuilder.create(option).range(2, 320))
                .build();

        /*
         * Mining Helmet
         */
        Option<Boolean> damageMiningHelmetOnUse = Option.<Boolean>createBuilder()
                .name(Text.translatable("configOption.coxinhautilities.damageMiningHelmetOnUse"))
                .description(OptionDescription.of(Text.translatable("configOption.coxinhautilities.damageMiningHelmetOnUse.tooltip")))
                .binding(
                        true,
                        () -> config.damageMiningHelmetOnUse,
                        newValue -> config.damageMiningHelmetOnUse = newValue
                )
                .controller(option -> BooleanControllerBuilder.create(option).yesNoFormatter().coloured(true))
                .build();

        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("itemGroup.miningutility.item_group"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("block.miningutility.rope_ladder"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("block.miningutility.rope_ladder"))

                                .option(ropeLadderBlockLimit)
                                .option(invertedRopeLadderBlockLimit)
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("item.miningutility.mining_helmet"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("item.miningutility.mining_helmet"))

                                .option(damageMiningHelmetOnUse)
                                .build())
                        .build())
                .save(() -> MiningUtility.saveConfig(config))
                .build()
                .generateScreen(parent);
    }

}