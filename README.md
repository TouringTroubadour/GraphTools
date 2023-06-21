# Graph Tools (0.0.2)

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

Since I am rather inexperienced in Maven and other Dependency Tools, the best way for you to use these tools at the moment is to simply either clone the repository or download the latest Executable Jar file [here](./dist/). As I said before it's often easier to not reinvent the wheel so if any of the code here is helpful to you please feel free to make whatever modifications you like! If I did provide you with something useful and/or you took it a step further to improve it I would love to know more about it!

### Using GraphTools

For now, we are focused on primitive double matrices `(double[][])` but in future I would like to expand it to other types.

To use GraphTools, download the latest Executable Jar file [here](./dist/)

## What's in the box?

There is a brief summary of Graph Tools different classes:

| Directory | Class | Description |
| --- | --- | --- |
| clustering | [HierarchicalClustering.java](src/com/graphtools/clustering/HierarchicalClustering.java) | Implementation of Hierarchical Clustering |
| conversion | [MatrixConversion.java](src/com/graphtools/conversion/MatrixConversion.java) | Implementation of Matrix Conversion algorithms |
| generators | [DistanceMatrixGenerator.java](src/com/graphtools/generators/DistanceMatrixGenerator.java) | Algorithm for creating randomised Distance Matrices |
| generators | [PerfectMatrixGenerator.java](src/com/graphtools/generators/PerfectMatrixGenerator.java) | Algorithm for creating Fully-Connected Sub-Matrices |
| generators | [RandomAdjacencyMatrixGenerator.java](src/com/graphtools/generators/RandomAdjacencyMatrixGenerator.java) | Algorithm for creating Randomly Generated Adjacency Matrices |
| io | [MatrixReader.java](src/com/graphtools/io/MatrixReader.java) | Implementation of reading double[][] matrices from text files |
| io | [MatrixWriter.java](src/com/graphtools/io/MatrixWriter.java) | Implementation of exporting matrices to text or dot files |
| metrics | [BasicMeasures.java](src/com/graphtools/metrics/BasicMeasures.java) | Implementation of Basic Graph Measures/Metrics |
| metrics | [CentralityMeasures.java](src/com/graphtools/metrics/CentralityMeasures.java) | Implementation of Graph Centrality Measures/Metrics |
| metrics | [ConnectivityMeasures.java](src/com/graphtools/metrics/ConnectivityMeasures.java) | Implementation of Graph Connectivity Measures/Metrics |
| metrics | [DegreeMeasures.java](src/com/graphtools/metrics/DegreeMeasures.java) | Implementation of Graph Degree Measures/Metrics |
| metrics | [DistanceMeasures.java](src/com/graphtools/metrics/DistanceMeasures.java) | Implementation of Graph Distance Measures/Metrics |
| ranking | [PageRank.java](src/com/graphtools/metrics/PageRank.java) | Implementation of PageRank algorithm |
| summary | [MetricsSummary.java](src/com/graphtools/metrics/MetricsSummary.java) | Implementation of "Summary" methods for various packages|
| transformation | [MatrixTransformation.java](src/com/graphtools/transformation/MatrixTransformation.java) | Implementation of various Matrix transformation methods |
| traversal | [DepthFirstSearch.java](src/com/graphtools/traversal/DepthFirstSearch.java) | Implementation of Depth First Search |
| traversal | [Dijkstra.java](src/com/graphtools/traversal/Dijkstra.java) | Implementation of Dijkstra Path Finding |
| traversal | [PrimMST.java](src/com/graphtools/traversal/PrimMST.java) | Implementation of Prim's Minimum Spanning Tree |
| utils | [MatrixOperations.java](src/com/graphtools/utils/MatrixOperations.java) | Implementation of various tools relating to mathematical computations |
| utils | [MatrixTools.java](src/com/graphtools/utils/MatrixTools.java) | Implementation of various tools relating to Matrices |
| utils | [MatrixValidator.java](src/com/graphtools/utils/MatrixValidator.java) | Implementation of various methods for validating Matrices |
| utils | [MatrixVisualizer.java](src/com/graphtools/utils/MatrixVisualizer.java) | Implementation of basic Image Visualization of Matrices |



## What's coming down the pipeline?

I aim to update this repository further in future as I continue to work with Matricies in my current related work. I will avoid including a TO-DO list here for the time being, but I am almost certain there is much more to be added. 

## MIT License

Copyright (c) 2023 TouringTroubadour

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
