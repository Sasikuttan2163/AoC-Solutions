package dayfive;

import utilities.MyUtilities;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class DayFive {
    static ArrayList<String> data;
    static ArrayList<Line> lines;
    static ArrayList<Point2D> pointList;
    static ArrayList<Point2D> multipleLinesPointList;
    public static void main(String[] args) {
        data = MyUtilities.readFile("datadayfive.txt");
        lines = new ArrayList<>();
        pointList = new ArrayList<>();
        multipleLinesPointList = new ArrayList<>();
        data.forEach(line->{
            int xa = Integer.parseInt(line.substring(0, line.indexOf(",")));
            int ya = Integer.parseInt(line.substring(line.indexOf(",")+1, line.indexOf(" ")));
            int xb = Integer.parseInt(line.substring(line.lastIndexOf(" ")+1, line.lastIndexOf(",")));
            int yb = Integer.parseInt(line.substring(line.lastIndexOf(",")+1));

            if(xa == xb || ya==yb){
                lines.add(new Line(xa, ya, xb, yb));
            }
        });
        lines.forEach(line->{
            if(line.x1==line.x2){
                int max = Math.max(line.y1, line.y2);
                int min = Math.min(line.y1, line.y2);
                ArrayList<Point2D> points= new ArrayList<>();
                for(int i=min; i<=max; i++){
                    points.add(new Point2D.Double(line.x1, i));
                }
                pointList.addAll(points);
            }
            if(line.y1==line.y2){
                int max = Math.max(line.x1, line.x2);
                int min = Math.min(line.x1, line.x2);
                ArrayList<Point2D> points = new ArrayList<>();
                for(int i=min;i<=max;i++){
                    points.add(new Point2D.Double(i, line.y1));
                }
                pointList.addAll(points);
            }
        });
        for(int i=0; i<pointList.size(); ){
            Point2D p = pointList.get(i);
            int c = countOccurrences(p);
            if(c>1){
                multipleLinesPointList.add(p);
                pointList.removeIf(point->{
                    return point.equals(p);
                });
            }
            else{
                i++;
            }
        }
        System.out.println(multipleLinesPointList.size());

    }
    public static int countOccurrences(Point2D point2D){
        int count=0;
        for(Point2D p: pointList){
            if(p.equals(point2D))
                count++;
        }
        return count;
    }
}
