package by.epam.fourthtask.entity;

import by.epam.fourthtask.exception.IncorrectDataException;

public class Gem
{
    private String name="";
    private String kind="";
    private String preciousness="";
    private String origin="";
    private String color="";
    private double transparency;
    private String faceting="";
    private double weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPreciousness() {
        return preciousness;
    }

    public void setPreciousness(String preciousness) {
        this.preciousness = preciousness;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getTransparency() {
        return transparency;
    }

    public void setTransparency(Double transparency) throws IncorrectDataException
    {
        if(transparency>=0 && transparency<=100)
        {
            this.transparency = transparency;
        }
        else
        {
            throw new IncorrectDataException("transparency has to be more than or equal to " +
                    "0 and less than or equal to 100.");
        }
    }

    public String getFaceting() {
        return faceting;
    }

    public void setFaceting(String faceting) {
        this.faceting = faceting;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) throws IncorrectDataException
    {
        if(weight>0)
        {
            this.weight = weight;
        }
        else
        {
            throw new IncorrectDataException("weight has to be more than 0.");
        }
    }

    @Override
    public String toString() {
        return "Gem{" +
                "name='" + name + '\'' +
                ", kind='" + kind + '\'' +
                ", preciousness='" + preciousness + '\'' +
                ", origin='" + origin + '\'' +
                ", color='" + color + '\'' +
                ", transparency=" + transparency + "%" + '\'' +
                ", faceting='" + faceting + '\'' +
                ", weight=" + weight + " с" + '\'' +
                '}';
    }
}
