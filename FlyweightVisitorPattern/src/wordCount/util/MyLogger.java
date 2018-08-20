package wordCount.util;

public class MyLogger
{
    public static enum DebugLevel { RESULTS, ERROR, CONSTRUCTOR, FILE_PROCESSOR, DEBUG, NONE, INFO
    };

    private static DebugLevel debugLevel;


    public static void setDebugValue (int levelIn) {
        switch (levelIn)
        {
        	case 6:
            debugLevel = DebugLevel.INFO; break;
            case 5:
                debugLevel = DebugLevel.DEBUG; break;
            case 4:
                debugLevel = DebugLevel.ERROR; break;
            case 3:
                debugLevel = DebugLevel.RESULTS; break;
            case 2:
                debugLevel = DebugLevel.CONSTRUCTOR; break;
            case 1:
                debugLevel = DebugLevel.FILE_PROCESSOR; break;
            case 0:
                debugLevel = DebugLevel.NONE; break;
        }
    }

    public static void setDebugValue (DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    public static void writeMessage (String     message  ,
                                     DebugLevel levelIn ) {
        if (levelIn == debugLevel)
            System.out.println(message);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
};
