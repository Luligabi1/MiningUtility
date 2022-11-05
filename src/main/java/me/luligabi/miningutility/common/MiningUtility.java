package me.luligabi.miningutility.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import me.luligabi.miningutility.common.block.BlockRegistry;
import me.luligabi.miningutility.common.item.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class MiningUtility implements ModInitializer {

    @Override
    public void onInitialize() {
        BlockRegistry.init();
        ItemRegistry.init();
    }


    private static ModConfig createConfig() {
        ModConfig finalConfig;
        LOGGER.info("Trying to read config file...");
        try {
            if(CONFIG_FILE.createNewFile()) {
                LOGGER.info("No config file found, creating a new one...");
                writeConfig(GSON.toJson(JsonParser.parseString(GSON.toJson(new ModConfig()))));
                finalConfig = new ModConfig();
                LOGGER.info("Successfully created default config file.");
            } else {
                LOGGER.info("A config file was found, loading it..");
                finalConfig = GSON.fromJson(new String(Files.readAllBytes(CONFIG_FILE.toPath())), ModConfig.class);
                if(finalConfig == null) {
                    throw new NullPointerException("The config file was empty.");
                } else {
                    LOGGER.info("Successfully loaded config file.");
                }
            }
        } catch(Exception e) {
            LOGGER.error("There was an error creating/loading the config file!", e);
            finalConfig = new ModConfig();
            LOGGER.warn("Defaulting to original config.");
        }
        return finalConfig;
    }

    public static void saveConfig(ModConfig modConfig) {
        try {
            writeConfig(GSON.toJson(JsonParser.parseString(GSON.toJson(modConfig))));
            LOGGER.info("Saved new config file.");
        } catch(Exception e) {
            LOGGER.error("There was an error saving the config file!", e);
        }
    }

    private static void writeConfig(String json) {
        try(PrintWriter printWriter = new PrintWriter(CONFIG_FILE)) {
            printWriter.write(json);
            printWriter.flush();
        } catch(IOException e) {
            LOGGER.error("Failed to write config file", e);
        }
    }

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
                    new Identifier(MiningUtility.MOD_ID, "item_group"))
            .icon(() -> new ItemStack(ItemRegistry.ESCAPE_ROPE_ITEM))
            .build();

    public static final String MOD_ID = "miningutility";

    public static Identifier modId(String identifier) {
        return new Identifier(MOD_ID, identifier);
    }

    public static final Logger LOGGER;
    private static final Gson GSON;

    private static final File CONFIG_FILE;
    public static final ModConfig CONFIG;

    static {
        LOGGER = LoggerFactory.getLogger("Mining Utility");
        GSON = new GsonBuilder().setPrettyPrinting().create();

        CONFIG_FILE = new File(String.format("%s%selementalcreepers.json", FabricLoader.getInstance().getConfigDir(), File.separator));
        CONFIG = createConfig();
    }
}
