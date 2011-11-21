/*
a = LilyPitchSeq([1, 3, 5]);
b = LilyPitchSeq([6, 8, 12]);
c = LilyPitchSeq([a,b,b,b,a,a,b]);
a.scramble.plot
c.string
b.plot
c.eventArray
(a ++ b).plot
(c +++ b).plot
[1,2,3].interlace([4, 5, 6])

*/

LilyPitchSeq : LilyShowableObj {

    var <>eventArray;
    var <>template = "just-pitches";

    *new {arg event;
        ^super.new.init(event);
    }


    init { arg eventList=nil;

        eventArray = Array.new;
        this.add(eventList);

    }

    add { arg thisEvent;

        case
        {(thisEvent.class == LilyPitch) || (thisEvent.class == LilyChord)}
        {eventArray = eventArray.add(thisEvent)}

        { thisEvent.isNumber }
        { eventArray = eventArray.add(LilyPitch(thisEvent))}

		{ thisEvent.isKindOf(LilyPitchSeq) }
		{ this.eventArray = this.eventArray.add(thisEvent.eventArray).flat }

        { thisEvent.class == Array }
        {
			if(thisEvent.rank == 1 ) { this.addAll(thisEvent)    };
			if(thisEvent.rank == 2 ) { this.addChords(thisEvent) };
		};

    }

    addAll { arg stuffIn;

        stuffIn.do { arg thisEvent;
            this.add(thisEvent)
        };
    }


    addChords { arg stuffIn;

        stuffIn.do { arg thisEvent;
            this.add(LilyChord(thisEvent))
        };
    }

    qt {
        ^eventArray.collect({arg i;
            i.qt})
    }



	// =====================================================================
	
	pick { arg n;
		^LilyPitchSeq(this.eventArray.scramble[0..(n-1)]);
	}
	
	
	// =====================================================================

	++ { arg otherSeq;

		if(otherSeq.isKindOf(LilyPitchSeq))
			{
				^LilyPitchSeq(this.eventArray ++ otherSeq.eventArray)
			} {
				"Wrong type: this must be a LilyPitchSeq".warn;
			};

	}


	+++ { arg otherSeq;

		if(otherSeq.isKindOf(LilyPitchSeq))
			{
				^LilyPitchSeq(this.eventArray +++ otherSeq.eventArray)
			} {
				"Wrong type: this must be a LilyPitchSeq".warn;
			};

	}

	// =====================================================================


	size { ^eventArray.size }

	at { arg index; ^eventArray.at(index) }

	choose { ^eventArray.choose }

	wchoose { arg probs; ^eventArray.wchoose(probs) }


	// =====================================================================


	chooseOne { ^LilyPitchSeq(this.choose)}

	chooseN { arg n;  
		var srb;
		srb = this.eventArray.scramble;
		eventArray = (0..n).collect({|i| srb[i] })	
	}

    perfectShuffle { eventArray = eventArray.perfectShuffle }

	swap     { arg i,j; eventArray = eventArray.swap(i,j) }

	scramble { eventArray = eventArray.scramble }

    mirror   { eventArray = eventArray.mirror }

    mirror1  { eventArray = eventArray.mirror1 }

    mirror2  { eventArray = eventArray.mirror2 }

    stutter  { arg n=2; eventArray = eventArray.stutter(n) }

	rotate   { arg n=1; eventArray = eventArray.rotate(n) }

	pyramid  { arg n=1; eventArray = eventArray.pyramid(n) }

	pyramidg { arg n=1; eventArray = eventArray.pyramidg(n) }

	sputter  { arg probability, maxlen; eventArray = eventArray.sputter(probability, maxlen) }

	lace     { arg lenght; eventArray = eventArray.lace(lenght) }

	permute  { arg nthPermutation; eventArray = eventArray.permute(nthPermutation) }

	slide    { arg windowLenght, stepSize; eventArray = eventArray.slide(windowLenght, stepSize) }


	// =====================================================================


    string {
        var outString = "";
        eventArray.do { arg thisEvent; outString = outString ++ thisEvent.string ++ "\n" };
        ^outString;
    }


    staffString { ^( "\\new PianoStaff {\n \\autochange {\n" ++ this.string ++ "\n}}" ) }


    /* The resulting LilyPond code */
    musicString { ^("\\score { \n" ++ this.staffString ++ "\n \\midi { } \n\t \\layout { }\n}") }

}
