package dayfive;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Line {
    int x1, y1, x2, y2;
    public Line(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public static void main(String[] args) {
        Line line = new Line(1,1,3,3);
        Line line2 = new Line(9,7,7,9);
        System.out.println(line.pointsInDiagonalLine());
        System.out.println(line2.pointsInDiagonalLine());
    }
    public boolean isHorizontal(){
        return y1==y2;
    }
    public boolean isVertical(){
        return x1==x2;
    }
    public boolean isDiagonal(){
       return Math.abs(y2-y1)==Math.abs(x2-x1);
    }
    public ArrayList<Point2D> pointsInDiagonalLine(){
        Line line = this;
        ArrayList<Point2D> points = new ArrayList<>();
        if(line.isDiagonal()){
            if(line.x1<=line.x2){
                if(line.y1<=line.y2){
                    for(int i=line.x1, j=line.y1; i<=line.x2; i++, j++){
                        points.add(new Point2D.Double(i, j));
                    }
                }
                else{
                    for(int i=line.x1, j=line.y1; i<=line.x2; i++, j--){
                        points.add(new Point2D.Double(i,j));
                    }
                }
            }
            else{
                if(line.y1<=line.y2){
                    for(int i=line.x1, j=line.y1; i>=line.x2; i--, j++){
                        points.add(new Point2D.Double(i, j));
                    }
                }
                else{
                    for(int i=line.x1, j=line.y1; i>=line.x2; i--, j--){
                        points.add(new Point2D.Double(i,j));
                    }
                }
            }

        }
        return points;
    }
}
