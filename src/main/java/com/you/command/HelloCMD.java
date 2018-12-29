package com.you.command;

import com.volmit.phantom.plugin.PhantomCommand;
import com.volmit.phantom.plugin.PhantomSender;
import com.volmit.phantom.plugin.Scaffold.Command;

// This is a phantom command
public class HelloCMD extends PhantomCommand
{
	// Here we define our sub command
	@Command
	private HelloSubCMD cmdHelloSub;

	public HelloCMD()
	{
		// The first parameter is the main command name
		// The rest are aliases
		super("hello", "helloalias", "helloalias2");

		// Since we defined this class in Coffee.java, it can be invoked with /hello
	}

	public boolean handle(PhantomSender sender, String[] args)
	{
		// The phantom sender already prefixes your messages with your plugin tag
		sender.sendMessage("Hello! Your args were " + args.toString());
		sender.sendMessage("Try /hello sub");

		return true;
	}
}
