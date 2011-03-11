+ Array {

	
	gdc {
	
		if(this.containsSeqColl.not, {
			^this.reduce({arg a, b; a.asInteger gcd: b.asInteger})
		});	
	}
	
	
	asRC {

		^RhythmicCell(this)
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