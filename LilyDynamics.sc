/*

* LilyDynamics.sc

Use:

    a = LilyDynamics(0.9)
    a.scDyn
    a.dynStringList
    a.size
    a.scList
    a.showDict
    a.scDynList
    a.asString
    a.string_(\mp)
    a.scDyn

*/

LilyDynamics {

    var <scDyn, <lilyDyn, <dynStringList, <dynScList, <dynDict, <scDynList;


    *new { |scDyn, dynStringList|

        ^super.new.initDyn(scDyn, dynStringList);
    }


    initDyn { arg newScDyn, newStringList = [\ppp, \pp, \p, \mp, \mf, \f, \ff, \fff];

        this.scDyn_(newScDyn);
        dynStringList = newStringList;
    }


    scDyn_ { |newScDyn|

        scDyn = newScDyn;
    }


    size {
        ^dynStringList.size;
    }


    scList {

          ^scDynList = Array.series(this.size, 1.0, -1/(this.size-1)).invert

    }
    

    dict {

        dynDict = Dictionary.new;
        dynStringList.size.do({|i|
            dynDict.put(dynStringList[i], this.scList[i])
        });
    }


    showDict {

        this.dict;
        ^dynDict;
    }


    asString {

        ^dynStringList.at(this.scList.indexIn(scDyn))
    }


    string_ { | newValue |
        this.dict;
        scDyn = dynDict[newValue];
    }

}