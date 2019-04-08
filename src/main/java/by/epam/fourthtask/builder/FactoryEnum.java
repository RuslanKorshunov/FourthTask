package by.epam.fourthtask.builder;

public enum FactoryEnum
{
    DOM("DOM"),
    SAX("SAX"),
    StAX("StAX");

    private String value;

    FactoryEnum(String value)
    {
        this.value=value;
    }

    public String getValue()
    {
        return value;
    }
}