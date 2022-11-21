import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        ///////////////////////////PRZYKLAD UZYCIA WYJATKU W PUNKT3D//////////////////////////////////////////
        Punkt3D punkt = new Punkt3D(1, 2, 3);
        try {
            punkt.Project(3, 3);
        }
        catch(ArithmeticException e)
        {
            System.out.println("Dzielenie przez 0!");
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////

        String name = "C:\\Users\\Klaudia\\Desktop\\InteliJ\\Punkty\\src\\lamana3D3.csv";
        FileReader fileReader;
        try {
            fileReader = new FileReader(name);
        }
        catch (FileNotFoundException e) {
            System.out.println("Plik nie istnieje");
            return;
        }
        catch(Exception e){
            System.out.println("Błąd podczas wczytywania pliku: " + e.getMessage());
            return;
        }
        Scanner in = new Scanner(fileReader);
        Vector<Punkt2D> Punkty2D = new Vector<Punkt2D>();
        Vector<Punkt3D> Punkty3D = new Vector<Punkt3D>();

        float x, y, z;
        char a;

        while (in.hasNext())
        {
            String line = in.nextLine();
            String[] elements = line.split(";");
            if (elements.length == 2)
            {
                try
                {
                    x = Float.parseFloat(elements[0]);
                    y = Float.parseFloat(elements[1]);
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Nie zostala podana liczba");
                    return;
                }
                Punkty2D.addElement(new Punkt2D(x, y));
            }

             if (elements.length == 3)
            {
                try
                {
                    x = Float.parseFloat(elements[0]);
                    y = Float.parseFloat(elements[1]);
                    z = Float.parseFloat(elements[2]);
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Nie zostala podana liczba");
                    return;
                }
                Punkty3D.addElement(new Punkt3D(x, y, z));
            }
        }
        int i = 0, j = 1, k = 2, l;

        if (!Punkty2D.isEmpty())
        { //W przestrzeni 2D wszystkie punkty leza na jednej plaszczynie
            while( k < Punkty2D.size())
            {
                double rownanie1 = (Punkty2D.get(i).GetY() - Punkty2D.get(j).GetY()) / (Punkty2D.get(i).GetX() - Punkty2D.get(j).GetX());
                double rownanie2 = (Punkty2D.get(i).GetY() - Punkty2D.get(k).GetY()) / (Punkty2D.get(i).GetX() - Punkty2D.get(k).GetX());
                if (rownanie1 == rownanie2)
                {
                    i++;
                    j++;
                    k++;
                }
                else
                {
                    Punkty2D.get(k).Print();
                    k = Punkty2D.size();
                }

            }
        }
        if (!Punkty3D.isEmpty())
        {
            while (k < Punkty3D.size())
            {
                Wektor3D first = new Wektor3D(Punkty3D.get(i), Punkty3D.get(j));
                Wektor3D second = new Wektor3D(Punkty3D.get(j), Punkty3D.get(k));
                Wektor3D result = new Wektor3D(0, 0, 0);
                if (first.CrossProduct(second).Compare(result)) //jesli sa wspoliniowe to przechodzi przez nie plaszczyzna
                {
                    i++;
                    j++;
                    k++;
                }
                else
                {
                    if (k > 2) //3 punty zawsze moga lezec na jednej plaszczyznie
                    {
                        i--;
                        j--;
                        l = k;
                        k--;

                        first = new Wektor3D(Punkty3D.get(i), Punkty3D.get(j));
                        second = new Wektor3D(Punkty3D.get(j), Punkty3D.get(k));
                        Wektor3D third = new Wektor3D(Punkty3D.get(k), Punkty3D.get(l));
                        Wektor3D help = first.CrossProduct(third);

                        if (first.CrossProduct(second).Compare(help)) // jesli leza na jednej plaszczyznie i nie sa wpolliniowe
                        {
                            System.out.println("jestem tu");
                            i+=2;
                            j+=2;
                            k+=2;
                        }
                        else //nie sa wspolliniowe i nie leza na jednej plaszczyznie
                        {
                            Punkty3D.get(l).Print();
                            k = Punkty3D.size();
                        }
                    }
                    else //3 punkt jest niewspolliniowy ale lezy na jednej plaszczyznie
                    {
                        i++;
                        j++;
                        k++;
                    }
                }
            }
        }

    }
}