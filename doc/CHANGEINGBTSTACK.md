**CHANGING** the Widcomm BT stack to Microsoft BT stack

[From <http://forum.eeeuser.com/viewtopic.php?id=38533>]

After making my post in the 1000H post I thought it probably best If it here as I don't see why it wouldn't work on the 901 models either...

Basically it's all to just to change from using the Widcomm stack for using bluetooth connections to the built in Windows stack, it's fairly simple to do and should be a performance boost as Widcomm runs fairly sloppy.

basically if you follow a guide similar to this: <http://www.x-setup.net/forum/showthread.php?p=4762>

Open file 'C:\Windows\inf\bth.inf' in notepad, (The inf folder is hidden by default so using Explorer go to Folder Options and change it to show all hidden folders)

(A good time to back up the bth.inf file if your worried about editing stuff wrongly but it's only like 3 lines worth)

Now you should have the section like this...

	[Manufacturer]
	%Microsoft%=Microsoft
	ALPS=ALPS, NT.5.1
	Belkin=Belkin, NT.5.1
	Brain Boxes=BrainBoxes, NT.5.1
	Broadcom=Broadcom, NT.5.1
	Cambridge Silicon Radio Ltd.=Cambridge, NT.5.1
	Dell=Dell, NT.5.1
	FIC=FIC, NT.5.1
	GVC=GVC, NT.5.1
	HP=HP, NT.5.1
	IBM=IBM, NT.5.1
	Microsoft=Microsoft, NT.5.1
	Motion Computing=MotionComputing, NT.5.1
	Silicon Wave=SiliconWave, NT.5.1
	Sony=Sony, NT.5.1
	TDK=TDK, NT.5.1
	TOSHIBA=Toshiba, NT.5.1
	Wistron NeWeb=Wistron, NT.5.1
	Zeevo=Zeevo, NT.5.1

Now add a line to the that section a line such as (without quotes of course):  'Azwave=Azwave, NT.5.1 '

There should then be sections below this one that starts with:

	;------------- Device section - Start -----------------------

Then insert a section that looks like this (Please note that you can check that the Vid and Pid are the same as your card by using device manage and looking at the properties of the device, and then the details tab, if not edit the following values, keeping the same format):

	[Azwave.NT.5.1]
	BT253=                                           BthUsb, USB\Vid_0b05&Pid_b700

Then save and close the notepad.

Then just either do a 'I'll choose from a list of drivers install' or if you haven't installed the drivers yet do a disable and enable of the bluetooth and it should install it automatically. The driver won't be signed so continue with it anyways.

I've now tested it with receiving a file from a phone and as such it seems to work absolutely fine.

Updated due to my own incompetence, don't worry this fix still works great.

Last edited by Sly (2008-08-30 8:51:54 pm)
