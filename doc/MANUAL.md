
wiiscan(1)


## NAME ##


wiiscan - a connection utility for wii console remotes


## SYNOPSIS ##
       wiiscan  <-a  <device> | -c <device> | -d <device> | -r | -s | -usbup |
       -usbdown> > [-cf <file>] [-lf <file>] [-b  <sleep>]  [-t  <sleep>]  [-u
       <sleep>]  [-p  <sleep>]  [-w <sleep>] [-q <usbradio>] [-f <removemode>]
       [-m <powermode>] [-l <wiilibrary>] [-wb] [-v]


## OVERVIEW ##
       wiiscan is a canvas function for a number  of  different  scanning  and
       connection utilities. It can detect build-in bluetooth radios, scan for
       nearby bluetooth devices, connect to a specific device and remove  that
       device again from the hardware.

       The main feature of wiiscan is to automatically connect to a wii remote
       (wiimote). This can be quiet cumbersome on a Windows  system,  and  the
       nesseccary steps for doing a robust, working connection is done by


       Delete wiimote hardware HID bluetooth entry:
               delete old entries of the wiimote in the bluetooth hardware. On
               some windows system the wiimote is readily detected, after  the
               first  manually  installation.  Pressing  the  "1-2"  makes the
               wiimote discoverable. On other systems  (including  mine),  any
               attempt  to connect to the wiimote, after one successful or un-
               successful connection attempt, will always fail!  Removing  the
               HID  and wiimote registry entries before the next attempt cures
               this feature.


       Cycle USB bus:
               the wiimote can be switched automatically to discoverable mode,
               if  the  power is briefly cut from the device. This can be done
               on a wiimote powered by the USB +5 volt (with a proper  voltage
               regulator to bring it under +3 volt). This step hence turns the
               USB hub off, killing the power, and  turns  it  on  again.  The
               "1-2" buttons on the wiimote must be pressed at all times, done
               by say some tape or gaffa!.


       Scan for wiimote:
               now comes the main part  of  the  connection;  scanning  for  a
               wiimote.  This includes bringing up the bluetooth radio device,
               initialize seek parameters, scanning and matching for a  device
               name or address, connecting to a matched device, installing the
               HID interface (that was removed in the first step), and finally
               trying  to  open the wiimote and reading some data from it. All
               steps involve a time variable, meaning that say installing  the
               HID  causes some windows registry fiddling, that takes a rather
               variable amount of time, and the next step is critically depen‐
               dent  on the former step to be finished. Hence a number of tun‐
               able waiting variable is introduced, Important is to being able
               to  reach the final connection step before the wiimote goes out
               of the discoverable mode and automatically turns off.


       A note on USB voltage cycling:
               This software solution is able to restart the wiimote  in  dis‐
               coverable mode automatically. But this require that the wiimote
               is powered by a USB cable to the PC and that the  "1-2"  button
               combination  is  permanently pressed. Cycling the power for the
               wiimote with the "1-2" buttons pressed enables the discoverable
               mode,  and  the  continuously pressing "1-2" does not interfere
               with wiimote operations hereafter.

               So this is a non-intrusive fix to the so called  "Ladder  prob‐
               lem" (http:wyxs.net/web/wiimote/digital_whiteboard.html).

               Power  control over USB hubs is dependent on the particular hub
               devices, and disabling the power may not be possible for a sin‐
               gle  USB  port  only. wiiscan uses a trick of disabling all USB
               hubs and then only enable and disable a singe hub.

               Disabling all hubs is a precondition to running wiiscan and  it
               can be accomblised by either going into "Control Panel | System
               | Hardware | Device manager", and manually disable all USB hubs
               or by using "devcon", say (careful, these commands applies only
               to my system)

                 devcon           disable            "@PCIN_8086&DEV_293*&SUB‐
               SYS_20F117AA&REV_033&B1BfB6*"

                 devcon            disable           "@PCIN_8086&DEV_293*&SUB‐
               SYS_20F017AA&REV_033&B1BfB6*"

               being careful that the pattern matches only our USB hubs!.  You
               can test this by

                 devcon            status            "@PCIN_8086&DEV_293*&SUB‐
               SYS_20F117AA&REV_033&B1BfB6*"

                 devcon            status            "@PCIN_8086&DEV_293*&SUB‐
               SYS_20F017AA&REV_033&B1BfB6*"

               A single USB now may be enabled or disabled by

                 devcon disable @PCIN_8086*DEV_2934*SUBSYS_20F017AA*

               again with the particular address for your system as a variable
               you need to lookup. The voltage on  the  USB  bus  can  now  be
               checked     Using     a     voltmeter     (see    details    in
               http:wyxs.net/web/wiimote/digital_whiteboard.html).


       NOTE: The configuration file of wiiscan contains the particular  system
       dependent USB hub pattern matches.


       NOTE: be careful of connecting other devices to the USB bus, since  the
       power cycle may cause severe interfere with the device.


