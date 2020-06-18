package com.github.erudosan05254.jp.tntprimed.api;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OnPrimedbyPlayer extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private String message;
	private boolean cancelled;

	public OnPrimedbyPlayer() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public boolean isCancelled() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void setCancelled(boolean arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public HandlerList getHandlers() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
