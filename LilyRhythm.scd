
a = RhythmicCell([3, [[2, [1, 1, 1, 1, 1, 1]], 1, 4, 2]] )
b = RhythmicCell([3, [2, [2, [1, 1, 1]], 2, 1]] )
a.reshapeLike(b).plot

a.plot
.lenght_(5)
a.lenghtMul(1/5)
a.plot
a.lenghtAdd(3)



f = [3, [2, [2, [2, 2, 2]], 2, 1]]

g = 4.collect({|i| f.rotateStruct(i) })
g.size
g.asRS.plot
f.rotateStruct(4)


a = TendencyMask(50)
a.gui
a.n_(30)
a.make
a.plot
b = a.asPitchSeq(40, -10)
b.plot

f={ |allowedDurs, n, thisSum|
	
	all {:x,
		x <- allowedDurs.dup(n).allTuples,
		x.sum==thisSum;
	};
};

Post << f.([1,2,3,4], 5, 8)



x.edit

a = [
	[3, [1, [2, [1, 1, [1, [1, 1, 1]], 1, 1, 1]], 1, 4, 2]],
	[4, [2, [2, [1, 1, 1, 1, 1, 1]], 1, 4, 2]],
	[2.5, [[2, [1, 1, 1, 1, 1, 1]], 1, 4, 2]],	
	[5, [4, [2, [1, 1, 1, 1, 1, 1]], 1, 4, 2]],
	[6, [4, [2, [1, 1]], 1, 4, 2]],
	[3, [4, [2, [1, 1, 1]], 1, 4, 2]],
	[8, [4, [2, [1, 1, 1, 1, 1]], 1, 3, 3]],
	[4, [4, [2, [1, 1, 1, 1, 1, 1, 1]], 2, 4, 1]]
];	

a.scramble.asRS

[[1, 2, 3, 4], [[9, 1, 2], [8, 2, 3], [7, 4, 3]]].flop
Help.gui


x = RhythmicGroup2();
x.putMeasure([2, 4, 3, 2, 3, 4, 2]);
x.putStruct([[2, 1, 3, 2, 1], [1, 2, 1, 3, 2], [2, 1, 3, 2, 1, 2, 3, 2], [2, 1, 2, 3, 1]]);
x.voiceArray

x.structMatrix
[
	[2, 4, 3, 2, 3, 4, 2],
	[[2, 1, 3, 2, 1], [1, 2, 1, 3, 2], [2, 1, 3, 2, 1, 2, 3, 2], [2, 1, 2, 3, 1]]
].flop


x.voice
x.plot


x.edit
x.voiceArray

x.structArray
x.measureArray
x.string

(a.scramble.asRS)
x.put(a.scramble.asRS)

x.plot


a = [[10,20],[30, 40, 50], 60, 70, [80, 90, 100]];
b = [[1, 2, [3, 4], [[5], 6], 7], 8, [[9]]];
a.reshapeLike(b);
b.reshapeLike(a);



x = rhythmicseq(a)
x.put(a)
x.plot

a.plot
a.string

a.lenght
a.struct
a.heads
a.heads.sum
a.factor 
a.tuplet
a = [2, [1, 1, 1, 1, 1, 1, 1, 2, 1]].asRC
a.hasTuplet
a.struct
a.adjustedStruct
a.adjustedLyStruct
a.tupletString
a.simpleString(a.adjustedLyStruct, 0)

a = RhythmCell(9,[2, 1, 1, 1])
a = RhythmCell(3, [[2, [1, 1, 1]], 1, 1, 1])
a.noTimeSigString
a.string
a.musicString
a.plot
a.template="compact-rhythmic"
a.templateList
a.tuplet
a.write
a.plot

a.adjustedHeads.sum
a.heads.sum * a.factor
a.factor
a.numer
a.denom
a.tuplet

gcd((a.lenght*8), (a.heads.sum * a.factor))

gcd(72,10)

[1, 2, 3, 4].containsSeqColl
[[2, [1, 1, 1]], 1, 1, 1].containsSeqColl