## DESCRIPTION ##
       -a <device>
               auto-connect to a device.


       -c <device>
               connects to a device.


       -d <device>
               deletes a device, clears HID and bluetooth registry entries.


       -r      looks for active internal bluetooth radio devices.


       -s      scans for external bluetooth devices.


       -usbup  turn the USB hub on.


       -usbdown
               turn the USB hub off.


       Default mode: wiiscan -a " Nintendo RVL-CNT-01"


       Note: "nintendo" is a shortcut for "Nintendo RVL-CNT-01"


## OPTIONS ##
       -cf <file>
               load a specific configuration file.


       -lf <file>
               specify a distinct logfile.


       -b <sleep>
               auto-mode bluetooth connection sleep in milliseconds.


       -t <sleep>
               bluetooth scanning interval in milliseconds.


       -u <sleep>
               auto-mode USB connection sleep in milliseconds.


       -p <sleep>
               automode usbm post-connection sleep in milliseconds.


       -w <sleep>
               timeout for wiimote in milliseconds.


       -q <usbradio>
               use bluetooth radio with this address. Note, this functionality
               is not working yet.


       -f <removemode>
               pre-remove   mode  of  device,  0=remove  if  not  connectable,
               1=always remove, 2=never remove


       -m <powermode>
               choose USB powercycle mode, 0=no power cycle,  1=use  USB  hub,
               2=use USBm IO hardware, 3=use USB Delcon IO hardware.


       -l <wiilibrary>
               use    specific    wiimote   library,   lib   can   be   one-of
               (wiiuse,wiimotelib).


       -y      scan retries in automode.


       -wb     start whiteboard in auto-mode


       -v      enable extra debugging printouts


## FILES ##
       wiiscan looks for a file names wiiscan.ini when executing in the  auto‐
       mode. See detail in the file.

## TIMING ##
       Various  timing  parametes  can  be  set  on the command line or in the
       inifile. The process of connecting is


       1: USB power down


       2: Delete old HID entries


       3: Sleep at least option_usbsleep (from 1) before commencing


       4: USB power up


       5: Sleep at least option_usbmsleep (from 3) before commencing. Sleep is
       only nesseccary for USBmicro devices, since step 4 is much faster using
       this device compared to the USB hub.


       6: Find and  wiimote  and  connect.  Scan  bluetooth  the  duration  in
       option_timeout.


       7: Sleep option_btsleep before commencing; waiting for HID entries  and
       new-hardware-found to finish in windows.


       8: Connect to  the  wiimote,  Scan  for  wiimotes  using  the  duration
       option_wiitimeout.


## EXAMPLE ##
       Scanning for devices nearby:

         wiiscan -s

       Auto-connect to a nintendo device, scan  bluetooth  for  four  seconds,
       verbose  on, and enable start of whiteboard software after a successful
       connection

         wiiscan -a nintendo -t 4000 -v -wb

       Cycle USB bus voltage

         wiiscan -usbdown

         wiiscan -usbup


## COMPILING ##
       The source code can  be  compiled  with  MS  Visual  C++  Express  2008
       (http:www.microsoft.com/express/vc/)  or  similar. It also needs wiiuse
       dlls (http:www.wiiuse.net/). If wiiuse is to be compiled by it self  it
       needs  Windows  SDK  and  DDK, but running wiiscan with just the wiiuse
       binaries is the easiest option.

       It does not work on Windows 2000, and has not been tried out on a Vista
       system.


