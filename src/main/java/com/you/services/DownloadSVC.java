package com.you.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.volmit.phantom.lang.io.VIO;
import com.volmit.phantom.plugin.Job;
import com.volmit.phantom.plugin.JobService;
import com.you.services.DownloadSVC.DownloadJob;

// This is a job service
// This service allows you to queue custom jobs into a running service thread
public class DownloadSVC extends JobService<DownloadJob> // Our job class
{
	// We define our download job class here
	public static class DownloadJob extends Job
	{
		private URL url;
		private File destination;

		// Take down the details
		public DownloadJob(String url, File destination)
		{
			try
			{
				this.url = new URL(url);
			}

			catch(MalformedURLException e)
			{
				throw new RuntimeException(e);
			}

			this.destination = destination;
		}

		// Its time to do some work
		public void run()
		{
			try
			{
				InputStream in = url.openStream();

				try
				{
					destination.getParentFile().mkdirs();
				}

				catch(Throwable e)
				{

				}

				FileOutputStream fos = new FileOutputStream(destination);
				byte[] buf = new byte[8192];

				// Always check this, the service thread might me trying to shut down
				// And especially downloads, should not hold up a shutdown
				while(shouldContinueWorking())
				{
					if(VIO.transfer(in, fos, buf) == -1)
					{
						break;
					}
				}

				in.close();
				fos.close();
			}

			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	// Thats it... You can download shit like:
	// SVC.get(DownloadSVC.class)
	//     .queue(new DownloadJob("https://google.com/", new File("google.html")));
}
