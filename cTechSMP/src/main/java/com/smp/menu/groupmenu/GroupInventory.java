package com.smp.menu.groupmenu;

import com.ctechcore.CTechCore;
import com.ctechcore.menu.InventoryMenu;
import com.smp.player.SMPPlayer;

public class GroupInventory extends InventoryMenu {

  private final SMPPlayer smpPlayer;

  public GroupInventory(SMPPlayer smpPlayer) {
    super(CTechCore.getInstance(), smpPlayer.getTeam().getName(), 45);

    this.smpPlayer = smpPlayer;
    addInventoryItem(0, new ModifyColorItem(smpPlayer.getTeam()));
    addInventoryItem(8, new DeleteGroupItem(smpPlayer.getTeam()));
  }
}