## TESTED SYSTEMS ##
       The  wiiscan has been tested on a Lenovo Thinkpad R500, XP professional
       (without build-in bluetooth) with a Trendnet TBW-102UB  bluetooth  don‐
       gle, and on a ASUS eeePC 1000H with XP home.

       Only the MS bluetooth stack was tested.


       Lenovo setup
               Windows XP professional, version 2002, SP2 USB dongle: Trendnet
               TBW-102UB  bluetooth(Broadcom  Ultimate  Low   Cost   Bluetooth
               2.0+EDR  USB),  date 24-02-2004, driver 5.1.2535.0 Microsoft BT
               stack: date 03-08-2004, driver 5.1.2600.2180


       eee setup
               Windows XP home, version 2002, SP3 USB dongle:  buildin  Azware
               BT252,  date  13-04-2008,  driver  5.1.2600.5512  Microsoft  BT
               stack: date 13-04-2008, driver 5.1.2600.5512


## BUGS ##
       1: restart pop-up (FIXED)
               Installing new hardware causes windows to require restart. Hap‐
               pens  once  in  a while, balloon pop-ups reports hardware, that
               where installed but not  working  properly.  A  restart  pop-up
               wants  to  reboot the PC. Small fix: just delete the device and
               re-run "wiiscan -c nintendo".

               FIX: the 'Change of systemsettings ... Do you  want  to  reboot
               now?' popup, happens only when the program runs for longer than
               19 seconds. Keeping the connection time within 19 sec seems  to
               cure the problem, Adding a sleep at the end of the program, say
               Sleep(20000) will eventually bring up  the  dialog  again.  The
               dialog can anyway safly be ignored!


       2: discoverable mode fast shutdown (FIXED)
               Sometimes the wiimote goes quickly out of discoverable mode, it
               takes it only about 3 seconds from turn-on  to  turn-off.  This
               makes  it  hard  to obtain a connection to it. Both my wiimotes
               does this once in a while, after failed connection attempts.

               Pressing one button only "1" or "2" makes the wiimote blink for
               a short time, but it is not really discoverable.

               FIX:  Initialization  of  wiilib  has been rewritten, making it
               recall the HID  inialization  routine.  Code  for  testing  the
               wiimote connection has also been introduced.


       3: buttons not working (OPEN)
               Pressing  the 1-2 button combination sometimes fails to turn-on
               the wiimote, pressing sync or power makes it  work  again.  The
               the  "1-2  button  freeze"  happens  after  a failed connection
               attempt. See also bug-2.


       4: radio null (OPEN)
               Sometimes the BT  radio  fails  to  reinitialize  after  a  USB
               down/up     flip,     this    means    that    "RadioInfo(time‐
               out,true,dbg)"return NULL. Can be fixed by  placing  the  blue‐
               tooth radio device on another bus than the USB.


       5: keep blinking (OPEN)
               Sometimes  the  wiimote is found and connected OK, but the LEDs
               keeps blinking (normal connect mode:  LEDs  are  turned  perma‐
               nently  on).  This  does however not affect connectability, and
               the wiimote does not turn off again automatically.


       6: failed to find wiimote (OPEN)
               Wiimote failed to find devices. This may be a  non-fatal  error
               or   an   error   caused   by   an   undervolted  wiimote.  The
               "wiiuse_find(0x0,4,2/4/6)" keeps returning 0.


       7: remove failed (OPEN)
               Sometimes the remove steps fails, but this may be non-fatal

                Removing device <Nintendo RVL-CNT-01>
                ** error: failed to find device
                Done [FAILED]


       8; balloon-tips (FIXED)
               Balloon-tips are annoying when connecting new  hardware.  Small
               fix: do

                Windows Registry Editor Version 5.00

                [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVer‐
               sion\Explorer1]
                "EnableBalloonTips"=dword:00000000


       9: double delete (FIXED)
               Double delete of nintendo device may cause  BluetoothFindFirst‐
               Device()  to return null Fixed by removing the throw, replacing
               it with a "if (hbf()==NULL) then return false"


       10: BSoD (OPEN)
               The "Blue Screen of Death" was encountered a number  of  times,
               indicating  a errorneous device driver. The cause may be in the
               MS bluetooth stack or  in  the  wiiuse  lib.  The  BSoD  mainly
               occured in the first phase of this project and havent been seen
               for a while with the current version (v0.7).

               Only happens for  MS  bluetooth  stack  version  "Microsoft  BT
               stack: date 03-08-2004, driver 5.1.2600.2180" (Lenovo version).


       11: New Hardware found wizard (OPEN)
               Sometimes the wizard appears out of the blue, when deleting the
               HID entry and trying to reinstall it. It is the call Bluetooth‐
               SetServiceState(...) that messes the  Window  system  up.  Dis‐
               abling  the wizard or disabling the Plug and play system do not
               seem to be an option, since the HID then newer  get  installed,
               and  ends up in a failty state. A reboot of the system does not
               cure this defect.


       12: Devcon hangs (FIXED)
               The devcon commands sometimes hangs at the diable USB  command.
               The  state of the USB controllers, and BT devices are undefined
               (some BT devices disabled, others not) and manually  trying  to
               disable  or  enable  the USB constroller fails. A status on the
               USB device gives a strange result.

               FIX: reboot the machine.


       13: Open Device fails to find a Nintendo (PARTLY FIXED)
               When opening  a  named  nintendo  device,  say  'Nintendo  RVL-
               CNT-01',  the  bluetooth fails to get the name from the device.
               This results in an empty name, and hence matching on  the  name
               fails.

               FIX:  use  a  device  adresss  instead  of  a  name.  Put,  say
               'allowed_wiimote_adr=00:19:FD:CC:60:61  00:19:1D:D6:65:E5'   in
               the ini file.


       14: Wiiscan fails to run (FIXED)
               Running wiiscan in a dosbox makes it terminate immidiatly. Run‐
               ning the tray version, or under MS VS Express causes it to dis‐
               play an missing DLL dialog (msvcp90.dll and friends).

               FIX:  this  is  a  MS  bug,  but can be fixed by setting the MT
               library in the project setting to use static libraries  instead
               of dynamic DLLs.


       15: BluetoothFindFirstDevice() stalls (FIXED)
               The BluetoothFindFirstDevice() sometimes newer returns. Happens
               only for MS stack version "Microsoft BT stack: date 13-04-2008,
               driver 5.1.2600.5512" (eee stack).

               Code stalls here:

               DeviceAutoClose<HBLUETOOTH_DEVICE_FIND,BOOL> hbf(BluetoothFind‐
               FirstDevice(&bdsp,&bdi),&BluetoothFindDeviceClose);

               FIX: start the BluetoothFindFirstDevice() function in a thread,
               terminate the thread if it has run for longer than, say 2+time‐
               out.


       16: Wiimote drops connection after scanning (PARTLY FIXED)
               If a connection attempt goes well until the last  part,  it  is
               most likely due to low-power batteries. The final stage of con‐
               necting draw extra power, that might cause the wiimote to shut‐
               down.

