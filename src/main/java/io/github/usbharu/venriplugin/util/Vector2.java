package io.github.usbharu.venriplugin.util;

public class Vector2 implements Cloneable {

  protected final double x;
  protected final double y;

  public static final Vector2 one = new Vector2(1, 1);
  public static final Vector2 zero = new Vector2(0, 0);
  public static final Vector2 up = new Vector2(0, 1);
  public static final Vector2 down = new Vector2(0, -1);
  public static final Vector2 left = new Vector2(-1, 0);
  public static final Vector2 right = new Vector2(1, 0);
  public Vector2(double x, double y){
    this.x = x;
    this.y = y;
  }

  public double getX(){
    return x;
  }

  public double getY(){
    return y;
  }

  public Vector2 add(Vector2 other){
    return new Vector2(x + other.x, y + other.y);
  }

  public Vector2 subtract(Vector2 other){
    return new Vector2(x - other.x, y - other.y);
  }

  public Vector2 multiply(float scalar){
    return new Vector2(x * scalar, y * scalar);
  }

  public Vector2 divide(float scalar){
    return new Vector2(x / scalar, y / scalar);
  }

  public double getLength(){
    return Math.sqrt(x * x + y * y);
  }

  public Vector2 normalize(){
    double length = getLength();
    return new Vector2(x / length, y / length);
  }

  public double dot(Vector2 other){
    return x * other.x + y * other.y;
  }

  public double cross(Vector2 other){
    return x * other.y - y * other.x;
  }

  public double getAngle(){
    return Math.atan2(y, x);
  }

  public Vector2 minus(){
    return new Vector2(-x, -y);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Vector2)) {
      return false;
    }

    Vector2 vector2 = (Vector2) o;

    if (Double.compare(vector2.getX(), getX()) != 0) {
      return false;
    }
    return Double.compare(vector2.getY(), getY()) == 0;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(getX());
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(getY());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public Object clone() {
    Vector2 vector2 = null;

    try{
      vector2 = (Vector2) super.clone();
    }catch (CloneNotSupportedException e){
      e.printStackTrace();
    }
    return vector2;
  }
}
