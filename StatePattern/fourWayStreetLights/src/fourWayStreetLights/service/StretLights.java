package fourWayStreetLights.service;

import fourWayStreetLights.util.Logger;
import fourWayStreetLights.util.FileProcessor;
import fourWayStreetLights.util.Results;

import java.util.HashMap;

public class StretLights
{

    private StreetLightsStateI NorthGreen;
    private StreetLightsStateI SouthGreen;
    private StreetLightsStateI WestGreen;
    private StreetLightsStateI EastGreen;
    private StreetLightsStateI AllRed;

    private StreetLightsStateI State;

    private int north_car_count , south_car_count, east_car_count, west_car_count;

    private Results result;
    private FileProcessor FileReader;
    private StreetLightsHelper helper;
    private HashMap<String,Boolean> TrafficSignal;

    /**
     * Constructor
     */
    public StretLights( String inFile, String outFile , Results res)
    {
        Logger.writeMessage(this.getClass().getName() + " Constructor is called ", Logger.DebugLevel.CONSTRUCTOR);

        result = res;
        FileReader = new FileProcessor(inFile);
        helper = new StreetLightsHelper();
        TrafficSignal = new HashMap<String, Boolean>();
        TrafficSignal.put("NORTH",false);
        TrafficSignal.put("SOUTH",false);
        TrafficSignal.put("EAST",false);
        TrafficSignal.put("WEST",false);

        NorthGreen = new GreenNorth(this, result);
        SouthGreen = new GreenSouth(this,result);
        EastGreen = new GreenEast(this, result);
        WestGreen = new GreenWest(this, result);
        AllRed = new RedAll(this, result);

        State = AllRed;     // initialize current state to all red

        north_car_count = 0;
        south_car_count = 0;
        east_car_count = 0;
        west_car_count = 0;

        this.Simulate();
    }


    /**
     * Parse input file line by line,
     * and perform operations accordingly
     */
    public void Simulate()
    {
        result.storeNewResult("Initial STATE : AllRed");
        String line;
        while ( ( line = FileReader.readLine() ) != null )
        {
            if ( helper.isCarParkingLine(line) )
            {
                int num_of_cars = helper.returnCarNo(line);

                if ( helper.carParkingSide(line).contains("N") )
                {
                    north_car_count = north_car_count + num_of_cars;
                }
                else if ( helper.carParkingSide(line).contains("S") )
                {
                    south_car_count = south_car_count + num_of_cars;
                }
                else if ( helper.carParkingSide(line).contains("E") )
                {
                    east_car_count = east_car_count + num_of_cars;
                }
                else if ( helper.carParkingSide(line).contains("W") )
                {
                    west_car_count = west_car_count + num_of_cars;
                }
            }
            else if ( helper.isSignalline(line) )
            {
                if (helper.getSignalLight(line).contains("RED") || helper.getSignalLight(line).contains("Red"))
                {
                    if (helper.getSignal(line).contains("N"))
                    {
                        State = this.set_North_Red();
                    }
                    else if (helper.getSignal(line).contains("S"))
                    {
                        State = this.set_South_Red();
                    }
                    else if (helper.getSignal(line).contains("E"))
                    {
                        State = this.set_East_Red();
                    }
                    else if (helper.getSignal(line).contains("W"))
                    {
                        State = this.set_West_Red();
                    }
                }
                else if (helper.getSignalLight(line).contains("GREEN") || helper.getSignalLight(line).contains("Green"))
                {
                    if (helper.getSignal(line).contains("N"))
                    {
                        State = this.Set_North_Green();
                    }
                    else if (helper.getSignal(line).contains("S"))
                    {
                        State = this.Set_South_Green();
                    }
                    else if (helper.getSignal(line).contains("E"))
                    {
                        State = this.Set_East_Green();
                    }
                    else if (helper.getSignal(line).contains("W"))
                    {
                        State = this.Set_West_Green();
                    }
                }
                else
                {
                    Logger.writeMessage("Invalid line found in file : " + line, Logger.DebugLevel.ERROR);
                }
            }
            else
            {
                // Invalid line found in file
                Logger.writeMessage("Invalid line found in file : " + line, Logger.DebugLevel.ERROR);
            }
            State = this.pass_car();
            State = this.pass_car();
        }
        Logger.writeMessage(this.getClass().getName() + " " + result.toString(), Logger.DebugLevel.RESULTS);
    }

