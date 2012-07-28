MobGod
============

Version: 0.1
CraftBukkit Build 1.2.5-R4.0 [Tested]

Info
Info This plugin lets you lets you give godmode to mobs such as villagers or any mob such as sheep by right clicking them using a predefined block in config, the default being set with redstone ore, but can be changed to any block or item. The plugin will save all the mobs in god to a the file "MobsinGod.bin" Located in the MobGod folder every time the plugin is disabled so it can be reloaded or restarted and mobs will still be in god. If a player tried to hit the mob then they will receive a custom notification from config which can also be disabled. This is useful if you have villagers that you do not want being killed or just have a farm for display with animals you do not wish to be killed.

 Download

Features:
Giving Godmode to mobs
Custom notifications from config
Custom God giver tool
Saving and loading of the mobs in God
Metrics
Config
MobGod:
  tool: '73' //The id of the  tool used for giving god
Notify:
  NotifyPlayers: true // When true players are alerted when hitting a mob in God
  NotifyMessage: This mob is in god by %owner%  //The message with %owner% being the person who made it god
Permissions
mobgod.god - Permission to give the mob god


When you try and attack a mob in God nothing happens except being shown the message if it is enabled in config.

Changelog
- v0.1

First release