// =====================================================================
// TendencyMask.sc
// =====================================================================

/*
    A user interface to Josh Parmenter's Tendency.sc

    USAGE:

    a = TendencyMask(50)

    a.gui
    a.guiAB

    a.parX
    a.parY
    a.parA
    a.parB

    a.xLine(0.5, 1.0)
    a.yLine(0.5, 1.0)
    a.aLine(0.5, 1.0)
    a.bLine(0.5, 1.0)

    a.xyLines(1.0, 0.3, 0.8, 0.2)
    a.abLines(1.0, 0.3, 0.8, 0.2)

    a.n_(30)
    a.make
    a.plot

    a.dist = \lpRand
    a.dist = \hpRand
    a.dist = \meanRand
    a.betaRand = \betaRand

*/

TendencyMask {

    var <>parX, <>parY, <>parA, <>parB, <>n, <thisTendency, <>dist, <>data;

    *new {|n|
        ^super.new.init(n);
    }

    init {arg thisN=100;

        this.n_(thisN);
        dist = \uniform;

    }

    gui {

        /////////////////////////////////
        // /////// GUI Start ///////// //
        /////////////////////////////////

        var win, view1, view2, swapData, config, urView, data1, data2;
        data1 = 1.0.dup(50);
        data2 = 0.0.dup(50);
        win = GUI.window.new("Tendency Mask par X/Y", Rect(200 , 450, 680, 520));
        GUI.button.new(win, Rect(0,0, 80,20)).states_([["Botton"],["Top"]]).action_{|v| swapData.(v.value)};
        urView = {GUI.multiSliderView.new(win, Rect(10, 24, 653, 480))};
        // General Configurations for the two MultiSliderView:
        config = {
            view1.drawLines_(true);
            view1.strokeColor_(Color.red);
            view2.drawLines_(true);
            view2.strokeColor_(Color.blue);
        };

        // Function that has a if control
        // depends of the button state
        swapData = { arg dofocusOn;

            // hacked from examples folder
            if(
                dofocusOn == 0,
                {
                    // if dofocusOn == 0
                    view1.remove;
                    view2.remove;
                    view1 = urView.value;
                    view2 = urView.value;
                    config.value;
                    view1.value_(data1);
                    view2.value_(data2);
                    "view2".postln;
                    view2.action_({|sl|
                        data2 = sl.value;
                        parY = Env(data2, 1.dup(data2.size - 1).normalizeSum);
                        thisTendency = Tendency.new( parX, parY, parA, parB);
                    });
                }, {
                    // if dofocusOn == 1
                    view1.remove;
                    view2.remove;
                    view2 = urView.value;
                    view1 = urView.value;
                    config.value;
                    view2.value_(data2);
                    view1.value_(data1);
                    "view1".postln;
                    view1.action_({|sl|
                        data1 = sl.value;
                        parX = Env(data1, 1.dup(data2.size - 1).normalizeSum);
                        thisTendency = Tendency.new( parX, parY, parA, parB);
                    });
                }
            );
        };
        swapData.value(0);
        win.front;

        /////////////////////////////////
        // ///// GUI End ///////////// //
        /////////////////////////////////
    }


    guiAB {

        /////// GUI Start /////////

        var win, view1, view2, swapData, config, urView, data1, data2;
        win = GUI.window.new("Tendency Mask par A/B", Rect(200 , 450, 680, 520));
        data1 = 1.0.dup(50);
        data2 = 0.0.dup(50);
        GUI.button.new(win, Rect(0,0, 80,20)).states_([["Botton"],["Top"]]).action_{|v| swapData.(v.value)};
        urView = {GUI.multiSliderView.new(win, Rect(10, 24, 653, 480))};
        // General Configurations for the two MultiSliderView:
        config = {
            view1.drawLines_(true);
            view1.strokeColor_(Color.red);
            view2.drawLines_(true);
            view2.strokeColor_(Color.blue);
        };

        // Function that has a if control
        // depends of the button state
        swapData = { arg dofocusOn;

            if(
                dofocusOn == 0,
                {
                    // if dofocusOn == 0
                    view1.remove;
                    view2.remove;
                    view1 = urView.value;
                    view2 = urView.value;
                    config.value;
                    view1.value_(data1);
                    view2.value_(data2);
                    "view2".postln;
                    view2.action_({|sl|
                        data2 = sl.value;
                        parA = Env(data2, 1.dup(data2.size - 1).normalizeSum);
                        thisTendency = Tendency.new( parX, parY, parA, parB);
                    });
                }, {
                    // if dofocusOn == 1
                    view1.remove;
                    view2.remove;
                    view2 = urView.value;
                    view1 = urView.value;
                    config.value;
                    view2.value_(data2);
                    view1.value_(data1);
                    "view1".postln;
                    view1.action_({|sl|
                        data1 = sl.value;
                        parB = Env(data1, 1.dup(data2.size - 1).normalizeSum);
                        thisTendency = Tendency.new( parX, parY, parA, parB);
                    });
                }
            );
        };
        swapData.value(0);
        win.front;
        ///// GUI End /////////////
    }



    xLine { | start, end |
        this.parX = Env([start, end], [1.0]);

    }

    yLine { | start, end |
        this.parY = Env([start, end], [1.0]);
    }

    xyLines { | startX, startY, endX, endY |
        this.parX = Env([startX, endX], [1.0]);
        this.parY = Env([startY, endY], [1.0]);
    }

    aLine { | start, end |
        this.parA = Env([start, end], [1.0]);
    }

    bLine { | start, end |
        this.parB = Env([start, end], [1.0]);
    }

    abLines { | startA, startB, endA, endB |
        this.parA = Env([startA, endA], [1.0]);
        this.parB = Env([startB, endB], [1.0]);
    }

    make {

        var thisOut;

        thisTendency = Tendency.new( parX, parY, parA, parB);
        thisOut = n.collect({ |i|
            i = i / n;
            thisTendency.at(i, dist.asString)
        });

        this.data_(thisOut);
        ^thisOut;
    }

    plot {
        this.make.plot(discrete: true, minval:0.0, maxval:1.0);
    }


    ////////////////////////////
    // Integration with Pitch //
    ////////////////////////////

    asPitchSeq { arg thisMul=12, thisAdd=0;

        var pitchArray;

        pitchArray = data.collect { |i|
            LilyPitch((i * thisMul) + thisAdd)
        };

        ^LilyPitchSeq(pitchArray);

    }







}