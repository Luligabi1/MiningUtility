package me.luligabi.miningutility.common;

import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.OptionGroup;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.gui.controllers.BooleanController;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfig {

    public int ropeLadderBlockLimit = 64;
    public int invertedRopeLadderBlockLimit = 64;

    public boolean damageMiningHelmetOnUse = true;

    public Screen createGui(Screen parent) {
        /*
         * Rope Ladders
         */
        Option<Integer> ropeLadderBlockLimit = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.coxinhautilities.ropeLadderBlockLimit"))
                .tooltip(Text.translatable("configOption.coxinhautilities.ropeLadderBlockLimit.tooltip"))
                .binding(
                        64,
                        () -> this.ropeLadderBlockLimit,
                        newValue -> this.ropeLadderBlockLimit = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 320, 2))
                .build();

        Option<Integer> invertedRopeLadderBlockLimit = Option.createBuilder(Integer.class)
                .name(Text.translatable("configOption.coxinhautilities.invertedRopeLadderBlockLimit"))
                .tooltip(Text.translatable("configOption.coxinhautilities.invertedRopeLadderBlockLimit.tooltip"))
                .binding(
                        64,
                        () -> this.invertedRopeLadderBlockLimit,
                        newValue -> this.invertedRopeLadderBlockLimit = newValue
                )
                .controller((intOption) -> new IntegerSliderController(intOption, 2, 320, 2))
                .build();

        /*
         * Mining Helmet
         */
        Option<Boolean> damageMiningHelmetOnUse = Option.createBuilder(Boolean.class)
                .name(Text.translatable("configOption.coxinhautilities.damageMiningHelmetOnUse"))
                .tooltip(Text.translatable("configOption.coxinhautilities.damageMiningHelmetOnUse.tooltip"))
                .binding(
                        true,
                        () -> this.damageMiningHelmetOnUse,
                        newValue -> this.damageMiningHelmetOnUse = newValue
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
                .save(() -> MiningUtility.saveConfig(this))
                .build()
                .generateScreen(parent);
    }
}
