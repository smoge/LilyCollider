
LilyNote : LilyShowableObj {

	classvar <pitchList, <octaveList, <pitchDict, <octDict;
	classvar <afterNoteDict, <beforeNoteDict;
	
	var <>fileName = "~/Desktop/LilyCollider";
	var <>pdfViewer = "okular";
	var <>textEditor = "frescobaldi"; // "jedit"
	
	var <notenumber, <>duration/*, qt*/;
	var <pitch, <octave, <artic, <>afterNote, <>beforeNote;
    
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
		// returns a string with pitch and octave
		// as the LilyPond format
		^(pitch ++ octave);
	}

	putAfterNote { | newArticulation |
		// this can the used(?) as a general way to set articulations
		if (
			// first check if the articulations is already there
			afterNote.includes(newArticulation) == false,
			{afterNote = afterNote.add(newArticulation)},
			{"This Array already contains this string".warn}
		)
	}

	putBeforeNote { | newArticulation |
		// some articulations needs to be placed *before* the pitch, right?
		if(
			// again: let's check if it's already there
			beforeNote.includes(newArticulation) == false,
			{beforeNote = beforeNote.add(newArticulation)},
			{"This Array already contains this string".warn}
		)
	}

	t { | value |
		// transpose the note with a number (interval)
		// is there a need to make a Interval class?
		this.notenumber = this.notenumber + value;
	}


 	lily_ { | newLilyString |
		// Change the pitch with a new string in Lilypond Format
		// Have to separate pitchclass and octave data
		// as always: c' = 0
		var newPitchString, newOctString;
		newPitchString = "";
		newOctString = "";

		newLilyString.do({|i|
			var string = i.asString;

			case { (string != "'").and(string != ",") } {
				newPitchString = newPitchString ++ string }

			{(string == "'").or(string == ",") } {
				newOctString = newOctString ++ string};

		});

		this.notenumber = pitchDict[newPitchString] + (octDict[newOctString] * 12);

	}


 	cps_ { arg newCps;
		// set the Pitch with its frequency in Hz
		this.notenumber = (newCps.cpsmidi - 60).round(0.5)
	}


	musicString {
		// same as .string but with curly brackets
		^("{ " ++  this.string ++ "}").asString;
	}


	== { arg otherNote;

		if(
			otherNote.class != Note,
			{"This is not a Note Object".warn}
		);
		
		^this.notenumber == otherNote.notenumber;
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
		^PitchSequence(outputArray.cpsmidi.round(roundNotes) - 60)
	}

*initClass {
	// default LilyPond names...
	// this is the dutch default names
	pitchList = ["c","cis","d","dis","e","f","fis", "g", "gis","a", "ais", "b"];
	octaveList =  [",,,,",",,,",",,",",", " ","'","''","'''", "''''"];
	pitchDict = Dictionary[
	"c" -> 0,
	"cih" -> 0.5,
	"cis" -> 1,
	"des" -> 1,
	"cisih" -> 1.5,
	"deh" -> 1.5,
	"d" -> 2,
	"dih" -> 2.5,
	"eeseh" -> 2.5,
	"dis" -> 3,
	"ees" -> 3,
	"disih" -> 3.5,
	"eeh" -> 3.5,
	"e" -> 4,
	"eis" -> 4.5,
	"feh" -> 4.5,
	"f" -> 5,
	"fih" -> 5.5,
	"geseh" -> 5.5,
	"fis" -> 6,
	"ges" -> 6,
	"fisih" -> 6.5,
	"geh" -> 6.5,
	"g" -> 7,
	"gih" -> 7.5,
	"aeseh" -> 7.5,
	"gis" -> 8,
	"aes" -> 8,
	"gisih" -> 8.5,
	"aeh" -> 8.5,
	"a" -> 9,
	"aih" -> 9.5,
	"beseh" -> 9.5,
	"ais" -> 10,
	"bes" -> 10,
	"aisih" -> 10.5,
	"beh" -> 10.5,
	"b" -> 11,
	"bih" -> 11.5,
	"ceh" -> 11.5
	];
	
	octDict = Dictionary[
	",,," -> -4,
	",," -> -3,
	"," -> -2,
	"" -> -1,
	"'" -> 0,
	"''" -> 1,
	"'''" -> 2,
	"''''" -> 3
	];
	
}

}