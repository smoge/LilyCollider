
LilyShowableObj : LilyObj {
	
	var <>fileName = "~/Desktop/LilyCollider";
	var <>pdfViewer = "okular"; // "evince"
	var <>textEditor = "frescobaldi"; // "jedit"
	var <>templatesFolder = "~/share/SuperCollider/Extensions/Lily/templates";


	*new {
		^super.new;
	}


    musicString {
        // just add a pair of curly brackets
        ^("{\n" ++  this.string ++ "\n}\n").asString;
    }

   
	write {
		var file;
		
		file = File(this.fileName.standardizePath ++ ".ly","w");
//      file.write(this.header ++ "\n");
		file.write(this.musicString);
		file.close;
	}

    templatePathList {

        ^(templatesFolder ++ "/*").pathMatch

    }
    
	templateList {
        
        ^(this.templatePathList.collect {|i| i.basename})
        
    }
    
    show {

        (
            this.pdfViewer ++ " " ++ this.fileName.standardizePath ++ ".pdf"
        ).unixCmd;
    }

	
	plot {
		
		fork {
			this.write;
			0.1.wait;
			(
                "lilypond -o " ++ this.fileName.standardizePath ++ " " ++
                this.fileName.standardizePath ++ ".ly"  
            ).unixCmd { this.show};
		}
	}

	
	edit {
		this.write;
		(
			this.textEditor ++ " " ++ this.fileName.standardizePath ++ ".ly"
		).unixCmd;
	}
	
	


}
