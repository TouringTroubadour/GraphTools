# Graph Tools (0.0.1)

## A collection of useful tools for Graphs.

## What's This?

The objective of the GraphTools repository is to contain useful classes related to Graphs / Matrices.
The classes are related to my personal work, and I felt it would be nice to keep them separate and publicly available should anyone else be interested. 
I guarantee that 99% of the stuff in this repository will probably be useless to you but nethertheless if I did help someone out I would feel immensely proud of myself.
I am not, and have never claimed to be, a good programmer, but I do take pride in making my work as clean and easy to follow as possible however.
I would not be surprised if I made any mistakes or took questionnable actions/decisions whilst working on these tools. 

## Prerequesites

I try my best to avoid using external dependencies and JARs if I can avoid it. I am the sort of person who likes to challenge themselves to reverse engineer libraries, and figure out how to do it themselves. With this said however I will adknowledge that in most cases, it's easier to not reinvent the wheel. 
If I do end up surrendering and using External Libraries, I will make a list of them below for you to check out for yourself:

## How to use

### Preface

Since I am rather inexperienced in Maven and other Dependency Tools, the best way for you to use these tools at the moment is to simply clone the repository, and use what you desire. As I said before it's often easier to not reinvent the wheel so if any of the code here is helpful to you please feel free to make whatever modifications you like! If I did provide you with something useful and/or you took it a step further to improve it I would love to know more about it!

### Using GraphTools

For now, we are focused on primitive double matrices `(double[][])` but in future I would like to expand it to other types.

To use GraphTools, download the latest Executable Jar file [here](./dist/)

## What's in the box?

There is a brief summary of Graph Tools different classes:

| Directory | Class | Description |
| --- | --- | --- |
| clustering | [HierarchicalClustering.java](src/main/java/clustering/HierarchicalClustering.java) | Implementation of Hierarchical Clustering |
| conversion | [MatrixConversion.java](src/main/java/conversion/MatrixConversion.java) | Implementation of Matrix Conversion algorithms |
| generators | [DistanceMatrixGenerator.java](src/main/java/generators/DistanceMatrixGenerator.java) | Algorithm for creating randomised Distance Matrices |
| generators | [PerfectMatrixGenerator.java](src/main/java/generators/PerfectMatrixGenerator.java) | Algorithm for creating Fully-Connected Sub-Matrices |
| io | [MatrixReader.java](src/main/java/io/MatrixReader.java) | Implementation of reading double[][] matrices from text files |
| io | [MatrixWriter.java](src/main/java/io/MatrixWriter.java) | Implementation of exporting matrices to text or dot files |
| statistics | [MatrixStatistics.java](src/main/java/statistics/MatrixStatistics.java) | Implementation of various methods for gathering statistics on matrices |
| transformation | [MatrixTransformation.java](src/main/java/transformation/MatrixTransformation.java) | Implementation of various Matrix transformation methods |
| traversal | [DepthFirstSearch.java](src/main/java/traversal/DepthFirstSearch.java) | Implementation of Depth First Search |
| traversal | [PrimMST.java](src/main/java/traversal/PrimMST.java) | Implementation of Prim's Minimum Spanning Tree |
| utils | [MatrixTools.java](src/main/java/utils/MatrixTools.java) | Implementation of various tools relating to Matrices |
| utils | [MatrixValidator.java](src/main/java/utils/MatrixValidator.java) | Implementation of various methods for validating Matrices |
| utils | [MatrixVisualizer.java](src/main/java/utils/MatrixVisualizer.java) | Implementation of basic Image Visualization of Matrices |



## What's coming down the pipeline?

I aim to update this repository further in future as I continue to work with Matricies in my current related work. I am also aiming to package this into a Jar file at some point for convenience. I will avoid including a TO-DO list here for the time being, but I am almost certain there is much more to be added. 