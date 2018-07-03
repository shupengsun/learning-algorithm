package com.rain.learning.algorithm;

import java.util.Arrays;

/**
 * 二分查找.
 * 
 * @author rain
 *
 */
public class BinarySearch {
	public static int rank(int key, int[] a) { // Array must be sorted.
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) { // Key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			if (key < a[mid])
				hi = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}

	public static int recursionRank(int key, int[] a, int lo, int hi) {
		if (hi < lo)
			return lo;
		int mid = lo + (hi - lo) / 2;
		if (key < a[mid]) {
			hi = mid - 1;
			return recursionRank(key, a, lo, hi);
		} else if (key > a[mid]) {
			lo = mid + 1;
			return recursionRank(key, a, lo, hi);
		} else{
			return mid;
		}
	}

	public static void main(String[] args) {
		int[] list = { 0, 1, 3, 6, 4, 5, 7, 9, 8, 2 };
		Arrays.sort(list);
		int key = 20;
		if (rank(key, list) < 0) {
			System.out.println(key);
		} else {
			System.out.println("OK");
		}
	}
}
