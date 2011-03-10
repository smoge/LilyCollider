
RhythmCell : LilyRhythmObj {


	var  <>struct, <>lenght;
	var <>template="rhythmic";
	
	*new { arg setThisLenght, setThisStruct;

		^super.new.initRhythmCell(setThisLenght, setThisStruct);
	}


	initRhythmCell { arg thisLenght, thisStruct;

		this.lenght_(thisLenght);
		this.struct_(thisStruct);
	}


	heads {	

		^this.getHeads(struct);
	}
	

	adjustedStruct { 
	
		^struct.collect({|i,j|  
			
			case
			{ i.isKindOf(Number) }	
			{ this.adjustedHeads[j] }
			
			{ i.isKindOf(Array) }	
			{
				[this.adjustedHeads[j], RhythmCell(this.adjustedHeads[j], i[1]).adjustedStruct]
			};
		})
	}


	adjustedLyStruct {

		^this.adjustedStruct.deepCollect(
			this.adjustedStruct.rank+1,
			{|i| durationDict.findKeyForValue(i)}
		)
	}


    getHead { arg thisItem;

		case
		{thisItem.isKindOf(Number)}	
		{^thisItem}
		
		{thisItem.isKindOf(Array)}	
		{^this.getHead(thisItem[0])};
	}


	getHeads { arg thisList;

		^thisList.collect({ arg item;
			this.getHead(item)
		})
	}


	factor {

		var thisFactor, adjustedSum;

		thisFactor = 1;
		adjustedSum = this.heads.sum;

		while(
			{ (adjustedSum) < (this.lenght * 8) },
			{ adjustedSum = adjustedSum * 2; thisFactor = thisFactor * 2 }
		);

		^thisFactor;
	}


	adjustedHeads {
	
		^this.heads * this.factor
	}


	numer {

		^(this.adjustedHeads.sum / gcd((lenght * 8), this.adjustedHeads.sum))
		
	}
	

	denom {

		var thisDenom;
		
		thisDenom = (lenght * 8) / gcd((lenght * 8), this.adjustedHeads.sum);

		if(thisDenom < this.numer, {
			while({(thisDenom * 2) < this.numer}, {
				thisDenom = thisDenom * 2
			})
		});

		^thisDenom
	}


	hasTuplet {
		
		^(this.numer == this.denom).not
	}
	
	
	tuplet {
	
		^[this.numer, this.denom]
	}


	tupletString {

		if(this.hasTuplet, {
			^(  "\\times " ++ this.denom.asString ++
				"/" ++ this.numer.asString ++ " "
			)
		});
	}


	simpleString { arg thisTree, thisLevel=1;

		var levelString = String.new;
		var stringOut = String.new;

		thisLevel.do { levelString = levelString ++ "\t"};

		this.hasTuplet.if({
			stringOut = levelString ++ this.tupletString ++ "{ \n" ++ levelString ++ "\t";
		});

		thisTree.do { arg thisNumber;
			stringOut = stringOut ++ "c'" ++ thisNumber.asString ++ "  "
		};

		this.hasTuplet.if({
			stringOut = stringOut ++ "\n" ++ levelString ++ "}"
		});

		^stringOut;

	}


	woTimeSigString { arg thisLevel =1;
	
		this.struct.containsSeqColl.not.if(
			{ ^this.simpleString(this.adjustedLyStruct, thisLevel)}
		);

	}


	string {

		var thisString;
		
		thisString = "\\time ";
		thisString = thisString ++ measureScaleLily[(this.lenght*2)-1].asString ++ "\n";
		thisString = thisString ++ this.woTimeSigString ++ "\n";

		^thisString
		
	}

	


}
