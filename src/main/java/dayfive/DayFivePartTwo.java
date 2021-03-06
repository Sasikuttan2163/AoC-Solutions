package dayfive;

import utilities.MyUtilities;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class DayFivePartTwo {
    static ArrayList<String> data;
    static ArrayList<Line> lines;
    static ArrayList<Point2D> pointList;
    static ArrayList<Point2D> multipleLinesPointList;
    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        data = MyUtilities.readFile("datadayfive.txt");
        lines = new ArrayList<>();
        pointList = new ArrayList<>();
        multipleLinesPointList = new ArrayList<>();
        data.forEach(line->{
            int xa = Integer.parseInt(line.substring(0, line.indexOf(",")));
            int ya = Integer.parseInt(line.substring(line.indexOf(",")+1, line.indexOf(" ")));
            int xb = Integer.parseInt(line.substring(line.lastIndexOf(" ")+1, line.lastIndexOf(",")));
            int yb = Integer.parseInt(line.substring(line.lastIndexOf(",")+1));

            if(xa == xb || ya==yb || Math.abs(ya-yb)==Math.abs(xa-xb)){
                lines.add(new Line(xa, ya, xb, yb));
            }
        });
        lines.forEach(line->{
            if(line.isVertical()){
                int max = Math.max(line.y1, line.y2);
                int min = Math.min(line.y1, line.y2);
                ArrayList<Point2D> points= new ArrayList<>();
                for(int i=min; i<=max; i++){
                    points.add(new Point2D.Double(line.x1, i));
                }
                pointList.addAll(points);
            }
            if(line.isHorizontal()){
                int max = Math.max(line.x1, line.x2);
                int min = Math.min(line.x1, line.x2);
                ArrayList<Point2D> points = new ArrayList<>();
                for(int i=min;i<=max;i++){
                    points.add(new Point2D.Double(i, line.y1));
                }
                pointList.addAll(points);
            }
            if(line.isDiagonal()){
                ArrayList<Point2D> points = line.pointsInDiagonalLine();
                pointList.addAll(points);
                System.out.println(pointList.size());
            }
        });
        for(int i=0; i<pointList.size(); ){
            Point2D p = pointList.get(i);
            if(i+1<pointList.size()) {
                Point2D nextPoint = pointList.get(i+1);
                int c = countOccurrences(p);
                if (c > 1) {
                    multipleLinesPointList.add(p);
                    pointList.removeIf(point -> {
                        return point.equals(p);
                    });
                    i= pointList.indexOf(nextPoint);
                } else {
                    i++;
                }
            }
            else break;
        }
        System.out.println(multipleLinesPointList.size());
        long b = System.currentTimeMillis();
        System.out.println(b-a+"ms");
    }
    public static int countOccurrences(Point2D point2D){
        int count=0;
        for(Point2D p: pointList){
            if(p.equals(point2D))
                count++;
            if(count>1)
                break;
        }
        return count;
    }
}
