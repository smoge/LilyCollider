
a = RhythmCell([3, [[2, [1, 1, 1]], 1, 1, 1]] )

a.lyHeads
a.heads
a.subHeads
a.string

a.lenght
a.struct
a.edit
a.string
a.miniLenght
a.noTimeSigString
b = a.adjustedStruct[0]
b.asRC.noTimeSigString
b.string
a.lenght.class
c = [8, [1, 1, 1]].asPseudoBar
c.asRC

c[1].class
[1, [1, 1, 1]].asRC.string
c.asRC.string


4 -> 0.5
8 -> 1
16 -> 2
32 -> 4


a.lenght
a.struct
a.heads
a.heads.sum
a.factor 
a.tuplet
a = [2, [1, 1, 1, 1, 1, 1, 1, 2, 1]].asRC.plo
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