import java.util.*;

public class Program
{
    public static void main(String[] args)
    {
        List<Point> points = generatePoints();
        Point[] closest = new Point[points.size()];

        KDTree tree = new KDTree(points, 0); // WILL MODIFY 'points'

        for (int i = 0; i < points.size(); i++)
        {
            closest[i] = tree.findClosest(points.get(i));
        }

        for (int i = 0; i < points.size(); i++)
        {
            System.out.println(points.get(i) + " is closest to " + closest[i]);
        }
    }

    private static List<Point> generatePoints()
    {
        ArrayList<Point> points = new ArrayList<Point>();
        Random r = new Random();

        for (int i = 0; i < 1000; i++)
        {
            points.add(new Point(r.nextInt() % 1000, r.nextInt() % 1000));
        }

        return points;
    }
}

class Point
{
    public static final Point INFINITY
        = new Point(Double.POSITIVE_INFINITY,
                    Double.POSITIVE_INFINITY);

    public double[] coord; // coord[0] = x, coord[1] = y

    public Point(double x, double y)
    {
        coord = new double[] { x, y };
    }

    public double getX() { return coord[0]; }
    public double getY() { return coord[1]; }

    public double distance(Point p)
    {
        double dX = getX() - p.getX();
        double dY = getY() - p.getY();
        return Math.sqrt(dX * dX + dY * dY);
    }

    public boolean equals(Point p)
    {
        return (getX() == p.getX()) && (getY() == p.getY());
    }

    public String toString()
    {
        return "(" + getX() + ", " + getY() + ")";
    }

    public static class PointComp implements Comparator<Point>
    {
        int d; // the dimension to compare in (0 => x, 1 => y)

        public PointComp(int dimension)
        {
            d = dimension;
        }

        public int compare(Point a, Point b)
        {
            return (int) (a.coord[d] - b.coord[d]);
        }
    }
}

class KDTree
{
    // 2D k-d tree
    private KDTree childA, childB;
    private Point point; // defines the boundary
    private int d; // dimension: 0 => left/right split, 1 => up/down split

    public KDTree(List<Point> points, int depth)
    {
        childA = null;
        childB = null;
        d = depth % 2;

        // find median by sorting in dimension 'd' (either x or y)
        Comparator<Point> comp = new Point.PointComp(d);
        Collections.sort(points, comp);

        int median = (points.size() - 1) / 2;
        point = points.get(median);

        // Create childA and childB recursively.
        // WARNING: subList() does not create a true copy,
        // so the original will get modified.
        if (median > 0)
        {
            childA = new KDTree(
                points.subList(0, median),
                depth + 1);
        }
        if (median + 1 < points.size())
        {
            childB = new KDTree(
                points.subList(median + 1, points.size()),
                depth + 1);
        }
    }

    public Point findClosest(Point target)
    {
        Point closest = point.equals(target) ? Point.INFINITY : point;
        double bestDist = closest.distance(target);
        double spacing = target.coord[d] - point.coord[d];
        KDTree rightSide = (spacing < 0) ? childA : childB;
        KDTree otherSide = (spacing < 0) ? childB : childA;

        /*
         * The 'rightSide' is the side on which 'target' lies
         * and the 'otherSide' is the other one. It is possible
         * that 'otherSide' will not have to be searched.
         */

        if (rightSide != null)
        {
            Point candidate = rightSide.findClosest(target);
            if (candidate.distance(target) < bestDist)
            {
                closest = candidate;
                bestDist = closest.distance(target);
            }
        }

        if (otherSide != null && (Math.abs(spacing) < bestDist))
        {
            Point candidate = otherSide.findClosest(target);
            if (candidate.distance(target) < bestDist)
            {
                closest = candidate;
                bestDist = closest.distance(target);
            }
        }

        return closest;
    }
}
