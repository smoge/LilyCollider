+ Array {


	gdc {
		
		if(this.containsSeqColl.not, {
			^this.reduce({arg a, b; a gcd: b})
		});	
	}


	asRC {

		^RhythmCell(this)
	}

	asPseudoBar {
		
		var thisLenght, thisStruct;
		
		#thisLenght, thisStruct = this;
		thisLenght = (thisLenght.asFloat / 8).round(0.5);
		^[thisLenght, thisStruct];
	}

}