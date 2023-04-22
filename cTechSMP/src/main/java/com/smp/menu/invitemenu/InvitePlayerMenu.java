package com.smp.menu.invitemenu;

import com.ctechcore.CTechCore;
import com.ctechcore.menu.InventoryMenu;
import com.ctechcore.player.TechPlayer;
import com.ctechcore.teams.Team;
import com.smp.player.SMPPlayer;

public class InvitePlayerMenu extends InventoryMenu {
  public InvitePlayerMenu(Team team) {
    super(CTechCore.getInstance(), "Invite a player", 45);

    int slot = 0;
    for (TechPlayer techPlayer : getCore().getTechPlayerManager().getTechPlayers()) {
      if (techPlayer.getTeam() == Team.NONE) continue;
      addInventoryItem(slot, new PlayerItem(((SMPPlayer) techPlayer), team));
      slot++;
    }
  }
}
