
RhythmicSeq : LilyShowableObj {

    var <>eventArray;
    var <>template = "rhythmic";

    *new { arg event;

        ^super.new.init(event);
    }


    init { arg eventList=nil;

        eventArray = Array.new;
        this.put(eventList);
    }


    put { arg thisEvent;

        case
        {(thisEvent.class == RhythmicCell)}
        {eventArray = eventArray.add(thisEvent)}

        {thisEvent.isKindOf(Array)}
        {
            thisEvent.do { |i|
                case
                {i.isKindOf(RhythmicCell)}
                {this.put(i)}
                // it must be a valid RhythmmicCell
                // need more checks here
                {i.isKindOf(Array)}
                {this.put(RhythmicCell(i))};
            }
        };
    }

    string {

        var outString = "";

        eventArray.do { arg thisEvent;
            outString = outString ++ thisEvent.string ++ "\n";
        };

        ^outString;
    }


    staffString {

        ^(
            "\\new RhythmicStaff {\n" ++ this.string ++ "\n}"
        )

    }

    musicString {

        ^(
            "\\score { \n" ++
            this.staffString ++
            "\n \\midi { } \n\t \\layout { }\n}"
        )

    }


}
