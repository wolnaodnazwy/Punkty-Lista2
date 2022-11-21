public class Punkt3D {
    private float x;
    private float y;
    private float z;
    public Punkt3D(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public float GetX(){ return x; }
    public float GetY(){ return y; }
    public float GetZ(){ return z; }
    public void SetX(float x){ this.x = x; }
    public void SetY(float y){ this.y = y; }
    public void SetZ(float z){ this.z = z; }
    public void Print(){ System.out.println("x: " + x + " y: " + y + " z: " + z); }
    public void Move(Wektor3D vector){
        x = x + vector.GetX();
        y = y + vector.GetY();
        z = z + vector.GetZ();
    }
    public Punkt2D Project(float c, float d) {
        float X, Y;
        if(c - z == 0)
        {
            throw new ArithmeticException();
        }
        else
        {
            X = ((c * x) - (z * d)) / (c - z);
            Y = (c * y) / (c - z);
        }
        return new Punkt2D(X, Y);
    }

};