
LilyChord : LilyShowableObj {


    var <>noteArray;
    var <>template = "just-pitches";


    *new { arg noteList;
        ^super.new.init(noteList);
    }


    init { arg firstStuff;

        noteArray = Array.new;
        this.put(firstStuff);
    }


    notenumber {

        ^noteArray.collect({ arg thisEvent;
            thisEvent.notenumber
        })
    }


    put { arg putThis;

        if(this.includes(putThis).not, {

            if(putThis.isKindOf(Number), {
                noteArray = noteArray.add(LilyPitch.new(putThis))
            });

            if(putThis.isKindOf(LilyPitch), {
                noteArray = noteArray.add(putThis)
            });

            if(putThis.isKindOf(Array), {
                this.putArray(putThis)
            });
        });
    }


    putArray { arg putThis;

        putThis.do { arg thisOne;
            this.put(thisOne);
        };

    }


    includes { arg testPitch;

        var thisTest;

        if(testPitch.isKindOf(LilyPitch),
            {thisTest = testPitch.notenumber},
            {thisTest = testPitch}
        );
        ^this.notenumber.includes(thisTest)
    }


    qt {
        ^noteArray.collect({|i| i.qt})
    }


    intervals  {
        var intervals, notes;

        notes = this.notenumber;
        (notes.size - 1).do({|i|
            intervals = intervals.add(notes[i + 1] - notes[i]) });
        ^intervals;
    }


    stringArray {

        ^this.noteArray.collect({|i| i.pitch ++ i.octave;})
    }


    string {

        var musicStringOut;

        musicStringOut = "< ";
        this.stringArray.do({|i|
            musicStringOut = musicStringOut ++ i ++ " "
        });

        ^musicStringOut ++ ">";
    }


	asPitchSeq {

		^LilyPitchSeq(this.noteArray)
	}


	gaussian { arg shake=0.5; 

		^LilyChord(this.notenumber.gaussian(shake).round(0.5)) 
	}


    ////////////////////////////////////////////////////
    // these are methods to manipulate chords as Sets //
    ////////////////////////////////////////////////////


    - { arg otherChord;

        ^LilyChord(this.notenumber.asSet - otherChord.notenumber.asSet)
    }


    -- { arg otherChord;

        ^LilyChord(this.notenumber.asSet -- otherChord.notenumber.asSet)
    }


    | { arg otherChord;

        ^LilyChord(this.notenumber.asSet | otherChord.notenumber.asSet)
    }


    & { arg otherChord;

        ^LilyChord(this.notenumber.asSet & otherChord.notenumber.asSet)
    }


    isSubsetOf { arg otherChord;

        ^LilyChord(this.notenumber.asSet.isSubsetOf(otherChord.notenumber.asSet))
    }

}
