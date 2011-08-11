+ Array {

/*
 * method  for substitution of a
 * Number with a Structure
 * inside a Structure:
 *
 * a = [3, 2, 1, 2, 1]
 * a.subst(3, [2, 1, 2]) // ==> [ 3, 2, 1, [ 2, [ 2, 1, 2 ] ], 1 ]
 *
 * This actually is a subdivision of a element in our score representation
 *
 */

    subst { arg index, thisStruct;

        case
        {thisStruct.isKindOf(Array)}
        {^this.put(index, [this.at(index), thisStruct])}

        {thisStruct.isKindOf(Number)}
        {^this.put(index, thisStruct)};
    }


    gdc {

        if(this.containsSeqColl.not, {
            ^this.reduce({arg a, b; a.asInteger gcd: b.asInteger})
        });
    }


    max {

        if(this.containsSeqColl.not, {
            ^this.reduce({arg a, b; a max: b})
        });
    }


    min {

        if(this.containsSeqColl.not, {
            ^this.reduce({arg a, b; a min: b})
        });
    }


    minSize {

        ^(this.collect {|i| i.size}).min
    }


    maxSize {

        ^(this.collect {|i| i.size}).max
    }


    asRhythmicCell {

        ^RhythmicCell(this)
    }


    asRC {
        ^RhythmicCell(this)
    }


    asRhythmicSeq {

        ^RhythmicSeq(this)
    }


    asRS {

        ^RhythmicSeq(this)
    }


    addRC { arg thisRSeq;

        thisRSeq.put(this)
    }


    addRS { arg thisRSeq;

        this.do { |i|
            thisRSeq.put(i)
        };
    }


}