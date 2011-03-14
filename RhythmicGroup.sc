
RhythmicGroup : LilyShowableObj {

	var <>voiceArray;
	var <>template="rhythmic";

	*new { arg stuffIn;

		^super.new.initRhythmicGroup(stuffIn);
	}
	

	initRhythmicGroup { arg stuffIn=nil;
		
		if(stuffIn.notNil, {
			this.put(stuffIn)
		});
	}


	put { arg stuffIn;
	
		case
		{stuffIn.isKindOf(RhythmicSeq)}	
		{voiceArray = voiceArray.add(stuffIn)}

		{stuffIn.isKindOf(Array)}
		{ this.putArray(stuffIn)};
	
	}


	putArray { arg thisArray;

		thisArray.do { |i|
			
			case
			{i.isKindOf(RhythmicSeq)}	
			{this.put(i)}
			
			{i.isKIndOf(Array)}	
			{this.put(i.asRS)};
		}
	}


	string {
	
		var outString = String.new;

		this.voiceArray.do { |i|
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



