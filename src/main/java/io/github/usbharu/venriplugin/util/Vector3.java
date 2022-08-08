package io.github.usbharu.venriplugin.util;

public class Vector3 extends Vector2 {

  public static final Vector3 one = new Vector3(1, 1, 1);
  public static final Vector3 zero = new Vector3(0, 0, 0);
  public static final Vector3 up = new Vector3(0, 1, 0);
  public static final Vector3 down = new Vector3(0, -1, 0);
  public static final Vector3 left = new Vector3(-1, 0, 0);
  public static final Vector3 right = new Vector3(1, 0, 0);
  public static final Vector3 forward = new Vector3(0, 0, 1);
  public static final Vector3 backward = new Vector3(0, 0, -1);


  protected final double z;

  public Vector3(double x, double y, double z) {
    super(x, y);
    this.z = z;
  }

  public Vector3(Vector2 v, double z) {
    super(v.getX(), v.getY());
    this.z = z;
  }

  public Vector3(Vector2 v) {
    super(v.getX(), v.getY());
    this.z = 0;
  }

  public double getZ() {
    return z;
  }

  public Vector3 add(Vector3 other) {
    return new Vector3(x + other.x, y + other.y, z + other.z);
  }

  public Vector3 subtract(Vector3 other) {
    return new Vector3(x - other.x, y - other.y, z - other.z);
  }

  @Override
  public Vector3 multiply(float scalar) {
    return new Vector3(x * scalar, y * scalar, z * scalar);
  }

  @Override
  public Vector3 divide(float scalar) {
    return new Vector3(x / scalar, y / scalar, z / scalar);
  }

  @Override
  public double getLength() {
    return Math.sqrt(x * x + y * y + z * z);
  }

  @Override
  public Vector3 normalize() {
    double length = getLength();
    return new Vector3(x / length, y / length, z / length);
  }

  public double dot(Vector3 other) {
    return x * other.x + y * other.y + z * other.z;
  }

  public double cross(Vector3 other) {
    return x * other.y - y * other.x + z * other.z;
  }

  @Override
  public double getAngle() {
    return (float) Math.atan2(y, x);
  }

  @Override
  public Vector3 minus() {
    return new Vector3(-x, -y, -z);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Vector3 vector3)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    return Double.compare(vector3.getZ(), getZ()) == 0;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    long temp;
    temp = Double.doubleToLongBits(getZ());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public Vector3 clone() {
    return (Vector3) super.clone();
  }

  @Override
  public String toString() {
    return "Vector3{" +
        "z=" + z +
        ", x=" + x +
        ", y=" + y +
        '}';
  }
}
