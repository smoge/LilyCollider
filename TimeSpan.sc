/*

TimeSpan.sc

	Use:
	t = TimeSpan(25); // 25 seconds
	u = TimeSpan(20);
	t = t + 2;
	t = t + u;
	t.duration
	t.asTimeString
	t.numNotes(120); // How many 8th 25 sec with tempo=120 ?
	t.duration_(60);
	t.tempo_(60);
	t.numNotes

*/


TimeSpan {

	var <>duration, <tempo, <quavers, minutes, seconds;


	*new { arg duration=30, tempo=60;
		^super.new.init(duration, tempo);
	}


	init { |thisDuration, thisTempo|

		duration = thisDuration;
		tempo = thisTempo;
	}


	tempo_ { |newTempo|

		tempo = newTempo;
	}


	numNotes {

		^((this.tempo * this.duration) / 60).round(0.5)
	}


	asTimeString {

		^this.duration.asTimeString
	}


	asTimeSigArray {

		^this.numNotes.asTimeSigArray
	}


	asTimeSig {

		^this.numNotes.asTimeSig
	}


	+ { |aThing|

		case
		{ (aThing.class == Integer).or(aThing.class == Float) } {
			this.duration = this.duration + aThing }

		{ aThing.class == TimeSpan } {
			this.duration = this.duration + aThing.duration }
	}


	- { |aThing|

		case
		{ (aThing.class == Integer).or(aThing.class == Float) } {
			this.duration = this.duration - aThing }

		{ aThing.class == TimeSpan } {
			this.duration = this.duration - aThing.duration }
	}


	* { |aThing|

		case
		{ (aThing.class == Integer).or(aThing.class == Float) } {
			this.duration = this.duration * aThing }

		{ aThing.class == TimeSpan } {
			this.duration = this.duration * aThing.duration }
	}


	/ { |aThing|

		case
		{ (aThing.class == Integer).or(aThing.class == Float) } {
			this.duration = this.duration / aThing }

		{ aThing.class == TimeSpan } {
			this.duration = this.duration / aThing.duration }
	}

}