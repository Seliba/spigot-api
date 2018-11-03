package de.seliba.api;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardAPI {
	
	private Scoreboard scoreboard;
	
	public ScoreboardAPI(String displayname, List<String> content) {
		scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
		Objective obj = scoreboard.registerNewObjective("ABC", "ABC", "ABC");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		int size = content.size();
		for(int i = 0; i < content.size(); i++) {
			obj.getScore(content.get(i)).setScore(size - i);
		}
	}
	
	public Scoreboard getScoreboard() {
		return scoreboard;
	}

}