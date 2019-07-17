This is an api I built for Monte Carlo Tree Search. It should be implementable for any 2 player turn based game by
modifying the methods in the AmazonsBoard class. You should keep all the methods, but may have to completly rewrite them
to fit with the game you are trying to simulate. It is setup to play against itself at the moment, but you could create a 
human method in the main class, but thats out of the scope for what I was doing with this project. 

The current board is for the game of amazons, which MCTS actually is fairly inefficient at choosing best moves because 
the game tree is extremly broad and deep (There are 2176 options for the first move!). However once the game tree starts to shrink 
it rapidly grows stronger at finding the most optimal move.

Issues:
-The getScore method uses a function I made, and although it is good can always be improved.

-Nodes will generate more children than there are moves (duplicates all still valid I believe)

-Seems to be an issue with finding most optimal score at the start

