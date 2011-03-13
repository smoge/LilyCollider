
LilyPitchSeq : LilyShowableObj {

    var <>eventArray;
	var <>template = "just-pitches";
	
    *new {arg event;
        ^super.new.init(event);
    }


    init { arg eventList=nil;

        eventArray = Array.new;
        this.put(eventList);

    }

    put { arg thisEvent;

        case
        {(thisEvent.class == LilyPitch) || (thisEvent.class == LilyChord)}
        {eventArray = eventArray.add(thisEvent)}

        { thisEvent.isNumber }
        { eventArray = eventArray.add(LilyPitch(thisEvent))}

        { thisEvent.class == Array }
        { this.putArray(thisEvent) };

    }

    putArray { arg stuffIn;

        stuffIn.do { arg thisEvent;
            this.put(thisEvent)
        };
    }

    qt {
        ^eventArray.collect({arg i;
            i.qt})
    }

    string {

        var outString = "";

        eventArray.do { arg thisEvent;
            outString = outString ++ thisEvent.string ++ "\n";
        };

        ^outString;
    }

}
