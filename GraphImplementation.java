/*
inspiration from https://www.geeksforgeeks.org/topological-sorting/
&& https://algorithms.tutorialhorizon.com/topological-sort/
 */

import java.util.*;

public class GraphImplementation implements Graph
{
	private ArrayList<Integer> list;
	private boolean[][] matrix;
	private int size;

	// constructor
	public GraphImplementation(int vertices)
	{
		this.size = vertices;
		this.matrix = new boolean[size][size];
		this.list = new ArrayList<>();
	}

	// adds an edge
	public void addEdge(int src, int tar) throws Exception
	{
		// if lower than zero or higher than the size, throw an exception
		if ((src < 0 || src > size) || (tar < 0 || tar > size))
		{
			throw new Exception("err. out of bounds");
		}
		// adds vertex at the position between the two vertices
		matrix[src][tar] = true;
		// prints visual representation of the matrix
		printMatrix();
	}

	// returns list of vertexIDs
	public List<Integer> neighbors(int vertex) throws Exception
	{
		// creates a list to track neighbors
		ArrayList<Integer> neighbors = new ArrayList<>();

		// if less than zero or greater than size, throw and error
		if (vertex < 0 || vertex > size)
		{
			throw new Exception("err. out of bounds");
		}
		for (int i = 0; i < size; i++)
		{
			if (matrix[vertex][i])
			{
				// if the neighbor has an edge, add it to the list
				neighbors.add(i);
			}
		}
		// return the list
		return neighbors;
	}

	//
	private void printMatrix()
	{
		for (int x = 0; x < matrix.length; x++)
		{
			for (int y = 0; y < matrix[x].length; y++)
			{
				if (matrix[x][y])
				{
					System.out.print("X  ");
				}
				else
				{
					System.out.print("O  ");
				}
			}
			System.out.println();
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
	}

	public List<Integer> topologicalSort()
	{
		int[] arr = new int[size];

		// nested for loops to reach each element of the 2d matrix
		for (int y = 0; y < size; y++)
		{
			for (int x = 0; x < size; x++)
			{
				if (matrix[x][y])
				{
					// if an edge exists, increment the corresponding y pos in arr
					arr[y]++;
				}
			}
		}

		// iterate through list
		while (list.size() < size)
		{
			// iterate through arr
			for (int i = 0; i < arr.length; i++)
			{
				if (arr[i] == 0)
				{
					list.add(i);
					arr[i] = -1;
					for (int j=0; j<size; j++)
					{
						if (matrix[i][j])
						{
							arr[j]--;
						}
					}
				}
			}
		}
		// returns the list
		return list;
	}

}