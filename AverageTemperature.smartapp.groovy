/**
 *  AverageTemperature.smartapp.groovy
 *  Average Temperature
 *
 *  Author: Brandon Gordon
 *  Code:   https://github.com/notoriousbdg/smartthings/blob/master/AverageTemperature.smartapp.groovy
 *  Date:   2014-10-13
 *
 *  Copyright 2014 Brandon Gordon
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 */

/**
 *  App Name:   Average Temperature
 *
 *  Author: 	Brandon Gordon
 *  Date:       2014-10-13
 *  Version: 	0.1
 *                  * Created
 *
 *  Overview:
 *
 *  Use this SmartApp to average the temperature from one or more temperature sensors to a Virtual Temperature Tile.
 *  If a single source sensor is specified, then the virtual tile will mirror the source sensor, which can be 
 *  useful to create a dedicated tile for a multi-sensor.  A Virtual Temperature Tile must be created from 
 *  https://github.com/statusbits/smartthings/blob/master/VirtualThings/VirtualTemperatureTile.device.groovy 
 *  device type to serve as the target Virtual Temperature Tile.
 *
 *  Install:
 *
 *  1. Create new SmartApp at https://graph.api.smartthings.com/ide/apps with the entire contents of this file.
 *  2. Create a new virtual temperature tile device based on https://github.com/statusbits/smartthings/blob/master/VirtualThings/VirtualTemperatureTile.device.groovy
 *  3. Install the newly created SmartApp in the SmartThings mobile application.
 *  4. For target virtual temperature tile prompt, select the virtual temperature tile created in step 2.
 *  5. Select one or more source temperature sensors.
 *  6. Assign a name.
 *  7. Tap done.
 */

definition(
    name: "Average Temperature",
    namespace: "notoriousbdg",
    author: "Brandon Gordon",
    description: "SmartApp to average the temperature from one or more temperature sensors to a Virtual Temperature Tile.  If a single source sensor is specified, then the virtual tile will mirror the source sensor, which can be useful to create a dedicated tile for a multi-sensor.  A Virtual Temperature Tile must be created from https://github.com/statusbits/smartthings/blob/master/VirtualThings/VirtualTemperatureTile.device.groovy device type to serve as the target Virtual Temperature Tile.",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
    section("Choose a target virtual temperature tile... "){
        input "target", "capability.temperatureMeasurement", title: "Tile"
    }
    section("Choose a source temperature sensors... "){
        input "sensors", "capability.temperatureMeasurement", title: "Sensors", multiple: true
    }
}

def installed() {
    initialize()
}

def updated() {
    unsubscribe()
    initialize()
}

def initialize() {
    def sum     = 0
    def count   = 0
    def average = 0

    subscribe(sensors, "temperature", temperatureHandler)

    for (sensor in sensors) {
        count += 1
        sum   += sensor.currentTemperature
    }

    average = sum/count

    settings.target.parse("temperature: ${average}")
}