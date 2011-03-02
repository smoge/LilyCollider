// Pitch representation with Lilypond suport
 
a = LilyPitch(13.5).plot

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

a = LilyPitch.new(30);
a.filename = "teste2";
a.musicString;
a.ly;
a.openPdf;
a.edit;

// Harmonic Series
 
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
