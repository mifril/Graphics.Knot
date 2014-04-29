package ru.nsu.vakhrushev.knot.model;

/**
 * @author Maxim Vakhrushev
 */
public class Quaternion {
    public double r, i, j, k;

    public Quaternion(double angle, double [] direction) {
        double cos = Math.cos(angle / 2.0);
        double sin = Math.sin(angle / 2.0);

        this.r = cos;
        this.i = direction[0] * sin;
        this.j = direction[1] * sin;
        this.k = direction[2] * sin;
    }

    public Quaternion(double r, double x, double y, double z) {
        this.r = r;
        this.i = x;
        this.j = y;
        this.k = z;
    }

    public double mag() {
        return Math.sqrt(r*r + i*i + j*j + k*k);
    }

    public Quaternion inverse() {
        double mag = this.mag();
        return new Quaternion(r/mag, -i/mag, -j/mag, -k/mag);
    }

    public Quaternion unit() {
        double mag = this.mag();
        return new Quaternion(r, i/mag, j/mag, k/mag);
    }

    public Quaternion mul(Quaternion q) {
        double r = this.r*q.r - this.i*q.i - this.j*q.j - this.k*q.k;
        double i = this.r*q.i + this.i*q.r + this.j*q.k - this.k*q.j;
        double j = this.r*q.j - this.i*q.k + this.j*q.r + this.k*q.i;
        double k = this.r*q.k + this.i*q.j - this.j*q.i + this.k*q.r;
        return new Quaternion(r, i, j, k);
    }
}
