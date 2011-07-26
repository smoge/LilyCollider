
RhythmicCell : LilyRhythmObj {


	var <>struct, <>lenght;
	var <>template = "rhythmic";
	

	*new { arg thisCell;

		^super.new.initRhythmCell(thisCell);
	}
	
	
	initRhythmCell { arg thisCell;

		this.lenght_(thisCell[0]);
		this.struct_(thisCell[1]);
	}



	heads {	

		^this.getHeads(struct);
	}
	

	lyHeads {
	
		^this.heads.collect({|i|  
			
			durationDict.findKeyForValue(i)
		})

	}


	subHeads {
		
		^this.heads.collect({|i|  
			
			i / 8.0
		})

	}


	adjustedStruct { 
	
		^struct.collect({|i,j|  
			
			case
			{ i.isKindOf(Number) }	
			{ this.adjustedHeads[j] }
			
			{ i.isKindOf(Array) }	
			{
				[this.adjustedHeads[j], RhythmicCell([this.adjustedHeads[j], i[1]]).struct]
			};
		})
	}


	adjustedLyStruct {

		^this.adjustedStruct.deepCollect(
			this.adjustedStruct.rank,
			{|i| 
				case
				{i.isKindOf(Number)}	
				{durationDict.findKeyForValue(i)}
				
				{i.isKindOf(Array)}	
				{i};
			}
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

		^(this.adjustedHeads.sum / gcd((lenght * 8).asInteger, this.adjustedHeads.sum.asInteger))
		
	}
	

	denom {

		var thisDenom;
		
		thisDenom = (lenght * 8) / gcd((lenght * 8).asInteger, this.adjustedHeads.sum.asInteger);

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


	noTimeSigString { arg thisLevel =1;

		var stringOut = String.new;
		var levelString = String.new;
		
		thisLevel.do { levelString = levelString ++ "\t"};
		
		this.struct.containsSeqColl.not.if({

				stringOut = this.simpleString(this.adjustedLyStruct, thisLevel)
			
			},{

				this.hasTuplet.if({
					stringOut = levelString ++ this.tupletString ++ "{ \n" ++ levelString ++ "\t";
				});
				
				this.adjustedStruct.do {arg thisItem, thisIndex;

					case
					{thisItem.isNumber}
					{stringOut = stringOut ++ "c'" ++ durationDict.findKeyForValue(thisItem) ++ "  "}

					{thisItem.isArray} 
					{ 
						stringOut = stringOut ++
						(RhythmicCell.new(thisItem.put(0, thisItem.at(0) / 8)).noTimeSigString) ++ " \n \t"; 
					}
					
				}; 

				this.hasTuplet.if({
					stringOut = stringOut ++ "\n" ++ levelString ++ "}"
				});
				
			} 
			
		); //end if

		^stringOut;
	}




	string {

		^("\\time " ++ measureScaleLily[(this.lenght*2)-1].asString ++ "\n" ++ this.noTimeSigString ++ "\n")
	}

	
	musicString {
		// overrides to print on a one-line staff
		^("\\new RhythmicStaff {" ++ this.string ++ "\n}"
		)
			
	}


	////////////////////////////
    // Rhythmic Manipulations //
    ////////////////////////////
	
	
	lenghtAdd { arg thisNumber;
		
		this.lenght_(this.lenght + thisNumber)
	}

	
	lenghtMul  { arg thisNumber;
		
		this.lenght_(this.lenght * thisNumber)
	}


	// substitute an element:
	subst { arg thisIndex, thisItem;
		
		this.struct_(this.struct.put(thisIndex, thisItem))
	}

	
	reshapeLike { arg other;
		//	TODO other index operators: \foldAt \clipAt \at
		this.struct_(this.struct.reshapeLike(other.struct));
	}

}
