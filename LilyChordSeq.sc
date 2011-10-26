/*

	a = LilyChord([1, 3, 5]);
	b = LilyChordSeq(a);
	b.put(a);
	b.string;
	b.notenumber;
	b.qt;

	b.plot;
	b.edit;
*/

LilyChordSeq : LilyShowableObj {

    var <>chordArray;
    var <>template = "just-pitches";

    *new {arg chordList;
        ^super.new.init(chordList)
    }


    init { arg firstStuff;

        chordArray = Array.new;
        this.put(firstStuff);
    }


    put { arg putThis;

		if(putThis.isKindOf(LilyChord)) {
			chordArray = chordArray.add(putThis)
		};
		
		if(putThis.isKindOf(Array)) {
			this.putArray(putThis)
		};

    }


    putArray { arg putThis;

        putThis.do { |i|

            if(i.isKindOf(LilyChord)) {
                this.put(i)
            };
			
			if(i.isKindOf(Array)) {
				this.put(LilyChord(i))
			};


        }

    }

    // includes { arg testChord;

    //     var thisTest;

    //     if(testChord.isKindOf(LilyChord),
    //         {thisTest = testChord.notenumber},
    //         {thisTest = testChord}
    //     );

    //     ^this.notenumber.includes(thisTest)
    // }


    qt { ^chordArray.collect({|i| i.qt}) }

    notenumber { ^chordArray.collect({|i| i.notenumber}) }

    intervals { ^chordArray.collect({|i| i.intervals }) }


	string {
		
		var stringOutput = "";
		
		this.chordArray.do { |i|
			stringOutput = stringOutput ++ i.string ++ "\n"
		};

		^stringOutput;
	}

}
