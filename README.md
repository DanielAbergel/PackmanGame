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
![software component diagram](https://user-images.githubusercontent.com/44754325/50836257-71973080-1361-11e9-843d-9ed415a7d98b.png)

 
 
### ExampleGameMap

![ariel1](https://user-images.githubusercontent.com/44754325/49361557-0b294d80-f6e5-11e8-90f0-a871b2571359.png)

### ExampleBeforeRun 
![26](https://user-images.githubusercontent.com/44754325/50837946-8e356780-1365-11e9-8f3b-90e6a6febe38.png)

### ExampleAfterRun
![25](https://user-images.githubusercontent.com/44754325/50837951-92618500-1365-11e9-8c44-be400a965112.png)


<img width="1924" alt="default" src="https://user-images.githubusercontent.com/44754325/50835120-87572680-135e-11e9-891d-555131187996.png">
### Run ans Start Playing .
#### There are two options to start:
- The goal of the game: Eat all the fruits on the board.
- Independent game with the mouse: The game move is that every click of the user moves the player to the location of the mouse, the user has to dodge the winds, and eat as many fruits or pakmans every penny will win it at one point.
- Automatic game: happens alone, the player will eat the fruits in the shortest way. (Just click the AutoGame tab)

have fun !

### Sorces

 - converting coordinates : https://stackoverflow.com/questions/1185408/converting-from-longitude-latitude-to-cartesian-coordinates  

- calculating 3D vector : https://stackoverflow.com/questions/41912407/calculate-a-vector-in-meter-from-two-gps-coordinates 

 - calculating distance : http://www.vias.org/comp_geometry/math_coord_convert_3d.htm

- how to convert csv to kml(from the resource) : http://convertcsv.com/csv-to-kml.htm 

- the idea of Azimuth : https://en.wikipedia.org/wiki/Azimuth 

