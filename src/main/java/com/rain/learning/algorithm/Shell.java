package com.rain.learning.algorithm;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Shell {

	public static void sort(Comparable[] a) { // Sort a[] into increasing order.
		int N = a.length;
		int h = 1;
		while (h < N / 3)
			h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
		while (h >= 1) { // h-sort the array.
			for (int i = h; i < N; i++) {
				// Insert a[i] among a[i-h], a[i-2*h],// a[i-3*h]... .
				for (int j = i; j >= h && (a[j].compareTo(a[j - 1]) < 0); j -= h) {
					Comparable t = a[j];
					a[j] = a[j - h];
					a[j - h] = t;
				}
			}
			h = h / 3;
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
