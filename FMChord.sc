/*
    'Frequency Modulation' Chord
    Arguments: carrier, modulator, index

    Use:

    a = FMChord.new(7, -5, 9)

    a
    a.index
    a.car
    a.car = 4
    a.mod
    a.addChord.plot
    a.diffChord.plot
    a.fmChord.plot
    a.fmChord.asPitchSeq.plot
    a.fmChord.asPitchSeq.playMidi;

*/


FMChord  {

    var <>car, <>mod, <>index;
	var <>thisRound = 0.5;

    *new {|car, mod, index|
        ^super.new.init(car, mod, index);
    }

    init {arg thiCar = 7, thiMod = -4, thisIndex = 9;

        car = thiCar;
        mod = thiMod;
        index = thisIndex;
    }

	addFreqs {
        ^index.collect {|i|
            (car+60).midicps + ((i+1) * (mod+60).midicps);
        };

	}


	addMidi {
		^this.addFreqs.cpsmidi.round(thisRound)
	}


    addChord {
        ^LilyChord(this.addMidi - 60);
    }


	diffFreqs {
		^index.collect {|i|
            (car+60).midicps - ((i+1) * (mod+60).midicps);
        };
	}


	diffMidi {
		^this.diffFreqs.cpsmidi.round(thisRound)

	}


    diffChord {
        ^LilyChord(this.diffMidi - 60);
    }


    /* Chord equivalent to all frequencies */
    fmChord {

        ^LilyChord.new(
            (this.diffChord.notenumber) ++ (this.addChord.notenumber)
        );

    }

}