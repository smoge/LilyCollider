
LilyShowableObj : LilyObj {

    var <>fileName = "~/Desktop/LilySketch";
    var <>pdfViewer = "okular"; // okular / evince
    var <>textEditor = "frescobaldi"; // emacsclient / jedit
    var <>templatesFolder = "~/share/SuperCollider/Extensions/LilyCollider/templates";
    var <>template = "doc";

    
	/* NOTE try later smaller png preview images with sxiv as a viewer */


    *new {
        ^super.new;
    }


    musicString {
        // just add a pair of curly brackets
        ^("{\n" ++  this.string ++ "\n}\n").asString;
    }


    templateFile {
        ^(this.templatesFolder ++ "/" ++ this.template ++ ".ly").standardizePath
    }


    header {
        var file, content;
     
        file = File(this.templateFile,"r");
        content = file.readAllString;
        file.close;
        ^content;
    }


    write {
        var file;

        file = File(this.fileName.standardizePath ++ ".ly","w");
        file.write(this.header);
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
            ).unixCmd { this.show };
        }
    }


    edit {
        this.write;
        (this.textEditor ++ " " ++ this.fileName.standardizePath ++ ".ly").unixCmd;
    }

}
