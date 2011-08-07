/*

    a = RhythmicSystem([2, 3, 4]) // <--- Init with measures
    a.putMeasure([2, 3, 2]) // <---you can add more later

    a.putStruct([[1, 1, 1], [1, 2, 1], [2, 1, 2]].scramble)
    // <-- this inserts a structure on the tail
    a.putStruct([[1, 2, 1], [1, 2, 1], [2, 1, 2]])
    a.putStruct([[1, 2, 1], [1, 2, 1, 1], [1, 2, 1]])

    a.plot

*/

RhythmicSystem : LilyShowableObj {

    var <>measures;
    var <>structs;
    var <>template="rhythmic";

    *new { arg thisMeasures;
        ^super.new.initRhythmicSystem(thisMeasures)
    }


    initRhythmicSystem { arg thisMeasures=nil;

        if(thisMeasures.notNil) {
            this.putMeasure(thisMeasures)
        };

    }

	nVoices {
		^this.structs.size
	}

	nMeasures {
		^this.measures.size
	}

    putMeasure { arg thisMeasure;

        case
        {thisMeasure.isKindOf(Number)}
        {measures = measures.add(thisMeasure)}

        {thisMeasure.isKindOf(Array)}
        {this.putMeasureList(thisMeasure)};
    }


    putMeasureList { arg thisMeasureList;

        thisMeasureList.do { |i|
            this.putMeasure(i)
        };
    }


    putStruct { arg thisStruct;

        structs = structs.add(thisStruct);
    }


    putStructList { arg thisStructMatrix;

        thisStructMatrix.do { |i|
            this.putStruct(i)
        };
    }


    // =====================================================================
    //  Manipulations on measures and struct
    // =====================================================================

    scrambleMeasures {
        this.measures_(this.measures.scramble)
    }

    rotateMeasures { arg n;
        this.measures_(this.measures.rotate(n))
    }

    insertMeasure { arg index, item;
        this.measures_(this.measures.insert(index, item))
    }

    permuteMeasures { arg n;
        this.measures_(this.measures.permute(n))
    }

    scrambleStructs {
        this.structs_(this.structs.scramble)
    }

    rotateStructs { arg n;
        this.structs_(this.structs.rotate(n))
    }

    insertStruct { arg index, item;
        this.structs_(this.structs.insert(index, item))
    }

    permuteStructs { arg n;
        this.structs_(this.structs.permute(n))
    }

    indicesOfEqual { arg thisValue;
        this.measures.indicesOfEqual(thisValue);
    }

    swapMeasures { arg i,j;
        this.measures_(this.measures.swap(i,j))
    }

    swapStructs { arg i,j;
        this.structs_(this.structs.swap(i,j))
    }


    // =====================================================================

    voices {

        var thisOut;
        thisOut=[];

        structs.size.do({ |i|
            thisOut = thisOut.add(
                RhythmicSeq([ measures, structs[i] ].flop)
            )
        });

        ^thisOut
    }


    string {

        var outString = String.new;

        this.voices.do { |i|
            outString = outString ++ i.staffString
        };
        ^outString
    }


    musicString {

        var outString = String.new;

        outString = outString ++ "\\score {\n\t<<\n\t\t";
        outString = outString ++ this.string;
        //  outString = outString ++ "\n \\midi { } \n\t \\layout { }\n";
        outString = outString ++ "\n  >> \n}\n\n";
        ^outString

    }

}

