package com.you;

import java.io.File;

import org.bukkit.entity.Player;

import com.volmit.phantom.plugin.Module;
import com.volmit.phantom.plugin.Phantom;
import com.volmit.phantom.plugin.PhantomSender;
import com.volmit.phantom.plugin.SVC;
import com.volmit.phantom.plugin.Scaffold.Async;
import com.volmit.phantom.plugin.Scaffold.Command;
import com.volmit.phantom.plugin.Scaffold.ConsoleTest;
import com.volmit.phantom.plugin.Scaffold.Instance;
import com.volmit.phantom.plugin.Scaffold.ModuleInfo;
import com.volmit.phantom.plugin.Scaffold.PlayerTest;
import com.volmit.phantom.plugin.Scaffold.Start;
import com.volmit.phantom.plugin.Scaffold.Stop;
import com.volmit.phantom.plugin.Scaffold.Test;
import com.volmit.phantom.plugin.Scaffold.Tick;
import com.volmit.phantom.text.C;
import com.you.command.HelloCMD;
import com.you.services.DownloadSVC;
import com.you.services.DownloadSVC.DownloadJob;

// We define our module with some information
// Since there is no plugin yml

@ModuleInfo(name = "Coffee", // The plugin name
		version = "1.0", // The version
		author = "You", // The author
		color = C.GREEN) // Your plugin color (chat tag etc)
public class Coffee extends Module // Extend Module to be a module
{
	// Lets lazy-set an instance of our module
	@Instance
	public static Coffee instance;

	// We define a command class, look at the class
	// The "Hello" parameter is a "sub-tag". The command response
	// will look like [Coffee - Hello]: some msg
	@Command("Hello")
	public HelloCMD cmdHello;

	// We define a start method
	// This method is sync, and you can have multiple start methods
	@Start
	private void start()
	{
		// This is info logging
		l("start() Called");

		// We can obtain our job service (downloader) and queue a job.
		SVC.get(DownloadSVC.class).queue(new DownloadJob("https://google.com/", new File("google.html")));
	}

	@Async // Do it async
	@ConsoleTest // Host a test for consoles to use /test logging()
	public void logging(PhantomSender s)
	{
		l("Info");
		w("Warn");
		f("Fail");
	}

	@Test // Host a test for consoles and players as /test ping()
	public void ping(PhantomSender s)
	{
		s.sendMessage("Pong!");
	}

	@PlayerTest // Host a test for players only as /test kill()
	public void kill(Player s)
	{
		s.setHealth(0D);
	}

	@Async // Async
	@Start // Another start method
	private void startAsync()
	{
		l("startAsync() Called (main thread = " + Phantom.isMainThread() + ")");
	}

	@Stop // A stop tag
	private void stop()
	{
		l("stop() Called");
	}

	// Tick method. You can have multiple and use the @Async tag too.
	// The value is how long to delay in ticks
	// Range is 0-INF
	@Tick(20 * 60)
	private void tick()
	{
		l("tick() Called 1/minute");
	}
}
