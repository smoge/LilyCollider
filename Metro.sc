/*
    //////////////////////////////////////////////////
    // Metro.sc									    //
    // 											    //
    // A Metronome with support for changing tempos //
    //////////////////////////////////////////////////
    
    USAGE:
    
    m = Metron([[120, 4], [90, 4], [60, 4]]);
    m.play;
    m.stop;
        
*/

Metron {
    
    var <>array, routine;

    *new { arg thisArray;
        ^super.new.init(thisArray);
    }
    
    
    init { arg thisArray =  [[120, 4], [80, 3], [60, 3], [40, 2], [70, 3], [140,6]];
        
        array = thisArray;
        
        
        SynthDef("impulse", { 
            Out.ar(0, 
                EnvGen.kr(Env.perc, 0.2, doneAction: 2) *
                0.3 * 
                SinOsc.ar([59.99, 60.01].midicps.dup)
            ) 
        }).add;
        
        
        routine = Routine({
            array.do({arg i; 
                Tempo.bpm_(i[0]);             
                i[1].do({ arg i;
                    [i,BeatSched.beat].postln;      
                    Synth(\impulse);        
                    Tempo.beats2secs(1).wait;
                });
                
            });
        });
        
    }

    play {
        
        SystemClock.play(routine);
        
    }


    stop {
        
        SystemClock.stop(routine);
        
    }


}