## SEE ALSO ##
wiiuse

	Wiiuse  is  a library written in C that connects with several Nintendo Wii remotes. Supports  motion  sensing,  IR  tracking, nunchuk,  classic controller, and the Guitar Hero 3 controller. Single threaded and nonblocking makes a light weight and  clean API. Licensed  under  GNU  GPLv3  and GNU LGPLv3 (non-commercial) by Michael Laforest (<http:www.wiiuse.net/>)

Wiimote Whiteboard

	Whiteboard software by Johnny Chung Lee.
	
	(http:www.cs.cmu.edu/~johnny/projects/wii/,
	http:www.wiimoteproject.com/)

devcon(1)

	   USB management software.
	
	   (http:support.microsoft.com/kb/311272)


## VERSION ##
       Version 0.9 NDEBUG


## AUTHOR ##
       Carsten  Frigaard,  Mergeit  ApS,  Kongsvang  Allé 37, DK-8000 Århus C,
       www.mergit.dk


## COPYRIGHT ##
       Copyright © 2009 MergeIt, Aps. License LGPL3 : GNU lesser GPL,  version
       3, <http:www.gnu.org/licenses/lgpl.txt>. This is free software: you are
       free to change and redistribute it. There is NO WARRANTY, to the extent
       permitted by law.



26 Mar 2009                       wiiscan(1)
