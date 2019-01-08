# PacmanNavigation![200px-pac_man svg](https://user-images.githubusercontent.com/44754325/49365224-e9cd5f00-f6ee-11e8-8a7b-4521bce0280f.png)

## Descreption

The project represents a game called Pacman Navigation, the game is performed on img of Google Earth. The course of the game: Choose a map location for Pacmans and fruits, then the goal of the Pacmans to eat the fruits in the shortest time (in terms of distance), the end of the game:
Will be displayed on the map any track that Packman has made
There is an example below the map.

There are two game options:
- Play independently with your mouse.
- Automatic play with algorithm

### Algorithms 
In the algorithm package there is an algorithms:
- Algorithm - An algorithm that calculates the shortest path between a fruit and a player, and returns the path.
(The algorithm knows how to avoid boxes that can not be passed through) .

### Coords 
The Coords package has the:
 #### mycoords class
  Represents a Class that allows us to perform calculations between GPS points such as:
  - Move a point with a vector
  - Calculates a vector between two points
  - Azimuth calculation, Elevation
  - Distance between two points
  ..
 
### File format 
The File format package has:
#### Board2Game
The class Convert a ArrayList<String> which represent data of the game at a given moment, and creates a new game from the same data.
 
#### Game2CSV
The class convert a CSV file to Game (Object in java) . 
And displays it on the screen



### Geom 
Geom package represents shapes in space:
- GPSPoint: lat,long,alt (regular GPS Point ) 
- Point3D : can represents GPSPoint and vec .


 #### ClassDiagram
![classd](https://user-images.githubusercontent.com/44754325/50738259-8d23ff00-11da-11e9-9f86-abfeec50958b.png)
 
 
### ExampleGameMap
![ariel1](https://user-images.githubusercontent.com/44754325/49361557-0b294d80-f6e5-11e8-90f0-a871b2571359.png)
### ExampleBeforeRun
![16](https://user-images.githubusercontent.com/44754325/50387581-72488980-0708-11e9-914f-c0835c6df1be.png)
### ExampleAfterRun
![17](https://user-images.githubusercontent.com/44754325/50387584-842a2c80-0708-11e9-95c0-a5d6c0b590b1.png)
### ExampleKmlRun
![18](https://user-images.githubusercontent.com/44754325/50387587-9ad08380-0708-11e9-8cb0-2c1ea3cc6bb4.png)
### Sorces

 - converting coordinates : https://stackoverflow.com/questions/1185408/converting-from-longitude-latitude-to-cartesian-coordinates  

- calculating 3D vector : https://stackoverflow.com/questions/41912407/calculate-a-vector-in-meter-from-two-gps-coordinates 

 - calculating distance : http://www.vias.org/comp_geometry/math_coord_convert_3d.htm

- how to convert csv to kml(from the resource) : http://convertcsv.com/csv-to-kml.htm 

- the idea of Azimuth : https://en.wikipedia.org/wiki/Azimuth 

