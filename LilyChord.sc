 /* 
	LilyChord.sc
	
	c = LilyChord.new([10, 11])
	c.templateList
	c.notenumbe
	c.noteArray
	c.qt
	c.stringArray
	c.string
	c.musicString
	c.intervals
	c.write
	c.plot
	c.edit

    -- Set Operations --
	a = LilyChord.New([1, 2, 3])
	b = LilyChord.New([2, 3, 4])
	c = a - b
	c.notenumber
*/ 

LilyChord : LilyShowableObj {

	var <>notenumber, <>noteArray;

	*new {|noteList|
		^super.new.init(noteList);
	}


	init { |noteList|

		// TODO:
		// it should be possible to initialize Chord
		// with a Array of Note Objects
		notenumber = Array.new;
		noteArray = Array.new;
		noteList.do({arg thisNote;
			notenumber = notenumber.add(thisNote);
			noteArray = noteArray.add(LilyNote.new(thisNote));
		})
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
        // same as .stringArray,
        // but puts everything in one single String
        // with < > (LilyPond synthax)
		var musicStringOut;
		musicStringOut = "< ";
		this.stringArray.do({|i|
			musicStringOut = musicStringOut ++ i ++ " "
		});
		^musicStringOut ++ ">";
	}

	// NOTE
	// these are methods to manipulate chords as Sets

	- { | otherChord |

		^LilyChord(this.notenumber.asSet - otherChord.notenumber.asSet)
	}


	-- { | otherChord |
		^LilyChord(this.notenumber.asSet -- otherChord.notenumber.asSet)
	}


	| { | otherChord |
		^LilyChord(this.notenumber.asSet | otherChord.notenumber.asSet)
	}


	& { | otherChord |
		^LilyChord(this.notenumber.asSet & otherChord.notenumber.asSet)
	
    }

	isSubsetOf { | otherChord |

		^LilyChord(this.notenumber.asSet.isSubsetOf(otherChord.notenumber.asSet))
	}

}