# SmartThings AverageThings
===========

##Overview:

These SmartApps have two primary uses.  The first is to create separate temperature and humidity tiles for multi-sensors.  This will allow an 
Aeon Lab Multi-Sensor to show the motion sensor on its tile while having its temperature and humidity mirrored to separate tiles.

The second use case is to calculate the average temperature and humidity for multiple sensors.  The average temperature tile could be used as 
an external temperature sensor for a thermostat SmartApp or it can be used to calculate the average of multiple outside temperature sensors.

These SmartApps rely on the Virtual Temperature Tile and Virtual Humidity Tile available at 
https://github.com/statusbits/smartthings/blob/master/VirtualThings/VirtualHumidityTile.device.groovy.

##Install:

1. Create new SmartApp at https://graph.api.smartthings.com/ide/apps with the entire contents of this file.
2. Create a new virtual humidity tile device based on https://github.com/statusbits/smartthings/blob/master/VirtualThings/VirtualHumidityTile.device.groovy
3. Install the newly created SmartApp in the SmartThings mobile application.
4. For target virtual humidity tile prompt, select the virtual humidity tile created in step 2.
5. Select one or more source humidity sensors.
6. Assign a name.
7. Tap done.