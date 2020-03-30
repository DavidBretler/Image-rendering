package primitives;

import java.util.Objects;

public class Vector {
    Point3D _head;

    /**4 vector constractor
     *
     * @param _head
     */
    public Vector(Point3D _head)
    {
        if (_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("ZERO vector is not valid");
        this._head = new Point3D(_head._x.get(),_head._y.get(),_head._z.get());

    }
    public Vector(Vector vec) {
        this._head = new Point3D(vec._head._x.get(), vec._head._y.get(), vec._head._z.get());
    }
    /**
     *Vector Constructor
     * @param _x  x vertex coordinate
     * @param _y start of  vector
     *  @param _z start of  vector
     */
    public Vector(Coordinate _x,Coordinate _y,Coordinate _z) {
        if ( new Point3D(_x,_y,_z).equals(Point3D.ZERO))
            throw new IllegalArgumentException("ZERO vector is not valid");
        this._head = new Point3D(_x,_y,_z);
    }
    public Vector(double _x,double _y,double _z) {
        if ( new Point3D(_x,_y,_z).equals(Point3D.ZERO))
            throw new IllegalArgumentException("ZERO vector is not valid");
        this._head = new Point3D(_x,_y,_z);
    }

    public Vector(Point3D p1, Point3D p2) {
       Point3D a = new Point3D(p2._x.get()-p1._x.get(),p2._y.get()-p1._y.get(),p2._z.get()-p1._z.get());
        if (a.equals(Point3D.ZERO))
            throw new IllegalArgumentException("ZERO vector is not valid");
        this._head = a;
    }

    /**
     * adss 2 vectors
     * @param vec
     * @return the new vector
     */
    public Vector add(Vector vec)
    {
        return new Vector
                (this._head._x.get()+vec._head._x.get(),
                        this._head._x.get()+vec._head._x.get(),
                        this._head._x.get()+vec._head._x.get());
    }

    /**
     * subtracts 2 vectors
     * @param vec
     * @return the new vector
     */
    public Vector 	subtract(Vector vec)
    {
        return new Vector
                (this._head._x.get()-vec._head._x.get(),
                        this._head._x.get()-vec._head._x.get(),
                        this._head._x.get()-vec._head._x.get());
    }
    /**
     *
     * @param num to multiply in the vector
     * @return new vector
     */
    public Vector Scale (double num)
    {
        return new Vector(_head.get_x().get()*num, _head.get_y().get()*num,_head.get_z().get()*num);
    }
    public Point3D get_head() {
        return _head;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Objects.equals(_head, vector._head);
    }

    public double dotProduct(Vector vec)
    {

        return this._head.get_x().get()*  vec._head.get_x().get()+
                this._head.get_y().get()*  vec._head.get_y().get()+
                this._head.get_z().get()*  vec._head.get_z().get();
    }

    public Vector crossProduct (Vector vec)
    {
        return new Vector(new Point3D(
                this.get_head().get_y().get()*vec.get_head().get_z().get()- this.get_head().get_z().get()*vec.get_head().get_y().get(),
                this.get_head().get_z().get()*vec.get_head().get_x().get()- this.get_head().get_x().get()*vec.get_head().get_z().get(),
                this.get_head().get_x().get()*vec.get_head().get_y().get()- this.get_head().get_y().get()*vec.get_head().get_x().get()


                ));
    }

    /**
     * calculat squared length
     * @return
     */
    public double	lengthSquared ()
    {
        return
                 this._head.get_x().get()* this._head.get_x().get()+
                         this._head.get_y().get()* this._head.get_y().get( )+
                         this._head.get_z().get()* this._head.get_z().get();
    }
    public double	length ()
    {
       return Math.sqrt(this.lengthSquared());
    }

    /**
     * changes the vector itself
     * @return the norlmized vector
     */
    public Vector	normalize()
    {
          double normalize= this.length();
          this._head=new Point3D
                  (this._head._x.get()/normalize,
                  this._head._y.get()/normalize,
                  this._head._z.get()/normalize);
        return this;
    }

    /**
     * creates a new vector
     * @return the norlmized vector
     */
    public Vector 	normalized()
    {
        Vector  vec=new Vector (this);
        return new Vector(vec.normalize());
    }
    public String toString() {
        return
                "" + _head.toString();
    }
}
