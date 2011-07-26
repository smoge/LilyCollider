// Pitch representation with Lilypond suport
 
a = LilyPitch(13.5)
a.plot
a.template="doc"
a.template="minimal"
a.header
a.templateList
a.templateFile
a.template

a.musicString
a.string
a.pitch
a.octave
a.notenumber = 10.5
a.lily = "cis'"
a.string
a.qt
a.cps = 440
a.string
a.lily = "cis'"
a.string
a.notenumber
Help.gui

a = LilyPitch.new(30);
b = LilyPitch.new(31);

d = LilyChord()
d.put(b)
d.includes(30)
d.plot
c = LilyPitch.new(33);
b = LilyChord(a)
b.put([1, 2, 4, 5, 6, 12])
b.notenumber
b.includes(33)
b.includes(LilyPitch(33))
b.plot


b.noteArray
b.put(a)
b.plot

a.filename = "teste2";
a.musicString;
a.ly;
a.openPdf;
a.edit;

// Trying LilyPitchSeq: sequence of Pitches and/or Chords:

a = LilyPitch.new(30);
a.string
b = LilyPitchSeq(a)
b.put(a)
b.eventArray
b.string
b.plot
b.qt
b.musicString
b.eventList
b.put(2)

// Harmonic Series

 true or: false
a = LilyPitch(-25)
a = a.makeHarmonicSeries
a.notenumber
a.ly
a.openPdf

// Articulations

a = LilyPitch(5)
a.putAfterNote("-.")
a.afterNote
a.putAfterNote("->")
a.afterNote
a.putBeforeNote("\\red ")
a.beforeNote
  
/*    TODO:
 *    better to have a Note and a Pitch class?
 * 
 *    Note would have duration, maybe expressions, color
 *    Pitch is just the pitch data
 *    Other kinds of data would have duration: Rest, Skip, Chord, Cluster,
 */
