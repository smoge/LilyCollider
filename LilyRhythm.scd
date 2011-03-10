
a = RhythmCell(2, [1, 2, 2, 1, 1, 1, 1])
a = RhythmCell(3, [2, 1, 1, 1])
a = RhythmCell(3, [[2, [1, 1, 1]], 1, 1, 1])


a.lenght
a.struct
a.heads
a.heads.sum
a.factor 
a.tuplet
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