package com.smp.menu.groupmenu;

import com.ctechcore.CTechCore;
import com.ctechcore.menu.InventoryMenu;
import com.smp.player.SMPPlayer;

public class GroupInventory extends InventoryMenu {

  private final SMPPlayer smpPlayer;

  public GroupInventory(CTechCore core, String name, int slots, SMPPlayer smpPlayer) {
    super(core, name, slots);

    this.smpPlayer = smpPlayer;
  }
}
