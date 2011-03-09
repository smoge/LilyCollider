
RhythmCell : LilyRhythmObj {

	var  <>struct, <>lenght;

	
	*new { arg setThisLenght, setThisStruct;

		^super.new.initRhythmCell(setThisLenght, setThisStruct);
	}


	initRhythmCell { arg thisLenght, thisStruct;

		lenght = thisLenght;
		struct = thisStruct;
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


	adjustedStructString {

		^struct.collect({|i,j|

		case
		{i.isKindOf(Number)}
		{
			this.adjustedString[j]
		}

		{i.isKindOf(Array)}
		{
			[this.adjustedString[j], RhythmCell(this.adjustedHeads[j], i[1]).adjustedStructString]
		};
		})
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
            { adjustedSum < (this.heads.size * 8) },
            { adjustedSum = adjustedSum * 2; thisFactor = thisFactor * 2 }
        );
        
        ^thisFactor;
    }


	adjustedHeads {
	
		^this.heads * this.factor
	}


    adjustedString {

        ^this.adjustedHeads.collect({ arg item;
            durationDict.findKeyForValue(item)
        });
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
			^(  "times " ++ this.denom.asString ++
				"/" ++ this.numer.asString ++ " "
			)
		});
	}

	
}



