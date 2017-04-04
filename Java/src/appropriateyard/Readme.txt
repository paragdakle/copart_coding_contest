O(n) -> MostAppropriateYard.java
Linear Search for closest Yard.

O(n-k) -> MostAppropriateYardModified.java
Cluster of Yards using Unsupervised Learning Algo K-Means.

compile: javac *.java //compile all classes in the folder 

Version1-
run: java MostAppropriateYard <lat> <long>
*make sure zipcodes csv file is in the same folder.

Version2-
run: java MostAppropriateYardModified <lat> <long>
*make sure zipcodes csv file is in the same folder.

NOTE:
1. We have ignored locations which had missing data due to time constraint.

2. Another Improvement could be to use Binary Search to find closest yard faster.
