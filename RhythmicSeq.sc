
RhythmicSeq : LilyShowableObj {

    var <>eventArray;

    *new {arg event;
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

        { (thisEvent.isKindOf(Array)).and(thisEvent.size == 2).and(thisEvent.at(0).isKindOf(Number)) }
        { this.putArray(thisEvent.asRC) }

		{ (thisEvent.isKindOf(Array)).and(thisEvent.at(0).isKindOf(Array))}
		{
			thisEvent.do({ arg stuffIn;
				this.put(stuffIn)
			});
		}; 
			

	}

    string {

        var outString = "";

        eventArray.do { arg thisEvent;
            outString = outString ++ thisEvent.string ++ "\n";
        };

        ^outString;
    }

	
	musicString {
		// overrides to print on a one-line staff
		^("\\new RhythmicStaff {" ++ this.string ++ "\n}"
		)
			
	}


}
