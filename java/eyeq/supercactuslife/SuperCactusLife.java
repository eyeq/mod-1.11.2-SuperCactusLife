package eyeq.supercactuslife;

import eyeq.supercactuslife.event.SuperCactusLifeEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static eyeq.supercactuslife.SuperCactusLife.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class SuperCactusLife {
    public static final String MOD_ID = "eyeq_supercactuslife";

    @Mod.Instance(MOD_ID)
    public static SuperCactusLife instance;

    public static float prob;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new SuperCactusLifeEventHandler());
        load(new Configuration(event.getSuggestedConfigurationFile()));
    }

    public static void load(Configuration config) {
        config.load();

        String category = "Float";
        prob = (float) config.get(category, "probDenominator", 0.1).getDouble();

        if(config.hasChanged()) {
            config.save();
        }
    }
}