    /**
     *
     * @return : The current state the State machine is in
     */
    public StreetLightsStateI getCurrentState()
    {
        return State;
    }

    /**
     * @return : StreetLightsStateI
     */
    public StreetLightsStateI getNorthGreen()
    {
        return NorthGreen;
    }

    /**
     * @return : StreetLightsStateI
     */
    public StreetLightsStateI getSouthGreen()
    {
        return SouthGreen;
    }

    /**
     * @return : StreetLightsStateI
     */
    public StreetLightsStateI getWestGreen()
    {
        return WestGreen;
    }

    /**
     * @return : StreetLightsStateI
     */
    public StreetLightsStateI getEastGreen()
    {
        return EastGreen;
    }

    /**
     * @return : StreetLightStateI
     */
    public StreetLightsStateI getAllRed()
    {
        return AllRed;
    }

    /**
     * @return no of cars in north side
     */
    public int getNorth_car_count()
    {
        return north_car_count;
    }

    /**
     * @return no of cars in south side
     */
    public int getSouth_car_count()
    {
        return south_car_count;
    }

    /**
     * @return no of cars in east side
     */
    public int getEast_car_count()
    {
        return east_car_count;
    }

    /**
     * @return no of cars in west side
     */
    public int getWest_car_count()
    {
        return west_car_count;
    }

    /**
     * Change signal from red to green
     * @param signal
     * @param status
     */
    public void SetSignal(String signal, Boolean status )
    {
        this.TrafficSignal.put(signal,status);
    }

    /**
     * @param signal
     * @return The status of a signal
     */
    public Boolean GetSignalStatus(String signal)
    {
        return this.TrafficSignal.get(signal);
    }

    /**
     * Reduce car count on north side by 1
     */
    public void NorthCarPassing()
    {
        if ( north_car_count > 0 )
        {
            north_car_count --;
        }

    }

    /**
     * Reduce car count on south side by 1
     */
    public void SouthCarPassing()
    {
        if ( south_car_count > 0 )
        {
            south_car_count--;
        }
    }

    /**
     * Reduce car count on east side by 1
     */
    public void EastCarPassing()
    {

        if ( east_car_count > 0 )
        {
            east_car_count --;
        }
    }

    /**
     * Reduce car count on west side by 1
     */
    public void WestCarPassing()
    {

        if ( west_car_count > 0 )
        {
            west_car_count --;
        }
    }

    /**
     * Calls the method related to state
     */
    private StreetLightsStateI Set_West_Green()
    {
        return State.Set_West_Green();
    }

    /**
     * Calls the method related to state
     */
    private StreetLightsStateI Set_South_Green()
    {
        return State.Set_South_Green();
    }

    /**
     * Calls the method related to state
     */
    private StreetLightsStateI Set_East_Green()
    {
        return State.Set_East_Green();
    }

    /**
     * Calls the method related to state
     */
    private StreetLightsStateI Set_North_Green()
    {
        return State.Set_North_Green();
    }

    /**
     * Call the method related to state
     */
    private void set_otherSignals_RED()
    {
        State.set_otherSignals_RED();
    }

    /**
     * Call the method related to state
     */
    private StreetLightsStateI Set_ALL_Red()
    {
        return State.Set_ALL_Red();
    }

    /**
     * Call the method related to state
     */
    private StreetLightsStateI pass_car()
    {
        return State.pass_car();
    }

    /**
     * Calls the method related to state
     */
    private StreetLightsStateI set_West_Red()
    {
        return State.set_West_Red();
    }

    /**
     * Calls the method related to state
     */
    private StreetLightsStateI set_South_Red()
    {
        return State.set_South_Red();
    }

    /**
     * Calls the method related to state
     */
    private StreetLightsStateI set_East_Red()
    {
        return State.set_East_Red();
    }

    /**
     * Calls the method related to state
     */
    private StreetLightsStateI set_North_Red()
    {
        return State.set_North_Red();
    }
};