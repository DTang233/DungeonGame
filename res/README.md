
## Overview
This is an adventure game using the MVC design pattern. The world for our game consists of a dungeon, a network of tunnels and caves that are interconnected so that player can explore the entire world by travelling from cave to cave through the tunnels that connect them.

- The dungeon should be able to represented on a 2-D grid.
- There should be a path from every cave in the dungeon to every other cave in the dungeon.
- Each dungeon can be constructed with a degree of interconnectivity. We define an interconnectivity = 0 when there is exactly one path from every cave in the dungeon to every other cave in the dungeon. Increasing the degree of interconnectivity increases the number of paths between caves.
- Not all dungeons "wrap" from one side to the other (as defined above).
- One cave is randomly selected as the start and one cave is randomly selected to be the end. The path between the start and the end locations should be at least of length 5.


## Features
The model should create and support a player moving through the world. To do this, it should support operations that allow:
- both wrapping and non-wrapping dungeons to be created with different degrees of interconnectivity
- provide support for at least three types of treasure: diamonds, rubies, and sapphires
- treasure to be added to a specified percentage of caves. For example, the client should be able to ask the model to add treasure to 20% of the caves and the model should add a random treasure to at least 20% of the caves in the dungeon. A cave can have more than one treasure.
- a player to enter the dungeon at the start
- provide a description of the player that, at a minimum, includes a description of what treasure the player has collected
- provide a description of the player's location that at the minimum includes a description of treasure in the room and the possible moves (north, east, south, west) that the player can make from their current location
- a player to move from their current location
- a player to pick up treasure that is located in their same location
- there are monsters that can be slayed by the player. This provides a way for the player to both win and lose the game.
- There is always at least one Otyugh in the dungeon located at the specially designated end cave. The actual number is specified on the command line. There is never an Otyugh at the start.
- Otyugh only occupy caves and are never found in tunnels. Their caves can also contain treasure or other items.
- They can be detected by their smell. In general, the player can detect two levels of smell:
    - a less pungent smell can be detected when there is a single Otyugh 2 positions from the player's current location
    - detecting a more pungent smell either means that there is a single Otyugh 1 position from the player's current location or that there are multiple Otyughs within 2 positions from the player's current location
- They are adapted to eat whatever organic material that they can find, but love it when fresh meat happens into the cave in which they dwell. This means that a player entering a cave with an Otyugh that has not been slayed will be killed and eaten (see next section on how to slay an Otyugh).
- Player starts with 3 crooked arrows but can find additional arrows in the dungeon with the same frequency as treasure. Arrows and treasure can be, but are not always, found together. Furthermore, arrows can be found in both caves and tunnels.
- A player that has arrows, can attempt to slay an Otyugh by specifying a direction and distance in which to shoot their crooked arrow. Distance is defined as the number of caves (but not tunnels) that an arrow travels. Arrows travel freely down tunnels (even crooked ones) but only travel in a straight line through a cave. For example:
    - a tunnel that has exits to the west and south can have an arrow enter the tunnel from the west and exit the tunnel to the south, or vice-versa (this is the reason the arrow is called a crooked arrow)
    - a cave that has exits to the east, south, and west will allow an arrow to enter from the east and exit to the west, or vice-versa; but an arrow that enters from the south would be stopped since there is no exit to the north
- Distances must be exact. For example, if you shoot an arrow a distance of 3 to the east and the Otyugh is at a distance of 2, you miss the Otyugh.
- It takes 2 hits to kill an Otyugh. Players has a 50% chance of escaping if the Otyugh if they enter a cave of an injured Otyugh that has been hit by a single crooked arrow.

**For example, `Player Interface` has the following functions:**
Pick up the treasures.
```sh
void pickTreasure()
```
Move to the north location.
```sh
void moveNorth()
```
Move to the south location.
```sh
void moveSouth()
```
Move to the east location.
```sh
void moveEast()
```
Move to the west location.
```sh
void moveWest()
```
Get the description of current status.
```sh
String getDescription()
```
Get the current location.
```sh
Location getLocation()
```
 Check if current location is the end.
```sh
 boolean isGameOver()
```
Shoot at given direction and distance.
```sh
boolean shoot(Direction dir, int distance)
```
Detect otyugh at this location.
```sh
int detectOtyugh();
```

## How To Run
To run the ```.jar``` file, run ```java -jar executable.jar```.
When the option panel shows up, you can specify the parameters, as well as to run the game in GUI mode or in TEXT mode.

## How to Use the Program
when the game starts, the left side is the controll panel. For example, you can click the move west button to move west. 
If you want to use key board as input, type "w" key will go west, "e" will go east "n" will go north, and "s" will go south. To shoot you will need to specify the direction using arrow keys, specify the distance using number key (1-3). If the the player enters a cave with otyugh, the battle will begin. Each turn when click attck button, there are 50% chance for the player to attack faster and the reduce the otyugh's health, otherwise the otyugh will attack the player. If the health of the player become 0, they lose.
You can also check the show hint checkbox to show where the otyugh is.

## Design Changes
I added ```action panel``` ,  ```text panel```, ```option panel``` and  ```battle frame```.

## Assumptions
- To ensure we can have a deterministic runtime, the start location and end location of the dungeon are randomly created to meet the constrain before generatate other edges using modified Kruskal's algorithm. [1]
- There is only one Otyugh at each cave.

## Limitations
All required features are working correctly, but the runtime might be improved when the dungeron is large.

## Citations
[1] https://en.wikipedia.org/wiki/Kruskal's_algorithm




