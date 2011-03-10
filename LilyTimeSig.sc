/*

LilyPond Time Signature Class

t = LilyTimeSig(3, 4)
t.numBeats // -> 3
t.noteValue // -> 4
t.timeSigArray // -> [3, 4]

This can be useful for manipulations on measures sequences

*/

LilyTimeSig {

    var <>numBeats, <>noteValue, <>timeSigArray;

    
    *new {arg numBeats, noteValue;
        ^super.new.initTimeSig(numBeats, noteValue);
    }


    initTimeSig { arg thisNumBeats, thisNoteValue;

        this.numBeats_(thisNumBeats);
        this.noteValue_(thisNoteValue);
        this.timeSigArray_([thisNumBeats, thisNoteValue]);
    }

}
