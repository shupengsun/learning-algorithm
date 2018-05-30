package com.rain.learning.algorithm;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Insertion2 {

	public static void sort(Comparable[] a) { // Sort a[] into increasing order.
		int N = a.length;
		for (int i = 1; i < N; i++) {
			// Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
			for (int j = i; j > 0 && (a[j].compareTo(a[j - 1]) < 0); j--) {
				Comparable t = a[j];
				a[j] = a[j - 1];
				a[j - 1] = t;
			}
		}
	}

	private static void show(Comparable[] a) { // Print the array, on a single
		for (int i = 0; i < a.length; i++)
			StdOut.print(a[i] + " ");
		StdOut.println();
	}

	public static void main(String[] args) {
		System.out.println("------------------");
		String[] a = In.readStrings();
		sort(a);
		show(a);

	}

}
