package fourWayStreetLights.service;

import fourWayStreetLights.util.Logger;

public class StreetLightsHelper {


    /**
     * Empty constructor
     */
    public StreetLightsHelper()
    {
        Logger.writeMessage(this.getClass().getName() + " Constructor is called ", Logger.DebugLevel.CONSTRUCTOR);
    }

    /**
     *
     * @param line
     * @return True if line is car parking line, false otherwise
     */
    public boolean isCarParkingLine(String line)
    {
        if ( line.contains("-") && ( line.contains("N") || line.contains("S") || line.contains("E") || line.contains("W") ) )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     *
     * @param line
     * @return True if its a signal line false otherwise
     */
    public boolean isSignalline(String line)
    {
        if ( line.contains("@") && ( line.contains("N") || line.contains("S") || line.contains("E") || line.contains("W") ) )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     *
     * @param line
     * @return No of cars parked on a side
     */
    public int returnCarNo(String line)
    {
        String[] num = line.split("-");
        String number = num[1];
        try
        {
            return Integer.parseInt(number);
        }
        catch ( NumberFormatException e)
        {
            Logger.writeMessage("Invalid line found in file : " + line + e.toString(), Logger.DebugLevel.ERROR);
            System.err.println(e);
            return 0;
        }
    }

    /**
     * Parse line, get side on which cars are positioned
     * @param line
     * @return Side of signal
     */
    public String carParkingSide(String line)
    {
        String[] num = line.split("-");
        return num[0];
    }

    /**
     * Parse input line
     * @param line
     * @return signal from string, NSEW
     */
    public String getSignal( String line )
    {
        String[] signal = line.split("@");
        return signal[0];
    }

    /**
     * Parse input line
     * @param line
     * @return signal from string, NSEW
     */
    public String getSignalLight( String line )
    {
        String[] signal = line.split("@");
        return signal[1];
    }
}
