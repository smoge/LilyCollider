/*
    Dyn.sc

    Use:

    a = Dyn(0.9)
    a.scDyn
    a.vol_(0.2)
    a.str_(\mf)
    a.scale
    a.scale = [\p, \mp, \mf, \f, \ff]
    a.size
    a.scList
    a.showDict
    a.scDynList
    a.asString
    a.string_(\mp)
    a.scDyn

*/

Dyn {

    var <scDyn, <lilyDyn, <>scale, <dynScList, <dynDict, <scDynList;

    *new { arg scDyn, scale;
        ^super.new.initDyn(scDyn, scale);
    }

    initDyn { arg newScDyn=0.5, newStringList = [\ppp, \pp, \p, \mp, \mf, \f, \ff, \fff];
        this.scDyn_(newScDyn);
        scale = newStringList;
    }

    scDyn_ { arg newScDyn;
        scDyn = newScDyn;
    }

    vol_ { arg newScDyn;
        scDyn = newScDyn;
    }

    size {
        ^scale.size;
    }

    scList {
        ^scDynList = Array.series(this.size, 1.0, -1/(this.size-1)).invert
    }

    dict {
        dynDict = Dictionary.new;
        scale.size.do({|i|
            dynDict.put( scale[i], this.scList[i] )
        });
    }

    showDict {
        this.dict;
        ^dynDict;
    }

    asString {
        var list;
        list = this.scList;
        ^scale.at(list.indexIn(scDyn))
    }

    string_ { arg newValue;
        this.dict;
        scDyn = dynDict[newValue];
    }

    str_ { arg newValue;
        this.dict;
        scDyn = dynDict[newValue];
    }

}