package fourWayStreetLights.service;

public interface StreetLightsStateI
{

    // nsew

    public StreetLightsStateI Set_West_Green();

    public StreetLightsStateI Set_East_Green();

    public StreetLightsStateI Set_South_Green();

    public StreetLightsStateI Set_North_Green();

    public void set_otherSignals_RED();

    public StreetLightsStateI Set_ALL_Red();

    public StreetLightsStateI pass_car();

    public void setCarPassCount(int setCount);

    public StreetLightsStateI set_North_Red();

    public StreetLightsStateI set_South_Red();

    public StreetLightsStateI set_East_Red();

    public StreetLightsStateI set_West_Red();
};
