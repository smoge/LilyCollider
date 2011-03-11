
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

	
	musicString {

		// overrides to print on a one-line staff
		^(
			"\\score { \n" ++ 
			"\\new RhythmicStaff {" ++ this.string ++ "\n}" ++
			"\n \\midi { } \n\t \\layout { }\t}"
		)
			
	}


}
