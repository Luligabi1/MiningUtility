package me.luligabi.miningutility.common;

import dev.architectury.platform.Platform;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.autogen.IntSlider;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;

public class ModConfig {

    public static final ConfigClassHandler<ModConfig> HANDLER = ConfigClassHandler.createBuilder(ModConfig.class)
        .id(MiningUtility.id("config"))
        .serializer(config -> GsonConfigSerializerBuilder.create(config)
            .setPath(Platform.getConfigFolder().resolve("miningutility.json"))
            .build())
        .build();


    @AutoGen(category = "rope_ladder", group = "rope_ladder")
    @IntSlider(min = 1, max = 320, step = 1)
    @SerialEntry
    public int ropeLadderBlockLimit = 64;
    @AutoGen(category = "rope_ladder", group = "inverted_rope_ladder")
    @IntSlider(min = 1, max = 320, step = 1)
    @SerialEntry
    public int invertedRopeLadderBlockLimit = 64;

    @AutoGen(category = "mining_helmet", group = "mining_helmet")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean damageMiningHelmetOnUse = true;

}