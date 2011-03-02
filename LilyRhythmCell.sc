/*
	LilyRhythmicCell.sc

	Use:
	a = LilyRhythmCell([4, [1, 1, [1, [1, 1, 1, 1]], 1, 1]])
	a.asLySequence

	Internal or with 1-deep Arrays:
	a = LyRhythmicCell([4, [1, 1, 1, 1, 1]])
	a.size
	a.tree
	a.adjustedList
	a.adjustedLy
	a.grupetto
	a.lyGrupetto
	a.sum
	a.asLispString
	a.asLyString

*/

LilyRhythmCell : LilyRhythmObj {

	classvar noteNotationScale, noteDurationScale, durationDict, eightNomeScale;
    classvar measureScaleLily, measureDict;

	var  <>size, <>tree, <tuplet, <>arrayTree, <>pitchStream, <tupletString;

	*new { arg arrayTree, pitchStream;
		^super.new.init(arrayTree, pitchStream);
	}

	init { arg arrayTree= [2, [1, 1, 1]], stream;
		#size, tree = arrayTree;
		pitchStream = stream;
	}

	// asLispString {
	// 	^this.tree.asLisp;
	// }

	sum {
		^tree.sum
	}

// 	asTimeSigString {
// 		^measureDict.findKeyForValue(size.round(0.5))
// 	}

	adjustedList {

        var factor, adjustedSum;
		factor = 1;
		adjustedSum = this.sum;

		while(
			{ adjustedSum < (size*8) },
			{ adjustedSum = adjustedSum * 2; factor = factor * 2 }
		);
		^tree * factor;
	}



	/////////////////////////////////////////////////////////
	// need work:

	adjustedLy {
		^this.adjustedList.collect({|i|
			durationDict.findKeyForValue(i)
		});
	}


	grupetto {
		
		^[
			(this.adjustedList.sum / (gcd((size * 8),this.adjustedList.sum))),
			((size * 8) / (gcd((size * 8), this.adjustedList.sum)))
		]
		
	}

}



