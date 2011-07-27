/*

    # LilyDynSeq.sc #

    _Sequenceble Collection of Dyn (Dynamics) Instances_
    See Dyn.sc


    ## Default ##

    scale = [\ppp, \pp, \p, \mp, \mf, \f, \ff, \fff]
    values: 0.0 <-> 1.0


    ## Usage ##

    a = LilyDynSeq.new([0.3, 0.2, 0.4])
    a = LilyDynSeq.new(
    ((1..10)/10).scramble,
    [\pppp, \ppp, \pp, \p, \mp, \mf, \f, \ff, \fff, \ffff]
    );

    a.dynSeq
    a.add(0.9)
    a.add(0.1)
    a.dynSeq[0]
    a.dynSeq[1]
    a.scDyn
    a.asString
    a.str_([\pp, \mp, \f])
    a.dynSeq[0]
    a.newRange(0.3, 0.6)
    a.scramble
    a.invert
    a.reverse
    a.rotate(2)
    a.mean
    a.first
    a.firstStr
    a.last
    a.lastStr
    a.permute(1)
    a.plot
    a.setTable(9)

*/

LilyDynSeq {

    var <dynSeq, <dynStringList;

    *new { | dynSeq, dynStringList |
        ^super.new.initDynSeq(dynSeq, dynStringList);
    }

    initDynSeq { arg newDynSeq = [0.5], newStringList = [\ppp, \pp, \p, \mp, \mf, \f, \ff, \fff];

        this.dynSeq_(newDynSeq);
        dynStringList = newStringList;

    }

    dynSeq_ { | newDynSeq |

        dynSeq = newDynSeq.collect({|i|
            LilyDyn(i, dynStringList);
        });
    }

    scDyn {

        ^dynSeq.collect({ | i |
            i.scDyn
        })
    }

    vol_ { | newDynSeq |

        dynSeq = Array.newClear(newDynSeq.size);
        dynSeq = newDynSeq.collect({|i|
            LilyDyn(i, dynStringList);
        });
    }

    size {
        ^dynSeq.size
    }

    asString {
        ^dynSeq.collect({|i|
            i.asString;
        })
    }

    str_ { | newStrList |
        max(newStrList.size, dynSeq.size).do({|i|
            dynSeq[i].str_(newStrList[i])
        })
    }

    newRange { arg newMin, newMax, newDynString = dynStringList;
        var newVol;
        newVol = this.scDyn.normalize * (newMax - newMin) + newMin;

        dynSeq = newVol.collect({|i|
            LilyDyn(i, newDynString);
        });
    }

    invert { arg newDynString = dynStringList;
        dynSeq = this.scDyn.invert.collect({|i|
            LilyDyn(i, newDynString);
        });
    }

    reverse { arg newDynString = dynStringList;
        dynSeq = this.scDyn.reverse.collect({|i|
            LilyDyn(i, newDynString);
        });
    }

    scramble { arg newDynString = dynStringList;
        dynSeq = this.scDyn.scramble.collect({|i|
            LilyDyn(i, newDynString);
        });
    }


    rotate { arg thisArg, newDynString = dynStringList;
        dynSeq = this.scDyn.rotate(thisArg).collect({|i|
            LilyDyn(i, newDynString);
        });
    }

    add { arg thisArg, newDynString = dynStringList;
        dynSeq = this.scDyn.add(thisArg).collect({|i|
            LilyDyn(i, newDynString);
        });
    }

    permute { arg thisArg, newDynString = dynStringList;
        dynSeq = this.scDyn.permute(thisArg).collect({|i|
            LilyDyn(i, newDynString);
        });
    }

    mean {
        ^LilyDyn(this.scDyn.mean, dynStringList);
    }

    first {
        ^this.scDyn.first;
    }

    firstStr {
        ^this.asString.first;
    }

    last {
        ^this.scDyn.last;
    }

    lastStr {
        ^this.asString.last;
    }

    sum {
        ^this.scDyn.sum
    }

    plot {
        this.scDyn.plot(discrete: true, minval: 0.0, maxval:1.0)
    }

    setTable { |n|

        var thisWindow, thisMultiSlider, thisArray;
        thisArray = Array.newClear(n);
        thisWindow = Window.new(this.class.asString, Rect(316, 409, n*13+20, 220)).front;
        thisMultiSlider = GUI.multiSliderView.new(thisWindow,Rect(7, 9, n*13+2, 200));
        thisMultiSlider.value=Array.fill(n, {|v| 0.5.gauss(0.1)});
        thisMultiSlider.action_{|v|
            this.vol_(v.value);
            this.asString.postln;
        };
    }
}