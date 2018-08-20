package fourWayStreetLights.service;
import fourWayStreetLights.util.Logger;
import fourWayStreetLights.util.Results;

public class GreenEast implements StreetLightsStateI
{

    private int carpass_count;
    private StretLights currentContext;
    private Results results;

    public GreenEast(StretLights contex, Results result)
    {
        Logger.writeMessage(this.getClass().getName() + " Constructor is called ", Logger.DebugLevel.CONSTRUCTOR);
        currentContext = contex;
        carpass_count = 0;
        results = result;
    }

    /**
     * Set numbers of cars passed
     * @param count
     */
    @Override
    public void setCarPassCount(int count)
    {
        carpass_count = count;
    }

    /**
     * only South is green , only cars in south can pass
     * @return : New State of the machine
     */
    @Override
    public StreetLightsStateI Set_South_Green()
    {
        if ( currentContext.getCurrentState() != currentContext.getSouthGreen())
        {
            results.storeNewResult("STATE changed to : SouthGreen");
            results.storeNewResult("ACTION set_otherSignals_RED: Set East North West Lights to RED");
        }
        // set state to South Green
        currentContext.getSouthGreen().setCarPassCount(0);
        return currentContext.getSouthGreen();
    }

    /**
     * only West is green , only cars in west can pass
     * @return : New State of the machine
     */
    @Override
    public StreetLightsStateI Set_West_Green()
    {
        if ( currentContext.getCurrentState() != currentContext.getWestGreen() )
        {
            results.storeNewResult("STATE changed to : WestGreen");
            results.storeNewResult("ACTION set_otherSignals_RED: Set East South North Lights to RED");
        }
        currentContext.getWestGreen().setCarPassCount(0);
        return currentContext.getWestGreen();
    }

    /**
     * only east is green , only cars in east can pass
     * @return : New State of the machine
     */
    @Override
    public StreetLightsStateI Set_East_Green()
    {
        if ( currentContext.getCurrentState() != currentContext.getEastGreen())
        {
            results.storeNewResult("STATE changed to : EastGreen");
            results.storeNewResult("ACTION set_otherSignals_RED: Set North South West Lights to RED");
        }
        currentContext.getEastGreen().setCarPassCount(0);
        return currentContext.getEastGreen();
    }

    /**
     * only North is green , only cars in North can pass
     * @return : New State of the machine
     */
    @Override
    public StreetLightsStateI Set_North_Green()
    {
        if ( currentContext.getCurrentState() != currentContext.getNorthGreen())
        {
            results.storeNewResult("STATE changed to : NorthGreen");
            results.storeNewResult("ACTION set_otherSignals_RED: Set East South West Lights to RED");
        }
        currentContext.getNorthGreen().setCarPassCount(0);
        return currentContext.getNorthGreen();
    }

    /**
     * No cars can pass
     * @return : New State of the machine
     */
    @Override
    public StreetLightsStateI Set_ALL_Red()
    {
        if ( currentContext.getCurrentState() != currentContext.getAllRed())
        {
            results.storeNewResult("STATE changed to : AllRed");
        }
        return currentContext.getAllRed();
    }

    /**
     * Pass a car from east side
     * @return : New state of machine
     */
    @Override
    public StreetLightsStateI pass_car()
    {
        if ( currentContext.getEast_car_count() > 0 && carpass_count < 2 )
        {
            results.storeNewResult("ACTION pass_car: Car passing from East side.");
            currentContext.EastCarPassing();
            carpass_count ++;
        }

        return currentContext.getCurrentState();
    }

    /**
     * Set North South West signals to RED
     */
    @Override
    public void set_otherSignals_RED() {
        currentContext.SetSignal("NORTH",false);
        currentContext.SetSignal("SOUTH",false);
        currentContext.SetSignal("EAST",true);
        currentContext.SetSignal("WEST",false);
    }

    /**
     * Set east signal to red
     * @param s
     */
    @Override
    public StreetLightsStateI set_East_Red() {
        return this.Set_ALL_Red();
    }

    /**
     * set north signal to red
     * @param s
     */
    @Override
    public StreetLightsStateI set_North_Red() {
        results.storeNewResult("ACTON set_North_Red : Set north signal to red");
        return currentContext.getCurrentState();
    }

    /**
     * set south signal to red
     * @param s
     */
    @Override
    public StreetLightsStateI set_South_Red() {
        results.storeNewResult("ACTON set_South_Red : Set south signal to red");
        return currentContext.getCurrentState();
    }

    /**
     * set west signal to red
     * @param s
     */
    @Override
    public StreetLightsStateI set_West_Red() {
        results.storeNewResult("ACTON set_West_Red : Set west signal to red");
        return currentContext.getCurrentState();
    }
};

