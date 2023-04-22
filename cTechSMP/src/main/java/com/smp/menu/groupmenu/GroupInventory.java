package com.smp.menu.groupmenu;

import com.ctechcore.CTechCore;
import com.ctechcore.menu.InventoryMenu;
import com.ctechcore.player.TechPlayer;
import com.smp.player.SMPPlayer;

public class GroupInventory extends InventoryMenu {

  private final SMPPlayer smpPlayer;

  public GroupInventory(SMPPlayer smpPlayer) {
    super(CTechCore.getInstance(), smpPlayer.getTeam().getName(), 45);

    this.smpPlayer = smpPlayer;
    addInventoryItem(0, new ModifyColorItem(smpPlayer.getTeam()));
    addInventoryItem(4, new InvitePlayerItem(smpPlayer.getTeam()));
    addInventoryItem(8, new DeleteGroupItem(smpPlayer.getTeam()));
    int slot = 18;
    for (TechPlayer techPlayer : smpPlayer.getTeam().getPlayers()) {
      addInventoryItem(slot, new KickPlayerItem(((SMPPlayer) techPlayer), smpPlayer.getTeam()));
      slot++;
    }
  }
}
