package com.ctechcore.kits;

import com.ctechcore.kits.types.Charged;
import com.ctechcore.kits.types.Interact;
import com.ctechcore.kits.types.Passive;
import com.ctechcore.player.TechPlayer;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public abstract class Kit {

  private final TechPlayer techPlayer;
  private final List<Ability> abilities;

  public Kit(TechPlayer techPlayer, List<Ability> abilities) {
    this.techPlayer = techPlayer;
    this.abilities = abilities;
  }

  public abstract String getName();
  public abstract List<String> getDescription();
  public abstract ItemStack getItem();
  public abstract ItemStack[] getArmor();

  public TechPlayer getTechPlayer() {
    return techPlayer;
  }

  public List<Ability> getAbilities() {
    return abilities;
  }

  public void addAbility(Ability ability) {
    this.abilities.add(ability);
  }

  public void removeAbility(Ability ability) {
    this.abilities.remove(ability);
  }

  /**
   * For when the game starts
   */
  public void equip() {
    PlayerInventory inv = getTechPlayer().getPlayer().getInventory();
    inv.addItem(getItem());
    inv.setArmorContents(getArmor());
    for (Ability ability : getAbilities()) {
      if (ability instanceof Interact) ((Interact) ability).register();
      if (ability instanceof Charged) ((Charged) ability).start();
      if (ability instanceof Passive) ability.doAbility();
    }
  }

  /**
   * For when the game ends or the kit is unequipped (Lobby Kit)
   */
  public void unEquip() {
    getTechPlayer().getPlayer().getInventory().clear();

    for (Ability ability : getAbilities()) {
      if (ability instanceof Interact) ((Interact) ability).unregister();
      if (ability instanceof Charged) ((Charged) ability).cancel();
    }
  }

  public String getSelectMessage() {
    return String.format("%sSkill> %sYou equipped: %s%s%s kit!", ChatColor.BLUE, ChatColor.GRAY, ChatColor.GREEN, getName(), ChatColor.GRAY);
  }

}
