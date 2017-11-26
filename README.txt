README.md

Goal and Purpose
This is one of my personal projects that I have developed using the skills I have learned at university. I wanted to develop a game that will help people be better drivers and help people learn the rules of the road where mistakes can be made without indangering anybody. Obviously this is a simulation and cannot be used to fully train someone in regards to New Zealand's road code, but the idea is to teach people about when to give way and what do to in certain situations. 

Struture
At this stage there are two parts to this project "view and model". 

The view package contains the GUI and renders the model to the screen for the player to see. The view package uses the javax.swing library to render the GUI and the java.awt library to respond to player keystrokes and mouse movements.

The model contains the logic of the game and is responsible for updating the player's state when an action occurs.

Current State and Updates
The game is in a alpha state and has poor graphics designed in windows paint. When I have access to a proper design tool I seek to improve the levels and models. 

In future I would like to add in the controller package so that the model is not directly accessing the view and vic versa.

The game lacks any AI and I need to do research before I can add this to the game.

I want to add a field of view so that the player will have to look around using the mouse to see hazards in the environment. The idea is that the screen would be black apart from the area the player can see.

Running the game
If you are working in an IDE simply run Main.java class in the view package.
Currently this is the only way to do it as the jar file throws an error when run.
