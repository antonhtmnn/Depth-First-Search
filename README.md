# Depth-First-Search

This repository contains the implementation of Depth-First Search (DFS) algorithms for generating and solving random mazes. The project is part of a course on algorithms and data structures, specifically focusing on graph algorithms. The key tasks include implementing DFS to explore graphs, generating random mazes, and finding paths within these mazes.

[![Depth-First-Search](https://i9.ytimg.com/vi/C9phDoLX4KQ/mqdefault.jpg?sqp=CLiY-LUG-oaymwEmCMACELQB8quKqQMa8AEB-AH-CYAC0AWKAgwIABABGDwgVShlMA8=&rs=AOn4CLAn5gIwd5sjNyZ_7PZpZXohDHpzgg)](https://youtu.be/C9phDoLX4KQ)

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)


## Installation

To set up this project locally, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/antonhtmnn/Depth-First-Search.git
   ```
   
2. Navigate to the project directory:

   ```bash
   cd Depth-First-Search
   ```

3. Compile the Java files:

   ```bash
   javac ./*.java
   ```

## Usage

You can run the different components of the project by executing the compiled Java classes. For example:

```bash
java Maze
```

## Features

### 1. Depth-First Search (DFS)

- **DepthFirstPaths.java**: Implements the classic DFS algorithm to explore all nodes in a graph and provides methods to compute pre-order, post-order, and paths.

### 2. Random Maze Generation

- **Maze.java**: Generates a random maze using a graph structure, where nodes represent intersections and edges represent paths.

- **RandomDepthFirstPaths.java**: Implements a randomized version of DFS to create more varied maze structures.

### 3. Pathfinding in Mazes

- **Maze.java**: Includes functionality to find paths between two nodes within the generated maze.
