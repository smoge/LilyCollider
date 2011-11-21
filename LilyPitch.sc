
LilyPitch : LilyShowableObj {


    var <notenumber, <>duration;
    var <pitch, <octave, <artic, <>afterNote, <>beforeNote;
	var <>template = "just-pitches";
    
    *new { arg notenumber;
        ^super.new.init(notenumber);
    }


    init { arg thisnotenumber;
        this.notenumber_(thisnotenumber);
        this.afterNote = [];
        this.beforeNote = [];
    }


    qt {
        ^(notenumber.frac.round(0.5) == (0.5))
    }



    notenumber_ { arg newnotenumber;

        var index, octIndex, notenumberFloor, noteNumberFloor;

        notenumber = newnotenumber;
        notenumberFloor = notenumber.floor;
        pitch = pitchList.at(notenumberFloor % 12);

        ((notenumber - notenumberFloor).round(0.5) == (0.5)).if({
            pitch = pitch ++ "ih"
        });
		
        octIndex = ((notenumber+60)/12).floor;
        octave = octaveList.at(octIndex);
    }


    string {
		var thisOut = pitch ++ octave;
		if(thisOut.isNil) {
			^""
		}{
			^thisOut; 
		};
    }


    putAfterNote { arg newArticulation;
    
        if (afterNote.includes(newArticulation) == false,
            {afterNote = afterNote.add(newArticulation)},
            {"This Array already contains this string".warn}
        )
    }


    putBeforeNote { arg newArticulation;
        // some articulations needs to be placed *before* the pitch, right?
        if(
            // again: let's check if it's already there
            beforeNote.includes(newArticulation) == false,
            {beforeNote = beforeNote.add(newArticulation)},
            {"This Array already contains this string".warn}
        )
    }


    t { arg value;
        // transpose the note with a number (interval)
        // is there a need to make a Interval class?
        this.notenumber = this.notenumber + value;
    }


    lily_ { arg newLilyString;

        /*  Change the pitch with a new string in Lilypond Format
            Have to separate pitchclass and octave data
            as always: c' = 0   */

        var newPitchString, newOctString;

        newPitchString = "";
        newOctString = "";

        newLilyString.do({|i|

            var string = i.asString;

            case
            { (string != "'").and(string != ",") }
            { newPitchString = newPitchString ++ string }

            {(string == "'").or(string == ",") }
            {newOctString = newOctString ++ string};

        });

        this.notenumber = pitchDict[newPitchString] + (octDict[newOctString] * 12);

    }


    cps_ { arg newCps;
        // set the Pitch with its frequency in Hz
        this.notenumber = (newCps.cpsmidi - 60).round(0.5)
    }


    == { arg otherPitch;

        if(
            otherPitch.isKindOf(LilyPitch),
            {^this.notenumber == otherPitch.notenumber},
            {"This is not a LilyPitch Object".error}
        );
        
    }


    makeHarmonicSeries { arg lo=0, high=15, roundNotes=0.5;

        var outputArray, thisCps, numberOfNotes;

        thisCps = (this.notenumber + 60).midicps;
        numberOfNotes = high-lo;
        outputArray = Array.new;
        (lo..high).do({|i|
            outputArray = outputArray.add(
                thisCps + (thisCps * i))
        });

        // returns a PitchSequence:
        ^LilyChord(outputArray.cpsmidi.round(roundNotes) - 60)
    }

}