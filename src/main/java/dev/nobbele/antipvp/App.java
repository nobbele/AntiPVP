package dev.nobbele.antipvp;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class App extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        boolean directHit = event.getEntityType() == EntityType.PLAYER && event.getDamager().getType() == EntityType.PLAYER;

        boolean indirectHit = false;
        if (event.getEntityType() == EntityType.PLAYER && event.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow)event.getDamager();
            if(arrow.getShooter() instanceof Player) {
                indirectHit = true;
            }
        }

        if (directHit || indirectHit) {
            event.setDamage(0);
        }
    }
}