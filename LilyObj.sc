
LilyObj {

    classvar <pitchList, <octaveList, <pitchDict, <octDict;
    classvar <afterNoteDict, <beforeNoteDict;
	
    *initClass {


        pitchList = [ "c", "cis", "d", "dis", "e", "f", "fis", "g",
            "gis","a", "ais", "b" ];

        octaveList = [ ",,,,", ",,,", ",,", ",", " ", "'", "''",
            "'''", "''''" ];

        pitchDict = Dictionary[ "c"     ->    0,
                                "cih"   ->  0.5,
                                "cis"   ->    1,
                                "des"   ->    1,
                                "cisih" ->  1.5,
                                "deh"   ->  1.5,
                                "d"     ->    2,
                                "dih"   ->  2.5,
                                "eeseh" ->  2.5,
                                "dis"   ->    3,
                                "ees"   ->    3,
                                "disih" ->  3.5,
                                "eeh"   ->  3.5,
                                "e"     ->    4,
                                "eis"   ->  4.5,
                                "feh"   ->  4.5,
                                "f"     ->    5,
                                "fih"   ->  5.5,
                                "geseh" ->  5.5,
                                "fis"   ->    6,
                                "ges"   ->    6,
                                "fisih" ->  6.5,
                                "geh"   ->  6.5,
                                "g"     ->    7,
                                "gih"   ->  7.5,
                                "aeseh" ->  7.5,
                                "gis"   ->    8,
                                "aes"   ->    8,
                                "gisih" ->  8.5,
                                "aeh"   ->  8.5,
                                "a"     ->    9,
                                "aih"   ->  9.5,
                                "beseh" ->  9.5,
                                "ais"   ->   10,
                                "bes"   ->   10,
                                "aisih" -> 10.5,
                                "beh"   -> 10.5,
                                "b"     ->   11,
                                "bih"   -> 11.5,
                                "ceh"   -> 11.5  ];


        octDict = Dictionary[ ",,,"  -> -4,
                              ",,"   -> -3,
                              ","    -> -2,
                              ""     -> -1,
                              "'"    ->  0,
                              "''"   ->  1,
                              "'''"  ->  2,
                              "''''" ->  3  ];     
    }
    
}