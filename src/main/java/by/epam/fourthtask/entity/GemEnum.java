package by.epam.fourthtask.entity;

public enum GemEnum
{
    NAME("name"),
    GEM("gem"),
    KIND("kind"),
    PRECIOUSNESS("preciousness"),
    ORIGIN("origin"),
    PARAMETERS("parameters"),
    COLOR("color"),
    TRANSPARENCY("transparency"),
    FACETING("faceting"),
    WEIGHT("weight");

    private String value;

    GemEnum(String value)
    {
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
