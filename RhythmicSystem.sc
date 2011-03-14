
/*
a = RhythmicSystem()
a.putMeasure([2, 3, 2])
a.putStruct([[1, 1, 1], [1, 2, 1], [2, 1, 2]])
a.putStruct([[1, 2, 1], [1, 2, 1], [2, 1, 2]])
a.putStruct([[1, 2, 1], [1, 2, 1, 1], [1, 2, 1]])

a.voices
a.plot
a.structs.minSize
a.measures
*/

RhythmicSystem : LilyShowableObj {

	var <>measures;
	var <>structs;
	var <>template="rhythmic";

	*new { 
		^super.new.initRhythmicGroup
	}
	

	initRhythmicGroup {

		
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
	
	/* a = [~measures, ~structs1].flop.asRS;
	* b = [~measures, ~structs2].flop.asRS;
	* c = [~measures, ~structs3].flop.asRS;
	*/

	voices {

		var thisOut;
		thisOut=[];
	
		structs.minSize.do({ |i|
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
		//	outString = outString ++ "\n \\midi { } \n\t \\layout { }\n";
		outString = outString ++ "\n  >> \n}\n\n";
		^outString
	
	}

}

