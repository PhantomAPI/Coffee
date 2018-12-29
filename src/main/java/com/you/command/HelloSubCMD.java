package com.you.command;

import com.volmit.phantom.plugin.PhantomCommand;
import com.volmit.phantom.plugin.PhantomSender;

// This is a subcommand example
// This command is referenced in HelloCMD
// So it cannot be used like /sub.
// It can only be used with /hello sub
public class HelloSubCMD extends PhantomCommand
{
	public HelloSubCMD()
	{
		// See HelloCMD for more here
		super("sub", "subalias2", "subalias3");
	}

	public boolean handle(PhantomSender sender, String[] args)
	{
		// See HelloCMD for more here
		sender.sendMessage("Hello sub alias! Your args were " + args.toString());

		return true;
	}
}
