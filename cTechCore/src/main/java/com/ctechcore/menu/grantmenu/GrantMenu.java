package com.ctechcore.menu.grantmenu;

import com.ctechcore.CTechCore;
import com.ctechcore.menu.InventoryMenu;
import com.ctechcore.rank.Rank;
import org.bukkit.entity.Player;

public class GrantMenu extends InventoryMenu {

  public GrantMenu(CTechCore core, Player player) {
    super(core, "Grant a Rank", 27);

    addInventoryItem(9, new GrantRankItem(player, Rank.COUNCIL));
    addInventoryItem(11, new GrantRankItem(player, Rank.JRCOUNCIL));
    addInventoryItem(17, new GrantRankItem(player, Rank.NONE));

  }



}
