/*
Given a set of points in 2d plane, find TOP K points which are closest to the origin.
Given solution runs in worst case time complexity of O(nlogk) and space (k+1)
*/
import java.util.Comparator;
import java.util.PriorityQueue;

public class OriginQuestion {

	public static class Point implements Comparable<Point>{

		public double x;

		public double y;


		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

		public Point(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

		public double distanceFromOrigin(){
			return (this.x*this.x + this.y*this.y); //there is no need add the square root overhead as we only need to compare the distance
		}

		@Override
		public int compareTo(Point p2) {
			double dist1 = this.distanceFromOrigin();
			double dist2 = p2.distanceFromOrigin();

			if(dist1 < dist2)
				return -1;
			else if(dist1 > dist2)
				return 1;
			else
				return 0;
		}

	} 

	public static Point[] closestk( Point  myList[], int k ) {
		PriorityQueue<Point> kClosestPoints = 
			new PriorityQueue<Point>(k);
		Point maxPointSoFar = new Point(0,0);
		for(int i=0;i<myList.length;i++){
			if(myList[i].compareTo(maxPointSoFar) > 0){
				maxPointSoFar = myList[i];
				if(kClosestPoints.size() < k){
					kClosestPoints.add(myList[i]);
				}
			}else{
				if(kClosestPoints.size() < k){
					kClosestPoints.add(myList[i]);
				}
			}
			
		}
		Point[] ret = new Point[k];
		for(int i=0;i<k;i++){
			ret[i] = kClosestPoints.remove();
		}
		return ret;
	}

	public static void main(String[] args){
		int n=50;
		Point[] p = new Point[n];
		for(int i=0;i<n;i++){
			p[i] = new Point(i,i);
		}
		Point[] q = closestk(p, 10);

		for(int i=0;i<q.length;i++){
			System.out.println(q[i].toString() );
		}
	}

	public class PointComparator implements Comparator<String>
	{
		@Override
		public int compare(String x, String y)
		{
			// Assume neither string is null. Real code should
			// probably be more robust
			if (x.length() < y.length())
			{
				return -1;
			}
			if (x.length() > y.length())
			{
				return 1;
			}
			return 0;
		}
	}
}