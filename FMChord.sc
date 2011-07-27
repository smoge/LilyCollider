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

*/


FMChord  {

    var <>car, <>mod, <>index;

    *new {|car, mod, index|
        ^super.new.init(car, mod, index);
    }

    init {arg thiCar = 7, thiMod = -4, thisIndex = 9;

        car = thiCar;
        mod = thiMod;
        index = thisIndex;
    }

    addChord {
        var addChordCps;
        addChordCps = index.collect {|i|
            (car+60).midicps + ((i+1) * (mod+60).midicps);
        };
        ^LilyChord.new(addChordCps.cpsmidi - 60);
    }


    diffChord {
        var diffChordCps;
        diffChordCps = index.collect {|i|
            (car+60).midicps - ((i+1) * (mod+60).midicps);
        };
        ^LilyChord.new(diffChordCps.cpsmidi - 60);
    }

    fmChord {
        ^LilyChord.new(
            (this.diffChord.notenumber) ++ (this.addChord.notenumber)
        );
    }

}