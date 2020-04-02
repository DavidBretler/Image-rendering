package geometries;


public class Cylinder extends RadialGeometry {

    double higet;


    public Cylinder(double _radius,double _higet2 )
    {
        super(_radius);
        higet=_higet2;  ;
    }



    public double getHiget() {
        return higet;
    }

    @Override
    public double get_radius() {
        return super.get_radius();
    }

    @Override
    public String toString() {
        return
                "higet=" + higet +
                ", _radius=" + _radius +
                '}';
    }
}
