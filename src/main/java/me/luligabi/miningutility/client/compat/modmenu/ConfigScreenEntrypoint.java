package me.luligabi.miningutility.client.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.OptionGroup;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.gui.controllers.BooleanController;
import dev.isxander.yacl.gui.controllers.string.number.IntegerFieldController;
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
        Option<Integer> ropeLadderBlockLimit = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.coxinhautilities.ropeLadderBlockLimit"))
                .tooltip(Text.translatable("configOption.coxinhautilities.ropeLadderBlockLimit.tooltip"))
                .binding(
                        64,
                        () -> config.ropeLadderBlockLimit,
                        newValue -> config.ropeLadderBlockLimit = newValue
                )
                .controller((intOption) -> new IntegerFieldController(intOption, 2, 320))
                .build();

        Option<Integer> invertedRopeLadderBlockLimit = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.coxinhautilities.invertedRopeLadderBlockLimit"))
                .tooltip(Text.translatable("configOption.coxinhautilities.invertedRopeLadderBlockLimit.tooltip"))
                .binding(
                        64,
                        () -> config.invertedRopeLadderBlockLimit,
                        newValue -> config.invertedRopeLadderBlockLimit = newValue
                )
                .controller((intOption) -> new IntegerFieldController(intOption, 2, 320))
                .build();

        /*
         * Mining Helmet
         */
        Option<Boolean> damageMiningHelmetOnUse = Option.createBuilder(Boolean.class)
                .name(Text.translatable("configOption.coxinhautilities.damageMiningHelmetOnUse"))
                .tooltip(Text.translatable("configOption.coxinhautilities.damageMiningHelmetOnUse.tooltip"))
                .binding(
                        true,
                        () -> config.damageMiningHelmetOnUse,
                        newValue -> config.damageMiningHelmetOnUse = newValue
                )
                .controller((booleanOption) -> new BooleanController(booleanOption, BooleanController.YES_NO_FORMATTER, true))
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