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
		var carFreq, modFreq, out;
		carFreq = (car+60).midicps;
		modFreq = (mod+60).midicps;
		out = [carFreq, modFreq];

        index.do {|i|
            out = out ++ ((car+60).midicps + (i * (mod+60).midicps));
        };
		^out
	}


	addMidi {
		^this.addFreqs.cpsmidi.round(thisRound)
	}


    addChord {
        ^LilyChord(this.addMidi.wrap(35,100) - 60);
    }


	diffFreqs {
		var carFreq, modFreq, out;
		carFreq = (car+60).midicps;
		modFreq = (mod+60).midicps;
		out = [carFreq, modFreq];

		index.do {|i|
            out = out ++ ((car+60).midicps - (i * (mod+60).midicps));
        };
		^out
	}


	diffMidi {
		^this.diffFreqs.cpsmidi.round(thisRound)

	}


    diffChord {
        ^LilyChord(this.diffMidi.wrap0(35,100) - 60);
    }


    /* Chord equivalent to all frequencies */
    fmChord {

        ^LilyChord.new(
            (this.diffChord.notenumber) ++ (this.addChord.notenumber)
        );

    }

}